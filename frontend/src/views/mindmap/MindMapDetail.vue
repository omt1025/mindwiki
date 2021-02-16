<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 마인드맵 수정 UI 수정 
    * 생성일자 : 2021-02-03
    * 최종수정일자 : 2021-02-16
  -->
  <v-app id="app">
    <!-- 상세 마인드맵 상단 네비게이션 -->
    <v-toolbar>
      <!-- 뒤로가기 누르면 전체 마인드맵 조회 페이지로 이동 -->
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>
    </v-toolbar>

    <v-card class="mx-auto" width="100%" flat outlined style="">
        <!-- 작성자 -->
      <v-card-title>
        <div v-if="profileImage === null">
          <v-img id="avatar" :src="defaultImage" alt=""></v-img>
        </div>
        <div v-else>
          <v-img id="avatar" :src="profileImage" alt=""></v-img>
        </div> 
        {{ mindmap.admin }}
      </v-card-title>
      <!-- 제목 -->
      <div id="mindTitle">{{ title }}</div>
      <!--  -->
      <v-card-text id="topExplanation">
        <div>{{ explanation }}</div>
      </v-card-text>
      <!-- 마인드맵 이미지 영역을 임의로 넓힘 -->
      <!-- 모바일 고려한 크기 조절 필요 -->
      <div>{{ map }}</div>
      <v-img width="100%" height="60%">
        <!-- 마인드맵 api 사용 -->
        <mind-map
          :data="map"
          width="360"
          height="500"
          :show-reason="false"
          :data-template="{ label: ' ', reason: 0 }"
          @data-change="handleDataChange"
          @node-delete="handleNodeDelete"
        ></mind-map>
      </v-img>
      <v-card-text id="bottomExplanation">
        <div>
          <!-- 누적된 좋아요 수 -->
          <span class="subheading ma-2">좋아요 {{ mindmap.likeCnt }}개</span>
          <!-- 누적된 스크랩 수 -->
          <span class="subheading ma-2">스크랩 {{ mindmap.scrapCnt }}회</span>
          <!-- 누적된 조회수 -->
          <span class="subheading ma-2">조회 {{ mindmap.viewCnt }}회</span>
        </div>
      </v-card-text>
    </v-card>

    <!-- 댓글 / 좋아요 / 스크랩 하는 곳 -->
    <v-card-actions id="cardAction">
      <v-list-item class="grow">
        <v-row id="cardbottom">
          <!-- 좋아요 -->
          <v-btn
            id="no-background-hover"
            :ripple="false"
            depressed
            color="white"
          >
            <v-icon class="mx-1" v-if="this.like" color="purple" @click="likemindmap(no, 1)">mdi-heart</v-icon>
            <v-icon class="mx-1" v-else @click="likemindmap(no, 0)">mdi-heart</v-icon>
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
          <v-btn
            id="no-background-hover"
            :ripple="false"
            depressed
            color="white"
          >
            <v-icon class="mx-1" v-if="this.scrap" color="purple" @click="scrapmindmap(no, 1)">mdi-bookmark</v-icon>
            <v-icon class="mx-1" v-else @click="scrapmindmap(no, 0)">mdi-bookmark</v-icon>
            <span>스크랩</span>
          </v-btn>
        </v-row>
      </v-list-item>
    </v-card-actions>
    <div class="text-center">
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            id="dropdown"
            v-bind="attrs"
            v-on="on"
            small
          >
            <v-icon>mdi-dots-horizontal</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="updatenode($route.params.no)">
            맵 수정
          </v-list-item>
          <!-- 노드 추가 버튼 구현 -->
          <!-- 로그인 한 유저와 작성자가 같을 때만 수정과 삭제 가능 -->
          <div v-if="mindmap.admin === useremail">
            <v-list-item @click="deletemind($route.params.no)">
              마인드 삭제
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
    </div>
  </v-app>
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'MindMapDetail',
  components: { mindMap },
  computed: {
    ...mapGetters({ map:'getMapData', likecheck:'likeData', scrapcheck:'scrapData'}),
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
      // 마인드맵 생성에 필요한 요소
      mindmap: '',
      useremail: this.$store.getters.userId,
      // 임시로 쓸 더미 데이터
      // map: [{ label: 'wwwwwwww', root:true, reason:0, url:'', children: [],},],
      hashtag: [],
      // ...map,
      title: '',
      subject: '',
      explanation: '',
      // 제목, 설명 글자 수 제한
      max_title: 16,
      max_explanation: 145,
    };
  },
  methods: {
    handleDataChange(data) {
      this.map = data;
    },
    handleNodeDelete(nodeData, callback) {
      console.log(nodeData);

      callback(true);
    },
    readmapdata() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);

      this.$store.dispatch('readMapData', form).then(() => {
        this.map = this.$store.getters.getMapData
        console.log(typeof this.map)
      });
    },
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
    // 서버로부터 마인드맵 데이터를 받아오는 함수
    readminddetail(no) {
      // jwt와 마인드 번호를 form에 담아서 보내야 함
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', no);
      // actions의 readMindDetail 함수 실행
      this.$store.dispatch('readMindDetail', form).then(() => {
        this.mindmap = this.$store.getters.getMessage;
        this.title = this.mindmap.title;
        this.explanation = this.mindmap.explanation;
        // 해시태그 임시
        if (this.mindmap.hashtag) this.hashtag = this.mindmap.hashtag.split(',');
        this.subject = this.mindmap.subject;
        for (var i=0; i<this.likecheck.length; i++) {
          if (this.likecheck[i]['mindID'] === this.mindmap.mindID) {
            this.like = true
            break;
          }
        }
        for (var j=0; j<this.scrapcheck.length; j++) {
          if (this.scrapcheck[j]['mindID'] === this.mindmap.mindID) {
            this.scrap = true
            break;
          }
        }
      })
      this.$store.dispatch('readMindAdminImage', form).then(() => {
        this.profileImage = this.$store.getters.getProfileImage.pic
      })
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
      // console.log(no)
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
        this.$router.push('/main/mindmap/mymindlist');
      });
    },
    // 마인드맵 제거 함수
    updatenode() {
      this.$router.push({ name: 'MindMapUpdate', params: { no: Number(this.no), map: this.map }})
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
        // console.log(res);
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
      });
    },
  },
  created: function() {
    this.readminddetail(this.no);
    this.readmapdata();
  },
};
</script>

<style lang="scss" scoped>
#app {
  background-color: #f1f5f8;
}
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
/* 설명이 적을 때 위치 고정 */
#cardAction {
  background-color: #fff;
  width: 100%;
  position: fixed;
  bottom: 0;
}
/* 설명부분 글자수 제한 가정 (적정 : 115자 ~ 145자) */
#topExplanation {
  height: 10%;
  text-align: left;
}
#bottomExplanation {
  height: 10%;
  text-align: right;
}
.interestTagTitle {
  font-family: Poppins-Regular;
  font-size: 14px;
  color: #333333;
  line-height: 1.5;
  padding-left: 7px;
  width: 100%;
  text-align: left;
  margin-bottom: 7px;
}
.mindmap-svg {
  position: fixed;
  top: -50px;
  left: 40px;
}
#app {
  height: 100%;
}
/* 마인드맵 수정 / 삭제 버튼 위치 */
#dropdown {
  position: fixed;
  right: 3%;
  top: 10%;
  background-color: #ffffff;
  border: none;
  box-shadow: none;
}
#updateBtn {
  border: none;
  background-color: #ffffff;
  box-shadow: none;
}
#no-background-hover::before {
  background-color: transparent !important;
}
</style>
