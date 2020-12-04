<template>
    <div>
'        <div style="width: 100%">
            <el-card class="admin-header">
                <a href="/mainpage">
                    <img src="../../assets/CCTV.jpg" alt="" width="55px" style="float: left;margin-top: -5px;">
                </a>
                <span style="font-size: 44px;font-weight: bold;float:left;margin-top:10px;margin-left:100px;height: 90px">货 比 三 家</span>
                <search-bar @onSearch='onSearch' ref='searchArea' style="float: left;margin-left: 150px"></search-bar>
                <div @click="goUserCenter">
                    <img class="avator" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="头像">
                </div>
            </el-card>
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
                var _this = this
                this.$axios.get('/logout').then(resp => {
                    if (resp && resp.data.code === 200) {
                        _this.$store.commit('logout')
                        _this.$router.replace('/home')
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
                this.$emit('onSearch')
            }
        }
    }
</script>

<style scoped>
    .admin-header {
        width: 100%;
        height: 90px;
        opacity: 0.85;
        line-height: 40px;
        min-width: 900px;
        margin: auto;
        float: left;
    }
    .el-icon-switch-button {
        cursor: pointer;
        outline:0;
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
    .avator{
        margin-left: 100px;
        width: 50px;
        height: 50px;
        float: left;
        overflow: hidden;
        cursor: pointer;
    }
</style>