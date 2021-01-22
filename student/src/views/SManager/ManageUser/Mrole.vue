<template>
    <div class="whole">
        <span class="title">角色管理</span>
        <el-divider></el-divider>
        <el-table stripe ref="multipleTable" :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
            tooltip-effect="dark" :row-key="getRowKey_one" :data="role.slice((currpage-1)*pagesize,currpage*pagesize)"
            @selection-change="handelSelectionChange">
            <el-table-column type="selection" :selectable="selectHandle" :reserve-selection="true" label="所有"
                width="80"></el-table-column>
            <el-table-column prop="roleName" label="角色名称" width="200" sortable></el-table-column>
            <el-table-column prop="description" label="角色描述" width="250" sortable></el-table-column>
            <el-table-column label="操作" width="257">
                <template slot-scope="scope">
                    <el-button @click="deTails(scope.row)" type="text" icon="el-icon-edit">编辑</el-button>
                    <el-button @click="deTails_divide(scope.row)" type="text" icon="el-icon-user">用户分配</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="pages" layout="total, sizes, prev, pager, next, jumper" @current-change="current_change"
            :total="total" @size-change="handelSizeChange" :page-size="pagesize" :page-sizes="[5,10,15,20]">
        </el-pagination>
        <el-button class="Button" type="primary" @click="Delete_message" icon="el-icon-delete">删除角色</el-button>
        <el-button class="Button1" type="primary" @click="addRole" icon="el-icon-circle-plus-outline">新增角色</el-button>
        <el-dialog :visible.sync="showdialog_one" width="35%" center>
            <el-form v-model="add" label-width="80px">
                <el-form-item label="角色名称">
                    <el-input v-model="add.name"></el-input>
                </el-form-item>
                <el-form-item label="角色描述">
                    <el-input v-model="add.describe"></el-input>
                </el-form-item>
                <!-- <el-form-item label="视图权限">
                    <div class="part">
                        <el-tree :data="data" show-checkbox ref="tree" node-key="id" @check="getCheckedKeys"></el-tree>
                    </div>
                </el-form-item> -->
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="Confirm_one" type="primary">确定</el-button>
                <el-button @click="Cancle_one" type="primary">取消</el-button>
            </span>
        </el-dialog>
        <el-dialog :visible.sync="showdialog_two" width="37%" center>
            <el-form v-model="update" label-width="80px">
                <el-form-item label="角色名称">
                    <el-input v-model="update.name"></el-input>
                </el-form-item>
                <el-form-item label="角色描述">
                    <el-input v-model="update.describe"></el-input>
                </el-form-item>
                <el-form-item label="视图权限">
                    <div class="part">
                        <el-tree :data="data" show-checkbox ref="tree" node-key="id" @check="getCheckedKeys">
                            <span slot-scope="{data}">
                                <span>
                                    <i :class="data.icon"></i>&nbsp;&nbsp;&nbsp;&nbsp;{{data.label}}
                                </span>
                            </span>
                        </el-tree>
                    </div>
                </el-form-item>
                <el-form-item label="管理用户">
                    <el-row>
                        <el-col :span="8">
                            <el-select class="college" v-model="update.college" placeholder="学院"
                                @click.native="show_college()" @change="change_college" style="width: 150px;"
                                :disabled="showCmd">
                                <el-option v-for="item in colleges" :key="item" :label="colleges[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8">
                            <el-select v-model="update.major" placeholder="专业" @click.native="change_college()"
                                @change="change_major" style="margin-left:40px;width:110px" :disabled="showCmd">
                                <el-option v-for="item in majors" :key="item" :label="majors[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8">
                            <el-select v-model="update.class" placeholder="班级" @click.native="change_major()"
                                @change="change_class" style="width:80px;margin-left:40px;" :disabled="showCmd">
                                <el-option v-for="item in classes" :key="item" :label="classes[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="Confirm_two" id="btn" type="primary" icon="el-icon-document">保存</el-button>
            </span>
        </el-dialog>

        <el-dialog :visible.sync="showdialog_three" width="1000px" center id="di">
            <div>
                <span class="title" style="left:350px;top:0px;">用户分配</span>
                <el-divider></el-divider>
            </div>
            <el-form label-width="40px" :model="user">
                <el-row>
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
            <el-table ref="multipleTable" :header-cell-style="{textAlign:'center'}" :cell-style="{textAlign:'center'}"
                tooltip-effect="dark" :row-key="getRowKey" stripe
                :data="check_stu.slice((currpage_stu-1)*pagesize_stu,currpage_stu*pagesize_stu)"
                @selection-change="handelSelectionChange_stu">
                <el-table-column :reserve-selection="true" type="selection" label="所有" width="60"></el-table-column>
                <el-table-column prop="userName" label="学号" width="225" sortable></el-table-column>
                <el-table-column prop="name" label="姓名" width="230" sortable></el-table-column>
                <el-table-column prop="college" label="学院" width="232" sortable></el-table-column>
            </el-table>
            <el-pagination class="pages" layout="total, sizes, prev, pager, next, jumper"
                @current-change="current_change_stu" :total="total_stu" @size-change="handelSizeChange_stu"
                :page-size="pagesize_stu" :page-sizes="[5,10,15,20]">
            </el-pagination>
            <span slot="footer" class="dialog-footer">
                <el-button @click="confirm_absolute" type="primary" style="margin-right:250px;">确定</el-button>
                <el-button @click="cancle_absolute" type="primary">取消</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {
        role_information
    } from '../../../api/apis';
    export default {
        data() {
            return {
                showCmd: false,
                role: [],
                currpage: 1,
                pagesize: 5,
                total: 0,
                add: {
                    name: '',
                    describe: ''
                },
                user: {
                    sno: ''
                },
                update: {
                    name: '',
                    describe: '',
                    college: '',
                    major: '',
                    class: ''
                },
                role_array: [1, 2, 3, 4, 5, 6, 7],
                colleges: [],
                majors: [],
                classes: [],
                role_delete: [],
                showdialog_one: false,
                showdialog_two: false,
                showdialog_three: false,
                check_stu: [],
                check_stu_delect: [], //存储选中的所需要进行删除的信息
                currpage_stu: 1,
                pagesize_stu: 5,
                total_stu: 0,
                role_update: '',
                assign_users: [],
                data: [{
                    id: 1,
                    label: '首页',
                    icon: 'el-icon-mobile'
                }, {
                    id: 2,
                    label: '学校主页',
                    icon: 'el-icon-mobile'
                }, {
                    id: 3,
                    label: '登录',
                    icon: 'el-icon-mobile'
                }, {
                    id: 4,
                    label: '个人中心',
                    icon: 'el-icon-mobile',
                    children: [{
                        id: 5,
                        label: '个人资料',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 6,
                        label: '修改密码',
                        icon: 'el-icon-mobile'
                    }]
                }, {
                    id: 7,
                    label: '退出登录',
                    icon: 'el-icon-mobile'
                }, {
                    id: 8,
                    label: '报到流程',
                    icon: 'el-icon-mobile',
                    children: [{
                        id: 9,
                        label: '信息确认',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 10,
                            label: '确定信息无误',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 11,
                            label: '修改信息',
                            icon: 'el-icon-mobile'
                        }]
                    }, {
                        id: 12,
                        label: '报到信息',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 13,
                            label: '提交报到信息',
                            icon: 'el-icon-mobile'
                        }]
                    }, {
                        id: 14,
                        label: '现场报到',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 15,
                        label: '事务处理',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 16,
                            label: '确认完成事务处理',
                            icon: 'el-icon-mobile'
                        }]
                    }, {
                        id: 17,
                        label: '学费支付',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 18,
                            label: '上传普通学生支付单据',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 19,
                            label: '上传绿色通道支付单据',
                            icon: 'el-icon-mobile'
                        }]
                    }]
                }, {
                    id: 20,
                    label: '状态确认',
                    icon: 'el-icon-mobile',
                    children: [{
                        id: 21,
                        label: '导出全部名单',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 22,
                        label: '导出已完成报到名单',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 23,
                        label: '导出未完成报到名单',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 24,
                        label: '学生状态',
                        icon: 'el-icon-mobile'
                    }, {
                        id: 25,
                        label: '学生信息',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 26,
                            label: '查询学生信息',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 27,
                            label: '查看学生详情',
                            icon: 'el-icon-mobile'
                        }]
                    }, {
                        id: 28,
                        label: '现场报到',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 29,
                            label: '查询未现场报到学生信息',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 30,
                            label: '确认学生现场报到',
                            icon: 'el-icon-mobile'
                        }]
                    }, {
                        id: 31,
                        label: '现场缴费',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 32,
                            label: '查询未现场缴费学生信息',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 33,
                            label: '确认学生现场缴费',
                            icon: 'el-icon-mobile'
                        }]
                    }]
                }, {
                    id: 34,
                    label: '高级管理',
                    icon: 'el-icon-mobile',
                    children: [{
                        id: 75,
                        label: '用户统计',
                        children: [{
                            id: 35,
                            label: '导出普通管理员名单',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 36,
                            label: '导出学生名单',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 37,
                            label: '导出已完成报到名单',
                            icon: 'el-icon-mobile'
                        }, {
                            id: 38,
                            label: '导出未完成报到名单',
                            icon: 'el-icon-mobile'
                        }, ]
                    }, {
                        id: 76,
                        label: '通知管理',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 39,
                            label: '学校通知',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 40,
                                label: '增加学校通知',
                                icon: 'el-icon-mobile',
                            }, {
                                id: 41,
                                label: '删除学校通知',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 42,
                                label: '修改学校通知',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 43,
                            label: '校园攻略',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 44,
                                label: '增加校园攻略',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 45,
                                label: '删除校园攻略',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 46,
                                label: '修改校园攻略',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 47,
                            label: '缴费须知',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 48,
                                label: '增加缴费须知',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 49,
                                label: '删除缴费须知',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 50,
                                label: '修改缴费须知',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 51,
                            label: '报到须知',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 52,
                                label: '增加报到须知',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 53,
                                label: '删除报到须知',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 54,
                                label: '修改报到须知',
                                icon: 'el-icon-mobile'
                            }]
                        }]
                    }, {
                        id: 55,
                        label: '权限管理',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 56,
                            label: '生成用户',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 57,
                                label: '导入用户',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 58,
                                label: 'excel导入用户',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 59,
                            label: '管理用户',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 60,
                                label: '查找用户信息',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 61,
                                label: '修改用户信息',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 62,
                                label: '删除用户信息',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 63,
                            label: '角色管理',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 64,
                                label: '增加角色',
                                icon: 'el-icon-mobile',
                            }, {
                                id: 65,
                                label: '删除角色',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 66,
                                label: '修改角色',
                                icon: 'el-icon-mobile'
                            }, {
                                id: 67,
                                label: '分配用户',
                                icon: 'el-icon-mobile'
                            }]
                        }]
                    }, {
                        id: 68,
                        label: '日志管理',
                        icon: 'el-icon-mobile',
                        children: [{
                            id: 69,
                            label: '用户日志',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 70,
                                label: '删除用户日志',
                                icon: 'el-icon-mobile'
                            }]
                        }, {
                            id: 71,
                            label: '系统日志',
                            icon: 'el-icon-mobile',
                            children: [{
                                id: 72,
                                label: '删除系统日志',
                                icon: 'el-icon-mobile'
                            }]
                        }]
                    }]
                }]
            }
        },
        mounted() {
            this.show();
            this.show_college();
        },
        methods: {
            //设置相关的表格多选框是否禁用
            selectHandle(row, index) {
                return !(row.roleId == 1 || row.roleId == 2 || row.roleId == 3);
            },
            //下拉框中读取学院信息
            show_college() {
                this.axios.get(this.$apiUrl +'/college/getcollege', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
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
                            console.log(this.colleges)
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            change_college() {
                if (this.update.college === '所有') {
                    //获取所对应的用户信息和相关的所有专业信息
                    this.axios.all([this.axios.get(this.$apiUrl +'/user/search', {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.get(this.$apiUrl +'/college/getmajor', {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2) => {
                            if (res1.code === 0 && res2.code === 0) {
                                this.assign_users = [];
                                for (let i = 0; i < res1.data.length; i++) {
                                    this.assign_users.push({
                                        id: res1.data[i].userId,
                                        roleId: this.role_update
                                    })
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
                    let param = new URLSearchParams();
                    param.append('college', this.update.college);
                    //通过学院查找所对应的用户信息和该学院下相关的专业
                    this.axios.all([this.axios.post(this.$apiUrl +'/users/findbycollege', param, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.post(this.$apiUrl +'/college/getmajorbycollege', param, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2) => {
                            if (res1.code === 0 && res2.code === 0) {
                                this.assign_users = [];
                                for (let i = 0; i < res1.data.length; i++) {
                                    this.assign_users.push({
                                        id: res1.data[i].userId,
                                        roleId: this.role_update
                                    })
                                }
                                console.log('this.assign_users:', this.assign_users);
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
            change_major() {
                if (this.update.major === '所有') {
                    this.classes = [];
                    this.classes.push('专业信息不明确');
                    this.change_college();
                } else {
                    let param = new URLSearchParams();
                    param.append('major', this.update.major);
                    this.axios.post(this.$apiUrl +'/users/findbymajor', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    }).then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '班级信息获取失败'
                            })
                        } else {
                            this.assign_users = [];
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.assign_users.push({
                                    id: res.data.data[i].userId,
                                    roleId: this.role_update
                                })
                            }
                        }
                    }).catch(err => {
                        console.length(err);
                    })
                    this.axios.post(this.$apiUrl +'/college/getclassesbymajor', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    }).then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '班级信息获取失败'
                            })
                        } else {
                            this.classes = [];
                            this.classes.push('所有');
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.classes.push(res.data.data[i].classes);
                            }
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }
            },
            change_class() {
                if (this.update.class === '所有') {
                    this.change_major();
                } else {
                    let param = new URLSearchParams();
                    param.append('major', this.update.major);
                    param.append('classes', this.update.class);
                    this.axios.post(this.$apiUrl +'/users/getstudentbyclasses', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    }).then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '用户信息获取失败'
                            })
                        } else {
                            this.assign_users = [];
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.assign_users.push({
                                    id: res.data.data[i].userId,
                                    roleId: this.role_update
                                })
                            }
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }
            },
            found_sno_stu() {
                if (this.user.sno === '') {
                    this.axios.get(this.$apiUrl +'/user/search', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    }).then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '用户信息获取失败'
                            })
                        } else {
                            this.check_stu = res.data.data;
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                } else {
                    let param = new URLSearchParams();
                    param.append('username', this.user.sno);
                    this.axios.post(this.$apiUrl +'/users/getuserbyusername', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    }).then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '用户信息获取失败'
                            })
                        } else {
                            this.check_stu = [];
                            this.check_stu.push({
                                userName: res.data.data.userName,
                                name: res.data.data.name,
                                college: res.data.data.college,
                                userId: res.data.data.userId
                            })
                            this.total_stu = 1;
                            this.currpage_stu = 1;
                            console.log(this.check_stu)
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }
            },
            getRowKey(row) {
                return row.userId;
            },
            getRowKey_one(row) {
                return row.roleId;
            },
            //获取相关的角色信息
            show() {
                role_information('', 'get', null, null,{
            'Authorization': localStorage.getItem('token' + this.$store.state.id)
        })
                .then(res => {
                        console.log(res.data);
                        // if(res.data.code==0){
                        this.role = res.data.records;
                        this.total = this.role.length;
                        // }
                    }).catch(err => {
                        console.log(err);
                    })
            },
            deTails_divide(row) {
                this.showdialog_three = true;
                this.user.sno = '';
                this.role_update = row.roleId;
                let url = this.$apiUrl +'/user/search';
                let url1 = this.$apiUrl +'/sysRoleTable/roleUser/' + this.role_update;
                this.axios.all([this.axios.get(url, {
                            headers: {
                                'Authorization': localStorage.getItem('token' + this.$store.state.id)
                            }
                        }).then(res => res.data),
                        this.axios.get(url1, {
                            headers: {
                                'Authorization': localStorage.getItem('token' + this.$store.state.id)
                            }
                        }).then(res => res.data)
                    ])
                    .then(this.axios.spread((res1, res2) => {
                        console.log(res2)
                        if (res1.code === 0 && res2.code === 0) {
                            this.check_stu = res1.data;
                            this.total_stu = res1.data.length;
                            var array = [];
                            for (let i = 0; i < res2.data.length; i++) {
                                if (res2.data[i] != null) {
                                    //使用array存放属于该角色的用户
                                    if (Object.keys(res2.data[i]).length > 2) {
                                        array.push({
                                            id: res2.data[i].id,
                                            userName: res2.data[i].name,
                                            college: res2.data[i].college
                                        })
                                    }
                                }
                            }
                            this.$refs.multipleTable.clearSelection();
                            console.log(this.role_update, ':', array);
                            array.forEach((item) => {
                                this.check_stu.forEach((items) => {
                                    if (item.id === items.userId) {
                                        this.$nextTick(function () {
                                            this.$refs.multipleTable.toggleRowSelection(
                                                items, true);
                                        })
                                    }
                                })
                            })
                            // this.$refs.multipleTable.toggleRowSelection(array);
                        } else {
                            this.$message({
                                type: 'error',
                                message: '用户信息读取失败'
                            })
                        }
                    })).catch(err => {
                        console.log(err);
                    })
            },
            getCheckedKeys() {
                console.log("权限为：" + this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys()));
            },
            setCheckedKeys(val) {
                this.$refs.tree.setCheckedKeys(val);
            },
            handelSelectionChange(val) {
                this.role_delete = val;
            },
            getRowClass() {
                return "background-color:transparent"
            },

            deTails(row) {
                if (row.roleId == 1) {
                    this.update.college = '所有';
                    this.update.major = '所有';
                    this.update.class = '所有';
                    this.showCmd = true;
                } else if (row.roleId == 2 || row.roleId == 3) {
                    this.update.college = '';
                    this.update.major = '';
                    this.update.class = '';
                    this.showCmd = true;
                } else {
                    this.showCmd = false;
                }
                this.update.name = row.roleName;
                this.update.describe = row.description;
                this.role_update = row.roleId;
                var array = [];
                let url = this.$apiUrl +'/sysRoleTable/roleMenu/' + this.role_update;
                let url1 = this.$apiUrl +'/sysRoleTable/getcollegemc';
                let param = new URLSearchParams();
                param.append('roleId', this.role_update);
                this.axios.all([this.axios.get(url, {
                            headers: {
                                'Authorization': localStorage.getItem('token' + this.$store.state.id)
                            }
                        }).then(res => res.data),
                        this.axios.post(url1, param, {
                            headers: {
                                'Authorization': localStorage.getItem('token' + this.$store.state.id)
                            }
                        }).then(res => res.data)
                    ])
                    .then(this.axios.spread((res1, res2) => {
                        if (res1.code === 0 && res2.code === 0) {
                            if (res2.data != null) {
                                this.update.college = res2.data.college;
                                this.update.major = res2.data.major;
                                this.update.class = res2.data.class;
                            }
                            for (let i = 0; i < res1.data.length; i++) {
                                if (Object.keys(res1.data[i]).length > 2) {
                                    array.push(parseInt(res1.data[i].id));
                                }
                            }
                            //在dom更新之后进行相关的回调
                            console.log('array=', array);
                            this.$nextTick(function () {
                                // this.setCheckedKeys(array);
                                let checkTreeNode = [];
                                for (let item of array) {
                                    let node = this.$refs.tree.getNode(item);
                                    if (node && node.isLeaf) {
                                        checkTreeNode.push(item);
                                    }
                                }
                                this.$refs.tree.setCheckedKeys(checkTreeNode);
                            })
                        } else {
                            this.$message({
                                type: 'error',
                                message: '信息获取失败'
                            })
                        }
                    })).catch(err => {
                        console.log(err);
                    })
                this.showdialog_two = true;

            },
            current_change(currpage) {
                this.currpage = currpage;
            },
            handelSizeChange(val) {
                this.pagesize = val;
            },
            Back() {
                this.$router.push('/smanager/userm')
            },
            Delete_message() {
                var array = [];
                for (var i in this.role_delete) {
                    array.push(this.role_delete[i].roleId)
                }
                this.$confirm('确定删除该条记录？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    if (this.role_delete.length === 0) {
                        this.$message({
                            type: 'info',
                            message: '请先选中选项'
                        })
                    } else {
                        console.log(array);
                        let param = new URLSearchParams();
                        param.append('idList', array);
                        role_information('', 'delete', null, param,{
            'Authorization': localStorage.getItem('token' + this.$store.state.id)
        })
                            .then(res => {
                                this.total -= this.role_delete.length;
                                if(this.total%this.pagesize === 0){
                                    this.currpage--;
                                }
                                this.$refs.multipleTable.clearSelection();
                                this.show();
                                this.$message({
                                    type: 'success',
                                    message: '成功删除角色信息'
                                })
                            })
                            .catch(err => {
                                console.log(err);
                            })
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
            addRole() {
                this.showdialog_one = true;
            },
            Confirm_one() {
                this.$confirm('确定添加该条记录？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.showdialog_one = false;
                    var obj = {
                        roleName: this.add.name,
                        description: this.add.describe
                    }
                    this.axios.post(this.$apiUrl +'/sysRoleTable', obj, {
                            headers: {
                                'Authorization': localStorage.getItem('token' + this.$store.state.id),
                            }
                        })
                        .then(res => {
                            console.log(res.data.msg);
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '信息添加失败'
                                })
                            } else {
                                this.$message({
                                    type: 'success',
                                    message: '添加成功！'
                                })
                                this.role.push({
                                    roleName: this.add.name,
                                    description: this.add.describe
                                })
                                this.total++;
                                this.add.name = '';
                                this.add.describe = '';
                                this.show();
                            }
                        })
                        .catch(err => {
                            console.log(err);
                        })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消添加'
                    })
                })
                // this.set_role(this.)
            },
            //取消新增数据操作
            Cancle_one() {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
                this.showdialog_one = false
            },
            Confirm_two() {
                this.$confirm('确定修改该角色的相关内容', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.showdialog_two = false;
                    let role_data = [];
                    var array1 = [];
                    for (let i = 0; i < this.$refs.tree.getCheckedKeys().length; i++) {
                        array1.push(this.$refs.tree.getCheckedKeys()[i]);
                    }
                    for (let i = 0; i < this.$refs.tree.getHalfCheckedKeys().length; i++) {
                        array1.push(this.$refs.tree.getHalfCheckedKeys()[i]);
                    }
                    for (let i = 0; i < array1.length; i++) {
                        role_data.push({
                            roleId: this.role_update,
                            id: array1[i],
                        })
                    }
                    let obj = {
                        roleId: this.role_update,
                        roleName: this.update.name,
                        description: this.update.describe
                    }
                    let obj1 = {
                        roleId: this.role_update,
                        college: this.update.college,
                        major: this.update.major,
                        classes: this.update.class
                    }
                    console.log('assign_users=', this.assign_users);
                    let url = this.$apiUrl +'/sysRoleTable/roleMenu/set' + '?roleId=' + this.role_update;
                    let url1 =this.$apiUrl + '/managerstudent/roleUser/set' + '?roleId=' + this.role_update;
                    let url2 = this.$apiUrl +'/sysRoleTable/updatecollegemc';
                    this.axios.all([this.axios.post(url, role_data, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.put(this.$apiUrl +'/sysRoleTable', obj, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.post(url1, this.assign_users, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data),
                            this.axios.post(url2, obj1, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state.id)
                                }
                            }).then(res => res.data)
                        ])
                        .then(this.axios.spread((res1, res2, res3, res4) => {
                            if (res1.code === 0 && res2.code === 0 && res3.code === 0 && res4.code ===
                                0) {
                                this.show();
                                this.$message({
                                    type: 'success',
                                    message: '角色信息修改成功'
                                })
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: '角色信息修改失败'
                                })
                            }
                        }))

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消分配'
                    })
                })

            },
            showChange_one(val) {
                console.log(val);
            },
            showChange_two(val) {
                console.log(val);
                this.update.idcard = val;
            },
            confirm_absolute() {
                this.$confirm('确定给这些用户分配该角色？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.user.sno = '';
                    this.showdialog_three = false;
                    let param = new URLSearchParams();
                    param.append("roleId", this.role_update);
                    let user_message = [];
                    console.log('this.role_updata=', this.role_update)
                    for (let i = 0; i < this.check_stu_delect.length; i++) {
                        //当前的userId未获取到
                        user_message.push({
                            id: this.check_stu_delect[i].userId,
                            roleId: this.role_update
                        })
                    }
                    console.log('world=', user_message);
                    let url = this.$apiUrl +'/sysRoleTable/roleUser/set' + '?roleId=' + this.role_update;
                    this.axios.post(url, user_message, {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id),
                        }
                    }).then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '用户分配失败'
                            })
                        } else {
                            this.$message({
                                type: 'success',
                                message: '用户分配成功'
                            })
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消分配'
                    })
                })
            },
            cancle_absolute() {
                this.showdialog_three = false;
            },
            handelSelectionChange_stu(val) {
                this.check_stu_delect = val;
            },
            current_change_stu(currpage_stu) {
                this.currpage_stu = currpage_stu;
            },
            handelSizeChange_stu(val) {
                this.pagesize_stu = val;
            },
        }
    }
</script>

<style lang="less" scoped>
    @import '../../../less/layout.css';
    // @import '../../../less/table.less';

    .el-table {
        margin-top: 10px;
        width: 78.7%;
        margin-left: auto;
        margin-right: auto;
    }

    /deep/ .el-table__header tr,
    /deep/ .el-table__header th {
        padding: 0;
        height: 50px;
        // background-color: #FAFAFA;
    }

    .pages {
        position: relative;
        margin-left: 10%;
        margin-top: 2%;
        margin-bottom: 2%;
        padding: 0%;
    }

    .Button {
        position: relative;
        left: 15%;
        margin-top: 2%;
        margin-bottom: 2%;
        padding: 7px;
        font-size: 12px;
        background-color: #409eff;
        opacity: 0.9;
    }

    /deep/ .el-dialog {
        background-color: #F4F8F6;
    }

    .Button1 {
        position: relative;
        left: 75%;
        margin-top: 2%;
        margin-bottom: 2%;
        padding: 7px;
        font-size: 12px;
        background-color: #409eff;
        opacity: 0.9;
    }

    .part {
        border: 1px solid gainsboro;
        width: 100%;
    }

    /deep/ .el-dialog__footer {
        margin-top: -40px;
    }

    .el-select {
        margin-right: 4px;

    }

    /deep/ .el-input__inner {
        height: 30px;
    }

    /deep/ .el-button [class*=el-icon-]+span {
        margin-left: 2px;
    }
</style>