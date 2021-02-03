<template>
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow" >
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>

      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>

      <!-- 홈 Tab -->
      <template v-if="bottomNav === 'home'" v-slot:extension>
        <v-tabs v-model="mainTab" grow color="#a64bf4" id="topnavi">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab v-for="(item, index) in items" :key="item" @click="setTab(index)">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>

      <!-- 활동 Tab -->
      <template v-else-if="bottomNav === 'activity'" v-slot:extension>
        <v-tabs v-model="mainTab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab v-for="(item, index) in activity_items" :key="item" @click="setTab(index)">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'TopNavi',
  components: {},
  computed: {
    ...mapGetters(['bottomNav']),
  },
  data() {
    return {
      mainTab: '0',
      items: ['실시간', '관심태그', 'My Wiki'],
      activity_items: ['알림', '메세지'],
    };
  },
  methods: {
    menu: function() {
      this.$router.push('/main/menu');
    },
    setTab(index) {
      this.mainTab = index;
      // console.log(this.mainTab);
      this.$store.dispatch('setMainTab', this.mainTab);
      // console.log('store getter : ' + this.$store.getters.mainTab);
    },
    onInputKeyword: function(event) {
      this.$emit('input-change', event.target.value);
      console.log('test')
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
  position: sticky;
  top: 0;
  z-index: 999;
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
