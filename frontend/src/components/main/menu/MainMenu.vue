<template>
  <v-app v-bind:style="{ backgroundColor : color }">
    <MenuNavi />
    <v-container fluid class="pa-0" >
      <v-row align="center">
        <v-col cols="12" sm="6">
          <div class="text-center">
            <!-- 비회원일 때 보여줌 -->

              <v-btn
                x-large
                width="500px"
                to="/login"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-login</v-icon>
                <span>로그인</span>
                <v-spacer></v-spacer>
              </v-btn>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-account-plus</v-icon>
                <span>회원가입</span>
                <v-spacer></v-spacer>
              </v-btn>
            </div>
            <!--  -->

            <!-- 회원일 때 보여줌 -->
            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                @click.native="logout"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-logout</v-icon>
                <span>로그아웃</span>
                <v-spacer></v-spacer>
              </v-btn>
            </div>
            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                to="/main/menu/changepassword"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-lock-reset</v-icon>
                <span>비밀번호 변경</span>
                <v-spacer></v-spacer>

                <v-icon>mdi-arrow-right</v-icon>
              </v-btn>
            </div>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-trophy</v-icon>
                <span>업적 관리</span>
                <v-spacer></v-spacer>
                <v-icon>mdi-arrow-right</v-icon>
              </v-btn>
            </div>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon color="green" v-show="!bookshelfClick">mdi-bookshelf</v-icon>

                <v-icon v-show="bookshelfClick">mdi-bookshelf</v-icon>
                
                <span>내 책장 공개</span>
                <v-spacer></v-spacer>

                <v-switch
                  v-on:click="bookshelfClick = !bookshelfClick"
                  color="green"
                ></v-switch>
              </v-btn>
            </div>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon color="green" v-show="!bellClick">mdi-bell</v-icon>

                <v-icon v-show="bellClick">mdi-bell-remove-outline</v-icon>

                <span>푸시 알림 설정</span>
                <v-spacer></v-spacer>

                <v-switch
                  v-on:click="bellClick = !bellClick"
                  color="green"
                  ></v-switch>
              </v-btn>
            </div>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
              >
                <v-icon>mdi-saw-blade</v-icon>
                <span>MIND 관리 및 설정</span>
                <v-spacer></v-spacer>
                <v-icon>mdi-arrow-right</v-icon>
              </v-btn>
            </div>

            <div class="my-5">
              <v-btn
                x-large
                width="500px"
                depressed
                color="white"
                id="no-background-hover"
                :ripple="false"
                >
                <v-icon>mdi-certificate</v-icon>
                <span>전문 자격 인증</span>
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
import MenuNavi from '@/components/navi/MenuNavi.vue';
import axios from 'axios';

export default {
  name: 'MainMenu',
  components: {
    MenuNavi,
  },
  data: () => ({
    bellClick: true,
    bookshelfClick: true,
    color: 'white'
  }),
  methods: {
    logout() {
      axios.post(`http://localhost:8000/mindwiki/mind/logout`)
    }
  }
};
</script>

<style scoped>
span {
  display: block;
  margin-left: 20px;
}
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Black+Han+Sans:400');
@import url('https://fonts.googleapis.com/css?family=Noto%20Sans%20KR');
@import url('https://fonts.googleapis.com/css?family=Poor+Story:400');

#no-background-hover::before {
  background-color: transparent !important;
}
#app {
  input {
    background: #fff;
  }
}
// span {
//   font-family: "Black Han Sans";
// }
// span {
//   font-family: "Noto Sans KR";
// }
span {
  font-family: "Poor Story";
}
</style>
