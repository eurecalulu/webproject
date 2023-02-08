import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import policyInterpretion from './modules/policyInterpretion'
Vue.use(Vuex)
const store = new Vuex.Store({
  modules: {
    user,
		policyInterpretion
  }
})
export default store