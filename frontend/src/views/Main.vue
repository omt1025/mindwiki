<template>
  <v-app>
    <div>
      <top-navigation></top-navigation>
      <main-home v-if="bottomNav === 'home'"></main-home>
      <main-activity v-else-if="bottomNav === 'activity'"></main-activity>
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
import MainActivity from '../components/main/activity/MainActivity.vue';

export default {
  name: 'Main',
  components: {
    TopNavigation,
    MainHome,
    BottomNavi,
    MainActivity,
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
