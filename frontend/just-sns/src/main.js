import Vue from 'vue';
import App from './App.vue';
import router from '@/router';
import store from '@/store';
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.config.productionTip = false;

// Bootstrap
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app');
