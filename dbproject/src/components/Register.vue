<template>
    <body id="paper">
    <el-form class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
        <h3 class="login_title">用户注册</h3>
        <el-form-item>
            <el-input type="text" v-model="loginForm.email"
                      auto-complete="off" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input type="text" v-model="loginForm.username"
                      auto-complete="off" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input type="password" v-model="loginForm.password"
                      auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item>
            <el-select style = "width:100%" v-model="valueMember" multiple placeholder="请选择平台会员" >
                <el-option
                        v-for="item in optionMember"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-select style = "width:100%" v-model="valueStudent" placeholder="是否为学生" >
                <el-option
                        v-for="item in optionStudent"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item style="width: 100%">
            <router-link to="login">
                <el-button type="primary"  style="width:40%;background: #505458;
                        border:none;margin-right: 10px">返回登录</el-button>
            </router-link>
            <el-button type="primary" style="width: 40%;background: #505458;border: none;margin-left: 10px" v-on:click="register">注册</el-button>
        </el-form-item>
    </el-form>
    </body>
</template>

<script>
    export default {
        name: "Register",
        data () {
            return {
                checked: true,
                loginForm: {
                    email:'',
                    username: '',
                    password: '',
                    isMember:'',
                    isVip:'',
                    isStudent:''
                },
                loading: false,
                optionMember: [{
                    value: '1',
                    label: '京东'
                }, {
                    value: '2',
                    label: '淘宝'
                }, {
                    value: '3',
                    label: '苏宁'
                }],
                valueMember: [],
                optionStudent: [{
                    value: '1',
                    label: '是'
                }, {
                    value: '0',
                    label: '否'
                }],
                valueStudent: '',
            }
        },
        methods: {
            register () {
                if (this.loginForm.email === '') {
                    this.$alert('请输入邮箱', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                } else if (this.loginForm.username === '') {
                    this.$alert('请输入用户名', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                } else if (this.loginForm.password === '') {
                    this.$alert('请输入密码', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                }
                var valueMembertemp = String();
                var i;
                // 最低位为京东，第二位为淘宝，最高位为苏宁
                for (i = 1;i <= 3;i++) {
                    if (this.valueMember.indexOf(i.toString()) >= 0) {
                        //console.log(i + "true")
                        valueMembertemp = valueMembertemp + "1"
                    } else {
                        //console.log(i + "false")
                        valueMembertemp = valueMembertemp + "0"
                    }
                }
                this.loginForm.isMember = valueMembertemp;
                this.loginForm.isStudent = (this.valueStudent === '')?'0':this.valueStudent;
                this.loginForm.isVip = '0'
                var _this = this
                this.$axios
                    .post('/register', {
                        email:this.loginForm.email,
                        username: this.loginForm.username,
                        password: this.loginForm.password,
                        ismember: this.loginForm.isMember,
                        isstudent: this.loginForm.isStudent,
                        isvip: this.loginForm.isVip,
                        date: this.getMyTime()
                    })
                    .then(resp => {
                        if (resp.data.code === 200) {
                            this.$alert('注册成功', '提示', {
                                confirmButtonText: '确定'
                            })
                            _this.$router.replace('/login')
                        } else {
                            this.$alert(resp.data.message, '提示', {
                                confirmButtonText: '确定'
                            })
                        }
                    })
                    .catch(failResponse => {
                        console.log(failResponse)
                    })
            }
        }
    }
</script>

<style scoped>
    #paper {
        background:url("../assets/login.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    body{
        margin: -5px 0px;
    }
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
</style>