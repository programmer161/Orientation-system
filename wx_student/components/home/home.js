
// pages/home/home.js
Component({

  /**
   * 页面的初始数据
   */
  data: {
    current_notice: 1
  },

  methods: {
    tab: function(e){
      let index = e.currentTarget.id
      this.setData({
        current_notice: index
      })
    },
  },
  attached(){
    wx.request({
      url: wx.$url+'/trategy/gettitle',
      method:'GET',
      data:{},
      header:{
        'content-type':wx.getStorageSync('token')
      },
      success:function(res){
        console.log(res.data)
      }
    })
  }
})