import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        user:{
            username:window.localStorage.getItem('user' || '[]') == null ? '' :
                JSON.parse(window.localStorage.getItem('user' || '[]')).username
        },
        adminMenus: []
    },
    mutations: {
        login(state, user) {
            state.user = user
            window.localStorage.setItem('user', JSON.stringify(user))
        },
        logout(state) {
            state.usre = []
            window.localStorage.removeItem('user')
        },
        initAdminMenu (state, menus) {
            state.adminMenus = menus
        }
    }
})