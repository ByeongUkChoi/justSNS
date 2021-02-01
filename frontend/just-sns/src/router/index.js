import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import('@/views/Home'),
    },
    {
      path: '/sign-up',
      component: () => import('@/views/auth/SignUpPage'),
    },
    {
      path: '/sign-in',
      component: () => import('@/views/auth/SignInPage'),
    },
    {
      path: '/write',
      component: () => import('@/views/post/Write'),
    },
    // Not Found Page
    {
      path: '*',
      component: () => import('@/views/NotFoundPage'),
    },
  ],
});
