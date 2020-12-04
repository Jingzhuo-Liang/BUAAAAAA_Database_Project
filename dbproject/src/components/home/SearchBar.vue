<template>
    <div >
        <el-input
                @keyup.enter.native="searchClick"
                placeholder="搜索商品..."
                prefix-icon="el-icon-search"
                size="small"
                style="width: 200px"
                v-model="keywords">
        </el-input>
        <el-button size="small" type="primary" icon="el-icon-search" @click="searchClick">搜索</el-button>
    </div>
</template>

<script>
    export default {
        name: "SearchBar",
        data () {
            return {
                keywords: '',
                commodities: [],
                cardLoading: []
            }
        },
        methods: {
            searchClick () {
                this.$emit('onSearch')
            }
        }
    }
</script>

<style scoped>

</style>