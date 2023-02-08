<template>
  <div class="container">
    <Header></Header>
    <div class="main">
      <SiderBar></SiderBar>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import SiderBar from '../components/SiderBar.vue'
import Header from '../components/Header.vue'
export default {
  components: { SiderBar, Header },
  name: 'Container',
  data () {
    return {
    }
  }
}
</script>

<style lang="less">
.container {
  display: flex;
  height: 100%;
  width: 100%;
  overflow: auto;
  flex-direction: column;
}

.main {
  display: flex;
  height: 100%;
  width: 100%;
  overflow: auto;
}
</style>
