<template>
  <div id="log">
    <div id="title">修改密码</div>
    <label for="ppaw">原密码：</label>
    <el-input v-model="ppassword" @blur="getPpawErr" placeholder="初始密码为身份证后6位" show-password></el-input>
    <span v-text="ppawErr"></span>
    <br />
    <label for="paw">密码：</label>
    <el-input v-model="password" @blur="getPawErr" placeholder="密码必须6位以上" show-password></el-input>
    <span v-text="pawErr"></span>
    <br />
    <label for="repaw">确认密码：</label>
    <el-input v-model="repassword" @blur="getRepawErr" placeholder="必须与密码一致" show-password></el-input>
    <span v-text="repawErr"></span>
    <br />
    <el-button @click="submit" id="btn">确认</el-button>
    <br />
  </div>
</template>
<script>
export default {
  data() {
    return {
      // url: "http://127.0.0.1:8080",
      ppassword: "",
      password: "",
      repassword: "",
      pawFlag: 0,
      repawFlag: 0,
      ppawFlag: 0,
      ppawErr: "",
      pawErr: "",
      repawErr: ""
    };
  },
  methods: {
    toreturn() {
      this.$router.push(this.$store.state.url);
    },
    getPpawErr() {
      this.ppawFlag = 0;
      var ppawReg = /^.{6,}$/g;
      if (this.ppassword == "") {
        this.ppawErr = "不能为空";
      } else if (!ppawReg.test(this.ppassword)) {
        this.ppawErr = "不能少于六位";
      } else {
        this.ppawErr = "";
        this.ppawFlag = 1;
      }
    },
    getPawErr() {
      this.pawFlag = 0;
      var pawReg = /.{6,}/g;
      if (this.password == "") {
        this.pawErr = "不能为空";
      } else if (!pawReg.test(this.password)) {
        this.pawErr = "格式错误";
      } else {
        this.pawErr = "";
        this.pawFlag = 1;
      }
    },
    getRepawErr() {
      this.repawFlag = 0;
      var repawReg = /.{6,}/g;
      if (this.repassword == "") {
        this.repawErr = "不能为空";
      } else if (!repawReg.test(this.repassword)) {
        this.repawErr = "格式错误";
      } else if (this.repassword != this.password) {
        this.repawErr = "密码不一致";
      } else {
        this.repawErr = "";
        this.repawFlag = 1;
      }
    },
    submit() {
      console.log(1);
      if (this.ppawFlag === 1 && this.pawFlag === 1 && this.repawFlag === 1) {
        this.$confirm("确认修改?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let param = new URLSearchParams()
          param.append("username",this.$store.state.id);
          param.append("password",this.ppassword);
          param.append("firstPassword",this.password);
          param.append("secondPassword",this.repassword);
          // console.log(this.Id)
          this.axios.post(this.$apiUrl +"/users/updatepwd",param,
              {
          headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
      }
          ).then(res=>{
              console.log(res.data)
              if(res.data.code!=0){
                  this.$message.error({
                      message: res.data.msg,
                      duration:1500
                  });
              }else{
                  // console.log(res.data.data.college)
                  this.$message({
                      message: res.data.data,
                      type: 'success',
                      duration:1500
                  });
              }
          }).catch(err=>{
              console.log(err);
          });
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
        
      } else {
        if (this.ppassword == "") {
          this.ppawErr = "不能为空";
        }
        if (this.password == "") {
          this.pawErr = "不能为空";
        }
        if (this.repassword == "") {
          this.repawErr = "不能为空";
        }
      }
    }
  },
  created() {
    // console.log(this.$store.state.list[0].code)
    
  }
};
</script>
<style lang="scss" scoped>
#title{
  height: 60px;
  line-height: 60px;
  font-size: 20px;
  text-align: center;
  border-bottom: 1px solid gray;
}
#log {
  width: 920px;
  // min-width: 400px;
  height: 330px;
  min-height: 250px;
  background-color: #f4f8f6;
  // margin-left: auto;
  margin-top: 20px;
  // padding-top: 30px;
  border: 1px solid gainsboro;
}
input::placeholder {
  font-size: 5px;
}
label {
  color: #606266;
  display: inline-block;
  margin-top: 40px;
  margin-left: 20%;
  min-width: 20%;
  text-align: right;
  font-size: 14px;
}

button {
  font-size: 15px;
}

#btn {
  height: 30px;
  width: 100px;
  margin-left: 45%;
  margin-top: 30px;
  line-height: 8px;
  background-color: #409eff;
  opacity:  0.9;
  color: white;
}

span {
  font-size: 3px;
  color: red;
}

.el-input{
  height: 30px;
  width: 25%;
  outline: none;
}

a {
  margin-left: 350px;
  cursor: pointer;
  color: #169bd5;
  font-size: 13px;
}
</style>