<template>
    <div>
      <el-container>
            <el-container>
                <el-aside width="200px">
                    <el-menu  class="el-menu-vertical-demo" 
                        @open="handleOpen"
                        @close="handleClose"
                         text-color="#fff" active-text-color="#ffd04b">
                         <el-menu-item v-for="(data,index) in list" :key="index"  @click="toinfo(data.frontendMenuUrl)" :class="(data.frontendMenuId==9||data.frontendMenuId==12 ||data.frontendMenuId==14 ||data.frontendMenuId==15 ||data.frontendMenuId==17)?'el':'None'">
                            <span slot="title" v-text="data.frontendMenuName"></span>
                         </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <router-view />
                    </el-main>
                </el-container>
                <el-aside width="120px">
                    <div id="info">
                        <div>信息确认<i class="el-icon-success" :class="this.$store.state.stuInfro.info=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.info!='已完成'?'red':'None'"></i></div>
                        <div>报道信息<i class="el-icon-success" :class="this.$store.state.stuInfro.reportinfo=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.reportinfo!='已完成'?'red':'None'"></i></div>
                        <div>现场报道<i class="el-icon-success" :class="this.$store.state.stuInfro.reportnow=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.reportnow!='已完成'?'red':'None'"></i></div>
                        <div>事务办理<i class="el-icon-success" :class="this.$store.state.stuInfro.affair=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.affair!='已完成'?'red':'None'"></i></div>
                        <div>学费支付<i class="el-icon-success" :class="this.$store.state.stuInfro.pay=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.pay!='已完成'?'red':'None'"></i></div>
                    </div>
                </el-aside>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    data(){
        return{
            // ulFlag:0,
            // List:[],
            // Id:""
            // [require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg")],
            list:this.$store.state.list,
        }
    },
    methods:{
        toinfo(data){
            this.$router.push(data.replaceAll('"',''));
        },
        
        handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    mounted(){
        let param = new URLSearchParams()
            param.append("username",this.$store.state.id);
            // console.log(this.Id)
            this.axios.post(this.$apiUrl +"/users/findstudentbyusername",param,
                {
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }
            ).then(res=>{
                console.log(res.data)
                if(res.data.code!=0){
                    // this.$message.error({
                    //     message: res.data.msg,
                    //     duration:1500
                    // });
                }else{
                    console.log(res.data.data)
                    this.$store.commit('updateInfo',res.data.data);
                }
            }).catch(err=>{
                console.log(err);
            });
    }
}
</script>

<style lang="scss" scoped>
.None{
    display: none;
}
.green{
        position: absolute;
        color: rgb(6, 189, 6);
        font-size: 30px;
        // line-height: 10px;
        margin-left: 10px;
        margin-top: 10px;
        // line-height: 50px;
    }
    .red{
        position: absolute;
        color: red;
        font-size: 30px;
        margin-top: 10px;
        margin-left: 10px;
    }
    
    .None{
        display: none;
    }
.el-header,
    .el-footer {
        // background-color:#D3DCE6;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside:first-child {
        text-align: center;
        line-height: 200px;
        margin-top: -20px;
    }
    .el{
        background-color: #276E51;
        margin-bottom: 10px;
        margin-left: 20px;
    }
    .el-menu-item-group{
        background-color: #276E51;
    }

    .el-menu-vertical-demo {
        height: 400px;
        margin-top: 30px;
    }
    #info{
        div{
            // display: inline-block;
            // font-size: 10px;
            width: 100px;
            height: 50px;
            line-height: 50px;
            // border: 1px solid black;
            margin-top: 15px;
        }
    }
    
</style>
<style lang="less">
.el-menu /deep/.el-menu-item:hover{
  background-color: #1D9558 !important;
}
</style>