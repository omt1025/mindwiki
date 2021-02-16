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
          <img src="../../../assets/images/profile/person.jpeg" alt="John" />
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
        <!-- <w-card-title class="card-title">팔로워</w-card-title> -->
        <w-card-description class="card-description">
        </w-card-description>
      </div>

      <w-button
        v-if="this.show === false"
        class="profile_button"
        @click="[userfollow()]"  
      >팔로우</w-button>

      <w-button
        v-if="this.show === true"
        class="profile_button" 
      >팔로잉</w-button>

      <w-button>메시지</w-button>

      <v-divider></v-divider>

      <!-- 내가 팔로우한 사람의 MIND목록 -->
      <div class="mymind_list p-t-60 p-b-20">
        <div class="txt2 p-t-15 p-b-15">
          <span>{{ $route.query.nickName }}님의 MIND</span>
        </div>
        <mind-list
          :mindlist="minditems">
        </mind-list>
      </div>
    </div>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import MindList from './MindList.vue';
export default {
  components: { MindList },
  name: 'UserProfile',
  computed: {
    ...mapGetters(['followingData']),
    ...mapGetters(['followingMindData']),
  },
  data() {
    return {
      user: {
      },
      followeremail: '',
      message: '',
      items: [],
      minditems: [],
      followerlist: [],
      show: false,
    }
  },
  methods: {
    // 팔로우 하기
    userfollow() {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', this.$route.query.email);

      this.$store.dispatch('userFollow', form).then(() => {
        this.message = this.$store.getters.getMessage;
      })
    },
    // 팔로우한 유저의 마인드 조회
    readfollowingminddata() {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', this.$route.query.email);
      
      this.$store.dispatch('readfollowingMindData', form).then(() => {
        this.minditems = this.$store.getters.followingMindData
      })
    },
    // 검색페이지로 이동
    backPage() {
      this.$router.push('/main')
    },
    // 팔로잉 확인
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.getters.followingData
      })
    },
  },
  created: function () {
    this.readfollowingminddata()
    this.readfollowing()

    // 팔로우 했을 때만 데이터 보여주기
    this.items = this.followingData;
    for (var i in this.items) {
    // console.log(this.items)
    // console.log(i)
    // console.log(this.items[i]['followerEmail'])
    if (this.items[i]['followerEmail'] === this.$route.query.email) {
      this.show = true
    } else {
      this.show = false
    }
    // console.log(this.show)
    }
  }
}
</script>

<style scoped>
.profile_info {
  background-image: url(../../../assets/images/profile/hero_mini.svg);
  min-height: auto;
  min-width: 375px;
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
