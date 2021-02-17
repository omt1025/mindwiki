<template>
  <v-col cols="12" id="col">
    <w-card height="100%">
      <w-card-category>
        <v-avatar class="user_avatar" size="30px">
          <img v-if="card.profileDefaultPic == null" src="@/assets/images/user.png" alt="John" />
          <img v-else :src="card.profileDefaultPic" alt="John" />
        </v-avatar>
        <p style="font-size: 1.1rem; color:black">{{ card.nickName }}</p>
        <p style="float:right; margin-right:10px">{{ card.initCreateTime.split(' ')[0] }}</p>
      </w-card-category>

      <!-- 썸네일 이미지 -->
      <v-img
        :src="card.thumbnail"
        class="white--text align-end "
        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
        height="200px"
        @click="clickParams(card)"
      >
      </v-img>
      <!-- class="justify-around" -->
      <!-- 게시글 정보가 담길 흰색 박스 -->
      <w-card-body>
        <!-- 제목 -->
        <w-card-title>{{ card.title }}</w-card-title>

        <!-- <w-card-description></w-card-description> -->

        <w-card-footer>
          <v-container>
            <v-row no-gutters>
              <v-col>
                <!-- 좋아요 버튼 토글 -->
                <div v-if="card.like" class="footer_btn">
                  <v-btn icon color="purple" @click="likemindmap(card.mindID, 1)">
                    <v-icon>mdi-heart</v-icon>
                    <p style="margin-left:.2rem">좋아요</p>
                  </v-btn>
                </div>
                <div v-else-if="!card.like" class="footer_btn">
                  <v-btn icon @click="likemindmap(card.mindID, 0)">
                    <v-icon>mdi-heart-outline</v-icon>
                    <p style="margin-left:.2rem">좋아요</p>
                  </v-btn>
                </div>
              </v-col>

              <!-- 스크랩 버튼 토글 -->
              <v-col>
                <div v-if="card.scrap" class="footer_btn">
                  <v-btn icon color="purple" @click="scrapmindmap(card.mindID, 1)">
                    <v-icon>mdi-bookmark</v-icon>
                    <p style="margin-left:.2rem">스크랩</p>
                  </v-btn>
                </div>
                <div v-else-if="!card.scrap" class="footer_btn">
                  <v-btn icon @click="scrapmindmap(card.mindID, 0)">
                    <v-icon>mdi-bookmark-outline</v-icon>
                    <p style="margin-left:.2rem">스크랩</p>
                  </v-btn>
                </div>
              </v-col>

              <v-col>
                <div class="footer_btn">
                  <v-btn icon class="mx-1">
                    <v-icon>mdi-eye</v-icon>
                    <p style="margin-left:.2rem">{{ card.viewCnt }}</p>
                  </v-btn>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </w-card-footer>
      </w-card-body>
    </w-card>
  </v-col>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'CardForm',
  data: () => ({ items: null, profile: '' }),
  props: {
    card: Object,
  },
  computed: {
    ...mapGetters(['likeData', 'scrapData', 'cards']),
  },
  created() {
    this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
      this.checklike();
    });
    this.$store.dispatch('readScrapMindMap', this.$store.getters.getJWT).then(() => {
      this.checkscrap();
    });
  },
  methods: {
    // 로그인 한 유저가 좋아요 누른 게시글 확인
    checklike() {
      for (var i = 0; i < this.$store.state.likeData.length; i++) {
        if (this.$store.state.likeData[i]['mindID'] === this.card['mindID']) {
          this.$set(this.card, 'like', true);
          break;
        } else {
          this.$set(this.card, 'like', false);
        }
      }
    },
    // 로그인 한 유저가 스크랩 한 게시글 확인
    checkscrap() {
      for (var i = 0; i < this.$store.state.scrapData.length; i++) {
        if (this.$store.state.scrapData[i]['mindID'] === this.card['mindID']) {
          this.$set(this.card, 'scrap', true);
          break;
        } else {
          this.$set(this.card, 'scrap', false);
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
        this.card.like = !this.card.like;
      });
    },
    // 스크랩 눌렀을 시 실행
    scrapmindmap(no, isScrap) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disScrap', isScrap);

      this.$store.dispatch('scrapMind', form).then(() => {
        this.card.scrap = !this.card.scrap;
      });
    },
    // 라우터에 마인드 아이디를 보냄
    clickParams(mind) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', mind.mindID);

      // this.$store.dispatch('readMapData', form).then(() => {
      //   this.map = (Object)(this.$store.getters.getMapData)
        this.$router.push({ name: 'MindMapDetail', params: { no: Number(mind.mindID) } });
      // });
    },
  },
};
</script>

<style>
#col {
  padding: 10px 20px;
}
.card-header[data-v-5f8090c8] {
  padding: 0.5rem 1.5rem !important;
  float: left;
}
.card-header {
  padding: 0.5rem 1.5rem !important;
  float: left !important;
}
.text[data-v-5c6dc5fc] {
  margin-top: 0.5rem;
}
.user_avatar {
  margin-left: 0.5rem;
}
.text_danger {
  color: #212529 !important;
}
.text[data-v-5c6dc5fc] {
  color: #212529 !important;
}
</style>

<style scoped>
.v-avatar {
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 5px;
  float: left;
}
.card-body[data-v-7376b4b2] {
  padding: 0rem;
}
.card-body {
  padding: 0rem;
}
.card-footer {
  background-color: white;
  display: block;
}
.card-footer[data-v-8bb473bc][data-v-3a752450] {
  clear: both;
}
.v-btn--icon.v-size--default {
  float: left;
}
.card-footer[data-v-8bb473bc] {
  padding: 0rem;
}
.v-application p {
  margin-bottom: 0rem;
}
.footer_btn {
  width: auto;
  display: flex;
  justify-content: center;
}
.v-btn--icon.v-size--default {
  width: auto;
  text-align: center;
}
.container {
  padding: 0px;
  margin: 0px;
  min-height: 0px;
  display: flex;
}
/* .col-xl,
.col-xl-auto,
.col-xl-12,
.col-xl-11,
.col-xl-10,
.col-xl-9,
.col-xl-8,
.col-xl-7,
.col-xl-6,
.col-xl-5,
.col-xl-4,
.col-xl-3,
.col-xl-2,
.col-xl-1,
.col-lg,
.col-lg-auto,
.col-lg-12,
.col-lg-11,
.col-lg-10,
.col-lg-9,
.col-lg-8,
.col-lg-7,
.col-lg-6,
.col-lg-5,
.col-lg-4,
.col-lg-3,
.col-lg-2,
.col-lg-1,
.col-md,
.col-md-auto,
.col-md-12,
.col-md-11,
.col-md-10,
.col-md-9,
.col-md-8,
.col-md-7,
.col-md-6,
.col-md-5,
.col-md-4,
.col-md-3,
.col-md-2,
.col-md-1,
.col-sm,
.col-sm-auto,
.col-sm-12,
.col-sm-11,
.col-sm-10,
.col-sm-9,
.col-sm-8,
.col-sm-7,
.col-sm-6,
.col-sm-5,
.col-sm-4,
.col-sm-3,
.col-sm-2,
.col-sm-1,
.col,
.col-auto,
.col-12,
.col-11,
.col-10,
.col-9,
.col-8,
.col-7,
.col-6,
.col-5,
.col-4,
.col-3,
.col-2,
.col-1 {
  padding-bottom: 0.15rem;
  display: flexbox;
} */
.v-application .text-h4 {
  font-size: 1rem !important;
  float: left;
  padding-left: 20px;
}
.v-application p[data-v-3a752450] {
  float: left;
}
</style>
