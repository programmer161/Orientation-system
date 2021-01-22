<template>
    <div>
        <div class="whole">
        <p id="title">信息详情</p>
        <span id="basicinfo">基本信息</span>
        <el-form ref="form"  label-width="120px" id="ta">
            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="姓名">
                        <span v-text="this.$store.state.stuInfro.name" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="学号">
                        <span v-text="this.$store.state.stuInfro.userName" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="身份证号">
                        <span v-text="this.$store.state.stuInfro.idnumber" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="性别">
                        <span v-text="this.$store.state.stuInfro.sex"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="出生日期">
                        <span v-text="this.$store.state.stuInfro.birth" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="民族">
                        <span v-text="this.$store.state.stuInfro.nation"></span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="学院">
                        <span v-text="this.$store.state.stuInfro.college" class="Input" ></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="专业">
                        <span v-text="this.$store.state.stuInfro.major" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="年级">
                        <span v-text="this.$store.state.stuInfro.grade" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="班级">
                        <span v-text="this.$store.state.stuInfro.classes" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="宿舍">
                        <span v-text="this.$store.state.stuInfro.drom"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="学制">
                        <span v-text="this.$store.state.stuInfro.xuezhi" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="应届生">
                        <span v-text="this.$store.state.stuInfro.studenttype" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="学习形式">
                        <span v-text="this.$store.state.stuInfro.studytype" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="政治面貌">
                        <span v-text="this.$store.state.stuInfro.politics" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="辅导员姓名">
                        <span v-text="this.$store.state.stuInfro.instructname" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="辅导员电话">
                        <span v-text="this.$store.state.stuInfro.instructphone" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="学籍状态">
                        <span v-text="this.$store.state.stuInfro.state" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <span id="basicinfo">预填报到信息</span>
        <el-form ref="form"  label-width="120px" id="ta">
            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-form-item label="预计到校时间:">
                        <span v-text="this.$store.state.stuInfro.arrivetime" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="到校交通方式:">
                        <span v-text="this.$store.state.stuInfro.traffic" class="Input"></span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="随行人员">
                        <span v-text="this.$store.state.stuInfro.entourage" class="Input"></span>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <span id="basicinfo">当前报到状况</span>
        <!-- <el-steps :space="200" :active="active" finish-status="success" process-status="wait"> -->
        <div id="info">
            <div>信息确认<i class="el-icon-success" :class="this.$store.state.stuInfro.info=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.info!='已完成'?'red':'None'"></i></div>
            <div>报道信息<i class="el-icon-success" :class="this.$store.state.stuInfro.reportinfo=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.reportinfo!='已完成'?'red':'None'"></i></div>
            <div>现场报道<i class="el-icon-success" :class="this.$store.state.stuInfro.reportnow=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.reportnow!='已完成'?'red':'None'"></i></div>
            <div>事务办理<i class="el-icon-success" :class="this.$store.state.stuInfro.affair=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.affair!='已完成'?'red':'None'"></i></div>
            <div>学费支付<i class="el-icon-success" :class="this.$store.state.stuInfro.pay=='已完成'?'green':'None'"></i><i class="el-icon-error" :class="this.$store.state.stuInfro.pay!='已完成'?'red':'None'"></i></div>
        </div>
    </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            Id:"",
            list:[],
            active:2,
                
        }
    },
    methods:{
        
    },
    mounted(){
        console.log(this.$store.state.stuInfro)
    }
}
</script>


<style lang="less" scoped>
// @import '../../../less/layout.css';
    .green{
        position: absolute;
        color: rgb(6, 189, 6);
        font-size: 30px;
        // line-height: 10px;
        margin-left: 10px;
        margin-top: 10px;
        // line-height: 50px;
    }
    .red{
        position: absolute;
        color: red;
        font-size: 30px;
        margin-top: 10px;
        margin-left: 10px;
    }
    
    .None{
        display: none;
    }
    .el-steps{
        margin-left: 100px;
        
    }
    .whole {
        background-color: #F4F8F6;
        padding-bottom: 20px;
        // margin-top: -20px;
        width: 1020px;
        min-height: 500px;
    }

    .Button { 
        position: relative;
        margin-left: 890px;
        line-height: 8px;
        margin-bottom: 2%;
        height: 30px;
    }

    .export {
        position: relative;
        margin-left: 5%;
        top: 25px;
        color: blue;
        cursor: pointer;
    }

    .back {
        position: relative;
        margin-left: 35%;
        top: 35px;
        color: blue;
        cursor: pointer;
    }

    /deep/ .el-row {
        padding-right: 2%;
        height: 1%;
    }

    /deep/ .el-form-item {
        height: 40px;
        width: 120px;
        background-color: #E5FFF2;
        border: 1px solid gainsboro;
        line-height: 40px;
        margin-bottom: 0px;
    }

    .el-form-item /deep/ .el-form-item__label {
        padding-left: 10px;
        color: black;
        text-align: left;
    }

    #title {
    font-size: 30px;
    height: 70px;
    line-height: 70px;
    font-weight: normal;
    text-align: center;
    border-bottom: 0.5px solid gainsboro;
}

    span{
        padding-left: 10px;
        display: inline-block;
        height: 40px;
        width: 195px;
        border: 1px solid gainsboro;
        background-color: white;
        margin-top: -1px;
    }
    #ta{
        margin-left: 20px;
        padding-bottom: 20px;
        // padding-top: 20px;
        margin-top: -10px;
    }
    #basicinfo{
        background-color: transparent;
        border: none;
        margin-top: 20px;
        margin-left: 10px;
    }
    #info{
        height: 50px;
        width: 980px;
        background-color: #E5FFF2;
        margin-left: 20px;
        border: 1px solid gainsboro;
        div{
            display: inline-block;
            width: 140px;
            height: 50px;
            line-height: 50px;
            // border: 1px solid black;
            margin-left: 50px;
        }
    }
    /deep/ .el-button [class*=el-icon-]+span{
        margin-left: 2px;
    }
</style>