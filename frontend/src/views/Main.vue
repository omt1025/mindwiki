<template>
  <v-app>
    <TopNavi/>
    <BottomNavi/>
    <v-tabs
    fixed-tabs
    background-color="primary"
    dark
  >
    <v-tab
      v-for="item in items"
      :key="item.tab"
    >
      {{ item.tab }}
    </v-tab>
  </v-tabs>
  </v-app>
</template>

<script>
import TopNavi from '@/components/navi/TopNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Main',
  components: {
    TopNavi,
    BottomNavi
  },
  data () {
    return {
      tab: null,
      items: [
        { tab: '실시간' },
        { tab: '관심 태그' },
        { tab: 'MyWiki' },
      ],
    }
  },
}
</script>

<style>
html, body, .v-application, .v-application--wrap {
    min-height: -webkit-fill-available;
  }
</style>
