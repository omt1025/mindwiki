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

    <!-- 프로필 슬라이드 -->
    <template>
      <v-sheet class="mx-auto" max-width="375">
        <v-slide-group
          multiple
        >
          <v-slide-item
            v-for="item in searchHandler"
            :key="item.email"
          >
            <v-list-item :key="item.email" id="v-list-item">
            <v-list-item-avatar>
              <v-img
                :src="creatorImage"
                @error="imageError = true"
              ></v-img>
            </v-list-item-avatar>

            <!-- 프로필 이메일 검색  -->
            <v-list-item-subtitle
              v-if="item.email !== null"
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

    <v-divider></v-divider>

    <!-- 마인드맵 해시태그 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">해시태그 검색</div>
    </v-list-item-content>

  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  components: {},
  computed: {
    ...mapGetters(['memberList']),
    creatorImage() {
      return this.imageError ? this.defaultImage : "creator-image.jpg"
    },
    // 닉네임이 null 값일 경우 에러가 나기 때문에 email 검색
    searchHandler() {
      return this.items.filter(elem => {
        return elem.email.toLowerCase().includes(this.search.toLowerCase());
      });
    }
  },
  data() {
    return {
      search: '',
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false,
      items: [],
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
  },
  created() {
    this.readmemberlist();
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