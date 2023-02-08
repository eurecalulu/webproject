import fetch from '@/utils/fetch'

const user = {
  state: {
  },

  mutations: {
  },

  getters: {
  },

  actions: {
    getCommentsById: async ({ state, dispatch, commit }, id) => {
      let url = `/comment/listByPolicyInterpretationId?id=${id}`
      const res = await fetch.get(url)
      return res
    },
    addComment: async ({ state, dispatch, commit }, data) => {
      let url = `/comment/add`
      const res = await fetch.post(url, data)
      return res
    },
    deleteComment: async ({ state, dispatch, commit }, commentId) => {
      let url = `/comment/delete?id=${commentId}`
      const res = await fetch.delete(url)
      return res
    }
  }
}

export default user
