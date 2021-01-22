<template>
    <div>
        <div id="mes">
            <div id="title" v-text="this.$store.state.msgtitle"></div>
            <div id="values" v-html="$xss(content)"></div>
            <!-- <p @click="toLogin">返回</p> -->
        </div>
    </div>

</template>

<script>
export default {
    data(){
        return{
            ulFlag:0,
            title:"test",
            content:""
        }
    },
    methods:{
        toLogin(){
            // this.$router.push('/login');
        },
        
    },
    mounted(){
        // document.body.style.backgroundImage="url(" + require("../assets/sicnu.jpg") + ")";
        let param = new URLSearchParams()
        param.append("id", this.$store.state.msgid)
        // this.title=this.$store.state.msgtitle;
        console.log(this.$store.state.msgid)
        // if(this.$store.state.msgtype=='缴费须知')
        if(this.$store.state.msgtype=='缴费须知'){
            this.axios.post(this.$apiUrl +"/paynotice/getvalues",param).then(res=>{
            console.log(res.data);
            if(res.data.code!=0){
                this.$message.error({
                    message: "通知获取失败！",
                    duration:1500
                });
            }else{
                this.content=res.data.data[0].tvalues;
            }                        
            }).catch(err=>{
            console.log(err);
            })
        }else if(this.$store.state.msgtype=='报到须知'){
            this.axios.post(this.$apiUrl +"/reportnotice/getvalues",param).then(res=>{
                console.log(res.data);
            if(res.data.code!=0){
                this.$message.error({
                    message: "通知获取失败！",
                    duration:1500
                });
            }else{
                this.content=res.data.data[0].tvalues;
            }                        
            }).catch(err=>{
            console.log(err);
            })
        }else if(this.$store.state.msgtype=='学校通知'){
            this.axios.post(this.$apiUrl +"/message/getvalues",param).then(res=>{
            console.log(res.data);
            if(res.data.code!=0){
                this.$message.error({
                    message: "通知获取失败！",
                    duration:1500
                });
            }else{
                this.content=res.data.data[0].tvalues;
            }                        
            }).catch(err=>{
            console.log(err);
            })
        }else if(this.$store.state.msgtype=='校园攻略'){
            this.axios.post(this.$apiUrl +"/trategy/getvalues",param).then(res=>{
            console.log(res.data);
            if(res.data.code!=0){
                this.$message.error({
                    message: "通知获取失败！",
                    duration:1500
                });
            }else{
                this.content=res.data.data[0].tvalues;
            }                        
            }).catch(err=>{
            console.log(err);
            })
        }
        
        
    }
}
</script>

<style lang="scss" scoped>
    // p{
    //     cursor: pointer;
    //     color: blue;
    //     margin-left: 95%;
    // }
    #mes{
        // border: 1px solid black;
        // width: 50%;
        font-size: 20px;
        margin-left: auto;
        margin-right: auto;
        padding-top: 30px;
        // padding-bottom: 20px;
        padding-left: 50px;
        padding-right: 50px;
        // margin-top: 50px;
        background-color: #F4F8F6;
        opacity: 0.8;
        // font-size: 13px;
        min-height: 300px;
        padding-bottom: 30px;
        #title{
            text-align: center;
            margin-bottom: 20px;
            font-size: 25px;
        }
        #values{
            // background-color: blue;
            min-height: 250px;
        }
    }
    
</style>