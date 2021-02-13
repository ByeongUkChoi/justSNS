<template>
  <div>
    <vs-card>
      <template #title>
        <h3>{{ post.title }} - {{ String(post.userId) }}</h3>
      </template>
      <template #img>
        <img src="/foto5.png" alt="" />
      </template>
      <template #text>
        <p>{{ post.body }}</p>
      </template>
      <template #interactions>
        <vs-button danger icon>
          <i class="bx bx-heart"></i>
        </vs-button>
        <vs-button class="btn-chat" shadow primary>
          <i class="bx bx-chat"></i>
          <span class="span"> 54 </span>
        </vs-button>
      </template>
    </vs-card>
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
