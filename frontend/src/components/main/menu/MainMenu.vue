<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 메인메뉴 수정하기
    * 생성일자 : 2021-01-21
    * 최종수정일자 : 2021-02-17
 -->
  <v-app>
    <!-- <MenuNavi /> -->
    <back-navi :title="title" v-on:backbtn="backPage"></back-navi>
    <v-row align="center">
      <v-col>
        <div class="my-5">
          <span></span>
          <v-spacer></v-spacer>
        </div>
        <!-- 비회원일 때 보여줌 -->
        <div v-bind:jwt="jwt" v-if="jwt == 'undefined'">
          <!-- <div v-if="getJWT!='undefined'"> -->
          <div class="my-5">
            <v-btn
              x-large
              width="500px"
              to="/login"
              depressed
              color="white"
              id="no-background-hover"
              :ripple="false"
            >
              <v-icon>mdi-login</v-icon>
              <span>로그인</span>
              <v-spacer></v-spacer>
            </v-btn>
          </div>
          <div class="my-5">
            <v-btn
              x-large
              width="500px"
              depressed
              color="white"
              id="no-background-hover"
              :ripple="false"
            >
              <v-icon>mdi-account-plus</v-icon>
              <span>회원가입</span>
              <v-spacer></v-spacer>
            </v-btn>
          </div>
        </div>
        <!-- 회원일 때 보여줌 -->
        <div v-else>
          <div class="my-5">
            <v-btn
              x-large
              width="500px"
              @click.native="logout"
              depressed
              color="white"
              id="no-background-hover"
              :ripple="false"
            >
              <v-icon>mdi-logout</v-icon>
              <span>로그아웃</span>
              <v-spacer></v-spacer>
            </v-btn>
          </div>
          <div class="my-5">
            <v-btn
              x-large
              width="500px"
              to="/main/menu/passwordchange"
              depressed
              color="white"
              id="no-background-hover"
              :ripple="false"
            >
              <v-icon>mdi-lock-reset</v-icon>
              <span id="sp">비밀번호 변경</span>
              <v-spacer></v-spacer>
              <v-spacer></v-spacer>
            </v-btn>
          </div>
          <div class="my-5">
            <v-btn
              x-large
              width="500px"
              depressed
              color="white"
              id="no-background-hover"
              to="menu/withdraw"
              :ripple="false"
            >
              <v-icon>mdi-account-remove</v-icon>
              <span>회원탈퇴</span>
              <v-spacer></v-spacer>
            </v-btn>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import BackNavi from '../../navi/BackNavi.vue';

export default {
  name: 'MainMenu',
  components: {
    BackNavi,
  },
  data: () => ({
    title: '설정',
    bellClick: true,
    accountClick: true,
    color: 'white',
    logoutcheck: '',
    jwt: localStorage.getItem('jwt'),
  }),
  methods: {
    logout() {
      // 탭 초기화(재사용 위해)
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');
      this.$store.dispatch('setMessage', null);
      // 로그아웃 처리
      this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
    },
    backPage: function() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
      this.$router.push('/main');
    },
  },
};
</script>

<style scoped>
.container--fluid {
  background-color: white;
}
span {
  display: block;
  margin-left: 20px;
}
#sp {
  margin-right: 40px;
}
#sp1 {
  margin-right: 68px;
}
#sp2 {
  margin-right: 42px;
}
#sp3 {
  margin-right: 22px;
}
#sp4 {
  margin-right: 5px;
}
#sp5 {
  margin-right: 32px;
}
</style>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Black+Han+Sans:400');
@import url('https://fonts.googleapis.com/css?family=Noto%20Sans%20KR');
@import url('https://fonts.googleapis.com/css?family=Poor+Story:400');

#no-background-hover::before {
  background-color: transparent !important;
}
#app {
  input {
    background: #fff;
  }
}
// span {
//   font-family: "Black Han Sans";
// }
// span {
//   font-family: "Noto Sans KR";
// }
span {
  font-family: 'Poor Story';
}
</style>
