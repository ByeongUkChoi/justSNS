import { auth } from './index';

function duplicateCheck(username) {
  return auth.get(`check-username/${username}`);
}

function signUp(data) {
  return auth.post('signup', data);
}

function signIn(data) {
  return auth.post('signin', data);
}

export { duplicateCheck, signUp, signIn };
