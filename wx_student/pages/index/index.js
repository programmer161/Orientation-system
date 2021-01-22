// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabBar: {
      "color": "#9E9E9E",
      "selectedColor": "#1296DB",
      "backgroundColor": "#fff",
      "borderStyle": "#ccc",
      "list": [{
        "index":0,
        "pagePath": "../home/home",
        "text": "首页",
        "iconPath": "../../icon/home.png",
        "selectedIconPath": "../../icon/home1.png",
        active: true,
        flag:true
      },{
        "index":1,
        "pagePath": "../report/report",
        "text": "报到流程",
        "iconPath": "../../icon/report.png",
        "selectedIconPath": "../../icon/report1.png",
        active: false,
        flag:false
      },
      {
        "index":2,
        "pagePath": "../info/info",
        "text": "用户管理",
        "iconPath": "../../icon/usermanage.png",
        "selectedIconPath": "../../icon/usermanage1.png",
        active: false,
        flag:false
      }, {
        "index":3,
        "pagePath": "../info/info",
        "text": "个人中心",
        "iconPath": "../../icon/user.png",
        "selectedIconPath": "../../icon/user1.png",
        active: false,
        flag:true
      }]
    },
    index1:0,
  },
  onClick(event){
    let index = event.currentTarget.dataset.index;
    // console.log(index)
    let _index=this.data.index1
    let active = `tabBar.list[${index}].active`;
    let _active = `tabBar.list[${_index}].active`;
    this.setData({
      [active]:!this.data.tabBar.list[index].active,
      [_active]:!this.data.tabBar.list[_index].active
    })
    this.setData({
      index1:index
    })
    // wx.navigateTo({
    //   url: this.data.tabBar.list[index].pagePath,
    // })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
console.log(wx.getStorageSync('author'))
this.setData({
  "tabBar.list[1].flag":wx.getStorageSync('author')=="ROLE_Student",
  "tabBar.list[2].flag":wx.getStorageSync('author')!="ROLE_Student"
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