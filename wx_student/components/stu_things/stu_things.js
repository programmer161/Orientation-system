//(Component构造器)
Component({
  //一些组件选项
  options: {
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },

  //组件的对外属性，属性设置中可包含三个字段,type 表示属性类型、 value 表示属性初始值、 observer 表示属性值被更改时的响应函数    
  properties: {
    // 活动封面
    coverUrl: {
      type: String,
      value: "http://img.youpenglai.cn/meetingpic/0b24376c43b1c372076aa65253b2f0ca123.jpg"
    },

    // 活动标题
    activityTitle: {
      type: String,
      value: "我是Title"
    },

    // 打卡时间范围
    signTimeRange: {
      type: String,
      value: "00:00-23:59"
    },

    // 是否是组织者
    organizerStatus: {
      type: Number,
      value: 0
    },

    // 是否是组织者
    signTimes: {
      type: Number,
      value: 0
    },

    // 打卡状态
    signStatus: {
      type: Number,
      value: 0
    }
  },
  //组件的内部数据，和 properties 一同用于组件的模版渲染
  data:{
    isShowOrganizer:false,
    manager_count:1,
    students:[],
    userId:''
  },
  //组件的方法，包括事件响应函数和任意的自定义方法 
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
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            'Authorization':'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MTEzODYwMTUsInN1YiI6IjIwMTgxMTAxMDIiLCJpYXQiOjE2MTA3ODEyMTUzNDB9.OcGklbkIas-m8Kzuaz2zbKBbiT81YE9VEdVzbgu9gxt6PIdzyFW7dIfUINUSxBsZhhmYceEWawZa7gOedTSqlQ'
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
          url: wx.$url+'/users/findstudentbyusername',
          method:'POST',
          data:{
            username:that.data.userId
          },
          header:{
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            'Authorization':'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MTEzODYwMTUsInN1YiI6IjIwMTgxMTAxMDIiLCJpYXQiOjE2MTA3ODEyMTUzNDB9.OcGklbkIas-m8Kzuaz2zbKBbiT81YE9VEdVzbgu9gxt6PIdzyFW7dIfUINUSxBsZhhmYceEWawZa7gOedTSqlQ'
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
      console.log(event.currentTarget.id)
    },
    // 跳转活动详情  
    activityDetailTap: function(e) {
      var currentPosition = e.currentTarget.dataset.currentPosition
      this.triggerEvent('signEvent', {
        'currentPosition': currentPosition
      })
    }
  },
  created(){
    
  },
  // 组件生命周期函数，可以为函数，或一个在methods段中定义的方法名
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
        'Authorization':'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MTEzODYwMTUsInN1YiI6IjIwMTgxMTAxMDIiLCJpYXQiOjE2MTA3ODEyMTUzNDB9.OcGklbkIas-m8Kzuaz2zbKBbiT81YE9VEdVzbgu9gxt6PIdzyFW7dIfUINUSxBsZhhmYceEWawZa7gOedTSqlQ'
      },
      success:function(res){
        if(res.data.code==0){
          that.setData({
            // students:res.data.data
            students:[{
              "userName":"2018",
              "name":"ejufihwe",
              "major":"asdas",
              "college":"asdas"
            },
            {
              "userName":"2019",
              "name":"ejufihwe",
              "major":"asdas",
              "college":"asdas"
            }
          ],
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
  moved: function(){},
  detached: function(){},

})

