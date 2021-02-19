<template>
  <!-- 
    * 작성자 : 서울2반 4팀 오민택
    * 내용 : 관심태그랑 마인드맵 리스트 출력
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-15
  -->
  <v-app>
    <!-- 관심 태그 -->
    <v-combobox
      append-icon
      flat
      v-model="hashtag"
      hashtag
      clearable
      label="관심태그를 입력해주세요."
      multiple
      hide-details="true"
      no-filter
      solo
      id="combobox"
      @change="updatehashtag()"
    >
      <template v-slot:selection="{ attrs, item, select, selected }">
        <v-chip
          v-bind="attrs"
          :input-value="selected"
          close
          class="ma-2"
          color="purple"
          text-color="white"
          @click="select"
          @click:close="remove(item)"
        >
          <div v-if="item !== ''">
            <strong class="test">{{ item }}</strong
            >&nbsp;
          </div>
        </v-chip>
      </template>
    </v-combobox>
    <v-row dense id="list">
      <!-- CardForm 컴포넌트로 데이터를 보냄 -->
      <CardForm v-for="(card, index) in items" :key="index" :card="card" />
    </v-row>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';
import CardForm from '../../common/CardForm.vue';

export default {
  name: 'FavoriteMind',
  data: () => ({
    items: null,
    hashtag: [],
  }),
  components: {
    CardForm,
  },
  computed: {
    ...mapGetters(['cards', 'memberList']),
  },
  methods: {
    // 작성자 프로필 사진 가져오기
    getImage() {
      for (var i = 0; i < this.items.length; i++) {
        for (var j = 0; j < this.memberList.length; j++) {
          if (this.items[i].admin === this.memberList[j].email) {
            this.$set(this.items[i], 'profileDefaultPic', this.memberList[j].profileDefaultPic);
            this.$set(this.items[i], 'nickName', this.memberList[j].nickName);
            break;
          }
        }
      }
    },
    // 관심태그와 같은 마인드맵을 받아옴
    readliketagmind() {
      this.$store.dispatch('readLikeTagMindMap', this.$store.getters.getJWT).then(() => {
        this.items = this.$store.state.liketagData;
        this.getImage();
      });
    },
    // 로그인된 유저가 설정한 관심태그를 받아옴
    readhashtag() {
      this.$store.dispatch('readHashTag', this.$store.getters.getJWT).then(() => {
        const temphashtag = this.$store.state.hashtag.hashtag;
        if (temphashtag) this.hashtag = temphashtag.split('#');
        this.hashtag.shift();
      });
    },
    // 관심태그 수정
    updatehashtag() {
      var hashtagform = '';
      for (var i = 0; i < this.hashtag.length; i++) hashtagform += '#' + this.hashtag[i];
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('hashtag', hashtagform);

      this.$store.dispatch('updateHashTag', form).then(() => {
        this.readliketagmind();
        this.getImage();
        this.readliketagmind();
      });
    },
    // 관심태그 삭제
    remove(item) {
      this.hashtag.splice(this.hashtag.indexOf(item), 1);
      this.hashtag = [...this.hashtag];
      var hashtagform = '';
      for (var i = 0; i < this.hashtag.length; i++) hashtagform += '#' + this.hashtag[i];
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('hashtag', hashtagform);

      this.$store.dispatch('updateHashTag', form).then(() => {
        this.readliketagmind();
        this.getImage();
        this.readliketagmind();
      });
    },
  },
  // 전체 게시글을 가져옴
  created() {
    this.readliketagmind();
    this.readhashtag();
  },
};
</script>

<style>
#list {
  padding: 5px;
  margin-bottom: 50px;
}
</style>
