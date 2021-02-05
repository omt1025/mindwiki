<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : SNS 로그인 JWT저장
    * 생성일자 : 2021-02-01
    * 최종수정일자 : 2021-02-02
  -->
  <v-container fluid>
    <!-- <v-btn @click="readmymindmap">임시</v-btn> -->
    <v-row dense id="list">
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <v-img
            :src="card.src"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="200px"
            @click="clickParams(card.mindID)"
          >
            <v-card-title v-text="card.title"></v-card-title>
          </v-img>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>

            <v-btn icon>
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
  name: "FavoriteMind",
  data: () => ({
    cards: "",
  }),
  methods: {
    // 자신이 만들었던 마인드맵을 호출
    readmymindmap() {
      let form = new FormData();
      form.append("jwt", this.$store.getters.getJWT);

      this.$store.dispatch("readMyMindMap", form).then(() => {
        this.cards = this.$store.getters.getMessage;
      });
    },
    clickParams(no) {
      this.$router.push({name: 'MindMapDetail', params: {no: no}});
    }
  },
  // 페이지가 로드되면 실행되도록
  created: function () {
    this.readmymindmap()
  }
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
