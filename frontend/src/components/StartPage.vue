<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : sns로그인시 가입된 계정이 아니면, 회원가입 페이지로 이동
    * 생성일자 : 2021-01-20
    * 최종수정일자 : 2021-02-08
 -->

  <div id="bg">
    <!-- 그라데이션 배경 -->
    <img src="../assets/images/user/bg-01.jpg" alt="" />

    <v-app>
      <v-row justify="center" align="center" style="min-height: 160px;">
        <v-col class="shrink">
          <!-- 마인드위키 로고 -->
          <v-img
            id="logo"
            src="../assets/images/mindwiki_logo-01.png"
            @click="expand = !expand"
            class="ma-8"
          ></v-img>

          <div id="bt">
            <!-- 회원가입 button -->
            <transition name="slide-fade">
              <v-btn
                v-if="expand"
                to="/signup"
                height="50px"
                width="300px"
                class="fb-btn font-weight-bold mx-auto ma-4"
                :ripple="false"
                id="no-background-hover"
                color="#ffffffbb"
                rounded
              >
                회원가입
              </v-btn>
            </transition>

            <!-- 로그인 button -->
            <transition name="slide-fade2">
              <v-btn
                v-if="expand"
                @click="goLogin"
                height="50px"
                width="300px"
                class="font-weight-bold mx-auto ma-4"
                :ripple="false"
                id="no-background-hover"
                color="#ffffffbb"
                rounded
              >
                로그인
              </v-btn>
            </transition>

            <!-- 비회원 접속 button -->
            <!-- 비회원일 경우, 메인화면으로 바로 이동가능 하지만 주요 서비스 이용 불가 -->
            <transition name="slide-fade3">
              <v-btn
                v-if="expand"
                height="50px"
                width="300px"
                class="font-weight-bold mx-auto ma-4"
                :ripple="false"
                id="no-background-hover"
                color="#ffffffbb"
                rounded
                @click="goMain"
              >
                비회원으로 접속하기
              </v-btn>
            </transition>
          </div>
        </v-col>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  name: 'StartPage',

  components: {},

  data: () => ({
    expand: false, // 슬라이드 효과에 사용
  }),
  created() {
    // SNS로그인하여 'url?jwt='해서 jwt를 받아온 경우[YJS]
    if (this.$route.query.jwt !== undefined) {
      var jwt = this.$route.query.jwt;
      let form = new FormData(); // form : axios통신 할 값을 넣어 전달
      form.append('jwt', jwt);

      // 서버와 통신(axios)을 해 아이디 존재 여부를 message에 가져옴
      this.$store
        .dispatch('isExist', form)
        .then(() => {
          this.$store.dispatch('setJWT', jwt); // jwt를 로컬스토리지에 저장
          this.message = this.$store.getters.getMessage;

          // 해당 계정이 존재하는 경우 => main페이지로 이동
          if (this.message === 'EXIST') {
            this.$store.dispatch('setMessage', null); // message 재사용 위해
            this.$router.push(`/main`); // main페이지로 이동
          }
          // 해당 계정이 존재하지 않는 경우 => signup페이지로 이동
          else if (this.message === 'NOT EXIST') {
            this.$store.dispatch('setMessage', null); // message 재사용 위해
            this.$router.push(`/signup`); // signup페이지로 이동
          }
        })
        .catch(({ message }) => (this.msg = message));
    }
  },
  methods: {
    // 로그인 페이지로 이동
    goLogin() {
      this.$router.push('/login');
    },
    // 메인화면으로 이동
    goMain() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
      this.$router.push('/main');
    },
  },
};
</script>

<style scoped>
/* StartPage_css[HYH] */

#bg img {
  /* Set rules to fill background */
  min-height: 100%;
  min-width: 375px;

  /* Set up proportionate scaling */
  width: 100%;
  height: auto;

  /* Set up positioning */
  position: fixed;
  top: 0;
  left: 0;
}
#logo {
  top: 200px;
}
#bt {
  /* Set up proportionate scaling */
  width: 100%;
  height: auto;

  /* Set up positioning */
  position: fixed;
  top: 500px;
}

/* 슬라이드 버튼[HYH] */
.slide-fade-enter-active {
  transition: all 1s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(30px);
  opacity: 0;
}
.slide-fade2-enter-active {
  transition: all 3s ease;
}
.slide-fade2-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade2-enter, .slide-fade2-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(30px);
  opacity: 0;
}
.slide-fade3-enter-active {
  transition: all 5s ease;
}
.slide-fade3-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade3-enter, .slide-fade3-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(30px);
  opacity: 0;
}
/* 버튼 글씨 회색[YJS] */
.theme--light .v-btn {
  color: rgba(61, 61, 61, 0.87) !important;
}
</style>

<style lang="scss">
#no-background-hover::before {
  background-color: transparent !important;
}
</style>

<style>
/* 선만 있는 버튼[HYH] */
.fb-btn.v-btn--outlined {
  border: thin solid;
}
</style>
