// pages/notice/notice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    noticeValue:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp()
    var that = this
    var id = options.id
    var Url
    if(app.globalData.notice_type==="学校通知"){
      Url = wx.$url+'/message/getvalues'
    }else if(app.globalData.notice_type==="校园攻略"){
      Url = wx.$url+'/trategy/getvalues'
    }else if(app.globalData.notice_type==="缴费须知"){
      Url = wx.$url+'/paynotice/getvalues'
    }else if(app.globalData.notice_type==="报到须知"){
      Url = wx.$url+'/reportnotice/getvalues'
    }
    wx.request({
      url: Url+'?id='+id,
      method:'POST',
      data:{},
      header:{
        'content-type':wx.getStorageSync('token')
      },
      success:function(res){
        if(res.data.code===0){
          for(var i=0;i<res.data.data.length;i++){
            var notice_value = res.data.data[i].tvalues
            that.data.noticeValue=that.data.noticeValue.concat(notice_value)
            that.data.noticeValue = that.data.noticeValue.replace(/<.+?>|&nbsp;|&ldquo|&rdquo;|&mdash;/g,'   ')
            that.setData({
              noticeValue: that.data.noticeValue
            })
          }
        }else{
          console.log("信息获取失败")
        }
      },
      fail:function(err){
        console.log(err)
      }
    })
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