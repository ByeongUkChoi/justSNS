import Vue from 'vue';
import App from './App.vue';
import router from '@/router';
import store from '@/store';

// Vuesax
import Vuesax from 'vuesax';
// import 'vuesax/dist/vuesax.css';
import 'vuesax/dist/vuesax.min.css';
import './assets/css/vuesax.docs-styles.9c3c9a79.css';

// boxicons
import 'boxicons';
import 'boxicons/css/boxicons.min.css';

Vue.config.productionTip = false;

// Vuesax
Vue.use(Vuesax);

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app');
