<template>
    <div id="pic">
    <div id="login">
        <input type="text" id="v_id" v-model="Id" @blur="inputId" placeholder="身份证号">
        <button id="btn" @click="login" /><span v-text="idErr"></span>
        <br><br>
        <p>您的学号/工号为：</p>
        <div>
            <p v-text="stuId" id="infro"></p>
        </div>
        <p id="return" @click="toLog">返回</p>
    </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            Id: '',
            idErr: '',
            stuId: '',
            idFlag: 0
        }
        },
        methods: {
            toLog(){
                this.$router.push('/main/login');
            },
            inputId() {
                var idReg = /^[0-9]{17}[0-9|x|X]$/g;
                if (idReg.test(this.Id)) {
                    this.idFlag = 1;
                    this.idErr = "";
                } else {
                    this.idFlag = 0;
                    if (this.Id === '') {
                        this.idErr = "不能为空";
                    } else {
                        this.idErr = "格式错误";
                    }
                }
            },
            login() {
                if (this.idFlag === 1 ) {
                    let param = new URLSearchParams()
                    param.append("idnumber", this.Id)

                    this.axios.post(this.$apiUrl +"/users/findsno",param)
                    .then(res=>{
                        console.log(res.data)
                        if(res.data.code==0){
                            this.stuId=res.data.data;
                        }else{
                            this.stuId=res.data.msg;
                        }
                    }).catch(err=>{
                        console.log(err);
                    })
                    
                }else {
                    if (this.Id == '') {
                         this.idErr = "不能为空";
                         this.idFlag = 0;
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
        #infro{
            color: red;
            margin-left: 40%;
            margin-top: 5%;
        }
        div{
            height: 20px;
        }
    }
    
    #v_id{
        height: 30px;
        width: 60%;
        outline: none;
        border: none;
        min-width: 40%;
        margin-left: 10%;
        margin-top: 5%;
        border: 1px solid gray;
        border-radius: 0.3em;
    }
    
    #btn {
        height: 30px;
        width: 30px;
        margin-left: 10px;
        margin-top: 20px;
        position: absolute;
        border: none;
        outline: none;
        background-image: url(../../../assets/u19.svg);
        background-repeat: no-repeat;
        background-size: 100%;
        background-color: transparent;
        cursor: pointer;
    }
    p{
        margin-left: 10%;
    }
    
    span {
        margin-left: 50px;
        font-size: 3px;
        color: red;
    }
    
    #return {
        position: absolute;
        margin-left: 345px;
        color: #169BD5;
        cursor: pointer;
        font-size: 13px;
        margin-top: 75px;
    }
    
</style>