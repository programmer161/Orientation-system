<template>
    <div id="pic">
        <div id="login">
            <label for="username">用户名：</label>
            <input type="text" id="username" v-model="username" @blur="inputUsername" placeholder="请输入已绑定的学号/工号"><span v-text="userErr"></span>
            <br>
            <label for="paw">密码：</label>
            <input type="password" id="paw" v-model="password" @blur="inputPassword" placeholder="初始密码为身份证后六位"><span v-text="pawErr"></span>
            <br>
            <el-button @click="login" id="btn">确认</el-button>
            <div>
                <a @click="toFindPwd">忘记密码</a>
                <a @click="toFindId" id="aaa">学号查找</a>
            </div>
        </div>
    </div>
</template>
<script>
// import axios from "axios"
export default {
    data(){
        return{
            username: '',
            password: '',
            userErr: '',
            pawErr: '',
            userFlag: 0,
            pawFlag: 0,
        }
        },
        methods: {
            toFindPwd(){
                this.$router.push('/main/findPassword');
            },
            toFindId(){
                this.$router.push('/main/findId');
            },
            inputUsername() {
                // var userReg = /^[0-9]{10}$/g;
                var userReg = /.*/g
                if (userReg.test(this.username)) {
                    this.userFlag = 1;
                    this.userErr = "";
                } else {
                    this.userFlag = 0;
                    if (this.username === '') {
                        this.userErr = "不能为空";
                    } else {
                        this.userErr = "格式错误";
                    }
                }
            },
            inputPassword() {
                var pawReg = /^.{6,}$/g;
                if (pawReg.test(this.password)) {
                    this.pawFlag = 1;
                    this.pawErr = "";
                } else {
                    this.pawFlag = 0;
                    if (this.password === '') {
                        this.pawErr = "不能为空";
                    } else if (this.password.length < 8) {
                        this.pawErr = "不能小于6位";
                    }
                }
            },
            login() {
                if (this.userFlag === 1 && this.pawFlag === 1) {
                    var obj = {
                        "username": this.username,
                        "password": this.password
                    }
                    console.log(obj);
                    // this.axios.post("/user/login",qs.stringify(obj),{
                    this.axios.post(this.$apiUrl +"/login",obj).then(res=>{
                        console.log(res.data);
                        if(res.data.code!=0){
                            this.$message.error({
                                message: res.data.msg,
                                duration:1500
                            });
                        }else{
                            this.$message({
                                message: res.data.msg,
                                type: 'success',
                                duration:1500
                            });
                            localStorage.setItem("token"+res.data.data.username,res.data.data.token)
                            console.log(res.data.data.token);
                            var list=res.data.data.menus;
                            console.log(res.data.data.auth[0].authority)
                            // var url=res.data.detail.url;
                            this.$store.commit('setloginflag',0);
                            this.$store.commit("setId",{id:res.data.data.username,list:list,stuinfo:res.data.data.user[0]});
                            this.$store.commit("setauth",res.data.data.auth[0].authority)
                            var index=0;
                            var i=0;
                            for(i=0;i<list.length;i++){
                                var t=list[i].frontendMenuId;
                                console.log(t)
                                if(t==34){
                                    index=1;
                                }
                            }
                            if (index==1) {
                                this.$store.commit("setGao",1)
                            } else {

                                this.$store.commit("setGao",0)
                            }
                            // console.log(index,state.gao)
                            console.log(this.$store.state.gao)
                            this.$router.push('/main');
                            // window.location.reload();
                        }
                        
                        
                    }).catch(err=>{
                        console.log(err);
                    })
                 }else {
                    if (this.username == '') {
                         this.userErr = "不能为空";
                         this.userFlag = 0;
                    }
                    if (this.password == '') {
                         this.pawErr = "不能为空";
                         this.pawFlag = 0;
                    }
                }  
            }
        },
        mounted(){
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
 #login {
        padding-top: 30px;
        width: 400px;
        min-width: 400px;
        height: 250px;
        min-height: 250px;
        background-color: rgba(255, 255, 255, 0.7);
        border: 1px solid #D0CDCD;
        margin-left: auto;
        margin-right: auto;
        // margin-top: 50px;
        div{
            margin-top: 25px;
        }
    }
    
    label {
        display: inline-block;
        margin-top: 35px;
        margin-left: 15%;
        min-width: 20%;
        text-align: right;
        font-size: 13px;
    }
    input::placeholder {
        font-size: 5px;
    }
    #username,
    #paw {
        height: 30px;
        width: 40%;
        outline: none;
        border: none;
        min-width: 40%;
        background-color: transparent;
        border-bottom: 1px solid gray;
    }
    
    #btn {
        height: 30px;
        width: 100px;
        margin-left: 40%;
        margin-top: 30px;
        line-height: 8px;
    }
    
    span {
        font-size: 3px;
        color: red;
    }
    
    a {
        color: #169BD5;
        margin-left: 5%;
        cursor: pointer;
        font-size: 13px;
    }
    #aaa{
        margin-left: 260px;
    }
    
</style>