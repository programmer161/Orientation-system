<template>
  <div class="whole">
    <div class="part">
            <p id="title">基本信息</p>
            <!-- <el-button class="title1" v-show="show2" v-if="t" @click="Update" type="text" icon="el-icon-edit">信息有误</el-button>
            <el-button class="title2" v-show="show1" v-if="t" @click="cancle_editor" type="text" icon="el-icon-edit">取消修改</el-button> -->
            <span class="title1" v-show="show2" v-if="t" @click="Update"><i class="el-icon-edit" style="color:blue;"></i>信息有误</span>
            <span class="title2" v-show="show1" v-if="t" @click="cancle_editor">取消修改</span>
            <el-form ref="form" :model="stu" label-width="100px">
                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学号">
                            <el-input v-model="stu.userName" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学号">
                            <span v-text="stu.userName"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="姓名">
                            <el-input v-model="stu.name" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="姓名">
                            <span v-text="stu.name"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="性别">
                            <el-select v-model="stu.sex">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="性别">
                            <span v-text="stu.sex"></span>
                        </el-form-item>
                    </el-col>
                    
                </el-row>

                <el-row type="flex" justify="center">
                    
                    
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="民族">
                            <el-select v-model="stu.nation" class="Input">
                                <el-option v-for="item in nations" :key="item" :label="nations[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="民族">
                            <span v-text="stu.nation"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学院">
                            <el-select v-model="stu.college" class="Input" @change="handleChange">
                                <el-option v-for="item in colleges" :key="item" :label="colleges[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学院">
                            <span v-text="stu.college"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="专业">
                            <el-select v-model="stu.major" class="Input" @change="change_major(stu.major)">
                                <el-option v-for="item in majors" :key="item" :label="majors[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="专业">
                            <span v-text="stu.major"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    
                    
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="年级">
                            <el-select v-model="stu.grade" class="Input">
                                <el-option v-for="item in grades" :key="item" :label="grades[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="年级">
                            <span v-text="stu.grade"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="班级">
                            <el-select v-model="stu.classes" class="Input">
                                <el-option v-for="item in classes" :key="item" :label="classes[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="班级">
                            <span v-text="stu.classes"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="宿舍">
                            <div style="display:flex;background-color:white;width:182px">
                                <el-select class="sushe" v-model="stu.infocount" style="width:104px" @change="handleChange1">
                                  <el-option v-for="item in dromm" :key="item" :label="tungs[item]" :value="item" >
                                    </el-option>
                                </el-select>
                                <el-select class="dong" v-model="stu.reportnowcount" style="width:73px" @change="handleChange3">
                                    <el-option v-for="item in tungs" :key="item" :label="tungs[item]" :value="item">
                                    </el-option>
                                </el-select>
                            </div>

                        </el-form-item>
                        <el-form-item v-show="show2" label="宿舍">
                            <span v-text="stu.drom"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学制">
                            <el-select v-model="stu.xuezhi" class="Input">
                                <el-option label="3年" value="3年"></el-option>
                                <el-option label="4年" value="4年"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学制">
                            <span v-text="stu.xuezhi"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="应届生">
                            <el-select v-model="stu.studenttype" class="Input">
                                <el-option label="应届生" value="应届生"></el-option>
                                <el-option label="往届生" value="往届生"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="应届生">
                            <span v-text="stu.studenttype"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="身份证号">
                            <el-input v-model="stu.idnumber" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="身份证号">
                            <span v-text="stu.idnumber"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="出生日期">
                            <el-date-picker
                              v-model="stu.birth"
                              type="date"
                              placeholder="选择日期"
                              value-format="yyyy-MM-dd" class="Input">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item v-show="show2" label="出生日期">
                            <span v-text="stu.birth"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学习形式">
                            <el-select v-model="stu.studytype" class="Input">
                                <el-option v-for="item in studys" :key="item" :label="studys[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学习形式">
                            <span v-text="stu.studytype"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="政治面貌">
                            <el-select v-model="stu.politics" class="Input">
                                <el-option v-for="item in politicss" :key="item" :label="politicss[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="政治面貌">
                            <span v-text="stu.politics"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学籍状态">
                            <el-select v-model="stu.studystate" class="Input">
                                <el-option v-for="item in statuss" :key="item" :label="statuss[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学籍状态">
                            <span v-text="stu.studystate"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="辅导员姓名">
                            <el-input v-model="stu.instructname" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="辅导员姓名">
                            <span v-text="stu.instructname"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="辅导员电话">
                            <el-input v-model="stu.instructphone" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="辅导员电话">
                            <span v-text="stu.instructphone"></span>
                        </el-form-item>
                    </el-col>
                    
                </el-row>
            </el-form>
        </div>
    <el-button type="primary" class="Button" v-show="show1" v-if="t" @click="Confirm"
      >更新</el-button
    >
    <el-button
      type="primary"
      class="Button"
      v-show="show2" v-if="t"
      @click="Confirm_other"
      >确认无误</el-button
    >
    <br />
  </div>
</template>

<script>
export default {
  data() {
    return {
      stu: this.$store.state.stuInfro,
      nations: ["汉族", "藏族", "维吾尔族", "苗族", "回族"],
      grades: [2017, 2018, 2019, 2020],
      classes: ["1班", "2班", "3班", "4班", "5班"],
      tungs: [],
      studys: ["全日制招生", "成人高考", "远程教育", "自考", "国家开发银行"],
      politicss: ["共青团员", "群众", "党员"],
      statuss: ["注册学籍", "保留学籍", "已离校"],
      colleges: [],
      majors: [],
      dromm:[],
      show2: true,
      t:this.$store.state.stuInfro.info!="已完成",
      show1: false,
    };
  },
  mounted() {
    this.show_dromm();
    this.show_colleges();
    console.log(this.$store.state.stuInfro);
    this.stu.infocount=this.stu.drom.substring(0,4);
    this.stu.reportcount=this.stu.drom.substring(4,);
  },
  methods: {
    change_major(val) {
                let param = new URLSearchParams();
                param.append('major', val);
                this.axios.post(this.$apiUrl +'/college/getclassesbymajor', param, {
                    headers: {
                        'Authorization': localStorage.getItem('token'+this.$store.state.id)
                    }
                }).then(res => {
                    if (res.data.code != 0) {
                        this.$message({
                            type: 'error',
                            message: '班级信息获取失败'
                        })
                    } else {
                        this.classes = [];
                        for (let i = 0; i < res.data.data.length; i++) {
                            this.classes.push(res.data.data[i].classes);
                        }
                    }
                }).catch(err => {
                    console.log(err);
                })
            },
    formatStartTime(val) {
      this.stu.birth = val;
  },
    Confirm_other() {
      var index=0;
      var i=0;
      for(i=0;i<this.$store.state.list.length;i++){
          var t=this.$store.state.list[i].frontendMenuId;
          // console.log(t)
          if(t==10){
              index=1;
          }
      }
      if(index==1){
        this.$confirm("确定信息无误?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.stu.info="已完成";
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
              this.t=false;
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
          this.$message(
            {
              type: "info",
              message: "已取消确认"
            },
          );
        });
  }else{
          this.$message.error({
              message: "无该权限！",
              duration:1500
          });
      }
    },
    show_colleges() {
      this.axios.get(this.$apiUrl +"/college/getcollege ",{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res => {
          console.log(res.data)
          if (res.data.code != 0) {
            this.$message({
              type: "error",
              message: "获取学院信息失败"
            });
          } else {
            this.colleges=[];
            var i=0;
            for(i=0;i<res.data.data.length;i++){
              this.colleges.push(res.data.data[i].college);
            }
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    show_dromm() {
      this.axios.get(this.$apiUrl +"/dorm/getapartments",{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res => {
          console.log(res.data)
          if (res.data.code != 0) {
            this.$message({
              type: "error",
              message: "获取宿舍信息失败"
            });
          } else {
            this.dromm = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleChange(val) {
      let param = new URLSearchParams()
      param.append("college",val);
      this.axios.post(this.$apiUrl +"/college/getmajorbycollege",param,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res => {
          console.log(res.data)
          if (res.data.code != 0) {
            this.$message({
              type: "error",
              message: "获取学院信息失败"
            });
          } else {
            this.majors=[];
            var i=0;
            for(i=0;i<res.data.data.length;i++){
              this.majors.push(res.data.data[i].major);
            }
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleChange1(val) {
      let param = new URLSearchParams()
      param.append("apartments",val);
      this.axios.post(this.$apiUrl +"/dorm/getdorm",param,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res => {
          console.log(res.data)
          if (res.data.code != 0) {
            this.$message({
              type: "error",
              message: "获取学院信息失败"
            });
          } else {
            this.stu.drom=val;
            this.tungs = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleChange3(val) {
      this.stu.drom=this.stu.drom+val;
      console.log(this.stu.drom)
    },
    Confirm() {
      this.$confirm("确定更新信息?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
      this.axios.post(this.$apiUrl +"/users/update", this.stu,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        })
        .then(res => {
          console.log(res.data);
          if (res.data.code != 0) {
            this.$message({
              type: "error",
              message: "更新失败"
            });
          } else {
            this.$store.commit('updateInfo',this.stu);
            this.show1 = false;
            this.show2 = true;
            this.$message({
              type: "success",
              message: "信息更新成功"
            });
          }
        })
        .catch(err => {
          console.log(err);
        });
      }).catch(() => {
        this.$message(
          {
            type: "info",
            message: "已取消更新"
          },
        );
      });
    },
    Update() {
      var index=0;
      var i=0;
      for(i=0;i<this.$store.state.list.length;i++){
          var t=this.$store.state.list[i].frontendMenuId;
          // console.log(t)
          if(t==11){
              index=1;
          }
      }
      if(index==1){
        this.show1 = true;
        this.show2 = false;
        this.stu.infocount=this.stu.drom.substring(0,4);
        this.stu.reportcount=this.stu.drom.substring(4,);
      }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
    },
    cancle_editor() {
        this.$confirm("确定未保存该修改信息后退出？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
          this.show1 = false;
          this.show2 = true;
          let param = new URLSearchParams()
            param.append("username",this.$store.state.id);
            console.log(this.$store.state.id)
            this.axios.post(this.$apiUrl +"/users/findstudentbyusername",param,
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
                    this.$store.commit('updateInfo',res.data.data);
                    this.stu=res.data.data;
                }
            }).catch(err=>{
                console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消退出编辑模式"
          });
        });
    }
  }
};
</script>
<style lang="less" scoped>
@import "../../../less/layout.css";
  .whole{
    margin-left: -10px;
    margin-top: -10px;
    width: 920px;
  }
    .Button {
        position: relative;
        margin-left: 40%;
        font-size: 25px;
        margin-bottom: 10px;
    }

    .Button1 {
        position: relative;
        margin-left: 40%;
        font-size: 25px;
        margin-bottom: 10px;
    }

    .back {
        position: relative;
        margin-left: 25%;
        top: 35px;
        color: blue;
        cursor: pointer;
    }

    .part {
        background-color: transparent;
        // width: 1000px;
        position: relative;
        // padding-left: 10px;
    }

    /deep/ .el-button {
        height: 30px;
        font-size: 15px;
        line-height: 8px;
        margin-top: 20px;
        margin-left: 900px;

    }

    .el-input {
        background-color: transparent;
        width: 100%;
    }

    span,
    .el-input,
    .el-select {
        display: inline-block;
        height: 40px;
        width: 180px;
        border: 1px solid gainsboro;
        background-color: white;
        margin-top: -1px;
    }

    span {
        padding-left: 12px;
    }


    /deep/ .el-row {
        padding-right: 2%;
        height: 1%;
        margin-right: 0%;
    }

    /deep/ .el-form-item {
        height: 40px;
        width: 120px;
        background-color: #E5FFF2;
        border: 1px solid gainsboro;
        line-height: 40px;
        margin-bottom: 0px;
    }
    .el-form{
      margin-left: 20px;
      margin-top: 30px;
    }
    .el-form-item /deep/ .el-form-item__label {
        padding-left: 10px;
        color: black;
        text-align: left;
    }

    .title1,
    .title2 {
        background-color: transparent;
        border: none;
        margin-top: 9px;
        margin-left: 25px;
        position: absolute;
    }

    .title1,
    .title2 {
        width: 100px;
        color: blue;
        cursor: pointer;
        text-decoration: underline;
    }
/deep/ .el-button {
  height: 30px;
  font-size: 15px;
  line-height: 8px;
  margin-top: 20px;
  margin-left: 800px;
  margin-bottom: 1%;
  background-color: #409eff;
  opacity:  0.8;
}

#title{
  height: 50px;
  line-height: 50px;
  color:white;
  text-align: center;
  background-color: #276E51;
  opacity: 0.8;
  margin-top: -1px;
  margin-left: -1px;
  font-size: 20px;
}

  /deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>