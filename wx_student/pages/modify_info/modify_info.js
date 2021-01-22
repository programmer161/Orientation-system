// pages/modify_info/modify_info.js
Page({
 
  /**
   * 页面的初始数据
   */
  data: 
  {
    sexlist: ['男', '女'],
    index:"",
	  info:{}
  },
  setSex: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.sexlist[e.detail.value])
    this.setData({
      "info.sex": this.data.sexlist[e.detail.value]
    })
  },
  setBirth: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      "info.birth": e.detail.value
    })
  },
  save:function(e){
    let that=this
    wx.showModal({
      title: '提示',
      content: '确认保存',
      success: function (res) {
        if (res.confirm) {
          
          console.log(that.data.info)
          wx.request({
            url: wx.$url+'/users/update',
            data:that.data.info,
            method:"POST",
            header:{
              "Authorization":"Bearer "+that.data.token
            },
            success (res) {
              console.log(res.data)
              if(res.data.code===0){
                wx.showToast({
                  title: '修改成功',//提示文字
                  duration:2000,//显示时长
                  mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
                  icon:'success', //图标，支持"success"、"loading"  
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
  },
  setemail: function (e) {
    console.log(e.detail.value)
    this.setData({
      "info.email":e.detail.value
    })
  },
  setName: function (e) {
    this.setData({
      "info.name": e.detail.value
    })
    console.log(this.data.info.name)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      token: wx.getStorageSync('token'),
      info:JSON.parse(wx.getStorageSync('user'))
    })
    this.setData({
      "index":this.data.sexlist.findIndex(i=>i===this.data.info.sex)
    })
  },
  
})