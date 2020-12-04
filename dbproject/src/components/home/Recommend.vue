<template>
    <div>
        <!--
        <el-carousel class = "recommend">
            <el-carousel-item v-for="item in populars" :key="item.id">
                <el-image style = "width: 100%;height: 100%"
                        :src="item.piclink" @click="getDetail(item)"
                        >
                </el-image>
            </el-carousel-item>
        </el-carousel>
        -->
        <el-carousel  class = "recommend" :interval="4000" type="card">
            <el-carousel-item v-for="item in populars" :key="item.id">
                <el-image style = "width: 100%;height: 100%"
                          :src="item.piclink" @click="getDetail(item)"
                >
                </el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    export default {
        name: "Recommend",
        data() {
            return {
                populars:[],
                time: new Date()
            }
        },
        mounted:function() {
          this.getPolulars();
        },
        methods :{
            getPolulars() {
                //var _this = this
                this.$axios.get('/popular').then(
                    resp=>{
                        if (resp && resp.data.code === 200) {
                            this.populars = resp.data.data;
                        }
                    }
                )
            },
            getDetail(item) {
                var _this = this
                console.log(item.url)
                _this.$store.commit('setInfo',item)
                this.$router.replace('/CommodityInfo')
            }
        }
    }
</script>

<style scoped>
    .recommend {
        height:600px;
        width: 100%;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>