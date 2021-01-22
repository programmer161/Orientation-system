import * as echarts from '../../ec-canvas/echarts';
 
const app = getApp();
//初始化图表--饼图
function init_pieCharts(canvas, width, height){
  // 初始化图表
  const pieChart = echarts.init(canvas, null, {
    width: 190,
    height: 190
  });
  var finished = 0
  var unfinished = 0
  wx.request({
    url: wx.$url+'/users/getsumfinished',
    method:'POST',
    data:{
      username:app.globalData.id
    },
    header:{
      'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      "Authorization":"Bearer "+wx.getStorageSync('token')
    },
    success:function(res){
      console.log(res.data)
      if(res.data.code===0){
        finished = res.data.data.finishedcount
        unfinished = res.data.data.sum-res.data.data.finishedcount
        var option = {
          title: {
              x: 'center', //x轴方向对齐方式
          },
          tooltip: {
              trigger: 'item',
              formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          series: [{
              name: '新生报到状况',
              type: 'pie',
              radius: '80%',
              center: ['50%', '55%'],
              data: [
                  {
                      // value: this.manager_sum,
                      value:finished,
                      name: '已完成',
                      itemStyle:{
                          normal:{
                              color:"#AACA18"
                          }
                      }
                  },
                  {
                      value: unfinished,
                      name: '未完成',
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
        }
        pieChart.setOption(option);
      }else{
        console.log('用户信息获取失败')
      }
    },
    fail:function(err){
      console.log(err)
    }
  })
  // 注意这里一定要返回 chart 实例，否则会影响事件处理等
  return pieChart;
}

function init_pieCharts1(canvas, width, height){
  // 初始化图表
  const pieChart = echarts.init(canvas, null, {
    width: 500,
    height: 500
  });
  var one = 0
  var two = 0
  var three = 0
  var four = 0
  var five = 0
  wx.request({
    url: wx.$url+'/users/getreportstatus',
    method:'POST',
    data:{
      username:app.globalData.id
    },
    header:{
      'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      "Authorization":"Bearer "+wx.getStorageSync('token')
    },
    success:function(res){
      console.log(res.data)
      if(res.data.code===0){
        one = res.data.data.infocount
        two = res.data.data.reportinfocount
        three = res.data.data.reportnowcount
        four = res.data.data.affaircount
        five = res.data.data.paycount
        var option = {
          title:{
              show:true,
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
                  data: [one,two,three,four,five],
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
        };
        pieChart.setOption(option);
      }else{
        console.log('用户信息获取失败')
      }
    },
    fail:function(err){
      console.log(err)
    }
  })
  return pieChart;
}

function init_pieCharts2(canvas, width, height){
  // 初始化图表
  const pieChart = echarts.init(canvas, null, {
    width: 500,
    height: 500
  });
  var one = 0
  var two = 0
  var three = 0
  var four = 0
  var five = 0
  wx.request({
    url: wx.$url+'/users/getreportstatus',
    method:'POST',
    data:{
      username:app.globalData.id
    },
    header:{
      'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      "Authorization":"Bearer "+wx.getStorageSync('token')
    },
    success:function(res){
      console.log(res.data)
      if(res.data.code===0){
        one = res.data.data.sum-res.data.data.infocount
        two = res.data.data.sum-res.data.data.reportinfocount
        three = res.data.data.sum-res.data.data.reportnowcount
        four = res.data.data.sum-res.data.data.affaircount
        five = res.data.data.sum-res.data.data.paycount
        var option = {
          title:{
              show:true,
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
                  data: [one,two,three,four,five],
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
        };
        pieChart.setOption(option);
      }else{
        console.log('用户信息获取失败')
      }
    },
    fail:function(err){
      console.log(err)
    }
  })
  return pieChart;
}

Page({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    ec: {
      onInit: init_pieCharts
    },
    eh:{
      onInit: init_pieCharts1
    },
    et:{
      onInit: init_pieCharts2
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {

  },
})
