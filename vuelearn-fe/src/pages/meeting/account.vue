<template>
  <el-row class="account-container">
    <el-col :span="24">
      <el-form :inline="true" :model="query">
        <el-form-item label="手机号:">
          <el-input v-model="query.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-input v-model="query.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="状态:">
          <el-select v-model="query.status" placeholder="请选择活动区域">
            <el-option label="正常" value="normal"></el-option>
            <el-option label="冻结" value="freeze"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item v-permission:10021="userPermissions">
          <el-button type="primary" @click="showDialog">新建账号</el-button>
        </el-form-item>
        <el-form-item v-permission:10011="userPermissions">
          <el-button type="primary" @click="batchRemove">批量删除</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="table-wrapper">
      <el-table
        :data="tableData"
        style="width: 100%" height="528"
        :highlight-current-row="true"
        @selection-change="select">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="mobile"
          label="手机号" width="150">
        </el-table-column>
        <el-table-column
          prop="contacts"
          label="联系人" width="100">
        </el-table-column>
        <el-table-column
          prop="balanceAmount"
          label="账户余额">
        </el-table-column>
        <el-table-column
          prop="rechargeAmount"
          label="充值金额">
        </el-table-column>
        <el-table-column
          prop="giveAmount"
          label="赠送金额">
        </el-table-column>
        <el-table-column
          prop="costTotalAmount"
          label="消费总额">
        </el-table-column>
        <el-table-column
          prop="costRealAmount"
          label="实际消费金额" width="150">
        </el-table-column>
        <el-table-column
          prop="costGiveAmount"
          label="消费冲赠金额" width="150">
        </el-table-column>
        <el-table-column
          prop="refundAmount"
          label="退款金额">
        </el-table-column>
        <el-table-column
          prop="meetingCount"
          label="会议次数">
        </el-table-column>
        <el-table-column
          prop="status"
          label="账号状态"
          :formatter="statusFormat">
        </el-table-column>
        <el-table-column
          width="180"
          label="操作"
          fixed="right">
          <template slot-scope="scope">
            <el-button type="text" @click="freeze(scope.row)">冻结账户</el-button>
            <el-button type="text" @click="unfreeze(scope.row)">解冻账户</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="24" class="top-15">
      <el-pagination
        :background="true"
        @current-change="search"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="total">
      </el-pagination>
    </el-col>
    <el-col :span="24">
      <el-dialog title="新建会议账号" :visible.sync="dialogShow" width="30%">
        <el-form :model="account" label-width="120px">
          <el-form-item label="会员手机号码:">
            <el-select
              v-model="account.mobile"
              filterable
              remote
              reserve-keyword
              placeholder="请输入手机号"
              :remote-method="remoteMethod"
              :loading="mobileOptionsLoading">
              <el-option
                v-for="item in mobileOptions"
                :key="item.mobile"
                :label="item.mobile"
                :value="item.mobile">
                <span style="float: left">{{ item.contacts }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.mobile }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="会员姓名:">
            <el-input v-model="account.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="联系人:">
            <el-input v-model="account.contact" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注:">
            <el-input type="textarea"
                      :rows="4"
                      v-model="account.content"
                      placeholder="备注"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogShow = false">取 消</el-button>
          <el-button type="primary" @click="newAccount">确 定</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<style>

</style>

<script>
  import api from '@/service/api'
  export default {
    data () {
      return {
        query: {
          mobile: '13253061062',
          name: '',
          status: ''
        },
        tableData: [],
        currentPage: 1,
        total: 0,
        pageSize: 10,
        dialogShow: false,
        mobileOptions: [],
        mobileOptionsLoading: false,
        account: {
          mobile: '',
          name: '',
          contact: '',
          content: ''
        },
        selections: []
      }
    },
    methods: {
      search () {
        api.getMeetingAccount(this.query)
          .then((data) => {
            this.total = data.total
            this.tableData = data.list
          })
      },
      showDialog () {
        this.dialogShow = true
      },
      statusFormat (row, column, cell) {
        return cell === 'normal' ? '正常' : '冻结'
      },
      freeze (row) {
        api.freezeAccount(row)
          .then(() => {
            this.$notify({
              title: '成功',
              message: '冻结会议账户成功',
              type: 'success'
            })
            this.search()
          })
          .catch(e => {
            this.$notify.error({
              title: '出错了',
              message: e.message
            })
          })
      },
      unfreeze (row) {
        api.unfreezeAccount(row)
          .then(() => {
            this.$notify({
              title: '成功',
              message: '解冻会议账户成功',
              type: 'success'
            })
            this.search()
          })
          .catch(e => {
            this.$notify.error({
              title: '出错了',
              message: e.message
            })
          })
      },
      remoteMethod (query) {
        if (query !== '') {
          this.mobileOptionsLoading = true
          api.getMeetingAccount({})
            .then((data) => {
              this.mobileOptionsLoading = false
              this.mobileOptions = data.list.filter(item => {
                return item.mobile.toLowerCase()
                    .indexOf(query.toLowerCase()) > -1
              })
            })
        } else {
          this.mobileOptions = []
        }
      },
      select (rows) {
        this.selections = rows
      },
      batchRemove () {
        if (this.selections && this.selections.length <= 0) {
          this.$message({
            message: '请先选择要删除的列表项',
            type: 'warning'
          })
          return
        }
        api.batchRemoveMeetingAccount({
          target: this.selections.map(item => item.id)
        })
          .then(() => {
            this.$notify({
              title: '成功',
              message: '批量删除成功',
              type: 'success'
            })
            this.search()
          })
      },
      newAccount () {
        api.addMeetingAccount(this.account)
          .then(() => {
            this.$notify({
              title: '成功',
              message: '新增会议账户成功',
              type: 'success'
            })
            this.search()
          })
          .catch(e => {
            this.$notify.error({
              title: '出错了',
              message: e.message
            })
          })
        this.dialogShow = false
      }
    },
    mounted () {
      this.search()
    }
  }
</script>