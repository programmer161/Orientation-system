let app=getApp()
Component({

  /**
   * 页面的初始数据
   */
  data: {
    token:"",
    id:"",
    info:{},
  },

  methods: {
    modify:function(e){
      //页面跳转
      wx.navigateTo({
        url: '../../pages/modify_info/modify_info',
      })
    },
    resetpwd:function(){
      wx.navigateTo({
        url: '../../pages/updatepwd/updatepwd',
      })
    },
    exit(){
      wx.showModal({  
        title: '提示',  
        content: '确认退出？',  
        success: function(res) {  
            if (res.confirm) {  
              console.log('用户点击确定')  
              wx.request({
                url: wx.$url+'/logout',
                header:{
                  "Authorization":"Bearer "+wx.getStorageSync('token')
                },
                success (res) {
                  console.log(res.data)
                  if(res.data.code===0){
                    wx.showToast({
                      title: '退出成功',//提示文字
                      duration:2000,//显示时长
                      mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
                      icon:'success', //图标，支持"success"、"loading"  
                   })
                   wx.removeStorageSync('token')
                   wx.removeStorageSync('user')
                   wx.redirectTo({
                     url: '../../pages/login/login',
                   })
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
  attached(){
    this.setData({
      token: wx.getStorageSync('token'),
      id:app.globalData.id,
      info:JSON.parse(wx.getStorageSync('user'))
    })
  },
  pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function () { 
      this.setData({
        token: wx.getStorageSync('token'),
        id:app.globalData.id,
        info:JSON.parse(wx.getStorageSync('user'))
      })
    },
    hide: function () { },
    resize: function () { },
  },
})