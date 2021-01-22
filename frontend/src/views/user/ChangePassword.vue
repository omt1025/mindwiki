<template>
  <div class="passwordChange_back">
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-35">
          <v-form class="login100-form validate-form" ref="form" v-model="valid">
            <span class="login100-form-title p-b-49">
              비밀번호 변경
            </span>
              <v-text-field
                label="Password"
                v-model="password"
                :rules="[rules.passwordRules]"
                type="password"
                required>
              </v-text-field>
              <v-text-field
                label="Confirm Password"
                v-model="confirmPassword"
                :rules="[rules.confirmPasswordRules, passwordConfirmationRule]"
                @update:error="checkPassword"
                type="password"
                required>
              </v-text-field>
          </v-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        password: "",
        confirmPassword: "",
        validPassword: "",
        rules: {
          passwordRules: v => !!v || "Password is required",
          confirmPasswordRules: v => !!v || "Password is required"
        }
      };
    },
    methods: {
      checkPassword(invalid) { 
      // correct: false
      if (true == invalid) {
          this.validPassword = false;
        } else {
          this.validPassword = true;
        }
      },
    },
    computed: {
      passwordConfirmationRule() {
        return () => (this.password === this.confirmPassword) || 'Password must match'
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