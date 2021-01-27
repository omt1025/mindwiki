<template>
  <v-app>
    <div v-if="mainTab == 0">
      <v-card flat>
        <activity-list></activity-list>
      </v-card>
    </div>
    <div v-if="mainTab == 1">
      <v-card flat>
        <activity-message></activity-message>
      </v-card>
    </div>
  </v-app>
</template>

<script>
import ActivityList from './ActivityList.vue';
import ActivityMessage from './ActivityMessage.vue';

import { mapGetters } from 'vuex';

export default {
  components: {
    ActivityList,
    ActivityMessage,
  },
  computed: {
    ...mapGetters(['mainTab']),
  },
  data() {
    return {
      tab: null,
      items: ['알림', '메세지'],
    };
  },
};
</script>

<style></style>
