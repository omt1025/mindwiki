<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 리스트 보여지는 영역 수정
    * 생성일자 : 2021-01-18
    * 최종수정일자 : 2021-02-10
  -->
  <v-app>
    <v-combobox
      append-icon
      flat
      v-model="hashtag"
      hashtag
      clearable
      label="Your favorite hobbies"
      multiple
      no-filter
      solo
      id="combobox"
      @change="updatehashtag()"
    >
      <template
        v-slot:selection="{ attrs, item, select, selected }"
      >
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
          <div v-if='item !== ""'>
            <strong class="test">{{ item }}</strong
            >&nbsp;
          </div>
        </v-chip>
      </template>
    </v-combobox>
    <v-row dense id="list">
      <!-- CardForm 컴포넌트로 데이터를 보냄 -->
      <CardForm 
        v-for="(card, index) in items" 
        :key="index"
        :card="card"
      />
    </v-row>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';
import CardForm from '../../common/CardForm.vue'

export default {
  name: 'FavoriteMind',
  data: () => ({ 
    items: null,
    hashtag: [], 
  }),
  components: {
    CardForm
  },
  computed: {
    ...mapGetters(['cards']),
  },
  methods:{
    readliketagmind () {
      this.$store.dispatch('readLikeTagMindMap', this.$store.getters.getJWT).then(() => {
      this.items = this.$store.state.liketagData
      });
    },
    readhashtag () {
      this.$store.dispatch('readHashTag', this.$store.getters.getJWT).then(() => {
      const temphashtag = this.$store.state.hashtag.hashtag
      if (temphashtag) this.hashtag = temphashtag.split('#')
      this.hashtag.shift()
      });
    },
    updatehashtag () {
      var hashtagform = '';
      for (var i = 0; i < this.hashtag.length; i++) hashtagform += '#' + this.hashtag[i];
      console.log('변했다!!')
      let form = new FormData();
      form.append("jwt", this.$store.getters.getJWT);
      form.append("hashtag", hashtagform)

      this.$store.dispatch('updateHashTag', form).then(() => {
      });
    },
    // 관심태그 삭제
    remove(item) {
      this.hashtag.splice(this.hashtag.indexOf(item), 1);
      this.hashtag = [...this.hashtag];
    },
  },
  // 전체 게시글을 가져옴
  created() {
    this.readliketagmind ();
    this.readhashtag ();
  },
};
</script>

<style>
  #list {
    padding: 5px;
    margin-bottom: 50px;
  }
</style>
