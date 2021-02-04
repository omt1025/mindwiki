<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 프로필화면 UI제작-1차
    * 생성일자 : 2021-02-04
    * 최종수정일자 : 2021-02-04
 -->

  <div class="profile_info">
    <div class="user_info">
      <!-- 사용자 프로필 이미지 -->
      <v-avatar class="user_avatar" size="120px">
        <img src="../../../assets/images/profile/person.jpeg" alt="John" />
      </v-avatar>

      <!-- 사용자 계정 정보 -->
      <div class="user_nickname text-center p-t-22 p-b-3">
        <span>{{ nickName }}</span>
      </div>
      <div class="txt1 text-center p-t-2 p-b-20">
        <span>{{ userId }}</span>
      </div>

      <!-- 팔로워, 팔로잉, 게시글 수 -->
      <w-card>
        <w-card-body>
          <!-- 본인이 작성한 게시물 수 -->
          <div class="user-num" @click="goMind">
            <w-card-title class="card-title">게시물</w-card-title>
            <w-card-description class="card-description">
              139
            </w-card-description>
          </div>
          <!-- 스크랩한 게시물 수 -->
          <div class="user-num">
            <w-card-title class="card-title">스크랩</w-card-title>
            <w-card-description class="card-description">
              203
            </w-card-description>
          </div>
          <!-- 본인을 팔로워한 사람 수 -->
          <div class="user-num" @click="goFollow">
            <w-card-title class="card-title">팔로워</w-card-title>
            <w-card-description class="card-description">
              159
            </w-card-description>
          </div>
          <!-- 본인이 팔로우한 사람 수 -->
          <div class="user-num" @click="goFollow">
            <w-card-title class="card-title">팔로잉</w-card-title>
            <w-card-description class="card-description">
              295
            </w-card-description>
          </div>

          <!-- 다른 사람 계정을 볼 때만 보여지도록 추후 구현 필요[YJS] -->
          <!-- 이미 팔로우한 경우, #a64bf4 -->
          <div v-if="nickName !== this.$store.getters.nickName" class="p-t-16">
            <w-button class="profile_button">팔로우</w-button>
            <w-button>메시지</w-button>
          </div>
        </w-card-body>
      </w-card>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['nickName', 'userId']),
  },
  methods: {
    goFollow() {
      this.$store.dispatch('setBottomNav', 'follow');
      this.$router.push('/main/mylibrary/follow');
    },
    goMind() {
      this.$router.push('/main/mindmap/mymindList');
    },
  },
};
</script>

<style scoped>
.profile_info {
  background-image: url(../../../assets/images/profile/hero_mini.svg);
  min-height: 1000px;
  min-width: 375px;
  width: 100%;
  height: 100%;
  position: relative;
  /* object-position: bottom; */
}
.user_info {
  padding: 20px;
}
/* 프로필 사진 */
.user_avatar {
  margin-top: 40px;
}
/* 계정에 해당하는 닉네임 */
.user_nickname {
  color: white;
  font-family: Poppins-Medium;
  font-size: 1.75rem;
}
/* 계정에 해당하는 이메일 */
.txt1 {
  color: white;
  font-family: Poppins-Medium;
}
.profile_button {
  margin-right: 10px;
  /* background-color: #a64bf4; */
}

/* 게시물 수 들어있는 card */
.user-num {
  display: inline-block;
  margin-left: 12px;
  margin-right: 12px;
}
/* 게시물, 스크랩, 팔로워, 팔로잉 title */
.card-title {
  font-size: 1.1rem !important;
  margin-bottom: 0px;
}
/* 게시물, 스크랩, 팔로워, 팔로잉 숫자 */
.card-description {
  text-align: center;
  /* margin: auto !important; */
}
</style>
