import vue from 'vue'
import {getDynamicUrl} from '@/utils'

const baseUrl = getDynamicUrl('http://127.0.0.1:8080/vuelearn')

const get = (path, query) => {
  return vue.prototype.$request(`${baseUrl}${path}`, query)
}

const post = (path, body) => {
  return vue.prototype.$request(`${baseUrl}${path}`, body, 'post')
}

export const config = {
  query: query => get('/test/query', query),
  add: body => post('/config/doAdd', body, true),
  get: body => post('/config/toUpdate', {id: body}),
  update: body => post('/config/doUpdate', body, true),
  delete: body => post('/config/doDelete', {id: body})
}
/** export const apiMenus = {
  getRoutes: query => get('/api/routes')
} */
