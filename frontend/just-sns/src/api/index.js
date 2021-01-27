import axios from 'axios';
import { setInterceptors } from './common/interceptors';

function createInstance(url) {
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}/`,
  });
  return setInterceptors(instance);
}

// function create(url) {
//   const instance = axios.create({ baseURL: url });
//   return instance;
// }
// TODO: create with auth

export const auth = createInstance('auth');
export const posts = createInstance('posts');
