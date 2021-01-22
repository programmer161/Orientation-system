<template>
    <div>
      <el-container>
                <el-aside width="200px">
                    <el-menu  class="el-menu-vertical-demo" 
                         text-color="#fff" active-text-color="#ffd04b">
                         <el-menu-item v-for="(data,index) in list" :key="index"  @click="toinfo(data.frontendMenuUrl)" :class="(data.frontendMenuId==39||data.frontendMenuId==43||data.frontendMenuId==47||data.frontendMenuId==51)?'el':'None'">
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
            // [require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg")],
            list:this.$store.state.list,
        }
    },
    methods:{
        toinfo(data){
            this.$router.push(data.replaceAll('"',''));
        },
    },
    // created() {
    //         // 注意：需要判断在路由已加载的情况下刷新，如果路由未加载则无需刷新
    //         if (this.$router.options.routes[1] !== undefined) {
    //         location.reload();
    //         }
    //     },
    watch:{
            $route(to){
                if(to.path =='/main/msgmhome'||to.path =='/main/msgmhome/schoolmsg' ||to.path =='/main/msgmhome/paymsg' ||to.path =='/main/msgmhome/reportmsg' ||to.path =='/main/msgmhome/schoolstrategy'){
                    this.$store.commit('setreport',1)
                }else{
                    this.$store.commit('setreport',0)
                }
                console.log(this.$store.state.reportflag)
                console.log(to.path)
            }
        },
    //     created() {
	// // 注意：需要判断在路由已加载的情况下刷新，如果路由未加载则无需刷新
    // if (this.$router.options.routes[1] == undefined) {
    //   location.reload();
    // }
//   }

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
        // margin-top: -20px;
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