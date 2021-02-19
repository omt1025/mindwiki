<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 반응형 수정
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-19
  -->
  <!-- 내가 팔로우한 사람들(팔로잉)을 보여준다. -->
  <div id="app">
    <template v-for="item in items">
      <v-subheader v-if="item.header" :key="item.header"></v-subheader>
      <v-list-item v-else :key="item.data">
        <v-list-item-avatar>
          <!-- 이미지 있을 때 -->
          <v-img
            v-if="item.profileDefaultPic !== null"
            v-bind:followingsImage="followingsImage"
            alt=""
            :src="item.profileDefaultPic"
          ></v-img>
          <!-- 이미지 없을 때 -->
          <v-img v-else alt="" :src="creatorImage" @error="imageError = true"></v-img>
        </v-list-item-avatar>
        <!-- 내가 팔로잉한 사람의 닉네임을 보여준다. -->
        <v-list-item-content>
          <v-list-item-title v-text="item.name"></v-list-item-title>
        </v-list-item-content>
        <!-- 팔로잉 목록에서 제거 -->
        <v-icon @click="[deletefollower(item.followerEmail)]">mdi-trash-can</v-icon>
      </v-list-item>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'FollowingList',
  computed: {
    ...mapGetters({ followers: 'followingData' }),
    ...mapGetters(['followingProfileImage']),
    // 사용자가 이미지 설정하지 않은 경우 default 이미지 첨부
    creatorImage() {
      return this.imageError ? this.defaultImage : 'creator-image.jpg';
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
    };
  },
  methods: {
    // 팔로잉 확인
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.getters.followingData;
        console.log(this.items);
      });
    },
    // 팔로잉 사진 확인
    readfollowingimage() {
      this.$store.dispatch('readFollowingProfileImage', this.$store.getters.getJWT).then(() => {
        this.followingsImage = this.$store.getters.followingProfileImage;
        for (var i = 0; i < this.$store.state.followingData.length; i++) {
          // 현재 프로필 사진 순서와 팔로우 주인 순서가 같다.
          // followingData에 items 순서대로 넣어주기
          if (this.followingsImage[i] === null) this.$set(this.items[i], 'profileDefaultPic', null);
          else this.$set(this.items[i], 'profileDefaultPic', this.followingsImage[i]);
        }
      });
    },
    // 팔로잉 목록에서 제거
    deletefollower(followeremail) {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('followeremail', followeremail);
      this.$store.dispatch('deleteFollower', form).then(() => {
        this.items = this.$store.getters.followingData;
        this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
          this.items = this.$store.getters.followingData;
          this.readfollowingimage();
        });
      });
    },
  },
  // 팔로워 확인
  created: function() {
    this.readfollowing();
    this.readfollowingimage();
  },
};
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
  padding: 11px 11px;
}
hr {
  margin-top: 0.4rem;
  margin-bottom: 0.4rem;
  margin-left: 1.1rem;
  margin-right: 1.1rem;
}
.v-list-item {
  padding: 0 25px;
}
@media (min-width: 320px) and (max-width: 480px) {
  #app {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  #app {
    max-width: 540px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  #app {
    max-width: 720px;
    margin: 0 auto;
  }
}
</style>
