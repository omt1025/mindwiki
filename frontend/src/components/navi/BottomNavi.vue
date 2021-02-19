<template>
  <v-app>
    <v-bottom-navigation v-model="value" color="#a64bf4" grow shift fixed>
      <v-btn value="home" @click="setState('home')">
        <span>홈</span>
        <v-icon>mdi-home-outline</v-icon>
      </v-btn>
      <v-btn value="search" @click="setState('search')">
        <span>검색</span>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <!-- to="/main/MindMapDetail" -->
      <v-btn value="mindmap" @click="setState('mindmap')">
        <span>생성</span>
        <v-icon>mdi-head-plus-outline</v-icon>
      </v-btn>
      <v-btn value="activity" @click="setState('activity')">
        <span>알림</span>
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>
      <!-- to="/main/mylibrary" -->
      <v-btn value="profile" @click="setState('profile')">
        <span>프로필</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
export default {
  name: 'BottomNavi',
  data() {
    return {
      value: '',
    };
  },
  methods: {
    setState(value) {
      // 마인드 생성은 페이지 이동이라 home으로 초기화 시켜주기
      if (value === 'mindmap') this.value = 'home';
      else this.value = value;

      this.$store.dispatch('setBottomNav', this.value);
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)

      // 마인드 버튼을 눌렀으면, 마인드 생성 페이지로 이동시키기
      if (value === 'mindmap') this.$router.push(`/main/createmind`);
    },
  },
  // 새로고침 후 하단 네비게이션 유지
  updated() {
    this.$nextTick(() => {
      this.value = this.$store.getters.bottomNav;
    });
  },
};
</script>

<style scoped>
.v-item-group.v-bottom-navigation .v-btn {
  background-color: transparent;
  border-radius: 0;
  box-shadow: none;
  flex: 0 1 auto;
  font-size: 0.75rem;
  height: inherit;
  max-height: 168px;
  min-width: auto;
  position: relative;
  text-transform: none;
}
</style>
