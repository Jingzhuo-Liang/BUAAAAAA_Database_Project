<template>
    <div>
    <el-container style = "width: 100%;margin: auto">
        <el-header >
            <div style="width: 100%">
                <div style="width: 100%">
                    <Header></Header>
                </div>
                <div class="dc">
                    <div class="title"><span style="margin-left: 10px;margin-bottom: 30px">商品详情</span></div>
                    <div class="fr">
                        <li><a>用户评价</a></li>
                        <li><a>|</a></li>
                        <li><a>价格走势</a></li>
                        <li><a>|</a></li>
                        <li><a>概述</a></li>
                    </div>
                </div>
            </div>
        </el-header>
        <el-main style="width: 70%;margin: auto">
            <div style="margin-top: 120px;width: 100%">
                <div style="float: left;width: 50%;height: 500px">
                    <el-image style="width: 100%;height: 100%"
                            :src = "commodity.piclink"></el-image>
                </div>
                <div class="infoField">
                    <div class="comnameField">
                        <span >{{commodity.comname}}</span>
                    </div >
                    <div class="giftField">
                        <span>附赠：{{commodity.gift}}</span>
                    </div>
                    <div class="storeField">
                        <span>店铺：{{commodity.storename}}</span>
                    </div>
                    <div class="priceField">
                        <div class="priceItem" :style="conHeight">
                            <div class="discountItem" >
                                <span class="discountLabel">原价</span>
                                <span class="discount"><del>{{commodity.newestprice}}</del></span>
                            </div>
                        </div>
                        <div class="priceItem" :style="conHeight">
                            <el-tooltip class="item" effect="dark"  placement="top-start">
                                <p v-for="(detail,index) in this.platformDiscountDetail" :key="index" slot="content"><span>{{detail}}</span></p>
                                <div class="discountItem">
                                    <span class="discountLabel">平台优惠</span>
                                    <span class="discount">-{{this.platformDiscountSum}}</span>
                                </div>
                            </el-tooltip>
                        </div>
                        <div class="priceItem" v-show="isPlatformMember" :style="conHeight">
                            <el-tooltip class="item" effect="dark" :content="this.platformMemberDiscountDetail" placement="top-start">
                                <p v-for="(detail,index) in this.platformMemberDiscountDetail" :key="index" slot="content"><span>{{detail}}</span></p>
                                <div class="discountItem">
                                    <span class="discountLabel">平台会员优惠</span>
                                    <span class="discount">-{{this.platformMemberDiscountSum}}</span>
                                </div>
                            </el-tooltip>
                        </div>
                        <div class="priceItem" :style="conHeight">
                            <el-tooltip class="item" effect="dark"  placement="top-start">
                                <p v-for="(detail,index) in this.commodityDiscountDetail" :key="index" slot="content"><span>{{detail}}</span></p>
                                <div class="discountItem">
                                    <span class="discountLabel">商品优惠</span>
                                    <span class="discount">-{{this.commodityDiscountSum}}</span>
                                </div>
                            </el-tooltip>
                        </div>
                        <div class="priceItem" v-show="this.$store.state.user.isVip === '1'" :style="conHeight">
                            <el-tooltip class="item" effect="dark" :content="this.vipDiscountDetail" placement="top-start">
                                <div class="discountItem">
                                    <span class="discountLabel">VIP优惠</span>
                                    <span class="discount">-{{this.vipDiscountSum}}</span>
                                </div>
                            </el-tooltip>
                        </div>
                        <div class="priceItem" v-show="this.$store.state.user.isStudent === '1'" :style="conHeight">
                            <el-tooltip class="item" effect="dark" :content="this.studentDiscountDetail" placement="top-start">
                                <div class="discountItem">
                                    <span class="discountLabel">学生优惠</span>
                                    <span class="discount">-{{this.studentDiscountSum}}</span>
                                </div>
                            </el-tooltip>
                        </div>
                        <div class="priceItem" :style="conHeight">
                            <span>￥{{this.finalPrice}}</span>
                        </div>
                    </div>
                    <div style="width: 100%;">
                        <el-button class="button" v-on:click="collect" icon="el-icon-star-off" type="detail" >收藏</el-button>
                        <el-button class="button" v-on:click="goPage(commodity.url)" type="detail">直达链接</el-button>
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
        <div style="width: 70%;margin: auto">
            <Graph ref="graphArea" ></Graph>
            <Message class="message-area" ref="messageArea" style="width: 100%"></Message>
        </div>
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
                conHeight: {
                   height:''
                },
                commodity:'',
                commodityDiscount:[],
                platformDiscount:[],
                vipDiscountSum:'',
                platformDiscountSum:'',
                platformMemberDiscountSum:'',
                commodityDiscountSum:'',
                studentDiscountSum:'',
                finalPrice:'',
                isPlatformMember:false,
                divNums : 4,
                commodityDiscountDetail:[],
                platformDiscountDetail:[],
                platformMemberDiscountDetail:[],
                studentDiscountDetail:'',
                vipDiscountDetail:''
            }
        },
        mounted: function () {
            this.commodity = this.$store.state.commodity
            if (this.commodity === '') {
                this.$router.replace('/mainpage')
            }
        },
        async created() {
            this.getParams()
            if (this.commodity === '') {
                return
            }
            //获取留言和回复
            this.getLeaveMessage()
            this.getPrice()
            this.addHistory()
            await this.getCommodityDiscount()
            await this.getPlatformDiscount()
            this.parsePrice()
        },
        methods:{
            async parsePrice() {
                var newPrice = this.commodity.newestprice
                var platformDiscountSum = 0
                var platformMemberDiscountSum = 0
                var commodityDiscountSum = 0
                var vipDiscountSum = 100
                var studentDiscountSum = 200
                var i
                for (i = 0;i < this.commodityDiscount.length;i++) {
                    if (this.commodity.newestprice < this.commodityDiscount[i].limit) { //不够满减
                        continue;
                    }
                    commodityDiscountSum += this.commodityDiscount[i].discount
                    this.commodityDiscountDetail.push( "满" + this.commodityDiscount[i].limit + "元立减" +
                        this.commodityDiscount[i].discount + "元 截止日期：" + this.commodityDiscount[i].time)
                }
                this.isPlatformMember = this.$store.state.user.isMember[this.commodity.platform - 1] === '1';
                for (i = 0;i < this.platformDiscount.length;i++) {
                    if (this.commodity.newestprice < this.platformDiscount[i].limit) { //不够满减
                        continue;
                    }
                    if (this.platformDiscount[i].isMember === 1) { // 该满减针对会员
                        if (this.isPlatformMember === true) { //该用户为会员
                            platformMemberDiscountSum += this.platformDiscount[i].discount;
                            this.platformMemberDiscountDetail.push( "满" + this.platformDiscount[i].limit + "元立减" +
                                this.platformDiscount[i].discount + "元 截止日期：" + this.platformDiscount[i].time)
                        }
                    }
                    else {
                        platformDiscountSum += this.platformDiscount[i].discount;
                        this.platformDiscountDetail .push( "满" + this.platformDiscount[i].limit + "元立减" +
                            this.platformDiscount[i].discount + "元 截止日期：" + this.platformDiscount[i].time)
                    }
                }
                if (this.isPlatformMember === true) {
                    newPrice -= platformMemberDiscountSum
                    this.divNums++;
                }
                if (this.$store.state.user.isVip === '1') {
                    newPrice -= vipDiscountSum
                    this.divNums++;
                    this.vipDiscountDetail = "VIP 立减"  + vipDiscountSum + "元"
                }
                if (this.$store.state.user.isStudent === '1') {
                    newPrice -= studentDiscountSum
                    this.divNums++;
                    this.studentDiscountDetail = "学生优惠" + studentDiscountSum + "元"
                }
                newPrice -= platformDiscountSum
                newPrice -= commodityDiscountSum
                this.finalPrice = (newPrice >= 0)?newPrice:0
                this.platformDiscountSum = platformDiscountSum
                this.platformMemberDiscountSum = platformMemberDiscountSum
                this.commodityDiscountSum = commodityDiscountSum
                this.vipDiscountSum = vipDiscountSum
                this.studentDiscountSum = studentDiscountSum
                this.conHeight.height = 200 / this.divNums + 'px'
            },
            async getCommodityDiscount() {
                if (this.commodity === '') {
                    return
                }
                await this.$axios.post("/getCommodityDiscount/" + this.commodity.id).then(
                    resp => {
                    if (resp && resp.data.code === 200) {
                        this.commodityDiscount = resp.data.data;
                    }
                    else {
                        console.log("load commodityDiscount failed")
                    }
                })
            },
            async getPlatformDiscount() {
                if (this.commodity === '') {
                    return
                }
                await this.$axios.post("/getPlatformDiscount/" + this.commodity.platform).then(
                    resp => {
                    if (resp && resp.data.code === 200) {
                        this.platformDiscount = resp.data.data;
                    }
                    else {
                        console.log("load PlatformDiscount failed")
                    }
                })
            },
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
    .fr {
        width: 50%;
        float: right;
        list-style-type: none;
        margin-top: 8px;
    }
    .title {
        width: 300px;
        height: 38px;
        line-height: 58px;
        font-size: 22px;
        font-weight: bold;
        float: left;
        text-align: left;
        margin-top: -8px;
    }
    .dc {
        width: 70%;
        margin: auto;
        margin-top: 100px;
        height: 38px;
        background: #f0f0f0;
    }
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
    li{
        width: auto;
        margin: 0 0 0 0;
        margin-left: 10px;
        float: right;
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
    .storeField {
        font-size: 16px;
        text-align: left;
        margin-top: 20px;
        width: 100%;
    }
    .giftField {
        font-size: 16px;
        text-align: left;
        margin-top: 20px;
        color: #b0b0b0;
        width: 100%;
    }
    .comnameField {
        font-size: 18px;
        font-weight: bold;
        text-align: left;
        width: 100%;
    }
    .infoField {
        float: left;
        width: 50%;
        height: 500px;
        background-color: #f8f8f8;
    }
    .priceField {
        margin-top: 20px;
        color: #1baeae;
        font-weight: bolder;
        font-size: 20px;
        float: right;
        width: 100%;
        height: 200px;
    }
    .priceItem{
        float: right;
        width: 100%;
        text-align: right;
    }
    .discountItem {
        margin: auto auto auto auto;
        width: 100%;
        float: right;
    }
    .discountLabel {
        color: #cac6c6;
        font-weight: normal;
        font-size: 16px;
        margin-right: 40px;
        float: left;

    }
    .discount {
        color: #1baeae;
        font-weight: normal;
        font-size: 16px;
    }
    .el-button--detail:focus,
    .el-button--detail:hover {
        background: #48D1CC;
        border-color: #48D1CC;
        color: #fff;
    }

    .el-button--detail {
        color: #FFF;
        background-color: #20B2AA;
        border-color: #20B2AA;
    }
</style>