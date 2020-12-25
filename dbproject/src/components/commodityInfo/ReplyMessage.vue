<template>
    <div>
        <el-dialog
                title="回复留言"
                :visible.sync="dialogFormVisible"
                @close="clear">
            <el-form v-model="replyMessage" style="text-align: left" ref="dataForm">
                <el-form-item label="回复留言" :label-width="formLabelWidth" prop="message">
                    <el-input v-model="replyMessage.message" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ReplyMessage",
        data() {
            return {
                dialogFormVisible:false,
                replyMessage: {
                    id:'',
                    userid:'',
                    leavemessageid:'',
                    time:'',
                    message:''
                },
                formLabelWidth: '120px'
            }
        },
        methods: {
            clear() {
                this.replyMessage = {
                    id:'',
                    userid:'',
                    leavemessageid:'',
                    time:'',
                    message:''
                }
            },
            onSubmit() {
                if (undefined === this.replyMessage.message) {
                    this.$alert('留言不能为空', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                }
                this.$axios
                    .post('/addReplyMessage', {
                        id: '0',
                        userid:this.replyMessage.userid,
                        leavemessageid:this.replyMessage.leavemessageid,
                        time: this.getMyTime(),
                        message:this.replyMessage.message
                    }).then(resp => {
                    if (resp && resp.status === 200) {
                        this.dialogFormVisible = false
                        this.$emit('onSubmit1')
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .el-icon-circle-plus-outline {
        margin: 50px 0 0 20px;
        font-size: 100px;
        float: left;
        cursor: pointer;
    }
</style>