<template>
  <div id="app">
    <div id="nav">
      <TopNavi/>
      <BottomNavi/>
      <!-- <router-link to="/main/home">홈</router-link> |
      <router-link to="/main/search">검색</router-link> |
      <router-link to="/main/makemind">생성</router-link> |
      <router-link to="/main/activity">활동</router-link> |
      <router-link to="/main/mylibrary">내 책장</router-link> -->
      <v-card>
        <v-tabs
          v-model="tab"
          background-color="primary"
          dark
        >
          <v-tab
            v-for="item in items"
            :key="item.tab"
          >
            {{ item.tab }}
          </v-tab>
        </v-tabs>
      </v-card>
    </div>
    <router-view/>
  </div>
</template>

<script>
import TopNavi from '@/components/navi/TopNavi.vue'
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: 'Main',
  components: {
    TopNavi,
    BottomNavi
  },
  data () {
    return {
      tab: null,
      items: [
        { tab: '실시간'},
        { tab: '관심 태그'},
        { tab: 'MyWiki'},
      ],
    }
  },
}
</script>

<style>

</style>
