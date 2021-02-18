<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : MakeMind.vue 마인드 생성 페이지 변경
    * 생성일자 : 2021-02-02
    * 최종수정일자 : 2021-02-16
 -->
  <div>
    <!-- 상단 Navi -->
    <back-navi :title="title" v-on:checkbtn="checkHandler" v-on:backbtn="backPage"></back-navi>
    <div class="container-login100">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-35">
        <!-- 마인드 제목 input -->
        <span class="label-input100 p-b-7" style="float: left"
          >마인드 제목
          <p style="font-size: 10px; float:right">(16자 이내)</p></span
        >
        <w-input
          class="m-b-10"
          id="title"
          ref="title"
          v-model="newmind.title"
          required
          @keypress.enter="checkHandler"
          type="text"
          :maxlength="max_title"
          placeholder="작성할 마인드의 제목"
        />
        <!-- 주제 input -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left">마인드 주제</span>
        <w-input
          class="m-t-10"
          required
          id="subject"
          ref="subject"
          v-model="newmind.subject"
          @keypress.enter="checkHandler"
          type="text"
          placeholder="작성할 마인드의 주제"
        />
        <!-- 해시태그 box -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left"
          >해시 태그
          <p style="font-size: 10px; float:right">(145자 이내, 최대 3개)</p>
        </span>
        <v-combobox
          append-icon
          flat
          v-model="chips"
          chips
          solo
          clearable
          multiple
          no-filter
          hide-details="true"
          background-color="#00000006"
          align-center
          class="combobox"
          style="float: left;"
        >
          <template v-slot:selection="{ attrs, item, select, selected }">
            <v-chip
              v-bind="attrs"
              :input-value="selected"
              close
              class="ma-2"
              color="orange"
              @click="select"
              @click:close="remove(item)"
            >
              <strong class="test">{{ item }}</strong
              >&nbsp;
            </v-chip>
          </template>
        </v-combobox>
        <!-- 마인드 설명 textarea -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left">설명</span>
        <v-textarea
          solo
          name="input-7-4"
          placeholder="마인드에 대한 설명 작성"
          class="explanation m-t-20"
          hide-details="true"
          single-line
          id="explanation"
          ref="explanation"
          v-model="newmind.explanation"
          @keypress.enter="checkHandler"
          :maxlength="max_explanation"
        ></v-textarea>
        <!-- 썸네일 -->
        <span class="label-input100 p-t-25 p-b-7" style="float: left">썸네일 설정</span>
        <input
          type="file"
          class="upload"
          @change="processFile($event)"
          @keypress.enter="checkHandler"
        />
      </div>
    </div>
  </div>
</template>

<script>
import BackNavi from '../../components/navi/BackNavi.vue';
export default {
  data() {
    return {
      title: '마인드 생성',
      chips: ['여행지', '음식', 'SNOW'], // 관심태그
      newmind: {
        title: '',
        subject: '',
        url: '',
        category: '',
        color: '',
        fx: '',
        fy: '',
        explanation: '',
        files: '',
      },
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      message: '', // 오류 받아 올 변수
      // 제목, 설명 글자 수 제한
      max_title: 16,
      max_explanation: 145,
    };
  },
  components: {
    BackNavi,
  },
  watch: {
    // 관심태그 갯수 최대 3개
    chips(value) {
      this.validateTag(value);
    },
  },
  methods: {
    // 초기 관심태그는 최대 3개
    validateTag() {
      // 3개 이상 입력시 삭제처리
      if (this.chips.length > 3) {
        this.msg['tag'] = '관심태그는 최대 3개 입니다.';
        this.remove(this.chips[this.chips.length - 1]);
      } else this.msg['tag'] = '';
    },
    backPage: function() {
      this.$store.dispatch('setMainTab', 0); // 탭 초기화(재사용 위해)
      this.$store.dispatch('setBottomNav', 'home');
      this.$router.push('/main');
    },
    processFile(event) {
      this.newmind.files = event.target.files[0];
    },

    // input 유효성 검사 메소드[HYH]
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
    // 마인드맵 생성 요청[HYH]
    makemindmap() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('title', this.newmind.title);
      form.append('subject', this.newmind.subject);
      form.append('explanation', this.newmind.explanation);
      form.append('hashtag', this.chips);
      form.append('files', this.newmind.files);

      // Actions를 호출
      this.$store
        .dispatch('makeMindMap', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;
          // 메세지에 마인드 번호가 담겨오면
          if (this.message !== null) {
            // 초기화 시킨 후
            this.$store.dispatch('setMessage', null);
            // 해당 마인드맵 상세 페이지로 이동
            this.$router.push({ name: 'MindMapUpdate', params: { no: Number(this.message) } });
            setTimeout(function() {}, 500);
            window.location.reload();
          } else {
            this.showConfirm('로그인 필요! 로그인 페이지로 이동합니다.');
          }
        })
        .catch(({ message }) => (this.msg = message));
    },
    // 관심태그 삭제[HYH]
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    // 다이얼로그 1[HYH]
    showAlert(msg, title) {
      const options = { title: title, size: 'sm' };
      this.$dialogs.alert(msg, options).then(() => {});
    },
    // 다이얼로그 2[HYH]
    showConfirm(msg) {
      const options = { title: '알림', cancelLabel: 'CANCEL' };
      this.$dialogs.confirm(msg, options).then(() => {
        this.$router.push('/login');
      });
    },
    // 생성 취소 후 알림창[HYH]
    setState(value) {
      this.value = value;
      this.$store.dispatch('setBottomNav', this.value);
      let msg = '취소 완료. "OK"를 누르고 다시 생성하려면 "생성" 누르기';
      this.showAlert(msg);
    },
  },
};
</script>

<style scoped>
.m-t-10 {
  margin-top: 10px !important;
}
/* 유효성 검사 span */
.validation {
  color: red;
  font-size: 0.6rem;
  margin-bottom: 18px;
}
/* 비밀번호 변경 팁 span */
.tip {
  font-size: 0.6rem;
  text-align: left;
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
.combobox {
  width: 100%;
}
input:focus {
  outline: none;
}
.combobox {
  float: left;
  width: 100%;
  box-sizing: border-box;
  background-clip: padding-box;
  border-radius: 0.25rem;
  border: 0;
  display: block;
  font-weight: '400';
  font-size: 0.875rem;
  height: auto;
  /* background-color: '#00000006' !important; */
  /* height: calc(1.5em + 1.25rem + 2px); */
  line-height: 1.5;
  /* padding: 0.625rem 0.75rem; */
  transition: box-shadow 0.15s ease;
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
.w-button[data-v-57059cc2] {
  padding: 0.46rem 1rem;
}
.v-chip.v-size--default {
  height: 28px;
  font-size: 13px;
}
.v-chip {
  padding: 0 10px;
}
</style>

<style>
/* 검색영역 조정 */
.v-text-field--filled > .v-input__control > .v-input__slot,
.v-text-field--full-width > .v-input__control > .v-input__slot,
.v-text-field--outlined > .v-input__control > .v-input__slot {
  align-items: stretch;
  min-height: 33px;
}
/* 돋보기 위치 조정 */
.v-text-field.v-text-field--enclosed:not(.v-text-field--rounded)
  > .v-input__control
  > .v-input__slot,
.v-text-field.v-text-field--enclosed .v-text-field__details {
  max-height: none !important;
  background-color: #00000006 !important;
  font-size: 0.875rem;
  box-shadow: none;
  box-shadow: 0 1px 3px rgba(50, 50, 93, 0.15), 0 1px 0 rgba(0, 0, 0, 0.02);
}
</style>
