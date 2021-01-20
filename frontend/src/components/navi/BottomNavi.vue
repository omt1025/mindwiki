<template>
  <v-app>
    <v-bottom-navigation
      v-model="value"
      :background-color="color"
      dark
      shift
      fixed
      justify="space"
    >
      
      <v-btn>
        <span>홈</span>

        <v-icon>mdi-home</v-icon>
      </v-btn>

      <v-btn>
        <span>검색</span>

        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn>
        <span>생성</span>

        <v-icon>mdi-head-plus-outline</v-icon>
      </v-btn>

      <v-btn>
        <span>활동</span>

        <v-icon>mdi-bell</v-icon>
      </v-btn>

      <v-btn>
        <span>내 책장</span>

        <v-icon>mdi-bookshelf</v-icon>
      </v-btn>  
    
    </v-bottom-navigation>
  </v-app>
</template>

<script>
  export default {
    name: 'BottomNavi',
    data: () => ({ value: 0 }),

    computed: {
      color () {
        switch (this.value) {
          case 0: return 'primary'
          case 1: return 'primary'
          case 2: return 'primary'
          case 3: return 'primary'
          case 4: return 'primary'
          default: return 'blue-grey'
        }
      },
    },
  }
</script>