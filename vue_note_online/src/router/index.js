import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/user/Home.vue'
import User from '@/views/user/User.vue'
import Login from '@/views/user/Login.vue'
import Appeal from "@/views/user/Appeal"
import Forget from '@/views/user/Forget.vue'
import Message from '@/views/user/Message.vue'
import Register from '@/views/user/Register.vue'
import UpdatePwdByPwd from '@/views/user/UpdatePwdByPwd.vue'
import CheckState from "@/views/user/CheckState";
import SeeNote from '@/views/note/SeeNote.vue'
import WriteNote from '@/views/note/WriteNote.vue'
import SearchNote from '@/views/note/SearchNote.vue'
import ChangeNote from '@/views/note/ChangeNote.vue'
import NoteBook from '@/views/notebook/NoteBook.vue'
import SeeNotebook from '@/views/notebook/SeeNotebook.vue'
import NewNoteBook from '@/views/notebook/NewNoteBook.vue'
import SearchNotebook from '@/views/notebook/SearchNotebook.vue'
import ClassifyByTime from '@/views/notebook/ClassifyByTime.vue'
import ClassifyByType from '@/views/notebook/ClassifyByType.vue'
import NotebookManagement from '@/views/notebook/NotebookManagement.vue'
import FriendList from "@/views/friend/FriendList"
import FriendHome from "@/views/friend/FriendHome"
import SearchUser from "@/views/friend/SearchUser"
import AdminLogin from "@/views/admin/AdminLogin";
import NoteDataAnalysis from '@/views/admin/NoteDataAnalysis.vue'
import UserDataAnalysis from '@/views/admin/UserDataAnalysis.vue'
import NoteAudit from '@/views/admin/NoteAudit'
import CheckAndAudit from "@/views/admin/CheckAndAudit";
import AuditSuccess from "@/views/admin/AuditSuccess";
import AuditLog from "@/views/admin/AuditLog";
import ReleaseMessage from "@/views/admin/ReleaseMessage";
import CheckFeedback from "@/views/admin/CheckFeedback";
import AppealAudit from "@/views/admin/AppealAudit";
import CheckAndAduitAppeal from "@/views/admin/CheckAndAduitAppeal";
import ShieldManage from "@/views/admin/ShieldManage";
import AdminUpdate from "@/views/admin/AdminUpdate";
import AdminPwd from "@/views/admin/AdminPwd";
import AdminRegister from "@/views/admin/AdminRegister";
import AddRNum from "@/views/admin/AddRNum";

Vue.use(VueRouter)

const routes = [
    /* 用户模块 */
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/user',
        name: 'User',
        component: User
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/appeal/:mno',
        name: 'Appeal',
        component: Appeal
    },
    {
        path: '/forget',
        name: 'Forget',
        component: Forget
    },
    {
        path: '/message',
        name: 'Message',
        component: Message
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/updatePwdByPwd',
        name: 'UpdatePwdByPwd',
        component: UpdatePwdByPwd
    },
    {
        path: '/checkState/:no',
        name: 'CheckState',
        component: CheckState
    },
    /* 笔记模块 */
    {
        path: '/seeNote/:noteNo',
        name: 'SeeNote',
        component: SeeNote
    },
    {
        path: '/writeNote/:notebookNo',
        name: 'WriteNote',
        component: WriteNote
    },
    {
        path: '/searchNote',
        name: 'SearchNote',
        component: SearchNote
    },
    {
        path: '/changeNote/:noteNo',
        name: 'ChangeNote',
        component: ChangeNote
    },
    /* 笔记本模块 */
    {
        path: '/notebook',
        name: 'Notebook',
        component: NoteBook
    },
    {
        path: '/seeNotebook/:notebookNo',
        name: 'SeeNotebook',
        component: SeeNotebook
    },
    {
        path: '/newNotebook',
        name: 'NewNoteBook',
        component: NewNoteBook
    },
    {
        path: '/searchNotebook',
        name: 'SearchNotebook',
        component: SearchNotebook
    },
    {
        path: '/classifyByTime',
        name: 'ClassifyByTime',
        component: ClassifyByTime
    },
    {
        path: '/classifyByType',
        name: 'ClassifyByType',
        component: ClassifyByType
    },
    {
        path: '/notebookManagement/:notebookNo',
        name: 'NotebookManagement',
        component: NotebookManagement
    },
    /* 好友模块 */
    {
        path: '/friend/friendList/',
        name: 'FriendList',
        component: FriendList
    },
    {
        path: '/friend/friendHome/:friendNo',
        name: 'FriendHome',
        component: FriendHome
    },
    {
        path: '/friend/searchUser',
        name: 'SearchUser',
        component: SearchUser
    },
    /* 管理员模块 */
    {
        path: '/admin/login',
        name: 'AdminLogin',
        component: AdminLogin
    },
    {
        path: '/admin',
        name: 'NoteDataAnalysis',
        component: NoteDataAnalysis
    },
    {
        path: '/admin/userData',
        name: 'UserDataAnalysis',
        component: UserDataAnalysis
    },
    {
        path: '/admin/noteAudit',
        name: 'NoteAudit',
        component: NoteAudit
    },
    {
        path: '/admin/checkAndAudit/:irrNo',
        name: 'CheckAndAudit',
        component: CheckAndAudit
    },
    {
        path: '/admin/auditSuccess/:noteName/:noteNo/:iType/:res',
        name: 'AuditSuccess',
        component: AuditSuccess
    },
    {
        path: '/admin/auditLog',
        name: 'AuditLog',
        component: AuditLog
    },
    {
        path: '/admin/releaseMessage',
        name: 'ReleaseMessage',
        component: ReleaseMessage
    },
    {
        path: '/admin/checkFeedback',
        name: 'CheckFeedback',
        component: CheckFeedback
    },
    {
        path: '/admin/appealAudit',
        name: 'AppealAudit',
        component: AppealAudit
    },
    {
        path: '/admin/caaAppeal/:ano',
        name: 'CheckAndAduitAppeal',
        component: CheckAndAduitAppeal
    },
    {
        path: '/admin/shieldManage',
        name: 'ShieldManage',
        component: ShieldManage
    },
    {
        path: '/admin/adminUpdate',
        name: 'AdminUpdate',
        component: AdminUpdate
    },
    {
        path: '/admin/adminPwd',
        name: 'AdminPwd',
        component: AdminPwd
    },
    {
        path: '/admin/adminRegister',
        name: 'AdminRegister',
        component: AdminRegister
    },
    {
        path: '/admin/addRNum',
        name: 'AddRNum',
        component: AddRNum
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
