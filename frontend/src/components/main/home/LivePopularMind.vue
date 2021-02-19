<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 작성자 프로필 가져오기
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-17
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
  name: 'LivePopular',
  data: () => ({ items: null }),
  components: {
    CardForm,
  },
  computed: {
    ...mapGetters(['cards', 'memberList']),
  },
  // 전체 게시글을 가져옴
  created() {
    this.$store.dispatch('readLiveRankingMindMap', this.$store.getters.getJWT).then(() => {
      this.items = this.$store.state.cards;
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
@media (min-width: 320px) and (max-width: 480px) {
  #list {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  #list {
    max-width: 540px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  #list {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  #list {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  #list {
    max-width: 720px;
    margin: 0 auto;
  }
}
</style>
