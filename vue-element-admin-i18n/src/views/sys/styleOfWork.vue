<template>
  <div align="center">
    <el-container>
      <el-header>
        <h1>通知</h1>
      </el-header>
      <el-main>


      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="Title" prop="title" v-show="false">
          <el-input v-model="temp.id"  v-show="false"/>
        </el-form-item>
        <el-form-item label="Type" prop="type">
          <el-select v-model="temp.type"  class="filter-item" placeholder="Please select">
            <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.display_name" />
          </el-select>
        </el-form-item>

        <el-form-item label="did" prop="did">
        <el-aside>
          <el-tree
            v-model="temp.depId"
          :data="mlists"
          show-checkbox
          node-key="id"
          ref="tree"
          :props="defaultProps"
          @check-change="handleCheckChange">
          </el-tree>
       </el-aside>
        </el-form-item>
        <el-form-item label="accessory">
        <el-aside>
          <el-upload
            v-model="temp.accessory"
            class="upload-demo"
            ref="upload"
            action="http://localhost:8001/datum/upload"
            :limit="1"
            >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <!--<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload()">上传到服务器</el-button>-->
            <div slot="tip" class="el-upload__tip">上传模板只能是 .zip,.rar,.doc,.xls,.xlsx,.ppt,.pptx,.docx,.txt,.pdf,.mkv,.mp4,.avi,.rm,.rmvb格式, 且不超过1G!</div>
          </el-upload>
        </el-aside>
        </el-form-item>
        <el-main>
        <el-form-item label="Title" prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
          <el-card style="height: 610px;">
            <quill-editor v-model="temp.context" ref="myQuillEditor" style="height: 500px;" :options="editorOption">
            </quill-editor>
          </el-card>
        </el-main>
    <el-footer>
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="createData()">
          Confirm
        </el-button>
    </el-footer>
      </el-form>
      </el-main>
    </el-container>
  </div>

</template>


<script>
  import { fetchListUser, fetchListEdu, fetchPv, createArticleSup, updateArticle } from '@/api/article'
  import waves from '@/directive/waves' // waves directive
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination' // secondary package based on el-pagination

  const calendarTypeOptions = [
    { key: '1', display_name: '作风建设' },
    { key: '2', display_name: '纪检再检查' },
    { key: '3', display_name: '执行力检查' }
  ];

  const cityOptions = ['上海', '北京', '广州', '深圳'];

  // arr to obj, such as { CN : "China", US : "USA" }
  const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})

  import { del } from '@/api/user'
  import {quillEditor} from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'
  import { groupDept } from '@/api/sys/dept'
  import { add, update, list, deleteMenu,mlist } from "@/api/sys/menu";

  export default {
    name: 'ComplexTable',
    components: { Pagination,quillEditor},
    directives: { waves },
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      },
      typeFilter(type) {
        return calendarTypeKeyValue[type]
      }
    },
    data() {
      return {
        path:'',

        mlists:[],
        defaultProps: {
          children: 'deptCharlen',
          label: 'deptName',
        },
        checkAll: false,
        checkedCities: [],
        cities: cityOptions,
        isIndeterminate: true,
        content: null,
        show:false,
        editorOption: {},
        tableKey: 0,
        list: null,
        total: 0,
        type: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 10,
          importance: undefined,
          title: '',
          listType: 3,
          sort: '+id'
        },
        importanceOptions: [1, 2, 3],
        calendarTypeOptions,
        sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
        statusOptions: [{label:'开启',key:1},{label:'关闭',key:2}],

        showReviewer: false,
        temp: {
          s:undefined ,
          depId:'',
          path:'',
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          type: '请选择',
          InformationTypes:'',
          state: null ,
          context:'',
          accessory:'',
          createTime:'',
          createId:'',
          creator:'',

        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        dialogPvVisible: false,
        pvData: [],
        rules: {
          type: [{ required: true, message: 'type is required', trigger: 'change' }],
          timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
          title: [{ required: true, message: 'title is required', trigger: 'blur' }]
        },
        downloadLoading: false
      }
    },
    created() {
      this.getList()
      this.Mlist()
    },
    methods: {

      getList() {
        this.listLoading = true
        groupDept().then(response => {
          this.list = response.data.items
          this.total = response.data.total
          console.log('出来吧', response)
          response.data.items.filter(item => {
            this.cities.push(item);
          })
          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })
      },
      Mlist(val = 0) {
        groupDept().then(response => {
          console.log("mlists", response);
          this.mlists = [];
          response.data.dept.filter(dept => {
            this.mlists.push(dept);
          });
        });
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      handleModifyStatus(row, status) {
        this.$message({
          message: '操作Success',
          type: 'success'
        })
        row.state = status
      },
      sortChange(data) {
        const {prop, order} = data
        if (prop === 'id') {
          this.sortByID(order)
        }
      },
      sortByID(order) {
        if (order === 'ascending') {
          this.listQuery.sort = '+id'
        } else {
          this.listQuery.sort = '-id'
        }
        this.handleFilter()
      },
      resetTemp() {
        this.temp = {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          InformationTypes: '',
          state: '',
          origin: '',
          context: '',
          pubdate: '',
          addTime: '',
          createId: '',
          creator: '',
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.temp.id = null // mock a id
            this.temp.author = 'vue-element-admin'
            this.temp.deptId=this.temp.s
            console.log("fuck"+this.temp)

            console.log(this.temp.deptId)
            createArticleSup(this.temp).then(() => {
              this.list.unshift(this.temp)
              console.log(this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: 'Success',
                message: 'Created Successfully',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.temp.timestamp = new Date(this.temp.timestamp)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleCheckAllChange(val) {
        this.checkedCities = val ? cityOptions : [];
        this.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },
      handleCheckChange () {
        let res = this.$refs.tree.getCheckedNodes()
        let arr = []
        res.forEach((item) => {
          arr.push(item.id)
        })
        this.temp.s = arr.toString()
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
            updateArticle(tempData).then(() => {
              const index = this.list.findIndex(v => v.id === this.temp.id)
              this.list.splice(index, 1, this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: 'Success',
                message: 'Update Successfully',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row, index) {
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
        del(row.id).then((response) => {
          this.getList()
          this.$notify({
            title: '成功',
            message: response.data.message,
            type: 'success',
            duration: 2000
          })
        })
        alert(parseInt(index.toString()) + 1)
        // this.list.splice(index, 1)
      },
      handleFetchPv(pv) {
        fetchPv(pv).then(response => {
          this.pvData = response.data.pvData
          this.dialogPvVisible = true
        })
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
          const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
          const data = this.formatJson(filterVal)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'table-list'
          })
          this.downloadLoading = false
        })
      },
      formatJson(filterVal) {
        return this.list.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      },
      getSortClass: function (key) {
        const sort = this.listQuery.sort
        return sort === `+${key}` ? 'ascending' : 'descending'
      },
    }
  }
</script>
<style>

  img{
    max-width:100%;height:auto;
  }
</style>
