<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        placeholder="用户名"
        style="width: 200px;"
        class="filter-item"
      />
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="getList"
      >查询</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >添加</el-button>
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
      <el-table-column label="部门名称" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.deptName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="负责人姓名" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.principalName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="分管领导" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.leadership }}</span>
        </template>
      </el-table-column>

      <el-table-column label="父部门编号" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.parentId }}</span>
        </template>
      </el-table-column>

      <el-table-column label="员工状态" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.state }}</span>
        </template>
      </el-table-column>

      <!--     自定义列-->
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页工具条  page当前页 total总记录数 limit每页显示多少条 pagination触发自定义事件，查询数据-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <!--  绑定了title，是一个数组里取的，表示是修改的标题还是添加的标题
      visible.sync 对话框是否显示
    -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" style="width: 80%">
      <!--
          rules:校验规则
          model:数据绑定
      -->
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 80%; margin-left:50px;"
      >
        <el-form-item label="部门编号" prop="id">
          <el-input :disabled="true" v-model="temp.id" />
        </el-form-item>

        <el-form-item label="负责人" prop="principalName">
          <el-input placeholder="请输入负责人名称" v-model="temp.principalName" />
        </el-form-item>

        <el-form-item label="部门名称" prop="deptName">
          <el-input placeholder="请输入部门名称" v-model="temp.deptName" />
        </el-form-item>

        <el-form-item label="所属部门" prop="deptName">
          <div class="block">
            <el-cascader
              :placeholder="placeholder"
              v-model="temp.parentId"
              label="temp.deptName"
              :props="props"
              @change="Change"
              :show-all-levels="false"
              :options="options"
            ></el-cascader>
          </div>
        </el-form-item>

        <el-form-item label="部门状态" prop="state">
          <el-select v-model="temp.state" placeholder="请选择">
            <el-option
              v-for="item in sta"
              :key="item.state"
              :label="item.label"
              :value="item.state"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="分管领导" prop="leadership">
          <el-select v-model="temp.leadership" placeholder="请选择">
            <el-option
              v-for="l in leaderships"
              :key="l.leadership"
              :label="l.leadership"
              :value="l.leadership"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <!--
          dialogStatus==='create'?createData():updateData()
          dialogStatus需要我们根据情况去改变
        -->
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//
import { add, update, list, deleteEmp } from "@/api/sys/dept";
import { groupDept } from "@/api/sys/dept";
import { leadership } from "@/api/sys/dept";
import request from "@/utils/request";
import waves from "@/directive/waves"; // waves directive
import { parseTime } from "@/utils";
import Pagination from "@/components/Pagination"; // 分页组件
export default {
  name: "userTable",
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null, // 后台返回，给数据表格展示的数据
      total: 0, // 总记录数
      listLoading: true, // 是否使用动画
      listQuery: {
        page: 1, // 分页需要的当前页
        limit: 10, // 分页需要的每页显示多少条
        sex: 1,
        name: ""
      },
      deptList: [], // 后台查询出来，分好组的部门信息
      temp: {
        // 添加、修改时绑定的表单数据
        id: undefined,
        name: "",
        password: "",
        email: "",
        mobile: "",
        deptId: "",
        introduction: ""
      },
      title: "添加", // 对话框显示的提示 根据dialogStatus create
      dialogFormVisible: false, // 是否显示对话框
      dialogStatus: "", // 表示表单是添加还是修改的
      rules: {
        // 校验规则
        name: [{ required: true, message: "用户名必填", trigger: "blur" }]
      },

      placeholder: "请选择",
      options: [],
      sta: [
        {
          state: "启动",
          label: "启动"
        },
        {
          state: "关闭",
          label: "关闭"
        }
      ],
      leaderships: [],
     // state: "",
      props: {
        children: "deptCharlen",
        label: "deptName",
        value: "did",
        checkStrictly: true,
        emitPath:false
      }
    };
  },
  // 创建实例时的钩子函数
  created() {
    this.getList();
    // 在创建时初始化获得部门信息
    this.getGroupDept();
    this.leadership();
  },
  methods: {
    //获得分管领导
    leadership() {
      leadership().then(response => {
        this.leaderships = [];
        console.log(response);
        response.data.leadership.filter(item => {
          this.leaderships.push(item);
        });
        console.log(this.leadership)
      });
    },
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
    Change(a) {
      console.log(a);
    },

    // 去后台取数据的
    getList() {
      // 开始转圈圈
      this.listLoading = true;
      // debugger // 调试
      list(this.listQuery).then(response => {
        this.list = response.data.items;
        this.total = response.data.total;
        // 转圈圈结束
        this.listLoading = false;
      });
    },
    // 重置表单数据
    resetTemp() {
      this.temp = {
        id: undefined,
        name: "",
        password: "",
        email: "",
        mobile: "",
        deptId: "",
        introduction: ""
      };
    },
    // 显示添加的对话框
    handleCreate() {
      // 重置表单数据
      this.resetTemp();
      // 点击确定时，是执行添加操作
      this.dialogStatus = "create";
      this.title = "添加部门";
      // 显示对话框
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        // 表单清除验证
        this.$refs["dataForm"].clearValidate();
      });
    },
    // 添加对话框里，点击确定，执行添加操作
    /*
    createData() {
      // 表单校验
      this.$refs["dataForm"].validate(valid => {
        // 所有的校验都通过
        if (valid) {
          // 调用api里的sys里的user.js的ajax方法
          add(this.temp).then(response => {
            console.log(this.temp)
            // 关闭对话框
            this.dialogFormVisible = false;
            // 刷新数据表格里的数据
            this.getList();
            // 显示一个通知
            this.$notify({
              title: "成功",
              message: response.data.message,
              type: "success",
              duration: 2000
            });
          });
        }
      });
    },
    */
    // 显示修改对话框
    handleUpdate(row) {
      // 将row里面与temp里属性相同的值，进行copy
      this.temp = Object.assign({}, row); // copy obj
      // 将对话框里的确定点击时，改为执行修改操作
      this.dialogStatus = "update";
      // 修改标题
      this.title = "修改部门";
      // 显示修改对话框
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        // 清除校验
        this.$refs["dataForm"].clearValidate();
      });
    },
    // 执行修改操作
    updateData() {
      this.$refs["dataForm"].validate(valid => {
        // 表单校验通过
        if (valid) {
          // 将temp拷贝到tempData
          const tempData = Object.assign({}, this.temp);
          console.log(tempData)
          // 进行ajax提交
          update(tempData).then(response => {
            // 提交完毕，关闭对话框
            this.dialogFormVisible = false;
            // 刷新数据表格
            this.getList();
            // 显示通知
            this.$notify({
              title: "成功",
              message: response.data.message,
              type: "success",
              duration: 2000
            });
          });
          // update(tempData).then(response => {
          //   // 提交完毕，关闭对话框
          //   this.dialogFormVisible = false;
          //   // 刷新数据表格
          //   this.getList();
          //   // 显示通知
          //   this.$notify({
          //     title: "成功",
          //     message: response.data.message,
          //     type: "success",
          //     duration: 2000
          //   });
          // });
        }
      });
    },
    handleDelete(row) {
      // 先弹确认取消框
      this.$confirm("确认删除【" + row.deptName + "】的信息吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 调用ajax去后台删除
          deleteEmp(row.id).then(response => {
            // 刷新数据表格
            this.getList();
            // ajax去后台删除
            this.$notify({
              title: "成功",
              message: response.data.message,
              type: "success",
              duration: 2000
            });
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    // 从后台读取数据的js方法
    showDeptList() {
      groupDept().then(response => {
        this.deptList = response.data.deptList;
      });
    }
  }
};
</script>