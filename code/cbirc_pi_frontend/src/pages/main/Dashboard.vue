<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <Chart :chartData="barData" type="bar" name="词频统计Top10" ref="bar"/>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <Chart :chartData="wordCloudData" type="wordCloud" name="词云" ref="wordCloud"/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Chart from './components/Chart.vue'
export default {
  components: { Chart },
  data () {
    return {
      wordCloudData: [],
      barData: []
    }
  },
  methods: {
    getRankNWordFrequency () {
      this.$store.dispatch('getRankNWordFrequency', 10).then(res => {
        this.barData = res.data.map(item => {
          return {
            name: item.keyWord,
            value: item.frequency
          }
        })
        this.$nextTick(() => {
          this.$refs.bar.draw()
        })
      })
    },
    getAllWordFrequency () {
      this.$store.dispatch('getAllWordFrequency').then(res => {
        this.wordCloudData = res.data.map(item => {
          return {
            name: item.keyWord,
            value: item.frequency,
            textStyle: {
              color: this.generateRandomColor()
            }
          }
        })
        this.$nextTick(() => {
          this.$refs.wordCloud.draw()
        })
      })
    },
    generateRandomColor () {
      let r = Math.floor(Math.random() * 256).toString(16)
      if (r.length === 1) r = '0' + r
      let g = Math.floor(Math.random() * 256).toString(16)
      if (g.length === 1) g = '0' + g
      let b = Math.floor(Math.random() * 256).toString(16)
      if (b.length === 1) b = '0' + b
      return '#' + r + g + b
    }
  },
  created () {
    this.getAllWordFrequency()
    this.getRankNWordFrequency()
  }
}
</script>

<style lang="less">
.dashboard-container {
  padding: 20px;
  width: 100%;
  background-color: #E9EEF3;
  overflow: auto;
  .el-row {
    height: 400px;
    margin-bottom: 20px;
    .el-col {
      height: 100%;
    }
  }
}
</style>
