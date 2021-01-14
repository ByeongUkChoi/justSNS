import axios from 'axios';

function create(url, options) {
  const instance = axios.create(Object.assign({ baseURL: url }, options));
  return instance;
}
// TODO: create with auth

// TODO: env
export const posts = create(process.env.VUE_APP_API_URL + '/posts/');
