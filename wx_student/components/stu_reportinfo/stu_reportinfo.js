var app = getApp()
//(Component构造器)
Component({
  //一些组件选项
  options: {
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },

  //组件的对外属性，属性设置中可包含三个字段,type 表示属性类型、 value 表示属性初始值、 observer 表示属性值被更改时的响应函数    
  properties: {
    // 活动封面
    coverUrl: {
      type: String,
      value: "http://img.youpenglai.cn/meetingpic/0b24376c43b1c372076aa65253b2f0ca123.jpg"
    },

    // 活动标题
    activityTitle: {
      type: String,
      value: "我是Title"
    },

    // 打卡时间范围
    signTimeRange: {
      type: String,
      value: "00:00-23:59"
    },

    // 是否是组织者
    organizerStatus: {
      type: Number,
      value: 0
    },

    // 是否是组织者
    signTimes: {
      type: Number,
      value: 0
    },

    // 打卡状态
    signStatus: {
      type: Number,
      value: 0
    }
  },
  //组件的内部数据，和 properties 一同用于组件的模版渲染
  data:{
    isShowOrganizer:false,
    info:{},
    token:"",
    id:""
  },
  //组件的方法，包括事件响应函数和任意的自定义方法 
  methods: {
    // 跳转活动详情  
    activityDetailTap: function(e) {
      var currentPosition = e.currentTarget.dataset.currentPosition
      this.triggerEvent('signEvent', {
        'currentPosition': currentPosition
      })
    },
    submit: function(){
      let that=this
      wx.showModal({  
        title: '提示',  
        content: '确认无误',  
        success: function(res) {  
            if (res.confirm) {  
            console.log('用户点击确定') 
            let list=that.data.info
            list.info="已完成"
            wx.request({
              url: wx.$url+'/users/update',
              data:list,
              method:"POST",
              header:{
                "Authorization":"Bearer "+that.data.token
              },
              success (res) {
                console.log(res.data)
                if(res.data.code===0){
                  wx.showToast({
                    title: '确认成功',//提示文字
                    duration:2000,//显示时长
                    mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
                    icon:'success', //图标，支持"success"、"loading"  
                 })
                 that.setData({
                  "info.info":"已完成"
                })
                 wx.setStorageSync('user', JSON.stringify(that.data.info))
                }
              },
              fail (res) {
                console.log(res)
              }
            }) 
            } else if (res.cancel) {  
            console.log('用户点击取消')  
            }  
        }  
    })  
    }
    
  },
  // 组件生命周期函数，可以为函数，或一个在methods段中定义的方法名
  attached: function(){
    this.setData({
      token: wx.getStorageSync('token'),
      id:app.globalData.id
    })
    let that=this
    wx.request({
      url: wx.$url+'/users/findstudentbyusername?username='+that.data.id,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        if(res.data.code===0){
         that.setData({
          info:res.data.data
        })
        console.log(that.data.info)
        }
      },
      fail (res) {
        console.log(res)
      }
    }) 
  },
  pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function () { 
      this.setData({
        info:JSON.parse(wx.getStorageSync('user'))
      })
      // console.log(this.data.info)
    },
    hide: function () { },
    resize: function () { },
  },
  moved: function(){},
  detached: function(){},
  

})

