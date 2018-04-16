import vue from 'vue'
// import {getDynamicUrl} from '@/utils'

const baseUrl = ''

const get = (path, query) => {
  return vue.prototype.$request(`${baseUrl}${path}`, query)
}

const post = (path, body) => {
  return vue.prototype.$request(`${baseUrl}${path}`, body, 'post')
}

export const config = {
  query: query => get('/vuelearn/test/query', query),
  add: body => post('/vuelearn/config/doAdd', body, true),
  get: body => post('/vuelearn/config/toUpdate', {id: body}),
  update: body => post('/vuelearn/config/doUpdate', body, true),
  delete: body => post('/vuelearn/config/doDelete', {id: body})
}
/** export const apiMenus = {
  getRoutes: query => get('/api/routes')
} */
