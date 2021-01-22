<template>
    <div>
      <el-container>
            <el-container>
                <el-aside width="200px">
                    <el-menu  class="el-menu-vertical-demo" 
                         text-color="#fff" active-text-color="#ffd04b">
                         <el-menu-item v-for="(data,index) in list" :key="index"  @click="toinfo(data.frontendMenuUrl)" :class="(data.frontendMenuId==56||data.frontendMenuId==59||data.frontendMenuId==63||data.frontendMenuId==75)?'el':'None'">
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
            console.log(data)
            this.$router.push(data.replaceAll('"',''));
        },
    }
}
</script>

<style lang="scss" scoped>
.None{
    display: none;
}
#form{
    width: 1020px;
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
        // box-shadow: 3px 3px 5px #276E51;
        margin-bottom: 10px;
        margin-left: 20px;
        // margin-right: 10px;
    }
    .el-main{
        padding: 10px;
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