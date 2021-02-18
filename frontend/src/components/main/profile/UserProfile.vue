<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 다른 회원 프로필 구현
    * 생성일자 : 2021-02-15
    * 최종수정일자 : 2021-02-16
  -->
  <v-app>
    <v-toolbar id="navi_shadow">
      <!-- 뒤로가기 누르면 검색페이지로 이동 -->
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" alt="" height="23px" />
      <v-spacer></v-spacer>
    </v-toolbar>
    <div class="profile_info">
      <div class="user_info">
        <v-avatar class="user_avatar" size="120px">
          <!-- 이미지 있을 때 -->
          <img
            v-if="$route.query.profileDefaultPic !== null"
            :src="$route.query.profileDefaultPic"
            alt=""
          />
          <!-- 이미지 없을 때 -->
          <img v-else :src="creatorImage" @error="imageError = true" alt="" />
        </v-avatar>
      </div>
      <!-- 사용자 계정 정보 -->
      <div class="user_nickname text-center p-t-22 p-b-3">
        <span>{{ $route.query.nickName }}</span>
      </div>
      <div class="txt1 text-center p-t-2 p-b-20">
        <span>{{ $route.query.email }}</span>
      </div>
      <!-- 팔로우 하기, 메시지 보내기 -->
      <div class="user-num">
        <w-card-description class="card-description"> </w-card-description>
      </div>
      <w-button v-if="this.show === false" class="profile_button" @click="userfollow()"
        >팔로우</w-button
      >
      <w-button v-if="this.show === true" class="profile_button">팔로잉</w-button>
      <v-divider></v-divider>
      <!-- 내가 팔로우한 사람의 MIND목록 -->
      <div class="mymind_list p-t-60 p-b-20">
        <div class="txt2 p-t-15 p-b-15">
          <span>{{ $route.query.nickName }}님의 MIND</span>
        </div>
        <mind-list :mindlist="minditems"> </mind-list>
      </div>
    </div>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';
import MindList from './MindList.vue';
export default {
  components: { MindList },
  name: 'UserProfile',
  computed: {
    ...mapGetters(['followingData']),
    ...mapGetters(['followingMindData']),
    // default 이미지 설정
    creatorImage() {
      return this.imageError ? this.defaultImage : 'creator-image.jpg';
    },
  },
  data() {
    return {
      user: {
        files: '',
      },
      followeremail: '',
      message: '',
      items: [],
      minditems: [],
      followerlist: [],
      show: false,
      profile: '',
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false,
    };
  },
  methods: {
    // 팔로우 하기
    userfollow() {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', this.$route.query.email);
      this.$store.dispatch('userFollow', form).then(() => {
        this.message = this.$store.getters.getMessage;
        this.show = !this.show;
      });
    },
    // 팔로우한 유저의 마인드 조회
    readfollowingminddata() {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', this.$route.query.email);
      this.$store.dispatch('readfollowingMindData', form).then(() => {
        this.minditems = this.$store.getters.followingMindData;
      });
    },
    // 검색페이지로 이동
    backPage() {
      this.$router.push('/main');
    },
    // 팔로잉 확인
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.getters.followingData;
      });
    },
  },
  created: function() {
    this.readfollowingminddata();
    this.readfollowing();
    // 프로필 정보 받아오기
    let form = new FormData();
    form.append('jwt', this.$store.getters.getJWT);
    this.$store.dispatch('myProfile', form).then(() => {
      // 응답 결과
      this.message = this.$store.getters.message;
      this.profile = this.$store.getters.profile;
      if (this.message === 'FAIL')
        this.showAlert('세션이 만료되었습니다. 다시 로그인 해 주세요.', '프로필 수정');
      else {
        this.user.usernickName = this.profile.nickName;
        this.user.useremail = this.profile.email;
        this.user.files = this.profile.profileDefaultPic;
      }
    });
  },
};
</script>

<style scoped>
.profile_info {
  background-image: url(../../../assets/images/profile/hero_mini.svg);
  min-height: auto;
  min-width: 360px;
  width: 100%;
  /* height: 100%; */
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
  margin-left: 6px;
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
.txt2 {
  text-align: left;
  margin-left: 20px;
  font-family: Poppins-Medium;
  font-size: 1.1rem !important;
}
.card-body {
  padding: 1rem;
}
</style>
