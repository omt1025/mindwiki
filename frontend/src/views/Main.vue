<template>
  <v-app>
    <TopNavi/>
    <BottomNavi/>
    <v-tabs
    fixed-tabs
    background-color="primary"
    dark
  >
    <v-tab>
      <h4>실시간</h4>
    </v-tab>
    <v-tab>
      <h4>관심태그</h4>
    </v-tab>
    <v-tab>
      <h4>My Wiki</h4>
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
}
</script>

<style>
html, body, .v-application, .v-application--wrap {
    min-height: -webkit-fill-available;
  }
</style>
