export default [

  {
    // 目录或具体路由的标题
    title: '个人客户管理',
    // icon根据element-ui的icon官方文档选择
    icon: 'el-icon-service',
    // path以_开头则表示这是一个目录path并不是路由的路径
    path: '_customer',
    // subMenus 代表子级菜单, 其中元素的字段名除icon外与父级相同
    subMenus: [
      {
        title: '客户信息',
        path: '/customer/list'
      },
      {
        title: '新建客户',
        path: '/customer/info',
        // hidden=true 代表不会在菜单中显示
        hidden: true
      },
      {
        title: '客户信息',
        // 动态路由示例 :id 为传入参数
        path: '/customer/info/:id',
        hidden: true
      }
    ]
  },
  {
    title: '邀请码管理',
    icon: 'el-icon-mobile-phone',
    path: '_invitation-code',
    subMenus: [
      {
        title: '邀请码信息',
        path: '/invitation-code/list'
      }
    ]
  },
  {
    title: '会议账号管理',
    icon: 'el-icon-date',
    path: '_meeting',
    subMenus: [
      {
        title: '会议账号',
        path: '/meeting/account'
      }
    ]
  }
]
