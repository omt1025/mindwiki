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

          <div class="my-5">
            <v-btn
              color="warning"
              dark
              x-large
              width="500px"
            >
            <v-icon>mdi-trophy</v-icon>           
              <span >업적 관리</span>
            <v-spacer></v-spacer>    
            <v-icon>mdi-arrow-right</v-icon>    
            </v-btn>
          </div>

          <div class="my-5">
            <v-btn
              color="error"
              dark
              x-large
              width="500px"
            >
            <v-icon>mdi-bookshelf</v-icon>
              <span >내 책장 공개</span>
            <v-spacer></v-spacer>

            <v-switch
            ></v-switch>

            </v-btn>
          </div>

          <div class="my-5">
            <v-btn
              x-large
              color="success"
              dark
              width="500px"
            >
            <v-icon
              v-show="!bellClick"
            >mdi-bell</v-icon>

            <v-icon
              v-show="bellClick"
            >mdi-bell-remove-outline</v-icon>
              
              <span >푸시 알림 설정</span>
            <v-spacer></v-spacer>

            <v-switch
              v-on:click="bellClick = !bellClick"
            ></v-switch>
            </v-btn>
          </div>

          <div class="my-5">
            <v-btn
              x-large
              color="#a64bf4"
              dark
              width="500px"
            >
            <v-icon>mdi-saw-blade</v-icon> 
              <span >MIND 관리 및 설정</span>
            <v-spacer></v-spacer>
            <v-icon>mdi-arrow-right</v-icon>
            </v-btn>
          </div>

          <div class="my-5">
            <v-btn
              x-large
              color="#039BE5"
              dark
              width="500px"
            >
            <v-icon>mdi-certificate</v-icon> 
              <span >전문 자격 인증</span>
            <v-spacer></v-spacer>
            <v-icon>mdi-arrow-right</v-icon>
            </v-btn>
          </div>

        </div>
      </v-col>
    </v-row>
  </v-container>
  </v-app>
</template>

<script>
import MenuNavi from '@/components/navi/MenuNavi.vue'

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

<style>
span{
  display: block;
  margin-left: 20px;
}
</style>