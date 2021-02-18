<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 내 활동 알림 구현
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-02-18
  -->
  <v-app>
    <!-- <div v-if="mainTab == 0"> -->
    <v-card flat>
      <activity-list id="act"></activity-list>
    </v-card>
  </v-app>
</template>

<script>
import ActivityList from './ActivityList.vue';
import { mapGetters } from 'vuex';

export default {
  components: {
    ActivityList,
  },
  computed: {
    ...mapGetters(['mainTab']),
  },
  data() {
    return {
      tab: null,
      items: ['알림', '메세지'],
    };
  },
};
</script>

<style scoped>
@media (min-width: 320px) and (max-width: 480px) {
  #act {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  #act {
    max-width: 540px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  #act {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  #act {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  #act {
    max-width: 720px;
    margin: 0 auto;
  }
}
</style>
