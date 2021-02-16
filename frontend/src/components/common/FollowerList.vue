<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 팔로워 리스트 구현
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-16
  -->
  <!-- 나를 팔로우한 사람을 보여준다. -->
  <div id="app">
    <template v-for="follower in followers">
      <v-subheader
        v-if="follower.header"
        :key="follower.header"
      ></v-subheader>

      <v-list-item
      v-else
      :key="follower.data"
      >
        <v-list-item-avatar>
          <v-img
            alt=""
            :src="creatorImage"
            @error="imageError = true"
          ></v-img>
        </v-list-item-avatar>
        <!-- 나를 팔로우한 사람의 이메일을 보여준다. -->
        <v-list-item-content>
          <v-list-item-title v-html="follower.split('@')[0]"></v-list-item-title>
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
    }
  },
  methods: {
    // 팔로워 목록 조회[HYH]
    readfollower() {
      this.$store.dispatch('readFollower', this.$store.getters.getJWT).then(() => {
        this.followers = this.$store.getters.followerData
      });
    },
  },
  // 팔로워 확인
  created: function() {
    this.readfollower()
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