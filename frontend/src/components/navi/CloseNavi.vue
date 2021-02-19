<template>
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <v-toolbar-title class="flex text-left" font-family>{{ title }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-icon @click="backPage">mdi-close</v-icon>
    </v-toolbar>
  </v-card>
</template>

<script>
export default {
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
.closenavi {
  max-height: 56px;
  height: 56px;
}
</style>
