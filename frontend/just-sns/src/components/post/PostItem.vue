<template>
  <div>
    <b-card :title="post.title" :sub-title="String(post.userId)">
      <b-card-text>{{ post.body }}</b-card-text>
    </b-card>
    <br />
  </div>
</template>

<script>
export default {
  props: {
    post: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style></style>
