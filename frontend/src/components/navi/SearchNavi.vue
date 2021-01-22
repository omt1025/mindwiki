<template>
  <v-app>
    <v-app-bar
      shift
      fixed
      color="primary"
      dark
    >

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">검색</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>

    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'SearchNavi',
  methods: {
    menu: function () {
      this.$router.push('/main')
    }
  },
}
</script>