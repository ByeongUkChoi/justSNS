<template>
  <div>
    <div v-if="isLoading" >Loading posts...</div>
    <div v-else>
      <div v-if="posts.length === 0"> No posts are here... yet.</div>
      <div v-for="post in posts" v-bind:key="post.id">
        {{post.title}}
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

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
            var vm = this;
            // TODO: 예시
            axios.get('https://jsonplaceholder.typicode.com/posts')
                .then(response => vm.posts = response.data)
                .catch(error => console.log(error))
        }
    }
}
</script>

<style>

</style>