<template>
  <div id="app">
    <div id="nav"></div>
    <router-view />
  </div>
</template>

<script>
import './components/css/style.scss';
export default {
  name: 'app',
};
</script>

<style lang="scss">
@import './assets/vendor/bootstrap/css/bootstrap.min.css'; // bootstrap
@import './assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css'; // fontawesome 아이콘
@import './assets/fonts/iconic/css/material-design-iconic-font.min.css'; // iconic

// 공통 적용되는 css파일
@import './assets/css/util.css';
@import './assets/css/main.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
