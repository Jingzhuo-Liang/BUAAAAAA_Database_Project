<template>
    <div>
        <el-row style="height: 840px;">
            <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
            <el-tooltip effect="dark" placement="right"
                        v-for="item in books.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                        :key="item.id">
                <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
                <p slot="content" style="font-size: 13px;margin-bottom: 6px">
                    <span>{{item.author}}</span> /
                    <span>{{item.date}}</span> /
                    <span>{{item.press}}</span>
                </p>
                <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
                <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                         bodyStyle="padding:10px" shadow="hover">
                    <div class="cover" @click="editBook(item)">
                        <img :src="item.cover" alt="封面">
                    </div>
                    <div class="info">
                        <div class="title">
                            <a href="">{{item.title}}</a>
                        </div>
                        <i class="el-icon-delete" @click="deleteBook(item.id)"></i>
                    </div>
                    <div class="author">{{item.author}}</div>
                </el-card>
            </el-tooltip>
            <edit-form @onSubmit="loadBooks()" ref="edit"></edit-form>
        </el-row>
        <el-row>
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="pagesize"
                    :total="books.length">
            </el-pagination>
        </el-row>
    </div>
</template>

<script>
    import EditForm from "@/components/library/EditForm";
    import SearchBar from "@/components/library/SearchBar";
    export default {
        name: 'Books',
        components: {EditForm,SearchBar},
        data () {
            return {
                /*
                cover: 'https://i.loli.net/2019/04/10/5cada7e73d601.jpg',
                title: '三体',
                author: '刘慈欣',
                date: '2019-05-05',
                press: '重庆出版社',
                abs: '文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……'
                */
                books:[],
                currentPage:1,
                pagesize:17,
            }
        },
        mounted:function() {
            this.loadBooks()
        },
        methods:{
            loadBooks() {
                var _this = this
                this.$axios.get('/books').then(resp=>{
                    if (resp && resp.status === 200) {
                        _this.books = resp.data;
                    }
                })
            },
            handleCurrentChange:function(currentPage) {
                this.currentPage = currentPage
                console.log(this.currentPage)
            },
            searchResult () {
                var _this = this
                console.log(this.$refs.searchBar.keywords)
                var url = '/search/' + this.$refs.searchBar.keywords;
                console.log(url)
                this.$axios
                    .get(url).then(resp => {
                    if (resp && resp.status === 200) {
                        _this.books = resp.data
                    }
                })
            },
            deleteBook(id) {
                this.$confirm('此操作将永久删除该书籍，是否继续','提示', {
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(()=>{
                    this.$axios.post('/delete',{id:id}).then(resp => {
                        if (resp && resp.status === 200) {
                            this.loadBooks()
                        }
                    })
                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消删除'
                    })
                })
            },
            editBook(item) {
                this.$refs.edit.dialogFormVisible = true
                this.$refs.edit.form = {
                    id:item.id,
                    cover:item.cover,
                    title:item.title,
                    author:item.author,
                    date:item.date,
                    press:item.press,
                    abs:item.abs,
                    category:{
                        id:item.category.id.toString(),
                        name:item.category.name
                    }
                }
            }
        }
    }
</script>

<style scoped>
    .cover {
        width: 115px;
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

    .title {
        font-size: 14px;
        text-align: left;
    }

    .author {
        color: #333;
        width: 102px;
        font-size: 13px;
        margin-bottom: 6px;
        text-align: left;
    }

    .abstract {
        display: block;
        line-height: 17px;
    }

    .el-icon-delete {
        cursor: pointer;
        float: right;
    }

    .switch {
        display: flex;
        position: absolute;
        left: 780px;
        top: 25px;
    }


    a {
        text-decoration: none;
    }

    a:link, a:visited, a:focus {
        color: #3377aa;
    }
</style>

