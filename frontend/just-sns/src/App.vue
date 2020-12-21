<template>
  <div id="app">
    <app-header></app-header>
    <br>
    <router-view></router-view>
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
