<template>
  <div class="sider-bar">
    <div class="sider-bar-list">
      <div @click="handleJump(item.path)" :class="{'sider-bar-list-item':true, 'sider-bar-list-item-selected': item.selected}" v-for="item in siderBarList" :key="item.id">
        <i :class="item.icon" />
        <span>{{item.label}}</span>
      </div>
    </div>
    <div class="sider-bar-footer">
      <el-tooltip content="用户手册" placement="top" effect="light">
        <i class="el-icon-question" @click="handleJump('/help')"/>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SiderBar',
  data () {
    return {
      siderBarList: [
        {
          name: 'PIPUBLIC',
          label: '政策解读列表展示',
          path: '/pi/public',
          icon: 'el-icon-document-checked',
          selected: false
        }
        // {
        //   name: 'PIDRAFT',
        //   label: '政策解读草稿列表',
        //   path: '/pi/draft',
        //   icon: 'el-icon-edit-outline',
        //   selected: false
        // },
        // {
        //   name: 'PIABOLISH',
        //   label: '已废止政策解读列表',
        //   path: '/pi/abolish',
        //   icon: 'el-icon-document-delete',
        //   selected: false
        // },
        // {
        //   name: 'PICreate',
        //   label: '新增政策解读',
        //   path: '/pi/create',
        //   icon: 'el-icon-document-add',
        //   selected: false
        // }
      ]
    }
  },
  watch: {
    '$route' () {
      this.getSelectedItem()
    }
  },
  created () {
    this.getSelectedItem()
  },
  methods: {
    getSelectedItem () {
      this.siderBarList.forEach(item => {
        item.selected = item.path === this.$route.fullPath
      })
    },
    handleJump (path) {
      this.$router.push(path)
    }
  }
}
</script>

<style lang="less">
.sider-bar {
  height: 100%;
  background-color: #444450;
  flex: 0 0 300px;
  color: #FFFFFF;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  justify-content: space-between;
  &-list-item {
    padding: 10px;
    &:hover {
      background-color: #333333;
      cursor: pointer;
    }
  }
  &-footer {
    padding: 50px;
    font-size: 24px;
    .el-icon-question {
      cursor: pointer;
    }
  }
  &-list-item-selected {
    color: #0caffa;
  }
}
</style>
