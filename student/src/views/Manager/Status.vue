<template>
    <div id="big">
        
        <div id="form">
            <p id="title">报到状态统计</p>
            
            <div>
                <div id="form1"></div>
            </div>
            <div>
                <div id="form2"></div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            name:"杨洋",
            sum:430,
            finished:200,
        }
    },
    methods:{
        
    },
    mounted(){
        // 引入 ECharts 主模块
        var echarts = require('echarts/lib/echarts');
        // 引入柱状图
        require('echarts/lib/chart/bar');
        // 引入提示框和标题组件
        require('echarts/lib/component/tooltip');
        require('echarts/lib/component/title');

        // 基于准备好的dom，初始化echarts实例
        let param = new URLSearchParams()
            param.append("username",this.$store.state.id);
        var chart1 = echarts.init(document.getElementById('form1'));
        var chart2 = echarts.init(document.getElementById('form2'));
        this.axios.post(this.$apiUrl +"/users/getreportstatus",param,{
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
                chart1.setOption({
                    title:{
                        show:true,
                        text:"已完成人数",
                        x:'center', 
                        y: '20px', 
                        textStyle: { 
                            fontFamily: 'Arial',
                            fontSize: 20,
                            fontStyle: 'normal',
                            fontWeight: 'bold',
                        },
                    },
                    xAxis: {
                        type: 'category',
                        data: ['信息确认', '报道信息', '现场报道', '事务办理','学费支付']
                    },
                    yAxis: {},
                    series: [
                        {
                            type: 'bar',
                            name: '已完成人数',
                            data: [res.data.data.infocount, res.data.data.reportinfocount, res.data.data.reportnowcount, res.data.data.affaircount,res.data.data.paycount],
                            itemStyle:{
                                normal:{
                                    color:function(params){
                                        var colorList = ['#cca272','#74608f','#d7a02b','#c8ba23',];
                                        return colorList[params.dataIndex]
                                    }
                                }
                            }
                        }
                    ],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        },
                        formatter: function (params) {
                            return params[0].data+'人';
                        }
                    },
                });
                // var params=[res.data.detail.sum-res.data.detail.infro, res.data.detail.sum-res.data.detail.reportinfro, res.data.detail.sum-res.data.detail.reportnow, res.data.detail.sum-res.data.detail.affair,res.data.detail.sum-res.data.detail.pay];
                chart2.setOption({
                    title:{
                        show:true,
                        text:"未完成人数",
                        x:'center', 
                        y: '20px', 
                        textStyle: { 
                            fontFamily: 'Arial',
                            fontSize: 20,
                            fontStyle: 'normal',
                            fontWeight: 'bold',
                        },
                    },
                    xAxis: {
                        type: 'category',
                        data: ['信息确认', '报道信息', '现场报道', '事务办理','学费支付']
                    },
                    yAxis: {},
                    series: [
                        {
                            type: 'bar',
                            name: '未完成人数',
                            data: [res.data.data.sum-res.data.data.infocount, res.data.data.sum-res.data.data.reportinfocount, res.data.data.sum-res.data.data.reportnowcount, res.data.data.sum-res.data.data.affaircount,res.data.data.sum-res.data.data.paycount],
                            itemStyle:{
                                normal:{
                                    color:function(params){
                                        var colorList = ['#cca272','#74608f','#d7a02b','#c8ba23',];
                                        return colorList[params.dataIndex]
                                    }
                                }
                            }
                        }
                    ],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        },
                        formatter: function (params) {
                            return params[0].data+'人';
                        }
                    },
                });
            }
        });
    }
}
</script>


<style lang="scss" scoped>
#title {
    font-size: 30px;
    height: 70px;
    line-height: 70px;
    font-weight: normal;
    text-align: center;
    border-bottom: 0.5px solid gainsboro;
}
        #form{
            // margin-top: 20px;
            // padding-top: 20px;
            min-width: 600px;
            margin-left: auto;
            margin-right: auto;
            // padding-left: 10px;
            background-color: #F4F8F6;
            div{
                width: 50%;
                display: inline-block;
                #form1{
                    width:90%;
                    height: 280px;
                    display: inline-block;
                    // border:1px solid black;
                }
                #form2{
                    width:90%;
                    height: 280px;
                    display: inline-block;
                    
                }
            }        
        }
    p{
        font-size: 30px;
        text-align: center;
        font-weight: bold;
        height: 60px;
        line-height: 60px;
        border-bottom: 1px dashed black;
    }
    
</style>