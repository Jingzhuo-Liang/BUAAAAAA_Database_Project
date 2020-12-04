<template>
    <div>
        <div style="text-align: left">
            <el-badge :value="leaveMessage.length" class="item">
                <el-button style = "font-size: 18px;font-weight: bold">留言</el-button>
            </el-badge>
            <el-button v-on:click="addLeaveMessage" style = "float: right"  type="primary" icon="el-icon-edit" circle>添加留言</el-button>
            <el-divider></el-divider>
        </div>
        <div>
            <el-collapse  v-model="activeNames" >
                <el-collapse-item class="deploy-setting" v-for="(item,index) in leaveMessage" :key="index" :name="index">
                    <template slot="title" style="height: max-content" >
                        <span style="font-weight:bold;font-size:22px;color:#2C8DF4;text-align: left">{{item.username}} 留言：</span>
                        <span style="font-weight:bold;font-size:18px;color:#333333; margin-top: 5px">{{item.message}}  </span>
                        <span style="font-size:8px;color:#333333;margin-top: 10px">{{item.time}}</span>
                        <el-button v-on:click="addReplyMessage(item)" style = "float: right;font-size: 1px"  type="primary" icon="el-icon-edit" circle>回复</el-button>
                    </template>
                    <div  style="text-align: left"
                          v-for ="item1 in replyMessage[item.id]" :key="item1.id">
                        <div style="text-align: left;margin-left: 20px">
                            <span style="float:left;font-weight:bold;font-size:18px;color:#2C8DF4;">{{item1.username}} 回复 {{item.username}} : </span>
                            <span style="float:left;font-weight:bold;font-size:14px;color:#333333;margin-top: 5px">{{item1.message}}</span>
                            <span style="float:left;font-size:4px;color:#333333;margin-top: 10px">{{  item1.time  }}</span>
                        </div>
                        <br/><br/>
                    </div>
                </el-collapse-item>
            </el-collapse>
            <LeaveMessage @onSubmit="getLeaveMessage()" ref="leaveMessageArea"></LeaveMessage>
            <ReplyMessage @onSubmit1="getReplyMessage()" ref="replyMessageArea"></ReplyMessage>
        </div>
    </div>
</template>

<script>
    import LeaveMessage from "@/components/commodityInfo/LeaveMessage";
    import ReplyMessage from "@/components/commodityInfo/ReplyMessage";
    export default {
        name: "Message",
        components: {LeaveMessage,ReplyMessage},
        data() {
            return {
                leaveMessage:[],
                replyMessage:[[]],
                activeNames:'0'
            }
        },
        methods:{
            addLeaveMessage() {
                if (this.$store.state.user.id === '') {
                    this.$alert('请先登录', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                }
                this.$refs.leaveMessageArea.dialogFormVisible = true
                this.$refs.leaveMessageArea.leaveMessage = {
                    userid:this.$store.state.user.id,
                    comid:this.$store.state.commodity.id,
                }
            },
            addReplyMessage(item) {
                if (this.$store.state.user.id === '') {
                    this.$alert('请先登录', '提示', {
                        confirmButtonText: '确定'
                    })
                    return
                }
                this.$refs.replyMessageArea.dialogFormVisible = true
                this.$refs.replyMessageArea.replyMessage = {
                    userid:this.$store.state.user.id,
                    leavemessageid:item.id,
                }
            },
            getLeaveMessage() {
                this.$axios.post("/leavemessage/" + this.$store.state.commodity.id).then(
                    resp=>{
                        if (resp && resp.status === 200) {
                            this.leaveMessage = resp.data.data
                            this.getReplyMessage()
                        }
                    }
                )
            },
            async getReplyMessage() {
                this.activeNames = '0'
                var i
                var _this = this
                for (i=0;i < _this.leaveMessage.length;i++) {
                    var id = _this.leaveMessage[i].id
                    await this.$axios.post("/replymessage/" +  id).then(
                        resp=>{
                            if (resp && resp.status === 200) {
                                //this.$refs.messageArea.replyMessage.push({id:resp.data.data})
                                //console.log(id)
                                this.replyMessage[id] = resp.data.data
                                //console.log(this.$refs.messageArea.replyMessage)
                            }
                        }
                    )
                }
            },
        }
    }
</script>

<style scoped>
    .el-collapse-item__arrow{
        float : left;
        margin-left:5px;
        margin-right:15px;
    }

    .el-collapse {
        border: 0;
    }

    .deploy-setting .el-collapse-item__header {
        border-bottom: 1px solid #2C8DF4;
        height: 1000px;
    }

    .deploy-setting .el-collapse-item__wrap{
        border-bottom:0px;
    }
    span {
        display: inline-block;
        line-height: 20px;
        padding: 5px 0;
        margin-right: 10px;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
        text-align: left;
    }
</style>