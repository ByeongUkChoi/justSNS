<template>
  <b-navbar type="dark" variant="dark">
    <b-navbar-nav>
      <b-nav-item to="/">Home</b-nav-item>
    </b-navbar-nav>

    <b-navbar-nav class="ml-auto">
      <b-button size="lg" variant="dark" to="/write">
        <b-icon icon="plus-circle" aria-label="write"></b-icon>
      </b-button>

      <b-nav-item-dropdown text="User" right>
        <b-dropdown-item href="#">Account</b-dropdown-item>
        <b-dropdown-item href="#">Settings</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-navbar-nav>
    <!-- plus-circle -->
  </b-navbar>
</template>

<script>
export default {
  name: 'AppHeader',
};
</script>
