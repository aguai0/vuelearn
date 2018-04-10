<template>
  <div v-loading="loading">
    <el-form ref="customer" status-icon :model="customer" label-width="120px" size="mini">
      <el-row>
        <el-col :span="24" class="row-title">
          <h2>个人信息</h2>
        </el-col>
        <el-col :span="18">
          <el-col :span="8">
            <el-form-item label="手机号:" prop="mobile" :rules="[
                    {required: true, message: '请输入客户手机号', trigger: 'blur'},
                    {
                      pattern: /^(13[0-9]|15[^4,\D]|16[6]|17[0-9]|18[0-9]|14[56789]|19[89])[0-9]{8}$/,
                      message: '请输入正确的手机号',
                      trigger: 'blur'
                    }
                  ]">
              <el-input v-model="customer.mobile"></el-input>
            </el-form-item>
            <el-form-item label="客户姓名:" prop="name" :rules="[
                    {required: true, message: '请输入客户姓名', trigger: 'blur'}
                   ]">
              <el-input v-model="customer.name"></el-input>
            </el-form-item>
            <el-form-item label="是否企业客户">
              <el-switch v-model="customer.type"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别">
              <el-radio-group v-model="customer.sex">
                <el-radio-button :label="0">女</el-radio-button>
                <el-radio-button :label="1">男</el-radio-button>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="账户余额">
              <el-input v-model="customer.balance"></el-input>
            </el-form-item>
            <el-form-item label="邀请码" class="choose-input" prop="invitationCode" :rules="[
                  {required: true, message: '请选择邀请码'}
                ]">
              <el-input v-model="customer.invitationCode" :disabled="true"></el-input>
              <el-button type="text" @click="chooseInvitationCode">选择</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="身份证号" prop="cardId" :rules="[
                  {required: true, message: '请输入客户身份证号', trigger: 'blur'},
                  {pattern: /\d{18}/, message: '请输入正取的身份证号', trigger: 'blur'}
                  ]">
              <el-input v-model="customer.cardId"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-input v-model="customer.email"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-select v-model="customer.noDisturbing" placeholder="请选择">
                <el-option
                  v-for="item in disturbingOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="可用日期">
              <el-col :span="10">
                <el-form-item prop="startDate" :rules="[
                      {required: true, message: '请选择开始日期', trigger: 'change'}
                    ]">
                  <el-date-picker format="yyyy-MM-dd HH:mm:ss" type="date" :editable="false" placeholder="选择日期" v-model="customer.startDate"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col class="line" :offset="1" :span="1">-</el-col>
              <el-col :span="10">
                <el-form-item prop="endDate" :rules="[
                      {required: true, message: '请选择结束日期', trigger: 'change'}
                    ]">
                  <el-date-picker format="yyyy-MM-dd HH:mm:ss" type="date" :editable="false" placeholder="选择日期" v-model="customer.endDate"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input type="textarea"
                        :rows="4"
                        v-model="customer.remark"
                        placeholder="备注"></el-input>
            </el-form-item>
          </el-col>
        </el-col>
        <el-col :span="4" class="avatar-uploader-wrapper">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            list-type="picture"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="customer.imgUrl" :src="customer.imgUrl" class="avatar">
            <span v-else class="avatar-uploader-icon">客户头像</span>
          </el-upload>
          <el-button type="primary" v-if="customer.imgUrl" @click="handlePictureCardPreview" size="mini">查看图片</el-button>
          <el-dialog :visible.sync="showImg">
            <img width="100%" :src="customer.imgUrl" alt="">
          </el-dialog>
        </el-col>
        <el-col :span="24" class="row-title">
          <h2>支付信息</h2>
        </el-col>
        <el-col :span="18">
          <el-col :span="8">
            <el-form-item label="支持支付方式">
              <el-checkbox-group v-model="customer.payList">
                <el-checkbox label="weixin" name="payList">微信</el-checkbox>
                <el-checkbox label="alipay" name="payList">支付宝</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
        </el-col>
        <el-col :span="24" class="row-title">
          <h2>常用地址</h2>
          <el-button type="primary" 　size="mini" icon="el-icon-plus" @click="addAddress">增加一条</el-button>
        </el-col>
        <el-col :span="18" v-for="(item, index) in customer.address" :key="index">
          <el-col :span="8">
            <el-form-item label="标签" :prop="'address.' + index + '.tag'" :rules="[
                  {required: true, message: '请填写标签', trigger: 'blur'}
                ]">
              <el-input v-model="item.tag"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="城市" :prop="'address.' + index + '.city'" :rules="[
                  {required: true, message: '请选择城市', trigger: 'change'}
                ]">
              <el-select v-model="item.city">
                <el-option
                  v-for="city in citys"
                  :key="city.value"
                  :label="city.label"
                  :value="city.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="详细地址" :prop="'address.' + index + '.detail'" :rules="[
                  {required: true, message: '请输入详细地址', trigger: 'blur'}
                ]">
              <el-input v-model="item.detail"></el-input>
            </el-form-item>
          </el-col>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button type="primary" size="normal" @click="onSubmit('customer')">提交</el-button>
            <el-button type="primary" size="normal">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-dialog title="选择邀请码" :visible.sync="dialogShow" :fullscreen="true">
      <app-invitation @choose="selectInvitationCode"></app-invitation>
    </el-dialog>
  </div>
</template>

<style>
  .avatar-uploader-wrapper {
    display: flex;
    flex-flow: column;
    align-items: center;
  }
  .avatar-uploader {
    padding: 0 20px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    display: block;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
import api from '@/service/api'

export default {
  data () {
    return {
      citys: [
        {
          label: '北京',
          value: '00'
        },
        {
          label: '上海',
          value: '01'
        },
        {
          label: '天津',
          value: '02'
        },
        {
          label: '深圳',
          value: '03'
        },
        {
          label: '广州',
          value: '04'
        }
      ],
      disturbingOptions: [
        {
          label: '开启',
          value: 1
        },
        {
          label: '关闭',
          value: 0
        }
      ],
      customer: {
        name: '',
        sex: 0,
        mobile: '',
        balance: 0,
        cardId: '',
        remark: '',
        payList: [],
        type: false,
        startDate: '',
        endDate: '',
        invitationCode: '',
        imgUrl: '',
        noDisturbing: 1,
        address: [
          {
            tag: '',
            city: '',
            detail: ''
          }
        ]
      },
      showImg: false,
      dialogShow: false,
      loading: false
    }
  },
  components: {
    'app-invitation': require('@/components/Invitation-code').default
  },
  methods: {
    handlePictureCardPreview () {
      this.showImg = true
    },
    handleAvatarSuccess (res) {
      this.$nextTick(() => {
        this.customer.imgUrl = res.url
        console.log(this.customer.imgUrl)
      })
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.$route.params.id) {
            api.updateCustomer(this.customer)
              .then(() => {
                this.$notify({
                  title: '成功',
                  message: '更新客户成功',
                  type: 'success'
                })
                this.$router.go(-1)
              })
              .catch(e => {
                this.$notify.error({
                  title: '出错了',
                  message: e.message
                })
              })
          } else {
            api.addCustomer(this.customer)
              .then(() => {
                this.$notify({
                  title: '成功',
                  message: '新增客户成功',
                  type: 'success'
                })
                this.$router.go(-1)
              })
              .catch(e => {
                this.$notify.error({
                  title: '出错了',
                  message: e.message
                })
              })
          }
        } else {
          return false
        }
      })
    },
    chooseInvitationCode () {
      /**
       * 组件内定义通用唤起新页面方方法
       * 第一个参数为唤起页面的URL(如传入本系统路径可省略协议域名等信息，传入#后路径即可)
       * 第二个参数为一个对象，提供两个字段，autoClose（默认：true）代表被打开页面执行本页面回调函数后会自动关闭，callback（必填）页面回调函数，参数由被唤起页面传入
       */
      this.callNewPage(`${window.getDynamicUrl('http://localhost:8080/#/invitation-code/list')}`, {
        callback: 'cb'
      })
    },
    selectInvitationCode (val) {
      this.dialogShow = false
      this.customer.invitationCode = val.invitationCode
    },
    addAddress () {
      this.customer.address.push({
        tag: '',
        city: '',
        detail: ''
      })
    },
    /**
     * 本页面回调函数，将由被唤起页面自动调用
     * @param item 由被唤起页面自动传入
     */
    cb (item) {
      this.customer.invitationCode = item.invitationCode
    }
  },
  mounted () {
    if (this.$route.params.id) {
      this.loading = true
      api.getCustomer(this.$route.params.id)
        .then((data) => {
          this.loading = false
          this.customer = data
        })
        .catch(e => {
          this.$notify.error({
            title: '出错了',
            message: e.message
          })
        })
    }
  }
}
</script>
