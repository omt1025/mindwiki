<template>
  <div id="app">
    {{ map }}
    <mind-map
      :data="map"
      width="360"
      height="600"
      :show-reason="false"
      :data-template="{ label: ' ', reason: 0 }"
      @data-change="handleDataChange"
      @node-delete="handleNodeDelete"
    ></mind-map>
    <v-btn @click="updatemapdata">임시 버튼</v-btn>
  </div>
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'MindMapUpdate',
  data() {
    // const map = this.$route.params.map
    const no = Number(this.$route.params.no)
    return {
      // map: map
      no: no,
    };
  },
  components: { mindMap },
  computed: {
    ...mapGetters({ map:Object('getMapData'), likecheck:'likeData', scrapcheck:'scrapData'}),
  },
  methods: {
    handleDataChange(data) {
      this.map = data;
    },
    handleNodeDelete(nodeData, callback) {
      console.log(nodeData);

      callback(true);
    },
     readmapdata() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);

      this.$store.dispatch('readMapData', form).then(() => {
        this.map = this.$store.getters.getMapData
        console.log(this.map)
      });
    },
    updatemapdata() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', this.no);
      form.append('data', JSON.stringify(this.map));

      this.$store.dispatch('updateMapData', form).then(() => {
        this.map = this.$store.getters.getMapData

        console.log(this.map)
        console.log(typeof this.map)
      });
    },

  },
  created() {
    //this.readmapdata();
    console.log(typeof this.map)
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