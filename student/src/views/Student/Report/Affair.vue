<template>
  <div class="box process">
    <p id="title">需要完成的事务</p>
    <div class="process-box">
      <div class="process-row">
        <div class="process-item">宿舍的登记入住</div>
        <div class="process-arow"></div>
        <div class="process-item">
          一卡通的领取
        </div>
        <div class="process-arow"></div>
        <div class="process-item">学生证的领取</div>
      </div>
    </div>
    <div class="process-box" style="height: 40px">
      <div class="process-arrow-down"></div>
    </div>
    <div class="process-box">
      <div class="process-row">
        <div class="process-item">上交身份证复印件</div>
        <div class="process-arow-re"></div>
        <div class="process-item">完成学费的支付</div>
        <div class="process-arow-re"></div>
        <div class="process-item">完成必要事务的办理</div>
      </div>
    </div>
    <div class="affair-btns">
      <el-button type="primary" class="con-btn" @click="con"
        v-if="flag">确认完成</el-button
      >
    </div>
  </div>
</template>
<script>
// import bus from "../../components/eventbus/eventBus.js";
export default {
  data() {
    return {
      flag:this.$store.state.stuInfro.affair!="已完成",
      stu:this.$store.state.stuInfro,
    };
  },
  methods: {
    con() {
      var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==16){
                    index=1;
                }
            }
            if(index==1){
      this.$confirm('确认完成?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: "warning"
      }).then(() => {
        this.stu.affair="已完成";
        this.axios.post(this.$apiUrl +"/users/update", this.stu,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        })
        .then(res => {
          console.log(res.data);
          if (res.data.code != 0) {
              this.$message({
              type: "error",
              message: "确认失败"
              });
          } else {
            this.$store.commit('updateInfo',this.stu);
            this.flag=0;
            this.$message({
                type: "success",
                message: "确认成功"
            });
          }
        })
        .catch(err => {
            console.log(err);
        });
      }).catch(() => {
        this.$message({
            type: 'info',
            message: '已取消确认'
        });          
      }); 
      }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
    }
  }
};
</script>

<style lang='scss' scoped>
#title{
  background-color: #276E51;
  opacity: 0.8;
  color: white;
  height: 50px;
  line-height: 50px;
  text-align: center;
  // border: 1px solid  #409eff;
  margin-top: -1px;
  margin-left: -1px;
  font-size: 17px;
}
.box {
  width: 920px;
  margin-left: -10px;
  // margin-right: auto;
  // padding: 10px;
  border: 1px solid gainsboro;
  background-color: #f4f8f6;
  margin-bottom: 40px;
  color: black;
  margin-top: -10px;
}
.con-btn {
  height: 35px;
  width: 100px;
  margin-left: 690px;
  margin-top: 30px;
  text-align: center;
  //padding-bottom: 20px;
  background-color:  #409eff;
  opacity: 0.8;
}
// .title {
//   color: #409eff;
// }
.process {
  padding-bottom: 40px;
  .process-box {
    width: 100%;
    display: flex;
    align-items: center;
    flex-direction: column;
    margin-top: 30px;
    .process-row {
      position: relative;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      .process-item {
        display: inline-block;
        font-size: 14px;
        width: 130px;
        //height: 40;
        max-height: 40px;
        line-height: 38px;
        text-align: center;
        background-color: #e5fff2;
        border: 1px solid gainsboro;
        border-radius: 5px;
      }
      .process-arow,
      .process-arow-re {
        display: inline-block;
        position: relative;
        left: 0;
        top: 0;
        width: 126px;
        height: 0;
        border-bottom: 4px solid rgba(0, 0, 0, 0.6);
      }
    }
  }
}


</style>