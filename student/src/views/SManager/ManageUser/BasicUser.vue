<template>
    <div class="whole">
        <!-- <span class="title">学生用户管理</span> -->
        <!-- <el-divider></el-divider> -->
        <div class="part">
            <span class="title">基本信息</span>
            <el-divider></el-divider>
            <el-button class="title1" v-show="show2" @click="Update" icon="el-icon-edit">编辑</el-button>
            <el-button class="title2" v-show="show1" @click="cancle_editor" icon="el-icon-edit">取消编辑</el-button>
            <!-- <span class="title1" v-show="show2" @click="Update">编辑</span>
            <span class="title2" v-show="show1" @click="cancle_editor">取消编辑</span> -->
            <el-form ref="form" :model="stu" label-width="120px">
                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学号">
                            <el-input v-model="stu.userName" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学号">
                            <span v-text="stu.userName"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="姓名">
                            <el-input v-model="stu.name" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="姓名">
                            <span v-text="stu.name"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="性别">
                            <el-select v-model="stu.sex">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="性别">
                            <span v-text="stu.sex"></span>
                        </el-form-item>
                    </el-col>

                </el-row>

                <el-row type="flex" justify="center">


                    <el-col :span="8">
                        <el-form-item v-show="show1" label="民族">
                            <el-select v-model="stu.nation" class="Input">
                                <el-option v-for="item in nations" :key="item+'1'" :label="nations[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="民族">
                            <span v-text="stu.nation"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学院">
                            <el-select v-model="stu.college" class="Input" @click.native="show_colleges()"
                                @change="setChange('a')">
                                <el-option v-for="item in colleges" :key="item+'2'" :label="colleges[item]"
                                    :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学院">
                            <span v-text="stu.college"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="专业">
                            <el-select v-model="stu.major" class="Input" @click.native="handleChange()"
                                @change="setChange('b')">
                                <el-option v-for="item in majors" :key="item+'3'" :label="majors[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="专业">
                            <span v-text="stu.major"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">


                    <el-col :span="8">
                        <el-form-item v-show="show1" label="年级">
                            <el-select v-model="stu.grade" class="Input">
                                <el-option v-for="item in grades" :key="item+'4'" :label="grades[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="年级">
                            <span v-text="stu.grade"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="班级">
                            <el-select v-model="stu.classes" class="Input" @click.native="change_major()">
                                <el-option v-for="item in classes" :key="item+'5'" :label="classes[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="班级">
                            <span v-text="stu.classes"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="宿舍">
                            <div style="display:flex;background-color:white;width:194px">
                                <el-select class="sushe" v-model="stu.infocount" style="width:164px"
                                    @change="handleChange1">
                                    <el-option v-for="item in dromm" :key="item+'6'" :label="dromm[item]" :value="item">
                                    </el-option>
                                </el-select>
                                <el-select class="dong" v-model="stu.reportnowcount" style="width:142px"
                                    @change="handleChange3">
                                    <el-option v-for="item in tungs" :key="item+'7'" :label="tungs[item]" :value="item">
                                    </el-option>
                                </el-select>
                            </div>

                        </el-form-item>
                        <el-form-item v-show="show2" label="宿舍">
                            <span v-text="stu.drom"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">

                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学制">
                            <el-select v-model="stu.xuezhi" class="Input">
                                <el-option label="3年" value="3年"></el-option>
                                <el-option label="4年" value="4年"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学制">
                            <span v-text="stu.xuezhi"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="应届生">
                            <el-select v-model="stu.studenttype" class="Input">
                                <el-option label="应届生" value="应届生"></el-option>
                                <el-option label="往届生" value="往届生"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="应届生">
                            <span v-text="stu.studenttype"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="身份证号">
                            <el-input v-model="stu.idnumber" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="身份证号">
                            <span v-text="stu.idnumber"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="出生日期">

                            <el-date-picker v-model="stu.birth" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                                class="Input">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item v-show="show2" label="出生日期">
                            <span v-text="stu.birth"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学习形式">
                            <el-select v-model="stu.studytype" class="Input">
                                <el-option v-for="item in studys" :key="item+'8'" :label="studys[item]" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学习形式">
                            <span v-text="stu.studytype"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="政治面貌">
                            <el-select v-model="stu.politics" class="Input">
                                <el-option v-for="item in politicss" :key="item+'9'" :label="politicss[item]"
                                    :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="政治面貌">
                            <span v-text="stu.politics"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" justify="center">
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="学籍状态">
                            <el-select v-model="stu.studystate" class="Input">
                                <el-option v-for="item in statuss" :key="item+'10'" :label="statuss[item]"
                                    :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item v-show="show2" label="学籍状态">
                            <span v-text="stu.studystate"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="辅导员姓名">
                            <el-input v-model="stu.instructname" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="辅导员姓名">
                            <span v-text="stu.instructname"></span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item v-show="show1" label="辅导员电话">
                            <el-input v-model="stu.instructphone" class="Input"></el-input>
                        </el-form-item>
                        <el-form-item v-show="show2" label="辅导员电话">
                            <span v-text="stu.instructphone"></span>
                        </el-form-item>
                    </el-col>

                </el-row>
            </el-form>
        </div>
        <el-button type="primary" class="Button" v-show="show1" @click="Confirm">保存</el-button>
        <br>
        <!-- <el-button type="primary" class="Button1" v-show="show2" @click="Update">更新</el-button> -->
        <!-- <span class="back" v-show="show1" @click="Back_one">返回</span> -->
        <!-- <span class="back" v-show="show2" @click="Back_two">返回</span> -->

    </div>
</template>

<script>
    export default {
        data() {
            return {
                stu: {},
                nations: ['汉族', '藏族', '维吾尔族', '苗族', '回族'],
                grades: [2017, 2018, 2019, 2020],
                classes: [],
                studys: ['全日制招生', '成人高考', '远程教育', '自考', '国家开发银行'],
                politicss: ['共青团员', '群众', '党员'],
                statuss: ['注册学籍', '保留学籍', '已离校'],
                colleges: [],
                majors: [],
                show1: false,
                show2: true,
                dromm: [],
                tungs: []
            }
        },
        mounted() {
            this.show();
            this.show_dromm();
            this.show_colleges();
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
            //获取相关的宿舍栋数
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
            //宿舍栋数改变后获取相关的宿舍号
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
                                message: "获取宿舍信息失败"
                            });
                        } else {
                            // this.stu.drom = val;
                            this.tungs = res.data.data;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
            },
            handleChange3(val) {
                // this.stu.drom = this.stu.drom + val;
                // console.log(this.stu.drom)
            },
            //获取相关的用户信息
            show() {
                // console.log(this.$store.state.stuInfro.sno)
                let param = new URLSearchParams();
                param.append("username", this.$store.state.stuInfro.userName);
                this.axios.post(this.$apiUrl +'/users/findstudentbyusername', param, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取信息失败'
                            })
                        } else {
                            this.stu = res.data.data;
                            this.stu.birth = res.data.data.birth.substr(0,10);
                            // this.stu.infocount = this.stu.dromm.substr(0, 4);
                            // this.stu.reportnowcount = this.stu.dromm.substring(4, this.stu.dromm.length);
                        }
                    })
            },
            //下拉框显示学院信息
            show_colleges() {
                this.axios.get(this.$apiUrl +'/college/getcollege', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        // console.log()
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
            //学院改变后调用相关专业信息
            handleChange() {
                let param = new URLSearchParams();
                param.append("college", this.stu.college);
                this.axios.post(this.$apiUrl +'/college/getmajorbycollege', param, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token'+ this.$store.state.id),
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取相关学院的专业信息失败'
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
            //专业改变后调用相关的班级信息
            change_major() {
                let param = new URLSearchParams();
                param.append('major', this.stu.major);
                this.axios.post(this.$apiUrl +'/college/getclassesbymajor', param, {
                    headers: {
                        'Authorization': localStorage.getItem('token'+ this.$store.state.id)
                    }
                }).then(res => {
                    if (res.data.code != 0) {
                        this.$message({
                            type: 'error',
                            message: '班级信息获取失败'
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
            setChange(val) {
                if (val === 'a') {
                    this.stu.major = '',
                        this.stu.classes = ''
                } else {
                    this.stu.classes = ''
                }
            },
            Confirm() {
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
                var obj = {
                    studystate: this.stu.studystate,
                    name: this.stu.name,
                    college: this.stu.college,
                    idnumber: this.stu.idnumber,
                    classes: this.stu.classes,
                    birth: this.stu.birth,
                    instructname: this.stu.instructname,
                    instructphone: this.stu.instructphone,
                    userName: this.stu.userName,
                    sex: this.stu.sex,
                    nation: this.stu.nation,
                    major: this.stu.major,
                    grade: this.stu.grade,
                    drom: this.stu.infocount + this.stu.reportnowcount,
                    studenttype: this.stu.studenttype,
                    politics: this.stu.politics,
                    studytype: this.stu.studytype,
                    xuezhi: this.stu.xuezhi
                }
                // this.stu.dromm=this.stu.infocount + this.stu.reportnowcount;
                this.stu.drom = obj.drom;
                console.log('studytype=', this.stu);
                this.$confirm('确定更新该用户信息？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if (this.stu.userName === '' || this.stu.idnumber === '') {
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
                        }else{
                            this.axios.post(this.$apiUrl +'/users/update', this.stu, {
                            headers: {
                                'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store
                                    .state.id),
                            }
                        })
                        .then(res => {
                            console.log(res.data)
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '更新失败'
                                })
                            } else {
                                this.show();
                                this.$message({
                                    type: 'success',
                                    message: '信息更新成功'
                                })
                                this.show1 = false;
                                this.show2 = true;
                            }
                        })
                        .catch(err => {
                            console.log(err);
                        })
                        }
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消更新用户信息'
                    })
                })
            },
            Back_one() {
                this.$router.push('/smanager/muser')
            },
            Back_two() {
                this.$router.push('/smanager/basicuser')
            },
            Update() {
                this.show1 = true;
                this.show2 = false;
                this.stu.infocount = this.stu.drom.substr(0,4);
                this.stu.reportnowcount = this.stu.drom.substr(4,this.stu.drom.length);
            },
            cancle_editor() {
                this.$confirm('确定未保存该修改信息后退出？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.show();
                    this.show1 = false;
                    this.show2 = true;
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消退出编辑模式'
                    })
                })
            }
        },
    }
</script>
<style lang="less" scoped>
    @import '../../../less/layout.css';

    .Button {
        position: relative;
        margin-left: 40%;
        font-size: 25px;
        margin-bottom: 10px;
        background-color: #409eff;
        opacity: 0.9;
    }

    .Button1 {
        position: relative;
        margin-left: 40%;
        font-size: 25px;
        margin-bottom: 10px;
    }

    .back {
        position: relative;
        margin-left: 25%;
        top: 35px;
        color: blue;
        cursor: pointer;
    }

    .part {
        background-color: transparent;
        width: 1000px;
        position: relative;
        padding-left: 10px;
    }

    /deep/ .el-button {
        height: 30px;
        font-size: 15px;
        line-height: 8px;
        margin-top: 20px;

    }

    .el-input {
        background-color: transparent;
        width: 100%;
    }

    span,
    .el-input,
    .el-select {
        display: inline-block;
        height: 40px;
        width: 192px;
        border: 1px solid gainsboro;
        background-color: white;
        margin-top: -1px;
    }

    span {
        padding-left: 12px;
    }


    /deep/ .el-row {
        padding-left: 0;
        height: 42px;
        margin-right: 0%;
        padding-right: 2%;
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

    .title,
    .title1,
    .title2 {
        background-color: transparent;
        border: none;
    }

    .title1,
    .title2 {
        width: 100px;
        color: blue;
        cursor: pointer;
        text-decoration: underline;
    }

    /deep/ .el-input__inner {
        height: 40px;
        // border: none;
    }

    /deep/ .el-button [class*=el-icon-]+span {
        margin-left: 2px;
    }
</style>