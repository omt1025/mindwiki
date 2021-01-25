<template>
  <v-card>
    <v-toolbar
      color="primary"
      dark
      flat
    >
      
      <v-spacer></v-spacer>

      <v-toolbar-title>Follow</v-toolbar-title>

      <v-spacer></v-spacer>
      

      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>


      <template v-slot:extension>
        <v-tabs
          v-model="tab"
          grow
        >
          <v-tabs-slider color="yellow"></v-tabs-slider>

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
          <FollowList/>
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <FollowList/>
        </v-card>
      </v-tab-item>

    </v-tabs-items>
  </v-card>
</template>

<script>
  import FollowList from "@/components/common/FollowList.vue"
  
  export default {
    name: 'FollowNavi',
    components: {
      FollowList,
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
