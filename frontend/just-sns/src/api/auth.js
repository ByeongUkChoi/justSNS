import { auth } from './index';

function duplicateCheck(username) {
  return auth.get(`check-username/${username}`);
}

function signUp(data) {
  return auth.post(`signup`, data);
}

export { duplicateCheck, signUp };
