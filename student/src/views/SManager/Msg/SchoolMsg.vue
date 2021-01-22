<template>
    <div>
        <div class="whole" v-if="flag">
            <span class="title">学校通知</span>
            <el-divider></el-divider>
            <el-table ref="multipleTable" :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}" tooltip-effect="dark"
                :data="notice_one.slice((currpage_one-1)*pagesize_one,currpage_one*pagesize_one)" stripe
                @selection-change="handelSelectionChange_one" :row-key="getRowKey">
                <el-table-column type="selection" :reserve-selection="true" label="全部" width="80"></el-table-column>
                <el-table-column prop="title" label="消息" width="508" sortable></el-table-column>
                <el-table-column prop="operation" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="deTails_one(scope.row)" type="text" icon="el-icon-edit-outline">修改
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination class="pages" layout="total, sizes, prev, pager, next, jumper"
                @current-change="current_change_one" :total="total_one" @size-change="handelSizeChange_one"
                :page-size="pagesize_one" :page-sizes="[3,6,9,12]">
            </el-pagination>
            <el-button type="primary" class="Button" @click="Delete_one" icon="el-icon-delete">删除</el-button>
            <el-button type="primary" class="Button1" @click="toadd" icon="el-icon-circle-plus-outline">新增</el-button>
            <!-- <el-dialog :visible.sync="showdialog_one" width="40%" center append-to-body>
                <el-form label-width="80px">
                    <el-form-item v-model="add_header" label="标题">
                        <el-input type="textarea" v-model="add_header"></el-input>
                    </el-form-item>
                    <el-form-item v-model="add_content" label="内容">
                        <el-input type="textarea" rows="5" v-model="add_content"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="Confirm_one" type="primary">确定</el-button>
                    <el-button @click="Cancle_one" type="primary">取消</el-button>
                </span>
            </el-dialog> -->
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                //存储从后端所读取到的数据
                notice_one: [],
                add_header: '',
                add_content: '',
                notice_one_delete: [], //存储所选中的需要进行删除的数据
                showdialog_one: false, //开始时其弹窗不进行相关显示
                total_one: 10, //默认的数据总数
                pagesize_one: 3, //每页的数据条数
                currpage_one: 1, //默认的开始的页面
                flag: this.$store.state.reportflag,
            }
        },
        mounted() {
            this.show_one();
            this.$store.commit("setMsgtype", "学校通知");
        },
        // created() {
        //     // 注意：需要判断在路由已加载的情况下刷新，如果路由未加载则无需刷新
        //     if (this.$router.options.routes[1] !== undefined) {
        //     location.reload();
        //     }
        // },
        methods: {
            getRowKey(row) {
                return row.id;
            },
            toadd() {
                var index = 0;
                var tt = 0;
                for (tt = 0; tt < this.$store.state.list.length; tt++) {
                    var t = this.$store.state.list[tt].frontendMenuId;
                    // console.log(t)
                    if (t == 40) {
                        index = 1;
                    }
                }
                if (index == 1) {
                    this.$router.push('/main/msgmhome/schoolmsg/addmsg');
                    this.flag = 0;
                } else {
                    this.$message.error({
                        message: "无该权限！",
                        duration: 1500
                    });
                }
            },
            show_one() {
                // window.location.reload();
                this.axios.get(this.$apiUrl +'/message/gettitle', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取信息失败'
                            })
                        } else {
                            this.notice_one = res.data.data;
                            this.total_one = res.data.data.length;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //将选中的需要进行删除的数据放入到notice_one_delete中
            handelSelectionChange_one(val) {
                this.notice_one_delete = val;
            },
            //读取当前所规定的页面大小
            handelSizeChange_one(val) {
                this.pagesize_one = val;
            },
            //读取选中的当前页
            current_change_one(currpage) {
                this.currpage_one = currpage;
            },
            //获取所需要进行操作的登录界面的信息
            deTails_one(row) {
                var index = 0;
                var tt = 0;
                for (tt = 0; tt < this.$store.state.list.length; tt++) {
                    var t = this.$store.state.list[tt].frontendMenuId;
                    // console.log(t)
                    if (t == 42) {
                        index = 1;
                    }
                }
                if (index == 1) {
                    this.$store.commit("setMsgId", {
                        id: row.id,
                        title: row.title,
                        value: row.tvalues,
                        type: "学校通知"
                    })
                    this.$store.commit('setreport', 0)
                    this.flag = 0
                    this.$router.push({
                        path: '/main/msgmhome/schoolmsg/message',
                    })
                } else {
                    this.$message.error({
                        message: "无该权限！",
                        duration: 1500
                    });
                }
            },
            //批量删除所选中的数据
            Delete_one() {
                var index = 0;
                var tt = 0;
                for (tt = 0; tt < this.$store.state.list.length; tt++) {
                    var t = this.$store.state.list[tt].frontendMenuId;
                    // console.log(t)
                    if (t == 42) {
                        index = 1;
                    }
                }
                if (index == 1) {
                    if (this.notice_one_delete.length === 0) {
                        this.$message({
                            type: 'info',
                            message: '请先选中选项'
                        })
                    } else {
                        var array = []; //存储需要给后端传递相关的mid值
                        for (var i in this.notice_one_delete) {
                            array.push(this.notice_one_delete[i].id)
                        }
                        //遍历删除所选中的内容
                        this.$confirm('确定删除该条记录？', '提示', {
                            confirmButtonText: '确定',
                            cancleButtonText: '取消',
                            type: 'warning',
                        }).then(() => {

                            let param = new URLSearchParams()
                            param.append("list", array)
                            // this.axios.post('/message/batchdelete', array)
                            this.axios.post(this.$apiUrl +'/message/delete', param, {
                                    headers: {
                                        'Authorization': localStorage.getItem('token' + this.$store.state
                                            .id)
                                    }
                                })
                                .then(res => {
                                    if (res.data.code != 0) {
                                        this.$message({
                                            type: 'error',
                                            message: '删除失败'
                                        })
                                    } else {
                                        var index = this.notice_one.findIndex(i => {
                                            if (i.mid == this.notice_one_delete.mid) {
                                                return true
                                            }
                                        });
                                        this.notice_one.splice(index, 1);
                                        this.total_one -= this.notice_one_delete.length;
                                        if(this.total_one%this.pagesize_one === 0){
                                            this.currpage_one--;
                                        }
                                        this.$refs.multipleTable.clearSelection();
                                        this.$message({
                                            type: 'success',
                                            message: '删除成功！'
                                        });
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
            Cancle_one() {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
                this.showdialog_one = false
            },
        },
        watch: {
            $route(to) {

                if (to.path == '/main/msgmhome/schoolmsg') {
                    // this.$store.commit('setreport',1)
                    this.flag = this.$store.state.reportflag;
                    this.show_one();

                }
            }
        }
    }
</script>

<style lang="less" scoped>
    @import '../../../less/layout.css';
    // @import '../../../less/table.less';

    //分页样式
    .pages {
        position: relative;
        margin-left: 10%;
        margin-top: 2%;
    }

    //删除按钮样式
    .Button {
        position: relative;
        margin-left: 35%;
        margin-top: 3%;
        margin-bottom: 3%;
        background-color: #409eff;
        opacity: 0.9;
    }

    //新增按钮样式
    .Button1 {
        position: relative;
        margin-left: 30%;
        margin-top: 3%;
        margin-bottom: 3%;
        background-color: #409eff;
        opacity: 0.9;
    }

    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
    }

    .el-table {
        width: 78.8%;
        margin-left: auto;
        margin-right: auto;
        // margin-top: 20px;
    }

    //多文本输入样式
    .Textarea {
        position: relative;
        margin-top: 3%;
        margin-left: 20%;
        width: 50%;
    }

    /deep/ .el-button [class*=el-icon-]+span {
        margin-left: 2px;
    }
</style>