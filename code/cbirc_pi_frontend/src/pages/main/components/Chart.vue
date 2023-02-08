<template>
  <div class="chart-container">
    <div class="chart-header">{{ name }}</div>
    <div class="chart-body" ref="myChart">
      <div class="chart-body-loading" v-if="loading">正在加载中...</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['chartData', 'name', 'type'],
  data () {
    return {
      loading: true
    }
  },
  methods: {
    draw () {
      this.loading = false
      let myChart = this.$echarts.init(this.$refs.myChart)
      myChart.setOption(this.getOption())
    },
    getOption () {
      let option = {
        tooltip: {
          show: true
        }
      }
      if (this.type === 'bar') {
        option.xAxis = {
          type: 'category',
          data: this.chartData.map(item => {
            return item.name
          })
        }
        option.yAxis = {
          type: 'value'
        }
      }
      let series = [{}]
      if (this.type === 'wordCloud') {
        series[0] = {
          type: this.type,
          sizeRange: [12, 55],
          rotationRange: [0, 0],
          textPadding: 0,
          autoSize: {
            enable: true,
            minSize: 10
          },
          data: this.chartData
        }
      } else if (this.type === 'bar') {
        series[0] = {
          type: 'bar',
          data: this.chartData
        }
      }
      option.series = series
      return option
    }
  }
}
</script>

<style lang="less">
.chart-container {
  background-color: #fff;
  width: 100%;
  height: 100%;
  border: 1px solid #BBBBBB;
  box-sizing: border-box;
  &:hover {
    box-shadow: 0 0 5px #666666;
  }
  .chart-header {
    border-bottom: 1px solid #BBBBBB;
    text-align: left;
    padding: 10px;
    font-weight: 700;
    height: 40px;
    box-sizing: border-box;
  }
  .chart-body {
    width: 100%;
    height: calc(100% - 40px);
    .chart-body-loading {
      margin-top: 50%;
    }
  }
}
</style>
