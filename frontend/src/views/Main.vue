<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 알림탭 navi변경
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-18
  -->
  <v-app>
    <div>
      <!-- Top Navigation -->
      <top-navi v-if="bottomNav !== 'activity'"></top-navi>
      <back-navi v-else :title="title" v-on:backbtn="backPage"> </back-navi>

      <!-- Content Navigation -->
      <!-- 홈 탭 -->
      <main-home v-if="bottomNav === 'home'"></main-home>
      <!-- 검색 탭 -->
      <main-search v-else-if="bottomNav === 'search'"></main-search>
      <!-- 알림 탭 -->
      <main-activity v-else-if="bottomNav === 'activity'"></main-activity>
      <!-- 프로필 탭 -->
      <main-profile v-else-if="bottomNav === 'profile'"></main-profile>

      <!-- Bottom Navigation -->
      <bottom-navi v-if="bottomNav !== 'follow'"></bottom-navi>
    </div>
    <div></div>
  </v-app>
</template>

<script>
import TopNavi from '../components/navi/TopNavi.vue';
import MainHome from '../components/main/home/MainHome.vue';
import BottomNavi from '../components/navi/BottomNavi.vue';
import MainActivity from '../components/main/activity/MainActivity.vue';
import MainSearch from '../components/main/search/MainSearch.vue';
import MainProfile from '../components/main/profile/MainProfile.vue';

import { mapGetters } from 'vuex';
import BackNavi from '../components/navi/BackNavi.vue';

export default {
  name: 'Main',
  components: {
    TopNavi,
    MainHome,
    BottomNavi,
    MainActivity,
    MainProfile,
    MainSearch,
    BackNavi,
  },
  data() {
    return {
      title: '알림',
    };
  },
  computed: {
    ...mapGetters(['bottomNav']),
  },
  methods: {
    backPage: function() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
      // 페이지 변경이 아니라서 다시 로드 처리
      window.location.reload();
      window.close();

      // this.$router.push('/main');
    },
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
