<template>
  <v-app>
    <MenuNavi/>
    <v-container
    fluid
    class="pa-0"
  >
    <v-row align="center">
      <v-col
        cols="12"
        sm="6"
      >
        <div class="text-center">

          <!-- 비회원일 때 보여줌 -->
          <div class="my-5">
            <v-btn
              x-large
              color="secondary"
              dark
              width="500px"
              to="/login"
            >
            <v-icon>mdi-login</v-icon>
              <span >로그인</span>
            <v-spacer></v-spacer>
            </v-btn>
          </div>
          <div class="my-5">
            <v-btn
              x-large
              color="secondary"
              dark
              width="500px"
            >
            <v-icon>mdi-account-plus</v-icon>
              <span >회원가입</span>
            <v-spacer></v-spacer>
            </v-btn>
          </div>
          <!--  -->

          <!-- 회원일 때 보여줌 -->
          <div class="my-5">
            <v-btn
              x-large
              color="secondary"
              dark
              width="500px"
            >
            <v-icon>mdi-logout</v-icon>
              <span >로그아웃</span>
            <v-spacer></v-spacer>
            </v-btn>
          </div>
          <div class="my-5">
            <v-btn
              x-large
              color="primary"
              dark
              width="500px"
              to="/main/menu/changepassword"
            >
            <v-icon>mdi-lock-reset</v-icon>         
              <span >비밀번호 변경</span>
            <v-spacer></v-spacer>

            <v-icon>mdi-arrow-right</v-icon>
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import MenuNavi from '@/components/navi/MenuNavi.vue';

export default {
  name: 'MainMenu',
  components: {
    MenuNavi,
  },
  data: () => ({
    bellClick: true,
  }),
};
</script>

<style scoped>
span {
  display: block;
  margin-left: 20px;
}
</style>
