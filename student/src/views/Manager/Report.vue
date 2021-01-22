<template>
    <div id="big">
        <p id="title">现场报到详情确认</p>
        <div id="find">
            <span>待确认报到的新生人数：{{valueList.length}}</span>
            <span id="sno">学号：</span>
            <input type="text" v-model="Id">
            <el-button @click="findById" icon="el-icon-search">查找</el-button>
        </div>
        <el-table
            :data="valueList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            stripe
            :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
            @selection-change="handelSelectionChange_two">
             <el-table-column prop="college" label="学院"></el-table-column>
            <el-table-column prop="major" label="专业"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="userName" label="学号" sortable></el-table-column>
            <el-table-column  label="身份证照片" width="200px" >
                <template slot-scope="scope">
                    <el-popover placement="left" title="" trigger="click">
                        <!-- :src="scope.row.proof" -->
                        <img  :src="scope.row.photo" style="width: 500px;height: 300px">
                        <img slot="reference" :src="scope.row.photo" style="width: 160px;height: 100px">
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="操作" >
                <template slot-scope="scope">
                    <div @click="sure(scope.row)" type="text" class="sure">确认报道</div>
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
                if(t==29){
                    index=1;
                }
            }
            if(index==1){
            if(this.Id==""){
                this.getlist();
            }else{
                let param = new URLSearchParams()
                param.append("username",this.Id);
                // console.log(this.Id)
                // this.axios.post("/users/getreportnowbyid",param,
                this.axios.post(this.$apiUrl +"/users/getreportnowbyusername",param,
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
                        var obj=[];
                        obj.push(res.data.data);
                        // obj[0].photo="data:image/jpeg;base64,"+obj[0].photo;
                        this.valueList=obj;
                        // console.log(obj[0].photo)
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
            // console.log(item)
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==30){
                    index=1;
                }
            }
            if(index==1){
                let param = new URLSearchParams()
                param.append("username",item.userName);
                // console.log(id);
                this.$confirm('确认报道?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    
                    this.axios.post(this.$apiUrl +"/users/reportconfirm",param,{
                headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
            }).then(res=>{
                        console.log(res.data)
                        if(res.data.code!=0){
                            this.$message.error({
                                message: res.data.msg,
                                duration:1500
                            });
                        }else{
                            this.$message({
                                type: 'success',
                                message: '确认成功!',
                                duration:1500
                            });
                            var index = this.valueList.findIndex(i =>{
                                if(i.userName==item.userName){
                                    return true
                                }
                            });
                            this.valueList.splice(index,1)
                            // console.log(res.data)
                            // this.valueList.splice(index,1);
                        }
                    }).catch(err=>{
                        console.log(err);
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消确认',
                        duration:1500
                    });          
                });
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
            this.axios.post(this.$apiUrl +"/users/getreportnow",param,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
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
    }
}
</script>


<style lang="less" scoped>
#sno{
    margin-left: 48%;
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
    // /deep/ .el-table,
    // /deep/ .el-table tr,
    // /deep/ .el-table td{
    //     background-color: white;
    //     // opacity: 0.9;
        
    // }
    // /deep/ .el-table th{
    //     // background-color: #f3ffdb;
    //     background-color: #E5FFF2;
    // }
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