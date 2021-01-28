<template>
  <div>
    <div v-if="isLoading" class="text-center">
      <b-spinner label="Spinning"></b-spinner>
    </div>
    <div v-else-if="posts.length === 0">No posts are here... yet.</div>
    <div v-else>
      <PostItem v-for="post in posts" :key="post.id" :post="post"></PostItem>
    </div>
  </div>
</template>

<script>
import PostItem from '@/components/post/PostItem';
import { fetchPosts } from '@/api/posts';

export default {
  components: {
    PostItem,
  },
  data() {
    return {
      posts: [],
      // TODO: test
      // posts: [
      //   {
      //     id: 1,
      //     userId: 'abc',
      //     title: 'tt',
      //     body: 'body',
      //   },
      //   {
      //     id: 2,
      //     userId: '22',
      //     title: 'tt3',
      //     body: 'body323',
      //   },
      // ],
      isLoading: false,
    };
  },
  mounted() {
    this.fetchData();
  },
  computed: {},
  methods: {
    async fetchData() {
      this.isLoading = true;
      const posts = await fetchPosts();
      this.isLoading = false;
      console.log(posts);
    },
  },
};
</script>

<style></style>
