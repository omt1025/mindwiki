<template>
  <div id="bg">
    <!-- <img src="../assets/vertical.png" alt=""> -->
    <v-app>
      <v-row justify="center" align="center" style="min-height: 160px;">
        <v-col class="shrink">
          <v-btn
            rounded
            class="ma-5"
            color="#0278ae"
            height="200px"
            width="350px"
            @click="expand = !expand"
          >
            <h1 class="login100-form-title">MIND WIKI</h1>
          </v-btn>

          <transition name="slide-fade">
            <v-card color="#51dacf" v-if="expand" class="mx-auto ma-6" width="350px">
              <v-btn
                to="/signup"
                height="50px"
                width="350px"
                color="#51dacf"
                align="center"
                class="font-weight-bold"
              >
                회원가입
              </v-btn>
            </v-card>
          </transition>

          <transition name="slide-fade2">
            <v-card color="#9ef5cf" v-if="expand" class="mx-auto ma-6" width="350px">
              <v-btn
                @click="goLogin"
                height="50px"
                width="350px"
                color="#9ef5cf"
                align="center"
                class="font-weight-bold"
              >
                로그인
              </v-btn>
            </v-card>
          </transition>

          <transition name="slide-fade3">
            <v-card color="#e8ffc1" v-if="expand" class="mx-auto ma-6" width="350px">
              <v-btn
                to="/main"
                height="50px"
                width="350px"
                color="#e8ffc1"
                align="center"
                class="font-weight-bold"
              >
                비회원으로 접속하기
              </v-btn>
            </v-card>
          </transition>
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
  methods: {
    goLogin() {
      this.$router.push('/login');
    },
  },
};
</script>

<style scoped>
#bg img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  min-width: 100%;
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
