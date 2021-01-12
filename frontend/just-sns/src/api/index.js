import axios from 'axios';

function create(url, options) {
	const instance = axios.create(Object.assign({ baseURL: url }, options));
	return instance;
}
// TODO: create with auth

// TODO: env
export const posts = create('http://localhost:8080/posts/');