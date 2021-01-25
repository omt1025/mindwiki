<template>
  <v-app>
    <v-bottom-navigation v-model="value" color="#a64bf4" grow shift fixed>
      <v-btn>
        <span>홈</span>
        <v-icon>mdi-home-outline</v-icon>
      </v-btn>

      <v-btn>
        <span>검색</span>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn>
        <span>생성</span>
        <v-icon>mdi-head-plus-outline</v-icon>
      </v-btn>

      <v-btn to="/main/activity">
        <span>활동</span>
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>

      <v-btn>
        <span>내 책장</span>
        <v-icon>mdi-bookshelf</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
export default {
  name: 'BottomNavi',
  data: () => ({
    value: 0,
  }),
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
