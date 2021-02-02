<template>
  <div id="bg">
    <img src="../assets/images/user/bg-01.jpg" alt="" />

    <v-app>
      <v-row justify="center" align="center" style="min-height: 160px;">
        <v-col class="shrink">
          <v-img
            id="logo"
            src="../assets/images/mindwiki_logo-01.png"
            @click="expand = !expand"
            class="ma-8"
          ></v-img>

          <div id="bt">
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
    expand: false,
    expand2: false,
    show: false,
  }),
  created() {
    // SNS로그인하여 'url?jwt='해서 jwt를 받아온 경우 => main페이지로 이동
    if (this.$route.query.jwt !== undefined) {
      var jwt = this.$route.query.jwt;
      this.$store.dispatch('setJWT', jwt);
      // console.log('jwt저장 확인 : ' + this.$store.getters.getJWT);
      this.$router.push('/main');
    }
  },
  methods: {
    goLogin() {
      this.$router.push('/login');
    },
    goMain() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
      this.$router.push('/main');
    },
  },
};
</script>

<style scoped>
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
</style>

<style lang="scss">
#no-background-hover::before {
  background-color: transparent !important;
}
</style>

<style>
.fb-btn.v-btn--outlined {
  border: thin solid;
}
</style>
