<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 배경화면 pc에서 깨지는 것 수정
    * 생성일자 : 2021-02-04
    * 최종수정일자 : 2021-02-19
  -->
  <div class="profile_info">
    <div class="user_info">
      <!-- 사용자 프로필 이미지 -->
      <v-avatar class="user_avatar" size="120px">
        <!-- <img :src="user.files" alt="John" /> -->
        <img v-if="user.files == null" src="@/assets/images/mindwiki_logo-03.png" alt="John" />
        <img v-else :src="user.files" alt="John" />
      </v-avatar>

      <!-- 사용자 계정 정보 -->
      <div class="user_nickname text-center p-t-22 p-b-3">
        <span>{{ user.usernickName }}</span>
        <i class="fa fa-pencil-square-o pencil" aria-hidden="true" @click="goRevise"></i>
      </div>
      <div class="txt1 text-center p-t-2 p-b-20">
        <span>{{ user.useremail }}</span>
      </div>

      <!-- 팔로워, 팔로잉, 게시글 수 -->
      <w-card>
        <w-card-body>
          <!-- 본인이 작성한 게시물 수 -->
          <div class="user-num">
            <w-card-title class="card-title">게시물</w-card-title>
            <w-card-description class="card-description">
              {{ myMindList.length }}
            </w-card-description>
          </div>
          <!-- 스크랩한 게시물 수 -->
          <div class="user-num">
            <w-card-title class="card-title">스크랩</w-card-title>
            <w-card-description class="card-description">
              {{ scrapList.length }}
            </w-card-description>
          </div>
          <!-- 본인을 팔로워한 사람 수 -->
          <div class="user-num" @click="goFollow">
            <w-card-title class="card-title">팔로워</w-card-title>
            <w-card-description class="card-description">
              {{ this.followers.length }}
            </w-card-description>
          </div>

          <!-- 본인이 팔로우한 사람 수 -->
          <div class="user-num" @click="goFollow">
            <w-card-title class="card-title">팔로잉</w-card-title>
            <w-card-description class="card-description">
              {{ this.followings.length }}
            </w-card-description>
          </div>

          <!-- 다른 사람 계정을 볼 때만 보여지도록 추후 구현 필요[YJS] -->
          <!-- 이미 팔로우한 경우, #a64bf4 -->
          <div v-if="nickName !== nickName" class="p-t-16">
            <w-button class="profile_button">팔로우</w-button>
            <w-button>메시지</w-button>
          </div>
        </w-card-body>
      </w-card>

      <!-- 내가 작성한 MIND목록 -->
      <div class="mymind_list p-t-60 p-b-20">
        <div class="txt2 p-t-15 p-b-15">
          <span>내 MIND</span>
        </div>
        <mind-list :mindlist="this.myMindList"></mind-list>
      </div>

      <!-- 내가 스크랩한 MIND목록 -->
      <div class="mymind_list p-t-20 p-b-50">
        <div class="txt2 p-t-2 p-b-15">
          <span>스크랩 MIND</span>
        </div>
        <mind-list :mindlist="this.scrapList"></mind-list>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import MindList from './MindList.vue';

export default {
  components: { MindList },
  data: function() {
    return {
      myMindList: {}, // 내 MIND
      scrapList: [], // 스크랩 MIND
      followings: [],
      followers: [],
      // 계정
      user: {
        username: '', // 이름
        usernickName: '', // 닉네임
        useremail: '', // 이메일
        phoneNumber: '', // 핸드폰
        files: '', // 프로필사진
      },
      msg: [], // 유효성검사 후, 출력할 메세지 담을 배열
      message: '', // 오류 받아 올 변수
      profile: '',
    };
  },
  computed: {
    ...mapGetters(['nickName', 'userId']),
    ...mapGetters(['followingData']),
    ...mapGetters(['followerData']),
  },
  created() {
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
  mounted() {
    // 내 마인드맵 리스트 불러오기
    this.readmymindmap();
    this.readscrapmindmap();
    // 팔로잉 목록 가져오기
    this.readfollowing();
    // 팔로워 목록 가져오기
    this.readfollower();
  },
  methods: {
    // 팔로워 보기
    goFollow() {
      this.$store.dispatch('setBottomNav', 'follow');
      this.$router.push('/main/mylibrary/follow');
    },
    goRevise() {
      this.$router.push('/main/profile/revise');
    },
    // 내 마인드 리스트 조회
    readmymindmap() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      this.$store.dispatch('readMyMindMap', form).then(() => {
        this.myMindList = this.$store.getters.mindList; // 응답 결과 저장된 마인드를 자식 컴포넌트에 전달
      });
    },
    // 스크랩 마인드 리스트 조회[OMT]
    readscrapmindmap() {
      this.$store.dispatch('readScrapMindMap', this.$store.getters.getJWT).then(() => {
        this.scrapList = this.$store.getters.scrapData; // 응답 결과 저장된 마인드를 자식 컴포넌트에 전달
      });
    },
    // 팔로잉 목록 조회[HYH]
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.followings = this.$store.getters.followingData;
      });
    },
    // 팔로워 목록 조회[HYH]
    readfollower() {
      this.$store.dispatch('readFollower', this.$store.getters.getJWT).then(() => {
        this.followers = this.$store.getters.followerData;
      });
    },
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
/* 프로필 수정 페이지 버튼 */
.pencil {
  font-size: 22px;
  margin-left: 10px;
  color: #ffffffbb;
}

@media (min-width: 320px) and (max-width: 480px) {
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
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
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
    max-width: 540px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
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
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  .profile_info {
    background-image: url(../../../assets/images/profile/hero_mini.svg);
    background-size: contain;

    min-height: auto;
    min-width: 360px;
    width: 100%;
    /* height: 100%; */
    position: relative;
    /* object-position: bottom; */
  }
  .user_info {
    max-width: 960px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  .profile_info {
    background-image: url(../../../assets/images/profile/hero_mini.svg);
    background-size: contain;

    min-height: auto;
    min-width: 360px;
    width: 100%;
    /* height: 100%; */
    position: relative;
    /* object-position: bottom; */
  }
  .user_info {
    max-width: 1140px;
    margin: 0 auto;
  }
}
</style>
