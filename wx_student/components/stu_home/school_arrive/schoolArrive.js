// components/stu_home/school_arrive/schoolArrive.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    items:[]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    item_notice(event){
      var app = getApp()
      app.globalData.notice_type = "报到须知"
      wx.navigateTo({
        url: '/pages/notice/notice?id='+event.currentTarget.id,
      })
    }
  },
  created(){
    var that = this
    wx.request({
      url: wx.$url+'/reportnotice/gettitle',
      method:'GET',
      data:{},
      header:{
        'content-type':wx.getStorageSync('token')
      },
      success:function(res){
        if(res.data.code===0){
          for(var i=0;i<res.data.data.length;i++){
            var notice_value = {
              id:res.data.data[i].id,
              title:res.data.data[i].title
            }
            that.data.items=that.data.items.concat(notice_value)
            that.setData({
              items: that.data.items
            })
          }
        }else{
          console.log("信息获取失败")
        }
      }
    })
  }
})
