<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 배경 화면 변경
    * 생성일자 : 2021-01-25
    * 최종수정일자 : 2021-02-18
  -->
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
                  id="useremail_icon"
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

            <!-- 로그인 text -->
            <div class="flex-col-c p-t-30">
              <span class="txt1 p-b-10">
                이미 회원이신가요?
              </span>

              <!-- 로그인 button -->
              <router-link to="/login" class="txt2">Login</router-link>
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
}
#text_1 {
  text-align: left;
  margin-bottom: 2px;
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

      if (event.target.value) {
        icon.style.color = '#a64bf4';
      } else if (!event.target.value) {
        icon.style.color = '#d9d9d9';
      }
      // https://negabaro.github.io/archive/vue-how-to-add-param-except-event
      // https://meaningone.tistory.com/318
    },
    //  비밀번호 찾기 input 유효성 검사 메소드
    checkHandler() {
      let err = true;
      let msg = '';
      !this.user.useremail &&
        ((msg = '이메일을 입력해주세요'), (err = false), this.$refs.useremail.focus());

      if (!err) this.showAlert(msg, '비밀번호찾기 실패');
      else this.findPW();
    },
    // 비밀번호 찾기 메소드 실행[YJS]
    findPW() {
      let form = new FormData(); // form : axios통신 할 값을 넣어 전달
      form.append('email', this.user.useremail);

      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('findpw', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          // 비밀번호 찾기 성공
          if (this.message === 'SUCCESS') {
            this.showAlert(
              '이메일로 임시 비밀번호가 발급되었습니다. 다시 로그인 해주세요.',
              '비밀번호찾기 성공'
            );
            // this.$router.push(`/login`); // main페이지로 이동
          }
          if (this.$store.getters.getMessage === 'FAIL') {
            // 비밀번호 찾기 실패 : 다이얼로그 띄우기
            this.showAlert('이메일을 다시 한 번 확인해주세요.', '비밀번호찾기 실패');
          }
        })
        .catch(({ message }) => (this.msg = message));

      // 탭 초기화(재사용 위해)
      this.$store.dispatch('setMessage', null);
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');
    },
    // 다이얼로그
    // https://vuejsexamples.com/slim-dialog-for-vuejs/
    showAlert(msg, title) {
      const options = { title, size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        // 비밀번호 찾기 성공시 모달창 ok버튼 누르면 로그인 페이지로 이동하기
        if (title === '비밀번호찾기 성공' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null); // message 재사용 위해
          this.$router.push(`/login`);
        }
      });
    },
  },
};
</script>
