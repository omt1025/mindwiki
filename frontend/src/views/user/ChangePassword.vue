<template>
  <div class="passwordChange_back">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">

            <span class="login100-form-title p-b-49">
              비밀번호 변경
            </span>

            <v-text-field
              label="변경할 비밀번호"
              v-model="password"
              :rules="[rules.passwordRules]"
              type="password"
              prepend-icon="fa fa-lock"
              required>
            </v-text-field>
            <v-text-field
              label="비밀번호 확인"
              v-model="confirmPassword"
              :rules="[rules.confirmPasswordRules, passwordConfirmationRule]"
              @update:error="checkPassword"
              type="password"
              prepend-icon="fa fa-lock"
              required>
            </v-text-field>

            <br>

            <div class="wrap-login100-form-btn">
              <div class="login100-form-bgbtn"></div>
              <button 
                class="login100-form-btn"
                @click="changePassword"
                :disabled="!isSubmit"
                :class="{disabled : !isSubmit}"
              >변경하기</button>
            </div>

            <br>

            <div class="wrap-login100-form-btn">
              <div class="login100-form-bgbtn"></div>
              <button 
                class="login100-form-btn"
                @click="backPage"
              >뒤로가기</button>
            </div>

        </div>
      </div>
  </div>
</template>

<script>

  export default {
    created() {
      this.component = this;
    },
    data() {
      return {
        password: "",
        confirmPassword: "",
        validPassword: "",
        rules: {
          passwordRules: v => {
            const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#%&])(?=.{8,})/;
            return (
              pattern.test(v) ||
              "영문자(대문자 최소 1개 포함), 숫자, 특수문자($, ^, * 제외) 8자리 이상"
            )
          } ,
          confirmPasswordRules: v => !!v || "비밀번호를 입력해주세요."
        }
      };
    },
    watch: {
      password: function() {
        this.passwordRules()
      },
      confirmPassword: function() {
        this.confirmPasswordRules()
      }
    },
    methods: {
      checkPassword(invalid) { 
      // correct: false
      if (true == invalid) {
          this.validPassword = false;
        } else {
          this.validPassword = true;
        }
      let isSubmit = true
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
      },
      backPage: function () {
        this.$router.push('/main/menu')
      },
      changePassword() {
        if (this.isSubmit) {
          // let { password, confirmPassword, validPassword } = this;
          // let data = {
          //   password,
          //   confirmPassword,
          //   validPassword,
          // };
          this.isSubmit = false;
          this.$router.push('/main/menu')
        }
      }
    },
    computed: {
      passwordConfirmationRule() {
        return () => (this.password === this.confirmPassword) || '비밀번호가 일치하지 않습니다.'
    },
  }
};
</script>

<style scoped>
.passwordChange_back {
  background-image: url(../../assets/images/user/bg-01.jpg);
  min-height: 100%;
  min-width: 375px;
  width: 100%;
  height: auto;
  position: fixed;
  top: 0;
  left: 0;
}
</style>