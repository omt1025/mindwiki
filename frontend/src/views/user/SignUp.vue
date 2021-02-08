<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 회원가입 서버연동
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-07
 -->

  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-35 p-b-35">
          <div class="login100-form validate-form">
            <!-- title -->
            <span class="login100-form-title p-b-39">
              SignUp
            </span>

            <!-- email input -->
            <div class="wrap-input100 validate-input m-b-18">
              <span class="label-input100" style="float: left">Email</span>
              <div class="input_icon">
                <i
                  class="fa fa-envelope-o"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 17px;"
                  id="useremail_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="text"
                placeholder="이메일을 입력해주세요."
                id="useremail"
                ref="useremail"
                v-model="user.useremail"
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

            <!-- realName input -->
            <div class="wrap-input100 validate-input m-b-18">
              <span class="label-input100" style="float: left">Username</span>
              <div class="input_icon">
                <i
                  class="fa fa-user-o"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 17px;"
                  id="username_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="text"
                id="username"
                ref="username"
                v-model="user.username"
                placeholder="이름을 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

            <!-- password input -->
            <div class="wrap-input100 validate-input m-b-18">
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
                label="userpwd"
                id="userpwd"
                ref="userpwd"
                :rules="[rules.passwordRules]"
                v-model="user.userpwd"
                placeholder="비밀번호를 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
                required
              />
            </div>

            <!-- password check input -->
            <div class="wrap-input100 validate-input m-b-18">
              <span class="label-input100" style="float: left">Password Check</span>
              <div class="input_icon">
                <i
                  class="fa fa-lock"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 22px;"
                  id="userpwd_check_icon"
                ></i>
              </div>
              <input
                class="input100"
                type="password"
                label="userpwd_check"
                id="userpwd_check"
                ref="userpwd_check"
                :rules="[rules.confirmPasswordRules, passwordConfirmationRule]"
                @update:error="checkPassword"
                v-model="user.userpwd_check"
                placeholder="비밀번호를 다시 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
                required
              />
            </div>

            <!-- 관심태그 추가 text -->
            <div>
              <p class="interestTagTitle">관심태그 추가</p>
            </div>

            <!-- 관심태그 box -->
            <v-combobox
              append-icon
              flat
              v-model="chips"
              chips
              clearable
              label="Your favorite hobbies"
              multiple
              no-filter
              solo
              id="combobox"
            >
              <template v-slot:selection="{ attrs, item, select, selected }">
                <v-chip
                  v-bind="attrs"
                  :input-value="selected"
                  close
                  class="ma-2"
                  color="orange"
                  text-color="white"
                  @click="select"
                  @click:close="remove(item)"
                >
                  <strong class="test">{{ item }}</strong
                  >&nbsp;
                </v-chip>
              </template>
            </v-combobox>

            <!-- 회원가입 button -->
            <!-- checkHandler에서 유효성검사 후, signup메소드에서 회원가입 처리 시도 -->
            <div class="container-login100-form-btn p-t-31">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn" @click="checkHandler">
                  회원가입
                </button>
              </div>
            </div>

            <!-- 로그인 text -->
            <div class="flex-col-c p-t-55">
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
  </div>
</template>

<script>
import '../../components/css/user.scss';
import '../../components/css/tag.css';

export default {
  data() {
    return {
      chips: ['싸피', 'ssafy'], // 관심태그
      // 회원가입 계정
      user: {
        username: '', // 이름
        useremail: '', // 이메일
        userpwd: '', // 비밀번호
        userpwd_check: '', // 비밀번호 확인
        interest: {}, // 관심태그
      },
      message: '', // 오류 받아 올 변수
      // 비밀번호 유효성 검사할 규칙
      validPassword: '',
      rules: {
        passwordRules: (v) => {
          const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#%&])(?=.{8,})/;
          return (
            pattern.test(v) ||
            '영문자(대문자 최소 1개 포함), 숫자, 특수문자($, ^, * 제외) 8자리 이상'
          );
        },
        confirmPasswordRules: (v) => !!v || '비밀번호를 입력해주세요.',
      },
    };
  },
  watch: {
    userpwd: function() {
      this.passwordRules();
    },
    userpwd_check: function() {
      this.confirmPasswordRules();
    },
  },
  computed: {
    passwordConfirmationRule() {
      return () => this.password === this.confirmPassword || '비밀번호가 일치하지 않습니다.';
    },
  },
  components: {},
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

    // 회원가입 input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지

      // 아이디 input이 공백인 경우
      !this.user.useremail &&
        ((msg = '이메일을 입력해주세요'), (err = false), this.$refs.useremail.focus());

      // 이름 input이 공백인 경우
      err &&
        !this.user.username &&
        ((msg = '이름을 입력해주세요'), (err = false), this.$refs.username.focus());

      // 비밀번호 input이 공백인 경우
      err &&
        !this.user.userpwd &&
        ((msg = '비밀번호를 입력해주세요'), (err = false), this.$refs.userpwd.focus());

      // 비밀번호 확인 inputdl 공백인 경우
      err &&
        !this.user.userpwd_check &&
        ((msg = '비밀번호확인을 입력해주세요'), (err = false), this.$refs.userpwd_check.focus());

      if (!err) this.showAlert(msg);
      else this.signup();
    },

    // Sign Up 액션 실행[YJS]
    signup() {
      let form = new FormData();
      form.append('email', this.user.useremail);
      form.append('realName', this.user.userpwd);
      form.append('password', this.user.username);
      form.append('nickName', this.user.useremail.split('@')[0]); // 초기 닉네임은 이메일 @앞 아이디
      form.append('hashtag', this.chips);

      this.$store
        .dispatch('signUp', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          // 회원가입 성공
          if (this.message === 'SUCCESS') {
            this.showAlert('로그인 화면으로 이동합니다.', '회원가입 성공');
          } else if (this.message === 'FAIL') {
            // 해당 이메일의 아이디가 이미 존재함
            this.showAlert(
              '해당 이메일이 이미 사용중입니다. 다른 이메일을 입력해주세요.',
              '회원가입 실패'
            );
          } else {
            // 서버 오류
            this.showAlert(
              '서버가 원활하지 않아 현재 회원가입이 불가합니다. 잠시후 다시 시도해주세요.',
              '서버 오류'
            );
          }
        })
        .catch(({ message }) => (this.msg = message));
    },

    // 관심태그 삭제[YJS]
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },

    // 다이얼로그[YJS]
    showAlert(msg, title) {
      const options = { title: title, size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        // 회원가입 성공시 모달창 ok버튼 누르면 로그인 페이지로 이동하기
        if (title === '회원가입 성공' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null);
          this.$router.push(`/login`); // login페이지로 이동
        }
      });
    },
    checkPassword(invalid) {
      // correct: false
      if (true == invalid) {
        this.validPassword = false;
      } else {
        this.validPassword = true;
      }
      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
  },
};
</script>

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

/* 관심태그 title */
.interestTagTitle {
  font-family: Poppins-Regular;
  font-size: 14px;
  color: #333333;
  line-height: 1.5;
  padding-left: 7px;
  width: 100%;
  text-align: left;
  margin-bottom: 7px;
}
</style>
