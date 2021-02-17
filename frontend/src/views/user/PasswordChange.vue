<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 비밀번호 길이 제한
    * 생성일자 : 2021-01-21
    * 최종수정일자 : 2021-02-17
 -->

  <div>
    <!-- 상단 Navi -->
    <close-navi :title="title"></close-navi>

    <div class="container-login100">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-35">
        <!-- 현재 비밀번호 -->
        <span class="label-input100 p-b-7" style="float: left">현재 비밀번호</span>
        <w-input
          class="m-b-10"
          id="oldpwd"
          ref="oldpwd"
          v-model="user.oldpwd"
          type="password"
          required
          placeholder="현재 비밀번호"
        />

        <!-- 새 비밀번호 -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left"
          >새 비밀번호
          <p style="font-size: 10px; float:right">(12자 이내)</p>
        </span>
        <w-input
          class="m-t-10"
          id="userpwd"
          ref="userpwd"
          v-model="user.userpwd"
          type="password"
          :maxlength="max_pw"
          placeholder="새 비밀번호"
        />
        <span class="validation" v-if="msg.pass">{{ msg.pass }}</span>

        <!-- 새 비밀번호 확인 -->
        <w-input
          class="m-t-10"
          type="password"
          id="userpwd_check"
          ref="userpwd_check"
          :maxlength="max_pw"
          v-model="user.userpwd_check"
          placeholder="새 비밀번호 확인"
        />
        <span class="validation" v-if="msg.pass_check">{{ msg.pass_check }}</span>

        <!-- 비밀번호 변경 tip -->
        <div class="m-t-25 m-b-25">
          <p style="text-align: left" class="m-b-5">
            비밀번호에 영문, 숫자, 특수문자를 조합하시면 비밀번호 안전도가 높아저 도용의 위험이
            줄어듭니다.<br />
          </p>
          <p class="tip">
            * 사용 가능한 특수문자: !@#$%^&amp;*( )+-/&lt;&gt;,. <br />
            * ID, 주민번호, 생일, 전화번호 등의 개인정보의 사용을 피하는 것이 좋습니다.<br />
            * 비밀번호는 주기적으로 바꾸어 사용하시는 것이 안전합니다.
          </p>
        </div>

        <!-- 변경하기 버튼 -->
        <div class="wrap-login100-form-btn">
          <div class="login100-form-bgbtn"></div>
          <button class="login100-form-btn" @click="checkHandler">
            변경하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CloseNavi from '../../components/navi/CloseNavi.vue';
export default {
  data() {
    return {
      title: '비밀번호 변경',
      user: {
        oldpwd: '', // 현재 비밀번호
        userpwd: '', // 비밀번호
        userpwd_check: '', // 비밀번호 확인
      },
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      message: '', // 오류 받아 올 변수
      max_pw: 12, // 비밀번호 길이 제한
    };
  },
  components: {
    CloseNavi,
  },
  watch: {
    // 비밀번호 유효성 검사
    'user.userpwd'(value) {
      this.validatePassword(value, 'userpwd');
    },
    // 비밀번호 확인 유효성 검사
    'user.userpwd_check'(value) {
      this.validatePassword(value, 'userpwd_check');
    },
  },
  methods: {
    backPage: function() {
      this.$router.push('/main');
    },
    // 비밀번호 유효성 검사 정규식 : 영어,숫자,특수문자 조합, 8글자 이상
    validatePassword(v, title) {
      if (title === 'userpwd')
        if (/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/g.test(v)) {
          this.msg['pass'] = '';
        } else this.msg['pass'] = '영어, 숫자, 특수문자를 조합하여 8글자 이상 입력해주세요.';
      else {
        if (this.user.userpwd_check !== this.user.userpwd)
          // 비밀번호 확인과 비밀번호의 입력값이 다른 경우
          this.msg['pass_check'] = '비밀번호를 다시 확인해주세요.';
        else this.msg['pass_check'] = '';
      }
    },
    // input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지

      // 현재 비밀번호 input이 공백인 경우
      !this.user.oldpwd && ((msg = '현재 비밀번호를 입력해주세요'), (err = false));

      // 비밀번호 input이 공백인 경우
      err && !this.user.userpwd && ((msg = '새 비밀번호를 입력해주세요'), (err = false));

      // 비밀번호 확인 inputdl 공백인 경우
      err && !this.user.userpwd_check && ((msg = '새 비밀번호 확인을 입력해주세요'), (err = false));

      if (!err) this.showAlert(msg, '양식 오류');
      else this.changePW();
    },
    changePW() {
      if (this.msg.pass || this.msg.pass_check)
        this.showAlert('양식을 지켜 다시 시도해주세요.', '양식 오류');
      else {
        // 탭 초기화(재사용 위해)
        this.$store.dispatch('setMessage', null);
        this.$store.dispatch('setMainTab', 0);
        this.$store.dispatch('setBottomNav', 'home');

        // 서버통신 위해 form에 담기
        let form = new FormData();
        form.append('jwt', this.$store.getters.getJWT);
        form.append('oldPassword', this.user.oldpwd);
        form.append('newPassword', this.user.userpwd);

        // 서버통신 위해 action호출
        this.$store
          .dispatch('changePW', form)
          .then(() => {
            this.message = this.$store.getters.getMessage;

            // 비밀번호 변경 성공
            if (this.message === 'SUCCESS') {
              this.showAlert('시작 화면으로 이동합니다.', '비밀번호변경 성공');
            } else if (this.message === 'FAIL') {
              // 현재 비밀번호와 일치하지 않음
              this.showAlert('현재 비밀번호가 일치하지 않습니다.', '비밀번호변경 실패');
            } else {
              // 서버 오류
              this.showAlert(
                '서버가 원활하지 않아 현재 회원가입이 불가합니다. 잠시후 다시 시도해주세요.',
                '서버 오류'
              );
            }
          })
          .catch(({ message }) => (this.msg = message));
      }
    },
    // 다이얼로그[YJS]
    showAlert(msg, title) {
      const options = { title: title, size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        // 비밀번호 변경 성공시 모달창 ok버튼 누르면 시작 페이지로 이동하기
        if (title === '비밀번호변경 성공' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null);
          this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
        }
      });
    },
  },
};
</script>

<style scoped>
@media (min-width: 320px) and (max-width: 480px) {
  .m-t-10 {
    margin-top: 10px !important;
  }
  /* 유효성 검사 span */
  .validation {
    color: red;
    font-size: 0.6rem;
    margin-bottom: 18px;
  }
  /* 비밀번호 변경 팁 span */
  .tip {
    font-size: 0.6rem;
    text-align: left;
  }
  .container-login100 {
    width: 100%;
    min-height: auto;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: normal;
    padding: 15px;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .input[data-v-565818fe] {
    background-color: #00000006 !important;
  }
}

.container-login100 {
  align-items: baseline;
}
</style>
