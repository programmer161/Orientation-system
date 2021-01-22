<template>
    <div>
      <el-container>
                <el-aside width="200px">
                    <el-menu  class="el-menu-vertical-demo" 
                         text-color="#fff" active-text-color="#ffd04b">
                        <el-menu-item v-for="(data,index) in list" :key="index"  @click="toinfo(data.frontendMenuUrl)" :class="(data.frontendMenuId==5||data.frontendMenuId==6)?'el':'None'">
                            <span slot="title" v-text="data.frontendMenuName"></span>
                         </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <router-view />
                    </el-main>
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
            list:this.$store.state.list,
            // [require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg")],
            messageList:[{
                mid:1,
                mtitle:"四川师范大学",
                mvalue:"dafdsfsd"
            },
            {
                mid:2,
                mtitle:"test",
                mvalue:"dafdsfsd"
            },
            {
                mid:3,
                mtitle:"test",
                mvalue:"dafdsfsd"
            }
            ]
        }
    },
    methods:{
        toinfo(data){
            console.log(data)
            this.$router.push(data.replaceAll('"',''));
        },
    },
    mounted(){
        // let param = new URLSearchParams();
        // param.append("id", this.$store.state.id);
        // // console.log(this.$store.state.id)
        // this.axios.post("/student/findbyid", param).then(res => {
        //     // console.log(res.data)
        //     if (res.data.code != 200) {
        //     this.$message({
        //         type: "error",
        //         message: "获取信息失败"
        //     });
        //     } else {
        //     this.$store.commit("setStuInfro",res.data.data)
        //     }
        // });
        
    }
}
</script>

<style lang="scss" scoped>
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

    .el-menu-vertical-demo {
        height: 400px;
        // margin-top: 30px;
    }
    .el-container{
        padding-top: -20px;
    }
    .el-main{
        padding-left: 10px;
        padding-top: 0;
        margin-top: -10px;
    }
    .el-menu-item-group{
        background-color: #276E51;
    }
    .el-menu-item{
        padding-left: 0;
    }
    .el-menu-vertical-demo {
        height: 400px;
        margin-top: 30px;
    }
    .el-menu{
        border: none;
    }
</style>
<style lang="less">
.el-menu /deep/.el-menu-item:hover{
  background-color: #1D9558 !important;
}
</style>