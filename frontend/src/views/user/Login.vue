<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : SNS 로그인 JWT저장
    * 생성일자 : 2021-01-21
    * 최종수정일자 : 2021-02-02
 -->

  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">
          <div class="login100-form validate-form">
            <!-- title -->
            <span class="login100-form-title p-b-49">
              Login
            </span>

            <!-- email input -->
            <div class="wrap-input100 validate-input m-b-18">
              <span class="label-input100" style="float: left">Email</span>
              <div class="input_icon">
                <i
                  class="fa fa-envelope-o"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 17px;"
                  id="userid_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="text"
                id="userid"
                ref="userid"
                v-model="user.userid"
                placeholder="이메일을 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

            <!-- password input -->
            <div class="wrap-input100 validate-input">
              <span class="label-input100" style="float: left">Password</span>
              <div class="input_icon">
                <i
                  class="fa fa-lock"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 22px;"
                  id="userpwd_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="password"
                id="userpwd"
                ref="userpwd"
                v-model="user.userpwd"
                placeholder="비밀번호를 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

            <!-- 비밀번호찾기 button -->
            <div class="text-right p-t-8 p-b-31">
              <router-link to="/findpw" style="margin-right: 8px">
                비밀번호 찾기
              </router-link>
              <router-link to="/signup">
                회원가입
              </router-link>
            </div>

            <!-- 로그인 button -->
            <!-- checkHandler에서 유효성검사 후, login메소드에서 로그인 처리 시도 -->
            <div class="container-login100-form-btn">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn" @click="checkHandler">
                  Login
                </button>
              </div>
            </div>

            <!-- SNS 로그인 text -->
            <div class="txt1 text-center p-t-70 p-b-15">
              <span>
                SNS 간편 로그인
              </span>
            </div>

            <!-- SNS 로그인 buttons -->
            <div class="flex-c-m sns-login">
              <!-- 카카오 로그인 -->
              <div @click="loginWithKakao">
                <kakaoLogin style="margin-right: 10px" />
              </div>

              <!-- 구글 로그인 -->
              <a
                :href="
                  'https:' +
                    '//accounts.google.com/o/oauth2/v2/auth?' +
                    'scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&' +
                    'access_type=online&' +
                    'include_granted_scopes=true&' +
                    'response_type=code&' +
                    'redirect_uri=http%3A//localhost:8000/mindwiki/GoogleOAuth&' +
                    'client_id=659791765906-faeludmkkn7l8vqlk37pqlhhisu4n1hb.apps.googleusercontent.com'
                "
              >
                <GoogleLogin />
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="dropDownSelect1"></div>
  </div>
</template>

<style scoped>
@media screen and (min-width: 320px) and (max-width: 480px) {
  /* Specific to this particular image */
  .login_back {
    background-image: url(../../assets/images/user/bg-01.jpg);
    /* Set rules to fill background */
    min-height: 100%;
    min-width: 360px;

    /* Set up proportionate scaling */
    width: 100%;
    height: auto;

    /* Set up positioning */
    position: fixed;
    top: 0;
    left: 0;
  }
}

.login_back {
  background-image: url(../../assets/images/user/bg-01.jpg);
  background-size: cover;
  /* Set rules to fill background */
  min-height: 100%;
  min-width: 360px;

  /* Set up proportionate scaling */
  width: 100%;
  height: auto;

  /* Set up positioning */
  position: fixed;
  top: 0;
  left: 0;

  /* https://knulab.com/archives/1185 */
}
</style>

<script>
import Vue from 'vue';
import VueRouter from 'vue-router';
import 'v-slim-dialog/dist/v-slim-dialog.css';
import SlimDialog from 'v-slim-dialog';

Vue.use(SlimDialog);
Vue.use(VueRouter);

import '../../components/css/user.scss';
import KakaoLogin from '../../components/user/snsLogin/Kakao.vue';
import GoogleLogin from '../../components/user/snsLogin/Google.vue';

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },

  data: function() {
    return {
      // user : id와 pass의 input에 입력된 값을 바인딩
      user: {
        userid: '',
        userpwd: '',
      },
      message: '', // 오류 받아 올 변수
    };
  },

  created() {
    this.component = this;
  },

  methods: {
    // 아이콘 색 변경 메소드[YJS]
    // https://negabaro.github.io/archive/vue-how-to-add-param-except-event
    // https://meaningone.tistory.com/318
    changeColor(event) {
      const incon_id = event.target.id + '_icon';
      var icon = document.getElementById(incon_id);

      // input에 값이 입력 된 경우, 아이콘을 보라색으로
      if (event.target.value) {
        icon.style.color = '#a64bf4';
      } else if (!event.target.value) {
        // input에 값이 없는 경우, 아이콘을 원래 색으로
        icon.style.color = '#d9d9d9';
      }
    },

    // 로그인 input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지

      // 아이디 input이 공백인 경우
      !this.user.userid &&
        ((msg = '이메일을 입력해주세요'), (err = false), this.$refs.userid.focus());

      // 비밀번호 input이 공백인 경우
      err &&
        !this.user.userpwd &&
        ((msg = '비밀번호를 입력해주세요'), (err = false), this.$refs.userpwd.focus());

      if (!err) this.showAlert(msg);
      else this.login(); // 아이디와 비밀번호가 공백이 아니면, 로그인처리
    },

    // LOGIN 액션 실행[YJS]
    login() {
      let form = new FormData(); // form : axios통신 할 값을 넣어 전달
      form.append('id', this.user.userid);
      form.append('pass', this.user.userpwd);

      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          // 로그인 성공
          if (this.message === 'SUCCESS') {
            this.$store.dispatch('setMessage', null); // message 재사용 위해
            this.$router.push(`/main`); // main페이지로 이동
          } else {
            // 로그인 실패 : 다이얼로그 띄우기

            this.showAlert('이메일과 비밀번호를 다시 한 번 확인해주세요.');
          }
        })
        .catch(({ message }) => (this.msg = message));

      // 탭 초기화(재사용 위해)
      this.$store.dispatch('setMessage', null);
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');
    },

    // 카카오 로그인[YJS]
    loginWithKakao() {
      const params = {
        redirectUri: 'http://localhost:8000/mindwiki/oauth',
      };
      window.Kakao.Auth.authorize(params);
    },

    // 다이얼로그[YJS]
    // https://vuejsexamples.com/slim-dialog-for-vuejs/
    showAlert(msg) {
      const options = { title: '로그인 실패', size: 'sm' };
      this.$dialogs.alert(msg, options).then(() => {});
    },
  },
};
</script>
