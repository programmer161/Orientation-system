// components/usermanage/usermanage.js
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
    current:0,
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onClick(event){
      let index = event.currentTarget.dataset.id;
      this.setData({
        current:index
      })
    }
  }
})
