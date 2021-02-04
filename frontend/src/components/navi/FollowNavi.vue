<template>
  <v-card>
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">{{ nickName }}</v-toolbar-title>
      <v-spacer></v-spacer>

      <template v-slot:extension>
        <v-tabs v-model="tab" grow color="#a64bf4">
          <v-tabs-slider color="#a64bf4"></v-tabs-slider>

          <v-tab v-for="item in items" :key="item">
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item>
        <v-card flat>
          <FollowerList />
        </v-card>
      </v-tab-item>

      <v-tab-item>
        <v-card flat>
          <FollowingList />
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
import FollowerList from '@/components/common/FollowerList.vue';
import FollowingList from '@/components/common/FollowingList.vue';

export default {
  name: 'FollowNavi',
  components: {
    FollowerList,
    FollowingList,
  },
  computed: {
    ...mapGetters(['nickName', 'userId']),
  },
  data() {
    return {
      tab: null,
      items: ['팔로워', '팔로잉'],
    };
  },
  methods: {
    backPage: function() {
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'profile');
      this.$router.push('/main');
    },
  },
};
</script>
<style scoped>
.v-toolbar__title {
  font-family: 'Fredoka One';
}
</style>
