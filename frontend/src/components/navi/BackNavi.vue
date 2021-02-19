<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 뒤로가기 navi 재사용
    * 생성일자 : 2021-02-15
    * 최종수정일자 : 2021-02-16
 -->
  <v-card id="topnavi">
    <v-toolbar id="navi_shadow">
      <v-icon v-if="title !== '마인드맵 수정'" @click="backPage">mdi-keyboard-backspace</v-icon>
      <v-toolbar-title class="flex text-left" font-family>{{ title }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-icon
        v-if="title !== '알림' && sub !== '게시물 상세 보기'"
        @click="checkbtn"
        color="#a64bf4"
        >mdi-check</v-icon
      >
    </v-toolbar>
  </v-card>
</template>

<script>
export default {
  props: {
    title: null,
    sub: null,
  },
  methods: {
    backPage: function() {
      this.$emit('backbtn');
    },
    checkbtn: function() {
      this.$emit('checkbtn');
    },
  },
};
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Fredoka%20One');
@import url('https://fonts.googleapis.com/css?family=Black+Han+Sans:400');

.v-toolbar__title {
  font-family: 'Fredoka One';
  padding-left: 25px;
}
// .v-toolbar__title {
//   font-family: 'Black Han Sans';
// }
.closenavi {
  max-height: 56px;
  height: 56px;
}
</style>
