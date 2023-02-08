import request from '@/utils/request'

const user = {
  state: {
    userName: '',
		token: ''
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
    },
		getToken: state => {
			return state.token
		}
  },

  actions: {
		login: async ({ state, dispatch, commit }, data) => {
			let url = '/user/login'
			const res = request.post(url, data)
			return res
    },
    register: async ({ state, dispatch, commit }, data) => {
      let url = '/user/register'
      const res = request.post(url, data)
      return res
    },
    getUserInfo: async ({ state, dispatch, commit }, data) => {
      let url = '/user/info'
      const res = request.get(url)
			console.log(res)
      return res
    },
    getUserCount: async ({ state, dispatch, commit }, data) => {
      let url = '/user/count'
      const res = await request.get(url)
      return res
    },
  }
}

export default user
