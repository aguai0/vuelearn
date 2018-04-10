import axios from 'axios'
import qs from 'qs'

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

const envMap = {
  development: '',
  test: 'test',
  test2: 'test02',
  test3: 'test03',
  preProduct: 'pre',
  product: ''
}

export default function install (Vue, options) {
  window.getDynamicUrl = url => {
    if (!window.runtime_env) return url
    return url.replace(/(.+)\.test.com/, `$1${envMap[window.runtime_env]}.test.com`)
  }

  axios.interceptors.response.use((response) => {
    return response.data
  }, (error) => {
    if (options && options.fake === true) return Promise.resolve()
    if (error.response.status <= 403 && error.response.status >= 401) {
      top.window.postMessage({
        type: 'NO_LOGIN',
        msg: '401'
      }, '*')
    }
    return Promise.reject(error.response)
  })
  Vue.prototype.$request = (url, body, type = 'get', isJson = false) => {
    const query = {
      url,
      method: type,
      withCredentials: true,
      timeout: 30000
    }
    if (type === 'get') {
      query.params = body
    } else {
      query.data = isJson ? body : qs.stringify(body)
    }
    return axios.request(query)
  }
  Vue.prototype.$ajax = (options) => {
    return axios(options)
  }
}
