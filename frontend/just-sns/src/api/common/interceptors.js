export function setInterceptors(instance) {
  instance.interceptors.request.use(
    config => {
      console.log(config);
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
