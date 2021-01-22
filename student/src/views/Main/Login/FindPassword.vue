<template>
<div id="pic">
    <div id="log">
        <label for="email">邮箱：</label>
        <input type="text" id="email" v-model="email" @blur="getEmailErr" placeholder="默认为QQ邮箱"><span v-text="emailErr"></span>
        <input type="button" id="verbtn" v-show='emailShow' :value="btnValue" :style='verifybtnStyle' @click="getCode" >
        <br>
        <label for="verify">验证码：</label>
        <input type="text" id="verify" v-model="verifyCode" @blur="getVerifyErr" placeholder="验证码为六位"><span v-text="verifyErr"></span>
        <br />
        <label for="paw">密码：</label>
        <input type="password" id="paw" v-model="password" @blur="getPawErr" placeholder="密码必须6位以上"><span v-text="pawErr"></span>
        <br />
        <label for="repaw">确认密码：</label>
        <input type="password" id="repaw" v-model="repassword" @blur="getRepawErr" placeholder="必须与密码一致"><span v-text="repawErr"></span>
        <br />
        <el-button id="btn" @click="submit">确认</el-button>
        <br>
        <p @click="toLog">返回</p>
    </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            // url: "http://127.0.0.1:8080",
            disabled: '',
            btnValue: '获取验证码',
            email: '',
            verifyCode: '',
            password: '',
            repassword: '',
            verifybtnStyle: {
                height: '30px',
                'background-color': 'transparent',
                outline: 'none',
                cursor: 'pointer',
                border:'none',
                color:'#169BD5'
            },
            time: "null",
            pawFlag: 0,
            repawFlag: 0,
            emailFlag: 0,
            emailShow: 1,
            codeFlag: 1,
            verifyFlag: 0,
            emailErr: '',
            verifyErr: '',
            pawErr: '',
            repawErr: '',
            code: '1234'
        }
        },
        methods: {
            toLog(){
                this.$router.push('/main/login');
            },
            getEmailErr() {
                this.emailFlag = 0;
                var emailReg = /.*@qq.com$/g;
                if (this.codeFlag === 1) {
                    if (!emailReg.test(this.email) && this.email != '') {
                        this.emailShow = 0;
                        this.emailErr = "格式错误";
                    } else if (this.email == '') {
                        this.emailShow = 0;
                        this.emailErr = "不能为空";
                    } else {
                        this.emailErr = '';
                        this.emailShow = 1;
                        this.emailFlag = 1;
                    }
                }
            },
            getVerifyErr() {
                this.verifyFlag = 0;
                var verifyReg = /^.{6}$/g;
                if (this.verifyCode == '') {
                    this.verifyErr = "不能为空";
                } else if (!verifyReg.test(this.verifyCode)) {
                    this.verifyErr = "格式错误";
                } else {
                    this.verifyErr = '';
                    this.verifyFlag = 1;
                }
            },
            getPawErr() {
                this.pawFlag = 0;
                var pawReg = /.{6,}/g;
                if (this.password == '') {
                    this.pawErr = "不能为空";
                } else if (!pawReg.test(this.password)) {
                    this.pawErr = "格式错误";
                } else {
                    this.pawErr = '';
                    this.pawFlag = 1;
                }
            },
            getRepawErr() {
                this.repawFlag = 0;
                var repawReg = /.{6,}/g;
                if (this.repassword == '') {
                    this.repawErr = "不能为空";
                } else if (!repawReg.test(this.repassword)) {
                    this.repawErr = "格式错误";
                } else if (this.repassword != this.password) {
                    this.repawErr = "密码不一致";
                } else {
                    this.repawErr = '';
                    this.repawFlag = 1;
                }
            },
            getCode() {
                var i;
                if (this.emailFlag === 0) {
                    this.emailShow = 0;
                    this.emailErr = "不能为空";
                } else {
                    if (this.time == "null") {
                        // let param = new URLSearchParams()
                        // param.append("receiver", this.email)
                        this.emailShow = 1;
                                this.codeFlag = 0;
                                this.verifybtnStyle['cursor'] = 'default';
                                this.verifybtnStyle['color']='#AAAAAA';
                                this.btnValue = "重新发送(59S)";
                                i = 58;
                                // console.log(1);
                                this.time = setInterval(() => {
                                    this.btnValue = "重新发送(" + i-- + "S)";
                                    if (i == 0) {
                                        clearInterval(this.time);
                                        this.btnValue = "重新发送";
                                        // this.disabled = "false";
                                        this.time = "null";
                                        this.verifybtnStyle['cursor'] = 'pointer';
                                        this.verifybtnStyle['color']='#169BD5';
                                        this.codeFlag = 1;
                                    }
                                }, 1000);
                        this.axios.get(this.$apiUrl +"/getpwd/getcode",{params:{
                            "receiver": this.email
                        }})
                        .then(res=>{
                            console.log(res.data)
                            if(res.data.code!=0){
                                // this.stuId=res.data.msg;
                                this.$message.error({
                                    message: res.data.msg,
                                    duration:1500
                                });
                                clearInterval(this.time);
                                        this.btnValue = "重新发送";
                                        // this.disabled = "false";
                                        this.time = "null";
                                        this.verifybtnStyle['cursor'] = 'pointer';
                                        this.verifybtnStyle['color']='#169BD5';
                                        this.codeFlag = 1;
                            }
                            
                        }).catch(err=>{
                            console.log(err);
                        })
                        
                    }
                }
            },
            submit() {
                // console.log(1);
                if (this.emailFlag === 1 && this.verifyFlag === 1 && this.pawFlag === 1 && this.repawFlag === 1) {
                    // console.log(this.email);

                    let param = new URLSearchParams()
                    param.append("code",this.verifyCode);
                    param.append("firstPassword",this.password);
                    param.append("secondPassword",this.repassword);
                    // console.log(this.code)
                    this.axios.post(this.$apiUrl +"/getpwd/updatepwd",param)
                    .then(res=>{
                        // console.log(res.data);
                        if(res.data.code!=0){
                            this.$message.error({
                                message: res.data.data,
                                duration:1500
                            });
                        }else{
                            this.$message({
                                message: res.data.data,
                                type: 'success',
                                duration:1500
                            });
                            this.$router.push('/main/login')
                        }                        
                    }).catch(err=>{
                        console.log(err);
                    })
                } else {
                    if (this.email == '') {
                        this.emailShow = 0;
                        this.emailErr = "不能为空";
                    }
                    if (this.verifyCode == '') {
                        this.verifyErr = "不能为空";
                    }
                    if (this.password == '') {
                        this.pawErr = "不能为空";
                    }
                    if (this.repassword == '') {
                        this.repawErr = "不能为空";
                    }
                }
            }
        }
}

</script>
<style lang="scss" scoped>
#pic{
    height: 350px;
    width: 100%;
    background-size: 100%;
    background-image: url('../../../assets/sicnu.jpg');
    margin-top: -1px;
    padding-top: 50px;
}
    #log {
        padding-bottom: 30px;
        width: 400px;
        min-width: 400px;
        height: 250px;
        min-height: 250px;
        background-color: rgba(255, 255, 255, 0.7);
        border: 1px solid #D0CDCD;
        margin-left: auto;
        margin-right: auto;
    }
    input::placeholder {
        font-size: 5px;
    }
    label {
        display: inline-block;
        margin-top: 25px;
        margin-left: 15%;
        min-width: 20%;
        text-align: right;
        font-size: 13px;
    }
    
    button{
        font-size: 15px;
    }

    #btn {
        height: 30px;
        width: 100px;
        margin-left: 40%;
        margin-top: 20px;
        line-height: 8px;
    }
    
    
    span {
        font-size: 3px;
        color: red;
    }
    
    #repaw,
    #email,
    #verify,
    #paw {
        height: 30px;
        width: 40%;
        background-color: transparent;
        outline: none;
        border: none;
        min-width: 40%;
        border-bottom: 1px solid gray;
    }
    
    p {
        margin-left: 340px;
        cursor: pointer;
        color: #169BD5;
        font-size: 13px;
        margin-top: -12px;
    }
    
</style>