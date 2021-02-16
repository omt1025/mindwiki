<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 팔로잉 리스트 구현
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-16
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
          <v-img
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
    }
  },
  methods: {
    // 팔로잉 확인
    readfollowing() {
      this.$store.dispatch('readFollowing', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.getters.followingData
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