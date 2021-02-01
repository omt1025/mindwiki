<template>
  <div id="app">
    <v-app-bar id="navi_shadow" shift fixed color="white" light>
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
    </v-app-bar>
    <div>
      <mindmap :nodes="nodes" :connections="connections" :editable="true" />
      <v-btn
        id="btn"
        depressed
        color="primary"
      >
        노드생성
      </v-btn>
    </div>
  </div>
</template>

<script>
import map from '../map.js';

export default {
  name: 'app',
  components: {},
  data() {
    return {
      ...map,
      newnode: {
        text: '',
        url: '',
        category: '',
        color: '',
      },
      newconnection: {
        source: '',
        target: '',
      }
      };
  },
};
</script>

<style scoped>
#app {
  background-color: #f1f5f8;
}
#btn {
  z-index: 1;
}
</style>
