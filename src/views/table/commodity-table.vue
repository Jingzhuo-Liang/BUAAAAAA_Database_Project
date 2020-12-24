<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.comname" placeholder="CommodityName" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.type" placeholder="Platform" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in sortplatform" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        Export
      </el-button>
    </div>
    <!--    fetchList-->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="ID" prop="id" sortable="custom" align="center" width="70" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Type" width="70px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.cid }}</span>
        </template>
      </el-table-column>
      <el-table-column label="CommodityName" width="140px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.comname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Platform" width="80px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.platform }}</span>
        </template>
      </el-table-column>
      <el-table-column label="StoreName" width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.storename }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Gift" width="90px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.gift }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Rate" width="80px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.favorrate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Sales" width="80px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.sales }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="Url" width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.url }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="PictureLink" width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.piclink }}</span>
        </template>
      </el-table-column>
      <el-table-column label="NewestPrice" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.newestprice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="180" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            Edit
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteData(row,$index)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="Type" prop="cid">
          <el-input v-model="temp.cid" />
        </el-form-item>
        <el-form-item label="Commodity" prop="comname">
          <el-input v-model="temp.comname" />
        </el-form-item>
        <el-form-item label="Platform" prop="platform">
          <el-input v-model="temp.platform" />
        </el-form-item>
        <el-form-item label="StoreName" prop="storename">
          <el-input v-model="temp.storename" />
        </el-form-item>
        <el-form-item label="Gift" prop="gift">
          <el-input v-model="temp.gift" />
        </el-form-item>
        <el-form-item label="Rate" prop="favorrate">
          <el-input v-model="temp.favorrate" />
        </el-form-item>
        <el-form-item label="Sales" prop="sales">
          <el-input v-model="temp.sales" />
        </el-form-item>
        <el-form-item label="Url" prop="url">
          <el-input v-model="temp.url" />
        </el-form-item>
        <el-form-item label="PictureLink" prop="piclink">
          <el-input v-model="temp.piclink" />
        </el-form-item>
        <el-form-item label="NewestPrice" prop="newestprice">
          <el-input v-model="temp.newestprice" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchCommodityList, updateCommodity, deleteCommodity } from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
// import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const sortplatform = [
  { key: 1, display_name: 'JingDong' },
  { key: 2, display_name: 'TaoBao' },
  { key: 3, display_name: 'SuNing' },
  { key: 4, display_name: 'Apple' },
  { key: 0, display_name: 'Total' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const VipKeyValue = sortplatform.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'UserTable',
  // components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return VipKeyValue[type]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: false,
      listQuery: {
      },
      temp: {
        // id: undefined,
        cid: '',
        id: '',
        comname: '',
        platform: '',
        storename: '',
        gift: '',
        favorrate: '',
        sales: '',
        url: '',
        piclink: '',
        newestprice: ''
      },
      sortplatform,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        comname: [{ required: true, message: 'CommodityName is required', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
    console.log('created')
  },
  methods: {
    getList() {
      this.listLoading = false
      console.log('getList')
      fetchCommodityList(this.listQuery).then(response => {
        console.log('getResponse')
        this.list = response.data
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: 'Operation Success',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      console.log('handle' + this.temp)
      // this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateCommodity(tempData).then(() => {
            console.log('dd' + tempData)
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    deleteData: function(row, index) {
      // console.log('this1' + JSON.stringify(this.temp))
      this.temp = Object.assign({}, row)
      // console.log('this2' + JSON.stringify(this.temp))
      const tempData = Object.assign({}, this.temp)
      // console.log('this3' + JSON.stringify(tempData))
      this.$confirm('The operation will permanently delete the commodity. Continue?', 'Notify', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        deleteCommodity(tempData).then(() => {
          console.log('this' + tempData)
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
        })
        this.list.splice(index, 1)
      }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: 'Delete Cancelled'
        })
      })
    },
    handleCreate() {
      // this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = null // sql will auto increase id
          updateCommodity(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['id', 'cid', 'comname', 'platform', 'storename', 'gift', 'favorrate', 'sales', 'url', 'piclink', 'newestprice']
        const filterVal = ['id', 'cid', 'comname', 'platform', 'storename', 'gift', 'favorrate', 'sales', 'url', 'piclink', 'newestprice']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'commodity-table'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
