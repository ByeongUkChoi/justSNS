import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/Home'),
    },
    {
      path: '/sign-up',
      name: 'signUp',
      component: () => import('@/views/SignUp'),
    },
    {
      path: '/write',
      name: 'write',
      component: () => import('@/views/Write'),
    },
  ],
});
