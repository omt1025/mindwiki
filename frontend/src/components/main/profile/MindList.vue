<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : key값으로 인한 console 에러 수정
    * 생성일자 : 2021-02-05
    * 최종수정일자 : 2021-02-16
-->
  <div>
    <v-sheet class="mx-auto">
      <v-slide-group multiple>
        <!-- Slide에 들어가는 card를 item만큼 만든다 -->
        <!-- key값이 중복되는 경우 에러가 나기 때문에 최대한 중복이 없는 마인드 생성시간으로 설정 -->
        <v-slide-item v-for="item in mindlist" :key="item.initCreateTime">
          <v-list-item id="v-list-item">
            <!-- 게시글 -->
            <w-card class="card_item p-b-10">
              <!-- 게시글 썸네일 -->
              <!-- 썸네일 클릭 시 상세 페이지로 이동[OMT] -->
              <img :src="item.thumbnail" class="card_img" @click="clickParams(item.mindID)" />
              <!-- 게시글 내용 -->
              <w-card-body class="body-rule">
                <w-card-title class="card-title">{{ item.title }}</w-card-title>
                <w-card-description>
                  {{ item.hashtag }}
                </w-card-description>
                <!-- <w-button>Get started</w-button> -->
              </w-card-body>
            </w-card>
          </v-list-item>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    mindlist: {}, // 내 MIND목록 건내준것 받아오기
  },
  data() {
    return {
      search: '',
      data: [],
    };
  },
  methods: {
    // 썸네일 클릭 시 상세 페이지로 이동[OMT]
    clickParams(no) {
      this.$router.push({ name: 'MindMapDetail', params: { no: Number(no) } });
    },
  },
};
</script>

<style scoped>
/* 뒤에 흰색 배경 깔리는 것 없애기 */
.theme--light.v-sheet {
  background-color: transparent;
}
/* card간격 조절 */
.card_item {
  max-width: 180px;
  max-height: 280px;
  min-height: 236px;
  margin: 2px;
  margin-bottom: 35px;
}
.txt1 {
  text-align: left;
  margin-left: 20px;
  font-family: Poppins-Medium;
  font-size: 1.1rem !important;
}
.card-title {
  font-size: 1.1rem !important;
  text-align: left;
  margin-bottom: 0px;
}
.card_img {
  background-color: black;
  height: 140px;
}
.card-image-default > img[data-v-d610e49e] {
  max-width: 100%;
  width: 100%;
  height: 140px;
  object-fit: fill;
}
.card-body {
  padding: 15px;
  padding-bottom: 0px;
}
/* 글자수 넘치면 안 보이도록 */
.body-rule {
  width: 165px;
  /* padding: 0 5px; */
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.text {
  font-size: 0.88rem;
}
.v-list-item {
  flex: none;
}
@media (min-width: 320px) and (max-width: 480px) {
  .mx-auto {
    max-width: 375px;
  }
}

@media (min-width: 576px) {
  .mx-auto {
    max-width: 540px;
  }
}

@media (min-width: 768px) {
  .mx-auto {
    max-width: 720px;
  }
}

@media (min-width: 992px) {
  .mx-auto {
    max-width: 960px;
  }
}

@media (min-width: 1200px) {
  .mx-auto {
    max-width: 1140px;
  }
}
</style>
