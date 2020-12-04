import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUi from 'element-ui'
import store from './store'
import 'element-ui/lib/theme-chalk/index.css'
import VChars from 'v-charts'
import  qs from 'qs'

// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8666/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.prototype.$qs = qs

Vue.use(VChars)
Vue.use(ElementUi)

var date = new Date()
Vue.prototype.getMyTime = function () {
  return (date.getFullYear() + "-" +
      ((date.getMonth() + 1 >= 10) ? (date.getMonth() + 1) : ("0" + (date.getMonth() + 1))) + "-" +
      ((date.getDate() >= 10) ? (date.getDate()) : ("0" + date.getDate())) + " " +
      ((date.getHours() >= 10) ? (date.getHours()) : ("0" + date.getHours())) + ":" +
      ((date.getMonth() >= 10) ? (date.getMonth()) : ("0" + date.getMonth())) + ":" +
      ((date.getSeconds() >= 10) ? (date.getSeconds()) : ("0" + date.getSeconds())))
}


router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next();
})
/*
const initAdminMenu = (router, store) => {
  if (store.state.adminMenus.length > 0) {
    return
  }
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
*/

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