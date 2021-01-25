<template>
  <v-app>
    <v-app-bar
      shift
      fixed
      height="58.44px"
      elevation="1"
    >
        <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">메뉴</v-toolbar-title>
      <v-spacer></v-spacer>

    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'BackPageNavi',
  methods: {
    backPage: function () {
      this.$router.push('/main')
    }
  },
}
</script>
