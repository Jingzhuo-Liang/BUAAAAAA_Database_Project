import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'
import Error from '@/components/Error'
import Home from '../components/Home'
import LibraryIndex from "@/components/library/LibraryIndex";
import SideMenu from "@/components/library/SideMenu";
import Books from "@/components/library/Books";
import EditForm from "@/components/library/EditForm";
import SearchBar from "@/components/library/SearchBar";
import Register from "@/components/Register";
import AdminIndex from "@/components/admin/AdminIndex"
import AdminMenu from "@/components/admin/AdminMenu";
import Header from "@/components/admin/Header";

Vue.use(Router)

export default new Router({
    mode:'history',
    routes: [
        // 下面都是固定的写法
        {path: '/', redirect:'/login'},
        {path:'/home',name:'Home', component:Home, redirect:'/index',
            children:[
                {path:'/index',name:'AppIndex',component:AppIndex,meta:{requireAuth: true}},
                {path:'/library',name:'Library',component: LibraryIndex,meta: {requireAuth: true}},
                {path:'/SideMenu',name:'SideMenu',component: SideMenu,meta: {requireAuth: true}},
                {path:'/Books',name:'Books',component: Books,meta: {requireAuth: true}},
                {path:'/EditForm', name:'EditForm',component:EditForm,meta:{requireAuth: true}},
                {path:'/SearchBar', name:'SearchBar',component:SearchBar,meta:{requireAuth: true}},
                //{path: '/admin', name: 'Admin', component:AdminIndex, meta: {requireAuth: true}},
            ]
        },
        {path: '/admin', name: 'Admin', component:AdminIndex, meta: {requireAuth: true},
            children: [
                {path:'/adminMenu',name:'AdminMenu',component:AdminMenu,mete:{requireAuth:true}},
                {path:'/header',name:'Header',component:Header,mete:{requireAuth:true}},
            ]
        },
        {path: '/login', name: 'Login', component: Login},
        {path:'/register', name:'Register',component:Register},
        {path: '/error', name: 'Error' , component:Error},
    ]
})


// 用于创建默认路由
//eslint-disable-next-line
export const createRouter = routes => new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Default',
            redirect: '/home',
            component: Home
        },
        {
            // home页面并不需要被访问，只是作为其它组件的父组件
            path: '/home',
            name: 'Home',
            component: Home,
            redirect: '/index',
            children: [
                {
                    path: '/index',
                    name: 'AppIndex',
                    component: () => import('../components/home/AppIndex')
                },
                /*
                {
                    path: '/jotter',
                    name: 'Jotter',
                    component: () => import('../components/jotter/Articles')
                },
                {
                    path: '/jotter/article',
                    name: 'Article',
                    component: () => import('../components/jotter/ArticleDetails')
                {
                    path: '/admin/content/editor',
                    name: 'Editor',
                    component: () => import('../components/admin/content/ArticleEditor'),
                    meta: {
                        requireAuth: true
                    }
                },
                */
                {
                    path: '/library',
                    name: 'Library',
                    component: () => import('../components/library/LibraryIndex')
                }
            ]
        },
        {
            path: '/login',
            name: 'Login',
            component: () => import('../components/Login')
        },
        {
            path: '/register',
            name: 'Register',
            component: () => import('../components/Register')
        },
        {
            path: '/admin',
            name: 'Admin',
            component: () => import('../components/admin/AdminIndex'),
            meta: {
                requireAuth: true
            },
            children: [
                {
                    /*
                    path: '/admin/dashboard',
                    name: 'Dashboard',
                    component: () => import('../components/admin/dashboard/admin/index'),
                    meta: {
                        requireAuth: true
                    }

                     */
                }
            ]
        },
        {
            path: '*',
            component: () => import('../components/pages/Error404')
        }
    ]
})


