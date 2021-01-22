<template>
    <div class="whole">
        <span class="title">管理统计表</span>
        <el-divider></el-divider>
        <div class="image_round">
            <div id="photo_one" style="width:50%;height:300px;"></div>
            <div id='photo_two' style="width:50%;height:300px;"></div>
        </div>
        <el-row>
            <el-button type="primary" class="Button_one" @click="export_admin" icon="el-icon-copy-document">导出普通管理员名单
            </el-button>
            <el-button type="primary" class="Button_one" @click="export_user" icon="el-icon-copy-document">导出学生名单
            </el-button>
            <el-button type="primary" class="Button_two" @click="export_finished" icon="el-icon-copy-document">导出已完成报到名单
            </el-button>
            <el-button type="primary" class="Button_two" @click="export_unfinished" icon="el-icon-copy-document">
                导出未完成报到名单</el-button>
        </el-row>
    </div>
</template>

<script>
    let echarts = require('echarts/lib/echarts')
    // 引入饼状图组件
    require('echarts/lib/chart/pie')
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip')
    require('echarts/lib/component/title')
    export default {
        data() {
            return {
                user_sum: '',
                manager_sum: '',
                finished_sum: '',
                unfinished_sum: ''
            }
        },
        mounted() {
            this.test();
            this.myEcharts();
        },
        methods: {
            //异步进行处理，保证图表中的数据能够进行正常的显示
            test: async function () {
                await this.axios.all([this.axios.get(this.$apiUrl +'/users/getmanagerall', {
                    headers: {
                        'Authorization': localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => res.data),
                this.axios.get(this.$apiUrl +'/users/getstudentsys',{
                    headers:{
                        'Authorization':localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => res.data),
                this.axios.get(this.$apiUrl +'/users/getfinishedsys',{
                    headers:{
                        'Authorization':localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => res.data),
                this.axios.get(this.$apiUrl +'/users/getunfinishedsys',{
                    headers:{
                        'Authorization':localStorage.getItem('token' + this.$store.state.id)
                    }
                }).then(res => res.data)
                ])
                .then(this.axios.spread((res1,res2,res3,res4) => {
                    if(res1.code === 0 &&res2.code === 0&&res3.code === 0&&res4.code === 0){
                        this.manager_sum = res1.data.length;
                        this.user_sum = res2.data.length;
                        this.finished_sum = res3.data.length;
                        this.unfinished_sum = res4.data.length;
                    }else{
                        this.$message({
                            type:'error',
                            message:'用户数据获取失败'
                        })
                    }
                }))
                .catch(err => {
                    console.log(err);
                })
            },
            //将所需要进行显示的的数据通过饼状图进行展示
            myEcharts: async function () {
                await this.test()
                var myChart_one = echarts.init(document.getElementById('photo_one'));
                myChart_one.setOption({
                    title: {
                        text: '用户统计表', //主标题
                        // subtext: '纯属虚构', //副标题
                        x: 'center', //x轴方向对齐方式
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['普通管理员', '学生']
                    },
                    series: [{
                        name: '用户管理情况',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '55%'],
                        data: [{
                                value: this.manager_sum,
                                name: '普通管理员',
                                itemStyle: {
                                    normal: {
                                        color: "#AACA18"
                                    }
                                }
                            },
                            {
                                value: this.user_sum,
                                name: '学生',
                                itemStyle: {
                                    normal: {
                                        color: "#C38F63"
                                    }
                                }
                            },
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            normal: {
                                formatter: '{b}:{c}',
                                position: 'inside'
                            },
                        }
                    }]
                });
                var myChart_two = echarts.init(document.getElementById('photo_two'));
                myChart_two.setOption({
                    title: {
                        text: '报到情况统计表', //主标题
                        // subtext: '纯属虚构', //副标题
                        x: 'center', //x轴方向对齐方式
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['已报到', '未报到']
                    },
                    series: [{
                        name: '学生报到情况',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '55%'],
                        data: [{
                                value: this.finished_sum,
                                name: '已报到',
                                itemStyle: {
                                    normal: {
                                        color: "#00BFBF"
                                    }
                                }
                            },
                            {
                                value: this.unfinished_sum,
                                name: '未报到',
                                itemStyle: {
                                    normal: {
                                        color: "#F59A23"
                                    }
                                }
                            }
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            normal: {
                                formatter: '{b}:{c}',
                                position: 'inside'
                            },
                        }
                    }]
                });
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]));
            },
            //导出普通管理员名单
            export_admin() {
                this.axios.get(this.$apiUrl +'/users/getmanagerall', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '导出普通管理员用户名单失败'
                            })
                        } else {
                            var list = res.data.data;
                            require.ensure([], () => {
                                const {
                                    export_json_to_excel
                                } = require('../../../assets/js/Export2Excel');
                                const tHeader = ['工号', '姓名', '电话号码', '政治面貌', '性别', '身份证号', '民族', '出生日期',
                                    '学院'
                                ];
                                const filterVal = ['userName', 'name', 'phonenumber', 'politics', 'sex',
                                    'idnumber', 'nation', 'birth', 'college'
                                ];
                                const data = this.formatJson(filterVal, list);
                                export_json_to_excel(tHeader, data, '普通管理员名单');
                            })

                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //导出用户名单
            export_user() {
                this.axios.get(this.$apiUrl +'/users/getstudentsys', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '导出用户名单失败'
                            })
                        } else {
                            var list = res.data.data;
                            require.ensure([], () => {
                                const {
                                    export_json_to_excel
                                } = require('../../../assets/js/Export2Excel');
                                const tHeader = ['姓名', '学号', '身份证号', '性别', '出生日期', '民族', '学院', '专业', '年级',
                                    '班级', '宿舍', '学制', '类别', '学习形式', '政治面貌', '辅导员姓名', '辅导员电话', '学籍状态'
                                ];
                                const filterVal = ['name', 'userName', 'idnumber', 'sex', 'birth', 'nation',
                                    'college', 'major', 'grade', 'classes', 'drom', 'xuezhi',
                                    'studenttype', 'studytype', 'politics', 'instructname',
                                    'instructphone', 'studytype'
                                ];
                                const data = this.formatJson(filterVal, list);
                                export_json_to_excel(tHeader, data, '用户名单');
                            })

                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //导出已完成报到名单
            export_finished() {
                this.axios.get(this.$apiUrl +'/users/getfinishedsys', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '导出用户名单失败'
                            })
                        } else {
                            var list = res.data.data;
                            require.ensure([], () => {
                                const {
                                    export_json_to_excel
                                } = require('../../../assets/js/Export2Excel');
                                const tHeader = ['姓名', '学号', '身份证号', '性别', '出生日期', '民族', '学院', '专业', '年级',
                                    '班级', '宿舍', '学制', '类别', '学习形式', '政治面貌', '辅导员姓名', '辅导员电话', '学籍状态'
                                ];
                                const filterVal = ['name', 'userName', 'idnumber', 'sex', 'birth', 'nation',
                                    'college', 'major', 'grade', 'classes', 'drom', 'xuezhi',
                                    'studenttype', 'studytype', 'politics', 'instructname',
                                    'instructphone', 'studystate'
                                ];
                                const data = this.formatJson(filterVal, list);
                                export_json_to_excel(tHeader, data, '已完成报到名单');
                            })

                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            },
            //导出未完成报到名单
            export_unfinished() {
                this.axios.get(this.$apiUrl +'/users/getunfinishedsys', {
                        headers: {
                            'Authorization': localStorage.getItem('token' + this.$store.state.id)
                        }
                    })
                    .then(res => {
                        if (res.data.code != 0) {
                            this.$message({
                                type: 'error',
                                message: '导出用户名单失败'
                            })
                        } else {
                            var list = res.data.data;
                            require.ensure([], () => {
                                const {
                                    export_json_to_excel
                                } = require('../../../assets/js/Export2Excel');
                                const tHeader = ['学号', '姓名', '性别', '学院', '专业', '班级', '信息确认', '报到信息', '现场报到',
                                    '事务办理', '学费支付'
                                ];
                                const filterVal = ['userName', 'name', 'sex', 'college', 'major', 'classes',
                                    'info', 'reportinfo', 'reportnow', 'affair', 'pay'
                                ];

                                const data = this.formatJson(filterVal, list);
                                export_json_to_excel(tHeader, data, '未完成报到名单');
                            })

                        }
                    })
                    .catch(err => {
                        console.log(err);
                    })
            }
        }
    }
</script>

<style lang="less" scoped>
    // 引入全局的整体布局样式
    @import '../../../less/layout.css';

    .image_round {
        display: flex;
        position: relative;
        margin-top: 6%;
    }

    .Button_one {
        position: relative;
        top: 8px;
        margin-bottom: 3%;
        left: 9%;
        font-size: 8px;
        margin-right: 10%;
        background-color: #409eff;
        opacity: 0.9;
    }

    .Button_two {
        position: relative;
        top: 8px;
        margin-bottom: 3%;
        left: 7%;
        font-size: 8px;
        margin-right: 10%;
        background-color: #409eff;
        opacity: 0.9;
    }

    /deep/ .el-button [class*=el-icon-]+span {
        margin-left: 2px;
    }
</style>