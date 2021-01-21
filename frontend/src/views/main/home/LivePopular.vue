<template>
  <div id="app">
    <div id="nav">
      <router-link to="/main/home">실시간</router-link> |
      <router-link to="/main/home/favorite">관심 태그</router-link> |
      <router-link to="/main/home/mywiki">My Wiki</router-link>
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