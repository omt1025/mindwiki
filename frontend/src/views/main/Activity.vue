<template>
  <v-app>
    <!-- <ActivityNavi/> -->
    <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
      <!-- Navbar content -->
      <a class="navbar-brand" href="#"
        ><img src="@/assets/images/user/mindwiki_logo.png" height="27px"
      /></a>
    </nav>
    <BottomNavi />
  </v-app>
</template>

<script>
// import ActivityNavi from '@/components/navi/ActivityNavi.vue';
import BottomNavi from '@/components/navi/BottomNavi.vue';

export default {
  name: 'Search',
  components: {
    // ActivityNavi,
    BottomNavi,
  },
};
</script>

<style></style>
