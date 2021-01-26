<template>
  <v-app>
    <div>
      <MainNavi />
      <BottomNavi />
    </div>
    <div>
    </div>
  </v-app>
</template>

<script>
import MainNavi from '@/components/navi/MainNavi.vue';
import BottomNavi from '@/components/navi/BottomNavi.vue';

export default {
  name: 'Main',
  components: {
    MainNavi,
    BottomNavi,
  },
};
</script>

<style>
html,
body,
.v-application,
.v-application--wrap {
  min-height: -webkit-fill-available;
}
#nav {
  padding: 0;
}
</style>
