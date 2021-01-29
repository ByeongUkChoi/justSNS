import Vue from 'vue';
import Vuex from 'vuex';
import { signIn } from '@/api/auth';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: '',
    name: '',
    token: '',
  },
  getters: {
    isLogin(state) {
      return state.username !== '';
    },
  },
  mutations: {
    setUsername(state, username) {
      state.username = username;
    },
    setName(state, username) {
      state.username = username;
    },
    setToken(state, token) {
      state.token = token;
    },
    clearAuth(state) {
      state.username = '';
      state.name = '';
      state.token = '';
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await signIn(userData);
      commit('setUsername', userData.username);
      // TODO: set name
      //commit('setName', );
      commit('setToken', data);
    },
  },
});
