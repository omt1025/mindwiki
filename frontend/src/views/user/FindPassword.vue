<template>
  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">
          <div class="login100-form validate-form">
            <span class="login100-form-title p-b-49">
              Find PW
            </span>

            <div class="wrap-input100 validate-input m-b-60">
              <span class="label-input100" style="float: left">Email</span>
              <div class="input_icon">
                <i
                  class="fa fa-user-o"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 17px;"
                  id="userid_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="text"
                id="useremail"
                ref="useremail"
                v-model="user.useremail"
                placeholder="이메일을 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

            <div class="container-login100-form-btn">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn" @click="checkHandler">
                  비밀번호 찾기
                </button>
              </div>
            </div>

            <div class="txt1 p-t-15" id="text_1">
              * 가입된 아이디가 있는 경우, 입력하신 이메일로 임시 비밀번호를 안내해드립니다.<br />
            </div>
            <div class="txt1 p-t-5 p-b-15" id="text_1">
              * 이메일 서비스 제공자 사정에 의해 즉시 도착하지 않을 수 있으니, 최대 30분 정도
              기다리신 후 다시 시도해주세요.
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="dropDownSelect1"></div>
  </div>
</template>

<style scoped>
.login_back {
  background-image: url(../../assets/images/user/bg-01.jpg);
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
#text_1 {
  text-align: left;
  margin-bottom: 2px;
}
</style>

<script>
import Vue from 'vue';
import VueRouter from 'vue-router';
import 'v-slim-dialog/dist/v-slim-dialog.css';
import SlimDialog from 'v-slim-dialog';

Vue.use(SlimDialog);
Vue.use(VueRouter);

import axios from 'axios';
import '../../components/css/user.scss';

export default {
  components: {},
  data: function() {
    return {
      user: {
        useremail: '',
      },
    };
  },
  created() {
    this.component = this;
  },
  methods: {
    // 아이콘 색 변경 메소드
    changeColor(event) {
      const incon_id = event.target.id + '_icon';
      var icon = document.getElementById(incon_id);
      // console.log(icon);
      if (event.target.value) {
        icon.style.color = '#a64bf4';
      } else if (!event.target.value) {
        icon.style.color = '#d9d9d9';
      }
      // https://negabaro.github.io/archive/vue-how-to-add-param-except-event
      // https://meaningone.tistory.com/318
    },
    // 로그인 input 유효성 검사 메소드
    checkHandler() {
      let err = true;
      let msg = '';
      !this.user.userid &&
        ((msg = '아이디를 입력해주세요'), (err = false), this.$refs.userid.focus());
      err &&
        !this.user.userpwd &&
        ((msg = '비밀번호를 입력해주세요'), (err = false), this.$refs.userpwd.focus());
      // if (!err) alert(msg);
      if (!err) this.showAlert(msg);
      else this.login();
    },
    // LOGIN 액션 실행
    login() {
      let form = new FormData();
      form.append('id', this.user.userid);
      form.append('pass', this.user.userpwd);

      axios.post(`http://localhost:8000/mindwiki/login`, form).then(({ data }) => {
        // alert(data.message);
        if (data.message === 'SUCCESS') this.$router.push('/main');
        else this.showAlert('아이디와 비밀번호를 다시 한 번 확인해주세요.');
      });
    },
    // 카카오 로그인
    loginWithKakao() {
      const params = {
        redirectUri: 'http://localhost:8000/mindwiki/oauth',
      };
      window.Kakao.Auth.authorize(params);
    },
    // 다이얼로그
    // https://vuejsexamples.com/slim-dialog-for-vuejs/
    showAlert(msg) {
      const options = { title: '로그인 실패', size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        console.log(res); // {ok: true|false|undefined}
      });
    },
  },
};
</script>
