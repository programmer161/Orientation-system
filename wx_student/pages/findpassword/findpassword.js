// pages/findpassword/findpassword.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    password:"",
    email:"",
    code:"",
    repassword:""
  },
  getPwd(e){
    this.setData({
      "password": e.detail.value
    })
  },
  getRepwd(e){
    this.setData({
      "repassword": e.detail.value
    })
  },
  getCode(e){
    this.setData({
      "code": e.detail.value
    })
  },
  getEmail(e){
    this.setData({
      "email": e.detail.value
    })
  },
  sendCode(){
    console.log(this.data.email)
    if(this.data.email==""){
      wx.showToast({
        title: "邮箱不能为空",
        icon: 'none',
        duration: 2000
      })
    }else{
      let  that=this
      wx.request({
        url: wx.$url+'/getpwd/getcode?receiver='+that.data.email,
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        success (res) {
          console.log(res.data)
          if(res.data.code!=0){
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000
            })
          }
        },
        fail (res) {
          console.log(res)
        }
        
      })
    }
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