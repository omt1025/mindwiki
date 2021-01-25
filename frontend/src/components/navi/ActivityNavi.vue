<template>
  <v-card>
    <v-toolbar
      color="primary"
      dark
      flat
    >
      
      <v-spacer></v-spacer>

      <v-toolbar-title>Activity</v-toolbar-title>

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
          <ActivityList/>
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <ActivityList/>
        </v-card>
      </v-tab-item>

    </v-tabs-items>
  </v-card>
</template>

<script>
import ActivityList from "@/components/common/ActivityList.vue"

export default {
  name: 'ActivityNavi',
  components: {
    ActivityList
  },
  data () {
    return {
      tab: null,
      items: [
        '알림', '메세지'
      ],
    }
  },
  methods: {
    menu: function () {
      this.$router.push('/main')
    }
  },
}
</script>