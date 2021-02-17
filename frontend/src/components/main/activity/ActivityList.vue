<template>
  <v-list two-line>
    <template v-for="(item, index) in items">
      <!-- <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader> -->
      <!-- <v-divider :key="index"></v-divider> -->

      <v-list-item :key="index">
        <v-list-item-action></v-list-item-action>

        <v-list-item-icon>
          <v-icon v-if="item.what === '좋아요'">mdi-heart</v-icon>
          <v-icon v-else>mdi-bookmark</v-icon>
        </v-list-item-icon>

        <v-list-item-action></v-list-item-action>

        <v-list-item-content>
          <v-list-item-title
            >'{{ item.nickName }}님이 {{ item.what }} 하셨습니다.</v-list-item-title
          >
          <!-- <v-list-item-subtitle class="subtitle"
            >{{ item.nickName }}님이 {{ item.what }} 하셨습니다.</v-list-item-subtitle
          > -->
          <v-list-item-subtitle class="subtitle">
            <p style="float: left">제목 : {{ item.title }}</p>
            <p style="float: right">{{ item.day }}</p>
          </v-list-item-subtitle>

          <!-- <v-list-item-subtitle class="subtitle2">{{ item.day }}</v-list-item-subtitle> -->
        </v-list-item-content>
      </v-list-item>
    </template>
  </v-list>
</template>

<script>
export default {
  name: 'ActivityMessage',

  data: () => ({
    items: null,
    members: null,
  }),
  created() {
    // 회원 목록 가져오기
    let form1 = new FormData();
    form1.append('jwt', this.$store.getters.getJWT);

    this.$store.dispatch('readMemberList', form1).then(() => {
      this.members = this.$store.getters.memberList;
    });

    // 내 게시물을 좋아요, 스크랩한 목록 가져오기
    let form = new FormData();
    form.append('jwt', this.$store.getters.getJWT);
    this.$store.dispatch('readActiveList', form).then(() => {
      this.items = this.$store.getters.activeList;

      this.getNickName();
    });
  },
  methods: {
    getNickName() {
      for (var i = 0; i < this.items.length; i++) {
        for (var j = 0; j < this.members.length; j++) {
          if (this.items[i].email === this.members[j].email) {
            if (this.members[j].nickName === null)
              this.$set(this.items[i], 'nickName', '(탈퇴회원)');
            else this.$set(this.items[i], 'nickName', this.members[j].nickName);

            if (this.items[i].time !== null)
              this.$set(this.items[i], 'day', this.items[i].time.split(' ')[0]);
          }
        }
      }
    },
  },
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
  display: block;
  margin-top: 6px;
}
</style>
