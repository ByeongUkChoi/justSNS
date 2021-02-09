<template>
  <div class="center content-inputs">
    <vs-row class="center" justify="center">
      <vs-input label-placeholder="name" v-model="name">
        <template v-if="validName" #message-success> Email Valid </template>
        <template v-else-if="name !== ''" #message-danger>
          Email Invalid
        </template>
      </vs-input>
    </vs-row>
    <vs-row class="center" justify="center">
      <vs-input label-placeholder="ID" v-model="id" />
    </vs-row>
    <vs-row class="center" justify="center">
      <vs-input label-placeholder="password" v-model="password" />
    </vs-row>
    <vs-row class="center" justify="center">
      <vs-input label-placeholder="password check" v-model="passwordCheck" />
    </vs-row>
    <vs-row class="center" justify="center">
      <vs-button @click="onSubmit">Sign Up</vs-button>
    </vs-row>
  </div>
</template>
<script>
export default {
  data: () => ({
    name: '',
    id: '',
    password: '',
    passwordCheck: '',
  }),
  methods: {
    async onSubmit() {
      console.log('on sumbit');
    },
  },
};
</script>

<style scoped>
.center.content-inputs {
  background-color: white;
}
</style>
