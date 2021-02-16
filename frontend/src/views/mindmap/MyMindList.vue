<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : SNS 로그인 JWT저장
    * 생성일자 : 2021-02-01
    * 최종수정일자 : 2021-02-09
  -->
  <v-card max-width="400" class="mx-auto">
    <!-- 나의 게시물 눌렀을 때 상단 네비게이션-->
    <v-toolbar id="navi_shadow">
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
      
      <v-spacer></v-spacer>
      <img src="@/assets/images/user/mindwiki_logo.png" height="23px" />
      <v-spacer></v-spacer>

    </v-toolbar>

    <!-- 생성한 게시물을 보여줌 -->
    <v-container>
      <v-row dense id="list">
        <CardForm 
          v-for="(card, index) in items" 
          :key="index"
          :card="card"
        />
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
import CardForm from '@/components/common/CardForm.vue'

export default {
  name: "MyMindList",
  data: () => ({ items: null }),
  components: {
    CardForm
  },
  computed: {
    ...mapGetters(['mindList']),
  },
  methods: {
    // 자신이 만들었던 마인드맵을 호출
    readmymindmap() {
      let form = new FormData();
      form.append("jwt", this.$store.getters.getJWT);

      this.$store.dispatch("readMyMindMap", form).then(() => {
        this.items = this.$store.getters.mindList;
        console.log(this.items)
      });
    },
    // 프로필을 통해 게시물로 접근한 경우, 뒤로갔을 때 프로필로 되돌아감
    backPage: function() {
      this.$router.push('/main')
    }
  },
  // 페이지가 로드되면 실행되도록
  created: function () {
    this.readmymindmap()
  }
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
