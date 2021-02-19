<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : UI고치기
    * 생성일자 : 2021-01-27
    * 최종수정일자 : 2021-02-19
  -->
  <div id="app">
    <!-- 검색 도구 -->
    <v-text-field
      type="search"
      class="search"
      placeholder="검색"
      rounded
      hide-details="true"
      v-model="search"
    >
      <template v-slot:append>
        <v-icon>mdi-magnify</v-icon>
      </template>
    </v-text-field>

    <!-- 프로필 검색 -->
    <v-list-item-content>
      <div class="caption purple--text txt2">프로필 검색</div>
    </v-list-item-content>

    <!-- 프로필 검색결과 -->
    <template v-if="search.length != 0">
      <v-sheet class="mx-auto">
        <v-slide-group multiple>
          <v-slide-item v-for="item in searchProfileHandler" :key="item.email">
            <div :key="item.email" id="v-list-item" v-bind:userId="userId">
              <v-list-item-avatar>
                <!-- 프로필 사진에서 이미지 없을 때 default 이미지 -->
                <v-img
                  v-if="item.profileDefaultPic === null"
                  :src="creatorImage"
                  @error="imageError = true"
                  @click="getUserEmail(item.email, item.nickName, item.profileDefaultPic)"
                ></v-img>
                <!-- 프로필에서 사진 등록하면 이미지 변경 -->
                <v-img
                  v-else
                  :src="item.profileDefaultPic"
                  @error="imageError = true"
                  @click="getUserEmail(item.email, item.nickName, item.profileDefaultPic)"
                ></v-img>
              </v-list-item-avatar>

              <!-- 프로필 이메일 검색  -->
              <v-list-item-subtitle v-html="item.nickName" id="subtitle"></v-list-item-subtitle>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </template>

    <v-divider></v-divider>

    <!-- 마인드맵 제목 검색 -->
    <v-list-item-content>
      <div class="caption purple--text txt2">제목 검색</div>
    </v-list-item-content>

    <!-- 마인드맵 제목 검색 결과 -->
    <template v-if="search.length != 0">
      <v-sheet class="mx-auto">
        <v-slide-group multiple>
          <!-- Slide에 들어가는 card를 item만큼 만든다 -->
          <v-slide-item v-for="item in searchTitleHandler" :key="item.email">
            <v-list-item>
              <v-card class="mx-auto" max-width="400">
                <v-img
                  class="black--text align-end"
                  height="160px"
                  width="160px"
                  :src="item.thumbnail"
                  @click="clickParams(item.mindID)"
                >
                  <v-card-subtitle v-html="item.title"></v-card-subtitle>
                </v-img>
              </v-card>
            </v-list-item>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </template>
    <v-divider></v-divider>

    <!-- 마인드맵 해시태그 검색 -->
    <v-list-item-content>
      <div class="caption purple--text txt2">해시태그 검색</div>
    </v-list-item-content>

    <!-- 마인드맵 해시태그 검색 결과 -->
    <template v-if="search.length != 0">
      <v-sheet class="mx-auto">
        <v-slide-group multiple>
          <!-- Slide에 들어가는 card를 item만큼 만든다 -->
          <v-slide-item v-for="item in searchHashtagHandler" :key="item.email">
            <v-list-item>
              <v-card class="mx-auto" max-width="400">
                <v-img
                  class="black--text align-end"
                  height="160px"
                  width="160px"
                  :src="item.thumbnail"
                  @click="clickParams(item.mindID)"
                >
                  <v-card-subtitle v-html="item.hashtag"></v-card-subtitle>
                </v-img>
              </v-card>
            </v-list-item>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  components: {},
  computed: {
    // 멤버 목록 가져오기
    ...mapGetters(['memberList']),
    // 전체 마인드 가져오기
    ...mapGetters(['cards']),
    // default 이미지 설정
    creatorImage() {
      return this.imageError ? this.defaultImage : 'creator-image.jpg';
    },
    // 닉네임이 null 값일 경우 에러가 나기 때문에 email 검색
    searchProfileHandler() {
      return this.items.filter((elem) => {
        return elem.nickName.toLowerCase().includes(this.search.toLowerCase());
      });
    },
    // 제목 검색
    searchTitleHandler() {
      return this.minditems.filter((elem) => {
        return elem.title.toLowerCase().includes(this.search.toLowerCase());
      });
    },
    // 해시태그 검색
    searchHashtagHandler() {
      return this.minditems.filter((elem) => {
        return elem.hashtag.toLowerCase().includes(this.search.toLowerCase());
      });
    },
  },
  data() {
    return {
      search: '',
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false,
      items: [],
      minditems: [],
      userId: localStorage.getItem('user-id'),
      user: {
        files: '', // 프로필사진
      },
      profile: '',
      message: '',
    };
  },
  methods: {
    readmemberlist() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      this.$store.dispatch('readMemberList', form).then(() => {
        this.items = this.$store.getters.memberList;
      });
    },
    // 썸네일 클릭 시 상세 페이지로 이동[OMT]
    clickParams(no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', no);
      this.$store.dispatch('readMapData', form).then(() => {
        this.map = Object(this.$store.getters.getMapData);
        this.$router.push({ name: 'MindMapDetail', params: { no: Number(no) } });
      });
    },
    // 회원 이메일, 닉네임 가져옴
    // query : 새로고침 시 데이터 유지
    getUserEmail(email, nickName, profileDefaultPic) {
      this.$router.push({
        name: 'UserProfile',
        query: { email: email, nickName: nickName, profileDefaultPic: profileDefaultPic },
      });
    },
  },
  created() {
    this.readmemberlist();
    this.$store.dispatch('readMindMap', this.$store.getters.getJWT).then(() => {
      this.minditems = this.$store.state.cards;
    });

    // 프로필 정보 받아오기
    let form = new FormData();
    form.append('jwt', this.$store.getters.getJWT);
    this.$store.dispatch('myProfile', form).then(() => {
      // 응답 결과
      this.message = this.$store.getters.message;
      this.profile = this.$store.getters.profile;
      if (this.message === 'FAIL')
        this.showAlert('세션이 만료되었습니다. 다시 로그인 해 주세요.', '프로필 수정');
      else {
        this.user.files = this.profile.profileDefaultPic;
      }
    });
  },
};
</script>

<style scoped>
#app {
  padding-left: 10px;
  padding-right: 10px;
}
/* 검색 css */
.search {
  height: 40px;
  padding: 0 5px;
  margin-top: 10px;
}
/* 검색 돋보기 위치 */
.mdi:before,
.mdi-set {
  position: fixed;
}
.v-list-item__title {
  text-align: left;
}
.subtitle {
  text-align: left;
  margin-top: 6px;
}
.v-list-item__content {
  padding: 11px 0;
}
hr {
  margin-top: 0.4rem;
  margin-bottom: 0.4rem;
  margin-left: 1.1rem;
  margin-right: 1.1rem;
}
#v-list-item {
  height: 80px;
}
#subtitle {
  position: fixed;
  bottom: 0%;
}
.txt2 {
  text-align: left;
  margin-left: 20px;
  font-family: Poppins-Medium;
  font-size: 1.1rem !important;
}

@media (min-width: 320px) and (max-width: 480px) {
  #app {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  #app {
    max-width: 540px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}
.v-slide-group__content {
  padding-left: 20px;
  padding-right: 20px;
}
.v-list-item__subtitle {
  margin-top: 5px !important;
}
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Sunflower:wght@300&display=swap" rel="stylesheet');

#subtitle {
  font-family: 'Noto Sans KR';
  height: 40px;
}
</style>

<style>
/* 돋보기 위치 조정 */
#searchIcon {
  position: fixed;
  bottom: 23%;
  right: 6%;
}
/* 구분선 */
#divider {
  margin: 0;
}
/* 검색바 위치 */
.searche {
  position: fixed;
  right: 5%;
  top: 25%;
  height: 10px;
}
/* 검색영역 조정 */
.v-text-field--filled > .v-input__control > .v-input__slot,
.v-text-field--full-width > .v-input__control > .v-input__slot,
.v-text-field--outlined > .v-input__control > .v-input__slot {
  align-items: stretch;
  min-height: 40px;
}
/* 돋보기 위치 조정 */
.v-text-field.v-text-field--enclosed:not(.v-text-field--rounded)
  > .v-input__control
  > .v-input__slot,
.v-text-field.v-text-field--enclosed .v-text-field__details {
  max-height: 12px;
}
.v-application--is-ltr .v-text-field .v-input__append-inner {
  margin-top: 20px;
}
.v-text-field fieldset,
.v-text-field .v-input__control {
  background-color: #00000006 !important;
  font-size: 0.875rem;
  box-shadow: none;
  box-shadow: 0 1px 3px rgba(50, 50, 93, 0.15), 0 1px 0 rgba(0, 0, 0, 0.02);
}
</style>

<style>
.v-input input {
  max-height: 40px !important;
}
</style>
