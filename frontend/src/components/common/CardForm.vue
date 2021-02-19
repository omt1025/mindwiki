<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 코드 정리
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-18
  -->
  <v-col cols="12" id="col">
    <!-- style="position:relative"  -->
    <w-card height="100%">
      <w-card-category>
        <div @click="getUserEmail(card.admin, card.nickName, card.profileDefaultPic)">
          <v-avatar class="user_avatar" size="30px">
            <img v-if="card.profileDefaultPic == null" src="@/assets/images/user.png" alt="John" />
            <img v-else :src="card.profileDefaultPic" alt="John" />
          </v-avatar>
          <p style="font-size: 1.1rem; color:black">{{ card.nickName }}</p>
        </div>
        <p style="float:right; margin-right:10px">{{ card.initCreateTime.split(' ')[0] }}</p>
      </w-card-category>
      <!-- 썸네일 이미지 -->
      <v-img
        v-if="card.thumbnail == null"
        :src="card.thumbnail"
        class="white--text align-end "
        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
        height="200px"
        @click="clickParams(card)"
      >
      </v-img>
      <v-img
        v-else
        :src="card.thumbnail"
        class="white--text"
        height="200px"
        style="background-position: none;"
        @click="clickParams(card)"
      >
      </v-img>
      <!-- 게시글 정보가 담길 흰색 박스 -->
      <w-card-body>
        <!-- 제목 -->
        <w-card-title>{{ card.title }}</w-card-title>
        <w-card-footer>
          <v-container>
            <v-row no-gutters>
              <v-col>
                <!-- 좋아요 버튼 토글 -->
                <div v-if="card.like" class="footer_btn">
                  <v-btn icon color="purple">
                    <v-icon @click="likemindmap(card.mindID, 1)">mdi-heart</v-icon>
                    <p style="margin-left:.2rem">좋아요</p>
                  </v-btn>
                </div>
                <div v-else-if="!card.like" class="footer_btn">
                  <v-btn icon>
                    <v-icon @click="likemindmap(card.mindID, 0)">mdi-heart-outline</v-icon>
                    <p style="margin-left:.2rem">좋아요</p>
                  </v-btn>
                </div>
              </v-col>
              <!-- 스크랩 버튼 토글 -->
              <v-col>
                <div v-if="card.scrap" class="footer_btn">
                  <v-btn icon color="purple">
                    <v-icon @click="scrapmindmap(card.mindID, 1)">mdi-bookmark</v-icon>
                    <p style="margin-left:.2rem">스크랩</p>
                  </v-btn>
                </div>
                <div v-else-if="!card.scrap" class="footer_btn">
                  <v-btn icon>
                    <v-icon @click="scrapmindmap(card.mindID, 0)">mdi-bookmark-outline</v-icon>
                    <p style="margin-left:.2rem">스크랩</p>
                  </v-btn>
                </div>
              </v-col>
              <!-- 조회수 버튼 -->
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
      this.$store.dispatch('readMapData', form).then(() => {
        this.map = Object(this.$store.getters.getMapData);
        this.$router.push({ name: 'MindMapDetail', params: { no: Number(mind.mindID) } });
      });
    },
    // 프사 누르면, 작성자 프로필 화면으로 이동
    // 회원 이메일, 닉네임 가져옴
    // query : 새로고침 시 데이터 유지
    getUserEmail(email, nickName, profileDefaultPic) {
      this.$router.push({
        name: 'UserProfile',
        query: { email: email, nickName: nickName, profileDefaultPic: profileDefaultPic },
      });
    },
  },
};
</script>

<style>
.v-image__image {
  background-position: top !important;
}
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
.v-application .text-h4 {
  font-size: 1rem !important;
  float: left;
  padding-left: 20px;
}
.v-application p[data-v-3a752450] {
  float: left;
}
</style>
