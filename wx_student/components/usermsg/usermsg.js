// components/usermsg/usermsg.js
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
    isShowOrganizer:false,
    manager_count:"",
    students:[],
    userId:''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    findId(){
      var that = this
      if(that.data.userId===""){
        wx.request({
          url: wx.$url+'/users/findsdtuentall',
          method:'POST',
          data:{
            username:'2018110102'
          },
          header:{
            'Content-Type':wx.getStorageSync('token')
          },
          success:function(res){
            if(res.data.code==0){
              that.setData({
                students:res.data.data,
                manager_count:res.data.data.length
              })
            }else{
              console.log("信息获取失败")
            }
          },
          fail:function(err){
            console.log(err)
          }
        })
      }else{
        wx.request({
          url: wx.$url+'/users/findstudentbyusername?username='+that.data.userId,
          method:'POST',
          header:{
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            "Authorization":"Bearer "+wx.getStorageSync('token')
          },
          success:function(res){
            if(res.data.code==0){
              var obj = []
              obj.push(res.data.data)
              that.setData({
                students:obj
              })
            }else{
              console.log("信息获取失败")
            }
          },
          fail:function(err){
            console.log(err)
          }
        })
      }
    },
    userIdInput:function(e){
      this.setData({
        userId:e.detail.value
      })
    },
    // 获取到相关所需的内容信息，同时进行相关页面的跳转，同时显示相关的学生的详细信息（注：在页面的跳转中需要传递相关的username,
    // 然后通过其所传的username进行相关内容的显示）
    student_details(event){
      wx.navigateTo({
        url: '/pages/userinfo/userinfo?id='+event.currentTarget.dataset.id,
      })
    },
    // 跳转活动详情  
    activityDetailTap: function(e) {
      var currentPosition = e.currentTarget.dataset.currentPosition
      this.triggerEvent('signEvent', {
        'currentPosition': currentPosition
      })
    }
  },
  attached: function(){
    var that = this
    wx.request({
      url: wx.$url+'/users/findsdtuentall',
      method:'POST',
      data:{
        username:'2018110102'
      },
      header:{
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        "Authorization":"Bearer "+wx.getStorageSync('token')
      },
      success:function(res){
        console.log(res.data)
        if(res.data.code==0){
          that.setData({
            students:res.data.data,
            manager_count:res.data.data.length
          })
        }else{
          console.log("信息获取失败")
        }
      },
      fail:function(err){
        console.log(err)
      }
    })
  },
})
