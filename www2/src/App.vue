<template>
   <div id="app">
      <NavBarTop msg="Welcome to Your Vue.js App" />
   </div>
</template>

<script>
import NavBarTop from "./components/NavBarTop.vue";
export default {
   name: "App",
   components: {
      NavBarTop
   }
};
</script>
