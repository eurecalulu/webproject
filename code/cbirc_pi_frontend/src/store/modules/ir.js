import fetch from '@/utils/fetch'

const ir = {
  state: {
  },

  mutations: {
  },

  getters: {
  },

  actions: {
    retrieval: async ({ state, dispatch, commit }, data) => {
      let url = `/ir/retrieval`
      const res = await fetch.post(url, data)
      return res
    }
  }
}

export default ir
