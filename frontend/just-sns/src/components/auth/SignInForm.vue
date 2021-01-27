<template>
  <b-form @submit.prevent="onSubmit">
    <b-form-group
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="ID"
      label-for="id"
      :invalid-feedback="'아이디를 입력해주세요.'"
      ><b-form-input
        id="id"
        v-model="form.id"
        :state="this.form.id.length > 0"
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      id="fieldset-1"
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="비밀번호"
      label-for="password"
      :invalid-feedback="'비밀번호를 입력해주세요.'"
      ><b-form-input
        id="password"
        type="password"
        v-model="form.password"
        :state="this.form.password.length > 0"
        trim
      ></b-form-input>
    </b-form-group>
    <b-container>
      <b-button type="submit" variant="primary" :disabled="!enableSubmit"
        >Login</b-button
      >
    </b-container>
  </b-form>
</template>

<script>
import { signIn } from '@/api/auth';

export default {
  data() {
    return {
      form: {
        id: '',
        password: '',
      },
    };
  },
  computed: {
    enableSubmit() {
      if (this.form.id && this.form.password) {
        return true;
      }
      return false;
    },
  },
  methods: {
    async onSubmit() {
      if (!this.enableSubmit) {
        return;
      }
      const payload = {
        username: this.form.id,
        password: this.form.password,
      };
      try {
        const { data } = await signIn(payload);
        this.$store.commit('setUsername', this.form.id);
        this.$store.commit('setToken', data);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
