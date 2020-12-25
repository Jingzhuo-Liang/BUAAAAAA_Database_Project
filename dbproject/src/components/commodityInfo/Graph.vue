<template>
        <ve-line :data="chartData" :settings="chartSettings"></ve-line>
</template>

<script>
    export default {
        name: "Graph",
        data () {
            this.chartSettings = {
                xAxisType: 'time'
            }
            return {
                chartData: {
                    columns: ['日期', '价格',],
                    rows: [
                        //{ '日期': '2018-01-01', '访问用户': 1393, '下单用户': 1093, '下单率': 0.32 },
                    ]
                }
            }
        }
    }
</script>

<style scoped>

</style>