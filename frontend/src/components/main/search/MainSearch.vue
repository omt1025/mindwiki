<template>
  <v-app>


  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  components: {

  },
  computed: {
    ...mapGetters(['mainTab']),
  },
  data() {
    return {
      tab: null,
      items: ['프로필', '타이틀', '해시태그'],
    };
  },
};
</script>

<style></style>
