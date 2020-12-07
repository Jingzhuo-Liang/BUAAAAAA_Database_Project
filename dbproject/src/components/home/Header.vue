<template>
    <div>
        <div class="top">
            <div class="center">
                <ul class="f1">
                    <li style="margin-left: 20px"> <a >货币三家网</a></li>
                    <li style="margin-left: 10px"> <a >后台管理系统 </a></li>
                    <li style="margin-left: 10px"> <a >GitHub地址</a></li>
                    <li style="margin-left: 10px"> <a >设计文档</a></li>
                    <li style="margin-left: 10px"> <a >捐赠</a></li>
                    <li style="margin-left: 10px"> <a >问题反馈</a></li>
                </ul>
                <div style="width: 20%;float: right" v-show="this.$store.state.user.id === ''" >
                    <ul class="f2" style="float: right">
                        <li style="float: right"> <a href="/login">登录 |</a></li>
                        <li style="float: right;margin-left: 5px;"> <a href="/register">注册</a></li>
                    </ul>
                </div>
                <div style="width: 20%;float: right" v-show="this.$store.state.user.id !== ''" v-on:click="goUserCenter" >
                    <span class="username">你好！{{this.$store.state.user.username}}</span>
                </div>
                <div class="collection">
                    <i class="el-icon-goods" v-on:click="goUserCenter">收藏夹</i>
                </div>
            </div>
        </div>
        <div style="width: 70%;margin: auto">
            <div class="admin-header">
                <div style="float: left">
                    <a href="/mainpage">
                        <img src="../../assets/logo.png" alt="" width="200px;" height="90px" style="float: left">
                    </a>
                </div>
                <div style="font-size: 44px;font-weight: bold;float:left;margin-top:25px;margin-left:100px;height: 90px">
                    货 比 三 家
                </div>
                <div style="margin-top: 20px">
                    <search-bar @onSearch='onSearch' ref='searchArea' style="float: right;margin-left: 150px"></search-bar>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import SearchBar from "@/components/home/SearchBar";
    export default {
        name: "Header",
        components: {SearchBar},
        data() {
            return {
                keywords:''
            }
        },
        methods: {
            goUserCenter() {
                if (this.$store.state.user.username === '') {
                    this.$confirm("请先登录" , "提示" ,{
                        confirmButtonText:'去登录',
                        cancelButtonText:'暂不登录'
                    }).then(()=>{
                        this.$router.replace('/login')
                    }).catch(()=>{

                    })
                    return
                }
                this.$router.replace('/userCenter');
            },
            logout () {
                this.$axios.get('/logout').then(resp => {
                    if (resp && resp.data.code === 200) {
                        this.$store.commit('logout')
                        this.$router.replace('/home')
                        // 清空路由，防止路由重复加载
                        //const newRouter = new Router()
                        //_this.$router.matcher = newRouter.matcher
                    }
                }).catch(failResponse => {
                    console.log(failResponse)
                })
            },
            onSearch() {
                this.keywords = this.$refs.searchArea.keywords
                this.$router.replace('/mainPage')
                this.$emit('onSearch')
            },
        }
    }
</script>

<style scoped>
    .username{
        font-size: 14px;
        color: #cac6c6;
        float: right;
        width: 40%;   /*一定要设置宽度，或者元素内含的百分比*/
        overflow:hidden; /*溢出的部分隐藏*/
        white-space: nowrap; /*文本不换行*/
        text-overflow:ellipsis;/*ellipsis:文本溢出显示省略号（...）；clip：不显示省略标记（...），而是简单的裁切*/
        cursor : pointer;
    }
    .collection {
        display: flex;
        width: 10%;
        font-size: 14px;
        color: #fff;
        margin: 0 20px;
        justify-content: center;
        align-items: center;
        background-color: #1baeae;
        height: 100%;
        cursor : pointer;
    }
    .admin-header {
        width: 100%;
        height: 90px;
        opacity: 0.85;
        line-height: 40px;
        min-width: 900px;
        margin: auto;
        float: left;
    }
    span {
        display: inline-block;
        line-height: 20px;
        padding: 5px 0;
        margin-right: 10px;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
        text-align: left;
    }
    .f2 {
        color: #cac6c6;
        flex-shrink: 0;
        display: flex;
        float: right;
        font-size: 14px;
    }
    .center {
        width: 70%;
        height: 40px;
        flex-shrink: 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 10px;
        float: left;
    }
    ul{
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 0px;
    }
    li{
        list-style-type: none;
        float: left;
    }
    .f1{
        color: #cac6c6;
        flex-shrink: 0;
        display: flex;
        width: 60%;
        float: left;
        font-size: 12px;
    }
    .top{
        display: flex;
        justify-content: center;
        width: 100%;
        height: 40px;
        background-color: #333;
    }
    a{
        text-decoration:none ;
        color:#cac6c6 ;
    }
</style>