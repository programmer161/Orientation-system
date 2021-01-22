import Vue from 'vue'
import VueRouter from 'vue-router'
// import store from '../store'
const Message = () =>
    import ('../views/Message/Message.vue')
const MsgList = () =>
    import ('../views/Message/MsgList.vue')
const MsgValue = () =>
    import ('../views/Message/MsgValue.vue')
    // const Login = () =>
    //     import ('../components/Login.vue')
const Log = () =>
    import ('../views/Main/Login/Log.vue')
const FindId = () =>
    import ('../views/Main/Login/FindId.vue')
const FindPassword = () =>
    import ('../views/Main/Login/FindPassword.vue')

// const StuReportHome = () =>
//     import ('../views/Student/Report/Home.vue')
const Affair = () =>
    import ('../views/Student/Report/Affair.vue')
const ReportInfo = () =>
    import ('../views/Student/Report/ReportInfo.vue')
const ReportNow = () =>
    import ('../views/Student/Report/ReportNow.vue')
const ReInfo = () =>
    import ('../views/Student/Report/Information.vue')


const Localpay = () =>
    import ('../views/Student/Report/localpay.vue')
    // const StuPersonHome = () =>
    //     import ('../views/Student/Person/Home.vue')
const StuInfo = () =>
    import ('../views/Student/Person/Information.vue')


const ManageUser = () =>
    import ('../views/SManager/ManageUser/Home.vue')
const Builduser = () =>
    import ('../views/SManager/ManageUser/Builduser.vue')
const Muser = () =>
    import ('../views/SManager/ManageUser/Muser.vue')
const BasicUser = () =>
    import ('../views/SManager/ManageUser/BasicUser.vue')
const BasicAdmin = () =>
    import ('../views/SManager/ManageUser/BasicAdmin.vue')
const Mrole = () =>
    import ('../views/SManager/ManageUser/Mrole.vue')
const Madetails = () =>
    import ('../views/SManager/ManageUser/Madetails.vue')
const Statistic = () =>
    import ('../views/SManager/ManageUser/Statistic.vue')



const StateHome = () =>
    import ('../views/Manager/Home.vue')
const Pay = () =>
    import ('../views/Manager/Pay.vue')
const MReport = () =>
    import ('../views/Manager/Report.vue')
const Status = () =>
    import ('../views/Manager/Status.vue')
const ManStuInfo = () =>
    import ('../views/Manager/Information.vue')
const FindById = () =>
    import ('../views/Manager/FindById.vue')

const UpdatePwd = () =>
    import ('../components/UpdatePwd.vue')
const Main = () =>
    import ('../components/Main.vue')
const Mainhome = () =>
    import ('../views/Main/Home.vue')
const Person = () =>
    import ('../components/Person.vue')
const Report = () =>
    import ('../components/Report.vue')
    // const Manage = () =>
    //     import ('../components/Manage.vue')
const State = () =>
    import ('../components/State.vue')

const Excel = () =>
    import ('../components/Excel.vue')

const SchoolMsg = () =>
    import ('../views/SManager/Msg/SchoolMsg.vue')
const AddMsg = () =>
    import ('../views/SManager/Msg/AddMessage.vue')
const SchoolStrategy = () =>
    import ('../views/SManager/Msg/SchoolStrategy.vue')
const PayMsg = () =>
    import ('../views/SManager/Msg/PayMsg.vue')
const ReportMsg = () =>
    import ('../views/SManager/Msg/ReportMsg.vue')
const MsgMHome = () =>
    import ('../views/SManager/Msg/Home.vue')
const MsgMessage = () =>
    import ('../views/SManager/Msg/Message.vue')


const LogMHome = () =>
    import ('../views/SManager/LogManage/Home.vue')
const UserLog = () =>
    import ('../views/SManager/LogManage/UserLog.vue')
const SysLog = () =>
    import ('../views/SManager/LogManage/SysLog.vue')


Vue.use(VueRouter)

const routes = [{
        path: '/',
        component: Main,
        // meta: { title: '首页' },
        children: [{
            path: '',
            component: Mainhome,
            meta: { title: '首页' },
        }, ]
    },
    {
        path: '/excel',
        component: Excel,
        meta: { title: '导入' },
    },
    {
        path: '/main',
        component: Main,
        meta: { title: '首页' },
        children: [{
                path: '',
                component: Mainhome,
                meta: { title: '首页' },
            },
            {
                path: 'state',
                component: State,
                meta: { title: '状态确认' },
                children: [{
                        path: '',
                        component: StateHome,
                        meta: { title: '新生统计详情' },
                    },
                    {
                        path: 'pay',
                        component: Pay,
                        meta: { title: '现场缴费' },
                    },
                    {
                        path: 'status',
                        component: Status,
                        meta: { title: '学生状态' },
                    },
                    {
                        path: 'report',
                        component: MReport,
                        meta: { title: '现场报到' },
                    },
                    {
                        path: 'information',
                        component: ManStuInfo,
                        meta: { title: '学生信息' },
                        children: [{
                            path: 'findbyid',
                            component: FindById,
                            meta: { title: '信息详情' },
                        }]
                    },

                ]
            },
            {
                path: 'msgmhome',
                component: MsgMHome,
                meta: { title: '通知管理' },
                children: [{
                        path: '',
                        // name: '学校通知',
                        component: SchoolMsg,
                        meta: { title: '学校通知' },
                    },
                    {
                        path: 'schoolmsg',
                        component: SchoolMsg,
                        // name: '学校通知',
                        meta: { title: '学校通知' },
                        children: [{
                                path: 'message',
                                component: MsgMessage,
                                meta: { title: '修改通知' },
                            },
                            {
                                path: 'addmsg',
                                component: AddMsg,
                                meta: { title: '增加通知' },
                            },
                        ]
                    },
                    {
                        path: 'schoolstrategy',
                        component: SchoolStrategy,
                        // name: '校园攻略',
                        meta: { title: '校园攻略' },
                        children: [{
                                path: 'message',
                                component: MsgMessage,
                                meta: { title: '修改通知' },
                            },
                            {
                                path: 'addmsg',
                                component: AddMsg,
                                meta: { title: '增加通知' },
                            },
                        ]
                    },
                    {
                        path: 'paymsg',
                        component: PayMsg,
                        // name: '缴费须知',
                        meta: { title: '缴费须知' },
                        children: [{
                                path: 'message',
                                component: MsgMessage,
                                meta: { title: '修改通知' },
                            },
                            {
                                path: 'addmsg',
                                component: AddMsg,
                                meta: { title: '增加通知' },
                            },
                        ]
                    },
                    {
                        path: 'reportmsg',
                        component: ReportMsg,
                        // name: '报到须知',
                        meta: { title: '报到须知' },
                        children: [{
                                path: 'message',
                                component: MsgMessage,
                                meta: { title: '修改通知' },
                            },
                            {
                                path: 'addmsg',
                                component: AddMsg,
                                meta: { title: '增加通知' },
                            },
                        ]
                    },

                ]
            },
            {
                path: 'manageuser',
                component: ManageUser,
                meta: { title: '权限管理' },
                children: [{
                        path: '',
                        component: Statistic,
                        meta: { title: '用户统计' },
                    },
                    {
                        path: 'builduser',
                        component: Builduser,
                        meta: { title: '生成用户' },
                    },
                    {
                        path: 'statistic',
                        component: Statistic,
                        meta: { title: '用户统计' },
                    },
                    {
                        path: 'muser',
                        component: Muser,
                        meta: { title: '管理用户' },
                        children: [{
                                path: 'basicuser',
                                component: BasicUser,
                                meta: { title: '编辑用户' },
                            },
                            {
                                path: 'basicadmin',
                                component: BasicAdmin,
                                meta: { title: '编辑管理员' },
                                children: [{
                                    path: 'madetails',
                                    component: Madetails,
                                    meta: { title: '管理详情' },
                                }, ]
                            },

                        ]
                    },
                    {
                        path: 'mrole',
                        component: Mrole,
                        meta: { title: '角色管理' },
                    },
                ]
            },
            {
                path: 'logmanage',
                component: LogMHome,
                meta: { title: '日志管理' },
                children: [{
                        path: '',
                        component: UserLog,
                        meta: { title: '用户日志' },
                    },
                    {
                        path: 'userlog',
                        component: UserLog,
                        meta: { title: '用户日志' },
                    },
                    {
                        path: 'syslog',
                        component: SysLog,
                        meta: { title: '系统日志' },
                    },
                ]
            },
            {
                path: 'message',
                component: Message,
                meta: { title: '通知面包屑' },
                children: [{
                        path: '',
                        component: MsgValue,
                        meta: { title: '通知' },
                    },
                    {
                        path: 'msglist',
                        component: MsgList,
                        meta: { title: '通知列表' },
                    },
                    {
                        path: 'msgvalue',
                        component: MsgValue,
                        meta: { title: '通知' },
                    },
                ]
            },
            {
                path: 'login',
                component: Log,
                meta: { title: '登录' },
            }, {
                path: 'findId',
                component: FindId,
                meta: { title: '查找学号' },
            },
            {
                path: 'findPassword',
                component: FindPassword,
                meta: { title: '忘记密码' },
            },
            {
                path: 'report',
                component: Report,
                meta: { title: '报道流程' },
                children: [{
                        path: '',
                        component: ReInfo,
                        meta: { title: '信息确认' },
                    },
                    {
                        path: 'info',
                        component: ReInfo,
                        meta: { title: '信息确认' },
                    },
                    {
                        path: 'localpay',
                        component: Localpay,
                        meta: { title: '学费支付' },
                    },
                    {
                        path: 'reportinfo',
                        component: ReportInfo,
                        meta: { title: '报到信息' },
                    },
                    {
                        path: 'reportnow',
                        component: ReportNow,
                        meta: { title: '现场报道' },
                    },
                    {
                        path: 'affair',
                        component: Affair,
                        meta: { title: '事务处理' },
                    },
                ]
            },
            {
                path: 'person',
                component: Person,
                meta: { title: '个人中心' },
                children: [{
                        path: '',
                        component: StuInfo,
                        meta: { title: '个人资料' },
                    },
                    {
                        path: 'info',
                        component: StuInfo,
                        meta: { title: '个人资料' },
                    },
                    {
                        path: 'updatepwd',
                        component: UpdatePwd,
                        meta: { title: '修改密码' },
                    },

                ]
            },

        ]
    },


]

const router = new VueRouter({
    routes
})

// Vue.use(Router)
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export default router