<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 실시간 인기 마인드 리스트 출력
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-08
  -->
  <v-container fluid>
    <v-row dense id="list">
      <v-col v-for="card in items" :key="card.title" :cols="card.flex">
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
          <div>
            <!-- <h4>{{ card.like }}</h4> -->
          </div>
          <v-card-actions>
            <v-spacer></v-spacer>
            <div v-if="card.like">
              <v-btn icon color="purple">
                <v-icon @click="likemindmap(card.mindID)">mdi-heart</v-icon>
              </v-btn>
            </div>
            <div v-else-if="!card.like">
              <v-btn icon>
                <v-icon @click="likemindmap(card.mindID)">mdi-heart</v-icon>
              </v-btn>
            </div>

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
import { mapGetters } from 'vuex';

export default {
  name: 'LivePopular',
  data: () => ({ items: null }),

  computed: {
    ...mapGetters(['likeData, cards']),
  },
  methods: {
    // 전체 마인드맵 데이터 불러오기
    // readmindmap() {
    //   this.$store.dispatch('readMindMap', this.$store.getters.getJWT).then(() => {
    //     this.cards = this.$store.getters.getMessage;
    //   });
    //   this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
    //     this.likeData = this.$store.getters.getMessage;
    //   });
    // },
    checklike() {
      this.items = this.$store.state.cards;
      for (var j = 0; j < this.$store.state.cards.length; j++) {
        for (var i = 0; i < this.$store.state.likeData.length; i++) {
          if (this.$store.state.likeData[i]['mindID'] === this.$store.state.cards[j]['mindID']) {
            this.$set(this.items[j], 'like', true);
            break;
          } else {
            this.$set(this.items[j], 'like', false);
          }
        }
      }
      // console.log(this.cards);
    },
    check(no) {
      let isLike = false;
      for (var i = 0; i < this.$store.getters.likeData.length; i++) {
        if (this.$store.getters.likeData[i]['mindID'] === no) {
          isLike = true;
          break;
        }
      }
      return isLike;
    },
    // 좋아요 눌렀을 시 실행
    likemindmap(no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disLike', 0);

      this.$store.dispatch('likeMind', form).then(() => {
        // console.log(this.$store.getters.getMessage)
      });
    },
    // 스크랩 눌렀을 시 실행
    scrapmindmap(no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disScrap', 0);

      this.$store.dispatch('scrapMind', form).then(() => {
        // console.log(this.$store.getters.getMessage)
      });
    },
    clickParams(no) {
      this.$router.push({ name: 'MindMapDetail', params: { no: Number(no) } });
    },
  },
  created() {
    this.$store.dispatch('readMindMap', this.$store.getters.getJWT).then(() => {
      // this.cards = this.$store.getters.getMessage;
    });
    this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
      // this.likeData = this.$store.getters.getMessage;
      this.checklike();
    });
  },
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
