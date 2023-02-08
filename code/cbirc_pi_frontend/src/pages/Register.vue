<template>
  <div class="register-container">
    <div class="register-box">
      <div class="register-box-label">银保监会政策解读库 注册</div>
      <el-input v-model="username" placeholder="请输入用户名" class="register-box-input" :maxlength="16"></el-input>
      <el-input v-model="password" placeholder="请输入密码" class="register-box-input" type="password" :maxlength="16"></el-input>
      <el-input v-model="passwordAgain" placeholder="请确认密码" class="register-box-input" type="password" :maxlength="16"></el-input>
      <div class="register-box-jump" @click="jumpToLogin">已有账号？立即登录>></div>
      <el-button @click="register" class="register-box-btn">注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      username: '',
      password: '',
      passwordAgain: ''
    }
  },
  methods: {
    register () {
      let legalUserName = /^[a-zA-Z][a-zA-Z0-9_]{5,16}$/
      let legalPassword = /^[a-zA-Z0-9!@#$%^&*_-]{6,16}$/
      let errorMsg = ''
      if (this.username.length < 6) {
        errorMsg = '用户名长度不得少于6位'
      } else if (!legalUserName.test(this.username)) {
        errorMsg = '用户名必须以字母开头，且只能包含字母数字及下划线'
      } else if (this.password !== this.passwordAgain) {
        errorMsg = '两次输入的密码不一致'
      } else if (this.password.length < 6) {
        errorMsg = '密码长度不得少于6位'
      } else if (!legalPassword.test(this.password)) {
        errorMsg = '密码包含非法字符'
      }
      if (errorMsg.length) {
        this.$message({
          message: errorMsg,
          type: 'error'
        })
        return
      }
      this.$store.dispatch('register', {
        name: this.username,
        password: this.password
      }).then(res => {
        if (res.data.success) {
          this.$message({
            message: '注册成功！已跳转至登录界面',
            type: 'success'
          })
          this.jumpToLogin()
        }
      }).catch(error => {
        this.$message({
          message: error.data.message,
          type: 'error'
        })
      })
    },
    jumpToLogin () {
      this.$router.push({
        name: 'Login',
        query: {
          redirect: this.$route.query.redirect
        }
      })
    }
  }
}
</script>

<style lang="less">
.register-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('../../public/bgpic.jpg');
  background-size: 100% 100%;
  background-position: center center;
  .register-box {
    height: 400px;
    width: 600px;
    background-color: #00000050;
    border-radius: 20px;
    padding: 100px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
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
