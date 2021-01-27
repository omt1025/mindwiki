<template>
  <v-list two-line>
    <template v-for="(item, index) in items">
      <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

      <v-divider v-else-if="item.divider" :key="index"></v-divider>

      <v-list-item v-else :key="item.title">
        <v-list-item-action></v-list-item-action>

        <v-list-item-icon>
          <v-icon v-html="item.icon"></v-icon>
        </v-list-item-icon>

        <v-list-item-action></v-list-item-action>

        <v-list-item-content>
          <v-list-item-title v-html="item.title"></v-list-item-title>
          <v-list-item-subtitle v-html="item.subtitle" class="subtitle"></v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </template>
  </v-list>
</template>

<script>
export default {
  name: 'ActivityMessage',

  data: () => ({
    items: [
      { header: '오늘 알림' },
      {
        icon: 'mdi-heart',
        title: 'giga님이 회원님의 게시물을 좋아합니다.',
        subtitle: `52분 전`,
      },
      {
        icon: 'mdi-message-text',
        title:
          'popo님이 댓글을 남겼습니다 <span class="grey--text text--lighten">: 이렇게 생각할수도 있겠네요</span>',
        subtitle: `10시간`,
      },

      { header: '이전 알림' },
      {
        icon: 'mdi-message-text',
        title:
          'zolryu님이 댓글을 남겼습니다 <span class="grey--text text--lighten">: 퍼가요^^</span>',
        subtitle: '2021-01-20',
      },
      {
        icon: 'mdi-heart',
        title: 'hei님이 회원님의 게시물을 좋아합니다',
        subtitle: '2021-01-15',
      },
      {
        icon: 'mdi-message-text',
        title:
          'mola님이 댓글을 남겼습니다 <span class="grey--text text--lighten">: 헉, 펜트하우스 넘나 존잼이죠!!</span>',
        subtitle: '2021-01-04',
      },
    ],
  }),
};
</script>

<style scoped>
.v-list-item__title {
  text-align: left;
}
.v-list--three-line .v-list-item,
.v-list-item--three-line {
  min-height: auto;
}
.v-application--is-ltr .v-list-item__action:first-child,
.v-application--is-ltr .v-list-item__icon:first-child {
  margin-right: 0px;
}
.subtitle {
  text-align: left;
  margin-top: 6px;
}
</style>
