<template>
  <div>
    <el-table
      :data="tableData"
      stripe>
      <el-table-column
        prop="docTitle"
        label="法规标题"
        :min-width="150">
      </el-table-column>
      <el-table-column
        prop="docIdentifier"
        label="法规文号">
      </el-table-column>
      <el-table-column
        prop="docDepart"
        label="发文部门">
      </el-table-column>
      <el-table-column
        prop="interpretDepart"
        label="解读部门">
      </el-table-column>
      <el-table-column
        prop="user"
        label="录入人">
      </el-table-column>
      <el-table-column
        prop="time"
        label="录入时间"
        :min-width="120">
      </el-table-column>
      <el-table-column
        prop="interpretTitle"
        label="解读标题"
        :min-width="150">
      </el-table-column>
      <el-table-column
        label="操作"
        :min-width="status === 'DRAFT' ? 240 : 160">
        <template slot-scope="scope">
          <el-button @click="seeDetail(scope.row)" type="primary" size="small">查看</el-button>
          <el-button @click="abolish(scope.row)" type="danger" size="small" :disabled="scope.row.user !== $store.getters.getCurrentUserName" v-if="status === 'PUBLIC'">废止</el-button>
          <el-button @click="edit(scope.row)" type="info" size="small" v-if="status === 'DRAFT'">编辑</el-button>
          <el-button @click="deletePI(scope.row)" type="danger" size="small" v-if="status === 'DRAFT'">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-sizes="[10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    status: {
      default: 'PUBLIC'
    }
  },
  data () {
    return {
      tableData: [],
      pageNo: 1,
      pageSize: 10,
      docTitle: '',
      docIdentifier: '',
      docDepart: '',
      totalSize: 10,
      startTime: '',
      endTime: ''
    }
  },
  created () {
    this.getPIList()
  },
  watch: {
    '$route' () {
      document.title = this.status === 'PUBLIC' ? '政策解读列表' : this.status === 'DRAFT' ? '政策解读草稿列表' : '已废止政策解读列表'
      this.tableData = []
      this.pageNo = 1
      this.pageSize = 10
      this.docTitle = ''
      this.docIdentifier = ''
      this.docDepart = ''
      this.startTime = ''
      this.endTime = ''
      this.getPIList()
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.getPIList()
    },
    handleCurrentChange (val) {
      this.pageNo = val
      this.getPIList()
    },
    abolish (val) {
      this.$confirm('此操作将废止该政策解读，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('updatePIStatus', {
          id: val.id,
          status: 'ABOLISH'
        }).then(res => {
          val.status = 'ABOLISH'
          this.$message({
            type: 'success',
            message: '废止成功！'
          })
          this.getPIList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消废止'
        })
      })
    },
    deletePI (val) {
      this.$confirm('此操作将删除该政策解读，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('deletePI', {
          id: val.id
        }).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功！'
          })
          this.getPIList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消废止'
        })
      })
    },
    edit (val) {
      this.$router.push({
        name: 'PIDetail',
        params: {
          id: val.id,
          state: 'edit'
        }
      })
    },
    seeDetail (val) {
      this.$router.push({
        name: 'PIDetail',
        params: {
          id: val.id,
          state: 'view'
        }
      })
    },
    search (params) {
      this.docTitle = params.docTitle
      this.docIdentifier = params.docIdentifier
      this.docDepart = params.docDepart
      this.startTime = params.startTime
      this.endTime = params.endTime
      this.getPIList()
    },
    getPIList () {
      this.$store.dispatch('getPIList', {
        status: this.status,
        docTitle: this.docTitle,
        docIdentifier: this.docIdentifier,
        docDepart: this.docDepart,
        startTime: this.startTime,
        endTime: this.endTime,
        pageSize: this.pageSize,
        pageNo: this.pageNo,
        withBody: false
      }).then(res => {
        this.tableData = res.data.data
        for (let i = 0; i < this.tableData.length; i++) {
          // console.log(this.tableData[i])
          if (this.tableData[i].docDepart === '') {
            this.tableData[i].docDepart = '法规部'
          }
          if (this.tableData[i].docIdentifier === '') {
            this.tableData[i].docIdentifier = '征求意见稿'
          }
        }
        this.totalSize = res.data.total
      })
    }
  }
}
</script>

<style lang="less">
.pagination {
  margin-top: 20px;
}

</style>
