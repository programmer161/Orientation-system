// components/adduser/adduser.js
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
    sexlist: ['男', '女'],
    index:0,
    info:{},
    date:"info.birth",
    index1:0,
    nationlist:["汉族", "藏族", "维吾尔族", "苗族", "回族"],
    studenttypelist:["应届生","往届生"],
    index2:0,
    politicslist:["共青团员", "群众", "党员"],
    index3:0,
    gradelist:["2017级","2018级","2019级","2020级"],
    index4:0,
    classeslist:["1班", "2班", "3班", "4班", "5班","6班"],
    index5:"",
    xuezhilist:["一年","二年","三年","四年","五年"],
    index6:0,
    studytypelist:["全日制招生", "成人高考", "远程教育", "自考", "国家开发银行"],
    index7:0,
    studystatelist:["注册学籍", "保留学籍", "已离校"],
    lndex8:0,
    drommlist:[],
    dromm:"",
    index9:0,
    tungslist:[],
    tungs:"",
    index10:0,
    collegelist:[],
    college:0,
    majorlist:[],
    major:0,
    token:""
  },

  /**
   * 组件的方法列表
   */
  methods: {
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
        },
        fail (res) {
          console.log(res)
        }
      })
  },
  
  submit(){
    // console.log(this.data.info.userName+","+this.data.info.idnumber)
    if (this.data.info.userName == '' || this.data.info.idnumber == '') {
      wx.showModal({
        title: '提示',
        content: '学号或身份证号不能为空！',
        success: function(res) {
            if (res.confirm) {
            console.log('用户点击确定')
            } else if (res.cancel) {
            console.log('用户点击取消')
            }
        }
    })
  }else{
    let that=this
    wx.showModal({  
      title: '提示',  
      content: '确认添加',  
      success: function(res) {  
          if (res.confirm) {  
            console.log('用户点击确定')  
            that.data.info.drom=that.data.dromm+that.data.tungs
            let list=that.data.info
            if(list.sex=="请选择"){
              list.sex=""
            }
            if(list.birth=="请选择"){
              list.birth=""
            }
            if(list.nation=="请选择"){
              list.nation=""
            }
            if(that.data.dromm=="请选择"){
              list.drom=""
            }
            if(list.studenttype=="请选择"){
              list.studenttype=""
            }if(list.studytype=="请选择"){
              list.studytype=""
            }
            if(list.college=="请选择"){
              list.college=""
            }
            if(list.major=="请选择"){
              list.major=""
            }
            if(list.classes=="请选择"){
              list.classes=""
            }
            if(list.grade=="请选择"){
              list.grade=""
            }
            if(list.studystate=="请选择"){
              list.studystate=""
            }
            if(list.xuezhi=="请选择"){
              list.xuezhi=""
            }
            if(list.politics=="请选择"){
              list.politics=""
            }
            let t=[]
            t.push(list)
            wx.request({
              url: wx.$url+'/users/addstudent',
              data:t,
              method:"POST",
              header:{
                "Authorization":"Bearer "+that.data.token
              },
              success (res) {
                console.log(res.data)
                if(res.data.code===0){
                  wx.showToast({
                    title: '添加成功',//提示文字
                    duration:2000,//显示时长
                    mask:true,//是否显示透明蒙层，防止触摸穿透，默认：false  
                    icon:'success', //图标，支持"success"、"loading"  
                 })
                 that.setData({
                  "info.userName":"",
                  "info.idnumber":"",
                  "info.instructname":"",
                  "info.instructphone":"",
                  "info.sex":"请选择",
                  "info.birth":"请选择",
                  "info.nation":"请选择",
                  "dromm":"请选择",
                  "tungs":"请选择",
                  "info.studenttype":"请选择",
                  "info.studytype":"请选择",
                  "info.college":"请选择",
                  "info.major":"请选择",
                  "info.classes":"请选择",
                  "info.xuezhi":"请选择",
                  "info.politics":"请选择",
                  "info.grade":"请选择",
                  "info.studystate":"请选择",
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
}
  },
  },
  attached: function(){
    this.setData({
      token: wx.getStorageSync('token')
    })
    this.setData({
      "info.userName":"",
      "info.idnumber":"",
      "info.instructname":"",
      "info.instructphone":"",
      "info.sex":"请选择",
      "info.birth":"请选择",
      "info.nation":"请选择",
      "dromm":"请选择",
      "tungs":"请选择",
      "info.studenttype":"请选择",
      "info.studytype":"请选择",
      "info.college":"请选择",
      "info.major":"请选择",
      "info.classes":"请选择",
      "info.xuezhi":"请选择",
      "info.politics":"请选择",
      "info.grade":"请选择",
      "info.studystate":"请选择",
    })
    this.get_college()
    this.get_dromm()
  },
})
