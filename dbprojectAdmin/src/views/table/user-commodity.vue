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
    </div>
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
    </el-table>
  </div>
</template>

<script>
import { fetchCommodityList } from '@/api/article'
import waves from '@/directive/waves' // waves directive

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
    }
  }
}
</script>
