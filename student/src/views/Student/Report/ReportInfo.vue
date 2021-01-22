<template>
<div id="Reportinfo">
    <div id="reportntc">
        <p id="addr-time">迎新报道时间为2020.9.1至2020.9.3日早上9点到晚上7点，地点为训练馆一楼.</p>
    </div>
    <div id="container">
        <div id="title">
            <p id="p1">预填学生报道信息</p>
        </div>
        <div id="prewrite">
            <div id="prewrite-time">
                <p id="p2">预计到校时间：</p>
                <input id="time" type="text" v-model="stu.arrivetime" v-if="flag"/>
                <span v-text="stu.arrivetime" v-if="!flag"></span>
            </div>
            <div id="prewrite-vehicle">
                <p id="p2">到校交通方式：</p>
                <select id="vehicle" v-model="stu.traffic" v-if="flag">
                    <option value="公交">公交</option>
                    <option value="私家车">私家车</option>
                    <option value="出租车">出租车</option>
                </select>
                <span v-text="stu.traffic" v-if="!flag"></span>
            </div>
        <div id="prewrite-person">
            <p id="p2">随行人员数量：</p>
            <select id="number" v-model="stu.entourage" v-if="flag">
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select>
            <span v-text="stu.entourage" v-if="!flag"></span>
        </div>
        <div id="prewrite-other">
            <p id="p2">其他:</p>
            <textarea id="other-matters" v-model="stu.other" v-if="flag"></textarea>
            <span v-text="stu.other" v-if="!flag" id="last"></span>
        </div>
        <div class="submit-btns">
            <el-button type="primary" v-if="flag" id="submitbtns" @click="submit" icon="el-icon-circle-check">提交</el-button>
            </div>
        </div>
</div>
</div>  
</template>
<script>
export default {
    name:"Report",
    data(){
        return{
            input:"",
            flag:this.$store.state.stuInfro.reportinfo!="已完成",
            stu:this.$store.state.stuInfro,
        }
    },
    methods:{
        submit(){
            var index=0;
            var i=0;
            for(i=0;i<this.$store.state.list.length;i++){
                var t=this.$store.state.list[i].frontendMenuId;
                // console.log(t)
                if(t==13){
                    index=1;
                }
            }
            if(index==1){
                this.$confirm('确认提交?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: "warning"
                }).then(() => {
                    this.stu.reportinfo="已完成";
                    this.axios.post(this.$apiUrl +"/users/update", this.stu,{
                        headers:{'Authorization': 'Bearer ' +localStorage.getItem('token'+this.$store.state.id),}
                    })
                    .then(res => {
                    console.log(res.data);
                    if (res.data.code != 0) {
                        this.$message({
                        type: "error",
                        message: "提交失败"
                        });
                    } else {

                        this.$store.commit('updateInfo',this.stu);
                        this.flag=0;
                        this.$message({
                            type: "success",
                            message: "提交成功"
                        });
                    }
                    })
                    .catch(err => {
                        console.log(err);
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消提交'
                    });          
                }); 
                }else{
        this.$message.error({
            message: "无该权限！",
            duration:1500
        });
    }

      },
        },
        mounted(){
            console.log(this.$store.state.stuInfro)
        }

}
</script>
<style lang="scss" scoped>
#Reportinfo{
    position: relative;
    width: 920px;
    margin-top: -10px;
    margin-left: -10px;
    background-color: #f4f8f6;
}
#reportntc{
    width: 100%;
    height: 50px;
    margin-left: 0;
    background-color: rgba($color: #276E51, $alpha: 0.8);
    display: flex;
    justify-content: flex-start;
    p{
        padding-left: 10px;
        line-height: 15px;
    }
}
span{
    display: inline-block;
    width: 70%;
    height: 25px;
    border:1px solid #797979;
    background-color: rgba($color: #ffffff, $alpha: 0.4);
}
#last{
    height: 50px;
    overflow-y:auto
}
#container{
    width: 100%;
    height: 400px;
    margin-top: 20px;
    margin-left: 0;
    color:#000000;
    #title{
        height: 30px;
        display: flex;
        justify-content: flex-start;
        
        p{
        padding-left: 10px;
    }
    }
    #prewrite{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        #prewrite-time{
            margin-top: 40px;
            display: flex;
            justify-content: space-between;
            margin-left: 290px;
            width: 40%;
            #time{
            width: 70%;
            height: 25px;
            border-color: #797979;
            border-width:0.1px;
            background-color: rgba($color: #ffffff, $alpha: 0.4);
}
        }
        #prewrite-vehicle{
            margin-top:30px ;
            margin-left: 290px;
            width: 40%;
            display: flex;
            justify-content: space-between;
            #vehicle{
                width: 70%;
                height:25px;
                border-color:#797979 ;
                background-color: rgba($color: #ffffff, $alpha: 0.4);
            }

            }
        #prewrite-person{
            margin-top:30px ;
            margin-left: 290px;
            width: 40%;
            display: flex;
            justify-content: space-between;
            #number{
                width: 70%;
                height:25px;
                border-color:#797979 ;
                background-color: rgba($color: #ffffff, $alpha: 0.4);
            }

            }
        #prewrite-other{
            margin-top:30px ;
            margin-left: 290px;
            width: 40%;
            display: flex;
            justify-content: space-between;
            #other-matters{
                width: 257px;
                min-height:50px;
                resize: none;
                border-color:#797979 ;
                border-width: 0.1px;
                background-color: rgba($color: #ffffff, $alpha: 0.4);
            }

            }
        .submit-btns{
            margin-top: 10px;
            margin-left: 290px;
            width: 40%;
            display: flex;
            justify-content: center;
            #submitbtns{
            width: 100px;
            line-height: 8px;
                height: 30px;
                background-color: rgba($color: #409eff, $alpha: 0.9);
                color: white;
                margin-top: 30px;
                margin-left: 70px;
                outline: none;
                border: none;
                border-radius: 0.5em;
                cursor: pointer;
        }
        }
        }

    }


#addr-time{
    margin-top: 17px;
    font-size: 17px;
    color:#ffffff;
}
#p1{
    font-size: 18px;
    color: #797979;
    font-weight: bold;
}
#p2{
    font-size: 16px;
    color: #797979;
    font-weight: 600;
    margin-left: -10px;
}


</style>