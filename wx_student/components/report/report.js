// pages/report/report.js
Component({

  /**
   * 页面的初始数据
   */
  data: {
    current:0,
  },
  methods: {
  onClick(event){
    let index = event.currentTarget.dataset.id;
    this.setData({
      current:index
    })
  },
},
})