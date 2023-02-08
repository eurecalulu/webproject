import fetch from '@/utils/fetch'

const user = {
  state: {
    userName: ''
  },

  mutations: {
    setUserName: (state, data) => {
      state.userName = data
    },
    logout: (state, data) => {
      state.userName = ''
    }
  },

  getters: {
    getCurrentUserName: state => {
      return state.userName
    }
  },

  actions: {
    register: async ({ state, dispatch, commit }, data) => {
      let url = '/user/register'
      const res = await fetch.post(url, data)
      return res
    },
    getUserCount: async ({ state, dispatch, commit }, data) => {
      let url = '/user/count'
      const res = await fetch.get(url)
      return res
    },
    getUserInfo: async ({ state, dispatch, commit }, data) => {
      let url = '/user/info'
      const res = await fetch.get(url)
      return res
    },
    login: async ({ state, dispatch, commit }, data) => {
      let url = '/user/login'
      const res = await fetch.post(url, data)
      return res
    }
  }
}

export default user
