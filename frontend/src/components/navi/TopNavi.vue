<template>
  <v-app>
    <v-app-bar app color="primary" dark>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center"></v-toolbar-title>
      <v-spacer></v-spacer>
      
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'TopNavi',
}
</script>

<style>

</style>