<template>
    <div id="big">
        <p id="title">管理详情</p>

        <div id="find">
            <span>管理的新生人数：{{valueList.length}}</span>
                <el-form label-width="40px" >
            <el-row style="padding:0px;">
                <el-col :span="6">
                    <el-form-item label="学号" style="margin-left:600px;width:160px;background-color: transparent;border: none;">
                        <el-input v-model="Id"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item style="background-color: transparent;border: none;">
                        <el-button type="primary" @click="findById" style="margin-left:500px;font-size:12px;padding:7px;background-color: #409eff;
        opacity:  0.9;" icon="el-icon-search">查找</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        </div>
        <el-table :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}" tooltip-effect="dark"
            :data="valueList.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe
            @selection-change="handelSelectionChange_two">
            <el-table-column prop="college" label="学院" sortable></el-table-column>
            <el-table-column prop="major" label="专业" sortable></el-table-column>
            <el-table-column prop="name" label="姓名" sortable></el-table-column>
            <el-table-column prop="userName" label="学号" sortable></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <span @click="sure(scope.row)" class="sure" >
                        <i class="el-icon-chat-line-square"></i>
                        详情
                    </span>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 15, 20, 30]" :page-size="pagesize" :total="valueList.length"
            layout="total, sizes, prev, pager, next, jumper" id="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                name: "杨洋",
                sum: 430,
                finished: 200,
                Id: "",
                valueList: [],
                currentPage: 1,
                pagesize: 5,
            }
        },
        methods: {
            getRowClass() {
                return "background-color:transparent"
            },
            handleSizeChange: function (val) {
                this.pagesize = val;
            },
            handleCurrentChange: function (currentPage) {
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
            findById() {
                // var index=0;
                // var i=0;
                // for(i=0;i<this.$store.state.list.length;i++){
                //     var t=this.$store.state.list[i].frontendMenuId;
                //     // console.log(t)
                //     if(t==26){
                //         index=1;
                //     }
                // }
                // if(index==1){
                if (this.Id == "") {
                    this.getlist();
                } else {
                    let param = new URLSearchParams()
                    param.append("username", this.Id);
                    console.log(this.Id)
                    this.axios.post(this.$apiUrl +"/users/findstudentbyusername", param, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                        }
                    }).then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message.error({
                                message: res.data.msg,
                                duration: 1500
                            });
                        } else {
                            // console.log(res.data.data.college)
                            var obj = [];
                            obj.push(res.data.data);
                            this.valueList = obj;
                        }
                    }).catch(err => {
                        console.log(err);
                    });
                }
                // }else{
                //     this.$message.error({
                //         message: "无该权限！",
                //         duration:1500
                //     });
                // }
            },
            sure(item) {
                // var index=0;
                // var i=0;
                // for(i=0;i<this.$store.state.list.length;i++){
                //     var t=this.$store.state.list[i].frontendMenuId;
                //     // console.log(t)
                //     if(t==27){
                //         index=1;
                //     }
                // }
                // if(index==1){
                this.$store.commit("setStuInfro", item)
                this.$router.push('/main/manageuser/muser/basicuser')
                // }else{
                //     this.$message.error({
                //         message: "无该权限！",
                //         duration:1500
                //     });
                // }
            },
            getlist() {
                // 2020-12-17
                let param = new URLSearchParams();
                param.append('username', this.$store.state.stuInfro.userName);
                this.axios.post(this.$apiUrl +"/managerstudent/getuserbyusernameandmanager", param, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                    }
                }).then(res => {
                    console.log(res.data)
                    if (res.data.code != 0) {
                        this.$message.error({
                            message: res.data.msg,
                            duration: 1500
                        });
                        // this.$router.push('/login');
                    } else {
                        // console.log(res.data)
                        this.valueList = res.data.data;
                    }
                }).catch(err => {
                    console.log(err);
                });
            }
        },
        mounted() {
            // var obj = {
            //     'id': this.$store.state.id
            // };
            this.getlist();
        }
    }
</script>


<style lang="less" scoped>

    #sno {
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

    #big {
        width: 1020px;
        background-color: #F4F8F6;

        // margin-top: 20px;
        // padding-top: 20px;
        #find {
            margin-left: 3%;
            margin-top: 20px;

            input {
                margin-left: 10px;
                height: 30px;
                width: 120px;
            }

            button {
                height: 30px;
                width: 80px;
                background-color: #409eff;
                opacity: 0.9;
                color: white;
                outline: none;
                border: none;
                border-radius: 0.5em;
                margin-left: 20px;
            }
        }
    }

    #page {
        margin-top: 2%;
        padding-left: 3%;
        padding-bottom: 20px;
    }

    .el-table {
        width: 95%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 20px;
    }

    .sure {
        color: #0099FF;
        cursor: pointer;
    }

    /* 表格内背景颜色 */
    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
    }

    p {
        font-size: 30px;
        text-align: center;
        font-weight: bold;
        height: 60px;
        line-height: 60px;
        border-bottom: 1px dashed black;
    }


    #big #find input[data-v-0b1e431a] {
        margin-left: 0px;
        height: 20px;
        width: 100px;
    }

    #big #find button[data-v-0b1e431a]{
        height: 25px;
        width: 50px;
        cursor: pointer;
    }
    /deep/ .el-form-item {
        padding: 0%;
        height: 40px;
        width: 120px;
        background-color: transparent;
        border: none;
        line-height: 40px;
        margin-bottom: 0px;
    }
</style>