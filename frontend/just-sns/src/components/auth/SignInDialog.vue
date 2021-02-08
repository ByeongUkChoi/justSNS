<template>
  <vs-dialog overflow-hidden blur v-model="showDialog">
    <template #header>
      <h4 class="not-margin">Welcome to <b>Just SNS</b></h4>
    </template>

    <div class="con-form content-inputs">
      <vs-input v-model="form.id" placeholder="ID">
        <template #icon>
          <i class="bx bx-user"></i>
        </template>
      </vs-input>
      <vs-input v-model="form.password" placeholder="Password">
        <template #icon>
          <i class="bx bx-lock"></i>
        </template>
      </vs-input>
      <!-- <span class="vs-input__icon--after">
          <i v-if="!hasVisiblePassword" class="bx bx-show-alt"></i>
      </span> -->
      <div class="flex">
        <vs-checkbox v-model="remember">Remember me</vs-checkbox>
        <a href="#">Forgot Password?</a>
      </div>
    </div>

    <template #footer>
      <div class="footer-dialog">
        <vs-button @click="onSubmit" block> Sign In </vs-button>

        <div class="new">New Here? <a href="#">Create New Account</a></div>
      </div>
    </template>
  </vs-dialog>
</template>
<script>
import { signIn } from '@/api/auth';

export default {
  props: {
    active: {
      type: Boolean,
      default: false,
    },
  },
  data: () => ({
    form: {
      id: '',
      password: '',
    },
    remember: false,
  }),
  computed: {
    showDialog: {
      get() {
        return this.active;
      },
      set(value) {
        if (value === false) {
          this.$emit('close');
        }
      },
    },
    enableSubmit() {
      return this.form.id && this.form.password;
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
        // TODO: time close
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
