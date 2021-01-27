<template>
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <!-- <v-spacer></v-spacer> -->
      <!-- <v-toolbar-title>Mind Wiki</v-toolbar-title> -->
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>

      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>

      <template v-slot:extension>
        <v-tabs v-model="tab" grow color="#a64bf4" id="topnavi">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab v-for="item in items" :key="item">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item>
        <v-card flat>
          <LivePopularMind />
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <FavoriteMind />
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <MyWiki />
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import LivePopularMind from '@/components/main/home/LivePopularMind.vue';
import FavoriteMind from '@/components/main/home/FavoriteMind.vue';
import MyWiki from '@/components/main/home/MyWiki.vue';

export default {
  name: 'TopNavigation',
  components: {
    LivePopularMind,
    FavoriteMind,
    MyWiki,
  },
  data() {
    return {
      tab: null,
      items: ['실시간', '관심태그', 'My Wiki'],
    };
  },
  methods: {
    menu: function() {
      this.$router.push('/main/menu');
    },
  },
};
</script>

<style>
#text {
  /* color: green; */
  color: #a64bf4;
}
.v-card > .v-toolbar {
  position: sticky;
  top: 0;
  z-index: 999;
}
#topnavi {
  box-shadow: none;
}
#navi_shadow {
  box-shadow: 0px 2px 4px -1px rgb(0 0 0 / 3%), 0px 4px 5px 0px rgb(0 0 0 / 3%),
    0px 1px 10px 0px rgb(0 0 0 / 3%);
}
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Noto%20Sans%20KR');

.v-tab {
  font-family: 'Noto Sans KR';
}
</style>
