<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 서버 응답 변경으로 fail 메세지 수정
    * 생성일자 : 2021-01-21
    * 최종수정일자 : 2021-02-19
 -->
  <div>
    <!-- 상단 Navi -->
    <close-navi :title="title"></close-navi>

    <div class="container-login100">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-35">
        <!-- 현재 비밀번호 -->
        <span class="label-input100 p-b-7" style="float: left">현재 비밀번호</span>
        <w-input
          class="m-b-10"
          id="userpwd"
          ref="userpwd"
          v-model="user.userpwd"
          type="password"
          required
          placeholder="현재 비밀번호"
        />

        <!-- 회원탈퇴 comment -->
        <div class="m-t-25 m-b-25">
          <p style="text-align: left" class="m-b-5">
            '{{ nickName }}' 님, 안녕하세요!<br />
            계정을 삭제하려고 하신다니 아쉽습니다.<br />
          </p>
          <p class="tip">
            * 게시글, 팔로우를 포함한 모든 데이터가 영구적으로 삭제됩니다.<br />
            * 탈퇴 후 다시 가입하여도, 게시판 등 이용 기록의 복구는 불가합니다. <br />
            * 더 이상 이 계정에 로그인 할 수 없으며, 아무도 해당 계정을 볼 수 없게 됩니다.
          </p>
        </div>

        <!-- 회원탈퇴 버튼 -->
        <div class="wrap-login100-form-btn">
          <div class="login100-form-bgbtn"></div>
          <button class="login100-form-btn" @click="checkHandler">
            탈퇴하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CloseNavi from '@/components/navi/CloseNavi.vue';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      title: '회원 탈퇴',
      user: {
        userpwd: '', // 현재 비밀번호
      },
      message: '', // 오류 받아 올 변수
    };
  },
  components: {
    CloseNavi,
  },
  computed: {
    ...mapGetters(['nickName']),
  },
  methods: {
    backPage: function() {
      this.$router.push('/main/menu');
    },
    // input 유효성 검사 메소드[YJS]
    checkHandler() {
      let err = true; // 에러 확인 변수
      let msg = ''; // 다이얼로그에 띄울 메세지

      // 현재 비밀번호 input이 공백인 경우
      !this.user.userpwd && ((msg = '현재 비밀번호를 입력해주세요'), (err = false));

      if (!err) this.showAlert(msg, '양식 오류');
      else this.withdraw();
    },
    // 회원탈퇴
    withdraw() {
      // 탭 초기화(재사용 위해)
      this.$store.dispatch('setMessage', null);
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');

      // 서버통신 위해 form에 담기
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('password', this.user.userpwd);

      // 서버통신 위해 action호출
      this.$store
        .dispatch('withdraw', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          // 회원탈퇴 성공
          if (this.message === 'SUCCESS') {
            this.showAlert('그동안 MindWiki를 이용해주셔서 감사합니다.', '회원탈퇴 성공');
          } else if (this.message === 'WITHDRAWAL_FAIL_INCORRECT_PASSWORD') {
            // 현재 비밀번호와 일치하지 않음
            this.showAlert('현재 비밀번호가 일치하지 않습니다.', '회원탈퇴 실패');
          } else {
            // 서버 오류
            this.showAlert(
              '서버가 원활하지 않아 현재 회원가입이 불가합니다. 잠시후 다시 시도해주세요.',
              '서버 오류'
            );
          }
        })
        .catch(({ message }) => (this.msg = message));
    },
    // 다이얼로그[YJS]
    showAlert(msg, title) {
      const options = { title: title, size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        // 회원 탈퇴 성공시 모달창 ok버튼 누르면 시작 페이지로 이동하기
        if (title === '회원탈퇴 성공' && res['ok'] === true) {
          this.$store.dispatch('setMessage', null);
          this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/').catch(() => {}));
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
/* 비밀번호 변경 팁 span */
.tip {
  font-size: 0.6rem;
  text-align: left;
}
.container-login100 {
  align-items: baseline;
}
.input[data-v-565818fe] {
  background-color: #00000006 !important;
}
</style>
