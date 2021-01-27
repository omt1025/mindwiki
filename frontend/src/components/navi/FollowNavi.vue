<template>
  <v-card>
    <v-toolbar>
      <v-spacer></v-spacer>
      <!-- <v-toolbar-title>Follow</v-toolbar-title> -->
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>
      
      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>

      <template v-slot:extension>
        <v-tabs v-model="tab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab
            v-for="item in items"
            :key="item"
          >
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item>
        <v-card flat>
          <FollowerList/>
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <FollowingList/>
        </v-card>
      </v-tab-item>

    </v-tabs-items>
  </v-card>
</template>

<script>
  import FollowerList from "@/components/common/FollowerList.vue"
  import FollowingList from "@/components/common/FollowingList.vue"
  
  export default {
    name: 'FollowNavi',
    components: {
      FollowerList,
      FollowingList,
    },
    data () {
      return {
        tab: null,
        items: [
          '팔로워', '팔로잉'
        ],
      }
    },
    methods: {
      menu: function () {
        this.$router.push('/main/menu')
      }
    }
  }
</script>
<style scoped>
.v-sheet.v-card:not(.v-sheet--outlined) {
  box-shadow: none;
}
</style>