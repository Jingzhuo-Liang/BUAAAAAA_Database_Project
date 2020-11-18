<template>
    <body id="poster">
        <el-form class="login-container" label-position="left" label-width="0px">
            <h3 class="login_title">系统登录</h3>
            <el-form-item>
                <el-input type = "text" v-model="loginForm.username"
                        auto-complete="off" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-input type="password" v-model="loginForm.password"
                        auto-complete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item style="width: 100%">
                <el-button type="primary" style="width:40%;background: #505458;
                        border:none;margin-right: 10px" v-on:click="login" >登录</el-button>
                <router-link to="register">
                    <el-button type="primary"  style="width: 40%;background: #505458;border: none;margin-left: 10px">注册</el-button>
                </router-link>
            </el-form-item>
        </el-form>
    </body>
</template>

<script>

    export default {
        name: 'Login',
        data () {
            return {
                loginForm: {
                    username: 'admin',
                    password: '123'
                },
                responseResult: []
            }
        },
        methods: {
            login () {
                var _this = this;
                console.log(this.$store.state)
                this.$axios
                    .post('/login', {
                        username: this.loginForm.username,
                        password: this.loginForm.password
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            _this.$store.commit('login',_this.loginForm)
                            var path = this.$route.query.redirect
                            this.$router.replace({path: path === '/' || path === undefined ?'/index':path})
                        }
                        else if (successResponse.data.code === 400) {
                            this.$alert(successResponse.data.message,"提示",{
                                confirmButtonText: '确定'
                            })
                            //this.$router.replace({path: '/login'})
                        }
                    })
                    .catch(failResponse => {
                        if (failResponse.data.code === 100) {
                            this.$router.replace({path: '/error'})
                        }
                    })
            }
        }
    }
</script>
<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }

    #poster {
        background:url("../assets/img/bg/eva1.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    body{
        margin: 0px;
    }
</style>