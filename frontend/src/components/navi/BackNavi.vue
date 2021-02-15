<template>
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-toolbar-title class="flex text-left" font-family>{{ title }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-icon v-if="title !== '설정'" @click="backPage" color="#a64bf4">mdi-check</v-icon>
    </v-toolbar>
  </v-card>
</template>

<script>
export default {
  name: 'BackPageNavi',
  props: {
    title: null,
  },
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
  padding-left: 25px;
}
// .v-toolbar__title {
//   font-family: 'Black Han Sans';
// }
.closenavi {
  max-height: 56px;
  height: 56px;
}
</style>
