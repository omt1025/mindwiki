<template>
  <v-app>
    <div>
      <top-navi></top-navi>

      <main-home v-if="bottomNav === 'home'"></main-home>
      <main-search v-else-if="bottomNav === 'search'"></main-search>
      <main-activity v-else-if="bottomNav === 'activity'"></main-activity>
      <!-- <main-make-mind v-else-if="bottomNav === 'mindmap'"></main-make-mind> -->
      <!-- <main-create-mind v-else-if="bottomNav === 'mindmap'"></main-create-mind> -->
      <main-profile v-else-if="bottomNav === 'profile'"></main-profile>

      <bottom-navi v-if="bottomNav !== 'follow'"></bottom-navi>
    </div>
    <div></div>
  </v-app>
</template>

<script>
import TopNavi from '../components/navi/TopNavi.vue';
import MainHome from '../components/main/home/MainHome.vue';
import BottomNavi from '../components/navi/BottomNavi.vue';
// import MainMakeMind from '../components/main/makemind/MakeMind.vue';
// import MainCreateMind from '../components/main/makemind/MainCreateMind.vue';
import MainActivity from '../components/main/activity/MainActivity.vue';
import MainSearch from '../components/main/search/MainSearch.vue';
import MainProfile from '../components/main/profile/MainProfile.vue';

import { mapGetters } from 'vuex';

export default {
  name: 'Main',
  components: {
    TopNavi,
    MainHome,
    BottomNavi,
    // MainMakeMind,
    // MainCreateMind,
    MainActivity,
    MainProfile,
    MainSearch,
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
