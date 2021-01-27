<template>
  <div>
    <SearchBar @input-change="onInputChange" />
    <BottomNavi/>
</div>
</template>

<script>
import SearchBar from '@/components/common/SearchBar.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Search',
  components: {
    BottomNavi,
    SearchBar
  },
  methods: {
    onInputChange: function (inputText) {
      // 입력한 데이터
      this.inputValue = inputText      
    }  
  }
}
</script>

<style>

</style>