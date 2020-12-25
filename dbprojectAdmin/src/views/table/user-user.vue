<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="UserName" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.type" placeholder="VIP" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in isvip" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
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
      <el-table-column label="UserName" width="130px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Password" width="130px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Email" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="isStudent" width="90px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.isstudent }}</span>
        </template>
      </el-table-column>
      <el-table-column label="isMember" width="90px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.ismember }}</span>
        </template>
      </el-table-column>
      <el-table-column label="isVip" width="90px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.isvip }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Join Date" width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.date }}</span>
        </template>
      </el-table-column>
    </el-table>

    <!--    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="UserName" prop="username">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="temp.password" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="isStudent" prop="isstudent">
          <el-select v-model="temp.isstudent" class="filter-item" placeholder="Please select">
            <el-option v-for="item in studentOptions" :key="item.key" :label="item.label" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="isMember" prop="ismember">
          <el-input v-model="temp.ismember" />
        </el-form-item>
        <el-form-item label="isVip" prop="isvip">
          <el-select v-model="temp.isvip" class="filter-item" placeholder="Please select">
            <el-option v-for="item in vip2_Options" :key="item.key" :label="item.label" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="Join Date" prop="date">
          <el-date-picker v-model="temp.date" type="date" value-format="yyyy-MM-dd" placeholder="Please pick a date" />
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
import { fetchList } from '@/api/article'
import waves from '@/directive/waves' // waves directive

const isvip = [
  { key: 'vip', display_name: 'VIP' },
  { key: 'nvip', display_name: 'NON-VIP' },
  { key: 'total', display_name: 'ALL' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const VipKeyValue = isvip.reduce((acc, cur) => {
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
        id: '',
        username: '',
        password: '',
        email: '',
        isstudent: '',
        ismember: '',
        date: new Date()
      },
      isvip,
      studentOptions: [{ label: '1 (Student)', key: '1' }, { label: '0 (Not Student)', key: '0' }],
      vip2_Options: [{ label: '1 (VIP)', key: '1' }, { label: '0 (Not VIP)', key: '0' }],
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        username: [{ required: true, message: 'UserName is required', trigger: 'change' }],
        password: [{ required: true, message: 'Password is required', trigger: 'change' }],
        email: [{ required: true, message: 'Email is required', trigger: 'change' }]
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
      fetchList(this.listQuery).then(response => {
        console.log('getResponse')
        this.list = response.data
        this.total = 4
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
