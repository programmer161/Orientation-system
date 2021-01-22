<template>
    <div id="whole">
        <span class="title">系统日志</span>
        <el-divider></el-divider>
        <!-- <div id="select">
            <span>日志等级 </span>
            <el-select v-model="datalist" placeholder="所有" >
                <el-option v-for="item in datalist.major" :key="item" :value="item">{{item}}
                </el-option>
            </el-select>
        </div> -->
        <el-form label-width="40px" :model="user">
            <el-row style="padding:0px;">
                <el-col :span="6">
                    <el-form-item label="学号" style="margin-left:600px;width:160px">
                        <el-input v-model="user.sno"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item>
                        <el-button type="primary" @click="found_sno_stu" style="margin-left:500px;font-size:12px;padding:7px;background-color: #409eff;
        opacity:  0.9;" icon="el-icon-search">查找</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-table :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}" tooltip-effect="dark"
            :data="valueList.slice((currentPage-1)*pagesize,currentPage*pagesize)" ref="multipleTable" stripe
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="userName" label="学号" sortable></el-table-column>
            <el-table-column prop="name" label="用户" sortable></el-table-column>
            <el-table-column prop="operation" label="日志" sortable></el-table-column>
            <el-table-column prop="type" label="类型" sortable></el-table-column>
            <el-table-column prop="createtime" label="操作时间" sortable></el-table-column>
            <!-- <el-table-column prop="params" label="请求参数" sortable></el-table-column> -->
            <el-table-column prop="loginip" label="ip地址" sortable></el-table-column>
            <el-table-column prop="url" label="url地址" sortable></el-table-column>
            <!-- <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="deTails(scope.row)" type="text" class="sure" icon="el-icon-chat-line-square">详情</el-button>
                </template>
            </el-table-column> -->
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 15, 20, 30]" :page-size="pagesize" :total="valueList.length"
            layout="total, sizes, prev, pager, next, jumper" id="page">
        </el-pagination>
        <el-button type="primary" @click="Delete_message" class="del" icon="el-icon-delete">删除</el-button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                datalist: {
                    "major": ['所有', 'ERROR', 'WARN', 'INFO', 'DEBUG']
                },
                user:{
                    sno:''
                },
                valueList: [],
                currentPage: 1,
                pagesize: 5,
                list: []
            }
        },
        methods: {
            found_sno_stu() {
                let param = new URLSearchParams();
                param.append('username', this.user.sno);
                this.axios.post(this.$apiUrl +'/excepmylog/getexcepmylog', param, {
                    headers: {
                        'Authorization': localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => {
                    if (res.data.code != 0) {
                        this.$message({
                            type: 'error',
                            message: '日志信息获取失败'
                        })
                    } else {
                        if (res.data.data.length === 0) {
                            this.$message({
                                type: 'error',
                                message: '无相关日志信息'
                            })
                        } else {
                            this.valueList = res.data.data;
                        }
                    }
                })
            },
            Delete_message() {
                var index = 0;
                var i = 0;
                for (i = 0; i < this.$store.state.list.length; i++) {
                    var t = this.$store.state.list[i].frontendMenuId;
                    // console.log(t)
                    if (t == 72) {
                        index = 1;
                    }
                }
                if (index == 1) {
                    let param = new URLSearchParams()
                    param.append("list", this.list);
                    var str = JSON.stringify(this.list).replace(/[[\]]/g, "");
                    // console.log(str);
                    if (str == "") {
                        this.$message({
                            type: 'info',
                            message: '请先选中选项'
                        })
                    } else {
                        this.$confirm('删除后不可恢复，确定删除？', '提示', {
                            confirmButtonText: '确定',
                            cancleButtonText: '取消',
                            type: 'warning',
                        }).then(() => {
                            this.axios({
                                    url: this.$apiUrl +'/excepmylog/delete',
                                    method: 'post',
                                    params: {
                                        list: str
                                    },
                                    headers: {
                                        'Authorization': 'Bearer ' + localStorage.getItem('token' + this
                                            .$store.state.id),
                                    }
                                })
                                .then(res => {
                                    console.log(res.data)
                                    if (res.data.code != 0) {
                                        this.$message({
                                            type: 'error',
                                            message: '删除失败'
                                        })
                                    } else {
                                        var t = 0;
                                        for (t = 0; t < this.list.length; t++) {
                                            var item = this.list[t];
                                            var index = this.valueList.findIndex(i => {
                                                if (i.id == item) {
                                                    return true
                                                }
                                            });
                                            // console.log(item,index)
                                            this.valueList.splice(index, 1)
                                        }
                                        this.$message({
                                            type: 'success',
                                            message: '删除成功！',
                                        })
                                    }
                                })

                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消删除'
                            })
                        })
                    }
                } else {
                    this.$message.error({
                        message: "无该权限！",
                        duration: 1500
                    });
                }
            },
            handelSizeChange_two(val) {
                this.pagesize_two = val;
            },
            //读取选中的当前页
            current_change_two(currpage) {
                this.currpage_two = currpage;
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
            handleSelectionChange(val) {
                this.multipleSelection = val;
                this.list = val.map(item => item.id);
            },
            //   deTails(data){
            //       console.log(data)
            //       this.$alert(
            //           '<strong>时间：</strong>'+data.createtime+"<br><strong>用户：</strong>"+data.name+"<br><strong>学号：</strong>"+data.idName
            //           +"<br><strong>方法：</strong>"+data.methodName+"<br><strong>参数：</strong>"+data.params
            //           +"<br><strong>ip：</strong>"+data.loginip+"<br><strong>操作：</strong>"+data.operation+"<br><strong>类型：</strong>"+data.type,
            //       {
            //       dangerouslyUseHTMLString: true
            //     });
            //   }

        },
        mounted() {
            this.axios.get(this.$apiUrl +"/excepmylog/getall", {
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
    }
</script>

<style lang="less" scoped>
    @import '../../../less/layout.css';

    .logvalue {
        color: black;
        // background-color: red;
        // height: 40px;
        // width: 800px;
        // border-radius: 0.3em;
        // line-height: 40px;
        // text-align: left;
    }

    span {
        margin-left: 10px;
    }

    .del {
        height: 30px;
        background-color: #409eff;
        opacity: 0.9;
        line-height: 8px;
        margin-left: 825px;
        margin-top: 10px;
        margin-bottom: 20px;
    }

    .el-table {
        width: 95%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 30px;
    }

    .sure {
        color: #0099FF;
        cursor: pointer;
    }

    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
    }

    .el-select {
        width: 150px;
        margin-left: 10px;
    }

    #whole {
        width: 1000px;
        min-height: 300px;
        background-color: #F4F8F6;
        // opacity: 0.8;
    }

    // #title{
    //     height: 60px;
    //     line-height: 60px;
    //     text-align: center;
    //     font-size: 20px;
    //     border-bottom: 1px solid gray;
    // }
    #select {
        margin-top: 20px;
        margin-left: 20px;
    }

    .el-pagination {
        margin-left: 20px;
    }

    /deep/ .el-button [class*=el-icon-]+span {

        margin-left: 2px;
    }
</style>