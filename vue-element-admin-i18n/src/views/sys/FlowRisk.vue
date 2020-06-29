
<template>
  <div class="app-container">
    <div class="filter-container">
          <div style="width: 200px;" class="filter-item">
             <el-input v-model="listQuery.pgname" placeholder="输入流程风险项目名称" />
          </div>
          <div style="width: 220px;" class="filter-item">
                  <el-date-picker
                    v-model="listQuery.qyearq"
                    type="year"
                    placeholder="选择年">
              </el-date-picker>
          </div>
      <el-button  v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getList">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
    </div>
    <!--  数据表格-->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="ID" prop="id" sortable="custom" align="center" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

<el-table-column label="序号" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.id }}</span>
        </template>
      </el-table-column>


      <el-table-column label="风险流程编号" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.riskId }}</span>
        </template>
      </el-table-column>


      <el-table-column label="风险流程名称" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.flowName }}</span>
        </template>
      </el-table-column>


      <el-table-column label="风险流程年份" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.flowYear }}</span>
        </template>
      </el-table-column>

      <el-table-column label="附件" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.accessory }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建人" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.createBy}}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.createTime }}</span>
        </template>
      </el-table-column>

      <!--     自定义列-->
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="danger" @click="handleDelete(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页工具条  page当前页 total总记录数 limit每页显示多少条 pagination触发自定义事件，查询数据-->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!--  绑定了title，是一个数组里取的，表示是修改的标题还是添加的标题
      visible.sync 对话框是否显示
    -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" style="width: 120%">
      <!--
          rules:校验规则
          model:数据绑定
      -->
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:px;">
        <!--        数据校验要求prop值和temp.属性名一致-->

        <el-form-item label="岗位编号" prop="riskId">
          <el-input placeholder="请输入岗位编号" v-model="temp.riskId" />
        </el-form-item>




        <el-form-item label="风险项目名" prop="project">
          <el-input placeholder="请输入名称" v-model="temp.project" />
        </el-form-item>



        <el-form-item label="廉政风险点描述">
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入廉政风险点描述"
            v-model="temp.riskDescribe">
          </el-input>
        </el-form-item>


        <el-form-item label="廉政风险值" prop="D">
          <el-input v-model="temp.riskD" />
        </el-form-item>


        <el-form-item label="风险等级" prop="Lv">
          <el-input v-model="temp.riskGrade" />
        </el-form-item>


        <el-form-item label="预防和控制措施">
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入预防和控制措施"
            v-model="temp.riskGuard">
          </el-input>
        </el-form-item>


        <el-form-item label="创建人" prop="createBy">
          <el-input v-model="temp.createBy" />
        </el-form-item>


        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="temp.createTime" />
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <!--
          dialogStatus==='create'?createData():updateData()
          dialogStatus需要我们根据情况去改变
        -->
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确认
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  //
  import { add, list ,deleteFlow} from '@/api/sys/Flow'
  import waves from '@/directive/waves' // waves directive
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination' // 分页组件
  export default {
    name: 'userTable',
    components: { Pagination },
    directives: { waves },
    data() {
      return {
        tableKey: 0,
        list: null, // 后台返回，给数据表格展示的数据
        total: 0, // 总记录数
        listLoading: true, // 是否使用动画

        listQuery: {  //获取查询的条件
          pgname: '',
          qyear: '',
          qyearq: '',
        },

        temp: { // 添加、修改时绑定的表单数据
          id: undefined,
          riskId: '',
          flowName: '',
          flowYear: '',
          flowMassGe: '',
          accessory: '',
          createTime: '',
          createBy: '',
          createName: '',
          state: '',
        },
        title: '添加', // 对话框显示的提示 根据dialogStatus create
        dialogFormVisible: false, // 是否显示对话框
        dialogStatus: '', // 表示表单是添加还是修改的
        rules: {
          // 校验规则
          //username: [{ required: true, message: '用户名必填', trigger: 'blur' }]
        },
        options:[],
        placeholder: "请选择",
         props: {
        children: "deptCharlen",
        label: "deptName",
        value: "id",
        checkStrictly: true,
        emitPath:false
      }
      }
    },
    // 创建实例时的钩子函数
    created() {
      this.getList() //调用查询

    },

  methods: {

      // 去后台取数据的
      getList() {
        // 开始转圈圈
        this.listLoading = true

        console.log(123456789)

        console.log(this.listQuery)

        list(this.listQuery).then(response => {

          console.log(987654321)
          
          console.log(response)

          this.list = response.data.items
          // 转圈圈结束
          console.log(response)

          this.listLoading = false
        })
      },

      resetTemp() {
        this.temp = {
          id: undefined,
          riskId: '',
          flowName: '',
          flowYear: '',
          flowMassGe: '',
          accessory: '',
          createTime: '',
          createBy: '',
          createName: '',
          state: '',
        }
      },
      // 显示添加的对话框
      handleCreate () {
        // 重置表单数据
        this.resetTemp()
        // 点击确定时，是执行添加操作
        this.dialogStatus = 'create'
        this.title="添加岗位风险"
        // 显示对话框
        this.dialogFormVisible = true
        this.$nextTick(() => {
          // 表单清除验证
          this.$refs['dataForm'].clearValidate()
        })
      },
      // 添加对话框里，点击确定，执行添加操作
      createData() {
        // 表单校验
        this.$refs['dataForm'].validate((valid) => {
          // 所有的校验都通过
          if (valid) {
            // 调用api里的sys里的user.js的ajax方法
            add(this.temp).then((response) => {

              // 关闭对话框
              this.dialogFormVisible = false
              // 刷新数据表格里的数据
              this.getList()
              // 显示一个通知
              this.$notify({
                title: '成功',
                message: response.data.message,
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      
      handleDelete(row) {
        // 先弹确认取消框
        this.$confirm('确认删除【'+row.project+'】的信息吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 调用ajax去后台删除
          deleteFlow( row.id ).then((response) => {
            // 刷新数据表格
            this.getList()
            // ajax去后台删除
            this.$notify({
              title: '成功',
              message: response.data.message,
              type: 'success',
              duration: 2000
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      }
    }
  }
</script>
