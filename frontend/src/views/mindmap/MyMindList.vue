<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : SNS 로그인 JWT저장
    * 생성일자 : 2021-02-01
    * 최종수정일자 : 2021-02-02
  -->
  <v-card max-width="400" class="mx-auto">
    <!-- 나의 게시물 눌렀을 때 상단 네비게이션-->
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>

    </v-toolbar>

    <!-- 생성한 게시물을 보여줌 -->
    <v-container>
      <v-row dense id="list">
        <!-- <v-col v-for="card in cards" :key="card.title" :cols="card.flex"> -->
        <v-col v-for="card in cards" :key="card.title" cols="12">
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
  </v-card>
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
        // console.log(this.cards)
      });
    },
    clickParams(no) {
      this.$router.push({name: 'MindMapDetail', params: {no: Number(no)}});
    },
    // 프로필을 통해 게시물로 접근한 경우, 뒤로갔을 때 프로필로 되돌아감
    backPage: function() {
      this.$router.push('/main')
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
