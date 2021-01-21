<template>
    <v-card>
    <v-toolbar
      color="primary"
      dark
      flat
    >
      
      <v-spacer></v-spacer>

      <v-toolbar-title>Mind Wiki</v-toolbar-title>

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
      <!-- <v-tab-item
        v-for="item in items"
        :key="item"
      >
      </v-tab-item> -->
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <Card/>
          </v-card-text>
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <v-card-title class="headline">
            두번째
          </v-card-title>
          <v-card-text>
            <p>B</p>
          </v-card-text>
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <v-card-title class="headline">
            세번째
          </v-card-title>
          <v-card-text>
            <p>C</p>
          </v-card-text>
        </v-card>
      </v-tab-item>

    </v-tabs-items>
  </v-card>
</template>

<script>
  import Card from "@/components/common/Card.vue"
  
  export default {
    name: 'MainNavi',
    components: {
      Card
    },
    data () {
      return {
        tab: null,
        items: [
          '실시간', '관심태그', 'My Wiki'
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
