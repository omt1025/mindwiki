<template>
  <v-col cols="12" id="col">
    <v-card height="100%">
      <v-img
        :src="card.thumbnail"
        class="white--text align-end"
        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
        height="200px"
        @click="clickParams(card)"
      >
        <v-card-title v-text="card.title"></v-card-title>
      </v-img>

      <v-card-actions>
        <v-spacer></v-spacer>

        <div v-if="card.like">
          <v-btn icon color="purple">
            <v-icon @click="likemindmap(card.mindID, 1)">mdi-heart</v-icon>
          </v-btn>
        </div>
        <div v-else-if="!card.like">
          <v-btn icon>
            <v-icon @click="likemindmap(card.mindID, 0)">mdi-heart</v-icon>
          </v-btn>
        </div>

        <div v-if="card.scrap">
          <v-btn icon color="purple">
            <v-icon @click="scrapmindmap(card.mindID, 1)">mdi-bookmark</v-icon>
          </v-btn>
        </div>
        <div v-else-if="!card.scrap">
          <v-btn icon>
            <v-icon @click="scrapmindmap(card.mindID, 0)">mdi-bookmark</v-icon>
          </v-btn>
        </div>

        <v-btn icon>
          <v-icon>mdi-share-variant</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-col>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'CardForm',
  data: () => ({ items: null }),
  props: {
    card: Object
  },
  computed: {
    ...mapGetters(['likeData, scrapData cards']),
  },
  methods: {
    // 로그인 한 유저가 좋아요 누른 게시글 확인
    checklike() {
      this.items = this.card;
      for (var i = 0; i < this.$store.state.likeData.length; i++) {
        if (this.$store.state.likeData[i]['mindID'] === this.card['mindID']) {
          this.$set(this.items, 'like', true);
          break;
        } else {
          this.$set(this.items, 'like', false);
        }
      }
    },
    // 로그인 한 유저가 스크랩 한 게시글 확인
    checkscrap() {
      this.items = this.card;
      for (var i = 0; i < this.$store.state.scrapData.length; i++) {
        if (this.$store.state.scrapData[i]['mindID'] === this.items['mindID']) {
          this.$set(this.items, 'scrap', true);
          break;
        } else {
          this.$set(this.items, 'scrap', false);
        }
      }
    },
    // 좋아요 눌렀을 시 실행
    likemindmap(no, isLike) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disLike', isLike);

      this.$store.dispatch('likeMind', form).then(() => {
        this.items.like = !this.items.like;
      });
    },
    // 스크랩 눌렀을 시 실행
    scrapmindmap(no, isScrap) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disScrap', isScrap);

      this.$store.dispatch('scrapMind', form).then(() => {
        this.items.scrap = !this.items.scrap;
      });
    },
    // 라우터에 마인드 아이디를 보냄
    clickParams(mind) {
      this.$router.push({ name: 'MindMapDetail', params: { no: Number(mind.mindID) } });
    },
  },
  created () {
    this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
      this.checklike();
    });
    this.$store.dispatch('readScrapMindMap', this.$store.getters.getJWT).then(() => {
      this.checkscrap();
    });
  }
};
</script>

<style>
#col {
  padding: 10px 20px;
}
</style>
