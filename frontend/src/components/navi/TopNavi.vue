<template>
  <v-card id="topnavi" v-if="bottomNav !== 'search'">
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

  <!-- 검색 Tab -->
  <v-card v-else>
    <v-toolbar id="navi_shadow" >
      <v-card-text>
        <v-form>
          <!-- 프로필 검색 탭 눌렀을 때 -->
          <v-text-field
            v-if="this.mainTab === 0"
            outlined
            clearable
            placeholder="프로필 검색"
            type="text"
            v-model="search"
          >
            <template v-slot:append>
              <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
            </template>
          </v-text-field>

          <!-- 타이틀 검색 탭 눌렀을 때 -->
          <v-text-field
            v-else-if="this.mainTab === 1"
            outlined
            clearable
            placeholder="타이틀 검색"
            type="text"
            v-model="search"
          >
            <template v-slot:append>
              <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
            </template>
          </v-text-field>

          <!-- 해시태그 검색 탭 눌렀을 때 -->
          <v-text-field
            v-else
            outlined
            clearable
            placeholder="해시태그 검색"
            type="text"
            v-model="search"
          >
            <template v-slot:append>
              <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
            </template>
          </v-text-field>
        </v-form>
      </v-card-text>

      <template v-slot:extension>
        <v-tabs v-model="mainTab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>
          <v-tab v-for="(item, index) in search_items" :key="item" @click="setTab(index)">
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
      search_items: ['프로필', '타이틀', '해시태그'],
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
.v-card__text {
  position: fixed;
  bottom: 0px;
  right: 0px;
}
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Noto%20Sans%20KR');

.v-tab {
  font-family: 'Noto Sans KR';
}
</style>
