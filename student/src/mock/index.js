import Mock from 'mockjs' //引入mockjs，npm已安装
// import { Random, toJSONSchema } from 'mockjs' // 引入random对象,随机生成数据的对象，（与占位符@一样）
Mock.setup({
    timeout: 100 //设置请求延时时间
});
// Mock.mock( rurl, rtype, template )表示当拦截到rurl和rtype的ajax请求时，将根据数据模板template生成模拟数据，并作为响应数据返回。
// Mock.mock( rurl, rtype, function( options ) )记录用于生成响应数据的函数。当拦截到匹配 rurl 和 rtype 的 Ajax 请求时，函数 function(options) 将被执行，并把执行结果作为响应数据返回。

//登录
// Mock.mock('/login', 'post', function(options) {
//     // console.log(options.body);
//     var reg = /username=([0-9]{10})&password=(.*)$/g;
//     options.body.match(reg);
//     // console.log(t)
//     // console.log(RegExp.$1);
//     // console.log(RegExp.$2);
//     if (RegExp.$2 == "123456") {
//         if (RegExp.$1 == "2018110103") {
//             return {
//                 "msg": "登录成功",
//                 "success": true,
//                 "detail": {
//                     "list": [{
//                             "link": "/main/report",
//                             "name": "报道流程",
//                             "code": 1
//                         },
//                         {
//                             "link": "/main/state",
//                             "name": "状态确认",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/manage",
//                             "name": "高级管理",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/person",
//                             "name": "个人中心",
//                             "code": 1
//                         }
//                     ]
//                 }
//             }
//         } else if (RegExp.$1 == "2018110102") {
//             return {
//                 "msg": "登录成功",
//                 "success": true,
//                 "detail": {
//                     "list": [{
//                             "link": "/main/report",
//                             "name": "报道流程",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/state",
//                             "name": "状态确认",
//                             "code": 1
//                         },
//                         {
//                             "link": "/main/manage",
//                             "name": "高级管理",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/person",
//                             "name": "个人中心",
//                             "code": 1
//                         }
//                     ]
//                 }
//             }
//         } else if (RegExp.$1 == "2018110101") {
//             return {
//                 "msg": "登录成功",
//                 "success": true,
//                 "detail": {
//                     "list": [{
//                             "link": "/main/report",
//                             "name": "报道流程",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/state",
//                             "name": "状态确认",
//                             "code": 0
//                         },
//                         {
//                             "link": "/main/manage",
//                             "name": "高级管理",
//                             "code": 1
//                         },
//                         {
//                             "link": "/main/person",
//                             "name": "个人中心",
//                             "code": 1
//                         }
//                     ]
//                 }
//             }
//         } else {
//             return {
//                 "msg": "用户名或密码错误",
//                 "success": false,
//                 "detail": null
//             }
//         }
//     } else {
//         return {
//             "msg": "用户名或密码错误",
//             "success": false,
//             "detail": null
//         }
//     }
// });

//学号查找
Mock.mock('/fingid', 'post', function(options) {
    // console.log(options.body);
    var reg = /{"id":"(.*)"}$/g;
    options.body.match(reg);
    // console.log(t)
    // console.log(RegExp.$1);
    // console.log(RegExp.$2);
    if (RegExp.$1 == "510111111111111111") {
        return {
            "message": "查询成功",
            "success": true,
            "data": {
                "username": "2018110103"
            }
        }
    } else {
        return {
            "message": "该用户不存在",
            "success": false,
            "data": null
        }
    }
});

//获取学生报道情况
Mock.mock('/manager/getnum', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110102") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": {
                "sum": 1300,
                "finished": 580
            }
        }
    } else {
        return {
            "msg": "获取信息失败",
            "success": false,
            "detail": null
        }
    }
});

//获取学生状态
Mock.mock('/manager/getstatus', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110102") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": {
                "sum": 1300,
                "infro": 1080,
                "reportinfro": 890,
                "reportnow": 760,
                "affair": 680,
                "pay": 555
            }
        }
    } else {
        return {
            "msg": "获取信息失败",
            "success": false,
            "detail": null
        }
    }
});


//查找现场报道信息
Mock.mock('/manager/getpaybyid', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110111") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "ccx",
                "id": "2018110111",
                "major": "绿色通道",
                "proof": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606560097117&di=1116cbcb436b8e4a1eed4e8553de597e&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg"
            }]
        }
    } else if (RegExp.$1 == "2018110112") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "ds",
                "id": "2018110112",
                "major": "普通学生",
                "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
            }]
        }
    } else {
        return {
            "msg": "该用户不存在",
            "success": false,
            "detail": null
        }
    }
});

//获取学费支付状况
Mock.mock('/manager/getpay', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110102") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ccx",
                    "id": "2018110111",
                    "major": "绿色通道",
                    "proof": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606560097117&di=1116cbcb436b8e4a1eed4e8553de597e&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "li",
                    "id": "2018110112",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "wang",
                    "id": "2018110113",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },

                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "绿色通道",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114",
                    "major": "普通学生",
                    "proof": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3361879810,3578970602&fm=26&gp=0.jpg"
                },

            ]
        }
    } else {
        return {
            "msg": "获取信息失败",
            "success": false,
            "detail": null
        }
    }
});

//获取现场报道
Mock.mock('/manager/getreport', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110102") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ccx",
                    "id": "2018110111"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "li",
                    "id": "2018110112"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "wang",
                    "id": "2018110113"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },

                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },

            ]
        }
    } else {
        return {
            "msg": "获取信息失败",
            "success": false,
            "detail": null
        }
    }
});


//获取完成情况
Mock.mock('/manager/test', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110102") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ccx",
                    "id": "2018110111"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "li",
                    "id": "2018110112"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "wang",
                    "id": "2018110113"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },

                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                }, {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                },

            ]
        }
    } else {
        return {
            "msg": "获取信息失败",
            "success": false,
            "detail": null
        }
    }
});
//确认现场报道
Mock.mock('/manager/surereport', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018110112" || RegExp.$1 == "1000" || RegExp.$1 == "2018110113" || RegExp.$1 == "2018110114") {
        return {
            "msg": "确认信息成功",
            "success": true,
            "detail": "success"
        }
    } else {
        return {
            "msg": "确认信息失败",
            "success": false,
            "detail": null
        }
    }
});

//查找现场报道信息
Mock.mock('/manager/getreportbyid', 'post', function(options) {
    var reg = /id=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ccx",
                    "id": "2018110111"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "li",
                    "id": "2018110112"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "wang",
                    "id": "2018110113"
                },
                {
                    "school": "计算机科学学院",
                    "type": "软件工程",
                    "name": "ds",
                    "id": "2018110114"
                }
            ]
        }
    } else if (RegExp.$1 == "2018110111") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "ccx",
                "id": "2018110111"
            }]
        }
    } else if (RegExp.$1 == "2018110112") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "li",
                "id": "2018110112"
            }]
        }
    } else if (RegExp.$1 == "2018110113") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "wang",
                "id": "2018110113"
            }]
        }
    } else if (RegExp.$1 == "2018110114") {
        return {
            "msg": "获取信息成功",
            "success": true,
            "detail": [{
                "school": "计算机科学学院",
                "type": "软件工程",
                "name": "ds",
                "id": "2018110114"
            }]
        }
    } else {
        return {
            "msg": "该学号不存在",
            "success": false,
            "detail": null
        }
    }
});




Mock.mock('/user/userinfo', 'get', function() {

    return {
        "msg": "获取信息失败",
        "success": false,
        "detail": null
    }

});

//获取用户总人数、报道人数、普通管理员人数
Mock.mock('/SystemManager/getNumber', 'post', function(options) {
    var reg = /userId=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 == "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: {
                userSum: 128,
                ManagerSum: 200,
                finished: 56
            }
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "获取失败"
        }
    }
})

//新增通知
Mock.mock('/SystemManager/addInform', 'post', function(options) {
    var reg = /userId=(.*)&title=(.*)&content=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "新增成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "新增失败"
        }
    }
})

//删除通知
Mock.mock('/SystemManager/deleteInform', 'post', function(options) {
    var reg = /userId=(.*)&title=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "删除成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "删除失败"
        }
    }
})

//更新通知
Mock.mock('/SystemManager/updateInform', 'post', function(options) {
    var reg = /userId=(.*)&value=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "更新成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "更新失败"
        }
    }
})

//导入用户（管理员）
Mock.mock('/SystemManager/importUser', 'post', function(options) {
    var reg = /userId=(.*)&UserList=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "导入成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "导入失败"
        }
    }
})

//删除用户（管理员）
Mock.mock('/SystemManager/DeleteUser', 'post', function(options) {
    var reg = /number=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "删除成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "删除失败"
        }
    }
})

//修改用户（管理员）
Mock.mock('/SystemManager/UpdateUser', 'post', function(options) {
    var reg = /userId=(.*)&form=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "修改成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "修改失败"
        }
    }
})

//导入单个用户（管理员）
Mock.mock('/SystemManager/importOneUser', 'post', function(options) {
    var reg = /userId=(.*)&form=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "导入成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "导入失败"
        }
    }
})

//查找用户（管理员）
Mock.mock('/SystemManager/SearchUser', 'post', function(options) {
    var reg = /UserNumber=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: {
                userId: "2019",
                name: "小王",
                college: "计算机科学学院",
                major: "软件工程"
            }
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "删除失败"
        }
    }
})

//密码修改
Mock.mock('/SystemManager/UpdatePwd', 'post', function(options) {
    var reg = /ManagerNumber=(.*)&oldpassword=(.*)&newpassword=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "密码修改成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "密码修改失败"
        }
    }
})

//退出登录
Mock.mock('/SystemManager/DeLogin', 'post', function(options) {
    var reg = /userId=(.*)$/g;
    options.body.match(reg);
    if (RegExp.$1 === "2018") {
        return {
            code: 200,
            message: "操作成功",
            data: "退出成功"
        }
    } else {
        return {
            code: 500,
            message: "操作失败",
            data: "退出失败"
        }
    }
})


//密码修改
Mock.mock('/updatePwd', 'post', function(options) {
    console.log(options.body);
    var reg = /email=(.*)&code=(.*)&password=(.*)$/g;
    options.body.match(reg, RegExp.$1, RegExp.$2);
    if (RegExp.$1 === "2540365386%40qq.com" && RegExp.$2 === "123456") {
        return {
            code: 200,
            message: "修改成功",
            data: "密码修改成功"
        }
    } else {
        return {
            code: 500,
            message: "修改失败",
            data: "密码修改失败"
        }
    }
})

export default Mock;