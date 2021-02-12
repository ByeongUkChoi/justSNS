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
import { duplicateCheck, signUp } from '@/api/auth';

export default {
  data: () => ({
    form: {
      name: '',
      id: '',
      password: '',
      passwordCheck: '',
    },
    isDuplicateId: false,
  }),
  computed: {
    enableSubmit() {
      if (this.name && this.id && this.password && this.passwordCheck) {
        return true;
      }
      return false;
    },
  },
  methods: {
    async checkDuplicateId() {
      try {
        await duplicateCheck(this.form.id);
        this.isDuplicateId = false;
      } catch (error) {
        this.isDuplicateId = true;
      }
    },
    async onSubmit() {
      if (!this.enableSubmit) {
        return;
      }
      const payload = {
        username: this.form.id,
        password: this.form.password,
        name: this.form.name,
        email: this.form.email,
      };
      try {
        await signUp(payload);
        alert('회원가입이 완료되었습니다.');
        this.$router.push('/sign-in');
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.center.content-inputs {
  background-color: white;
}
</style>
