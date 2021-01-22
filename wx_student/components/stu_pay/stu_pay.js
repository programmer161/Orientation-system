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
    tempFilePaths: '',
    tempFilePaths_other:''
  },
  //组件的方法，包括事件响应函数和任意的自定义方法 
  methods: {
    // 跳转活动详情  
    chooseimage: function chooseimage() {
      var that = this;
      wx.chooseImage({
        count: 1, // 默认9  
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
        success: function success(res) {
          wx.showToast({
            title: '正在上传...',
            icon: 'loading',
            mask: true,
            duration: 1000
          });
          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片  
          that.setData({
            tempFilePaths: res.tempFilePaths
          });
          var reader1 = "data:image/png;base64,"+wx.getFileSystemManager().readFileSync(res.tempFilePaths[0],"base64")
          wx.request({
            url: wx.$url+'/fileoperation/uppayimg',
            method:'POST',
            data:{
              username:"2018110103",
              payimg:reader1
            },
            header:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
              'Authorization':wx.getStorageSync('token')
            },
            success:function(res){
              if(res.data.code===0){
                console.log("图片上传成功")
              }else{
                console.log("图片上传失败")
              }
            },
            fail:function(err){
              console.log(err)
            }
          })
        }
      });
    },
    chooseimage_other: function chooseimage() {
      var _this = this;
      wx.chooseImage({
        count: 1, // 默认9  
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
        success: function success(res) {
          wx.showToast({
            title: '正在上传...',
            icon: 'loading',
            mask: true,
            duration: 1000
          });
          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片  
          _this.setData({
            tempFilePaths_other: res.tempFilePaths
          });
          var reader1 = "data:image/png;base64,"+wx.getFileSystemManager().readFileSync(res.tempFilePaths[0],"base64")
          wx.request({
            url: wx.$url+'/fileoperation/upphoto',
            method:'POST',
            data:{
              username:"2018110103",
              photo:reader1
            },
            header:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
              'Authorization':wx.getStorageSync('token')
            },
            success:function(res){
              if(res.data.code===0){
                console.log("图片上传成功")
              }else{
                console.log("图片上传失败")
              }
            },
            fail:function(err){
              console.log(err)
            }
          })
        }
      });
    }
  },
  created(){
    var that = this
    wx.request({
      url: wx.$url+'/fileoperation/getpayimg',
      method:'POST',
      data:{
        username:'2018110103'
      },
      header:{
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        'Authorization':wx.getStorageSync('token')
      },
      success:function(res){
        if(res.data.code===0){
          var file_data = res.data.data
          that.setData({
            tempFilePaths:file_data
          })
        }else{
          console.log("图片获取失败")
        }
      },
      fail:function(err){
        console.log(err)
      }
    })
    wx.request({
      url: wx.$url+'/fileoperation/getphoto',
      method:'POST',
      data:{
        username:'2018110103'
      },
      header:{
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        'Authorization':wx.getStorageSync('token')
      },
      success:function(res){
        if(res.data.code===0){
          var file_data = res.data.data
          that.setData({
            tempFilePaths_other:file_data
          })
        }else{
          console.log("图片获取失败")
        }
      },
      fail:function(err){
        console.log(err)
      }
    })
  },
  // 组件生命周期函数，可以为函数，或一个在methods段中定义的方法名
  attached: function(){},
  moved: function(){},
  detached: function(){},

})

