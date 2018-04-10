import vue from 'vue'

export default {
  getPermissions (sys) {
    return vue.prototype.$request('/api/permission/button', {
      sys: sys
    })
  }
}
