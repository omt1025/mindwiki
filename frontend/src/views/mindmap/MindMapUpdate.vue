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
  </div>
</template>

<script>
import mindMap from '../../components/mindmap/mind-map.vue';

export default {
  name: 'MindMapUpdate',
  data() {
    // const map = this.$route.params.map
    return {
      // map: map
      map: [
        {"children":
            [{"reason":"0","label":"123"},
            {"reason":"0","label":"123"},
            {"reason":"0","label":"123"}],
        "root":"true",
        "label":"woong",
        "url":""}
      ]
    };
  },
  components: { mindMap },
  methods: {
    handleDataChange(data) {
      this.map = data;
    },
    handleNodeDelete(nodeData, callback) {
      console.log(nodeData);

      callback(true);
    },
  },
  created() {
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