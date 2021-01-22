<template>
    <div class="whole">
        <span class="title">生成用户</span>
        <el-divider></el-divider>
        <span style="margin-left:12px;margin-right:12px;">用户身份</span>
        <el-radio-group v-model="role" @change="showChange(role)">
            <el-radio label="学生"></el-radio>
            <el-radio label="普通管理员"></el-radio>
        </el-radio-group>
        <br>
        <br>
        <div v-if="buildUser">
            <el-form ref="form" :model="stu" label-width="100px">
                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="学号">
                            <el-input v-model="stu.sno" class="Input"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="姓名">
                            <el-input v-model="stu.name" class="Input"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="性别">
                            <el-select v-model="stu.sex">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>


                </el-row>

                <el-row type="flex" justify="center">


                    <el-col :span="8">
                        <el-form-item label="民族">
                            <el-select v-model="stu.nation" class="Input">
                                <el-option v-for="item in nations" :key="item" :label="nations[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="学院">
                            <el-select v-model="stu.college" class="Input" @click.native="show_colleges()">
                                <el-option v-for="item in colleges" :key="item" :label="colleges[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="专业">
                            <el-select v-model="stu.major" class="Input" @click.native="change_college()">
                                <el-option v-for="item in majors" :key="item" :label="majors[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">


                    <el-col :span="8">
                        <el-form-item label="年级">
                            <el-select v-model="stu.grade" class="Input">
                                <el-option v-for="item in grades" :key="item" :label="grades[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="班级">
                            <el-select v-model="stu.class" class="Input" @click.native="change_major()">
                                <el-option v-for="item in classes" :key="item" :label="classes[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="宿舍">
                            <div style="display:flex;background-color:white;width:204px">
                                <el-select v-model="stu.dormitory" style="width:102px" @change="handleChange1">
                                    <el-option v-for="item in dromm" :key="item" :label="dromm[item]" :value="item">
                                    </el-option>
                                </el-select>
                                <el-select v-model="stu.tung" style="width:102px" @change="handleChange3">
                                    <el-option v-for="item in tungs" :key="item" :label="tungs[item]" :value="item">
                                    </el-option>
                                </el-select>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">


                    <el-col :span="8">
                        <el-form-item label="学制">
                            <el-select v-model="stu.year" class="Input">
                                <el-option label="3年" value="3年"></el-option>
                                <el-option label="4年" value="4年"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="应届生">
                            <el-select v-model="stu.session" class="Input">
                                <el-option label="应届生" value="应届生"></el-option>
                                <el-option label="往届生" value="往届生"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="身份证号">
                            <el-input v-model="stu.idcard" class="Input"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="出生日期">
                            <el-date-picker type="date" placeholder="选择日期" v-model="stu.birthday"
                                value-format="yyyy-MM-dd" class="Input">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="学习形式">
                            <el-select v-model="stu.study" class="Input">
                                <el-option v-for="item in studys" :key="item" :label="studys[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="政治面貌">
                            <el-select v-model="stu.politics" class="Input">
                                <el-option v-for="item in politicss" :key="item" :label="politicss[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="学籍状态">
                            <el-select v-model="stu.status" class="Input">
                                <el-option v-for="item in statuss" :key="item" :label="statuss[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="辅导员姓名">
                            <el-input v-model="stu.adname" class="Input"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="辅导员电话">
                            <el-input v-model="stu.adphone" class="Input"></el-input>
                        </el-form-item>
                    </el-col>

                </el-row>
            </el-form>
            <el-upload accept=".xls, .xlsx" action="" :auto-upload="false" :show-file-list="false" :on-change="Export">
                <span class="export">excel导入大量用户>></span>
            </el-upload>
            <el-button type="primary" class="Button" @click="Import_user" icon="el-icon-copy-document">导入用户</el-button>
        </div>
        <div v-if="buildAdmin">
            <el-form ref="form" :model="admin" label-width="100px" label-position="right">
                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="工号">
                            <el-input v-model="admin.sno"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="姓名">
                            <el-input v-model="admin.name"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="性别">
                            <el-select v-model="admin.sex">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>


                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="学院">
                            <el-select v-model="admin.college">
                                <el-option v-for="item in colleges" :key="item" :label="colleges[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="民族">
                            <el-select v-model="admin.nation">
                                <el-option v-for="item in nations" :key="item" :label="nations[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="电话">
                            <el-input v-model="admin.phone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item label="身份证号">
                            <el-input v-model="admin.idcard"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="出生日期">
                            <el-date-picker type="date" placeholder="选择日期" v-model="admin.birthday"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="政治面貌">
                            <el-select v-model="admin.politics">
                                <el-option v-for="item in politicss" :key="item" :label="politicss[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <el-upload accept=".xls, .xlsx" action="" :auto-upload="false" :show-file-list="false" :on-change="Export">
                <span class="export">excel导入大量用户>></span>
            </el-upload>
            <el-button type="primary" class="Button" @click="Import_admin" icon="el-icon-copy-document">导入用户</el-button>
        </div>
    </div>
</template>

<script>
    import xlsx from 'xlsx'
    export default {
        data() {
            return {
                stu: {
                    name: '',
                    sno: '',
                    idcard: '',
                    sex: '',
                    birthday: '',
                    nation: '',
                    college: '',
                    major: '',
                    grade: '',
                    class: '',
                    dormitory: '',
                    tung: '',
                    year: '',
                    session: '',
                    study: '',
                    politics: '',
                    status: '',
                    adname: '',
                    adphone: '',
                },
                admin: {
                    name: '',
                    sno: '',
                    idcard: '',
                    sex: '',
                    birthday: '',
                    nation: '',
                    college: '',
                    phone: '',
                    politics: '',
                },
                nations: ['汉族', '藏族', '维吾尔族', '苗族', '回族'],
                grades: [2017, 2018, 2019, 2020],
                classes: [],
                tungs: ['1栋', '2栋', '3栋', '4栋', '5栋'],
                studys: ['全日制招生', '成人高考', '远程教育', '自考', '国家开发银行'],
                politicss: ['共青团员', '群众', '党员'],
                statuss: ['注册学籍', '保留学籍', '已离校'],
                colleges: [],
                majors: [],
                buildUser: false,
                buildAdmin: false,
                role: '',
                tableData: [],
                tableHeader: [],
                dromm: [],
            }
        },
        mounted() {
            this.show_dromm();
            this.show_colleges();
            this.role = "学生";
            this.buildUser = true;
            this.buildAdmin = false;
        },
        methods: {
            //验证输入的身份证号是否合法
            isIdCardNo(num) {
                // num = num.toUpperCase(); //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。       
                if (!(/(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/.test(num))) {
                    //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');             
                    // alert('身份证号长度不正确或不符合规定！');
                    return false;
                }
                //验证前2位，城市符合
                var aCity = {
                    11: "北京",
                    12: "天津",
                    13: "河北",
                    14: "山西",
                    15: "内蒙古",
                    21: "辽宁",
                    22: "吉林",
                    23: "黑龙江 ",
                    31: "上海",
                    32: "江苏",
                    33: "浙江",
                    34: "安徽",
                    35: "福建",
                    36: "江西",
                    37: "山东",
                    41: "河南",
                    42: "湖北",
                    43: "湖南",
                    44: "广东",
                    45: "广西",
                    46: "海南",
                    50: "重庆",
                    51: "四川",
                    52: "贵州",
                    53: "云南",
                    54: "西藏",
                    61: "陕西",
                    62: "甘肃",
                    63: "青海",
                    64: "宁夏",
                    65: "新疆",
                    71: "台湾",
                    81: "香港",
                    82: "澳门",
                    91: "国外"
                };
                if (aCity[parseInt(num.substr(0, 2))] == null) {
                    // alert('身份证号不正确或不符合规定！');
                    return false;
                }
                //alert('城市:'+aCity[parseInt(num.substr(0,2))]);

                //下面分别分析出生日期和校验位
                var len, re;
                len = num.length;

                if (len == 15) {
                    let arrSplit, dtmBirth, bGoodDay, arrInt, arrCh, nTemp, valnum;
                    re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
                    arrSplit = num.match(re); //检查生日日期是否正确
                    dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
                    bGoodDay;
                    bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(
                        arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                    if (!bGoodDay) {
                        // alert('身份证号的出生日期不对！');
                        return false;
                    } else { //将15位身份证转成18位 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。       
                        arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                        arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                        nTemp = 0,
                            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                        for (let i = 0; i < 17; i++) {
                            nTemp += num.substr(i, 1) * arrInt[i];
                        }
                        num += arrCh[nTemp % 11];
                        return true;
                    }
                }
                if (len == 18) {
                    let arrSplit, dtmBirth, bGoodDay, arrInt, arrCh, nTemp, valnum;
                    re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
                    arrSplit = num.match(re); //检查生日日期是否正确
                    dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
                    bGoodDay;
                    bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(
                        arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                    if (!bGoodDay) {
                        //alert(dtmBirth.getYear());
                        //alert(arrSplit[2]);
                        // alert('身份证号的出生日期不对！');
                        return false;
                    } else { //检验18位身份证的校验码是否正确。 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                        valnum;
                        arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                        arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                        nTemp = 0;
                        for (let i = 0; i < 17; i++) {
                            nTemp += num.substr(i, 1) * arrInt[i];
                        }
                        valnum = arrCh[nTemp % 11];
                        if (valnum != num.substr(17, 1)) {
                            //alert('18位身份证的校验码不正确！应该为：' + valnum);
                            // alert('18位身份证号的校验码不正确！');
                            return false;
                        }
                        return true;
                    }
                }
                return false;

            },
            //获取宿舍相关的栋数
            show_dromm() {
                this.axios.get(this.$apiUrl +"/dorm/getapartments", {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                        }
                    }).then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: "error",
                                message: "获取宿舍信息失败"
                            });
                        } else {
                            this.dromm = res.data.data;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
            },
            //获取该宿舍相关的号数
            handleChange1(val) {
                let param = new URLSearchParams()
                param.append("apartments", val);
                this.axios.post(this.$apiUrl +"/dorm/getdorm", param, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                        }
                    }).then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: "error",
                                message: "获取学院信息失败"
                            });
                        } else {
                            this.stu.dormitory = val;
                            this.tungs = res.data.data;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
            },
            handleChange3(val) {
                // this.stu.tung = this.stu.drom + val;
                // console.log(this.stu.drom)
            },
            showChange(val) {
                if (val === "学生") {
                    this.buildUser = true;
                    this.buildAdmin = false;
                } else {
                    this.buildUser = false;
                    this.buildAdmin = true;
                }
            },
            //下拉框显示所有学院
            show_colleges() {
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
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.colleges.push(res.data.data[i].college);
                            }
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //获取相关学院的专业信息
            change_college() {
                let param = new URLSearchParams();
                console.log(this.stu.college);
                param.append("college", this.stu.college);
                this.axios.post(this.$apiUrl +'/college/getmajorbycollege', param, {
                        headers: {
                            'Authorization': localStorage.getItem('token'+ this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '请先选择学院信息'
                            })
                        } else {
                            this.majors = [];
                            for (let i = 0; i < res.data.data.length; i++) {
                                this.majors.push(res.data.data[i].major);
                            }
                        }

                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            change_major() {
                let param = new URLSearchParams();
                param.append('major', this.stu.major);
                this.axios.post(this.$apiUrl +'/college/getclassesbymajor', param, {
                    headers: {
                        'Authorization': localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => {
                    if (res.data.code != 0) {
                        this.$message({
                            type: 'error',
                            message: '请先选择专业信息'
                        })
                    } else {
                        this.classes = [];
                        for (let i = 0; i < res.data.data.length; i++) {
                            this.classes.push(res.data.data[i].classes);
                        }
                    }
                }).catch(err => {
                    console.log(err);
                })
            },
            Import_user() {
                var flag1 = 1;
                // var re = /^[0-9]+.?[0-9]*/;
                // if (!re.test(this.stu.sno)) {
                //     flag1 = 0;
                // } else {
                //     if (this.stu.sno.length != 10) {
                //         flag1 = 0;
                //     }
                // }
                // if (!this.isIdCardNo(this.stu.idcard)) {
                //     flag1 = 0;
                // }
                var obj = [];
                obj.push({
                    studystate: this.stu.status,
                    userName: this.stu.sno,
                    college: this.stu.college,
                    idnumber: this.stu.idcard,
                    classes: this.stu.class,
                    birth: this.stu.birthday,
                    instructname: this.stu.adname,
                    instructphone: this.stu.adphone,
                    name: this.stu.name,
                    sex: this.stu.sex,
                    nation: this.stu.nation,
                    major: this.stu.major,
                    grade: this.stu.grade,
                    drom: this.stu.dormitory + this.stu.tung,
                    studenttype: this.stu.session,
                    politics: this.stu.politics,
                    studytype: this.stu.study,
                    xuezhi: this.stu.year
                })
                if (this.stu.sno === '' || this.stu.idcard === '') {
                    this.$message({
                        type: 'error',
                        message: '学号或身份证号不能为空！'
                    })
                } else {
                    if (flag1 === 0) {
                        this.$message({
                            type: 'error',
                            message: '学号或身份证号输入格式错误！'
                        })
                    } else {
                        this.$confirm('确定导入该用户信息？', '提示', {
                                confirmButtonText: '确定',
                                cancleButtonText: '取消',
                                type: 'warning',
                            }).then(() => {
                                this.axios.post(this.$apiUrl +'/users/addstudent', obj, {
                                        headers: {
                                            'Authorization': localStorage.getItem('token' + this.$store.state
                                                .id)
                                        }
                                    })
                                    .then(res => {
                                        if (res.data.code != 0) {
                                            this.$message({
                                                type: 'error',
                                                message: '导入用户名单失败'
                                            })
                                        } else {
                                            this.$message({
                                                type: 'success',
                                                message: '导入用户名单成功',
                                            })
                                            this.stu = {};
                                        }
                                    })
                                    .catch(err => {
                                        console.log(err);
                                    })
                            })
                            .catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消导入用户名单'
                                })
                            })
                    }
                }
            },
            Import_admin() {
                var flag1 = 1;
                // var re = /^[0-9]+.?[0-9]*/;
                // if (!re.test(this.admin.sno)) {
                //     flag1 = 0;
                // } else {
                //     if (this.admin.sno.length != 10) {
                //         flag1 = 0;
                //     }
                // }
                // if (!this.isIdCardNo(this.admin.idcard)) {
                //     flag1 = 0;
                // }
                var obj = {
                    mworknumber: this.admin.sno,
                    mnation: this.admin.nation,
                    mdate: this.admin.birthday,
                    msex: this.admin.sex,
                    midnumber: this.admin.idcard,
                    mphonenumber: this.admin.phone,
                    mname: this.admin.name,
                    mpolitics: this.admin.politics,
                    college: this.admin.college
                }
                if (this.admin.sno === '' && this.admin.idcard === '') {
                    this.$message({
                        type: 'error',
                        message: '工号或身份证号不能为空！'
                    })
                } else {
                    if (flag1 === 0) {
                        this.$message({
                            type: 'error',
                            message: '工号或身份证号输入格式错误！'
                        })
                    } else {
                        this.$confirm('确定导入该管理员信息？', '提示', {
                                confirmButtonText: '确定',
                                cancleButtonText: '取消',
                                type: 'warning',
                            }).then(() => {
                                let array = [];
                                array.push({
                                    userName: this.admin.sno,
                                    name: this.admin.name,
                                    phonenumber: this.admin.phone,
                                    idnumber: this.admin.idcard,
                                    sex: this.admin.sex,
                                    college: this.admin.college,
                                    nation: this.admin.nation,
                                    birth: this.admin.birthday,
                                    politics: this.admin.politics
                                })
                                console.log(array);
                                this.axios.post(this.$apiUrl +'/users/addmanager', array, {
                                    headers: {
                                        'Authorization': localStorage.getItem('token' + this.$store.state
                                            .id)
                                    }
                                }).then(res => {
                                    if (res.data.code != 0) {
                                        this.$message({
                                            type: 'error',
                                            message: '用户信息导入失败'
                                        })
                                    } else {
                                        this.$message({
                                            type: 'success',
                                            message: '导入成功'
                                        })
                                        this.admin = {};
                                    }
                                }).catch(err => {
                                    console.log(err);
                                })
                            })
                            .catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消导入管理员名单'
                                })
                            })
                    }
                }
            },
            user_manager() {
                this.$router.push('/smanager/userm');
            },
            async Export(file, fileList) {
                var data = file.raw;
                const excel = await this.readFile(data);
                // 使用xlsx读取二进制数据 生成excel的全部信息
                var workbook = xlsx.read(excel, {
                    type: "binary"
                });
                // 通过xlsx转换为可读的表格数据 
                //workbook.Sheets[workbook.SheetNames[0]] 获取当前上传的表格的信息,例如总共有几行几列啥的
                data = xlsx.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]);
                const header = this.getHeaderRow(workbook.Sheets[workbook.SheetNames[0]]);
                this.tableData = data;
                this.tableHeader = header;
                this.$confirm('确定导入该用户信息？', '提示', {
                        confirmButtonText: '确定',
                        cancleButtonText: '取消',
                        type: 'warning',
                    }).then(() => {
                        let array = [];
                        if (this.tableHeader.length > 9) {
                            for (let i = 0; i < this.tableData.length; i++) {
                                array.push({
                                    userName: this.tableData[i].学号,
                                    name: this.tableData[i].姓名,
                                    phonenumber: this.tableData[i].电话,
                                    idnumber: this.tableData[i].身份证号,
                                    sex: this.tableData[i].性别,
                                    college: this.tableData[i].学院,
                                    nation: this.tableData[i].民族,
                                    birth: this.tableData[i].出生日期,
                                    politics: this.tableData[i].政治面貌,
                                    major: this.tableData[i].专业,
                                    grade: this.tableData[i].年级,
                                    classes: this.tableData[i].班级,
                                    drom: this.tableData[i].宿舍,
                                    xuezhi: this.tableData[i].学制,
                                    studenttype: this.tableData[i].学习形式,
                                    studystate: this.tableData[i].学籍状态,
                                    instructname: this.tableData[i].辅导员姓名,
                                    instructphone: this.tableData[i].辅导员电话
                                })
                            }
                            this.axios.post(this.$apiUrl +'/users/addstudent', array, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state
                                        .id)
                                }
                            }).then(res => {
                                if (res.data.code != 0) {
                                    this.$message({
                                        type: 'error',
                                        message: '用户信息导入失败'
                                    })
                                } else {
                                    this.$message({
                                        type: 'success',
                                        message: res.data.data
                                    })
                                }
                            }).catch(err => {
                                console.log(err);
                            })
                        } else {
                            for (let i = 0; i < this.tableData.length; i++) {
                                array.push({
                                    userName: this.tableData[i].工号,
                                    name: this.tableData[i].姓名,
                                    phonenumber: this.tableData[i].电话,
                                    idnumber: this.tableData[i].身份证号,
                                    sex: this.tableData[i].性别,
                                    college: this.tableData[i].学院,
                                    nation: this.tableData[i].民族,
                                    birth: this.tableData[i].出生日期,
                                    politics: this.tableData[i].政治面貌
                                })
                            }
                            this.axios.post(this.$apiUrl +'/users/addmanager', array, {
                                headers: {
                                    'Authorization': localStorage.getItem('token' + this.$store.state
                                        .id)
                                }
                            }).then(res => {
                                if (res.data.code != 0) {
                                    this.$message({
                                        type: 'error',
                                        message: '用户信息导入失败'
                                    })
                                } else {
                                    this.$message({
                                        type: 'success',
                                        message: res.data.data
                                    })
                                }
                            }).catch(err => {
                                console.log(err);
                            })
                        }
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消导入用户名单'
                        })
                    })
            },
            // 读取上传的文件
            readFile(file) {
                return new Promise((resolve, reject) => {
                    var reader = new FileReader();
                    // 把文件转为二进制
                    reader.readAsBinaryString(file);
                    reader.onload = e => {
                        // console.log(e.target.result);
                        resolve(e.target.result);
                    };
                });
            },
            // 获取表头信息
            getHeaderRow(sheet) {
                const headers = [];
                //获取总共的行数和列数
                const range = xlsx.utils.decode_range(sheet["!ref"]);
                let C;
                const R = range.s.r; // 开始行
                //遍历每列
                for (C = range.s.c; C <= range.e.c; ++C) {
                    // 找到每列的第一行数据即使表头
                    const cell = sheet[xlsx.utils.encode_cell({
                        c: C,
                        r: R
                    })];
                    let hdr = "UNKNOWN " + C;
                    if (cell && cell.t) hdr = xlsx.utils.format_cell(cell);
                    headers.push(hdr);
                }
                return headers;
            }
        }
    }
</script>
<style lang="less" scoped>
    @import '../../../less/layout.css';

    .whole {
        // background-color: #E5FFF2;
        padding-left: 10px;
    }

    .Button {
        position: relative;
        margin-left: 890px;
        line-height: -8px;
        margin-bottom: 2%;
        height: 30px;
        background-color: #409eff;
        opacity: 0.9;
    }

    .export {
        position: relative;
        // margin-left: 5%;
        top: 25px;
        color: blue;
        cursor: pointer;
    }

    .back {
        position: relative;
        margin-left: 35%;
        top: 35px;
        color: blue;
        cursor: pointer;
    }

    /deep/ .el-row {
        padding-right: 2%;
        height: 1%;
    }

    /deep/ .el-form-item {
        height: 40px;
        width: 120px;
        background-color: #E5FFF2;
        border: 1px solid gainsboro;
        line-height: 40px;
        margin-bottom: 0px;
    }

    .el-form-item /deep/ .el-form-item__label {
        padding-left: 10px;
        color: black;
        text-align: left;
    }

    .title {
        background-color: transparent;
        border: none;
    }

    .el-input,
    .el-select {
        display: inline-block;
        height: 40px;
        width: 204px;
        border: 1px solid gainsboro;
        background-color: white;
        margin-top: -1px;
    }

    /deep/ .el-button [class*=el-icon-]+span {
        margin-left: 2px;
    }
</style>