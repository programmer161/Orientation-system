
const app=getApp();
// pages/updatepwd/updatepwd.js
Page({
 
  /**
   * 页面的初始数据
   */
  data: {
  
  },
  formSubmit:function(e){
    // console.log(e);
    var oldpwd=e.detail.value.oldpwd;
    var newpwd = e.detail.value.newpwd;
    var newpwd2 = e.detail.value.newpwd2;
    if(oldpwd=='' || newpwd=='' || newpwd2==''){
      wx.showToast({
        title: '密码不能为空',
        icon:'none',
        duration:1000
      })
    }else if(newpwd!=newpwd2){
      wx.showToast({
        title: '两次输入不一致',
        icon: 'none',
        duration: 1000
      })
    }else{
      // console.log(wx.getAccountInfoSync('token'))
      // var url = app.globalData.url.setpassword;
      wx.request({
        url: wx.$url+'/users/updatepwd?username='+app.globalData.id+'&password='+oldpwd+"&firstPassword="+newpwd+"&secondPassword="+newpwd2, 
        method:'POST',
        header:{
          "Authorization":"Bearer "+wx.getStorageSync('token')
        },
        success: (res) => {
          console.log(res.data);
          if(res.data.code!=0){
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000
            })
          }else{
            wx.showToast({
              title: '修改成功',//提示文字
              duration:2000,//显示时长
              mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
              icon:'success', //图标，支持"success"、"loading"  
           })
          }
         
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