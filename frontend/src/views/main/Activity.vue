<template>
  <v-app>
    <ActivityNavi/>
    <BottomNavi/>
  </v-app>
</template>

<script>
import ActivityNavi from '@/components/navi/ActivityNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Search',
  components: {
    ActivityNavi,
    BottomNavi,
  },
}
</script>

<style>

</style>