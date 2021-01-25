<template>
  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-35 p-b-35">
          <div class="login100-form validate-form">
            <span class="login100-form-title p-b-39">
              SignUp
            </span>

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
                id="userpwd"
                ref="userpwd"
                v-model="user.userpwd"
                placeholder="비밀번호를 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>

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
                id="userpwd_check"
                ref="userpwd_check"
                v-model="user.userpwd_check"
                placeholder="비밀번호를 다시 입력해주세요."
                @change="changeColor($event)"
                @keypress.enter="checkHandler"
              />
            </div>
            <div>
              <p class="interestTagTitle">관심태그 추가</p>
            </div>
            <!-- :items="items" -->
            <!-- <div class="wrap-input100 validate-input m-b-18"> -->
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
            <!-- </div> -->

            <div class="container-login100-form-btn p-t-31">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn" @click="checkHandler">
                  회원가입
                </button>
              </div>
            </div>

            <div class="flex-col-c p-t-55">
              <span class="txt1 p-b-10">
                이미 회원이신가요?
              </span>

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

<script>
import axios from 'axios';
import '../../components/css/user.scss';
import '../../components/css/tag.css';

export default {
  data() {
    return {
      chips: ['싸피', 'ssafy'],
      // items: ['Streaming', 'Eating'],
      user: {
        username: '',
        useremail: '',
        userpwd: '',
        userpwd_check: '',
        interest: {},
      },
    };
  },
  components: {},
  created() {
    this.component = this;
  },
  methods: {
    // 아이콘 색 변경
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
    // 로그인 input 유효성 검사 메소드
    checkHandler() {
      let err = true;
      let msg = '';
      !this.user.useremail &&
        ((msg = '이메일을 입력해주세요'), (err = false), this.$refs.useremail.focus());
      err &&
        !this.user.username &&
        ((msg = '이름을 입력해주세요'), (err = false), this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = '비밀번호를 입력해주세요'), (err = false), this.$refs.userpwd.focus());
      err &&
        !this.user.userpwd_check &&
        ((msg = '비밀번호확인을 입력해주세요'), (err = false), this.$refs.userpwd_check.focus());
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
    // 관심태그 삭제
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    // 다이얼로그
    showAlert(msg) {
      const options = { title: '회원가입 실패', size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        console.log(res); // {ok: true|false|undefined}
      });
    },
  },
};
</script>
