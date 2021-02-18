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
        <!-- 프로필 사진 input -->
        <span class="label-input100 p-b-7" style="float: left">프로필 사진</span>
        <input
          type="file"
          class="upload p-b-7"
          id="input-imgage"
          @change="processFile($event)"
          @keypress.enter="checkHandler"
        />
        <v-avatar class="user_avatar" size="120px">
          <img v-if="profile.profileDefaultPic !== null" :src="user.origin" alt="John" />
          <img
            v-else
            src="@/assets/images/mindwiki_logo-color.png"
            id="preview-imgage"
            alt="John"
          />
        </v-avatar>

        <!-- 이메일 input -->
        <span class="label-input100 p-t-20 p-b-7" style="float: left">이메일 </span>
        <w-input
          class="m-t-10"
          id="useremail"
          ref="useremail"
          background-color="red"
          v-model="this.profile.email"
          required
          disabled
          @keypress.enter="checkHandler"
          type="email"
          placeholder="이메일을 입력해주세요."
        />

        <!-- realName input -->
        <span class="label-input100 p-t-20 p-b-7" style="float: left">이름</span>
        <w-input
          class="m-t-10"
          required
          id="username"
          ref="username"
          v-model="this.profile.realName"
          @keypress.enter="checkHandler"
          type="text"
          disabled
          placeholder="이름을 입력해주세요."
        />

        <!-- nickName input -->
        <span class="label-input100 p-t-20 p-b-7" style="float: left"
          >닉네임
          <p style="font-size: 10px; float:right">(16자 이내)</p>
        </span>
        <w-input
          class="m-t-10"
          required
          id="usernickName"
          ref="usernickName"
          v-model="user.usernickName"
          @keypress.enter="checkHandler"
          type="text"
          :maxlength="max_nickName"
          placeholder="닉네임을 입력해주세요."
        />

        <!-- 핸드폰번호 input -->
        <span class="label-input100 p-t-20 p-b-7" style="float: left"
          >핸드폰 번호
          <p style="font-size: 10px; float:right">('-' 제외)</p>
        </span>
        <w-input
          class="m-t-10"
          required
          id="phoneNumber"
          ref="phoneNumber"
          v-model="user.phoneNumber"
          @keypress.enter="checkHandler"
          type="text"
          :maxlength="max_phone"
          placeholder="핸드폰번호를 입력해주세요."
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
      // 계정
      user: {
        username: '', // 이름
        usernickName: '', // 닉네임
        useremail: '', // 이메일
        phoneNumber: '', // 핸드폰
        files: null, // 프로필사진
        origin: '',
      },
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      message: '', // 오류 받아 올 변수
      profile: '',
      // 글자 수 제한
      max_nickName: 16,
      max_phone: 11,
    };
  },
  created() {
    let form = new FormData();
    form.append('jwt', this.$store.getters.getJWT);
    this.$store.dispatch('myProfile', form).then(() => {
      // 응답 결과
      this.message = this.$store.getters.message;
      this.profile = this.$store.getters.profile;
      if (this.message === 'FAIL')
        this.showAlert('세션이 만료되었습니다. 다시 로그인 해 주세요.', '프로필 수정');
      else {
        this.user.usernickName = this.profile.nickName;
        this.user.phoneNumber = this.profile.phoneNumber;
        this.user.origin = this.profile.profileDefaultPic;
      }
    });
  },
  methods: {
    backPage: function() {
      // 내 프로필 화면으로 이동
      this.$router.push('/main');
    },
    processFile(event) {
      this.user.files = event.target.files[0];
      const previewImage = document.getElementById('preview-imgage');
      previewImage.setAttribute('src', URL.createObjectURL(this.user.files));
    },
    // input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지
      // 닉네임 input이 공백인 경우
      !this.user.usernickName && ((msg = '닉네임을 입력해주세요.'), (err = false));
      // 핸드폰번호 input이 공백인 경우
      err && !this.user.phoneNumber && ((msg = '핸드폰 번호를 입력해주세요.'), (err = false));
      if (!err) this.showAlert(msg, '양식 오류');
      else this.revise();
    },
    revise() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('phoneNumber', this.user.phoneNumber);
      form.append('nickName', this.user.usernickName);
      if (this.user.files != null) {
        form.append('files', this.user.files);
      }

      this.$store.dispatch('changeProfile', form).then(() => {
        // 응답 결과
        this.message = this.$store.getters.message;
        if (this.message === 'FAIL')
          this.showAlert('세션이 만료되었습니다. 다시 로그인 해 주세요.', '프로필 수정');
        else {
          localStorage.setItem('user-nickname', this.user.usernickName);
          this.showAlert('수정을 완료하였습니다.', '프로필 수정 성공');
        }
      });
    },
    // 다이얼로그[YJS]
    showAlert(msg, title) {
      const options = { title: title, size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        // 세션만료로 로그아웃 처리
        if (title === '프로필 수정' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null);
          this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
        } else if (title === '프로필 수정 성공' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null);
          this.$router.push('/main');
        }
      });
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
#useremail,
#username {
  background-color: #f1f5f8 !important;
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
.upload {
  width: 100%;
  display: block;
  padding-top: 3px;
  padding-left: 2px;
  /* background-color: #00000006; */
  color: rgb(111, 115, 120);
  font-size: 0.8rem;
  height: auto;
}
</style>
