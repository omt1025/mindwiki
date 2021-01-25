<template>
  <div>
    <SearchNavi/>
    <v-container>
      <SearchBar @input-change="onInputChange" />
    </v-container>
    
    <BottomNavi/>
  </div>
</template>

<script>
import SearchBar from '@/components/common/SearchBar.vue'
import SearchNavi from '@/components/navi/SearchNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Search',
  components: {
    SearchNavi,
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