import fetch from '@/utils/fetch'

const wordFrequency = {
  state: {
  },

  mutations: {
  },

  getters: {
  },

  actions: {
    getRankNWordFrequency: async ({ state, dispatch, commit }, data) => {
      let url = `/wordFrequency/getRankNWordFrequency?n=${data}`
      const res = await fetch.get(url, data)
      return res
    },
    getAllWordFrequency: async ({ state, dispatch, commit }, data) => {
      let url = `/wordFrequency/getAllWordFrequency`
      const res = await fetch.get(url, data)
      return res
    }
  }
}

export default wordFrequency
