<template>
  <div class="center examplex">
    <vs-navbar center-collapsed>
      <template #left>
        <img class="logo" src="@/assets/logo.png" alt="logo" />
      </template>
      <template #right>
        <!-- <vs-switch v-model="active2">
          <template #circle>
            <i v-if="!active2" class="bx bxs-moon"></i>
            <i v-else class="bx bxs-sun"></i>
          </template>
        </vs-switch> -->
        <template v-if="isLogin">
          <vs-navbar-item>{{ loginUsername }}</vs-navbar-item>
          <vs-button flat @click="logout">Logout</vs-button>
        </template>
        <template v-else>
          <vs-button flat>Login</vs-button>
        </template>
      </template>
    </vs-navbar>
  </div>
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
  width: 64px;
  height: 64px;
  border-radius: 50%;
}
</style>
