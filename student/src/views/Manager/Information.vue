<template>
<div>
    <div id="big" v-if="flag">
        <p id="title">学生信息</p>
        
        <div id="find">
            <span>管理的新生人数：{{valueList.length}}</span>
            <!-- <div> -->
            <span id="sno">学号：</span>
            <input type="text" v-model="Id">
            <el-button @click="findById" icon="el-icon-search">查找</el-button>
            <!-- </div> -->
        </div>
        <el-table
            :data="valueList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
            @selection-change="handelSelectionChange_two"
            stripe>
            <el-table-column prop="college" label="学院"></el-table-column>
                    <el-table-column prop="major" label="专业"></el-table-column>
                    <el-table-column prop="name" label="姓名"></el-table-column>
                    <el-table-column prop="userName" label="学号" sortable></el-table-column>
                    <el-table-column label="操作" >
                        <template slot-scope="scope">
                            <div @click="sure(scope.row)" type="text" class="sure">详情</div>
                        </template>
                    </el-table-column>
        </el-table>
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20, 30]"
        :page-size="pagesize"
        :total="valueList.length"
        layout="total, sizes, prev, pager, next, jumper"
        id="page">
        </el-pagination>
    </div>
    <router-view></router-view>
    </div>
</template>

<script>

export default {
    data(){
        return{
            name:"杨洋",
            sum:430,
            finished:200,
            Id:"",
            valueList:[],
            currentPage:1,
            pagesize:5,
            flag:this.$store.state.infoflag
        }
    },
    methods:{
        getRowClass(){
            return "background-color:transparent"
        },
        handleSizeChange: function(val) {
            this.pagesize = val;
        },
        handleCurrentChange: function(currentPage) {
            this.currentPage = currentPage;
        },
        handelSelectionChange_two(val) {
                this.notice_two_delete = val;
            },
            //读取当前所规定的页面大小
            handelSizeChange_two(val) {
                this.pagesize_two = val;
            },
            //读取选中的当前页
            current_change_two(currpage) {
                this.currpage_two = currpage;
            },
        findById(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==26){
                    index=1;
                }
            }
            if(index==1){
                if(this.Id==""){
                this.getlist();
            }else{
            let param = new URLSearchParams()
            param.append("username",this.Id);
            console.log(this.Id)
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
                    // console.log(res.data.data.college)
                    var obj=[];
                    obj.push(res.data.data);
                    this.valueList=obj;
                }
            }).catch(err=>{
                console.log(err);
            });
            }
            }else{
                this.$message.error({
                    message: "无该权限！",
                    duration:1500
                });
            }
        },
        sure(item){
            var index=0;
            var i=0;
            
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==27){
                    index=1;
                }
            }
            if(index==1){
                this.$store.commit('setinfoflag',0);
                this.flag=0;
                this.$store.commit("setStuInfro",item)
                this.$router.push('/main/state/information/findbyid')
            }else{
                this.$message.error({
                    message: "无该权限！",
                    duration:1500
                });
            }
        },
        getlist(){
            let param = new URLSearchParams()
            param.append("username",this.$store.state.id);
            this.axios.post(this.$apiUrl +"/users/findsdtuentall",param,
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
                // this.$router.push('/login');
            }else{
                // console.log(res.data)
                this.valueList=res.data.data;
            }
        }).catch(err=>{
            console.log(err);
        });
        }
    },
    mounted(){
        // var obj = {
        //     'id': this.$store.state.id
        // };
        this.getlist();
    },
    watch:{
        $route(to){
            if(to.path =='/main/state/information'){
                this.$store.commit('setinfoflag',1)
                this.flag=1;
            }else{
                this.$store.commit('setinfoflag',0)
                this.flag=0;
            }
            // console.log(to.path)
            // console.log(this.$store.state.adminflag)
        }
    },
}
</script>


<style lang="less" scoped>
#sno{
    margin-left: 52%;
}
#title {
    font-size: 30px;
    height: 70px;
    line-height: 70px;
    font-weight: normal;
    text-align: center;
    border-bottom: 0.5px solid gainsboro;
}
    #big{
        width:1020px;
        background-color: #F4F8F6;
        // margin-top: 20px;
        // padding-top: 20px;
        #find{
            margin-left: 3%;
            margin-top:20px;
            input{
                margin-left: 10px;
                height: 30px;
                width: 120px;
            }
            button{
                height: 30px;
                width: 80px;
                background-color: #409eff;
                opacity:  0.9;
                color: white;
                outline: none;
                border: none;
                border-radius: 0.5em;
                margin-left: 20px;
            }
        }
    }
    #page{
        padding-left: 3%;
        padding-bottom: 20px;
    }
    .el-table{
        width: 95%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 20px;
    }
    /deep/ .el-table thead{
        height: 50px;
    }
    .sure{
        color: #0099FF;
        cursor: pointer;
    }
    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
    }
    p{
        font-size: 30px;
        text-align: center;
        font-weight: bold;
        height: 60px;
        line-height: 60px;
        border-bottom: 1px dashed black;
    }
    /deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>