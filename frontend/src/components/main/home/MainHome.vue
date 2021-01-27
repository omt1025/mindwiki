<template>
  <v-app>
    <div v-if="homeTab == 0">
      <v-card flat>
        <LivePopularMind />
      </v-card>
    </div>
    <div v-if="homeTab == 1">
      <v-card flat>
        <FavoriteMind />
      </v-card>
    </div>
    <div v-if="homeTab == 2">
      <v-card flat>
        <MyWiki />
      </v-card>
    </div>
  </v-app>

  <!-- <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <template>
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
  </v-card> -->
</template>

<script>
import LivePopularMind from '@/components/main/home/LivePopularMind.vue';
import FavoriteMind from '@/components/main/home/FavoriteMind.vue';
import MyWiki from '@/components/main/home/MyWiki.vue';

import { mapGetters } from 'vuex';

export default {
  components: {
    LivePopularMind,
    FavoriteMind,
    MyWiki,
  },
  computed: {
    ...mapGetters(['homeTab']),
  },
  data() {
    return {
      tab: null,
      items: ['실시간', '관심태그', 'My Wiki'],
    };
  },
};
</script>

<style></style>
