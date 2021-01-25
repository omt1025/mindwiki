<template>
  <v-app>
    <FollowNavi/>
    <BottomNavi/>
  </v-app>
</template>

<script>
import FollowNavi from '@/components/navi/FollowNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

  export default {
    name: "Followers",
    components: {
      FollowNavi,
      BottomNavi
    },
  }
</script>

<style>

</style>