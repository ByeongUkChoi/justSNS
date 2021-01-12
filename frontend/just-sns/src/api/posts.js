import { posts } from './index';

function fetchPosts() {
    return posts.get();
}

export {
    fetchPosts,
};