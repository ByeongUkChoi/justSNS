<template>
  <vs-navbar>
    <template #left>
      <router-link to="/">
        <img class="logo" src="@/assets/logo.png" alt="logo" />
      </router-link>
    </template>
    <template #right>
      <template v-if="isLogin">
        <vs-navbar-item>{{ loginUsername }}</vs-navbar-item>
        <vs-button flat @click="logout">Logout</vs-button>
      </template>

      <template v-else>
        <vs-button @click="showSignInDialog = true" flat>Sign In</vs-button>
        <SignInDialog
          :active="showSignInDialog"
          @close="showSignInDialog = false"
        ></SignInDialog>
        <vs-button to="/sign-up" :disabled="$route.path == '/sign-up'"
          >Sign Up</vs-button
        >
      </template>
    </template>
  </vs-navbar>
</template>
<script>
import SignInDialog from '@/components/auth/SignInDialog';

export default {
  components: {
    SignInDialog,
  },
  data() {
    return {
      showSignInDialog: false,
    };
  },
  computed: {
    isLogin() {
      return this.$store.getters.isLogin;
    },
    loginUsername() {
      return this.$store.state.username;
    },
  },
  methods: {
    logout() {
      this.$store.commit('clearAuth');
      this.$router.push('sign-in');
    },
  },
};
</script>

<style scoped>
.logo {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}
.vs-navbar-content {
  position: relative;
  /* margin-bottom: 10px; */
}
</style>
