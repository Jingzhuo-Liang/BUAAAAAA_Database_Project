import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
    namespaced: true,
    state:{
        user:{
            id: window.localStorage.getItem('id') === null ? '' :JSON.parse(window.localStorage.getItem('id')),
            username: window.localStorage.getItem('username') === null ? '' :JSON.parse(window.localStorage.getItem('username')),
            email:window.localStorage.getItem('email') === null ? '' :JSON.parse(window.localStorage.getItem('email')),
            isStudent:window.localStorage.getItem('isStudent') === null ? '' :JSON.parse(window.localStorage.getItem('isStudent')),
            isMember:window.localStorage.getItem('isMember') === null ? '' :JSON.parse(window.localStorage.getItem('isMember')),
            isVip:window.localStorage.getItem('isVip') === null ? '' :JSON.parse(window.localStorage.getItem('isVip')),
            date:window.localStorage.getItem('date') === null ? '' :JSON.parse(window.localStorage.getItem('date'))
        },
        adminMenus: [],
        commodity:'',
    },
    mutations: {
        login(state, user) {
            state.user.id = user.id
            state.user.username = user.username
            state.user.email = user.email
            state.user.isStudent = user.isstudent
            state.user.isMember = user.ismember
            state.user.isVip = user.isvip
            state.user.date = user.date
            window.localStorage.setItem('id', JSON.stringify(user.id))
            window.localStorage.setItem('username', JSON.stringify(user.username))
            window.localStorage.setItem('email', JSON.stringify(user.email))
            window.localStorage.setItem('isStudent', JSON.stringify(user.isstudent))
            window.localStorage.setItem('isMember', JSON.stringify(user.ismember))
            window.localStorage.setItem('isVip', JSON.stringify(user.isvip))
            window.localStorage.setItem('date', JSON.stringify(user.date))
        },
        logout(state) {
            state.user = {
                id:'',
                username:'',
                email:'',
                isStudent:'',
                isMember:'',
                isVip:'',
            }
            window.localStorage.removeItem('id')
            window.localStorage.removeItem('username')
            window.localStorage.removeItem('email')
            window.localStorage.removeItem('isStudent')
            window.localStorage.removeItem('isMember')
            window.localStorage.removeItem('isVip')
        },
        initAdminMenu (state, menus) {
            state.adminMenus = menus
        },
        setInfo(state, commodity) {
            state.commodity = commodity
            window.localStorage.setItem('commodity', JSON.stringify(commodity))
        },
        getInfo(state) {
            console.log(state.commodity.id)
            return window.localStorage.getItem('commodity')
            //return state.commodity
        }
    }
})