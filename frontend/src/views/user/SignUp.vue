<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 비밀번호 길이 제한, 태그 최대 3개
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-17
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
            <div class="wrap-input100 validate-input " id="email">
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
                type="email"
                placeholder="이메일을 입력해주세요."
                id="useremail"
                ref="useremail"
                v-model="user.useremail"
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>
            <span class="validation" v-if="msg.email">{{ msg.email }}</span>

            <!-- realName input -->
            <div class="wrap-input100 validate-input" style="margin-top:18px">
              <span class="label-input100" style="float: left">Username </span>
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
            <div class="wrap-input100 validate-input m-t-15">
              <span class="label-input100" style="float: left"
                >Password
                <p style="font-size: 10px; float:right">(12자 이내)</p>
              </span>
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
                :maxlength="max_pw"
                v-model="user.userpwd"
                placeholder="비밀번호를 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>
            <span class="validation" v-if="msg.pass">{{ msg.pass }}</span>

            <!-- password check input -->
            <div class="wrap-input100 validate-input m-t-15">
              <span class="label-input100" style="float: left"
                >Password Check
                <p style="font-size: 10px; float:right">(12자 이내)</p>
              </span>
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
                id="userpwd_check"
                ref="userpwd_check"
                :maxlength="max_pw"
                v-model="user.userpwd_check"
                placeholder="비밀번호를 다시 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>
            <span class="validation" v-if="msg.pass_check">{{ msg.pass_check }}</span>

            <!-- 관심태그 추가 text -->
            <div class="m-t-15">
              <span class="label-input100 interestTagTitle" style="float: left"
                >관심태그 추가
                <p style="font-size: 10px; float:right">(최대 3개)</p>
              </span>

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
                a
                solo
                hide-details="true"
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
            </div>
            <!-- <span class="validation" v-if="msg.tag">{{ msg.tag }}</span> -->

            <!-- 회원가입 button -->
            <!-- checkHandler에서 유효성검사 후, signup메소드에서 회원가입 처리 시도 -->
            <div class="container-login100-form-btn m-t-15">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn" @click="checkHandler">
                  회원가입
                </button>
              </div>
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
  </div>
</template>

<script>
import '../../components/css/user.scss';
import '../../components/css/tag.css';

export default {
  data() {
    return {
      chips: ['snow'], // 관심태그
      // 회원가입 계정
      user: {
        username: '', // 이름
        useremail: '', // 이메일
        userpwd: '', // 비밀번호
        userpwd_check: '', // 비밀번호 확인
        interest: {}, // 관심태그
      },
      message: '', // 오류 받아 올 변수
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      max_pw: 12, // 비밀번호 보안을 위해
    };
  },
  mounted() {
    // input에 SNS 로그인에서 가져온 값을 넣어주기[YJS]
    // mounted에 적용하지 않으면, 초기 input값 설정 불가능함
    // https://jdkblog.tistory.com/39
    if (this.$store.state.jwt !== null) {
      var email = document.getElementById('useremail');
      email.value = localStorage.getItem('user-id');
      email.disabled = true; // 아이디 입력 못하도록 비활성화
      document.getElementById('useremail_icon').style.color = '#a64bf4';
      this.user.useremail = email.value;
    }
  },
  updated() {
    // input에 SNS 로그인에서 가져온 값을 넣어주기[YJS]
    // updated에도 똑같이 적용해주지않으면, 다른 input입력시 값이 사라짐
    if (this.$store.state.jwt !== null) {
      var email = document.getElementById('useremail');
      email.value = localStorage.getItem('user-id');
      email.readOnly = true; // 아이디 입력 못하도록 비활성화
    }
  },
  watch: {
    // 이메일 양식 유효성 검사
    'user.useremail'(value) {
      this.user.useremail = value;
      this.validateEmail(value);
    },
    // 비밀번호 유효성 검사
    'user.userpwd'(value) {
      this.validatePassword(value, 'userpwd');
    },
    // 비밀번호 확인 유효성 검사
    'user.userpwd_check'(value) {
      this.validatePassword(value, 'userpwd_check');
    },
    // 관심태그 갯수 최대 3개
    chips(value) {
      this.validateTag(value);
    },
  },
  methods: {
    // 초기 관심태그는 최대 3개
    validateTag() {
      // 3개 이상 입력시 삭제처리
      if (this.chips.length > 3) {
        this.msg['tag'] = '관심태그는 최대 3개 입니다.';
        this.remove(this.chips[this.chips.length - 1]);
      } else this.msg['tag'] = '';
    },
    // 이메일 유효성 검사 정규식 : @ .여부
    validateEmail(v) {
      if (
        /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(v)
      ) {
        this.msg['email'] = '';
      } else {
        this.msg['email'] = '올바른 이메일을 입력해주세요.';
      }
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
      // chips를 #로 구분하여 전달하기 위해
      var hashtag = '';
      for (var i = 0; i < this.chips.length; i++) hashtag += '#' + this.chips[i];

      let form = new FormData();
      form.append('email', this.user.useremail);
      form.append('realName', this.user.username);
      form.append('password', this.user.userpwd);
      // 소셜 로그인의 경우, 소셜 닉네임을 가져온다.
      if (localStorage.getItem('user-nickname') !== null)
        form.append('nickName', localStorage.getItem('user-nickname'));
      // 초기 닉네임은 이메일 @앞 아이디
      else form.append('nickName', this.user.useremail.split('@')[0]); // 초기 닉네임은 이메일 @앞 아이디
      form.append('hashtag', hashtag);

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
  },
};
</script>

<style scoped>
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
  /* position: fixed; */
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
  margin-bottom: 6px;
}

/* 유효성 검사 span */
.validation {
  color: red;
  font-size: 0.6rem;
  margin-bottom: 18px;
}

.v-text-field.v-text-field--enclosed {
  clear: both;
}
</style>
<style>
/* 배경색 없애기 */
.v-text-field.v-text-field--enclosed:not(.v-text-field--rounded)
  > .v-input__control
  > .v-input__slot,
.v-text-field.v-text-field--enclosed .v-text-field__details {
  max-height: none !important;
  background-color: #fff !important;
  font-size: 0.875rem;
  box-shadow: none;
}
</style>
