<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 실시간 인기 마인드 리스트 출력
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-09
  -->
  <v-container fluid>
    <v-row dense id="list">
      <!-- CardForm 컴포넌트로 데이터를 보냄 -->
      <CardForm 
        v-for="(card, index) in items" 
        :key="index"
        :card="card"
      />
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import CardForm from '../../common/CardForm.vue'

export default {
  name: 'LivePopular',
  data: () => ({ items: null }),
  components: {
    CardForm
  },
  computed: {
    ...mapGetters(['cards']),
  },
  // 전체 게시글을 가져옴
  created() {
    this.$store.dispatch('readMindMap', this.$store.getters.getJWT).then(() => {
    this.items = this.$store.state.cards
    });
  },
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
