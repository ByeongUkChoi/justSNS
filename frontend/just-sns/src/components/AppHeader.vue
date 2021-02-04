<template>
  <vs-navbar>
    <template #left>
      <img class="logo" src="@/assets/logo.png" alt="logo" />
    </template>
    <template #right>
      <template v-if="isLogin">
        <vs-navbar-item>{{ loginUsername }}</vs-navbar-item>
        <vs-button flat @click="logout">Logout</vs-button>
      </template>
      <template v-else>
        <vs-button flat to="/sign-in">Login</vs-button>
      </template>
    </template>
  </vs-navbar>
</template>
<script>
export default {
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
  margin-bottom: 10px;
}
</style>
