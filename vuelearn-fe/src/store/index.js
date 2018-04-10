import Vuex from 'vuex'
import Vue from 'vue'
import permissions from './modules/permissions'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    permissions
  },
  strict: debug,
  plugins: []
})
