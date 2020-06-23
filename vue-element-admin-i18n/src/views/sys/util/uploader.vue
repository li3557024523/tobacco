<template>
  <div id="global-uploader">

    <!-- 上传 -->
    <uploader
      ref="uploader"
      :options="options"
      :autoStart="false"
      @file-added="onFileAdded"
      @file-success="onFileSuccess"
      @file-progress="onFileProgress"
      @file-error="onFileError"
      class="uploader-app">
      <uploader-unsupport></uploader-unsupport>

      <uploader-btn id="global-uploader-btn" :attrs="attrs" ref="uploadBtn">选择文件</uploader-btn>

      <uploader-list v-show="panelShow">
        <div class="file-panel" slot-scope="props" :class="{'collapse': collapse}">
          <div class="file-title">
            <h2>文件列表</h2>
            <div class="operate">
              <el-button @click="fileListShow" type="text" :title="collapse ? '展开':'折叠' ">
                <i class="iconfont" :class="collapse ? 'icon-fullscreen': 'icon-minus-round'"></i>
              </el-button>
              <el-button @click="close" type="text" title="关闭">
                <i class="iconfont icon-close"></i>
              </el-button>
            </div>
          </div>

          <ul class="file-list">
            <li v-for="file in props.fileList" :key="file.id">
              <uploader-file :class="'file_' + file.id" ref="files" :file="file" :list="true"></uploader-file>
            </li>
            <div class="no-file" v-if="!props.fileList.length"><i class="nucfont inuc-empty-file"></i> 暂无待上传文件</div>
          </ul>
        </div>
      </uploader-list>

    </uploader>

  </div>
</template>


<script>
    export default {
        name: "uploader",
      data() {
        return {
          options: {
            target: 'http://xxxxx/xx', // 目标上传 URL
            chunkSize: '2048000',   //分块大小
            fileParameterName: 'file', //上传文件时文件的参数名，默认file
            maxChunkRetries: 3,  //最大自动失败重试上传次数
            testChunks: true,     //是否开启服务器分片校验
            // 服务器分片校验函数，秒传及断点续传基础
            checkChunkUploadedByResponse: function (chunk, message) {
              let objMessage = JSON.parse(message);
              if (objMessage.skipUpload) {
                return true;
              }

              return (objMessage.uploaded || []).indexOf(chunk.offset + 1) >= 0
            },
            headers: {
              // 在header中添加的验证，请根据实际业务来
              Authorization: "Bearer " + Ticket.get().access_token
            },
          },
          attrs: {
            // 接受的文件类型，形如['.png', '.jpg', '.jpeg', '.gif', '.bmp'...] 这里我封装了一下
            accept: ACCEPT_CONFIG.getAll()
          },
          panelShow: false,   //选择文件后，展示上传panel
        }
      },
      onFileAdded(file) {
        this.panelShow = true;

        // 计算MD5，下文会提到
        this.computeMD5(file);
      },
      onFileProgress(rootFile, file, chunk) {
        console.log(`上传中 ${file.name}，chunk：${chunk.startByte / 1024 / 1024} ~ ${chunk.endByte / 1024 / 1024}`)
      },
      onFileSuccess(rootFile, file, response, chunk) {
        let res = JSON.parse(response);

        // 服务器自定义的错误，这种错误是Uploader无法拦截的
        if (!res.result) {
          this.$message({ message: res.message, type: 'error' });
          return
        }

        // 如果服务端返回需要合并
        if (res.needMerge) {
          api.mergeSimpleUpload({
            tempName: res.tempName,
            fileName: file.name,
            ...this.params,
          }).then(data => {
            // 文件合并成功
            Bus.$emit('fileSuccess', data);
          }).catch(e => {});
          // 不需要合并
        } else {
          Bus.$emit('fileSuccess', res);
          console.log('上传成功');
        }
      },

      onFileError(rootFile, file, response, chunk) {
        console.log(error)
      },/* 计算md5，实现断点续传及秒传
    * @param file
    */
    /**
     * 计算md5，实现断点续传及秒传
     * @param file
     */
    computeMD5(file) {
      let fileReader = new FileReader();
      let time = new Date().getTime();
      let blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice;
      let currentChunk = 0;
      const chunkSize = 10 * 1024 * 1000;
      let chunks = Math.ceil(file.size / chunkSize);
      let spark = new SparkMD5.ArrayBuffer();

      // 文件状态设为"计算MD5"
      this.statusSet(file.id, 'md5');

      file.pause();

      loadNext();

      fileReader.onload = (e => {
        spark.append(e.target.result);
        if (currentChunk < chunks) {
          currentChunk++;
          loadNext();
          // 实时展示MD5的计算进度
          this.$nextTick(() => {
            $(`.myStatus_${file.id}`).text('校验MD5 '+ ((currentChunk/chunks)*100).toFixed(0)+'%')
          })
        } else {
          let md5 = spark.end();
          this.computeMD5Success(md5, file);
          console.log(`MD5计算完毕：${file.name} \nMD5：${md5} \n分片：${chunks} 大小:${file.size} 用时：${new Date().getTime() - time} ms`);
        }
      });
      fileReader.onerror = function () {
        this.error(`文件${file.name}读取出错，请检查该文件`)
        file.cancel();
      };
      function loadNext() {
        let start = currentChunk * chunkSize;
        let end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
        fileReader.readAsArrayBuffer(blobSlice.call(file.file, start, end));
      }
    },

    computeMD5Success(md5, file) {
      // 将自定义参数直接加载uploader实例的opts上
      Object.assign(this.uploader.opts, {
        query: {
          ...this.params,
        }
      })
      file.uniqueIdentifier = md5;
      file.resume();
      this.statusRemove(file.id);
    }
    ,
    }
</script>

<style scoped>

</style>
