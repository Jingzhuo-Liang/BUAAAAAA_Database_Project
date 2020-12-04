<template>
    <div>
        <div style="width: 70%;margin: auto">
            <el-container style = "width: 100%;margin: auto">
                <el-header>
                    <Header @onSearch='onSearch' ref='headerArea' style="width: 100%"></Header>
                    <div style="width: 100%;margin-top: 100px">
                        <recommend class = "recommend-area"></recommend>
                    </div>
                </el-header>
                <el-main>
                    <side-menu @categorySelect='listByCategory' ref='sideMenuArea' style=""></side-menu>
                    <commodities class="commodities-area" ref="commoditiesArea"></commodities>
                </el-main>
            </el-container>
        </div>
        <div>
            <Footer></Footer>
        </div>
    </div>
</template>

<script>
    import Header from "@/components/home/Header";
    import Recommend from "@/components/home/Recommend";
    import SideMenu from "@/components/home/SideMenu";
    import Commodities from "@/components/home/Commodities";
    import Footer from "@/components/home/Footer";
    export default {
        name: "Mainpage",
        components:{Footer, SideMenu, Recommend, Header,Commodities},
        methods: {
            onSearch() {
               this.$refs.commoditiesArea.searchCommodities(this.$refs.headerArea.keywords)
            },
            listByCategory() {
                var cid = this.$refs.sideMenuArea.cid
                if (cid === '0') {
                    this.$refs.commoditiesArea.loadCommodities()
                    return
                }
                var url = 'category/'+ cid
                this.$axios.get(url).then(resp=>{
                    if (resp && resp.status === 200) {
                        this.$refs.commoditiesArea.commodities = resp.data.data
                    }
                    else {
                        console.log("加载失败")
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .recommend-area{
        margin-top: 100px;
        width: 100%;
    }
    .commodities-area {
        width: 100%;
        margin-top: 300px;
        margin-left: auto;
        margin-right: auto;
    }
</style>