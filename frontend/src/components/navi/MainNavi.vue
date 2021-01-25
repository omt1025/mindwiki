<template>
  <v-card>
    <v-toolbar>
      <v-spacer></v-spacer>
      <!-- <v-toolbar-title>Mind Wiki</v-toolbar-title> -->
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>

      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>

      <template v-slot:extension>
        <v-tabs v-model="tab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab v-for="item in items" :key="item">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <!-- <v-tab-item
        v-for="item in items"
        :key="item"
      >
      </v-tab-item> -->
      <v-tab-item>
        <v-card flat>
          <Card />
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <Card />
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <Glide />
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import Card from '@/components/common/Card.vue';
import Glide from '@/components/common/Glide.vue';

export default {
  name: 'MainNavi',
  components: {
    Card,
    Glide,
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
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Noto%20Sans%20KR');

.v-tab {
  font-family: 'Noto Sans KR';
}
</style>
