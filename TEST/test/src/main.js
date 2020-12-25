import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUi from 'element-ui'
import store from './store'
import 'element-ui/lib/theme-chalk/index.css'

// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUi)

router.beforeEach((to, from, next) => {
    if (store.state.user.username && to.path.startsWith('/admin')) {
        initAdminMenu(router, store)
    }
    next();
    /*
    // 已登录状态下访问 login 页面直接跳转到后台首页
    if (store.state.username && to.path.startsWith('/login')) {
        next({
            path: 'admin/dashboard'
        })
    }
    if (to.meta.requireAuth) {
        if (store.state.user.username) {
            axios.get('/authentication').then(resp => {
                if (resp) next()
            })
        } else {
            next({
                path: 'login',
                query: {redirect: to.fullPath}
            })
        }
    } else {
        next()
    }

     */
})

const initAdminMenu = (router, store) => {
    if (store.state.adminMenus.length > 0) {
        return
    }
    /*
    axios.get('/menu').then(resp => {
        if (resp && resp.status === 200) {
            var fmtRoutes = formatRoutes(resp.data)
            router.addRoutes(fmtRoutes)
            store.commit('initAdminMenu', fmtRoutes)
        }
    })
     */
    axios.post('/menu',
        {
         username:store.state.user.username,
        }).then( resp=> {
            if (resp && resp.status === 200) {
                console.log(resp.data.data)
                var fmtRoutes = formatRoutes(resp.data.data)
                router.addRoutes(fmtRoutes)
                store.commit('initAdminMenu', fmtRoutes)
            }
        })
}

const formatRoutes = (routes) => {
    let fmtRoutes = []
    routes.forEach(route => {
        if (route.children) {
            route.children = formatRoutes(route.children)
        }
        let fmtRoute = {
            path: route.path,
            component: resolve => {
                require(['./components/admin/' + route.component + '.vue'], resolve)
            },
            name: route.name,
            nameZh: route.nameZh,
            iconCls: route.iconCls,
            meta: {
                requireAuth: true
            },
            children: route.children
        }
        fmtRoutes.push(fmtRoute)
    })
    return fmtRoutes
}

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>',
}).$mount('#app')

/*
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
 */