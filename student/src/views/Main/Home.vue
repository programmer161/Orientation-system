<template>
    <div>
        <div id="pic">
            <el-carousel :interval="4000" type="card" height="400px" indicator-position="outside">
                <el-carousel-item v-for="data in picList" :key="data.id">
                    <img :src="data.idView" class="image" />
                </el-carousel-item>
            </el-carousel>
        </div>
        <div id="content">
            <div class="msg">
                <span @click="toschoolmsg">>>更多内容</span>
                <school-msg></school-msg>
                <span @click="toreportmsg">>>更多内容</span>
                <report-msg></report-msg>
                
            </div>
            <div class="msg">
                <span @click="toschoolstrategy">>>更多内容</span>
                <school-strategy></school-strategy>
                <span @click="topaymsg">>>更多内容</span>
                <pay-msg></pay-msg>
                
            </div>
        </div>
    </div>
</template>

<script>
import SchoolMsg from '../../components/SchoolMsg.vue'
import SchoolStrategy from '@/components/SchoolStrategy.vue'
import PayMsg from '../../components/PayMsg.vue'
import ReportMsg from '../../components/ReportMsg.vue'
export default {
    components:{
        SchoolMsg:SchoolMsg,
        SchoolStrategy:SchoolStrategy,
        PayMsg:PayMsg,
        ReportMsg:ReportMsg
    },
    data(){
        return{
            // ulFlag:0,
            // List:[],
            // Id:""
            picList:[
                {id:"0",idView:require('../../assets/sicnu1.jpg')},
                {id:"1",idView:require('../../assets/sicnu2.jpg')},
                {id:"2",idView:require('../../assets/sicnu3.jpg')},
                {id:"3",idView:require('../../assets/sicnu4.jpg')}            
            ],
            // [require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg"),require("../assets/sicnu.jpg")],
            
        }
    },
    methods:{
        toschoolstrategy(){
            this.$store.commit("setMsgId",{id:'',title:'',type:"校园攻略"})
            this.$router.push('/main/message/msglist');
        },
        toschoolmsg(){
            this.$store.commit("setMsgId",{id:'',title:'',type:"学校通知"})
            this.$router.push('/main/message/msglist');
        },
        topaymsg(){
            this.$store.commit("setMsgId",{id:'',title:'',type:"缴费须知"})
            this.$router.push('/main/message/msglist');
        },
        toreportmsg(){
            this.$store.commit("setMsgId",{id:'',title:'',type:"报到须知"})
            this.$router.push('/main/message/msglist');
        },
    }
}
</script>

<style lang="scss" scoped>
span{
    display: inline-block;
    color:gray;
    margin-left: 420px;
    font-size: 10px;
    position: absolute;
    margin-top: 32px;
    // padding-bottom: 30px;
    cursor: pointer;
}
.image{
    height: 100%;
    width: 100%;
    background-size: 100%;

    }
li{
    list-style-type: square;
    content:".";  
    color:gray;
}
 #content{
        min-height: 200px;
        margin-bottom: 20px;
        width: 1100px;
        // border: 1px solid black;
        margin-left: auto;
        margin-right: auto;
        display: flex;
        .msg{
            width: 540px;
            margin-left: 25px;
        }
    }
    
</style>