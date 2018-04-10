import permission from '@/service/permission'

const state = {
  permissions: []
}

const getters = {
  permissions: state => state.permissions
}

const actions = {
  updatePermissions ({ commit }, sys) {
    permission.getPermissions(sys)
      .then((data) => {
        commit('UPDATEPERMISSION', data)
      })
  }
}

const mutations = {
  'UPDATEPERMISSION' (state, permissions) {
    state.permissions = permissions
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
