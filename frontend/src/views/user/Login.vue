<template>
  <div class="login_back">
    <div class="user" id="login">
      <div class="wrapC">
        <p id="page_title">
          로그인
        </p>

        <div class="input-with-label">
          <input
            v-model="email"
            v-bind:class="{ error: error.email, complete: !error.email && email.length !== 0 }"
            @keyup.enter="Login"
            id="email"
            placeholder="이메일을 입력하세요."
            type="text"
          />
          <label for="email">이메일</label>
          <div class="error-text" v-if="error.email">{{ error.email }}</div>
        </div>

        <div class="input-with-label">
          <input
            v-model="password"
            type="password"
            v-bind:class="{
              error: error.password,
              complete: !error.password && password.length !== 0,
            }"
            id="password"
            @keyup.enter="Login"
            placeholder="비밀번호를 입력하세요."
          />
          <label for="password">비밀번호</label>
          <div class="error-text" v-if="error.password">{{ error.password }}</div>
        </div>
        <button
          id="loginbtn"
          class="btn btn--back btn--login"
          @click="onLogin"
          :disabled="!isSubmit"
          :class="{ disabled: !isSubmit }"
        >
          로그인
        </button>

        <div class="option">
          <div class="text2" style="text-align: center">
            <p id="login_sub">비밀번호 찾기</p>
            <p id="login_sub">/</p>
            <p id="login_sub">회원가입</p>
            <!-- <div class="bar"></div> -->
          </div>
        </div>

        <div class="sns-login">
          <div class="text">
            <div class="bar"></div>
            <p>SNS 간편 로그인</p>
            <!-- <div class="bar"></div> -->
          </div>

          <kakaoLogin :component="component" />
          <NaverLogin
            client-id="<client-id>"
            callback-url="<callback-url>"
            v-bind:is-popup="true"
            v-bind:button-type="1"
            v-bind:button-height="45"
            button-color="green"
            :callbackFunction="callbackFunction"
            style="display: inline-block; margin-right: 10px"
          />
          <GoogleLogin :component="component" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../../components/css/user.scss';
// import PV from 'password-validator';
// import * as EmailValidator from 'email-validator';
import KakaoLogin from '../../components/user/snsLogin/Kakao.vue';
import NaverLogin from 'vue-naver-login';
// import NaverLogin from '../../components/user/snsLogin/Naver.vue';
import GoogleLogin from '../../components/user/snsLogin/Google.vue';
// import UserApi from '../../api/UserApi';

export default {
  components: {
    NaverLogin,
    KakaoLogin,
    GoogleLogin,
  },
  created() {
    this.component = this;
  },
  methods: {
    callbackFunction(status) {
      if (status) {
        /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
        // var email = naverlogin.user.getEmail();
        // if (email == undefined || email == null) {
        // alert('이메일은 필수정보입니다. 정보제공을 동의해주세요.');
        /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
        // naverlogin.reprompt();
        // return;
        // }

        window.location.replace(
          'http://' +
            window.location.hostname +
            (location.port == '' || location.port == undefined ? '' : ':' + location.port) +
            '/sample/main.html'
        );
      } else {
        console.log('callback 처리에 실패하였습니다.');
      }
    },
    checkForm() {
      // var emailRegExp = /^[a-z0-9_]+[a-z0-9]*[@]{1}[a-z0-9]+[a-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
      // if (this.email.length >= 0 && !EmailValidator.validate(this.email))
      //   this.error.email = '이메일 형식이 아닙니다.';
      // else if (!emailRegExp.test(this.email)) {
      //   this.error.email = '이메일 첫 글자에 대문자가 들어있습니다.';
      // } else this.error.email = false;
      // if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
      //   this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
      // else this.error.password = false;
      // let isSubmit = true;
      // Object.values(this.error).map((v) => {
      //   if (v) isSubmit = false;
      // });
      // this.isSubmit = isSubmit;
    },
    onLogin() {
      // if (this.isSubmit) {
      //   let { email, password } = this;
      //   let data = {
      //     email,
      //     password,
      //   };
      //   //요청 후에는 버튼 비활성화
      //   this.isSubmit = false;
      //   UserApi.requestLogin(
      //     data,
      //     (res) => {
      //       //통신을 통해 전달받은 값 콘솔에 출력
      //       //console.log(res);
      //       //요청이 끝나면 버튼 활성화
      //       this.isSubmit = true;
      //       this.$router.push('feed/main');
      //     },
      //     (error) => {
      //       //요청이 끝나면 버튼 활성화
      //       this.isSubmit = true;
      //     }
      //   );
      // }
    },
  },
  data: () => {
    return {
      email: '',
      password: '',
      // passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      component: this,
    };
  },
};
</script>

<style scoped>
.login_back {
  background-image: url(../../assets/images/login_background.png);
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

  /* https://knulab.com/archives/1185 */
}
.user {
  margin-top: 100px;
}
#page_title {
  text-align: center;
  font-weight: 600;
  font-size: 200%;
  padding-bottom: 50px;
}
.input-with-label {
  margin-bottom: 15px;
}
.user#login .add-option .bar {
  width: calc(100% - 90px);
}
#login_sub {
  color: #787878;
  font-size: 0.857em;
  margin-right: 10px;
  display: inline-block;
  /* text-align: center; */
}
.option {
  margin-top: 12px;
}
/* .user#login .sns-login .bar {
  width: calc(100% - 180px);
} */
.snslogin_title {
  height: 1px;
  background-color: #cccccc;
  display: inline-block;
  /* float: right; */
  margin-top: 9px;
  margin-bottom: 20px;
}
</style>
