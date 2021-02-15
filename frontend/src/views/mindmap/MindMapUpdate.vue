<template>
  <div id="app">
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
  name: 'App',
  data() {
    return {
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
  methods: {
    handleDataChange(data) {
      this.map = data;
    },
    handleNodeDelete(nodeData, callback) {
      console.log(nodeData);

      callback(true);
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