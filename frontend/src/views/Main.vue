<template>
  <v-app>
    <div>
      <top-navigation></top-navigation>

      <main-home v-if="bottomNav === 'home'"></main-home>
      <search-bar v-else-if="bottomNav === 'search'"></search-bar>
      <main-activity v-else-if="bottomNav === 'activity'"></main-activity>
      <mind-map-detail v-else-if="bottomNav === 'mindmap'"></mind-map-detail>

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
import SearchBar from '../components/common/SearchBar.vue';
import MindMapDetail from './MindMapDetail.vue';

export default {
  name: 'Main',
  components: {
    TopNavigation,
    MainHome,
    BottomNavi,
    MainActivity,
    SearchBar,
    MindMapDetail,
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
