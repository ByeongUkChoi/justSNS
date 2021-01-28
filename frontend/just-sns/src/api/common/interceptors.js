import store from '@/store';

export function setInterceptors(instance) {
  instance.interceptors.request.use(
    config => {
      config.headers.Authorization = store.state.token;
    },
    error => {
      console.log(error);
    },
  );

  instance.interceptors.response.use(
    response => {
      console.log(response);
    },
    error => {
      console.log(error);
    },
  );

  return instance;
}
