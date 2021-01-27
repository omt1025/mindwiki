<template>
  <v-app>
    <v-app-bar shift fixed height="58.44px" elevation="1" color="#fff">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center" font-family>메뉴</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'BackPageNavi',
  methods: {
    backPage: function() {
      this.$router.push('/main');
    },
  },
};
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Fredoka%20One');
@import url('https://fonts.googleapis.com/css?family=Black+Han+Sans:400');

.v-toolbar__title {
  font-family: 'Fredoka One';
}
// .v-toolbar__title {
//   font-family: 'Black Han Sans';
// }
</style>
