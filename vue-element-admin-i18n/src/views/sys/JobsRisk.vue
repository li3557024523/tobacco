
<template>
  <div class="app-container">
    <div class="filter-container">
          <div style="width: 200px;" class="filter-item">
           <el-cascader
              :placeholder="placeholder"
              v-model="listQuery.doid"
              label="temp.deptName"
              :props="props"
              @change="Change"
              :show-all-levels="false"
              :options="options"
            ></el-cascader>
          </div>
          <div style="width: 200px;" class="filter-item">
           <el-select v-model="listQuery.poid" placeholder="请选择">
                <el-option v-for="l in findroleLists" :key="l.id" :label="l.name" :value="l.id"></el-option>
              </el-select>
          </div>
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getList"> <!--@  click="getListWhere"  -->
          查询
        </el-button>
        <!-- 表单
        <form id ="form" action="listwhere" methods ="post">
        </form>-->
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
      <el-table-column label="编号ID" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.id }}</span>
        </template>
      </el-table-column>


      <el-table-column label="岗位编号" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.riskId }}</span>
        </template>
      </el-table-column>


      <el-table-column label="岗位" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.mName }}</span>
        </template>
      </el-table-column>


      <el-table-column label="部门信息" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.dName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="执行年份" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="风险项目名称" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.project}}</span>
        </template>
      </el-table-column>

      <el-table-column label="风险等级" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.riskGrade }}</span>
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


        <el-form-item label="选择年份" prop="year">
          <el-date-picker v-model="temp.year" align="right" type="date" placeholder="选择日期" size="small" value-format="yyyy-MM-dd"/>
        </el-form-item>


      <el-form-item label="所属部门" prop="dName">
          <el-cascader
              :placeholder="placeholder"
              v-model="temp.dId"
              label="temp.deptName"
              :props="props"
              @change="Change"
              :show-all-levels="false"
              :options="options"
            ></el-cascader>
        </el-form-item>



      
        <el-form-item label="岗位" prop="findrole">
            <el-select v-model="temp.pId" placeholder="请选择">
              <el-option v-for="l in findroleLists" :key="l.id" :label="l.name" :value="l.id"></el-option>
            </el-select>
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



        <el-form-item label="L值" prop="pProbableLValue">
          <el-select v-model="temp.riskL" placeholder="请选择">
            <el-option
                 v-for="l in lValueList"
                :key="l.label"
                :label="l.label"
                :value="l.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="发现可能L值" prop="pProbableLValue">
          <el-input readonly="" placeholder="分1级,3级,6级,10级" v-model="temp.riskL" />
        </el-form-item>


        <el-form-item label="c值" prop="riskC">
          <el-select v-model="temp.riskC" placeholder="请选择">
            <el-option
                 v-for="c in cValueList"
                :key="c.label"
                :label="c.label"
                :value="c.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="发现可能C值" prop="pCValue">
          <el-input readonly="" placeholder="分7级,15级,40级,100级" v-model="temp.riskC" />
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
  import { add, deleteRisk, list } from '@/api/sys/risk'
  import { groupDept } from "@/api/sys/dept"
  import waves from '@/directive/waves' // waves directive
  import { parseTime } from '@/utils'
  import { findroleList } from "@/api/sys/user"
  import Pagination from '@/components/Pagination'
  export default {
    name: 'userTable',
    components: { Pagination },
    directives: { waves },
    
    data() {
      return {
        tableKey: 0,
        list: null, // 后台返回，给数据表格展示的数据
        listWhere: null,
        total: 0, // 总记录数
        listLoading: true, // 是否使用动画
        listQuery: {
          page: 1, // 分页需要的当前页
          limit: 10, // 分页需要的每页显示多少条
          poid: "",
          doid: ""
        },
        deptList: [], // 后台查询出来，分好组的部门信息
        findroleLists: [], //后台查询出来，岗位
        temp: { // 添加、修改时绑定的表单数据
          id: '',
          riskId: '',
          year: '',
          dId: '',
          pId: '',
          project: '',
          riskDescribe: '',
          riskL: '',
          riskC: '',
          riskD: '',
          riskGrade: '',
          riskGuard: '',
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
          username: [{ required: true, message: '用户名必填', trigger: 'blur' }]
        },
        options:[],
        placeholder: "请选择",
         props: {
        children: "deptCharlen",
        label: "deptName",
        value: "id",
        checkStrictly: true,
        emitPath:false
      },
      //L值数组
        lValueList: [
          {
            label: '可能性小',
            value: 1
          },
          {
            label: '可能性一般',
            value: 3
          },
          {
            label: '可能性较大',
            value: 6
          },
          {
            label: '完全可能',
            value: 10
          }
        ],
        //c值数组
        cValueList: [
          {
            label: '较小',
            value: 7
          },
          {
            label: '一般',
            value: 15
          },
          {
            label: '较大',
            value: 40
          },
          {
            label: '重大',
            value: 100
          }
        ],
      }
    },
    // 创建实例时的钩子函数
    created() {
      //得到显示数据
      this.getList()
      // 在创建时初始化获得部门信息
      this.getGroupDept()
      // 得到岗位
      this.findroleList()
    },

  methods: {

      // 获得分好组的部门信息
    getGroupDept(val = 0) {
      groupDept().then(response => {
        console.log("deptlist", response);
        this.options = [];
        response.data.dept.filter(item => {
          this.options.push(item);
        });
        console.log(this.options);
      });
    },
       Change(a){
      console.log(a)
    },
    //获得角色
    findroleList() {
      findroleList().then(response => {
        this.findroleLists = [];
        response.data.findroleList.filter(item => {
          this.findroleLists.push(item);
        });
      });
    },
    
      // 去后台取数据的
      getList() {
        // 重置表单数据
        this.resetTemp();
        // 开始转圈圈
        this.listLoading = true
        // debugger // 调试
        console.log(123456789)
        console.log(this.listQuery)

        list(this.listQuery).then(response => {

          console.log(response)
          
          this.list = response.data.items
          // 转圈圈结束
          this.listLoading = false
        })
      },
      // 调用岗位查询
      /*selectGet(vId){
      let obj = {};
      obj = this.selectList.find((item)=>{//这里的selectList就是上面遍历的数据源
          return item.id === vId;//筛选出匹配数据
      });
        console.log(obj.name);//我这边的name就是对应label的
        console.log(obj.id);
      },
      */
      // 重置表单数据
      resetTemp() {
        this.temp = {
          id: '',
          riskId: '',
          year: '',
          dId: '',
          pId: '',
          project: '',
          riskDescribe: '',
          riskL: '',
          riskC: '',
          riskD: '',
          riskGrade: '',
          riskGuard: '',
          createTime: '',
          createBy: '',
          createName: '',
          state: '',
        }
      },
      // 显示添加的对话框
      handleCreate () {
        // 重置表单数据
        this.resetTemp();
        // 点击确定时，是执行添加操作
        this.dialogStatus = 'create';
        this.title="添加岗位风险";
        // 显示对话框
        this.dialogFormVisible = true;
        this.$nextTick(() => {
          // 表单清除验证
          this.$refs["dataForm"].clearValidate();
        });
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
      /*// 显示修改对话框
      handleUpdate(row) {
        // 将row里面与temp里属性相同的值，进行copy
        this.temp = Object.assign({}, row) // copy obj
        // 将对话框里的确定点击时，改为执行修改操作
        this.dialogStatus = 'update'
        // 修改标题
        this.title = '修改用户'
        // 显示修改对话框
        this.dialogFormVisible = true
        this.$nextTick(() => {
          // 清除校验
          this.$refs['dataForm'].clearValidate()
        })
      },
      // 执行修改操作
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          // 表单校验通过
          if (valid) {
            // 将temp拷贝到tempData
            const tempData = Object.assign({}, this.temp)
            // 进行ajax提交
            update(tempData).then((response) => {
              // 提交完毕，关闭对话框
              this.dialogFormVisible = false
              // 刷新数据表格
              this.getList()
              // 显示通知
              this.$notify({
                title: '成功',
                message: response.data.message,
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },*/
      handleDelete(row) {
        // 先弹确认取消框
        this.$confirm('确认删除【'+row.project+'】的信息吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          // 调用ajax去后台删除
          deleteRisk(row.id).then((response) => {
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
