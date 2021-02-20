<template>
  <div>
    <div v-if="posts.length === 0">No posts are here... yet.</div>
    <div v-else>
      <PostItem
        class="center"
        v-for="post in posts"
        :key="post.id"
        :post="post"
      ></PostItem>
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
      // TODO: test
      posts: [
        {
          id: 1,
          userId: 'abc',
          title: 'tt',
          body: 'body',
        },
        {
          id: 2,
          userId: '22',
          title: 'tt3',
          body: 'body323',
        },
      ],
      // posts: [],
    };
  },
  mounted() {
    this.fetchData();
  },
  computed: {},
  methods: {
    async fetchData() {
      const loading = this.$vs.loading();
      const posts = await fetchPosts();
      loading.close();
      console.log(posts);
    },
    // openLoading() {
    //   const loading = this.$vs.loading();
    //   setTimeout(() => {
    //     loading.close();
    //   }, 3000);
    // },
  },
};
</script>

<style></style>
