var app = getApp()
// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:"",
    password:""
  },
  getUsername(e){
    this.setData({
      "username": e.detail.value
    })
  },
  getPassword(e){
    this.setData({
      "password": e.detail.value
    })
  },
  forget(){
    wx.navigateTo({
      url: '../findpassword/findpassword',
    })
  },
  login(){
    // console.log(this.data.password)
    wx.request({
      url: wx.$url+'/login',
      method:"POST",
      data:{
          "username": this.data.username,
          "password": this.data.password
      },
      success (res) {
        console.log(res.data)
        if(res.data.code==0){
          let data=res.data.data.token
          app.globalData.id=res.data.data.username
          console.log(res.data)
          wx.setStorageSync('author', res.data.data.auth[0].authority)
          console.log(res.data.data.auth[0].authority)
          wx.setStorageSync('user', JSON.stringify(res.data.data.user[0]))
          wx.setStorageSync('token', data)
          wx.reLaunch({
            url: '/pages/index/index',
          })
        }else{
          wx.showToast({
            title: res.data.msg.substring(5,10),
            icon: 'none',
            duration: 2000
          })
        }
      },
      fail (res) {
        console.log(res)
      }
      
    })
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})