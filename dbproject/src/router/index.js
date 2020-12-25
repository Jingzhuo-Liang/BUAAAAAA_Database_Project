import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/components/Login'
import Register from "@/components/Register";
import Home from '../components/home/Home'
import Mainpage from "@/components/home/Mainpage";
import Header from "@/components/home/Header";
import Recommend from "@/components/home/Recommend";
import SideMenu from "@/components/home/SideMenu";
import Commodities from "@/components/home/Commodities";
import CommodityInfo from "@/components/commodityInfo/CommodityInfo";
import Message from "@/components/commodityInfo/Message";
import LeaveMessage from "@/components/commodityInfo/LeaveMessage";
import ReplyMessage from "@/components/commodityInfo/ReplyMessage";
import Graph from "@/components/commodityInfo/Graph";
import UserCenter from "@/components/usercenter/UserCenter";

Vue.use(Router)

export default new Router({
    mode:'history',
    routes: [
        // 下面都是固定的写法
        {path: '/', redirect:'/login'},
        {path: '/login', name: 'Login', component: Login},
        {path:'/register', name:'Register',component:Register},
        {path:'/home',name:'Home',component:Home,redirect:'/mainpage',
            children:[
                {path:'/header', name:'Header', component: Header, meta: {requireAuto: false}},
                {path:'/mainpage', name:'MainPage',component:Mainpage,meta: {requireAuto:false,title:'货比三家网'}},
                {path:'/recommend',name:'Recommend',component:Recommend, meta: {requireAuto: false}},
                {path:'/sideMenu', name:'SideMenu',component:SideMenu, meta:{requireAuto: false}},
                {path:'/commodities', name:'Commodities',component:Commodities, meta:{requireAuto: false}},
                {path:'/userCenter',name:'UserCenter', component:UserCenter,meta:{requireAuto: false}}
            ]
        },
        {path:'/CommodityInfo', name:'CommodityInfo', component:CommodityInfo, meta:{requireAuto:false,title:'货比三家网'},
            children:[
                {path:'/message',name: 'Message',component: Message,meta: {requireAuto:false},
                    children:[
                        {path: 'leavemessage',name: 'LeaveMessage',component: LeaveMessage,meta: {requireAuto:false}},
                        {path: 'replymessage',name: 'ReplyMessage',component: ReplyMessage,meta: {requireAuto: false}},
                        {path:'/graph',name:'Graph',component:Graph,meta:{requireAuto: false}},
                    ]
                },

            ]
        },

        //{path: '/error', name: 'Error' , component:Error},
    ]
})