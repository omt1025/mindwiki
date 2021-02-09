<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 상세 마인드맵 페이지 UI 수정
    * 생성일자 : 2021-02-03
    * 최종수정일자 : 2021-02-09
  -->
  <v-app>
      <!-- 상세 마인드맵 상단 네비게이션 -->
      <v-toolbar>
      <!-- 뒤로가기 누르면 전체 마인드맵 조회 페이지로 이동 -->
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>
    </v-toolbar>

    <v-card class="mx-auto" max-width="400" flat outlined>
      <!-- 마인드맵 이미지 영역을 임의로 넓힘 -->
      <!-- 모바일 고려한 크기 조절 필요 -->
      <v-img min-height="550" width="375px">
        <!-- 마인드맵 api 사용 -->
        <mindmap :nodes="nodes" :connections="connections" :editable="true" />
        <v-card-title>{{ title }}</v-card-title>
      </v-img>

      <v-card-text id="explanationSize">
        <div>{{ explanation }}</div>
      </v-card-text>
    </v-card>

    <!-- 댓글 / 좋아요 / 스크랩 하는 곳 -->
    <v-card-actions id="cardAction">
      <v-list-item class="grow">
        <!-- mr : 아이콘과 span의 간격 조정 -->
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
        <v-row align="center" justify="end">
          <v-icon color="purple">mdi-heart</v-icon>
          <!-- 누적된 좋아요 수 -->
          <span class="subheading mr-2">256</span>
          <span class="mr-1">·</span>
          <v-icon class="mr-1">mdi-share-variant</v-icon>
          <!-- 누적된 스크랩 수 -->
          <span class="subheading">45</span>
        </v-row>
      </v-list-item>
    </v-card-actions>

      <!-- 노드 추가 버튼 구현 -->
      <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :nudge-width="200"
        offset-x
      >
        <template v-slot:activator="{ on, attrs }">
          <!-- 임시로 버튼 제작 -->
          <!-- <v-btn @click="readminddetail($route.params.no)">{{ $route.params.no }}</v-btn> -->
          <!-- <v-btn @click="updatemind($route.params.no)">수정 임시</v-btn> -->
          <v-btn id="btnDelete" color="purple" dark fab small @click="deletemind($route.params.no)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>

          <v-btn id="btnUpdate" color="purple" dark v-bind="attrs" v-on="on" fab small>
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </template>

        <!-- 추가 버튼 클릭 시 팝업 창 활성화 -->
        <v-card>
          <v-card-title>
            <span class="headline">마인드맵 수정</span>
          </v-card-title>

          <v-card-text>
              <v-row>
                <!-- 제목 -->
                <v-col cols="12">
                  <v-text-field
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
                  <p class="interestTagTitle">해시태그 설정</p>
                </div>

                <v-combobox
                  append-icon
                  flat
                  v-model="hashtag"
                  hashtag
                  clearable
                  label="Your favorite hobbies"
                  multiple
                  no-filter
                  solo
                  id="combobox"
                >
                  <template
                    v-slot:selection="{ attrs, item, select, selected }"
                  >
                    <v-chip
                      v-bind="attrs"
                      :input-value="selected"
                      close
                      class="ma-2"
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
                <!-- 설명 -->
                <v-col cols="12">
                  <v-text-field 
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

            <v-btn 
            text
            @click="checkHandler"
            >
              OK
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
  </v-app>
</template>

<script>
// import map from '../map.js';

export default {
  name: "MindMapDetail",
  components: {},
  // props: {
  //   no: {
  //     type: Number,
  //     default: 0
  //   }
  // },
  data() {
    const no = Number(this.$route.params.no);
    return {
      no: no,
      fav: true,
      menu: false,
      // 마인드맵 생성에 필요한 요소
      mindmap: "",
      // 임시로 쓸 더미 데이터
      nodes: [
        {
          'text': '중간발표',
          'url': '',
          'fx': -13.916222252976013,
          'fy': -659.1641376795345,
          'nodes': [],
        },
        {
          'text': '여행지',
          'url': '',
          'fx': 15.3247731601375,
          'fy': -964.73700786748157,
          'nodes': []
        },
        {
        'text': '음식',
        'url': '',
        'fx': 355.7839253819375,
        'fy': -455.5539283546699,
        'nodes': []
        },
        {
        'text': 'SNOW',
        'note': 'hihi',
        'url': '',
        'fx': -98.5231997717085,
        'fy': -355.07462866512333,
        'nodes': []
        },
      ],
      connections: [
        {
          'source': '중간발표',
          'target': 'SNOW',
          'curve': {
            'x': -43.5535,
            'y': 299.545
          }
        },
        {
          'source': '중간발표',
          'target': '여행지',
          'curve': {
            'x': -78.1206,
            'y': -114.714
          }
        },
        {
          'source': '중간발표',
          'target': '음식',
          'curve': {
            'x': 29.6649,
            'y': 80.1111
          }
        },
      ],
      hashtag: [],
      // ...map,
      title: "",
      subject: "",
      explanation: "",
      // 제목, 설명 글자 수 제한
      max_title: 16,
      max_explanation: 145,
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.title &&
        ((msg = '제목을 입력해주세요.'), (err = false), this.$refs.title.focus());
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
      form.append("jwt", this.$store.getters.getJWT);
      form.append("no", no)
      // actions의 readMindDetail 함수 실행
      this.$store.dispatch("readMindDetail", form).then(() => {
        this.mindmap = this.$store.getters.getMessage;
        // console.log(this.mindmap)
        this.title = this.mindmap.title
        this.explanation = this.mindmap.explanation
        // 해시태그 임시
        if (this.mindmap.hashtag) this.hashtag = this.mindmap.hashtag.split(',')
        this.subject = this.mindmap.subject
        // console.log(this.hashtag)
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
      form.append("MindID", no);
      // console.log(no)
      this.$store.dispatch("updateMind", form).then(() =>{
        this.showAlert('수정이 완료되었습니다.'); 
      })
    },
    // 마인드맵 제거 함수
    deletemind(no) {
      let form = new FormData();
      form.append("jwt", this.$store.getters.getJWT);
      form.append("no", no)
      
      this.$store.dispatch("deleteMind", form).then(() =>{
        this.$router.push('/main/mindmap/mymindlist')
      })
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
        this.menu = false
      });
    },
    // 전체 조회 페이지로 이동
    backPage: function() {
      this.$router.push('/main/mindmap/mymindlist')
    },
    // 마인드맵 댓글 페이지 이동
    goMindComment(no) {
      this.$router.push({name: 'MindComment', params: {no: Number(no)}});
    }
  },
  created: function () {
    this.readminddetail(this.no)
  }
};
</script>

<style scoped>
#app {
  background-color: #f1f5f8;
}
/* 설명이 적을 때 위치 고정 */
#cardAction {
  background-color: #fff;
}
/* 설명부분 글자수 제한 가정 (적정 : 115자 ~ 145자) */
#explanationSize {
  min-height: 140px;
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
</style>

<style>
.mindmap-svg {
  position: fixed;
  top: -50px;
  left: 40px;
}
.container {
  max-height: 500px;
}
/* 마인드맵 수정 / 삭제 버튼 위치 */
#btnDelete {
  position: fixed;
  right: 10px;
  top: 65px;
}
#btnUpdate {
  position: fixed;
  right: 70px;
  top: 65px;
}
</style>

<style lang="scss" scoped>
#no-background-hover::before {
  background-color: transparent !important;
}
</style>