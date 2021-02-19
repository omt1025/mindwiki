<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 로그아웃 구현
    * 생성일자 : 2021-02-02
    * 최종수정일자 : 2021-02-16
  -->
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>
      <!-- 기존 메뉴 -->
      <v-menu>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="menu in menus" :key="menu" @click="() => {}">
            <v-list-item-title v-if="menu === '로그아웃'" @click="logout">
              <v-icon>mdi-logout</v-icon>
              {{ menu }}
            </v-list-item-title>
            <v-list-item-title v-else-if="menu === '비밀번호 변경'" @click="goPasswordChange">
              <v-icon>mdi-lock-reset</v-icon>
              {{ menu }}
            </v-list-item-title>
            <v-list-item-title v-else @click="goWithdraw">
              <v-icon>mdi-account-remove</v-icon>
              {{ menu }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <!-- 홈 Tab -->
      <template v-if="bottomNav === 'home'" v-slot:extension>
        <v-tabs v-model="mainTab" grow color="#a64bf4" id="topnavi">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>
          <v-tab v-for="(item, index) in items" :key="item" @click="setTab(index)">
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
      activity_items: ['내 활동 알림'],
      menus: ['로그아웃', '비밀번호 변경', '회원탈퇴'],
      jwt: localStorage.getItem('jwt'),
    };
  },
  methods: {
    menu1: function() {
      this.$router.push('/main/menu');
    },
    setTab(index) {
      this.mainTab = index;
      this.$store.dispatch('setMainTab', this.mainTab);
    },
    onInputKeyword: function(event) {
      this.$emit('input-change', event.target.value);
    },
    logout() {
      // 탭 초기화(재사용 위해)
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');
      this.$store.dispatch('setMessage', null);
      // 로그아웃 처리
      this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
    },
    // 비밀번호 변경 페이지로 이동
    goPasswordChange() {
      this.$router.push('/main/menu/passwordchange');
    },
    // 회원탈퇴로 이동
    goWithdraw() {
      this.$router.push('/main/menu/withdraw');
    },
  },
  // 새로고침 후 상단 탭 유지
  updated() {
    this.$nextTick(() => {
      this.mainTab = this.$store.getters.mainTab;
    });
  },
  created() {
    this.$nextTick(() => {
      this.mainTab = this.$store.getters.mainTab;
    });
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
