import { auth } from './index';

function duplicateCheck(username) {
  return auth.get(`check-username/${username}`);
}

export { duplicateCheck };
