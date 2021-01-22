<template>
    <div>
        <div class="whole" v-if="flag">
            <span class="title">管理用户</span>
            <el-divider></el-divider>
            <span style="margin-left:100px;margin-right:12px;">用户身份</span>
            <el-radio-group v-model="role" @change="showChange(role)">
                <el-radio label="学生"></el-radio>
                <el-radio label="普通管理员"></el-radio>
            </el-radio-group>
            <br>
            <br>
            <div v-if="Mauser">
                <el-form label-width="40px" :model="stu">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="学院">
                                <el-select v-model="stu.college" placeholder="请选择相应的学院"
                                    @change="change_college(stu.college)">
                                    <el-option v-for="item in colleges" :key="item" :label="colleges[item]"
                                        :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6">
                            <el-form-item label="专业">
                                <el-select v-model="stu.major" placeholder="请选择相应的专业" @change="change_major(stu.major)">
                                    <el-option v-for="item in majors" :key="item" :label="majors[item]" :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6">
                            <el-form-item label="学号" style="margin-left:115px;width:160px">
                                <el-input v-model="stu.userId"></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6">
                            <el-form-item>
                                <el-button type="primary" @click="found_sno_stu" style="margin-left:30px;font-size:12px;padding:7px;background-color: #409eff;
        opacity:  0.9;" icon="el-icon-search">查找</el-button>
                            </el-form-item>
                        </el-col>

                    </el-row>
                </el-form>
                <el-table
                stripe key="key1"
                :data="check_stu.slice((currpage_stu-1)*pagesize_stu,currpage_stu*pagesize_stu)" 
                :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
                    @selection-change="handelSelectionChange_stu">
                    <el-table-column type="selection" label="所有" width="60"></el-table-column>
                    <el-table-column prop="userName" label="学号" width="150" sortable></el-table-column>
                    <el-table-column prop="name" label="姓名" width="110" sortable></el-table-column>
                    <el-table-column prop="major" label="专业" width="160" sortable></el-table-column>
                    <el-table-column prop="college" label="学院" width="200" sortable></el-table-column>
                    <el-table-column label="操作" width="108">
                        <template slot-scope="scope">
                            <el-button @click="deTails_stu(scope.row)" type="text" icon="el-icon-chat-line-square">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination class="pages" layout="total, sizes, prev, pager, next, jumper"
                    @current-change="current_change_stu" :total="total_stu" @size-change="handelSizeChange_stu"
                    :page-size="pagesize_stu" :page-sizes="[5,10,15,20]">
                </el-pagination>
                <el-button type="primary" class="Delete" @click="Delete_message_stu" icon="el-icon-delete">删除用户</el-button>
            </div>
            <div v-if="Madmin">
                <el-form label-width="60px" :model="ma">
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="学院" style="margin-right:22%;margin-left:5%">
                                <el-select v-model="ma.college" placeholder="请选择相应的学院"
                                    @change="change_college(ma.college)">
                                    <el-option v-for="item in colleges" :key="item" :label="colleges[item]"
                                        :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="工号" style="margin-left:230px;width:160px">
                                <el-input v-model="ma.sno"></el-input>
                            </el-form-item>

                        </el-col>
                        <el-col :span="8">
                            <el-form-item>
                                <el-button type="primary" @click="found_sno" style="margin-left:60px;font-size:12px;padding:7px;background-color: #409eff;
        opacity:  0.9;" icon="el-icon-search">查找</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-table
            :data="check.slice((currpage-1)*pagesize,currpage*pagesize)"
            :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
            stripe key="key2"
            @selection-change="handelSelectionChange">
                    <el-table-column type="selection" label="所有" width="60"></el-table-column>
                    <el-table-column prop="userName" label="工号" width="190" sortable></el-table-column>
                    <el-table-column prop="name" label="姓名" width="180" sortable></el-table-column>
                    <el-table-column prop="college" label="学院" width="220" sortable></el-table-column>
                    <el-table-column label="操作" width="138">
                        <template slot-scope="scope">
                            <el-button @click="deTails(scope.row)" type="text" icon="el-icon-chat-line-square">详情</el-button>
                        </template>
                    </el-table-column>
        </el-table>
                <el-pagination class="pages" layout="total, sizes, prev, pager, next, jumper"
                    @current-change="current_change" :total="total" @size-change="handelSizeChange"
                    :page-size="pagesize" :page-sizes="[5,10,15,20]">
                </el-pagination>
                <el-button type="primary" class="Delete" @click="Delete_message" icon="el-icon-delete">删除用户</el-button>
            </div>
        </div>
        <router-view></router-view>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                key1:1,
                key2:2,
                flag: this.$store.state.userflag,
                colleges: [],
                majors: [],
                check_stu: [],
                stu: {
                    college: '所有',
                    major: '所有',
                    sno: ''
                },
                check_stu_delect: [], //存储选中的所需要进行删除的信息
                currpage_stu: 1,
                pagesize_stu: 5,
                total_stu: 0,
                check: [],
                ma: {
                    college: '所有',
                    major: '',
                    sno: ''
                },
                check_delect: [], //存储选中的所需要进行删除的信息
                currpage: 1,
                pagesize: 5,
                total: 0,
                Mauser: false,
                Madmin: false,
                role: '学生'
            }
        },

        methods: {
            //获取所有普通管理员的信息
            show() {
                this.axios.get(this.$apiUrl +'/users/getmanagerall', {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token'+this.$store.state.id),
                        }
                    })
                    .then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取管理员信息失败'
                            })
                        } else {
                            this.check = res.data.data;
                            this.total = res.data.data.length;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //在表格中显示所有用户信息
            show_stu() {
                this.axios.get(this.$apiUrl +'/users/getstudentsys',{
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token'+this.$store.state.id),
                        }
                    })
                    .then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取用户信息失败'
                            })
                        } else {
                            this.check_stu = res.data.data;
                            this.total_stu = res.data.data.length;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //在下拉框中显示从后端所读取到的学院信息
            show_colleges() {
                this.axios.get(this.$apiUrl +'/college/getcollege', {
                        headers: {
                            'Authorization': localStorage.getItem('token'+this.$store.state.id),
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取学院信息失败'
                            })
                        } else {
                            this.colleges = [];
                            this.colleges.push('所有');
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.colleges.push(res.data.data[i].college);
                            }
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //当选中下拉框中的学院时关联其所对应的专业，同时在表格中显示该学院的学生信息
            change_college() {
                var url;
                var flag1;
                if ((this.stu.college === '所有'&&this.role === '学生') || (this.ma.college === '所有' && this.role === '普通管理员')) {
                    if (this.role === '学生') {
                        let param = new URLSearchParams()
                        param.append("username",this.$store.state.id);
                        url = this.$apiUrl +'/users/findsdtuentall';
                        flag1 = 0;
                        //获取所对应的用户信息和相关的所有专业信息
                    this.axios.all([this.axios.post(url, param,{
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.get(this.$apiUrl +'/college/getmajor', {
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2) => {
                            if (res1.code === 0 && res2.code === 0) {
                                if (flag1 === 0) {
                                    this.check_stu = res1.data;
                                    this.total_stu = res1.data.length;
                                    this.currpage_stu = 1;
                                } else {
                                    this.check = res1.data;
                                    this.total = res1.data.length;
                                    this.currpage = 1;
                                }
                                this.majors = [];
                                this.majors.push('所有');
                                for (let i = 0; i < res2.data.length; i++) {
                                    this.majors.push(res2.data[i].major);
                                }
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: '用户信息和专业信息获取失败'
                                })
                            }
                        })).catch(err => {
                            console.log(err);
                        })
                    } else {
                        url = this.$apiUrl +'/users/getmanagerall';
                        flag1 = 1;
                        //获取所对应的用户信息和相关的所有专业信息
                    this.axios.all([this.axios.get(url, {
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.get(this.$apiUrl +'/college/getmajor', {
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2) => {
                            if (res1.code === 0 && res2.code === 0) {
                                if (flag1 === 0) {
                                    this.check_stu = res1.data;
                                    this.total_stu = res1.data.length;
                                    this.currpage_stu = 1;
                                } else {
                                    this.check = res1.data;
                                    this.total = res1.data.length;
                                    this.currpage = 1;
                                }
                                this.majors = [];
                                this.majors.push('所有');
                                for (let i = 0; i < res2.data.length; i++) {
                                    this.majors.push(res2.data[i].major);
                                }
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: '用户信息和专业信息获取失败'
                                })
                            }
                        })).catch(err => {
                            console.log(err);
                        })
                    }
                    
                } else {
                    let param = new URLSearchParams();
                    if (this.role === '学生') {
                        url = '/users/findbycollege';
                        param.append('college', this.stu.college);
                        flag1 = 0;
                    } else {
                        url =this.$apiUrl + '/users/findmanagerbycollege';
                        param.append('college', this.ma.college);
                        flag1 = 1;
                    }
                    //通过学院查找所对应的用户信息和该学院下相关的专业
                    this.axios.all([this.axios.post(url, param, {
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.post(this.$apiUrl +'/college/getmajorbycollege', param, {
                                headers: {
                                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2) => {
                            if (res1.code === 0 && res2.code === 0) {
                                if (flag1 === 0) {
                                    this.check_stu = res1.data;
                                    this.total_stu = res1.data.length;
                                    this.currpage_stu = 1;
                                } else {
                                    this.check = res1.data;
                                    this.total = res1.data.length;
                                    this.currpage = 1;
                                    console.log('this.check', this.check)
                                }
                                this.majors = [];
                                this.majors.push('所有');
                                for (let i = 0; i < res2.data.length; i++) {
                                    this.majors.push(res2.data[i].major);
                                }
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: '用户信息或专业信息获取失败'
                                })
                            }
                        })).catch(err => {
                            console.log(err);
                        })
                }
            },
            //当选择了所对应的专业后在表格中显示该专业所对应的学生信息
            change_major() {
                let url;
                if (this.stu.major === '所有') {
                    this.change_college();
                } else {
                    let param = new URLSearchParams();
                    param.append('major', this.stu.major);
                    this.axios.post(this.$apiUrl +'/users/findbymajor', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token'+this.$store.state.id)
                        }
                    }).then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '用户信息获取失败'
                            })
                        } else {
                            this.check_stu = res.data.data;
                            this.total_stu = res.data.data.length;
                            this.currpage_stu = 1;
                        }
                    }).catch(err => {
                        console.length(err);
                    })
                }
            },
            found_sno_stu() {
                console.log(this.stu.userId);
                if (this.stu.userId === '') {
                    this.change_major();
                } else {
                    let param = new URLSearchParams();
                    param.append("username", this.stu.userId);
                    this.axios.post(this.$apiUrl +'/users/findstudentbyusername', param, {
                            headers: {
                                'Authorization': localStorage.getItem('token'+this.$store.state.id)
                            }
                        })
                        .then(res => {
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '无该学号所对应的学生信息'
                                })
                            } else {
                                var array = [];
                                array.push(res.data.data);
                                this.check_stu = array;
                                this.total_stu = this.check_stu.length;
                                this.currpage_stu = 1;
                            }
                        })
                        .catch(err => {
                            console.log(err);
                        })
                }
            },
            handelSelectionChange_stu(val) {
                this.check_stu_delect = val;
            },
            //向/smanager/basicuser页面中传递该用户的学号信息
            deTails_stu(row) {
                this.$router.push({
                    path: '/main/manageuser/muser/basicuser',
                    query: {
                        id: row.userName
                    }
                })
                this.$store.commit("setStuInfro", row)

                this.$router.push({
                    path: '/main/manageuser/muser/basicuser',
                })
            },
            current_change_stu(currpage_stu) {
                this.currpage_stu = currpage_stu;
            },
            handelSizeChange_stu(val) {
                this.pagesize_stu = val;
            },
            Delete_message_stu() {
                var array = [];
                for (var i in this.check_stu_delect) {
                    array.push(this.check_stu_delect[i].userId)
                }
                this.$confirm('确定删除该条记录？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    if(this.check_stu_delect.length===0){
                        this.$message({
                            type:'info',
                            message:'请先选中选项'
                        })
                    }else{
                        let url = this.$apiUrl +'/user' + '?idList=' + array;
                    this.axios.delete(url, {
                            headers: {
                                'Authorization': localStorage.getItem('token'+this.$store.state.id)
                            }
                        })
                        .then(res => {
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '删除失败'
                                })
                            } else {
                                for (let i = 0; i < this.check_stu_delect.length; i++) {
                                    for (let j = 0; j < this.check_stu.length; j++) {
                                        if (JSON.stringify(this.check_stu[j]) === JSON.stringify(this
                                                .check_stu_delect[i])) {
                                            this.check_stu.splice(j, 1)
                                        }
                                    }
                                }
                                this.total_stu -= this.check_stu_delect.length;
                                this.$message({
                                    type: 'success',
                                    message: '删除成功！',
                                })
                            }
                        })
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
            showChange(val) {
                if (val === "学生") {
                    this.show_stu();
                    this.Mauser = true;
                    this.Madmin = false;
                    
                } else {
                    this.show();
                    this.Mauser = false;
                    this.Madmin = true;
                    
                }
            },
            found_sno() {
                if (this.ma.sno === '') {
                    this.change_college();
                } else {
                    let param = new URLSearchParams();
                    param.append("username", this.ma.sno);
                    this.axios.post(this.$apiUrl +'/users/findmanagerbyusername', param, {
                            headers: {
                                'Authorization': localStorage.getItem('token'+this.$store.state.id)
                            }
                        })
                        .then(res => {
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '无该工号所对应的管理员'
                                })
                            } else {
                                var array = [];
                                array.push(res.data.data);
                                this.check = array;
                                this.total = this.check.length;
                                this.currpage = 1;
                            }
                        })
                        .catch(err => {
                            console.log(err);
                        })
                }
            },
            handelSelectionChange(val) {
                this.check_delect = val;
            },
            deTails(row) {
                this.$router.push({
                    path: '/main/manageuser/muser/basicadmin',
                    query: {
                        id: row.userName
                    }
                })
                this.$store.commit("setStuInfro", row)
                this.$store.commit('setuserflag', 1)
                this.$store.commit('setadminflag', 1)
                this.flag = 0;
                this.$router.push({
                    path: '/main/manageuser/muser/basicadmin',
                })
            },
            current_change(currpage) {
                this.currpage = currpage;
            },
            handelSizeChange(val) {
                this.pagesize = val;
            },
            Delete_message() {
                var array = [];
                for (var i in this.check_delect) {
                    array.push(this.check_delect[i].userId)
                }
                this.$confirm('确定删除该条记录？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    if(this.check_delect.length===0){
                        this.$message({
                            type:'info',
                            message:'请先选中选项'
                        })
                    }else{
                        let url = this.$apiUrl +'/user' + '?idList=' + array;
                    this.axios.delete(url, {
                            headers: {
                                'Authorization': localStorage.getItem('token'+this.$store.state.id)
                            }
                        })
                        .then(res => {
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '删除失败'
                                })
                            } else {
                                for (let i = 0; i < this.check_delect.length; i++) {
                                    for (let j = 0; j < this.check.length; j++) {
                                        if (JSON.stringify(this.check[j]) === JSON.stringify(this
                                                .check_delect[i])) {
                                            this.check.splice(j, 1)
                                        }
                                    }
                                }
                                this.total -= this.check_delect.length;
                                this.$message({
                                    type: 'success',
                                    message: '删除成功！',
                                })
                            }
                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
        },
        mounted() {
            this.show_colleges();
            this.role = "学生";
            this.Mauser = true;
            this.Madmin = false;
            this.show_stu();
            // this.$store.state.userflag=1;

        },
        watch: {
            $route(to) {
                if (to.path == '/main/manageuser/muser') {
                    this.$store.commit('setuserflag', 1)
                    this.flag = 1;
                } else {
                    this.$store.commit('setuserflag', 0)
                    this.flag = 0;
                }
                console.log(to.path)
                console.log(this.$store.state.adminflag)
            }
        },
    }
</script>

<style lang="less" scoped>
    @import '../../../less/layout.css';
    // @import '../../../less/table.less';

    .whole {
        width: 1000px;
    }

    .pages {
        position: relative;
        margin-left: 10%;
        margin-top: 2%;
        margin-bottom: 0%;
        padding: 0%;
    }

    /deep/ .el-row {
        padding-left: 10%;
    }

    .Delete {
        position: relative;
        left: 83%;
        // margin-top: 2%;
        margin-bottom: 2%;
        padding: 7px;
        font-size: 12px;
        background-color: #409eff;
        opacity: 0.9;
    }

    /deep/ .el-form-item {
        display: inline-block;
        width: 90%;
        padding: 0%;
    }

    /deep/ .el-input__inner {
        height: 30px;
    }

    /deep/ .el-icon-arrow-up:before {
        top: 20px;
    }

    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
    }
    /deep/ .el-table{
        width: 78.8%;
        margin-left: auto;
        margin-right: auto;
    }

    /deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>