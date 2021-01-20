<template>
  <div id="app">
    <div id="nav">
      <TopNavi/>
      <BottomNavi/>
      <!-- <router-link to="/main/home">홈</router-link> |
      <router-link to="/main/search">검색</router-link> |
      <router-link to="/main/makemind">생성</router-link> |
      <router-link to="/main/activity">활동</router-link> |
      <router-link to="/main/mylibrary">내 책장</router-link> -->
    </div>
    <router-view/>
  </div>
</template>

<script>
import TopNavi from '@/components/navi/TopNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Main',
  components: {
    TopNavi,
    BottomNavi
  }
}
</script>

<style>

</style>
