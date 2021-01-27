<template>
  <v-app>
    <div>
      <top-navigation></top-navigation>
      <main-home v-if="bottomNav === 'home'"></main-home>
      <bottom-navi></bottom-navi>
    </div>
    <div></div>
  </v-app>
</template>

<script>
// import MainNavi from '@/components/navi/MainNavi.vue';
import TopNavigation from '../components/navi/TopNavigation.vue';
import MainHome from '../components/main/home/MainHome.vue';
import BottomNavi from '../components/navi/BottomNavi.vue';

import { mapGetters } from 'vuex';

export default {
  name: 'Main',
  components: {
    TopNavigation,
    MainHome,
    BottomNavi,
  },
  computed: {
    ...mapGetters(['bottomNav']),
  },
};
</script>

<style>
html,
body,
.v-application,
.v-application--wrap {
  min-height: -webkit-fill-available;
}
#nav {
  padding: 0;
}
</style>
