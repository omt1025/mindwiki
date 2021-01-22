<template>
  <v-app>
    <v-app-bar
      shift
      fixed
      color="primary"
      dark
    >
        <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">정보 수정</v-toolbar-title>
      <v-spacer></v-spacer>

    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'InfoUpdateNavi',
  methods: {
    backPage: function () {
      this.$router.push('/main')
    }
  },
}
</script>