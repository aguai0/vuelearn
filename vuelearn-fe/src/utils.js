
export const recursion = (array, key, value, childKey) => {
  for (let i = 0; i < array.length; i++) {
    if (array[i][key] === value) return array[i]
    if (array[i][childKey] && array[i][childKey] instanceof Array && array[i][childKey].length > 0) {
      let result = recursion(array[i][childKey], key, value, childKey)
      if (result) return result
    }
  }
  return null
}
export const parseRoute = (menus) => {
  const mixin = (obj, title) => {
    const base = {
      data () {
        return {
          $uConfig: {
            autoClose: false,
            openerCallBack: '',
            title: ''
          }
        }
      },
      computed: {
        userPermissions () {
          return this.$store.getters.permissions
        }
      },
      methods: {
        initTitle () {
          document.title = this.$data.$uConfig.title
        },
        registryVm () {
          window.$currentVm = this
        },
        pageOver () {
          const timer = arguments[arguments.length - 1] &&
          typeof arguments[arguments.length - 1] === 'number' ? arguments[arguments.length - 1] : 300
          const opener = window.opener
          if (!opener) return
          const {autoClose, openerCallBack} = this.$data.$uConfig
          if (openerCallBack &&
            typeof opener.$currentVm[openerCallBack] === 'function') {
            opener.$currentVm[openerCallBack](...arguments)
          }
          if (autoClose === true) {
            setTimeout(() => {
              window.close()
            }, timer)
          }
        },
        callNewPage (url, options = {}) {
          if (!options.autoClose) options.autoClose = true
          let path = /\/$/.test(url) ? url : `${url}/`
          const target = /^(http|https):\/\//.test(path) ? url
            : `/#${url}`
          let queryStr = ''
          for (let key in options) {
            queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
          }
          window.open(`${target}${queryStr}`)
        }
      },
      mounted () {
        this.$data.$uConfig.autoClose = !!this.$route.query.autoClose
        this.$data.$uConfig.openerCallBack = this.$route.query.callback
        this.$data.$uConfig.title = title
        this.registryVm()
        this.initTitle()
      },
      beforeRouteUpdate () {
        this.registryVm()
      }
    }
    if (obj.mixins) {
      obj.mixins.push(base)
    } else {
      obj.mixins = [base]
    }
    return obj
  }
  const routes = []
  const traverse = (list) => {
    for (let i = 0; i < list.length; i++) {
      const url = list[i].menuUrl || list[i].path
      if (url && !/^_/.test(url)) {
        const src = url.replace(/.+#/, '').replace(/\/:[\w_\d]+/g, '')
        const path = url.replace(/.+#/, '')
        const resource = require(`@/pages${src}`)
        if (resource && resource.default) {
          routes.push({
            path,
            component: mixin(resource.default, list[i].title)
          })
        } else {
          console.warn('未能解析的文件路径:', src)
        }
      }
      const children = list[i].children || list[i].subMenus
      if (children instanceof Array && children.length > 0) {
        traverse(children)
      }
    }
    return routes
  }
  return traverse(menus)
}

export const getDynamicUrl = url => {
  const envMap = {
    development: '',
    test: 'test',
    test2: 'test02',
    test3: 'test03',
    preProduct: 'pre',
    product: ''
  }
  // window.runtime_env 在build/webpack.dev.conf.js|build/webpack.prod.conf.js中定义, 旨在标注当前代码运行以及打包时的环境
  if (!window.runtime_env) return url
  return url.replace(/(.+)\.test.com/, `$1${envMap[window.runtime_env]}.test.com`)
}
