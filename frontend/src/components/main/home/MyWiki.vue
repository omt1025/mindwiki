<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 좋아요한 게시글
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-09
  -->
  <v-row dense id="list">
    <!-- CardForm 컴포넌트로 데이터를 보냄 -->
    <CardForm v-for="(card, index) in items" :key="index" :card="card" />
  </v-row>
</template>

<script>
import { mapGetters } from 'vuex';
import CardForm from '../../common/CardForm.vue';

export default {
  name: 'MyWiki',
  data: () => ({ items: null }),
  components: {
    CardForm,
  },
  computed: {
    ...mapGetters(['likeData', 'memberList']),
  },
  // 좋아요 누른 게시글을 가져옴
  created() {
    this.$store.dispatch('readLikeMindMap', this.$store.getters.getJWT).then(() => {
      this.items = this.$store.getters.likeData;
      this.getImage();
    });
  },
  methods: {
    // 작성자 프로필 사진 가져오기
    getImage() {
      for (var i = 0; i < this.items.length; i++) {
        for (var j = 0; j < this.memberList.length; j++) {
          if (this.items[i].admin === this.memberList[j].email) {
            this.$set(this.items[i], 'profileDefaultPic', this.memberList[j].profileDefaultPic);
            this.$set(this.items[i], 'nickName', this.memberList[j].nickName);

            break;
          }
        }
      }
    },
  },
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
