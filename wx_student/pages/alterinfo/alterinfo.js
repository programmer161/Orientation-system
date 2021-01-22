var app = getApp()
// pages/alterinfo/alterinfo.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    sexlist: ['男', '女'],
    index:"",
    info:{},
    date:"info.birth",
    index1:"",
    nationlist:["汉族", "藏族", "维吾尔族", "苗族", "回族"],
    studenttypelist:["应届生","往届生"],
    index2:"",
    politicslist:["共青团员", "群众", "党员"],
    index3:"",
    gradelist:["2017级","2018级","2019级","2020级"],
    index4:"",
    classeslist:["1班", "2班", "3班", "4班", "5班","6班"],
    index5:"",
    xuezhilist:["一年","二年","三年","四年","五年"],
    index6:"",
    studytypelist:["全日制招生", "成人高考", "远程教育", "自考", "国家开发银行"],
    index7:"",
    studystatelist:["注册学籍", "保留学籍", "已离校"],
    lndex8:"",
    drommlist:[],
    dromm:"",
    index9:"",
    tungslist:[],
    tungs:"",
    index10:"",
    collegelist:[],
    college:"",
    majorlist:[],
    major:"",
    token:""
  },

  setUsername: function (e) {
    this.setData({
      "info.userName": e.detail.value
    })
    console.log(this.data.info.userName)
  },
  setName: function (e) {
    this.setData({
      "info.name": e.detail.value
    })
    console.log(this.data.info.name)
  },
  setSex: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.sexlist[e.detail.value])
    this.setData({
      "info.sex": this.data.sexlist[e.detail.value]
    })
  },
  setBirth: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      "info.birth": e.detail.value
    })
  },
  setIdnumber: function (e) {
    this.setData({
      "info.idnumber": e.detail.value
    })
    console.log(this.data.info.idnumber)
  },
  setNation: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.nationlist[e.detail.value])
    this.setData({
      "info.nation": this.data.nationlist[e.detail.value]
    })
  },
  setStudenttype: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.studenttypelist[e.detail.value])
    this.setData({
      "info.studenttype": this.data.studenttypelist[e.detail.value]
    })
  },
  setPolitics: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.politicslist[e.detail.value])
    this.setData({
      "info.politics": this.data.politicslist[e.detail.value]
    })
  },
  setGrade: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.gradelist[e.detail.value])
    this.setData({
      "info.grade": this.data.gradelist[e.detail.value]
    })
  },
  setClasses: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.classeslist[e.detail.value])
    this.setData({
      "info.classes": this.data.classeslist[e.detail.value]
    })
  },
  setXuezhi: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.xuezhilist[e.detail.value])
    this.setData({
      "info.xuezhi": this.data.xuezhilist[e.detail.value]
    })
  },
  setStudytype: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.studytypelist[e.detail.value])
    this.setData({
      "info.studytype": this.data.studytypelist[e.detail.value]
    })
  },
  setStudystate: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.studystatelist[e.detail.value])
    this.setData({
      "info.studystate": this.data.studystatelist[e.detail.value]
    })
  },
  setDromm: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.drommlist[e.detail.value])
    this.setData({
      "dromm": this.data.drommlist[e.detail.value]
    })
    let that=this
    wx.request({
      url: wx.$url+'/dorm/getdorm?apartments='+that.data.dromm,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        console.log(res.data)
        that.setData({
          tungslist:res.data.data
        })
        that.setData({
          "tungs":"请选择"
        })
      },
      fail (res) {
        console.log(res)
      }
    })
  },
  setTungs: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.tungslist[e.detail.value])
    this.setData({
      "tungs": this.data.tungslist[e.detail.value]
    })
  },
  setCollege: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.collegelist[e.detail.value])
    this.setData({
      "info.college": this.data.collegelist[e.detail.value]
    })
    let that=this
    wx.request({
      url: wx.$url+'/college/getmajorbycollege?college='+that.data.info.college,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        console.log(res.data)
        let t=res.data.data
        let j=[]
        let list=[]
        for(j in t){
          list.push(t[j].major)
        }
        that.setData({
          majorlist:list
        })
        that.setData({
          "info.major":"请选择"
        })
      },
      fail (res) {
        console.log(res)
      }
    })
  },
  setMajor: function(e) {
    console.log('picker发送选择改变，携带值为', this.data.majorlist[e.detail.value])
    this.setData({
      "info.major": this.data.majorlist[e.detail.value]
    })
  },
  setInstructname:function (e) {
    this.setData({
      "info.instructname": e.detail.value
    })
    console.log(this.data.info.instructname)
  },
  setInstructphone:function (e) {
    this.setData({
      "info.instructphone": e.detail.value
    })
    console.log(this.data.info.instructphone)
  },
get_dromm(){
  // console.log(this.data.token)
  let that=this
    wx.request({
      url: wx.$url+'/dorm/getapartments',
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        console.log(res.data)
        that.setData({
          drommlist:res.data.data
        })
        let i=0
        // console.log(that.data.info.dromm.substring(0,4))
        // 宿舍楼
        that.setData({
          "index9":that.data.drommlist.findIndex(i=>i===that.data.dromm)
        })
      },
      fail (res) {
        console.log(res)
      }
    })
},
get_tungs(){
  // console.log(this.data.token)
  let that=this
    wx.request({
      url: wx.$url+'/dorm/getdorm?apartments='+that.data.dromm,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        console.log(res.data)
        that.setData({
          tungslist:res.data.data
        })
        let i=0
        // console.log(that.data.info.dromm.substring(0,4))
        // 宿舍楼
        that.setData({
          "index10":that.data.tungslist.findIndex(i=>i===that.data.tungs)
        })
      },
      fail (res) {
        console.log(res)
      }
    })
},
get_college(){
  // console.log(this.data.token)
  let that=this
    wx.request({
      url: wx.$url+'/college/getcollege',
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        let t=res.data.data
        let j=[]
        let list=[]
        for(j in t){
          list.push(t[j].college)
        }
        that.setData({
          collegelist:list
        })
        let i=0
        // 学院
        that.setData({
          "college":that.data.collegelist.findIndex(i=>i===that.data.info.college)
        })
    },
    fail (res) {
      console.log(res)
    }
  })
},
get_major(){
  // console.log(this.data.token)
  let that=this
  // console.log(that.data.token)
    wx.request({
      url: wx.$url+'/college/getmajorbycollege?college='+that.data.info.college,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        console.log(res.data)
        let t=res.data.data
        let j=[]
        let list=[]
        for(j in t){
          list.push(t[j].major)
        }
        that.setData({
          majorlist:list
        })
        let i=0
        // console.log(that.data.info.dromm.substring(0,4))
        // 专业
        that.setData({
          "major":that.data.majorlist.findIndex(i=>i===that.data.info.major)
        })
      },
      fail (res) {
        console.log(res)
      }
    })
},

submit(){
  let that=this
  wx.showModal({  
    title: '提示',  
    content: '确认无误',  
    success: function(res) {  
        if (res.confirm) {  
          console.log('用户点击确定')  
          that.data.info.drom=that.data.dromm+that.data.tungs
          wx.request({
            url: wx.$url+'/users/update',
            data:that.data.info,
            method:"POST",
            header:{
              "Authorization":"Bearer "+that.data.token
            },
            success (res) {
              console.log(res.data)
              if(res.data.code===0){
                wx.showToast({
                  title: '修改成功',//提示文字
                  duration:2000,//显示时长
                  mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
                  icon:'success', //图标，支持"success"、"loading"  
               })
              }
            },
            fail (res) {
              console.log(res)
            }
          })
        } else if (res.cancel) {  
          console.log('用户点击取消')  
        }  
    }  
})  
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      token: wx.getStorageSync('token'),
      id:options.id
    })
    let that=this
    wx.request({
      url: wx.$url+'/users/findstudentbyusername?username='+options.id,
      method:"POST",
      header:{
        "Authorization":"Bearer "+that.data.token
      },
      success (res) {
        if(res.data.code===0){
         that.setData({
          info:res.data.data
        })
        console.log(that.data.info)
        that.setData({
          dromm:that.data.info.drom.substring(0,4)
        })
        that.setData({
          tungs:that.data.info.drom.substring(4)
        })
        // 性别
        that.setData({
          "index":that.data.sexlist.findIndex(i=>i===that.data.info.sex)
        })
        // 民族
        that.setData({
          "index1":that.data.nationlist.findIndex(i=>i===that.data.info.nation)
        })
        //应届生
        that.setData({
          "index2":that.data.studenttypelist.findIndex(i=>i===that.data.info.studenttype)
        })
        // 政治面貌
        that.setData({
          "index3":that.data.politicslist.findIndex(i=>i===that.data.info.politics)
        })
        // 年级
        that.setData({
          "index4":that.data.gradelist.findIndex(i=>i===that.data.info.grade)
        })
        // 班级
        that.setData({
          "index5":that.data.classeslist.findIndex(i=>i===that.data.info.classes)
        })
        
        // 学制
        that.setData({
          "index6":that.data.xuezhilist.findIndex(i=>i===that.data.info.xuezhi)
        })
        // 学习形式studytype
        that.setData({
          "index7":that.data.studytypelist.findIndex(i=>i===that.data.info.studytype)
        })
        // 学籍状态
        that.setData({
          "index8": that.data.studystatelist.findIndex(i=>i===that.data.info.studystate)
        })
        wx.getStorage({
          key: 'token',
          success:function(res){
            that.setData({
              token:res.data
            })
          },
        })
        that.get_dromm()
        that.get_tungs()
        that.get_college()
        that.get_major()
        }
      },
      fail (res) {
        console.log(res)
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