<template>
    <div>
        <div id="topper">
            <div class="top">
                <img src="../assets/sicnu2.png" alt="" >
                <!-- <div></div> -->
                <!-- <p id="first">四川师范大学数字迎新系统</p> -->
            </div>
            <!-- <div class="top"></div> -->
            <div class="top">
                <p class="t" @click="tothis">首页</p>
                <p class="t" @click="tohome">学校主页</p>
                
                <p v-for="data in list" :key="data.frontendMenuName" @click="toother(data.frontendMenuUrl)" v-text="data.frontendMenuName" :class="(data.frontendMenuId==4 ||data.frontendMenuId==8 ||data.frontendMenuId==20)?'t':'None'" ></p>
                <!-- <p class="t"> -->
                    <!-- v-for="data in list" :key="data.name" :class="data.name='高级管理'&&data.code==1?'t':'None'" -->
                <p :class="gao==1?'t':'None'"  @click="ulflag=!ulflag">
                    高级管理<i class="el-icon-arrow-down el-icon--right"></i>
                </p>
                <p class="t" @click="tologin" v-if="this.$store.state.loginflag">登录</p>
                <p class="t" @click="tologout" v-if="!this.$store.state.loginflag">退出</p>
            </div>
            <ul v-if="ulflag" @click="ulflag=!ulflag">
                <li v-for="data in list" :key="data.frontendMenuName" @click="tomanage(data.frontendMenuUrl)" :class="(data.frontendMenuId==55 ||data.frontendMenuId==68 ||data.frontendMenuId==76)?'':'None'" v-text="data.frontendMenuName"></li>
                
            </ul>
        </div>
        <hr>
        <div>
            <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$store.state.pathflag">
                <el-breadcrumb-item
                    v-for="(item,index) in breadList"
                    :key="index"
                    :to="{ path: item.path }" :class="index==0?'None':'t'"
                >{{item.meta.title}}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <router-view/>
        </div>
        <div id="foot">
            <div id="site">
                <div>狮子山校区：成都市锦江区静安路5号 (邮编:610066)</div>
                <div>成龙校区：成都市龙泉驿区成龙大道二段1819号 (邮编:610101)</div>
                <div>© All Right Reserved . 四川师范大学 版权所有  蜀ICP备05026983号</div>
            </div>
            <div id="sicnu">
                <a href="https://www.weibo.com/chuanshida?refer_flag=1015155010_&is_all=1">@四川师范大学微博</a>
                <br><br><br><br>
                <a href="http://www.sicnu.edu.cn/">@四川师范大学官网</a>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    data(){
        return{
            index:0,
            list:this.$store.state.list,
            flag:this.$store.state.id=='',
            gao:this.$store.state.gao,
            ulflag:0,
            breadList: [],
        }
    },
    methods:{
        tomanage(data){
            this.$router.push(data.replaceAll('"',''))
        },
        tothis(){
            this.$router.push('/main')
        },
        toother(link){
            this.$router.push(link.replaceAll('"',''))
        },
        tologin(){
            this.$router.push('/main/login')
        },
        tohome(){
            window.location.href="http://www.sicnu.edu.cn/";
        },
        tologout(){
             this.$confirm('确认退出?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.axios.get(this.$apiUrl +"/logout",
                    {
                headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
            }
                ).then(res=>{
                    // console.log(res.data)
                    if(res.data.code!=0){
                        this.$message.error({
                            message: res.data.msg,
                            duration:1500
                        });
                    }else{
                        this.$message({
                            type:"success",
                            message: res.data.data,
                            duration:1500
                        });
                    }
                }).catch(err=>{
                    console.log(err);
                });
                this.$store.commit('setloginflag',1);
                this.$store.commit("setGao",0)
                this.$store.commit('setId',{id:'',list:[],stuinfo:{}});
                this.list=this.$store.state.list;
                this.gao=this.$store.state.gao;
                console.log(1)
                this.$router.push('/main')
                console.log(111)
                
                // window.location.reload();   
            }).catch(() => {
                console.log(222)
                this.$message({
                    type: 'info',
                    message: '取消退出',
                    duration:1500
                });          
                this.$router.push('/main')
            });
            

        },
        handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      setdata(){
            this.list=this.$store.state.list;
      },
      isHome(route) {
        return route.name === "home";
    },
    getBreadcrumb() {
        let matched = this.$route.matched;
        //如果不是首页
        if (!this.isHome(matched[0])) {
            matched = [{ path: "/home", meta: { title: "首页" } }].concat(matched);
        }
        this.breadList = matched;
        },
        
    },
    mounted(){
        console.log(this.$store.state.gao);
        var i=0;
        for(i=0;i<this.$store.state.list.length;i++){
            var item=this.$store.state.list[i].frontendMenuId;
            // console.log(item)
            if(item==4||item==8||item==20){
                this.index++;
            }
        }
        console.log(this.$store.state.list)
        this.setdata();
        // this.list=this.$store.state.list;
        
    },
    created() {
        this.getBreadcrumb();
    },
    watch:{
        $route(to){
            this.getBreadcrumb();
            if (to.path =='/main' ||to.path =='') {
                this.$store.commit("setMsgNum",3);
                this.list=this.$store.state.list;
                this.gao=this.$store.state.gao;
                console.log(this.$store.state.gao)
                // this.pathflag=0;
                // window.location.reload();  
                if(this.$store.state.loginflag==1){
                    this.$store.commit('setId',{id:'',list:[]})
                }
                console.log(this.$store.state.list)
                // this.$router.go(0)
            }else{
                this.$store.commit("setMsgNum",1000);
            }
            if(to.path=='/main/findId' || to.path=='/main/findPassword'||to.path =='/main' ||to.path ==''|| to.path =='/main/message'||to.path =='/main/message/msglist'||to.path =='/main/login'||to.path =='/main/findid'||to.path =='/main/findpassword'){
                
                this.$store.commit("setPath",0);
            }else{
                this.$store.commit("setPath",1);
            }
            console.log(to.path)
            if(to.path=='/main/manageuser/muser'){
                this.$store.commit("setuserflag",1);
            }
            if(to.path=="/main/state/information"){
            this.$store.commit('setinfoflag',1);
            }
            if(to.path =='/main/msgmhome'){
                this.$store.commit('setreport',1)
                console.log(this.$store.state.reportflag)
            }
            
        }
    },
}
</script>

<style lang="scss" scoped>

.el-breadcrumb{
    padding-left: 210px;
    margin-top: 10px;
}
ul{
    position: absolute;
    color: white;
    background-color: #276E51;
    margin-left: 86%;
    margin-top: 80px;
    z-index: 100;
    li{
        height: 35px;
        line-height: 35px;
        width: 130px;
        font-size: 15px;
        text-align: center;
        background-color:  #276E51;
        font-family: cursive;
        cursor: pointer;
        list-style: none;
    }
    li:hover{
        background-color: #1D9558;
    }
}
    
    #topper{
        height: 80px;
        width: 100%;
        color: white;
        display: flex;
        justify-content: space-between;
        background-color: #276E51;
        img{
            display: inline-block;
            height: 80px;
            background-size: 100%;
            
        }
        #first{
            display: inline-block;
        }
        p{
            font-size: 40px;
            // margin-left: 20px;
            vertical-align: top;
            line-height: 80px;
            font-weight: lighter;
            font-family: cursive;
        }
        .t{
            // display: inline-block;
            display: inline;
            font-size: 20px;
            cursor: pointer;
            margin-left: 20px;
        }
        .top{
            // display: inline;
            height: 80px;
            min-width: 30px;
        }
    } 
   
    #foot{
        height: 170px;
        width: 100%;
        background-color:#276E51;
        // opacity: 0.6; 
        color: white;
        font-size: 13px;
        #site{
            width: 800px;
            padding-top: 65px;
            padding-left: 5%;
            div{
                display: inline-block;
                height: 30px;
                width: 400px;
            }
        }
    }
    #sicnu{
        position: absolute;
        margin-top: -80px;
        margin-left: 85%;
        font-size: 13px;
        a{
            color: white;
            text-decoration: none;
        }
    }
    #pic{
        margin-top: 20px;
    }
.None{
        display: none;
    }
    
</style>
<style lang="less">
    /deep/.el-menu-item{
    padding: 0px;
    }
    .el-menu{
        border: none;
    }
</style>