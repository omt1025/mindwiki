<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 좋아요한 게시글
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-08
  -->
  <v-container fluid>
    <v-row dense id="list">
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <v-img
            :src="card.thumbnail"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="200px"
            @click="clickParams(card.mindID)"
          >
            <v-card-title v-text="card.title"></v-card-title>
          </v-img>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn icon color="purple" @click="likemindmap(card.mindID)">
              <v-icon>mdi-heart</v-icon>
            </v-btn>

            <v-btn icon @click="scrapmindmap(card.mindID)">
              <v-icon>mdi-bookmark</v-icon>
            </v-btn>

            <v-btn icon>
              <v-icon>mdi-share-variant</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'MyWiki',
  data: () => ({
    cardsData:[],
    cards: [],
  }),
  methods: {
    // 좋아요 한 마인드맵 출력
    readlikemindmap () {
      this.$store.dispatch("readLikeMindMap", this.$store.getters.getJWT).then(() => {
        this.cards = this.$store.getters.getMessage
        console.log(this.cards)
      })
    },
    // 좋아요 눌렀을 시 실행
    likemindmap (no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disLike', 1);

      this.$store.dispatch("likeMind", form).then(() => {
        // console.log(this.$store.getters.getMessage)
        this.$router.go(this.$router.currentRoute);
      })
    },
    // 스크랩 눌렀을 시 실행
    scrapmindmap (no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disScrap', 0);

      this.$store.dispatch("scrapMind", form).then(() => {
        console.log(this.$store.getters.getMessage)
      })
    },
    // 라우터에 마인드 아이디를 보냄
    clickParams(no) {
      this.$router.push({name: 'MindMapDetail', params: {no: Number(no)}});
    }
  },
  mounted: function () {
    this.readlikemindmap ()
  }
};
</script>

<style>
  #list {
    padding: 5px;
    margin-bottom: 50px;
  }
</style>
