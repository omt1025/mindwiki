<template>
  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-35 p-b-35">
          <form class="login100-form validate-form">
            <span class="login100-form-title p-b-39">
              SignUp
            </span>

            <div class="wrap-input100 validate-input m-b-18" data-validate="Username is reauired">
              <span class="label-input100" style="float: left">Userid</span>
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
                name="userid"
                id="userid"
                placeholder="아이디를 입력해주세요."
                @change="changeColor($event)"
              />
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate="Username is reauired">
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
                name="username"
                placeholder="이메일을 입력해주세요."
                id="useremail"
                @change="changeColor($event)"
              />
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate="Password is required">
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
                name="userpwd"
                id="userpwd"
                placeholder="비밀번호를 입력해주세요."
                @change="changeColor($event)"
              />
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate="Password is required">
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
                name="userpwd_check"
                id="userpwd_check"
                placeholder="비밀번호를 다시 입력해주세요."
                @change="changeColor($event)"
              />
            </div>

            <div class="wrap-input100 validate-input m-b-18">
              <span class="label-input100" style="float: left">관심태그 추가</span>
              <input
                class="input100"
                type="password"
                name="pass_check"
                placeholder="비밀번호를 다시 입력해주세요."
              />
              <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn p-t-31">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn">
                  회원가입
                </button>
              </div>
            </div>

            <div class="flex-col-c p-t-55">
              <span class="txt1 p-b-17">
                이미 회원이신가요?
              </span>

              <router-link to="/login" class="txt2">Login</router-link>
            </div>
          </form>
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
</style>

<script>
import '../../components/css/user.scss';

export default {
  components: {},
  created() {
    this.component = this;
  },
  methods: {
    changeColor(event) {
      const incon_id = event.target.id + '_icon';
      var icon = document.getElementById(incon_id);
      console.log(icon);
      if (event.target.id) {
        icon.style.color = '#a64bf4';
      } else if (!event.target.id) {
        icon.style.color = '#d9d9d9';
      }
      // https://negabaro.github.io/archive/vue-how-to-add-param-except-event
      // https://meaningone.tistory.com/318
    },
  },
};
</script>
