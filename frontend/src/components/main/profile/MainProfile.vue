<template>
  <div>
    <div class="follow" @click="goFollow">
      <img src="@/assets/images/user.png" height="50px" />
      팔로우
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goFollow() {
      this.$store.dispatch('setBottomNav', 'follow');
      this.$router.push('/main/mylibrary/follow');
    },
  },
};
</script>

<style>
.follow {
  margin-top: 20px;
}
</style>
