<template>
  <div>
    <close-navi :title="title"></close-navi>
    <div class="container-login100">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-30 p-b-35">
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
        userpwd: '',
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
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('password', this.user.userpwd);

      this.$store
        .dispatch('withdraw', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          // 회원탈퇴 성공
          if (this.message === 'SUCCESS') {
            this.showAlert('그동안 MindWiki를 이용해주셔서 감사합니다.', '회원탈퇴 성공');
          } else if (this.message === 'FAIL') {
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
