<template>
  <div>
    <div v-if="isLoading" >Loading posts...</div>
    <div v-else>
      <div v-if="posts.length === 0"> No posts are here... yet.</div>
      <div v-for="post in posts" v-bind:key="post.id">
        {{post.subject}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            posts: [],
            isLoading: true
        }
    },
    mounted() {
        this.fetchPosts();
    },
    computed: {
    },
    methods: {
        fetchPosts() {
            if (this.isLoading) {
                this.isLoading = false;
            }
            // TODO: test
            this.posts = [
                {
                    id: 1,
                    subject: "test 1",
                    author: "T"
                },
                {
                    id: 2,
                    subject: "test 2",
                    author: "ABC"
                }
            ]
        }
    }
}
</script>

<style>

</style>