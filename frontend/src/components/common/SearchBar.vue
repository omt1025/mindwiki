<template lang="html">
  <div class="centerx icons-example">
    <v-container>
      <vs-input icon="search" placeholder="Search" @keypress.enter="onInputKeyword" type="text"/>
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