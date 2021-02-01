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
        <span>활동</span>
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
// created() {

// },
export default {
  name: 'BottomNavi',
  data() {
    return {
      value: '',
    };
  },
  methods: {
    setState(value) {
      this.value = value;
      // console.log(this.value);
      this.$store.dispatch('setBottomNav', this.value);
      // console.log('store getter : ' + this.$store.getters.bottomNav);

      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
    },
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