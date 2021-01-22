<template>
    <div id="localpay">
        <!-- <div id="container"> -->
            <div id="localpayment">
                <p id="p1">现场支付学费</p>
            </div>
            <div id="container">
                <div class="demo">
                    <p id="p3">请将你的单据拍下来，并在下方提交给管理员审核</p>
                    <div id="pic">
                            <input type="file" class="input_flie" name="form_data" ref="file" id="chooseImg" @change="reads($event)"/>
                            <div class="div_shuline" v-if="imgUrl==''"></div>
                            <div class="div_hengline" v-if="imgUrl==''"></div>    
                    </div>
                    <el-button  id="btn" @click="upload1" icon="el-icon-circle-check">上传</el-button>
                </div>
                <div class="demo">
                    <p id="p3">请将你的身份证正面照拍下来，并在下方提交给管理员审核</p>
                    <div id="pic2">
                        <input type="file" class="input_flie" name="form_data" ref="file" id="chooseImg" @change="reads2($event)"/>
                        <div class="div_shuline" v-if="imgUrl2==''"></div>
                        <div class="div_hengline" v-if="imgUrl2==''"></div>
                    </div>
                    <el-button  id="btn" @click="upload2" icon="el-icon-circle-check">上传</el-button>
                </div>
            </div>
            <br>
            <!-- <a @click="toreturn">返回</a> -->
        </div>
    <!-- </div> -->
</template>
<script>

export default {
    data(){
        return{
            imgUrl: "",
            imgUrl2: "",
            blob:'',
            idImage:'',
            img1:"",
            img2:""
        }
        
    },
    methods:{
        toreturn(){
                this.$router.push('/student/tuition');
            },
        upload1(){
            // console.log(this.idImage)
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==18){
                    index=1;
                }
            }
            if(index==1){
            this.$confirm("确认上传?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                var reader =new FileReader();
                reader.readAsDataURL(this.img1);
                var that=this;
                reader.onload=function(e){ 
                    that.idImage= reader.result; //把图片的二进制流付给表单ruleForm里面的idImage
                    let param = new FormData(); //创建form对象
                    // console.log(that.idImage)
                    param.append('payimg',that.idImage);//通过append向form对象添加数据
                    param.append('username',that.$store.state.id);
                    // console.log(this.$store.state.id)
                    that.axios.post(that.$apiUrl +"/fileoperation/uppayimg",param,{
                headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+that.$store.state.id),
                'Content-Type': 'multipart/form-data'}
            }
                ).then(res=>{
                    // console.log(res.data)
                    if(res.data.code!=0){
                        that.$message.error({
                            message: res.data.msg,
                            duration:1500
                        });
                        // this.$router.push('/login');
                    }else{
                        // console.log(res.data)
                        that.$message({
                            type:"success",
                            message: res.data.data,
                            duration:1500
                        });
                    }
                }).catch(err=>{
                    console.log(err);
                });
                    // console.log(that.idImage);//二进制
                }
                
                }).catch(() => {
                this.$message(
                    {
                    type: "info",
                    message: "已取消"
                    },
                    console.log("cuo")
                );
                });
                }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
        },
        upload2(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==19){
                    index=1;
                }
            }
            if(index==1){
            this.$confirm("确认上传?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                var reader =new FileReader();
                reader.readAsDataURL(this.img2);
                var that=this;
                reader.onload=function(e){ 
                    that.idImage= reader.result; //把图片的二进制流付给表单ruleForm里面的idImage
                    let param = new FormData(); //创建form对象
                    // console.log(that.idImage)
                    param.append('photo',that.idImage);//通过append向form对象添加数据
                    param.append('username',that.$store.state.id);
                    that.axios.post(that.$apiUrl +"/fileoperation/upphoto",param,{
                headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+that.$store.state.id),
                'Content-Type': 'multipart/form-data'}
            }
                ).then(res=>{
                    // console.log(res.data)
                    if(res.data.code!=0){
                        that.$message.error({
                            message: res.data.msg,
                            duration:1500
                        });
                        // this.$router.push('/login');
                    }else{
                        // console.log(res.data)
                        that.$message({
                            type:"success",
                            message: res.data.data,
                            duration:1500
                        });
                    }
                }).catch(err=>{
                    console.log(err);
                });
                    // console.log(that.idImage);//二进制
                }
                }).catch(() => {
                this.$message(
                    {
                    type: "info",
                    message: "已取消"
                    },
                    console.log("cuo")
                );
                });
                }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
        },
        reads(event){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==18){
                    index=1;
                }
            }
            if(index==1){
            var reader =new FileReader();//创建读取文件的方法
            this.img1=event.target.files[0];
            reader.readAsDataURL(this.img1);//将文件已url的形式读入页面
            this.imgUrl = URL.createObjectURL(this.img1);
            document.getElementById("pic").style.backgroundColor="transparent";
            document.getElementById("pic").style.opacity="1";
            document.getElementById("pic").style.backgroundImage="url(" + this.imgUrl+ ")";
            document.getElementById("pic").style.backgroundSize="100%";
            // reader.readAsDataURL(this.img1);
            // var that=this;
            // reader.onload=function(e){ 
            //     that.idImage= reader.result; //把图片的二进制流付给表单ruleForm里面的idImage
                
            //     console.log(that.idImage);//二进制
            // }
            // console.log(this.idImage);//二进制
            }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
      },
        reads2(event) {
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==19){
                    index=1;
                }
            }
            if(index==1){
            var reader =new FileReader();//创建读取文件的方法
            this.img2=event.target.files[0];
            reader.readAsDataURL(this.img2);//将文件已url的形式读入页面
            this.imgUrl2 = URL.createObjectURL(this.img2);
            document.getElementById("pic2").style.backgroundColor="transparent";
            document.getElementById("pic2").style.opacity="1";
            document.getElementById("pic2").style.backgroundImage="url(" + this.imgUrl2+ ")";
            document.getElementById("pic2").style.backgroundSize="100%";
            var that=this;
            reader.onload=function(e){ 
                that.idImage= reader.result; //把图片的二进制流付给表单ruleForm里面的idImage
                // console.log(that.idImage);//二进制
            }
            }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }
        }
    },
    mounted(){
        let param = new URLSearchParams()
        param.append("username",this.$store.state.id);
            this.axios.post(this.$apiUrl +"/fileoperation/getpayimg",param,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }
            ).then(res=>{
                // console.log(res.data)
                if(res.data.code!=0){
                    this.$message.error({
                        message: res.data.msg,
                        duration:1500
                    });
                }else{
                    if(res.data.data!=null){
                        this.imgUrl=res.data.data;
                        document.getElementById("pic").style.backgroundColor="transparent";
                        document.getElementById("pic").style.opacity="1";
                        document.getElementById("pic").style.backgroundImage="url(" + this.imgUrl+ ")";
                        document.getElementById("pic").style.backgroundSize="100%";
                    }
                }
            }).catch(err=>{
                console.log(err);
            });
            let param1 = new URLSearchParams()
            param1.append("username",this.$store.state.id);
            this.axios.post(this.$apiUrl +"/fileoperation/getphoto",param1,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }
            ).then(res=>{
                // console.log(res.data)
                if(res.data.code!=0){
                    this.$message.error({
                        message: res.data.msg,
                        duration:1500
                    });
                }else{
                    if(res.data.data!=null){
                        this.imgUrl2=res.data.data;
                        document.getElementById("pic2").style.backgroundColor="transparent";
                        document.getElementById("pic2").style.opacity="1";
                        document.getElementById("pic2").style.backgroundImage="url(" + this.imgUrl2+ ")";
                        document.getElementById("pic2").style.backgroundSize="100%";
                    }
                }
            }).catch(err=>{
                console.log(err);
            });
    }

}
</script>
<style lang="scss" scoped>
#localpay{
    position: relative;
    width: 920px;
    // height: 700px;
    margin-top: -10px;
    margin-left: -10px;
}
    #localpayment{
        width: 100%;
        min-height: 50px;
        border-width: 0.5px;
        background-color: rgba($color: #276E51, $alpha: 0.8);
        display: flex;
        justify-content: center;
        }
    #container{
        margin-top:0 ;
        min-height: 360px;
        display: flex;
        justify-content: space-around;
        .demo{
            display: inline-block;
            width: 50%;
            background-color: #f4f8f6;
            background-color: #f4f8f6;
            border-width: 1px;
                div{
                height: 200px;
                width: 250px;
                background-color: #797979;
                opacity: 0.3;
                margin-left: 100px;
                margin-top: 20px;
                .input_flie {
                    display:block;
                    width:250px;
                    height:200px;
                    opacity:0;
                    z-index:100;
                    position:absolute;
                    cursor: pointer;
                }
                .div_shuline {
                    position:absolute;
                    width:150px;
                    height:1.5px;
                    background-color:black;
                    margin-top:95px;
                    z-index:5;
                    margin-left: 50px;
                }
                .div_hengline {
                    position:absolute;
                    width:1px;
                    height:130px;
                    background-color:black;
                    margin-left:120px;
                    margin-top:30px;
                    z-index:5;
                }
            }
                button{
                    width: 200px;
                    height: 40px;
                    background-color:#409eff;
                    color: white;
                    margin-top: 30px;
                    margin-left: 125px;
                    outline: none;
                    border: none;
                    border-radius: 0.5em;
                    cursor: pointer;
        }
        }
    }
    a {
        margin-left: 90%;
        // margin-top: 30px;
        cursor: pointer;
        color: blue;
        font-size: 13px;
    }
// }
#p1{
    font-size: 18px;
    margin-top: 15px;
    // font-weight: bolder;
    color:#ffffff;
}
#p2{
    margin-top: 25px;
    margin-left: 100px;
    font-size: 16px;
    font-weight:bold;
    color: #797979;
}
#p3{
    margin-top: 25px;
    // margin-left: 105px;
    font-size: 14px;
    text-align: center;
}


</style>