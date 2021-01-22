<template>
    <div id="ReportNow">
        <div id="reportntc">
            <p id="notice">各位{{nianfen}}级新生请详细阅读录取通知书上的学院和校区信息，按时到校完成入学报道手续。</p>
        </div>
        <div id="container">
            
            <div id="shizishan">
                <h4>狮子山校区</h4>
                <viewer :images="arrayList">
                    <img
                    v-for="(src,index) in arrayList"
                    src="../../../assets/shizishan.jpg"
                    :key="index"
                    class="pro-img"
                    >
                </viewer>
                <hr><hr><hr><hr>
                <p class="t">校区地址：成都市锦江区静安路5号。</p>
                <p class="t">{{szsmsg}}</p>
            </div>
            <div id="chenglong">
                <h4>成龙校区</h4>
                <viewer :images="arrayList">
                    <img
                    v-for="(src,index) in arrayList"
                    src="../../../assets/chenglong.jpg"
                    :key="index"
                    class="pro-img"
                    >
                </viewer>
                <hr><hr><hr><hr>
                <p class="t">校区地址：成都市龙泉驿区成龙大道1819号。</p>
                <p class="t">{{clmsg}}</p>
            </div>
        </div>
        <!-- <img src="../../../assets/shizishan.jpg" class="pic"> -->
    </div>
</template>
<script>
export default {
    data(){
        return{
            arrayList:["../../../assets/shizishan.jpg"],
            // list:['../../../assets/shizishan.jpg',"../../../assets/chenglong.jpg"],
            // big1:0,
            nianfen:"2020",
            szsmsg:"疫情原因，学生的陪同人员不能进入校园。学生需带好相关证件，测量体温后凭通知书一书一入。学生从校门进来后，先根据个人信息找到对应宿舍楼，拿到宿舍钥匙放置好行李。携带身份证和录取通知书到一教楼下找到对应的学院报道点，在网上缴清学费的学生可以直接排队报道领取一卡通和相关资料。绿色通道的学生需带好相关凭证，到绿色通道排队拿到报名许可，再到学院报道点注册报道。",
            clmsg:"疫情原因，成龙校区西岑门暂不予开放。陪同家属不能进入校园。学生需携带好健康码，15日所在地登记表，以及录取通知书并测量体温后才能进入校园。学生从校门进来后，先根据个人信息找到对应宿舍楼，拿到宿舍钥匙放置好行李。携带身份证和录取通知书到训练馆找到对应的学院报道点，在网上缴清学费的学生可以直接排队报道领取一卡通和相关资料。绿色通道的学生需带好相关凭证，到绿色通道排队完成相关手续，再到学院报道点注册报道。"
        }
    },
    methods:{
        // pic1() {
        //     var viewer = new Viewer(document.getElementById('pic1'), {
        //         url: "../../../assets/shizishan.jpg",
        // });
        // }
    }
}
</script>
<style lang="scss" scoped>
hr{
    visibility: hidden;
}
img{
    height: 170px;
    width: 210px;
    position: absolute;
    margin-left: 690px;
    background-size: 100%;

}
.pic{
    height: 500px;
    width: 550px;
    background-size: 100%;
    position: absolute;
    margin-top: -600px;
}
#ReportNow{
    position: relative;
    width: 920px;
    margin-top: -10px;
    margin-left: -10px;
    // background-color: #f4f8f6;
}
#reportntc{
    width: 100%;
    height: 50px;
    margin-left: 0;
    background-color: rgba($color: #276E51, $alpha: 0.8);
    display: flex;
    justify-content: center;
}
#container{
    width: 100%;
    margin-top: 0;
    height: 500px;
    #shizishan{
        height: 45%;
        background-color: #f4f8f6;
        border-width: 1px;
    }
    #chenglong{
        margin-top: 5px;
        height: 48%;
        background-color: #f4f8f6;
    }
}
#notice{
    margin-top: 15px;
    font-size: 16px;
    color:#ffffff;
}
h4{
    font-weight: bolder;
    color: #797979;
}
.t{
    margin-left: 20px;
    margin-right: 230px;
    color: #797979;
}

h4{
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
}

</style>