<template>
  <div>
    <div v-if="isLoading" class="text-center">
        <b-spinner label="Spinning"></b-spinner>
    </div>
    <div v-else-if="posts.length === 0">
        No posts are here... yet.
    </div>
    <div v-else>
        <div v-for="post in posts" v-bind:key="post.id">
            <b-card :title="post.title" :sub-title="String(post.userId)">
                <b-card-text>{{post.body}}</b-card-text>
            </b-card>
            <br>
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
            // TODO: 예시
            axios.get('https://jsonplaceholder.typicode.com/posts')
                .then(response => this.posts = response.data)
                .catch(error => console.log(error))
                .finally(() => {
                    if(this.isLoading) {
                        this.isLoading = false
                    }
                })
        }
    }
}
</script>

<style>

</style>