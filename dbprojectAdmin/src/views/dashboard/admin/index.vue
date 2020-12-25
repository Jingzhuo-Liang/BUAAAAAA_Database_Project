<template>
  <div class="dashboard-editor-container">
    <panel-group :vv-today="vvToday" @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" :date-range="dateRange" />
    </el-row>

  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import { fetchList } from '@/api/visit'

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart
  },
  data() {
    return {
      list: null,
      period: 7,
      // dateRange: this.getRange(new Date(), 7)
      dateRange: [],
      vvToday: '',
      AllData: {
        visitView: { expectedData: [2, 3, 2, 1, 3, 2, 1], actualData: [] },
        messages: {},
        purchases: {},
        shoppings: {}
      }
    }
  },
  created() {
    this.getList()
    this.lineChartData = this.AllData['visitView']
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = this.AllData[type]
      console.log('type' + type)
      console.log('see' + this.AllData.visitView)
    },
    formatTime(mytime) {
      const year = mytime.getFullYear()
      let month = mytime.getMonth() + 1
      let day = mytime.getDate()
      if (month < 10) {
        month = '0' + month
      }
      if (day < 10) {
        day = '0' + day
      }
      return year + '-' + month + '-' + day
    },
    getRange(dateNow, period) {
      const oneDay = 24 * 60 * 60 * 1000
      const list = []
      let i
      for (i = period - 1; i >= 0; i--) {
        const listday = new Date(dateNow.getTime() - i * oneDay)
        list.push(this.formatTime(listday))
      }
      return list
    },
    getList() {
      fetchList(this.period).then(response => {
        console.log(this)
        this.list = response.data
        console.log('list' + JSON.stringify(this.list))
        const myDate = new Date()
        this.dateRange = this.getRange(myDate, 7)
        console.log('Total ' + this.dateRange)
        let i
        const vvData = []
        const daylist = this.list.map(x => x['day'])
        for (i = 0; i < this.dateRange.length; i++) {
          if (daylist.indexOf(this.dateRange[i]) === -1) {
            vvData.push(0)
          } else {
            vvData.push(this.list[daylist.indexOf(this.dateRange[i])]['cntday'])
          }
        }
        console.log('data' + vvData)
        this.AllData.visitView.actualData = vvData
        this.vvToday = vvData[vvData.length - 1]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
