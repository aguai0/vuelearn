import Router from 'vue-router'
import index from '@/pages/index'
import _404 from '@/pages/404'

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/404',
      name: '404',
      component: _404
    }
  ]
})

router.beforeEach((to, from, next) => {
  next()
})

export default router
