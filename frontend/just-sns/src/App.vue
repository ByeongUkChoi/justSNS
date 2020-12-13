<template>
  <div id="app">
    <app-header></app-header>
    <b-container>
      <HelloWorld msg="Welcome to Your Vue.js App"/>
    </b-container>
  </div>
</template>

<script>
import AppHeader from '@/components/AppHeader';
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    AppHeader,
    HelloWorld
  }
}
</script>
