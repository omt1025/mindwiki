<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 맵 수정시, 썸네일 캡처 업로드 
    * 생성일자 : 2021-01-25
    * 최종수정일자 : 2021-02-18
  -->
  <div id="app">
    <div ref="printMe" id="printMe">
      <!-- 마인드맵 api 사용 -->
      <mind-map
        :data="map"
        id="mind"
        width="360"
        height="600"
        :show-reason="false"
        :data-template="{ label: ' ', reason: 0 }"
        :no="no"
        :button="true"
        @send="updatemapdata"
      ></mind-map>
    </div>
  </div>
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
    };
  },
  components: { mindMap },
  computed: {
    ...mapGetters({ map: Object('getMapData'), likecheck: 'likeData', scrapcheck: 'scrapData' }),
  },
  methods: {
    // 맵 업데이트
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

      this.print();
    },
    async print() {
      let el = this.$refs.printMe;
      el.appendChild(document.getElementById('mind'));

      let options = {
        type: 'dataURL',
      };
      this.output = await this.$html2canvas(el, options);

      var file = this.dataURLtoFile(this.output, 'capture.img');
      console.log(file);

      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);
      form.append('files', file);

      this.$store.dispatch('captureImage', form).then(() => {
        // 응답 결과
        this.message = this.$store.getters.message;
        if (this.message === 'FAIL') console.log('실패');
        else console.log('성공');
      });
    },
    // 파일 변환 메소드
    dataURLtoFile(dataurl, fileName) {
      var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new File([u8arr], fileName, { type: mime });
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
  margin-top: 50px;
}

::v-deep .drawing-board {
  border-radius: 4px;
}
</style>
