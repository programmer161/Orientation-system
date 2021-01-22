<template>
  <div class="whole">
    <div id="title">
      个人资料
    </div>
    <div class="row">
      <div class="col-1">
        <label class>学号:</label>
      </div>
      <div class="col-2">
        <el-input v-model="input1" placeholder="请输入学号"></el-input>
      </div>
    </div>
    <div class="row">
      <div class="col-1">
        <label class>姓名:</label>
      </div>
      <div class="col-2">
        <el-input v-model="input4" placeholder="请输入姓名"></el-input>
      </div>
    </div>
    
    <div class="row">
      <div class="col-1">
        <label class>性别:</label>
      </div>
      <div class="col-2 t">
        <el-radio v-model="radio" label="女">女</el-radio>
        <el-radio v-model="radio" label="男">男</el-radio>
      </div>
    </div>
    
    <div class="row">
      <div class="col-1">
        <label class>生日:</label>
      </div>
      <div class="col-2">
        <el-date-picker
        v-model="input3"
        type="date"
        value-format="yyyy-MM-dd"
        @change="formatStartTime"
        placeholder="选择日期">
      </el-date-picker>
      </div>
    </div>
    <div class="row">
      <div class="col-1">
        <label class>绑定邮箱:</label>
      </div>
      <div class="col-2">
        <!-- <input placeholder="input email" /> -->
        <el-input v-model="input2" placeholder="请输入邮箱"></el-input>
      </div>
    </div>
    <el-button type="primary" class="Button" @click="Confirm_other"
      >确认编辑</el-button
    >
    <div class="text">* 我们确保你所提供的信息均处于严格保密状态，不会泄露</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: "",
      input2: "",
      input3: "",
      input4: "",
      imgUrl:"",
      stu:{

      },
      radio: "man"
    };
  },
  methods: {
     formatStartTime(val) {
      this.input3 = val;
  },
    Confirm_other() {
      console.log(this.input3)
      this.$confirm("确认修改?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$store.commit('updateSelf',{username:this.input1,name:this.input4,radio:this.radio,birth:this.input3,email:this.input2});
          var obj=this.$store.state.stuInfro;
          this.axios.post(this.$apiUrl +"/users/update",obj,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
            console.log(res.data)
          }).catch(err=>{
            console.log(err);
        });
          this.$message(
            {
              type: "success",
              message: "编辑成功"
            },
          );
        })
        .catch(() => {
          this.$message(
            {
              type: "info",
              message: "已取消"
            },
            console.log("cuo")
          );
        });
    },
  },
  mounted(){
    
    this.input1=this.$store.state.stuInfro.userName;
    this.input4=this.$store.state.stuInfro.name;
    this.radio=this.$store.state.stuInfro.sex;
    this.input3=this.$store.state.stuInfro.birth;
    this.input2=this.$store.state.stuInfro.email;
    console.log(this.$store.state.stuInfro)
  }
};
</script>
<style lang="less" scoped>

// @import "../../../less/layout.css";
.whole {
  // background-color: #E5FFF2;
  // padding-left: 10px;
  background-color: #f4f8f6;
  border: 0.2px solid gainsboro;
  width: 920px;
  margin-top: 20px;
  padding-bottom: 20px;
  // padding-right: 100px;
  // margin-left: 250px;
}
#title {
  // width: 100%;
  height: 60px;
  line-height: 60px;
  font-size: 20px;
  margin-left: auto;
  margin-right: auto;
  border-bottom: 1px solid gray;;
  text-align: center;
  margin-bottom: 20px;
}

.text {
  color: #b5bbc4;
  font-size: 12px;
  padding-left: 10px;
  margin-top: 20px;
}
.row {
  display: flex;
  flex-wrap: wrap;
  width: 400px;
  margin-left: 190px;
}
.col-1 {
  flex: 0 0 16.666667%;
  max-width: 16.666667%;
  position: relative;
  width: 100%;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
  float: left;
  margin-bottom: 30px;
  padding-left: 80px;
  font-size: 14px;
  color: #606266;
  margin-top: 10px;
  text-align: right;
}
.col-2 {
  // margin-left: 120px;
  margin-top: 2px;
}
.Button {
  margin-left: 400px;
  margin-top: 5px;
  height: 34px;
  background-color: #409eff;
  opacity:  0.9;
  line-height: 8px;
}
.t{
  margin-top: 9px;
  margin-left: 30px;
  .el-radio{
    margin-left: 20px;
  }
}
.el-input{
  height: 30px;
  width: 230px;
  outline: none;
}

/deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>