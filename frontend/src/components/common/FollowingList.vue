<template>
  <div id="app">
    <template v-for="(item, index) in items">
      <v-subheader
        v-if="item.header"
        :key="item.header"
        v-text="item.header"
      ></v-subheader>

      <v-divider
        v-else-if="item.divider"
        :key="index"
        :inset="item.inset"
      ></v-divider>

      <v-list-item
      v-else
      :key="item.title"
      >
        <v-list-item-avatar>
          <v-img
            :alt="`${item.title} avatar`"
            :src="item.avatar"
          ></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="item.title"></v-list-item-title>
        </v-list-item-content>

        <v-list-item-icon>
          <v-btn icon color="purple">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </v-list-item-icon>
      </v-list-item>
    </template>
  </div>
</template>

<script>
  export default {
    name: "FollowingList",

    data: () => ({
      items: [
        { header: '모든 팔로잉' },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Conan',
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Kiki',
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui',
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'Apeach',
        },
        { divider: true, inset: true },
        {
          title: 'pooh',
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
        }
      ],
    }),
  }
</script>

<style scoped>
.v-list-item__title {
  text-align: left;
}
.subtitle {
  text-align: left;
  margin-top: 6px;
}
.v-list-item__content {
  padding: 11px 0;
}
hr {
  margin-top: 0.4rem;
  margin-bottom: 0.4rem;
  margin-left: 1.1rem;
  margin-right: 1.1rem;
}
</style>