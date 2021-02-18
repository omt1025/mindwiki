<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 팔로잉 리스트 프로필 사진 수정
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-17
  -->
  <!-- 내가 팔로우한 사람들(팔로잉)을 보여준다. -->
  <div id="app">
    <template v-for="item in items">
      <v-subheader
        v-if="item.header"
        :key="item.header"
      ></v-subheader>
      <v-list-item
      v-else
      :key="item.data"
      >
        <v-list-item-avatar>
          <!-- 이미지 있을 때 -->
          <v-img
            v-if="item.profileDefaultPic !== null "
            v-bind:followingsImage="followingsImage"
            alt=""
            :src="item.profileDefaultPic"
          ></v-img>
          <!-- 이미지 없을 때 -->
          <v-img
            v-else
            alt=""
            :src="creatorImage"
            @error="imageError = true"
          ></v-img>
        </v-list-item-avatar>
        <!-- 내가 팔로잉한 사람의 이메일을 보여준다. -->
        <v-list-item-content>
          <v-list-item-title v-text="item.followerEmail.split('@')[0]"></v-list-item-title>
        </v-list-item-content>
      <!-- 팔로잉 목록에서 제거 -->
      <v-icon
        @click="[deletefollower(item.followerEmail)]"
      >mdi-trash-can</v-icon>
      </v-list-item>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "FollowingList",
  computed: {
    ...mapGetters({followers: 'followingData'}),
    ...mapGetters(['followingProfileImage']),
    // 사용자가 이미지 설정하지 않은 경우 default 이미지 첨부
    creatorImage() {
      return this.imageError ? this.defaultImage : "creator-image.jpg"
    },
  },
  data() {
    return {
      items: [],
      // followers: [],
      defaultImage: require('@/assets/images/mindwiki_logo-color.png'),
      imageError: false,
      followingsImage: [],
      user: {
        files: '',
      },
      profile: '',
      message: '',
      profileDefaultPic: '',
    }
  },
  methods: {
    // 팔로잉 확인
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.getters.followingData
      })
    },
    // 팔로잉 사진 확인
    readfollowingimage() {
      this.$store.dispatch('readFollowingProfileImage', this.$store.getters.getJWT).then(() => {
        this.followingsImage = this.$store.getters.followingProfileImage
        for (var i = 0; i < this.$store.state.followingData.length; i++) {
        // 현재 프로필 사진 순서와 팔로우 주인 순서가 같다.
        // followingData에 items 순서대로 넣어주기
        this.$set(this.items[i], 'profileDefaultPic', this.followingsImage[i]);
      }        
    })
    },
    // 팔로잉 목록에서 제거
    deletefollower(followeremail) {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', followeremail);
      this.$store.dispatch('deleteFollower', form).then(() => {
        this.items = this.$store.getters.followingData
        this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
          this.items = this.$store.getters.followingData
        })        
      })
    },
    
  },
  // 팔로워 확인
  created: function() {
    this.readfollowing()
    this.readfollowingimage()

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