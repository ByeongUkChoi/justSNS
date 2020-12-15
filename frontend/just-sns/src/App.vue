<template>
  <div id="app">
    <app-header></app-header>
    <b-container>
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
import AppHeader from '@/components/AppHeader';

export default {
  name: 'App',
  components: {
    AppHeader
  }
}
</script>
