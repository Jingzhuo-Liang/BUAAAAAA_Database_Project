<template>
    <div>
    <el-container style = "width: 70%;margin: auto">
        <el-header>
            <Header></Header>
        </el-header>
        <el-main>
            <div style="margin-top: 50px;width: 100%">
                <div style="float: left;width: 50%;height: 500px">
                    <el-image style="width: 100%;height: 100%"
                            :src = "commodity.piclink"></el-image>
                </div>
                <div style="float: left;width: 50%;height: 500px;background-color: #f8f8f8">
                    <div style="font-size: 18px;font-weight: bold;text-align: left;width: 100%">
                        <span >{{commodity.comname}}</span>
                    </div >
                    <div style="font-size: 16px;text-align: left;margin-top: 20px;color: #b0b0b0;width: 100%">
                        <span>附赠：{{commodity.gift}}</span>
                    </div>
                    <div style="font-size: 16px;text-align: left;margin-top: 20px;width: 100%">
                        <span>店铺：{{commodity.storename}}</span>
                    </div>
                    <div style="color: #1baeae;font-weight: bolder;font-size: 20px;float: right;width: 100%;height: 200px">
                        <span style="float: right">{{commodity.newestprice}}元</span>
                    </div>
                    <div style="width: 100%;">
                        <el-button class="button" v-on:click="collect" icon="el-icon-star-off" >收藏</el-button>
                        <el-button class="button" v-on:click="goPage(commodity.url)">直达链接</el-button>
                    </div>
                    <div class="tb-extra ml20" >
                        <dl style="width: 100%">
                            <dt style="float: left;">承诺</dt>
                            <dd style="float: left"
                                ><a class="cont" title="满足7天无理由退换货申请的前提下，包邮商品需要买家承担退货邮费，非包邮商品需要买家承担发货和退货邮费。" href="#"
                                   target="_blank"><img src="../../assets/7d.jpg">7天无理由</a></dd>
                        </dl>
                        <dl style="width: 100%;">
                            <dt style="float: left">支付</dt>
                            <dd style="float: left;">
                                <a class="cont" href="##" target="_blank"><img src="../../assets/hua.png">蚂蚁花呗  </a>
                                <a class="cont" href="##" target="_blank"><img src="../../assets/card.png">信用卡支付  </a>
                                <a class="cont" href="##" target="_blank"><img src="../../assets/ji.png">集分宝  </a>
                            </dd>
                        </dl>
                        <dl style="width: 100%;">
                            <dt style="float: left">支持</dt>
                            <dd style="float: left">折旧变现，买新更省钱。<a class="cont"  href="##">详情</a></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </el-main>
        <Graph ref="graphArea"></Graph>
        <Message class="message-area" ref="messageArea" style="width: 100%"></Message>
    </el-container>
    <div style="margin-top: 50px">
        <Footer></Footer>
    </div>
    </div>
</template>

<script>
    import Header from "@/components/home/Header";
    import Message from "@/components/commodityInfo/Message";
    import Graph from "@/components/commodityInfo/Graph";
    import Footer from "@/components/home/Footer";
    export default {
        name: "CommodityInfo",
        components:{Footer, Graph, Message, Header},
        data() {
            return {
                commodity:'',
            }
        },
        mounted: function () {
            this.commodity = this.$store.state.commodity
            if (this.commodity === '') {
                this.$router.replace('/mainpage')
            }
        },
        created() {
            this.getParams()
            //获取留言和回复
            this.getLeaveMessage()
            this.getPrice()
            this.addHistory()
            //this.getReplyMessage()
        },
        methods:{
            addHistory() {
                if (this.commodity === '') {
                    return
                }
                this.$axios.post("/addHistory" ,{
                    comid: this.commodity.id,
                    userid: this.$store.state.user.id,
                    time: this.getMyTime()
                }).then(resp => {
                    if (resp && resp.data.code === 200) {
                        //
                    }
                    else {
                        //
                    }
                })
            },
            collect() {
                if (this.commodity === '') {
                    return
                }
                this.$axios.post("/addCollection" ,{
                    comid: this.commodity.id,
                    userid: this.$store.state.user.id,
                    time: this.getMyTime()
                }).then(resp => {
                    if (resp && resp.data.code === 200) {
                        alert("收藏成功")
                    }
                    else {
                        alert("收藏失败")
                    }
                })
            },
            getPrice() {
                if (this.commodity === '') {
                    return
                }
                this.$axios.post("/price/" + this.commodity.id).then(
                    resp=>{
                        if (resp && resp.status === 200) {
                            var i;
                            for (i = 0;i < resp.data.data.length;i++) {
                                this.$refs.graphArea.chartData.rows.push({'日期':resp.data.data[i].date,'价格':resp.data.data[i].price});
                            }
                            //this.$refs.messageArea.leaveMessage = resp.data.data
                        }
                    }
                )
            },
            getParams() {
                this.commodity = this.$store.state.commodity
            },
            getLeaveMessage() {
                this.commodity = this.$store.state.commodity
                this.$axios.post("/leavemessage/" + this.commodity.id).then(
                    resp=>{
                        if (resp && resp.status === 200) {
                            this.$refs.messageArea.leaveMessage = resp.data.data
                            this.getReplyMessage()
                        }
                    }
                )
            },
            async getReplyMessage() {
                var i
                var _this = this
                for (i=0;i < _this.$refs.messageArea.leaveMessage.length;i++) {
                    var id = _this.$refs.messageArea.leaveMessage[i].id
                    await this.$axios.post("/replymessage/" +  id).then(
                        resp=>{
                            if (resp && resp.status === 200) {
                                //this.$refs.messageArea.replyMessage.push({id:resp.data.data})
                                    //console.log(id)
                                    this.$refs.messageArea.replyMessage[id] = resp.data.data
                            }
                        }
                    )
                }
            },
            goPage(url) {
                //window.location.href = url
                window.open(url,'_blank')
            },
        }
    }
</script>

<style scoped>
    .ml20 {
        margin-left: 20px;
    }
    .tb-extra {
        font-size: 13px;
        margin-top: 20px;
        height: 100px;
        width: 55%;
        float: left;
    }
    .button{
        float:right;
        margin-right:50px;
        background-color: #1baeae;
        color: white;
        width: 120px;
    }
    .icons{
        text-align: left;
        font-size: 14px;
        color: #cac6c6;
        height: 100px;
        float: bottom;
        background-color: #333333;
    }
    .cont{
        text-decoration: none;
        color: #b0b0b0;
    }
    dl{
        width:100%;
        text-align:center;
        float:left;
        margin:5px;
    }
    dt{

    }
    dd{

    }
</style>