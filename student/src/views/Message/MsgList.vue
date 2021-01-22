<template>
    <div>
        <school-msg v-if="this.$store.state.msgtype=='学校通知'"></school-msg>
        <report-msg v-if="this.$store.state.msgtype=='报到须知'"></report-msg>
        <school-strategy v-if="this.$store.state.msgtype=='校园攻略'"></school-strategy>
        <pay-msg v-if="this.$store.state.msgtype=='缴费须知'"></pay-msg>
    </div>

</template>

<script>
import SchoolMsg from '../../components/SchoolMsg.vue'
import SchoolStrategy from '../../components/SchoolStrategy.vue'
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
            reportmsglag:0,
            schoolmsgflag:1,
            schoolstrategyflag:0,
            paymsgflag:0
        }
    },
    methods:{
        toLogin(){
            // this.$router.push('/login');
        },
        
    },
    mounted(){
        // document.body.style.backgroundImage="url(" + require("../assets/sicnu.jpg") + ")";
       
    }
}
</script>

<style lang="scss" scoped>
    
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