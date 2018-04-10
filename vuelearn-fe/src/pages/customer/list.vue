<template>
  <el-row class="list-container">
    <el-col :span="24">
      <el-form :inline="true" :model="query">
        <el-form-item label="手机号:">
          <el-input v-model="query.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-input v-model="query.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="在线设备号:">
          <el-input v-model="query.deviceId" placeholder="在线设备号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-edit-outline" @click="createCustomer">新建客户</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="table-wrapper">
      <el-table
        :data="tableData"
        style="width: 100%" height="528"
        :highlight-current-row="true">
        <el-table-column
          label="姓名" width="100">
          <template slot-scope="scope">
            <!--超链接的写法 target="_blank"代表打开一个新页面 推荐使用这种写法 可以选择写全Url
              eg :href="http://xxxx" 如果是本域名下的地址可简写为 :href="/#/xxxx" 简写一定要以"/#/开头"-->
            <a :href="'/#/customer/info/' + scope.row.id" target="_blank">{{scope.row.name}}</a>
          </template>
        </el-table-column>
        <el-table-column
          prop="sex"
          label="性别" width="50">
        </el-table-column>
        <el-table-column
          prop="mobile"
          label="手机号" width="120">
        </el-table-column>
        <el-table-column
          prop="level"
          label="会员等级" width="100">
        </el-table-column>
        <el-table-column
          prop="balance"
          label="余额" width="100">
        </el-table-column>
        <el-table-column
          prop="totalMiles"
          label="总里程" width="100">
        </el-table-column>
        <el-table-column
          prop="monthMiles"
          label="当月里程" width="100">
        </el-table-column>
        <el-table-column
          prop="type"
          label="客户类型" width="100"
          :formatter="typeFormat">
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态" width="100"
          :formatter="statusFormat">
        </el-table-column>
        <el-table-column
          prop="lastLoginTime"
          label="上次登录时间" width="120">
        </el-table-column>
        <el-table-column
          prop="lastLoginType"
          label="上次登录方式">
        </el-table-column>
        <el-table-column
          width="150"
          label="操作"
          fixed="right">
          <template slot-scope="scope">
            <el-button type="text" @click="update(scope.row)">修改</el-button>
            <el-button type="text" @click="remove(scope.row)">删除</el-button>
            <el-dropdown trigger="click">
              <el-button type="text">
                更多
                <i class="el-icon-arrow-down"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>充值卡充值</el-dropdown-item>
                <el-dropdown-item>发票抬头</el-dropdown-item>
                <el-dropdown-item>历史记录</el-dropdown-item>
                <el-dropdown-item>账号操作记录</el-dropdown-item>
                <el-dropdown-item>收藏司机</el-dropdown-item>
                <el-dropdown-item>拉新纪录</el-dropdown-item>
                <el-dropdown-item>定制服务</el-dropdown-item>
                <el-dropdown-item>里程详情</el-dropdown-item>
                <el-dropdown-item>等级记录</el-dropdown-item>
                <el-dropdown-item>大转盘权益记录</el-dropdown-item>
                <el-dropdown-item>中奖纪录</el-dropdown-item>
                <el-dropdown-item>调整未开票金额</el-dropdown-item>
                <el-dropdown-item>刷新</el-dropdown-item>
                <el-dropdown-item>乘车喜好</el-dropdown-item>
                <el-dropdown-item>接收信用卡优惠</el-dropdown-item>
                <el-dropdown-item>亲情账户</el-dropdown-item>
                <el-dropdown-item>配置预约特权</el-dropdown-item>
                <el-dropdown-item>批量开通预约特权</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="24" class="top-15">
      <el-pagination
        :background="true"
        @current-change="search"
        :current-page.sync="query.currentPage"
        :page-size="query.pageSize"
        layout="total, prev, pager, next"
        :total="10000">
      </el-pagination>
    </el-col>
  </el-row>
</template>

<style scoped>

</style>

<script>
import api from '@/service/api'

export default {
  data () {
    return {
      query: {
        mobile: '',
        name: '',
        deviceId: '',
        currentPage: 1,
        pageSize: 10,
        id: ''
      },
      tableData: [],
      total: 0
    }
  },
  methods: {
    search () {
      api.getCustomerList(this.query)
        .then((data) => {
          this.total = data.total
          this.tableData = data.list.map(item => {
            if (!item.name) item.name = this.query.name
            return item
          })
        })
    },
    createCustomer () {
      this.$router.push('/customer/info')
    },
    typeFormat (row) {
      return row.type === 'single' ? '个人' : '企业'
    },
    statusFormat (row) {
      return row.status === 'normal' ? '正常' : '异常'
    },
    update (row) {
      this.$router.push(`/customer/info/${row.id}`)
    },
    remove (row) {
      api.removeCustomer(row)
        .then(() => {
          this.$notify({
            title: '成功',
            message: '删除用户成功!',
            type: 'success'
          })
          this.search()
        })
    }
  },
  mounted () {
    this.search()
  }
}
</script>
