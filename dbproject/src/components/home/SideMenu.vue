<template>
    <el-menu
            class="categories"
            default-active="0"
            @select="handleSelect"
            active-text-color="#1baeae">
        <el-menu-item index="0">
            <i class="el-icon-menu"></i>
            <span slot="title">全部</span>
        </el-menu-item>
        <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">手机</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">电脑</span>
        </el-menu-item>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">耳机</span>
        </el-menu-item>
        <el-menu-item index="4">
            <i class="el-icon-menu"></i>
            <span slot="title">其他</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "SideMenu",
        data() {
            return {
                cid:''
            }
        },
        methods:{
            handleSelect(key, keyPath) {
                this.cid = key
                // 定义触发事件，可触发父组件中的方法
                /*
                触发handleSelect方法，将el-menu-item 中的index赋值给cid即类别码，并触发父组件中的函数indexSelect，
                父组件通过ref获得子组件中的cid并执行listByCategory函数，并发送请求，
                后端执行查询代码，返回数据，再通过 refs 修改 Books组件的 data 以动态渲染页面
                 */
                this.$emit('categorySelect')
                keyPath
            }
        }
    }
</script>

<style scoped>
    .categories{
        position: fixed;
        margin-left: 50%;
        left: -670px;
        top: 250px;
        width: 150px;
    }
</style>