<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 회원 프로필 검색
    * 생성일자 : 2021-01-27
    * 최종수정일자 : 2021-02-13
  -->
  <v-app>
    <!-- 검색 도구 -->
    <v-text-field
      type="search"
      class="search"
      placeholder="검색"
      outlined
      v-model="search"
      @keypress.enter="onInputKeyword"
    >
      <template v-slot:append>
        <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
      </template>
    </v-text-field>

    <!-- 프로필 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">프로필 검색</div>
    </v-list-item-content>

    <!-- 프로필 검색결과 -->
    <template>
      <v-sheet class="mx-auto" max-width="375">
        <v-slide-group
          multiple
        >
          <v-slide-item
            v-for="item in searchProfileHandler"
            :key="item.email"
          >
            <v-list-item
            :key="item.email"
            id="v-list-item"
            v-bind:userId="userId"
            v-if="userId !== item.email"
          >
            <v-list-item-avatar>
              <v-img
                :src="creatorImage"
                @error="imageError = true"
                @click="goUserProfile(item.email)"
              ></v-img>
            </v-list-item-avatar>

            <!-- 프로필 이메일 검색  -->
            <v-list-item-subtitle
              v-html="item.email.split('@')[0]"
              id="subtitle"
            ></v-list-item-subtitle>

            </v-list-item>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </template>

    <v-divider></v-divider>
    
    <!-- 마인드맵 제목 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">제목 검색</div>
    </v-list-item-content>

    <!-- 마인드맵 제목 검색 결과 -->
    <template>
      <v-sheet class="mx-auto" max-width="375">
        <v-slide-group multiple>
          <!-- Slide에 들어가는 card를 item만큼 만든다 -->
          <v-slide-item v-for="item in searchTitleHandler" :key="item.email">
            <v-list-item>
              <v-card class="mx-auto" max-width="400">
                <v-img
                  class="black--text align-end"
                  height="160px"
                  width= "160px"
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
      <div class="caption purple--text">해시태그 검색</div>
    </v-list-item-content>

    <!-- 마인드맵 해시태그 검색 결과 -->
    <template>
      <v-sheet class="mx-auto" max-width="375">
        <v-slide-group multiple>
          <!-- Slide에 들어가는 card를 item만큼 만든다 -->
          <v-slide-item v-for="item in minditems" :key="item.email">
            <v-list-item>
              <v-card class="mx-auto" max-width="400">
                <v-img
                  class="black--text align-end"
                  height="160px"
                  width= "160px"
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

  </v-app>
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
      return this.imageError ? this.defaultImage : "creator-image.jpg"
    },
    // 닉네임이 null 값일 경우 에러가 나기 때문에 email 검색
    searchProfileHandler() {
      return this.items.filter(elem => {
        return elem.email.toLowerCase().includes(this.search.toLowerCase());
      });
    },
    // 제목 검색
    searchTitleHandler() {
      return this.minditems.filter(elem => {
        return elem.title.toLowerCase().includes(this.search.toLowerCase());
      });
    },
    // 해시태그 검색
    // searchHashtagHandler() {
    //   return this.minditems.filter(elem => {
    //     return elem.hashtag.toLowerCase().includes(this.search.toLowerCase());
    //   });
    // },
  },
  data() {
    return {
      search: '',
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false,
      items: [],
      minditems: [],
      userId: localStorage.getItem('user-id'),
    };
  },
  methods: {
    onInputKeyword: function(event) {
      this.$emit('input-change', event.target.value);
    },
    readmemberlist() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);

      this.$store.dispatch('readMemberList', form).then(() => {
        this.items = this.$store.getters.memberList;
      })
    },
    // 썸네일 클릭 시 상세 페이지로 이동[OMT]
    clickParams(no) {
      this.$router.push({name: 'MindMapDetail', params: {no: Number(no)}});
    },
    // 회원 이메일 가져옴
    goUserProfile(email) {
      console.log(email)
    }
  },
  created() {
    this.readmemberlist();
    this.$store.dispatch('readMindMap', this.$store.getters.getJWT).then(() => {
    this.minditems = this.$store.state.cards;
    });
  },
};
</script>

<style scoped>
/* 검색 css */
.search {
  height: 40px;
}
/* 검색 돋보기 위치 */
.mdi:before, .mdi-set {
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
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Sunflower:wght@300&display=swap" rel="stylesheet');

#subtitle {
  font-family: 'Noto Sans KR';
}
</style>
