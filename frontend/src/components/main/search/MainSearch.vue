<template>
  <v-app>
    <!-- 프로필 검색을 눌렀을 때 -->
    <div v-if="mainTab == 0">
      <v-card flat>
      </v-card>
    </div>
    
    <!-- 타이틀 검색을 눌렀을 때 -->
    <div v-if="mainTab == 1">
      <v-card flat>
      </v-card>
    </div>
    
    <!-- 해시태그 검색을 눌렀을 때 -->
    <div v-if="mainTab == 2">
      <v-card flat>
      </v-card>
    </div>
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
