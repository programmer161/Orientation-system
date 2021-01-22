<template>
    <div id="big">
        <p id="title">新生详情统计</p>
        <div id="form">
            <div>
                <div id="form1"></div>
            </div>
            <div>
                <div id="form2"></div>
            </div>
        </div>
        <div id="btn">
            <el-button @click="reportAll" icon="el-icon-copy-document">导出全部名单</el-button>
            <el-button @click="reportFinished" icon="el-icon-copy-document">导出已完成报到名单</el-button>
            <el-button @click="reportUnFinished" icon="el-icon-copy-document">导出未完成报到名单</el-button>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            name:"杨洋",
            sum:'',
            finished:'',
        }
    },
    methods:{
        reportAll(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==21){
                    index=1;
                }
            }
            if(index==1){
            this.axios.get(this.$apiUrl +"/users/findsdtuentall",{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
                if(res.data.code!=0){
                    this.$message.error({
                        message: res.data.msg,
                        duration:1500
                    });
                    // this.$router.push('/login');
                }else{
                    // console.log(res.data)
                    const list=res.data.data;
                    require.ensure([], () => {
                        const {
                            export_json_to_excel
                        } = require('../../assets/js/Export2Excel');
                        const tHeader = ['学号','姓名','性别', '学院', '专业','班级','信息确认','报到信息','现场报到','事务办理','学费支付'];
                        const filterVal = ['userName','name','sex','college','major','classes','info','reportinfo','reportnow','affair','pay'];
                        // const list = this.test;
                        // console.log(list)
                        const data = this.formatJson(filterVal, list);
                        export_json_to_excel(tHeader, data, '全部名单');
                    })
                }
            }).catch(err=>{
                console.log(err);
            });
            }else{
                this.$message.error({
                    message: "无该权限！",
                    duration:1500
                });
            }
        },
        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]))
        },
        reportFinished(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==22){
                    index=1;
                }
            }
            if(index==1){
            this.axios.get(this.$apiUrl +"/users/getfinished",{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
                if(res.data.code!=0){
                    this.$message.error({
                        message: res.data.msg,
                        duration:1500
                    });
                    // this.$router.push('/login');
                }else{
                    // console.log(res.data)
                    const list=res.data.data;
                    require.ensure([], () => {
                        const {
                            export_json_to_excel
                        } = require('../../assets/js/Export2Excel');
                        const tHeader = ['学号','姓名','性别', '学院', '专业','班级','信息确认','报到信息','现场报到','事务办理','学费支付'];
                        const filterVal = ['userName','name','sex','college', 'major','classes','info','reportinfo','reportnow','affair','pay'];
                        // const list = this.test;
                        // console.log(list)
                        const data = this.formatJson(filterVal, list);
                        export_json_to_excel(tHeader, data, '已完成报到名单');
                    })
                }
            }).catch(err=>{
                console.log(err);
            });
            }else{
                this.$message.error({
                    message: "无该权限！",
                    duration:1500
                });
            }
        },
        reportUnFinished(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==23){
                    index=1;
                }
            }
            if(index==1){
            this.axios.get(this.$apiUrl +"/users/getunfinished",{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
                if(res.data.code!=0){
                    this.$message.error({
                        message: res.data.msg,
                        duration:1500
                    });
                    // this.$router.push('/login');
                }else{
                    // console.log(res.data)
                    const list=res.data.data;
                    require.ensure([], () => {
                        const {
                            export_json_to_excel
                        } = require('../../assets/js/Export2Excel');
                        const tHeader = ['学号','姓名','性别', '学院', '专业','班级','信息确认','报到信息','现场报到','事务办理','学费支付'];
                        const filterVal = ['userName','name','sex','college', 'major','classes','info','reportinfo','reportnow','affair','pay'];
                        // const list = this.test;
                        // console.log(list)
                        const data = this.formatJson(filterVal, list);
                        export_json_to_excel(tHeader, data, '未完成报到名单');
                    })
                }
            }).catch(err=>{
                console.log(err);
            });
            }else{
                this.$message.error({
                    message: "无该权限！",
                    duration:1500
                });
            }
        }
    },
    mounted(){
        // 引入 ECharts 主模块
        var echarts = require('echarts/lib/echarts');
        // 引入柱状图
        require('echarts/lib/chart/pie');
        // 引入提示框和标题组件
        require('echarts/lib/component/tooltip');
        require('echarts/lib/component/title');

        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('form1'));
        var myChart2 = echarts.init(document.getElementById('form2'));
        // 绘制图表
        // var obj = {
        //     'id': this.$store.state.id
        // };
        let param = new URLSearchParams()
            param.append("username",this.$store.state.id);
        this.axios.post(this.$apiUrl +"/users/getsumfinished",param,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
            console.log(res.data)
            if(res.data.code!=0){
                this.$message.error({
                    message: res.data.msg,
                    duration:1500
                });
                // this.$router.push('/login');
            }else{
                // 
                this.sum=res.data.data.sum;
                this.finished=res.data.data.finished;

                myChart1.setOption({
                    title: {
                        text: '新生报到状况', //主标题
                        // subtext: '纯属虚构', //副标题
                        x: 'center', //x轴方向对齐方式
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['报道完成人数', '报道未完成人数']
                    },
                    series: [{
                        name: '新生报到状况',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '55%'],
                        data: [
                            {
                                // value: this.manager_sum,
                                value:res.data.data.finishedcount,
                                name: '报道完成人数',
                                itemStyle:{
                                    normal:{
                                        color:"#AACA18"
                                    }
                                }
                            },
                            {
                                value: res.data.data.sum-res.data.data.finishedcount,
                                name: '报道未完成人数',
                                itemStyle:{
                                    normal:{
                                        color:"#C38F63"
                                    }
                                }
                            },
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            normal: {
                                formatter: '{b}:{c}',
                                position: 'inside'
                            },
                        }
                    }],
                    
                });
                
            }
        }).catch(err=>{
            console.log(err);
        });
        let param1 = new URLSearchParams()
            param1.append("username",this.$store.state.id);
        this.axios.post(this.$apiUrl +"/users/getsexnumber",param1,{
            headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
        }).then(res=>{
            console.log(res.data)
            if(res.data.code!=0){
                this.$message.error({
                    message: res.data.msg,
                    duration:1500
                });
                // this.$router.push('/login');
            }else{
                myChart2.setOption({
                    title: {
                        text: '性别分布图', //主标题
                        // subtext: '纯属虚构', //副标题
                        x: 'center', //x轴方向对齐方式
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['男', '女']
                    },
                    series: [{
                        name: '性别分布图',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '55%'],
                        data: [
                            {
                                // value: this.manager_sum,
                                value:res.data.data.mc,
                                name: '男',
                                itemStyle:{
                                    normal:{
                                        color:"#00BFBF"
                                    }
                                }
                            },
                            {
                                value: res.data.data.fmc,
                                name: '女',
                                itemStyle:{
                                    normal:{
                                        color:"#F59A23"
                                    }
                                }
                            },
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            normal: {
                                formatter: '{b}:{c}',
                                position: 'inside'
                            },
                        }
                    }],
                    
                });
            }
        }).catch(err=>{
            console.log(err);
        });
    }
}
</script>


<style lang="scss" scoped>
.None{
    display: none;
}
#title {
    font-size: 30px;
    height: 70px;
    line-height: 70px;
    font-weight: normal;
    text-align: center;
    border-bottom: 0.5px solid gainsboro;
}
    #big{
        width:1000px;
        min-height:400px;
        background-color: #F4F8F6;
        // margin-left: 10px;
        // margin-top: 20px;
        // padding-top: 10px;
        padding-bottom: 20px;
        #form{
            margin-top: 50px;
            // padding-top: 20px;
            width: 100%;
            min-height: 330px;
            // margin-left: auto;
            // margin-right: auto;
            // padding-left: 10px;
            // background-color: #F4F8F6;
            div{
                width: 50%;
                height: 330px;
                display: inline-block;
                #form1{
                    width:90%;
                    min-height: 300px;
                    // background-color: red;
                    display: inline-block;
                    // border:1px solid black;
                }
                #form2{
                    width:90%;
                    min-height: 300px;
                    display: inline-block;
                    
                }
            } 
        }
        #btn{
            margin-top: 20px;
            button{
                width: 180px;
                height: 40px;
                background-color: #409eff;
                opacity:  0.9;
                color: white;
                margin-left: 13%;
                outline: none;
                border: none;
                border-radius: 0.5em;
                cursor: pointer;
            }
        }
    }
        span{
            color: #02A7F0;
        }
    // p{
        // margin-top: 20px;
        // margin-left: 5%;
    // }
    
</style>