<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 컴포넌트 연결
    * 생성일자 : 2021-01-22
    * 최종수정일자 : 2021-01-22
  -->
  <div class="home">
    <StartPage msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import StartPage from '@/components/StartPage.vue'

export default {
  name: 'Home',
  components: {
    StartPage
  }
}
</script>
