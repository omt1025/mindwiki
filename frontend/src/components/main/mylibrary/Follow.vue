<template>
  <div>
    <FollowNavi />
  </div>
</template>

<script>
import FollowNavi from '@/components/navi/FollowNavi.vue';

export default {
  name: 'Followers',
  components: {
    FollowNavi,
  },
  data() {
    return {
      title: '팔로우목록',
    };
  },
  methods: {},
};
</script>

<style></style>
