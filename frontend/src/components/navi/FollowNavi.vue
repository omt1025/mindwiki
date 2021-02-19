<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 팔로우 수정
    * 생성일자 : 2021-02-02
    * 최종수정일자 : 2021-02-19
  -->
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-toolbar-title class="flex text-left" font-family>팔로우 목록</v-toolbar-title>
      <v-spacer></v-spacer>
      <template v-slot:extension>
        <v-tabs v-model="followTab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>
          <v-tab v-for="(item, index) in items" :key="item" @click="setTab(index)">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="followTab">
      <v-tab-item>
        <v-card flat>
          <FollowerList />
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <FollowingList />
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
import FollowerList from '@/components/common/FollowerList.vue';
import FollowingList from '@/components/common/FollowingList.vue';

export default {
  name: 'FollowNavi',
  components: {
    FollowerList,
    FollowingList,
  },
  computed: {
    ...mapGetters(['nickName', 'userId']),
  },
  data() {
    return {
      followTab: '0',
      items: ['팔로워', '팔로잉'],
    };
  },
  methods: {
    // 이전 페이지로 이동
    backPage: function() {
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'profile');
      this.$router.push('/main');
    },
    // 팔로우 버튼
    setTab(index) {
      this.followTab = index;
      this.$store.dispatch('setFollowTab', this.followTab);
    },
  },
  // 새로고침 시 상단 탭(팔로워 / 팔로잉) 유지
  updated() {
    this.$nextTick(() => {
      this.followTab = this.$store.getters.followTab;
    });
  },
};
</script>
<style scoped>
.v-toolbar__title {
  font-family: 'Fredoka One';
}
</style>
