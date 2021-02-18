<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 맵 업데이트 구현
    * 생성일자 : 2021-01-25
    * 최종수정일자 : 2021-02-18
  -->
  <div id="app">
    <!-- 마인드맵 api 사용 -->
    <mind-map
      :data="map"
      width="360"
      height="600"
      :show-reason="false"
      :data-template="{ label: ' ', reason: 0 }"
      :no="no"
      :button="true"
      @send="updatemapdata"
    ></mind-map>
  </div>
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'MindMapUpdate',
  data() {
    // 마인드맵 번호
    const no = Number(this.$route.params.no)
    return {
      no: no,
    };
  },
  components: { mindMap },
  computed: {
    ...mapGetters({ map:Object('getMapData'), likecheck:'likeData', scrapcheck:'scrapData'}),
  },
  methods: {
    // 맵 업데이트
    updatemapdata(mapData) {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);
      form.append('data', JSON.stringify(mapData));

      this.$store.dispatch('updateMapData', form).then(() => {
        this.map = this.$store.getters.getMapData
        // 저장 후 상세 마인드맵 페이지로 이동
        this.$router.push({ name: 'MindMapDetail', params: { no: Number(this.no) } });
      });
    },
  }
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}

#app {
  margin-top: 50px;
}

::v-deep .drawing-board {
  border-radius: 4px;
}
</style>