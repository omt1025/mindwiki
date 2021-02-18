<template>
  <div>
    <div id="app">
      <div ref="printMe" id="printMe">
        <mind-map
          id="mind"
          :data="map"
          height="600"
          width="360"
          :show-reason="false"
          :data-template="{ label: ' ', reason: 0 }"
          @data-change="handleDataChange"
          @node-delete="handleNodeDelete"
        ></mind-map>
      </div>
      <button @click="print">Click</button>
    </div>
    <!-- OUTPUT -->
    <img :src="output" style="object-fit: cover;" />
  </div>
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';

export default {
  name: 'App',
  data() {
    return {
      output: null,
      message: '',
      map: [
        {
          label: '마인드맵',
          root: true,
          reason: 0,
          url: '',
          children: [
            {
              label: 'A1',
              reason: 0,
              children: [
                {
                  label: '홍홍',
                  reason: 0,
                },
                {
                  label: '콩콩',
                  reason: 0,
                },
                {
                  label: '둥둥',
                  reason: 0,
                },
              ],
            },
            {
              label: 'A2',
              reason: 0,
              children: [
                {
                  label: '얄라리',
                  reason: 0,
                },
                {
                  label: '얄라',
                  reason: 0,
                },
              ],
            },
            {
              label: 'A3',
              reason: 0,
            },
          ],
        },
      ],
    };
  },
  components: { mindMap },
  mounted() {
    // this.print();
  },
  methods: {
    handleDataChange(data) {
      this.map = data;
    },
    handleNodeDelete(nodeData, callback) {
      console.log(nodeData);

      callback(true);
    },
    updatemapdata() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);
      form.append('data', this.map);

      this.$store.dispatch('readMapData', form).then(() => {
        this.map = this.$store.getters.getMapData;
      });
    },
    async print() {
      let el = this.$refs.printMe;
      el.appendChild(document.getElementById('mind'));
      // add option type to get the image version
      // if not provided the promise will return
      // the canvas.
      // let mind = document.getElementById('printMe');

      let options = {
        type: 'dataURL',
      };
      this.output = await this.$html2canvas(el, options);
      // this.output = this.$html2canvas(document.getElementById('printMe'), options).then(function(
      //   canvas
      // ) {
      //   document.body.appendChild(canvas);
      // });

      // const decodImg = atob(this.output.split(',')[1]);

      // let array = [];
      // for (let i = 0; i < decodImg.length; i++) {
      //   array.push(decodImg.charCodeAt(i));
      // }

      // const file = new Blob([new Uint8Array(array)], { type: 'image/jpeg' });
      // // const fileName = 'canvas_img_' + new Date().getMilliseconds() + '.jpg';

      // console.log(file);

      var file = this.dataURLtoFile(this.output, 'capture.img');
      console.log(file);

      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', '140');
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
    // const dataURLtoFile = (dataurl, fileName) => {

    //     var arr = dataurl.split(','),
    //         mime = arr[0].match(/:(.*?);/)[1],
    //         bstr = atob(arr[1]),
    //         n = bstr.length,
    //         u8arr = new Uint8Array(n);

    //     while(n--){
    //         u8arr[n] = bstr.charCodeAt(n);
    //     }

    //     return new File([u8arr], fileName, {type:mime});
    // }

    // //Usage example:
    // var file = dataURLtoFile('data:text/plain;base64,aGVsbG8gd29ybGQ=','hello.txt');
    // console.log(file);
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
