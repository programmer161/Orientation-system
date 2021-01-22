<template>
    <div id="msg">
        <div id="title">学校通知：</div>
        <div>
            <li v-for="(data,index) in messageList1" :key="'schoolmsg'+index" :class="index<t?'item':'None'" @click="toMessage(data)"><span v-text="data.title"></span></li>                   
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            t:this.$store.state.msgnum,
            messageList1:[
            ]
        }
    },
    methods:{
        toMessage(data){
            console.log(data);
            this.$store.commit("setMsgId",{id:data.id,title:data.title,type:"学校通知"})
            this.$router.push('/main/message');
        },
        getMsg(){
            this.axios.get(this.$apiUrl +'/message/gettitle').then(res=>{
                if(res.data.code==0){
                    this.messageList1=res.data.data;
                }
            }).catch(err=>{
                console.log(err);
            })
        }
    },
    mounted(){
        this.getMsg();
    }
}
</script>

<style lang='scss' scoped>
.None{
            display: none;
        }
li{
        list-style-type: square;
        content:".";  
        color:rgb(56, 46, 46);
    }
    #msg{
            min-height: 100px;
            width: 500px;
            border: 1px solid #D0CDCD;
            margin-top: 20px;
            padding-bottom: 10px;
            box-shadow: 5px 5px 10px #D0CDCD;
            text-align: left;
            #title{
                background-color:#E5FFF2;
                color: #276E51;
                height: 40px;
                line-height: 40px;
                size: 50px;
                padding-left: 10px;
            }
            div{
                .item{
                    width: 480px;
                    margin-left: auto;
                    margin-right: auto;
                    height: 40px;
                    line-height: 40px;
                    padding-left: 10px;
                    font-size: 13px;
                    border-bottom: 1px dashed #D0CDCD;
                    font-weight: 340;
                    span{
                        cursor: pointer;
                    }
                    span:hover{
                        color: #169BD5;
                    }
                }
            }
        }
</style>