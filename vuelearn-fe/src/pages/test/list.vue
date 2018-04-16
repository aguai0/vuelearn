<template>
  <el-row>
    <el-col :span="24" class="row-title">
      <h2>设置</h2>
    </el-col>
    <el-col :span="24">
      <el-form size="mini" :inline="true" :model="query">
        <el-form-item label="名称:">
          <el-input v-model="query.name" placeholder="名称" clearable :maxlength="100"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="resetAndSearch">查询</el-button>
        </el-form-item>
        <el-col :span="24">
          <el-form-item>
            <el-button-group>
              <el-button type="primary" @click="create">新建</el-button>
              <el-button type="primary" @click="toUpdate" :disabled="!selected">修改</el-button>
              <el-button type="primary" @click="remove" :disabled="!selected">删除</el-button>
            </el-button-group>
          </el-form-item>
        </el-col>
      </el-form>
    </el-col>
    <el-col :span="24" class="table-wrapper">
      <el-table
        :data="tableData"
        style="width: 100%" height="528"
        :highlight-current-row="true"
        @current-change="choose">
        <el-table-column
          label="选择"
          width="100">
          <template slot-scope="scope">
            <el-radio v-model="currentHoliday" :label="scope.row.id"><i></i></el-radio>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称" width="400">
        </el-table-column>
        <el-table-column
          prop="startTime"
          label="开始日期" width="400">
        </el-table-column>
        <el-table-column
          prop="endTime"
          label="结束日期" width="400">
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
        :total="total">
      </el-pagination>
    </el-col>
    <el-col :span="24">
      <el-dialog title="设置" :before-close="handleClose" :visible.sync="dialogShow" width="50%">
        <el-form :model="testConfig" label-width="120px" :rules="rules" ref="testConfig">
          <el-row>
            <el-col :span="10">
              <el-form-item label="名称:" prop="holidayName">
                <el-input v-model="testConfig.name" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="开始日期:" prop="startTime">
                <el-date-picker
                  v-model="testConfig.startTime"
                  type="date"
                  :editable="false"
                  format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="结束日期:" prop="endTime">
                <el-date-picker
                  v-model="testConfig.endTime"
                  type="date"
                  :editable="false"
                  format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancle">取 消</el-button>
          <el-button type="primary" @click="add">保 存</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<style>

</style>

<script>
import {config} from '@/service/api'

export default {
  data () {
    const checkHolidayName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('名称不能为空'))
      } else {
        const patrn = /^[A-Za-z0-9\u4e00-\u9fa5]+$/g
        if (!patrn.test(value)) {
          callback(new Error('名称由字母,汉字或数字组成'))
        } else if (value.length > 20) {
          callback(new Error('名称长度不能大于20'))
        }
        callback()
      }
    }
    return {
      query: {
        name: '',
        currentPage: 1,
        pageSize: 10
      },
      testConfig: {
        name: '',
        startTime: '',
        endTime: '',
        isValid: '1',
        id: ''
      },
      tableData: [],
      currentHoliday: '',
      total: 0,
      dialogShow: false,
      id: '',
      rules: {
        name: [
          { validator: checkHolidayName, trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    selected () {
      return this.currentHoliday !== ''
    }
  },
  methods: {
    resetAndSearch () {
      this.query.currentPage = 1
      this.search()
    },
    choose (item) {
    },
    search () {
      config.query(this.query)
        .then((res) => {
          if (res) {
            this.tableData = res.datas
            this.total = res.total
          }
        })
    },
    handleClose (done) {
      this.$refs['testConfig'].resetFields()
      done()
    },
    cancle () {
      this.$refs['testConfig'].resetFields()
      this.dialogShow = false
    },
    create () {
      this.dialogShow = true
    },
    add () {
      this.$refs['testConfig'].validate((valid) => {
        if (valid) {
          if (this.holidayConfig.id) {
            config.update(this.holidayConfig)
              .then((res) => {
                if (res.status !== 0) {
                  this.$message(res.msg)
                } else {
                  this.$message('更新成功')
                  this.dialogShow = false
                }
                this.search()
              })
            this.currentHoliday = ''
          } else {
            config.add(this.holidayConfig)
              .then((res) => {
                if (res.status !== 0) {
                  this.$message(res.msg)
                } else {
                  this.$message('新建成功')
                  this.dialogShow = false
                }
                this.search()
              })
            this.currentHoliday = ''
          }
        } else {
          return false
        }
      })
    },
    toUpdate () {
      config.get(this.currentHoliday)
        .then((res) => {
          this.holidayConfig = res.re.data.holidayConfig
          delete this.holidayConfig.limit
          this.dialogShow = true
          this.search()
        })
    },
    remove () {
      config.delete(this.currentHoliday)
        .then((res) => {
          if (res.status !== 0) {
            this.$message(res.msg)
          } else {
            this.$message('删除成功')
            this.dialogShow = false
            this.search()
          }
          this.currentHoliday = ''
        })
    }
  },
  mounted () {
    this.search()
  }
}
</script>
