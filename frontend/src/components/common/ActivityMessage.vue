<template>
  <v-list three-line>
    <template v-for="(item, index) in items">
      <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

      <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

      <v-list-item v-else :key="item.title">
        <v-list-item-avatar>
          <v-img :src="item.avatar"></v-img>
        </v-list-item-avatar>

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
      // { header: 'Today' },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
        title: 'Conan  &nbsp;<span class="grey--text text--lighten-1">오전 4:14</span>',
        subtitle: `내 이름은 코난 함정이죠. 내 이름은 짱구 못말리죠. 내 이름은 스폰지밥 네모나죠.`,
      },
      { divider: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
        title: 'Kiki &nbsp;<span class="grey--text text--lighten-1">오전 1:40</span>',
        subtitle: `마트 다녀오셨어요? 아니 영기엄마가 텃밭에서 고구마 호박을.. 호박 고구마예요. 그래! 호구! 호박 고구마!!`,
      },
      { divider: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
        title: 'Oui &nbsp;<span class="grey--text text--lighten-1">어제</span>',
        subtitle: '넘 졸린데, 한 게 없어서 못 자겠네ㅋㅋ낄낄.. 멍청한 자식..ㅋ',
      },
      { divider: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
        title: 'Apeach &nbsp;<span class="grey--text text--lighten-1">1월 25일</span>',
        subtitle:
          '나 오늘 저녁에 떡볶이 먹다가 간장 떡볶이/짜장 떡볶이/매운 떡볶이 이걸로 레시피 마인드맵 만들어볼까..',
      },
      { divider: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
        title: 'pooh &nbsp;<span class="grey--text text--lighten-1">1월 21일</span>',
        subtitle: '주짓수 마인드 너무 재밌게봤어요. 혹시 제가 퍼가도 될까요?',
      },
      { divider: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
        title: 'giga &nbsp;<span class="grey--text text--lighten-1">1월 17일</span>',
        subtitle: '과제는 얼마나 함? 하....ㅋㅋㅋ 작심삼일.. 언제 다 하냐ㅜㅜ ㄸㄹㄹ',
      },
    ],
  }),
};
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
