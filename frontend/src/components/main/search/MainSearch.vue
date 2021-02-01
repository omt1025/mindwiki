<template>
  <search-bar @input-change="onInputChange"></search-bar>
</template>

<script>
import SearchBar from './SearchBar.vue';
export default {
  components: { SearchBar },
  methods: {
    onInputChange: function(inputText) {
      // 입력한 데이터
      this.inputValue = inputText;
    },
  },
};
</script>

<style></style>
