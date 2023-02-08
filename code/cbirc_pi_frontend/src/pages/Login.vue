<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-box-label">银保监会政策解读库 登录</div>
      <el-input v-model="username" placeholder="请输入用户名" class="login-box-input"></el-input>
      <el-input v-model="password" placeholder="请输入密码" class="login-box-input" type="password"></el-input>
      <div class="login-box-jump" @click="jumpToRegister">立即注册>></div>
      <el-button class="login-box-btn" @click="handleLogin">登录</el-button>
    </div>
  </div>
</template>

<script>
import { setToken } from '@/utils/auth'
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    handleLogin () {
      let legalPassword = /^[a-zA-Z0-9!@#$%^&*_-]{6,16}$/
      let errorMsg = ''
      if (this.password.length < 6 || !legalPassword.test(this.password)) {
        errorMsg = '密码格式不正确！请重试'
      }
      if (errorMsg.length) {
        this.$message({
          message: errorMsg,
          type: 'error'
        })
        return
      }
      this.$store.dispatch('login', {
        name: this.username,
        password: this.password
      }).then(res => {
        if (res) {
          this.$message({
            message: '登录成功！',
            type: 'success'
          })
          setToken({
            token: res.data.token,
            userName: this.username
          })
          this.$store.commit('setUserName', this.username)
          this.$router.push(this.$route.query.redirect ? this.$route.query.redirect : '/')
        }
      }).catch(error => {
        this.$message({
          message: error.data.message,
          type: 'error'
        })
      })
    },
    jumpToRegister () {
      this.$router.push({
        name: 'Register',
        query: {
          redirect: this.$route.query.redirect
        }
      })
    }
  }
}
</script>

<style lang="less">
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('../../public/bgpic.jpg');
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  .login-box {
    height: 400px;
    width: 600px;
    background-color: #00000050;
    border-radius: 20px;
    padding: 100px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    &-label {
      text-align: left;
      font-weight: 700;
      font-size: 18px;
      width: 100%;
      margin-bottom: 20px;
    }
    &-input {
      margin-top: 10px;
    }
    &-jump {
      align-self: flex-end;
      font-size: 12px;
      cursor: pointer;
      color: white;
      &:hover {
        color: #cc0000;
      }
    }
    &-btn {
      margin-top: 10px;
      width: 200px;
    }
  }
}
</style>
