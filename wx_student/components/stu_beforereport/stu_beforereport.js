let app=getApp()
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
    token:"",
    info:"",
    trafficlist:["公交","出租车","私家车"],
    index:"",
    entouragelist:["1人","2人","3人","4人","5人","6人","7人","8人"],
    index1:"",
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
    setArrivetime(e){
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        "info.arrivetime": e.detail.value
      })
    },
    setTraffic(e){
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        "info.traffic": this.data.trafficlist[e.detail.value]
      })
    },
    setEntourage(e){
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        "info.entourage": this.data.entouragelist[e.detail.value]
      })
    },
    setOther(e){
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        "info.other": e.detail.value
      })
    },
    submit(){
      let that=this
      wx.showModal({  
        title: '提示',  
        content: '确认提交?',  
        success: function(res) {  
            if (res.confirm) {  
            console.log('用户点击确定') 
            let list=that.data.info
            list.reportinfo="已完成"
            if(list.entourage=="请选择"){
              list.entourage=""
            }
            if(list.traffic=="请选择"){
              list.traffic=""
            }
            if(list.arrivetime=="请选择"){
              list.arrivetime=""
            }
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
                    title: '提交成功',
                    duration:2000,
                    mask:true,
                    icon:'success', 
                 })
                 that.setData({
                  "info.reportinfo":"已完成",
                  info:list
                })
                 wx.setStorageSync('user', JSON.stringify(list))
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
        that.setData({
          "index":that.data.trafficlist.findIndex(i=>i===that.data.info.traffic)
        })
        that.setData({
          "index1":that.data.entouragelist.findIndex(i=>i===that.data.info.entourage)
        })
        if(that.data.info.reportinfo!="已完成"){
          that.setData({
            "info.arrivetime":"请选择",
            "info.traffic":"请选择",
            "info.entourage":"请选择",
          })
        }
        }
      },
      fail (res) {
        console.log(res)
      }
    }) 
    // console.log(this.data.info)
    
  },
  moved: function(){},
  detached: function(){},

})

