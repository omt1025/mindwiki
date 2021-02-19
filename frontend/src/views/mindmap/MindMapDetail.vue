<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 아이디 -> 닉네임 변경
    * 생성일자 : 2021-02-03
    * 최종수정일자 : 2021-02-19
  -->
  <v-app id="app">
    <!-- 상단 Navi -->
    <!-- title : 게시물 제목 -->
    <back-navi :title="title" :sub="naviTitle" v-on:backbtn="backPage"></back-navi>

    <div class="mindmap_detail">
      <!-- 카드 타이틀 -->
      <v-card class="mx-auto" width="100%" flat outlined style="margin-top: 8px">
        <v-menu offset-y class="mmm" v-if="bottomNav === 'profile'">
          <template v-slot:activator="{ on, attrs }">
            <v-btn id="dropdown" v-bind="attrs" v-on="on" small>
              <v-icon>mdi-dots-horizontal</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="updatenode($route.params.no)">
              <v-btn id="updateBtn">
                맵 수정
              </v-btn>
            </v-list-item>
            <!-- 노드 추가 버튼 구현 -->
            <!-- 로그인 한 유저와 작성자가 같을 때만 수정과 삭제 가능 -->
            <div v-if="mindmap.admin === useremail">
              <v-list-item @click="deletemind($route.params.no)">
                <v-btn id="updateBtn">
                  마인드 삭제
                </v-btn>
              </v-list-item>
              <v-list-item>
                <v-menu v-model="menu" :close-on-content-click="false" :nudge-width="200" offset-x>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn id="updateBtn" v-bind="attrs" v-on="on">
                      마인드 수정
                    </v-btn>
                  </template>

                  <!-- 추가 버튼 클릭 시 팝업 창 활성화 -->
                  <v-card>
                    <v-card-title class="justify-center">
                      <span class="headline">마인드 수정</span>
                    </v-card-title>
                    <v-card-text>
                      <v-row>
                        <!-- 제목 -->
                        <v-col cols="12">
                          <v-text-field
                            class="mx-2"
                            label="제목 (최대 16자)"
                            required
                            id="title"
                            ref="title"
                            v-model="title"
                            @keypress.enter="checkHandler"
                            type="text"
                            :maxlength="max_title"
                          ></v-text-field>
                        </v-col>
                        <!-- 주제 -->
                        <v-col cols="12">
                          <v-text-field
                            class="mx-2"
                            label="주제"
                            required
                            id="subject"
                            ref="subject"
                            v-model="subject"
                            @keypress.enter="checkHandler"
                            type="text"
                          ></v-text-field>
                        </v-col>
                        <!-- 해시태그 -->
                        <div>
                          <div padding="10px">
                            <p class="interestTagTitle mx-3">해시태그 설정</p>
                          </div>
                          <v-combobox
                            class="mx-5"
                            append-icon
                            flat
                            v-model="hashtag"
                            hashtag
                            clearable
                            multiple
                            no-filter
                            id="combobox"
                          >
                            <template v-slot:selection="{ attrs, item, select, selected }">
                              <v-chip
                                v-bind="attrs"
                                :input-value="selected"
                                close
                                class="ma-1 mb-2"
                                color="purple"
                                text-color="white"
                                @click="select"
                                @click:close="remove(item)"
                              >
                                <strong class="test">{{ item }}</strong
                                >&nbsp;
                              </v-chip>
                            </template>
                          </v-combobox>
                        </div>
                        <!-- 설명 -->
                        <v-col cols="12">
                          <v-text-field
                            class="mx-2"
                            label="설명 (최대 145자)"
                            required
                            id="explanation"
                            ref="explanation"
                            v-model="explanation"
                            :maxlength="max_explanation"
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn text @click="menu = false">
                        Cancel
                      </v-btn>
                      <v-btn text @click="checkHandler">
                        OK
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-menu>
              </v-list-item>
            </div>
          </v-list>
        </v-menu>

        <!-- 작성자 -->
        <v-card-title>
          <!-- 프로필 사진이 없다면 기본 이미지 출력 -->
          <div v-if="profileImage === null">
            <v-img id="avatar" :src="defaultImage" alt=""></v-img>
          </div>
          <div>
            <v-img id="avatar" :src="profileImage" alt=""></v-img>
          </div>
          <p class="admin">{{ nickName }}</p>
        </v-card-title>

        <!-- 게시물 설명 -->
        <v-card-text id="topExplanation" style="padding-top:0px">
          <div>{{ explanation }}</div>
        </v-card-text>

        <v-card-text id="num_card" style="padding-bottom:0px">
          <!-- 누적된 좋아요 수 -->
          <span class="subheading ma-2">좋아요 {{ mindmap.likeCnt }}개</span>
          <!-- 누적된 스크랩 수 -->
          <span class="subheading ma-2">스크랩 {{ mindmap.scrapCnt }}회</span>
          <!-- 누적된 조회수 -->
          <span class="subheading ma-2">조회 {{ mindmap.viewCnt }}회</span>
        </v-card-text>
        <v-divider></v-divider>
        <!-- 댓글 / 좋아요 / 스크랩 하는 곳 -->
        <v-card-actions id="cardAction">
          <v-list-item class="grow">
            <v-row id="cardbottom">
              <!-- 좋아요 -->
              <v-btn id="no-background-hover" :ripple="false" depressed color="white">
                <v-icon class="mx-1" v-if="this.like" color="purple" @click="likemindmap(no, 1)"
                  >mdi-heart</v-icon
                >
                <v-icon class="mx-1" v-else @click="likemindmap(no, 0)">mdi-heart-outline</v-icon>
                <span>좋아요</span>
              </v-btn>
              <!-- 댓글 쓰기 -->
              <v-btn
                id="no-background-hover"
                :ripple="false"
                depressed
                color="white"
                @click="goMindComment(no)"
              >
                <v-icon class="mr-2">mdi-comment-outline</v-icon>
                <span>댓글 쓰기</span>
              </v-btn>
              <!-- 스크랩 -->
              <v-btn id="no-background-hover" :ripple="false" depressed color="white">
                <v-icon class="mx-1" v-if="this.scrap" color="purple" @click="scrapmindmap(no, 1)"
                  >mdi-bookmark</v-icon
                >
                <v-icon class="mx-1" v-else @click="scrapmindmap(no, 0)"
                  >mdi-bookmark-outline</v-icon
                >
                <span>스크랩</span>
              </v-btn>
            </v-row>
          </v-list-item>
        </v-card-actions>
      </v-card>

      <div width="100%" height="100%">
        <!-- 마인드맵 a -->
        <mind-map
          :data="map"
          height="600px"
          :show-reason="false"
          :data-template="{ label: ' ', reason: 0 }"
          :button="false"
        ></mind-map>
      </div>
    </div>
  </v-app>
</template>

<script>
import BackNavi from '../../components/navi/BackNavi.vue';
import mindMap from '../../components/mindmap/mind-map.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'MindMapDetail',
  components: { mindMap, BackNavi },
  computed: {
    ...mapGetters({
      map: 'getMapData',
      likecheck: 'likeData',
      scrapcheck: 'scrapData',
      bottomNav: 'bottomNav',
    }),
  },
  data() {
    const no = Number(this.$route.params.no);
    return {
      no: no,
      fav: true,
      menu: false,
      like: false,
      scrap: false,
      profileImage: null,
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      mindmap: '',
      useremail: this.$store.getters.userId,
      hashtag: [],
      title: '',
      subject: '',
      explanation: '',
      max_title: 16,
      max_explanation: 145,
      naviTitle: '게시물 상세 보기',
      time: '',
      members: null,
      nickName: '',
    };
  },
  methods: {
    // 서버로부터 마인드맵 데이터를 받아오는 함수
    readminddetail(no, flag) {
      // jwt와 마인드 번호를 form에 담아서 보내야 함
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('flag', Number(flag));
      // actions의 readMindDetail 함수 실행
      this.$store.dispatch('readMindDetail', form).then(() => {
        this.mindmap = this.$store.getters.getMessage;
        this.title = this.mindmap.title;
        this.explanation = this.mindmap.explanation;
        // 해시태그 임시
        if (this.mindmap.hashtag) this.hashtag = this.mindmap.hashtag.split(',');
        this.subject = this.mindmap.subject;
        for (var i = 0; i < this.likecheck.length; i++) {
          if (this.likecheck[i]['mindID'] === this.mindmap.mindID) {
            this.like = true;
            break;
          }
        }
        for (var j = 0; j < this.scrapcheck.length; j++) {
          if (this.scrapcheck[j]['mindID'] === this.mindmap.mindID) {
            this.scrap = true;
            break;
          }
        }
      });
      this.$store.dispatch('readMindAdminImage', form).then(() => {
        this.profileImage = this.$store.getters.getProfileImage.pic;
      });
    },
    // 마인드맵 수정메뉴에서 항목 작성 체크
    checkHandler() {
      let err = true;
      let msg = '';
      !this.title && ((msg = '제목을 입력해주세요.'), (err = false), this.$refs.title.focus());
      err &&
        !this.subject &&
        ((msg = '부모 노드를 입력해주세요.'), (err = false), this.$refs.subject.focus());
      err &&
        !this.explanation &&
        ((msg = '설명을 적어주세요.'), (err = false), this.$refs.explanation.focus());
      // if (!err) alert(msg);
      if (!err) this.showAlert(msg);
      else this.updatemind(this.no);
    },
    // 마인드맵 수정 함수
    updatemind(no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('title', this.title);
      form.append('subject', this.subject);
      form.append('explanation', this.explanation);
      form.append('hashtag', this.hashtag);
      form.append('MindID', no);
      this.$store.dispatch('updateMind', form).then(() => {
        this.showAlert('수정이 완료되었습니다.');
      });
    },
    // 마인드맵 제거 함수
    deletemind(no) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      this.$store.dispatch('deleteMind', form).then(() => {
        this.$router.push('/main');
      });
    },
    // 마인드맵 업데이트 페이지로 이동
    updatenode() {
      this.$router.push({ name: 'MindMapUpdate', params: { no: Number(this.no), map: this.map } });
    },
    // 관심태그 삭제
    remove(item) {
      this.hashtag.splice(this.hashtag.indexOf(item), 1);
      this.hashtag = [...this.hashtag];
    },
    // 다이얼로그
    showAlert(msg) {
      const options = { title: '알림', size: 'sm' };
      this.$dialogs.alert(msg, options).then(() => {
        this.menu = false;
      });
    },
    // 전체 조회 페이지로 이동
    backPage: function() {
      this.$router.push('/main');
    },
    // 마인드맵 댓글 페이지 이동
    goMindComment(no) {
      this.$router.push({ name: 'MindComment', params: { no: Number(no) } });
    },
    // 좋아요 눌렀을 시 실행
    likemindmap(no, isLike) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disLike', isLike);

      this.$store.dispatch('likeMind', form).then(() => {
        this.like = !this.like;
        this.readminddetail(this.no, 1);
      });
    },
    // 스크랩 눌렀을 시 실행
    scrapmindmap(no, isScrap) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      form.append('disScrap', isScrap);

      this.$store.dispatch('scrapMind', form).then(() => {
        this.scrap = !this.scrap;
        this.readminddetail(this.no, 1);
      });
    },
  },
  created: function() {
    // 마인드맵 불러오기
    this.readminddetail(this.no, 0);

    // 회원 목록 가져오기
    let form1 = new FormData();
    form1.append('jwt', this.$store.getters.getJWT);

    this.$store.dispatch('readMemberList', form1).then(() => {
      this.members = this.$store.getters.memberList;
      for (var j = 0; j < this.members.length; j++) {
        if (this.members[j].email === this.mindmap.admin) this.nickName = this.members[j].nickName;
      }
    });
  },
};
</script>

<style lang="scss" scoped>
#mindTitle {
  text-align: left;
  margin: 10px 20px;
}
#avatar {
  margin-right: 10px;
  vertical-align: middle;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: inline-block;
}
#cardbottom {
  justify-content: space-around;
}
#topExplanation {
  height: 10%;
  text-align: left;
}
#bottomExplanation {
  height: 56px;
  text-align: right;
  position: fixed;
  bottom: 0;
  width: 100%;
}
/* 마인드맵 수정 / 삭제 버튼 위치 */
#dropdown {
  position: absolute;
  right: 3%;
  background-color: #ffffff;
  border: none;
  box-shadow: none;
}
#updateBtn {
  border: none;
  background-color: #ffffff;
  box-shadow: none;
  justify-content: center;
}
#no-background-hover::before {
  background-color: transparent !important;
}
.v-card__title {
  font-size: 1rem;
}
.v-card__subtitle,
.v-card__text,
.v-card__title {
  padding: 10px;
  padding-left: 12px;
}
.v-card__actions {
  padding: 0px;
}
.num_card {
  padding: 0px !important;
  float: right;
}
.admin {
  color: #000;
  font-size: 1rem;
  text-align: left;
  clear: both;
  margin-bottom: 0px;
}
.time {
  text-align: left;
  clear: both;
  margin-bottom: 0px;
}
hr {
  margin-bottom: 0px;
  margin-top: 0.5rem;
}
@media (min-width: 320px) and (max-width: 480px) {
  .mindmap_detail {
    max-width: 375px;
    margin: 0 auto;
  }
  #app {
    background-color: #f1f5f8;
  }
  .mind-map-item {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  .mindmap_detail {
    max-width: 540px;
    margin: 0 auto;
  }
  .mind-map-item {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  .mindmap_detail {
    max-width: 720px;
    margin: 0 auto;
  }
  .mind-map-item {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  .mindmap_detail {
    max-width: 720px;
    margin: 0 auto;
  }
  .mind-map-item {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  .mindmap_detail {
    max-width: 720px;
    margin: 0 auto;
  }
  .mind-map-item {
    max-width: 720px;
    margin: 0 auto;
  }
}
</style>
