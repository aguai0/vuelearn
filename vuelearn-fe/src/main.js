// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'core-js/fn/array/from'
import 'core-js/fn/object/assign'
import 'core-js/es6/promise'
import Vue from 'vue'
import App from './App'
import Router from 'vue-router'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/style/style.css'
import {parseRoute} from '@/utils'
import store from '@/store/index'
// import {apiMenus} from '@/service/api'
import menus from '@/constant/menu'
import Request from '@/service/request'

Vue.use(Router)
Vue.use(ElementUI)
Vue.use(Request)

const routeList = parseRoute(menus).concat([{
  path: '*',
  redirect: '/404'
}])
router.addRoutes(routeList)

Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
