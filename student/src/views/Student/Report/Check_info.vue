<template>
  <div class="whole">
    <div class="part">
      <span class="title">学生信息修改</span>
      <el-divider></el-divider>
      <!-- <el-form ref="form" :model="stu" label-width="120px"> </el-form> -->
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">学生姓名：</span>
          <span class="info-item-txt">小明</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学生学号：</span>
          <span class="info-item-txt">2020170111</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学生身份证号：</span>
          <span class="info-item-txt">51056320010708284x</span>
        </div>
      </div>
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">学生性别：</span>
          <span class="info-item-txt">男</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学生年龄：</span>
          <span class="info-item-txt">18</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学生民族：</span>
          <span class="info-item-txt">汉族</span>
        </div>
      </div>
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">学院：</span>
          <span class="info-item-txt">计算机科学学院</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">专业：</span>
          <span class="info-item-txt">软件工程</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">年级：</span>
          <span class="info-item-txt">大一</span>
        </div>
      </div>
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">班级：</span>
          <span class="info-item-txt">1班</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">宿舍：</span>
          <span class="info-item-txt">东苑三栋</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学制：</span>
          <span class="info-item-txt">4年</span>
        </div>
      </div>
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">学生类别：</span>
          <span class="info-item-txt">应届生</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学习形式：</span>
          <span class="info-item-txt">线下</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">政治面貌：</span>
          <span class="info-item-txt">共青团员</span>
        </div>
      </div>
      <div class="info-row">
        <div class="info-item">
          <span class="info-item-name">辅导员姓名：</span>
          <span class="info-item-txt">潘瑞英</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">辅导员电话：</span>
          <span class="info-item-txt">11111111111</span>
        </div>
        <div class="info-item">
          <span class="info-item-name">学籍状态：</span>
          <span class="info-item-txt">注册学籍</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // name: "information"
  data() {
    return {};
  }
};
</script>
<style lang="less" scoped>
@import "../../../less/layout.css";
.whole {
  background-color: #f4f8f6;
  width: 800px;
  border-radius: 3px;
  border: 0.2px solid gainsboro;
  position: relative;
  left: 50%;
  transform: translate(-50%);
  margin-top: 5%;
  margin-bottom: 5%;
}
.part {
  background-color: #f4f8f6;
  // height: 70%;
  width: 700px;
  // border: 1px solid black;
  position: relative;
  // margin-top: 3%;
  padding-left: 10px;
  // margin-left: 7%;
}
.info-row {
  width: 100%;
  margin-top: 30px;
  padding-left: 20px;
  display: flex;
  .info-item {
    flex: 1;
  }
}

span {
  display: inline-block;
  height: 40px;
  width: 204px;
  border: 1px solid gainsboro;
  background-color: white;
  margin-top: -1px;
}

.title {
  background-color: #f4f8f6;
  border: none;
}

/deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>
<style lang="less" >
</style>