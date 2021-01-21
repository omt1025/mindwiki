<template>
  <div class="login_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">
          <form class="login100-form validate-form">
            <span class="login100-form-title p-b-49">
              Login
            </span>

            <div class="wrap-input100 validate-input m-b-18" data-validate="Username is reauired">
              <span class="label-input100" style="float: left">Username</span>
              <input
                class="input100"
                type="text"
                name="username"
                placeholder="Type your username"
              />
              <span class="focus-input100" data-symbol="&#xf206;"></span>
            </div>

            <div class="wrap-input100 validate-input" data-validate="Password is required">
              <span class="label-input100" style="float: left">Password</span>
              <input
                class="input100"
                type="password"
                name="pass"
                placeholder="Type your password"
              />
              <span class="focus-input100" data-symbol="&#xf190;"></span>
            </div>

            <div class="text-right p-t-8 p-b-31">
              <a href="#">
                Forgot password?
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
  created() {
    this.component = this;
  },
};
</script>
