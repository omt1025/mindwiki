<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 팔로워 리스트 프로필 사진 수정
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-17
  -->
  <!-- 나를 팔로우한 사람을 보여준다. -->
  <div id="app">
    <template v-for="follower in items">
      <v-subheader
        v-if="follower.header"
        :key="follower.header"
      ></v-subheader>
      <v-list-item
      v-else
      :key="follower.index"
      >
        <v-list-item-avatar>
          <!-- 이미지 있을 때 -->
          <v-img
            v-if="follower.profileDefaultPic !== null"
            alt=""
            :src="follower.profileDefaultPic"
            @error="imageError = true"
          ></v-img>
          <!-- 이미지 없을 때 -->
          <v-img
            v-else
            alt=""
            :src="creatorImage"
            @error="imageError = true"
          ></v-img>
        </v-list-item-avatar>
        <!-- 나를 팔로우한 사람의 이메일을 보여준다. -->
        <v-list-item-content>
          <v-list-item-title v-html="follower.email.split('@')[0]"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "FollowerList",
  computed: {
    ...mapGetters(['followerData']),
    ...mapGetters(['followerProfileImage']),
    // 사용자가 이미지 설정하지 않은 경우 default 이미지 첨부
    creatorImage() {
      return this.imageError ? this.defaultImage : "creator-image.jpg"
    },
  },
  data() {
    return {
      items: [],
      followers: [],
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false, 
      followersImage: [],
      user: {
        files: '',
      },
      profile: '',
      message: '',
      profileDefaultPic: '',
    }
  },
  methods: {
    // 팔로워 목록 조회[HYH]
    readfollower() {
      this.$store.dispatch('readFollower', this.$store.getters.getJWT).then(() => {
        this.followers = this.$store.getters.followerData
        this.$store.dispatch('readFollowerProfileImage', this.$store.getters.getJWT).then(() => {
          this.followersImage = this.$store.getters.followerProfileImage
          for (var j = 0; j < this.$store.state.followerData.length; j++) {
            // 현재 프로필 사진 순서와 팔로우 주인 순서가 같다.
            // followingData에 items 순서대로 넣어주기
            const form = []
            this.$set(form, 'email', this.followers[j])
            this.$set(form, 'profileDefaultPic', this.followersImage[j]);
            this.items.push(form)
          }              
        });        
        
      });
    },
    // 팔로워 사진 확인[HYH]
    readfollowerprofileimage() {
      this.$store.dispatch('readFollowerProfileImage', this.$store.getters.getJWT).then(() => {
        this.followersImage = this.$store.getters.followerProfileImage
        for (var j = 0; j < this.$store.state.followerData.length; j++) {
        // 현재 프로필 사진 순서와 팔로우 주인 순서가 같다.
        // followingData에 items 순서대로 넣어주기
        this.$set(this.followers[j], 'profileDefaultPic', this.followersImage[j]);
      }              
      });
    },
  },
  // 팔로워 확인
  created: function() {
    this.readfollower()  
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
        this.user.files = this.profile.profileDefaultPic;
      }
    });
  }
}
</script>

<style scoped>
.v-list-item__title {
  text-align: left;
}
.subtitle {
  text-align: left;
  margin-top: 6px;
}
.v-list-item__content {
  padding: 11px 0;
}
hr {
  margin-top: 0.4rem;
  margin-bottom: 0.4rem;
  margin-left: 1.1rem;
  margin-right: 1.1rem;
}
</style>