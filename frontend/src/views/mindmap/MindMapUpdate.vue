<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 맵 수정시, 썸네일 캡처 업로드 
    * 생성일자 : 2021-01-25
    * 최종수정일자 : 2021-02-18
  -->

  <div id="app">
    <!-- <div ref="printMe" id="printMe"> -->
    <!-- 마인드맵 api 사용 -->
    <mind-map
      :data="map"
      id="mind"
      height="700"
      :show-reason="false"
      :data-template="{ label: ' ', reason: 0 }"
      :no="no"
      :button="true"
      :where="where"
      @send="updatemapdata"
    ></mind-map>
  </div>
  <!-- </div> -->
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'MindMapUpdate',
  data() {
    // 마인드맵 번호
    const no = Number(this.$route.params.no);
    return {
      no: no,
      where: 'update',
    };
  },
  components: { mindMap },
  computed: {
    ...mapGetters({ map: Object('getMapData'), likecheck: 'likeData', scrapcheck: 'scrapData' }),
  },
  methods: {
    // 맵 업데이트[YJS]
    updatemapdata(mapData) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);
      form.append('data', JSON.stringify(mapData));
      this.$store.dispatch('updateMapData', form).then(() => {
        this.map = this.$store.getters.getMapData;
        // 저장 후 상세 마인드맵 페이지로 이동
        this.$router.push({ name: 'MindMapDetail', params: { no: Number(this.no) } });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}

#app {
  // margin-top: 50px;
}

::v-deep .drawing-board {
  border-radius: 4px;
}

@media (min-width: 320px) and (max-width: 480px) {
  #mind {
    max-width: 375px;
    margin: 0 auto;
  }
}

@media (min-width: 576px) {
  .mindmap_detail {
    max-width: 540px;
    margin: 0 auto;
  }
  #mind {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  #mind {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 992px) {
  #mind {
    max-width: 720px;
    margin: 0 auto;
  }
}

@media (min-width: 1200px) {
  #mind {
    max-width: 720px;
    margin: 0 auto;
  }
}
</style>
