<template>
  <v-app>
    <v-app-bar

      shift
      fixed
      color="primary"
      dark
    >
      <v-btn to='/main' icon color="white">
        <v-icon>mdi-keyboard-backspace</v-icon>
      </v-btn>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">메뉴</v-toolbar-title>
      <v-spacer></v-spacer>

    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: 'BackPageNavi',
}
</script>