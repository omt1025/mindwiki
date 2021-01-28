<template>
  <div id="app">
    <v-app-bar id="navi_shadow" shift fixed color="white" light>
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">마인드맵 생성</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>

    <mindmap :nodes="nodes" :connections="connections" :editable="true" />
  </div>
</template>

<script>
import map from '../map.js';

export default {
  name: 'app',
  components: {},
  data() {
    return { ...map };
  },
};
</script>

<style scoped>
#app {
  background-color: #f1f5f8;
}
</style>
