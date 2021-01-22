// app.js
App({
  globalData: {
    id:"",
    info:{},
    index:0,
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
        flag:true
      }, {
        "index":2,
        "pagePath": "../info/info",
        "text": "个人中心",
        "iconPath": "../../icon/user.png",
        "selectedIconPath": "../../icon/user1.png",
        active: false,
        flag:true
      }]
    },
  },
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    wx.$url = 'http://localhost:8080'
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  }
})
