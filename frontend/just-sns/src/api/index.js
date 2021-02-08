import axios from 'axios';
import { setInterceptors } from './common/interceptors';

function createInstance(url) {
  return axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}`,
  });
}

function createInstanceWithAuth(url) {
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}`,
  });
  return setInterceptors(instance);
}

export const auth = createInstance('auth');
export const posts = createInstanceWithAuth('posts');
