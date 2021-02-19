<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 컴포넌트 사용
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-15
  -->
  <v-app id="mainhome">
    <div v-if="mainTab == 0">
      <v-card flat>
        <LivePopularMind />
      </v-card>
    </div>
    <div v-if="mainTab == 1">
      <v-card flat>
        <FavoriteMind />
      </v-card>
    </div>
    <div v-if="mainTab == 2">
      <v-card flat>
        <MyWiki />
      </v-card>
    </div>
  </v-app>
</template>

<script>
import LivePopularMind from '@/components/main/home/LivePopularMind.vue';
import FavoriteMind from '@/components/main/home/FavoriteMind.vue';
import MyWiki from '@/components/main/home/MyWiki.vue';
import { mapGetters } from 'vuex';

export default {
  components: {
    LivePopularMind,
    FavoriteMind,
    MyWiki,
  },
  computed: {
    ...mapGetters(['mainTab']),
  },
  created() {
    let form = new FormData();
    form.append('jwt', this.$store.getters.getJWT);
    this.$store.dispatch('readMemberList', form).then(() => {});
  },
  data() {
    return {
      tab: null,
      items: ['실시간', '관심태그', 'My Wiki'],
    };
  },
};
</script>

<style scoped>
.mainhome {
  padding: 0 10px;
}
</style>
