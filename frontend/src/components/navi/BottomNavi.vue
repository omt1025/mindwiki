<template>
  <v-app>
    <v-bottom-navigation v-model="value" color="green" shift fixed justify="space" height="58.44px">
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
