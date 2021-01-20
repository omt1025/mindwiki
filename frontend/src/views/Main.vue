<template>
  <div id="app">
    <div id="nav">
      <router-link to="/main/home">홈</router-link> |
      <router-link to="/main/search">검색</router-link> |
      <router-link to="/main/makemind">생성</router-link> |
      <router-link to="/main/activity">활동</router-link> |
      <router-link to="/main/mylibrary">내 책장</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
