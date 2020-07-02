<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.timestamp" placement="top">
        <el-card v-if="size!=null">

          <h4>亲爱的管理员</h4>

          <p>您还剩余{{size}}条报告未处理,请尽快处理</p>
        </el-card>
        <el-card v-if="size==null">

          <h4>亲爱的管理员:</h4>

          <p>您今天尚无任务安排</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
  import { asd } from '@/api/article'

  export default {

  data() {

    return {
      size:null,
      timeline: [
        {
          timestamp: new Date(),
        },


      ]

    }
  },
    created() {
    this.made();
    },
    methods :{
    made(){
      asd().then(Response => {
        this.size=Response.data.size
        console.log("卧槽"+this.size)

        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    }
    }
}
</script>
