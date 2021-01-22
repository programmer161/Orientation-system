<template>
    <div>
        <div id="mes">
            <div id="title">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="题目：">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                </el-form>
            </div>

            <p @click="setbtn" v-text="btnvalue"></p>
            <div class="app-content">
                <div class="Tinymce_box">
                    <tinymce v-model="content" :height="700" v-if="!flag" />
                    <div v-if="flag" class="editor-content">

                        <h3>预览效果：</h3>
                        <div v-html="content" />
                    </div>
                </div>
            </div>
            <el-button type="primary" @click="Update">确认修改</el-button>
        </div>

    </div>

</template>

<script>
    import Tinymce from "../../../Tinymce";
    export default {
        components: {
            Tinymce
        },
        data() {
            return {
                flag: 0,
                btnvalue: "预览",
                title: "test",
                content: this.$store.state.msgvalue,
                form: {
                    name: this.$store.state.msgtitle
                }
            }
        },
        methods: {
            setbtn() {
                this.flag = !this.flag;
                if (this.flag == 0) {
                    this.btnvalue = "预览";
                } else {
                    this.btnvalue = "取消预览";
                }
            },
            Update() {
                // this.content=this.content.replace(/<[^>]+>/g, '')
                // this.content=this.content.replace(/&rdquo;/g, '')
                // this.content=this.content.replace(/&ldquo;/g, '')
                console.log('content=',this.content);
                this.$confirm('确定修改该通知内容？', '提示', {
                    confirmButtonText: '确定',
                    cancleButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    var obj = {
                        id: this.$store.state.msgId,
                        title: this.form.name,
                        tvalues: this.content
                    }
                    let url;
                    let url1;
                    if (this.$store.state.msgtype === '学校通知') {
                        url = this.$apiUrl +'/message/updatemessage';
                        url1 = this.$apiUrl +'/main/msgmhome/schoolmsg'
                    } else if (this.$store.state.msgtype === '校园攻略') {
                        url = this.$apiUrl +'/trategy/updatetrategy';
                        url1 = this.$apiUrl +'/main/msgmhome/schoolstrategy'
                    } else if (this.$store.state.msgtype === '缴费须知') {
                        url = this.$apiUrl +'/paynotice/updatepaynotice';
                        url1 = this.$apiUrl +'/main/msgmhome/paymsg'
                    } else {
                        url = this.$apiUrl +'/reportnotice/update';
                        url1 = this.$apiUrl +'/main/msgmhome/reportmsg'
                    }
                    this.axios.post(url, obj, {
                            headers: {
                                'Authorization': localStorage.getItem('token'+this.$store.state.id)
                            }
                        })
                        .then(res => {
                            if (res.data.code != 0) {
                                this.$message({
                                    type: 'error',
                                    message: '信息修改失败'
                                })
                            } else {
                                this.$message({
                                    type: 'success',
                                    message: '信息修改成功'
                                })
                                this.$router.push(url1);
                            }
                        })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消添加'
                    })
                })
            }
        },
        mounted() {
            // document.body.style.backgroundImage="url(" + require("../assets/sicnu.jpg") + ")";
            let param = new URLSearchParams()
            param.append("id", this.$store.state.msgId)
            // this.title=this.$store.state.msgtitle;
            // // console.log(this.$store.state.msgId)
            //获取相关通知的内容
            var url;
            
            if (this.$store.state.msgtype === '学校通知') {
                url = this.$apiUrl +'/message/getvalues';
            } else if (this.$store.state.msgtype === '校园攻略') {
                url = this.$apiUrl +'/trategy/getvalues';
            } else if (this.$store.state.msgtype === '缴费须知') {
                url = this.$apiUrl +'/paynotice/getvalues';
            } else {
                url = this.$apiUrl +'/reportnotice/getvalues';
            }
            this.axios.post(url, param, {
                headers: {
                    'Authorization': localStorage.getItem('token'+this.$store.state.id)
                }
            }).then(res => {
                // console.log(res.data);
                if (res.data.code != 0) {
                    this.$message.error({
                        message: "通知获取失败！",
                        duration: 1500
                    });
                } else {
                    console.log('hello world');
                    this.content = res.data.data[0].tvalues;
                }
            }).catch(err => {
                console.log(err);
            })
        },
        watch: {
            $route(to) {
                console.log(this.flag)
                if (to.path == '/main/msgmhome/schoolmsg/message' || to.path ==
                    '/main/msgmhome/schoolstrategy/message' || to.path == '/main/msgmhome/paymsg/message' || to.path ==
                    '/main/msgmhome/reportmsg/message') {
                    this.$store.commit('setreport', 0)
                }
            }
        },
    }
</script>

<style lang="scss" scoped>
    .el-button {
        background-color: #409eff;
        opacity: 0.9;
        margin-left: 400px;
        margin-top: 20px;
    }

    p {
        font-size: 10px;
        margin-left: 890px;
        position: absolute;
        margin-top: 10px;
        color: rgb(8, 191, 247);
        z-index: 10;
        cursor: pointer;
    }

    .editor-content {
        border: 2px dashed #f1f1f1;
        padding: 0 20px;
    }

    h3 {
        color: #808080;
    }

    #mes {
        // border: 1px solid black;
        // width: 50%;
        font-size: 20px;
        margin-left: auto;
        margin-right: auto;
        padding-top: 30px;
        // padding-bottom: 20px;
        padding-left: 50px;
        padding-right: 50px;
        // margin-top: 50px;
        background-color: #F4F8F6;
        opacity: 0.8;
        // font-size: 13px;
        min-height: 300px;
        padding-bottom: 30px;

        #title {
            text-align: center;
            margin-bottom: 20px;
            font-size: 25px;
        }

        #values {
            // background-color: blue;
            min-height: 250px;
        }
    }

</style>