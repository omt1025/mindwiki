<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 반응형 수정, 닉네임 가져오기
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-19
  -->
  <!-- 나를 팔로우한 사람을 보여준다. -->
  <div id="app">
    <template v-for="follower in items">
      <v-subheader v-if="follower.header" :key="follower.header"></v-subheader>
      <v-list-item v-else :key="follower.index">
        <v-list-item-avatar>
          <!-- 이미지 있을 때 -->
          <v-img
            v-if="follower.profileDefaultPic !== null"
            alt=""
            :src="follower.profileDefaultPic"
            @error="imageError = true"
          ></v-img>
          <!-- 이미지 없을 때 -->
          <v-img v-else alt="" :src="creatorImage" @error="imageError = true"></v-img>
        </v-list-item-avatar>
        <!-- 나를 팔로우한 사람의 이메일을 보여준다. -->
        <v-list-item-content>
          <v-list-item-title v-html="follower.nickName"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'FollowerList',
  computed: {
    ...mapGetters(['followerData']),
    ...mapGetters(['followerProfileImage']),
    // 사용자가 이미지 설정하지 않은 경우 default 이미지 첨부
    creatorImage() {
      return this.imageError ? this.defaultImage : 'creator-image.jpg';
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
      members: '',
    };
  },
  methods: {
    // 팔로워 목록 조회[HYH]
    readfollower() {
      this.$store.dispatch('readFollower', this.$store.getters.getJWT).then(() => {
        this.followers = this.$store.getters.followerData;

        this.$store.dispatch('readFollowerProfileImage', this.$store.getters.getJWT).then(() => {
          this.followersImage = this.$store.getters.followerProfileImage;
          for (var j = 0; j < this.$store.state.followerData.length; j++) {
            // 현재 프로필 사진 순서와 팔로우 주인 순서가 같다.
            // followingData에 items 순서대로 넣어주기
            const form = [];
            this.$set(form, 'email', this.followers[j]);
            this.$set(form, 'profileDefaultPic', this.followersImage[j]);
            this.items.push(form);
            this.getNickName();
          }
        });
      });
    },
    // 팔로워 닉네임 조회[YJS]
    getNickName() {
      for (var i = 0; i < this.items.length; i++) {
        for (var j = 0; j < this.members.length; j++) {
          if (this.items[i].email === this.members[j].email) {
            this.$set(this.items[i], 'nickName', this.members[j].nickName);
          }
        }
      }
    },
  },
  created: function() {
    // 팔로워 확인
    this.readfollower();

    // 회원 목록 가져오기
    let form1 = new FormData();
    form1.append('jwt', this.$store.getters.getJWT);

    this.$store.dispatch('readMemberList', form1).then(() => {
      this.members = this.$store.getters.memberList;
    });
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
