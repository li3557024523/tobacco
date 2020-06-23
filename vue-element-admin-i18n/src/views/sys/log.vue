<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.operation"
        placeholder="请求方式"
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
      <el-table-column label="ID"  sortable="custom" align="center" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column label="用户信息" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column label="访问路径" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.url }}</span>
        </template>
      </el-table-column>

      <el-table-column label="请求方式" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.operation }}</span>
        </template>
      </el-table-column>

         <el-table-column label="请求ip" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.ip }}</span>
        </template>
      </el-table-column>

         <el-table-column label="访问浏览器" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.browser }}</span>
        </template>
      </el-table-column>

         <el-table-column label="访问时间" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.date }}</span>
        </template>
      </el-table-column>

      <!--     自定义列-->
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
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

  </div>
</template>

<script>
//
import { list, deletelog } from "@/api/sys/log";
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
      temp: {
        // 添加、修改时绑定的表单数据
        id: undefined,
        name: "",
        password: "",
        email: "",
        mobile: "",
        deptId: "",
        introduction: "",
        
      },
      dialogFormVisible: false, // 是否显示对话框
      dialogStatus: "", // 表示表单是添加还是修改的
      rules: {
        // 校验规则
        name: [{ required: true, message: "用户名必填", trigger: "blur" }]
      },
    };
  },
  // 创建实例时的钩子函数
  created() {
    this.getList();
  },
  methods: {
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
    handleDelete(row) {
      // 先弹确认取消框
      this.$confirm("确认删除【" + row.id + "】的信息吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 调用ajax去后台删除
          deletelog(row.id).then(response => {
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

  
  }
};
</script>