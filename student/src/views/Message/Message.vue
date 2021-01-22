<template>
    <div>
        <div id="mes">
            <div>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item to="/main">首页</el-breadcrumb-item>
                <el-breadcrumb-item to="/main/message/msglist" @click.native="toMsg">{{this.$store.state.msgtype}}</el-breadcrumb-item>
                <!-- <el-breadcrumb-item>活动列表</el-breadcrumb-item> -->
                <el-breadcrumb-item v-text="this.$store.state.msgtitle" v-if="flag"></el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <router-view> </router-view>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    data(){
        return{
            flag:0,
        }
    },
    methods:{
        toMsg(){
            this.flag=0;
        },
        setFlag(){
            if (this.$route.path =='/main/message' ) {
                // this.$router.push();
                this.flag=1;
            }else{
                this.flag=0;
            }
        }
        
    },
    mounted(){
        // document.body.style.backgroundImage="url(" + require("../assets/sicnu.jpg") + ")";
        this.setFlag();
        // console.log(this.$route.path)
    },
    watch:{
        $route(to){
            if (to.path =='/main/message' ) {
                // this.$router.push();
                this.flag=1;
            }else{
                this.flag=0;
            }
        }
    },
}
</script>

<style lang="scss" scoped>
    #mes{
        // border: 1px solid black;
        // width: 50%;
        font-size: 20px;
        margin-left: auto;
        margin-right: auto;
        padding-top: 30px;
        // padding-bottom: 20px;
        padding-left: 50px;
        padding-right: 50px;
        // margin-top: 50px;
        background-color: #F4F8F6;
        opacity: 0.8;
        // font-size: 13px;
        min-height: 300px;
        padding-bottom: 30px;
        #title{
            text-align: center;
            margin-bottom: 20px;
            font-size: 25px;
        }
        #values{
            // background-color: blue;
            min-height: 250px;
        }
    }
    
</style>