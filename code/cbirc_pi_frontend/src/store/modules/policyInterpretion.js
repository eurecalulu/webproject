import fetch from '@/utils/fetch'

const policyInterpretion = {
  state: {
  },

  mutations: {
  },

  getters: {
  },

  actions: {
    getPIList: async ({ state, dispatch, commit }, data) => {
      let url = '/policyInterpretation/list'
      const res = await fetch.post(url, data)
      return res
    },
    getPIByID: async ({ state, dispatch, commit }, id) => {
      let url = `/policyInterpretation/getById/?id=${id}`
      const res = await fetch.get(url)
      return res
    },
    createPI: async ({ state, dispatch, commit }, data) => {
      let url = `/policyInterpretation/addInterpretation`
      const res = await fetch.post(url, data)
      return res
    },
    updatePI: async ({ state, dispatch, commit }, data) => {
      let url = `/policyInterpretation/updateInterpretation`
      const res = await fetch.post(url, data)
      return res
    },
    // 仅改变状态，参数包括id和目标状态
    updatePIStatus: async ({ state, dispatch, commit }, data) => {
      let url = `/policyInterpretation/updateInterpretationStatus?id=${data.id}&status=${data.status}`
      const res = await fetch.post(url)
      return res
    },
    deletePI: async ({ state, dispatch, commit }, data) => {
      let url = `/policyInterpretation/deleteById?id=${data.id}`
      const res = await fetch.post(url)
      return res
    },
    updatePIDocBody: async ({ state, dispatch, commit }, data) => {
      let url = `/policyInterpretation/updateDocText`
      const res = await fetch.post(url, data)
      return res
    }
  }
}

export default policyInterpretion
