<template>
  <div class="app-container">
    <el-header align="center" style="font-size: 50px">资料锦集</el-header>
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="Title" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading=""
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column  label="编号id" prop="id" v-if="false" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" prop="username" sortable="custom" align="center" width="250" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件名称" prop="username" sortable="custom" align="center" width="210" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.fileName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="username" sortable="custom" align="center" width="190" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.creatDate }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建者"  v-if="false" prop="username" sortable="custom" align="center" width="150" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.creatId }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建者姓名" prop="username" sortable="custom" align="center" width="190" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.creatName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="状态" prop="username" sortable="custom" align="center" width="183" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span v-if="row.state==1">创建</span>
          <span v-if="row.state==2">待审核</span>
          <span v-if="row.state==3">已审核</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" prop="fileAddress" v-if="false" sortable="custom" align="center" width="190" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.fileAddress }}</span>
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            修改
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
          <el-button type="warning" size="mini" v-if="row.state==3" >
            下载
          </el-button>
        </template>

      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" enctype="multipart/form-data" style="width: 400px; margin-left:50px;">
        <el-form-item label="标题" prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
        <el-form-item label="状态" v-if="temp.state!=''">
          <el-select v-model="temp.state"  class="filter-item" placeholder="Please select">
            <el-option v-for="item in statusOptions" :key="item.key" :label="item.label" :value="item.key"  />
          </el-select>
        </el-form-item>
        <el-form-item label="文件上传">
        <el-upload
          class="upload-demo"
          v-model="temp.fileAddress"
          ref="upload"
          action="http://localhost:8001/datum/uploada"
          :limit="1"
          :before-upload="beforeUpload">
          <el-button  size="small" type="primary">选取文件</el-button>
          <!--<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload()">上传到服务器</el-button>-->
          <div slot="tip" class="el-upload__tip">上传模板只能是 .zip,.rar,.doc,.xls,.xlsx,.ppt,.pptx,.docx,.txt,.pdf,.mkv,.mp4,.avi,.rm,.rmvb格式, 且不超过1G!</div>
        </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          提交
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
<script type="text/css" src="../../utils/webuploader/webuploader.css"></script>
<script type="text/javascript" src="../../utils/webuploader/webuploader.min.js"></script>
<script>
  import { fetchListDau,fetchListUser,deleteDatum, fetchListEdu, fetchPv, createArticle,createArticleDau, updateArticleD,updateArticle,uploadfile } from '@/api/article'
  import waves from '@/directive/waves' // waves directive
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination' // secondary package based on el-pagination
  const calendarTypeOptions = [
    { key: '1', display_name: '领导讲话' },
    { key: '2', display_name: '廉政要闻' },
    { key: '3', display_name: '文件制度' },
    { key: '4', display_name: '警钟长鸣' }
  ]


  // arr to obj, such as { CN : "China", US : "USA" }
  const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})

  import { del } from '@/api/user'
  import {
    quillEditor
  } from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'
  export default {
    name: 'ComplexTable',
    components: { Pagination,quillEditor,},
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
        content: null,
        editorOption: {},
        tableKey: 0,
        list: null,
        file:'',
        total: 0,
        type: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 10,
          importance: undefined,
          title: '',
          sort: '+id'
        },
        importanceOptions: [1, 2, 3],
        calendarTypeOptions,
        sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
        statusOptions: [{label:'创建',key:1},{label:'待审',key:2},{label:'已审核',key:3}],

        showReviewer: false,
        temp: {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          fileName:'',
          state: '',
          createDate:new Date(),
          addTime:'',
          createId:'',
          creator:'',
          fileAddress:'',
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
    },
    methods: {
      getList() {
        this.listLoading = true
        fetchListDau(this.listQuery).then(response => {
          this.list = response.data.items
          this.total = response.data.total

          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })
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
        row.status = status
      },
      sortChange(data) {
        const { prop, order } = data
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
          origin:'',
          context:'',
          pubdate:'',
          addTime:'',
          createId:'',
          creator:'',
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
            this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
            this.temp.author = 'vue-element-admin'
            createArticleDau(this.temp).then(() => {
              this.list.unshift(this.temp)
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
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
            updateArticleD(tempData).then(() => {
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
        deleteDatum(row.id).then((response) => {
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
      beforeUpload(file){
        console.log('FGHJ')
        console.log(file,'文件');
        this.files = file;
        const extension = file.name.split('.')[1] === 'zip'
        const extension2 = file.name.split('.')[1] === 'rar'
        const extension3 = file.name.split('.')[1] === 'doc'
        const extension4 = file.name.split('.')[1] === 'xls'
        const extension5 = file.name.split('.')[1] === 'xlsx'
        const extension6 = file.name.split('.')[1] === 'ppt'
        const extension7 = file.name.split('.')[1] === 'pptx'
        const extension8 = file.name.split('.')[1] === 'docx'
        const extension9 = file.name.split('.')[1] === 'txt'
        const extension10 = file.name.split('.')[1] === 'pdf'
        const extension11 = file.name.split('.')[1] === 'mkv'
        const extension12 = file.name.split('.')[1] === 'mp4'
        const extension13 = file.name.split('.')[1] === 'avi'
        const extension14 = file.name.split('.')[1] === 'rm'
        const extension15 = file.name.split('.')[1] === 'rmvb'
        const isLt2M = file.size / 1024 / 1024 < 1024
        if (!extension && !extension2&&!extension3 && !extension4&&!extension5 && !extension6&&!extension7 && !extension8&&!extension9 && !extension10&&!extension11 && !extension12&& !extension13&&!extension14 && !extension15) {
          this.$message.warning('上传模板只能是 .zip,.rar,.doc,.xls,.xlsx,.ppt,.pptx,.docx,.txt,.pdf,.mkv,.mp4,.avi,.rm,.rmvb格式, 且不超过1G!')
          return
        }
        if (!isLt2M) {
          this.$message.warning('上传模板大小不能超过 1GB!')
          return
        }
        this.temp.fileName = file.name;
        this.temp.fileAddress=file.uid;
        console.log(this.fileName)
        this.submitUpload(file);
        return false // 返回false不会自动上传
      },
      submitUpload(file) {
        console.log('上传'+this.files.name)
        if(this.fileName == ""){
          this.$message.warning('请选择要上传的文件！')
          return false
        }
        console.log(this.files)
        let fileFormData = new FormData();
        fileFormData.append('file', this.files);//filename是键，file是值，就是要传的文件，test.zip是要传的文件名
        //fileFormData.append('rid',this.temp.rid)
        //var d = new Date(this.temp.created);
        //var times=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        //fileFormData.append('created',times)
        fileFormData.append('temp',this.temp)

        let requestConfig = {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        }
        this.$http.post('http://localhost:8001/datum/upload',fileFormData,requestConfig).then(rep =>{
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            contentType: false,
            onClose: () => {
              this.visible = false
              this.$emit('refreshDataList')
            }
          }).catch((e) => {}),
          this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
          this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length-1].path)
        })
        /*uploading(fileFormData,requestConfig).then(rep=>{
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.visible = false
              this.$emit('refreshDataList')
            }
          })
        })*/
        //this.$refs.upload.submit();
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
      getSortClass: function(key) {
        const sort = this.listQuery.sort
        return sort === `+${key}` ? 'ascending' : 'descending'
      },
      onFileSuccess: function (rootFile, file, response, chunk) {
        //这里可以根据response（接口）返回的数据处理自己的实际问题（如：从response拿到后台返回的想要的数据进行组装并显示）
        //注，这里从文件夹每上传成功一个文件会调用一次这个方法
      }
    }
  }
</script>
<style>

  img{
    max-width:100%;height:auto;
  }


   .uploader-example {
     width: 90%;
     padding: 15px;
     margin: 40px auto 0;
     font-size: 12px;
     box-shadow: 0 0 10px rgba(0, 0, 0, .4);
   }

  .uploader-example .uploader-btn {
    margin-right: 4px;
  }

  .uploader-example .uploader-list {
    max-height: 440px;
    overflow: auto;
    overflow-x: hidden;
    overflow-y: auto;
  }

</style>
