import Vue from 'vue';
import Vuex from 'vuex';

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
});
