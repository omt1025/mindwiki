<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 상세 마인드맵 노드 댓글 UI
    * 생성일자 : 2021-02-09
    * 최종수정일자 : 2021-02-09
  -->
  <v-app>
    <div>
    <v-toolbar id="navi_shadow">
      <!-- 뒤로가기 누르면 해당하는 상세 마인드맵 페이지로 이동 -->
      <v-icon>mdi-keyboard-backspace</v-icon>
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" alt="" height="23px" />
      <v-spacer></v-spacer>
    </v-toolbar>

    <!-- 댓글 목록 -->
    <v-card
      flat
      height="100%"
    >
      <v-list>
        <template v-for="(item, index) in items">
          <v-subheader
            class="d-flex justify-center"
            v-if="item.header"
            :key="item.header"
          >
            {{ item.header }}
          </v-subheader>
          <v-divider
            v-else-if="item.divider"
            :key="index"
            :inset="item.inset"
          ></v-divider>
          <v-list-item
            v-else
            :key="item.data"
          >
            <v-list-item-avatar>
              <img :src="item.avatar" alt="">
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-html="item.data"></v-list-item-title>
              <v-list-item-subtitle v-html="item.email"></v-list-item-subtitle>
              <v-btn @click="deletecomment(item.commentID)">delete 임시</v-btn>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-card>
    </div>

    <!-- 댓글 입력창 -->
    <v-footer
      id="footer"
      elevation="3"
    >
      <!-- textarea : 엔터 누르면 줄바꿈 가능 / no-resize : 줄바꿔도 textarea 늘어나지 않음-->
      <v-textarea
        append-outer-icon="mdi-comment"
        no-resize
        label="댓글 입력"
        rows="1"
        color="purple"
        id="write"
        ref="write"
        v-model="write"
      ></v-textarea>
      <v-btn @click="createcomment">임시</v-btn>
    </v-footer>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'MindComment',
  computed: {
    ...mapGetters(['commentData']),
  },
  data() {
    const no = Number(this.$route.params.no);
    // 댓글 임시 데이터
    return {
      no: no,
      write: '',
      items: [
        // { header: '댓글' },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', title: 'Brunch this weekend?', subtitle: `<span class="font-weight-bold">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>', subtitle: `<span class="font-weight-bold">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', title: 'Oui oui', subtitle: '<span class="font-weight-bold">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?' },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', title: 'Brunch this weekend?1', subtitle: `<span class="font-weight-bold">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>', subtitle: `<span class="font-weight-bold">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', title: 'Oui oui1', subtitle: '<span class="font-weight-bold">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?' },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', title: 'Brunch this weekend?2', subtitle: `<span class="font-weight-bold">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', title: 'Summer BBQ2 <span class="grey--text text--lighten-1">4</span>', subtitle: `<span class="font-weight-bold">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', title: 'Oui oui2', subtitle: '<span class="font-weight-bold">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?' },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', title: 'Summer BBQ1 <span class="grey--text text--lighten-1">4</span>', subtitle: `<span class="font-weight-bold">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.` },
        // { divider: true, inset: true },
        // { avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', title: 'hossi', subtitle: '<span class="font-weight-bold">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?' },
        // { divider: true, inset: true },
      ],
    }
  },
  methods: {
    createcomment() {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT)
      form.append('no', this.no);
      form.append('data', this.write);

      this.$store.dispatch('createComment', form).then(() => {
        this.items = this.$store.getters.commentData
      })
    },
    readcomment() {
      this.$store.dispatch('readComment', this.no).then(() => {
        this.items = this.$store.getters.commentData
        console.log(this.items)
      })
    },
    // updatecomment(no) {
    //   this.$store.dispatch('updateComment', no).then(() => {
    //     this.items = this.$store.getters.commentData
    //     console.log(this.items)
    //   })
    // },
    deletecomment(commentID) {
      const form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('no', this.no)
      form.append('commentID', commentID);
      this.$store.dispatch('deleteComment', form).then(() => {
        this.items = this.$store.getters.commentData
        console.log(this.items)
      })
    },
  },
  created: function () {
    this.readcomment()
  }
}
</script>

<style>
/* 상단 네비 css */
#navi_shadow {
  box-shadow: 0px 2px 4px -1px rgb(0 0 0 / 3%), 0px 4px 5px 0px rgb(0 0 0 / 3%),
    0px 1px 10px 0px rgb(0 0 0 / 3%);
  position: sticky;
  top: 0;
  z-index: 999;
}
/* 댓글 입력창 css */
#footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  background: #fff;
  z-index: 999;
}
</style>