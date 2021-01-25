<template>
  <div id="app">
    <v-container>
      <input class="fas fa-search" placeholder="검색" @keypress.enter="onInputKeyword" type="text"/>
      <v-icon>mdi-magnify</v-icon>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  methods: {
    onInputKeyword: function (event) {
      this.$emit('input-change', event.target.value)
    }
  }
}
</script>

<style>

</style>