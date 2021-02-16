<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 내 활동 알림
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-16
  -->
  <v-app>
    <v-card flat height="100%">
      <!-- 좋아요 누른 게시글 알림 -->
      <v-list three-line>
        <template v-for="item in items">
          <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

          <v-list-item v-else :key="item.index">
            <!-- 프로필 사진 -->
            <v-list-item-avatar>
              <v-img :src="creatorImage" @error="imageError = true" alt=""> </v-img>
            </v-list-item-avatar>

            <v-list-item-subtitle v-html="'회원님이 좋아요한 게시글'"></v-list-item-subtitle>
            <v-list-item-title
              @click="clickParams(item.mindID)"
              v-html="item.title"
            ></v-list-item-title>
          </v-list-item>
        </template>
      </v-list>
    </v-card>
    
    <v-divider></v-divider>

    <!-- 스크랩 누른 게시글 알림 -->
    <v-card flat height="100%">
      <v-list three-line id="list">
        <template v-for="scrap in scraps">
          <v-subheader v-if="scrap.header" :key="scrap.header" v-text="scrap.header"></v-subheader>

          <v-list-item v-else :key="scrap.index">
            <!-- 프로필 사진 -->
            <v-list-item-avatar>
              <v-img :src="creatorImage" @error="imageError = true" alt=""> </v-img>
            </v-list-item-avatar>

            <v-list-item-subtitle v-html="'회원님이 스크랩한 게시글'"></v-list-item-subtitle>
            <v-list-item-title
              @click="clickParams(scrap.mindID)"
              v-html="scrap.title"
            ></v-list-item-title>
          </v-list-item>
        </template>
      </v-list>
    </v-card>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';


export default {
  name: 'MyActivity',
  components: {  },
  computed: {
    ...mapGetters(['likeData, scrapData']),
    creatorImage() {
      return this.imageError ? this.defaultImage : 'creator-image.jpg';
    },
  },
  data: () => ({
    defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
    imageError: false,
    items: [],
    scraps: [],
    }),
  // 좋아요 누른 게시글을 가져옴
  created() {
    this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
      this.items = this.$store.getters.likeData;
    });
    this.$store.dispatch('readScrapMindMap', this.$store.getters.getJWT).then(() => {
      this.scraps = this.$store.getters.scrapData;
    });
  },
  methods: {
    // 라우터에 마인드 아이디를 보냄
    clickParams(no) {
      this.$router.push({ name: 'MindMapDetail', params: { no: Number(no) } });
    },
  },
};
</script>

<style scoped>
/* 영역 조정 */
.v-list-item.theme--light {
  margin-bottom: 5px;
}
</style>
