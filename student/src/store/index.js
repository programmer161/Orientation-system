import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        //学号/工号
        id: "",
        //权限
        list: [],
        msgid: 0,
        msgtitle: "",
        msgtype: "",
        // url: "",
        stuInfro: [],
        stuState: {},
        stratelist: [],
        tuitionlist: [],
        msglist: [],
        gao: 0,
        msgnum: 3,
        pathflag: 0,
        loginflag: 1,
        reportflag: 1,
        userflag: 1,
        adminflag: 1,
        infoflag: 1,
        //this.$store.state.code
        msgId: 0,
    },
    mutations: {
        setMsgtype(state, data) {
            state.msgtype = data;
        },
        setinfoflag(state, data) {
            state.infoflag = data;
        },
        updateInfo(state, data) {
            state.stuInfro = data;
        },
        updateSelf(state, data) {
            state.stuInfro.userName = data.username;
            state.stuInfro.email = data.email;
            state.stuInfro.sex = data.radio;
            state.stuInfro.name = data.name;
            state.stuInfro.birth = data.birth;
        },
        setreport(state, data) {
            state.reportflag = data;
        },
        setuserflag(state, data) {
            state.userflag = data;
        },
        setadminflag(state, data) {
            state.adminflag = data;
        },
        setloginflag(state, data) {
            state.loginflag = data;
        },
        setPath(state, data) {
            state.pathflag = data;
        },
        setMsgNum(state, data) {
            state.msgnum = data;
        },
        setId(state, data) {
            state.id = data.id;
            state.list = data.list;
            state.stuInfro = data.stuinfo;

        },
        setGao(state, data) {
            state.gao = data;
            console.log(state.gao)
        },
        setauth(state, data) {
            if (data == "ROLE_SysManager") {
                state.gao = 1;
            } else {
                state.gao = 0;
            }
        },
        clearId(state) {
            state.id = "";
            state.list = [{
                    "link": "/main/report",
                    "name": "报道流程",
                    "code": 0
                },
                {
                    "link": "/main/state",
                    "name": "状态确认",
                    "code": 0
                },
                {
                    "link": "/main/manage",
                    "name": "高级管理",
                    "code": 0
                },
                {
                    "link": "/main/person",
                    "name": "个人中心",
                    "code": 0
                }
            ];
            state.msgId = 0;
            state.url = "";
            state.gao = 0;
        },
        setMsgId(state, data) {
            state.msgid = data.id;
            state.msgtitle = data.title;
            state.msgtype = data.type;
            state.msgId = data.id;
        },
        setStuInfro(state, data) {
            state.stuInfro = data;
        },
        setStuState(state, data) {
            state.stuState = data;
        },
        setMsgList(state, data) {
            state.msglist = data;
        },
        logout(state) {
            state.id = "";
        }
    },
    actions: {
        //共用的数据，不需多次请求  异步
        // this.$store.dispatch("name")
    },
    modules: {}
})