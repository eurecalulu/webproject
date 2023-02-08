<template>
  <div class="search-header">
    <div class="search-header-row">
    <div class="search-header-item"><el-input v-model="doc_title" placeholder="法规标题"></el-input></div>
    <div class="search-header-item"><el-input v-model="doc_identifier" placeholder="法规文号"></el-input></div>
    <div class="search-header-item"><el-input v-model="doc_depart" placeholder="发文机构"></el-input></div>
    </div>
    <div class="search-header-row">
    <div class="search-header-item--bigger">
      <el-date-picker
        v-model="dateRange"
        type="daterange"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        value-format="yyyy-MM-dd">
      </el-date-picker>
    </div>
    <div class="search-header-item">
      <el-button type="primary" @click="onSubmit">查询</el-button>
      <el-button type="primary" @click="clear">清空</el-button>
    </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchHeader',
  data () {
    return {
      doc_title: '',
      doc_identifier: '',
      doc_depart: '',
      dateRange: ['', '']
    }
  },
  watch: {
    '$route' () {
      this.doc_title = ''
      this.doc_identifier = ''
      this.doc_depart = ''
      this.dateRange = ['', '']
    }
  },
  methods: {
    onSubmit () {
      this.$emit('search', {
        docTitle: this.doc_title,
        docIdentifier: this.doc_identifier,
        docDepart: this.doc_depart,
        startTime: this.dateRange[0],
        endTime: this.dateRange[1]
      })
    },
    clear () {
      this.doc_title = ''
      this.doc_identifier = ''
      this.doc_depart = ''
      this.dateRange[0] = ''
      this.dateRange[1] = ''
      this.onSubmit()
    }
  }
}
</script>

<style lang="less" scoped>
.search-header {
  background-color: #B3C0D1;
  color: #333;
  width: 100%;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  flex-flow: column;
  height: 100%;
  &-row {
    width: 70%;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  &-item {
    width: 30%;
    &--bigger {
      width: 65%;
      .el-date-editor {
        width: 100%;
      }
    }
  }
}
</style>
