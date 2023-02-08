<template>
  <div class="header">
    <div class="logo">
      <i class="el-icon-reading" :class="{'el-icon-reading--selected': $route.fullPath === '/dashboard'}" @click="jumpToDashboard"/>
    </div>
    <div class="title">
      银保监会政策解读库
    </div>
    <div class="avator">
      <el-tooltip effect="light">
        <div slot="content">
          <div class="avator-username">{{ $store.getters.getCurrentUserName }}</div>
          <el-button type="text" size="small" @click="handleLogout">退出登录</el-button>
          <!-- <el-button type="text" size="small" @click="handleChangePassword">修改密码</el-button> -->
        </div>
        <el-avatar :size="50" :src="circleUrl" id="avator_button"></el-avatar>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
import { removeToken } from '@/utils/auth'
export default {
  name: 'Header',
  data () {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  methods: {
    handleLogout () {
      this.$store.commit('logout')
      removeToken()
      this.$router.push({
        name: 'Login',
        query: {
          redirect: this.$route.path
        }
      })
    },
    handleChangePassword () {
    },
    jumpToDashboard () {
      this.$router.push({
        name: 'Dashboard'
      })
    }
  }
}
</script>

<style lang="less">
.header {
  flex: 0 0 80px;
  display: flex;
  padding: 0 120px;
  justify-content: space-between;
  min-width: 800px;
  background: #444450;
  color: #FFFFFF;
  border-bottom: 1px solid #B3C0D1;
}

.logo {
  display: flex;
  height: 80px;
  font-size: 60px;
  justify-content: center;
  align-items: center;
  .el-icon-reading {
    cursor: pointer;
    &--selected {
      color: #0caffa;
    }
  }
}

.title {
  display: flex;
  height: 80px;
  font-size: 48px;
  font-weight: 700;
  justify-content: center;
  align-items: center;
}

.avator {
  display: flex;
  height: 80px;
  justify-content: center;
  align-items: center;
}

.avator-username {
  font-size: 20px;
}

#logo_button:hover {
  cursor: pointer;
}

#avator_button:hover {
  cursor: pointer;
}

</style>
