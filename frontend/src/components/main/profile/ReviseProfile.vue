<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 회원정보 수정페이지
    * 생성일자 : 2021-02-16
    * 최종수정일자 : 2021-02-16
 -->
  <div>
    <!-- 상단 Navi -->
    <back-navi :title="title" v-on:checkbtn="checkHandler" v-on:backbtn="backPage"></back-navi>

    <div class="container-login100">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-35">
        <!-- 이메일 input -->
        <span class="label-input100 p-b-7" style="float: left">이메일 </span>
        <!-- <p style="font-size: 10px; float:right">(16자 이내)</p> -->
        <w-input
          class="m-b-10"
          id="useremail"
          ref="useremail"
          v-model="user.useremail"
          required
          @keypress.enter="checkHandler"
          type="email"
          placeholder="이메일을 입력해주세요."
        />

        <!-- realName input -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left">이름</span>
        <w-input
          class="m-t-10"
          required
          id="username"
          ref="username"
          v-model="user.username"
          @keypress.enter="checkHandler"
          type="text"
          placeholder="이름을 입력해주세요."
        />
      </div>
    </div>
  </div>
</template>

<script>
import BackNavi from '../../navi/BackNavi.vue';
export default {
  components: { BackNavi },
  data() {
    return {
      title: '프로필정보 수정',
      chips: ['여행지', '음식', 'SNOW'], // 관심태그
      // 회원가입 계정
      user: {
        username: '', // 이름
        usernickName: '', // 닉네임
        useremail: '', // 이메일
        userpwd: '', // 비밀번호
        userpwd_check: '', // 비밀번호 확인
        interest: {}, // 관심태그
      },
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      message: '', // 오류 받아 올 변수
      // 제목, 설명 글자 수 제한
      max_title: 16,
      max_explanation: 145,
    };
  },
  methods: {
    backPage: function() {
      // 내 프로필 화면으로 이동
      this.$router.push('/main');
    },
    // input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지

      // 제목 input이 공백인 경우
      !this.newmind.title && ((msg = '마인드 제목을 입력해주세요.'), (err = false));

      // 주제 input이 공백인 경우
      err &&
        !this.newmind.subject &&
        ((msg = '루트 노드가 될 주제를 입력해주세요.'), (err = false));

      // 비밀번호 확인 inputdl 공백인 경우
      err &&
        !this.newmind.explanation &&
        ((msg = '마인드에 대한 설명을 적어주세요.'), (err = false));

      if (!err) this.showAlert(msg, '양식 오류');
      else this.makemindmap();
    },
  },
};
</script>

<style scoped>
.m-t-10 {
  margin-top: 10px !important;
}
.container-login100 {
  width: 100%;
  min-height: auto;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: normal;
  padding: 15px;
  background-repeat: no-repeat;
  background-size: cover;
}
.input[data-v-565818fe] {
  background-color: #00000006 !important;
  color: black;
}
input:focus {
  outline: none;
}
.v-text-field {
  padding-top: 0;
  margin-top: 0;
}
.v-input__slot {
  margin-bottom: 0;
}
.v-messages {
  min-height: 0 !important;
}
.v-text-field__details {
  min-height: 0 !important;
}
.explanation {
  display: block;
}
.v-text-field.v-text-field--solo .v-input__control {
  min-height: 60px;
}
.v-input__slot {
  margin-bottom: 0px;
}
</style>
