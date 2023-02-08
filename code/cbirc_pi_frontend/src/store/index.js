import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import policyInterpretion from './modules/policyInterpretion'
import wordFrequency from './modules/wordFrequency'
import comment from './modules/comment'
import ir from './modules/ir'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user,
    policyInterpretion,
    wordFrequency,
    comment,
    ir
  }
})
