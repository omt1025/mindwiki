<template>
  <v-app>
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center" font-family>메뉴</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
  </v-app>
</template>

<script>
export default {
  name: 'BackPageNavi',
  methods: {
    backPage: function() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
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
