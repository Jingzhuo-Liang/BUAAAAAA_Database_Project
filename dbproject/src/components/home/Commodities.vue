<template>
    <div>
        <div style="margin-top: 400px;margin-bottom: 30px;text-align: left">
            <span style="font-size: 25px;font-weight: bold">为您推荐</span>
            <el-divider></el-divider>
        </div>
        <div style="height: 1000px;width: 100%">
            <el-tooltip effect="dark" placement="right"
                        v-for="(item,index) in commodities.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                        :key="item.id">
                <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.comname}}</p>
                <p slot="content" style="font-size: 13px;margin-bottom: 6px"><span>{{item.gift}}</span></p>
                <p slot="content" style="width: 300px" class="abstract">{{item.storename}}</p>
                <el-card v-if = "index % 5 != 4" class="card" bodyStyle="padding:10px" shadow="hover">
                    <div class="pic" v-on:click="getDetail(item)">
                        <img :src="item.piclink" alt="商品图片">
                    </div>
                    <div class="info">
                        <div class="comname">
                            {{item.comname}}
                        </div>
                        <div class="price">
                            {{item.newestprice}}
                        </div>
                    </div>
                </el-card>
                <el-card v-else class="card1" bodyStyle="padding:10px" shadow="hover">
                    <div class="pic" @click="getDetail(item)"> <!--修改click-->
                        <img :src="item.piclink" alt="商品图片">
                    </div>
                    <div class="info">
                        <div class="comname">
                            {{item.comname}}
                        </div>
                        <div class="price">
                            {{item.newestprice}}
                        </div>
                    </div>
                </el-card>
            </el-tooltip>
        </div>
        <div style="height: 50px;width: 100px;margin: 50px auto auto;">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="pagesize"
                    :total="commodities.length">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Commodities",
        data () {
            return {
                commodities:[],
                currentPage:1,
                pagesize:20,
            }
        },
        mounted:function() {
            this.loadCommodities()
        },
        methods: {
            searchCommodities(keywords) {
                var url = '/search/' + keywords;
                console.log(url)
                this.$axios
                    .get(url).then(resp => {
                    if (resp && resp.status === 200) {
                        this.commodities = resp.data.data
                        console.log(this.commodities)
                    }
                    else {
                        console.log("搜索失败")
                    }
                })

            },
            loadCommodities() {
                this.$axios.get('/commodities').then(resp => {
                    if (resp && resp.status === 200) {
                        this.commodities = resp.data.data;
                        //console.log(_this.commodities.length)
                    }
                })
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage
                //console.log(this.currentPage)
            },
            getDetail(item) {
                this.$store.commit('setInfo',item)
                this.$router.replace('/CommodityInfo')
            }
        }
    }
</script>

<style scoped>
    .card {
        width: 19%;
        margin-bottom: 20px;
        height: 233px;
        float: left;
        margin-right: 1%;
    }
    .card1 {
        width: 19%;
        margin-bottom: 20px;
        height: 233px;
        float: right;
    }
    .pic {
        width: 100%;
        height: 172px;
        margin-bottom: 7px;
        overflow: hidden;
        cursor: pointer;
    }
    img {
        width: 115px;
        height: 172px;
        /*margin: 0 auto;*/
    }
    .comname {
        color: #333333;
        font-size: 16px;
        width: 100%;   /*一定要设置宽度，或者元素内含的百分比*/
        overflow:hidden; /*溢出的部分隐藏*/
        white-space: nowrap; /*文本不换行*/
        text-overflow:ellipsis;/*ellipsis:文本溢出显示省略号（...）；clip：不显示省略标记（...），而是简单的裁切*/
    }
    .price{
        color:  #1baeae;
        font-size: 16px;
        width: 100%;
        text-align: center;
    }
    .abstract {
        display: block;
        line-height: 17px;
    }
    a {
        text-decoration: none;
    }
</style>