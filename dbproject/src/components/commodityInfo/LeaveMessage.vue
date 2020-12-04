<template>
    <div>
        <el-dialog
                title="添加留言"
                :visible.sync="dialogFormVisible"
                @close="clear">
            <el-form v-model="leaveMessage" style="text-align: left" ref="dataForm">
                <el-form-item label="留言" :label-width="formLabelWidth">
                    <el-input v-model="leaveMessage.message" autocomplete="off"></el-input>
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
        name: "LeaveMessage",
        data() {
            return {
                dialogFormVisible:false,
                leaveMessage: {
                    id:'',
                    userid:'',
                    comid:'',
                    date:'',
                    message:''
                },
                formLabelWidth:'120px'
            }
        },
        methods: {
            clear() {
                this.leaveMessage = {
                    id:'',
                    userid:'',
                    comid:'',
                    time:'',
                    message:''
                }
            },
            onSubmit() {
                if (undefined === this.leaveMessage.message) {
                    this.$alert('留言不能为空', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                }
                this.$axios
                    .post('/addLeaveMessage', {
                        id: '0',
                        userid:this.leaveMessage.userid,
                        comid:this.leaveMessage.comid,
                        time: this.getMyTime(),
                        message:this.leaveMessage.message
                    }).then(resp => {
                    if (resp && resp.status === 200) {
                        this.dialogFormVisible = false
                        this.$emit('onSubmit')
                    }
                })
            },
        }
    }
</script>

<style scoped>
</style>