<template>
    <div>
        <div class="whole" v-if="adminflag">
            <p class="title">管理员管理</p>
            <el-divider></el-divider>
            <div class="part">
                <el-button class="title1" v-show="show2" @click="Update_message" icon="el-icon-edit">编辑</el-button>
                <el-button class="title1" v-show="show1" @click="cancle_editor" icon="el-icon-edit">取消编辑</el-button>
                <!-- <i class="el-icon-edit"></i><span class="title1" v-show="show2" @click="Update_message">编辑</span> -->
                <!-- <span class="title2" v-show="show1" @click="cancle_editor">取消编辑</span> -->
                <el-form ref="form" :model="admin" label-width="100px" label-position="right">
                    <el-row type="flex" justify="center">
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="工号">
                                <el-input v-model="admin.userName"></el-input>
                            </el-form-item>
                            <el-form-item v-show="show2" label="工号">
                                <span v-text="admin.userName"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="姓名">
                                <el-input v-model="admin.name"></el-input>
                            </el-form-item>
                            <el-form-item v-show="show2" label="姓名">
                                <span v-text="admin.name"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="性别">
                                <el-select v-model="admin.sex">
                                    <el-option label="男" value="男"></el-option>
                                    <el-option label="女" value="女"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item v-show="show2" label="性别">
                                <span v-text="admin.sex"></span>
                            </el-form-item>
                        </el-col>

                    </el-row>

                    <el-row type="flex" justify="center">


                        <el-col :span="8">
                            <el-form-item v-show="show1" label="学院">
                                <el-select v-model="admin.college">
                                    <el-option v-for="item in colleges" :key="item" :label="colleges[item]"
                                        :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item v-show="show2" label="学院">
                                <span v-text="admin.college"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="民族">
                                <el-select v-model="admin.nation">
                                    <el-option v-for="item in nations" :key="item" :label="nations[item]" :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item v-show="show2" label="民族">
                                <span v-text="admin.nation"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="电话">
                                <el-input v-model="admin.phonenumber"></el-input>
                            </el-form-item>
                            <el-form-item v-show="show2" label="电话">
                                <span v-text="admin.phonenumber"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row type="flex" justify="center">
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="身份证号">
                                <el-input v-model="admin.idnumber"></el-input>
                            </el-form-item>
                            <el-form-item v-show="show2" label="身份证号">
                                <span v-text="admin.idnumber"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="出生日期">
                                <el-date-picker v-model="admin.birth" type="date" placeholder="选择日期"
                                    value-format="yyyy-MM-dd" class="Input">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item v-show="show2" label="出生日期">
                                <span v-text="admin.birth"></span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item v-show="show1" label="政治面貌">
                                <el-select v-model="admin.politics">
                                    <el-option v-for="item in politicss" :key="item" :label="politicss[item]"
                                        :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item v-show="show2" label="政治面貌">
                                <span v-text="admin.politics"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-button type="primary" v-show="show1" class="Confirm" @click="confirm_true" icon="el-icon-document">
                    保存</el-button>
                <el-button type="primary" v-show="show2" class="user_details" @click="users_details"
                    icon="el-icon-s-order">用户管理详情</el-button>
                <br>
            </div>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                admin: {},
                nations: ['汉族', '藏族', '维吾尔族', '苗族', '回族'],
                politicss: ['共青团员', '群众', '党员'],
                show1: false,
                show2: true,
                colleges: [],
                adminflag: this.$store.state.adminflag,
            }
        },
        mounted() {
            this.show();
            this.show_colleges();
            // this.adminflag=1;
        },
        methods: {
            //验证输入的身份证号是否合法
            isIdCardNo(num) {
                num = num.toUpperCase(); //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。       
                if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
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
            //获取相关管理员的详情信息
            show() {
                // console.log(this.$store.state.stuInfro)
                let param = new URLSearchParams();
                param.append("username", this.$store.state.stuInfro.userName);
                this.axios.post(this.$apiUrl +'/users/getuserbyusername', param, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token' + this.$store.state.id),
                        }
                    })
                    .then(res => {
                        console.log(res.data)
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '获取信息失败'
                            })
                        } else {
                            this.admin = res.data.data;
                            this.admin.birth = res.data.data.birth.substr(0,10);
                        }
                    })
            },
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
            Update_message() {
                this.show1 = true;
                this.show2 = false;
            },

            Export_message() {

            },
            confirm_true() {
                var flag1 = 1;
                var re = /^[0-9]+.?[0-9]*/;
                if (!re.test(this.stu.sno)) {
                    flag1 = 0;
                } else {
                    if (this.stu.sno.length != 10) {
                        flag1 = 0;
                    }
                }
                if (!this.isIdCardNo(this.stu.idcard)) {
                    flag1 = 0;
                }
                this.$confirm('确定更新该用户信息？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if (this.admin.userName === '' && this.admin.idnumber === '') {
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
                            this.axios.post(this.$apiUrl +'/users/updatemanager', this.admin, {
                                    headers: {
                                        'Authorization': localStorage.getItem('token' + this.$store.state
                                            .id)
                                    }
                                })
                                .then(res => {
                                    if (res.data.code != 0) {
                                        this.$message({
                                            type: 'error',
                                            message: '更新失败'
                                        })
                                    } else {
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
            },
            users_details() {
                this.$store.commit('setadminflag', 0)
                console.log(111)
                this.adminflag = 0;
                this.$router.push('/main/manageuser/muser/basicadmin/madetails')
            }
        },
        watch: {
            $route(to) {
                console.log(to.path)
                if (to.path == '/main/manageuser/muser/basicadmin') {
                    //     this.$store.commit('setadminflag',0)
                    // }else{
                    this.$store.commit('setadminflag', 1)
                }
                this.adminflag = this.$store.state.adminflag
                console.log(this.$store.state.adminflag)
            }
        },
    }
</script>

<style lang="less" scoped>
    @import '../../../less/layout.css';
    // @import '../../../less/table.less';

    .whole {
        width: 1020px;
    }

    .title {
        background-color: transparent;
        border: none;
    }

    .pages {
        position: relative;
        margin-top: 2%;
        margin-left: 7%;
    }

    .part {
        background-color: transparent;
        height: 68%;
        width: 80%;
        position: relative;
        margin-top: 3%;
        margin-left: 10%;
    }

    .Count {
        color: red;
        font-size: 20px;
        position: relative;
        top: 10px;
        margin-left: 4%;
    }

    .Count1 {
        color: red;
        font-size: 20px;
        position: relative;
        top: 10px;
    }

    .el-input {
        background-color: transparent;
        width: 100%;
    }

    .el-select {
        width: 100%;
    }

    .el-form-item {
        margin-right: 2%;
    }

    .el-input {
        border-radius: 4px;
        min-height: 36px;
    }

    /deep/ .el-row {
        padding-right: 2%;
        height: 1%;
        margin: 0%;
        padding-left: 0;
    }

    /deep/ .el-form-item {
        padding: 0%;
        height: 40px;
        width: 120px;
        background-color: #E5FFF2;
        border: 1px solid gainsboro;
        line-height: 40px;
        margin-bottom: 0px;
    }

    .Delete {
        position: relative;
        margin-left: 20%;
        margin-top: 10%;

    }

    .Export {
        position: relative;
        margin-left: 45%;
        margin-top: 10%;
        margin-bottom: 6%;
    }

    .back {
        color: blue;
        position: relative;
        margin-left: 5%;
        top: 30px;
        cursor: pointer;
    }

    .Update {
        position: relative;
        margin-top: 8%;
        margin-left: 47%;
        margin-bottom: 6%;
    }

    .back1 {
        color: blue;
        position: relative;
        margin-left: 22%;
        top: 30px;
        cursor: pointer;
    }

    .Confirm {
        position: relative;
        margin-left: 740px;
        line-height: 8px;
        margin-bottom: 2%;
        margin-top: 2%;
        height: 30px;
        background-color: #409eff;
        opacity: 0.9;
    }

    .user_details {
        position: relative;
        margin-left: 680px;
        line-height: 8px;
        margin-bottom: 2%;
        margin-top: 2%;
        height: 30px;
        background-color: #409eff;
        opacity: 0.9;
    }

    span,
    .el-select,
    .el-input {
        display: inline-block;
        height: 40px;
        border: 1px solid gainsboro;
        background-color: white;
        margin-top: -1px;
    }

    span {
        padding-left: 12px;
        width: 152px;
    }

    .el-select,
    .el-input {
        width: 164px;
    }

    .el-form-item /deep/ .el-form-item__label {
        padding-left: 10px;
        color: black;
        text-align: left;
    }

    .title1,
    .title2 {
        background-color: transparent;
        border: none;
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