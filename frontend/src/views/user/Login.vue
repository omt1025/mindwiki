<template>
  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">
          <form class="login100-form validate-form">
            <span class="login100-form-title p-b-49">
              Login
            </span>

            <div class="wrap-input100 validate-input m-b-18">
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
                id="userid"
                name="userid"
                v-model="user.userid"
                placeholder="아이디를 입력해주세요."
                @change="changeColor"
              />
            </div>

            <div class="wrap-input100 validate-input" data-validate="Password is required">
              <span class="label-input100" style="float: left">Password</span>
              <div class="input_icon">
                <i
                  class="fa fa-lock"
                  aria-hidden="true"
                  style="font-weight: 800; font-size: 22px;"
                ></i>
              </div>
              <input
                class="input100"
                type="password"
                id="userpwd"
                name="userpwd"
                v-model="user.userpwd"
                placeholder="비밀번호를 입력해주세요."
              />
            </div>

            <div class="text-right p-t-8 p-b-31">
              <a href="#">
                비밀번호 찾기
              </a>
            </div>

            <div class="container-login100-form-btn">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn">
                  Login
                </button>
              </div>
            </div>

            <div class="txt1 text-center p-t-70 p-b-15">
              <span>
                SNS 간편 로그인
              </span>
            </div>

            <div class="flex-c-m sns-login">
              <kakaoLogin :component="component" style="margin-right: 10px" />
              <GoogleLogin :component="component" />
            </div>

            <!-- <div class="flex-col-c p-t-155">
              <span class="txt1 p-b-17">
                Or Sign Up Using
              </span>

              <a href="#" class="txt2">
                Sign Up
              </a>
            </div> -->
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
import KakaoLogin from '../../components/user/snsLogin/Kakao.vue';
import GoogleLogin from '../../components/user/snsLogin/Google.vue';

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },
  data: function() {
    return {
      user: {
        userid: '',
        userpwd: '',
      },
      activeColor: '#a64bf4',
    };
  },
  created() {
    this.component = this;
  },
  methods: {
    changeColor() {
      if (this.user.userid) {
        console.log('됐나????');
      }
    },
  },
};
</script>
