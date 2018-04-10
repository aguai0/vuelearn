import vue from 'vue'
import {getDynamicUrl} from '@/utils'

const baseUrl = getDynamicUrl('')

const get = (path, query) => {
  return vue.prototype.$request(`${baseUrl}${path}`, query)
}

const post = (path, body) => {
  return vue.prototype.$request(`${baseUrl}${path}`, body, 'post')
}

export default {
  getCustomerList: query => get('/api/customer/list', query),
  getCustomer: id => get(`/api/customer/${id}`),
  addCustomer: body => post('/api/customer/new', body),
  updateCustomer: body => post('/api/customer/update', body),
  removeCustomer: body => post('/api/customer/delete', body),
  getMeetingAccount: query => get('/api/meeting/account/list', query),
  addMeetingAccount: body => post('/api/meeting/account/new', body),
  batchRemoveMeetingAccount: body => post('/api/meeting/account/remove', body),
  freezeAccount: body => post('/api/meeting/account/freeze', body),
  unfreezeAccount: body => post('/api/meeting/account/unfreeze', body),
  getInvitationCode: query => get('/api/invitation-code/list', query)
}

/** export const apiMenus = {
  getRoutes: query => get('/api/routes')
} */
