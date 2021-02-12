<template>
  <!-- 
    * 작성자 : 서울2반 4팀 황윤호
    * 내용 : 검색기능 구현
    * 생성일자 : 2021-01-27
    * 최종수정일자 : 2021-02-10
  -->
  <v-app>
    <!-- 검색 도구 -->
    <v-text-field
      type="search"
      class="search"
      placeholder="검색"
      outlined
      v-model="search"
      @keypress.enter="onInputKeyword"
    >
      <template v-slot:append>
        <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
      </template>
    </v-text-field>

    <!-- 프로필 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">프로필 검색</div>
    </v-list-item-content>

    <!-- 프로필 슬라이드 -->
    <template>
      <v-sheet class="mx-auto" max-width="375">
        <v-slide-group
          multiple
        >
          <v-slide-item
            v-for="item in searchHandler"
            :key="item.id"
          >
            <v-list-item id="v-list-item">
            <v-list-item-avatar color="purple">
              <v-img
                :alt="`${item.name} avatar`"
                :src="item.avatar"
              ></v-img>
            </v-list-item-avatar>

            <v-list-item-subtitle id="subtitle" v-text="item.name"></v-list-item-subtitle>
            
            </v-list-item>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </template>

    <v-divider></v-divider>
    <!-- 마인드맵 제목 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">제목 검색</div>
    </v-list-item-content>

    <v-divider></v-divider>

    <!-- 마인드맵 해시태그 검색 -->
    <v-list-item-content>
      <div class="caption purple--text">해시태그 검색</div>
    </v-list-item-content>

  </v-app>
</template>

<script>
import { persons } from '@/data/data';

export default {
  components: {

  },
  computed: {
    searchHandler() {
      return this.data.filter(elem => {
        return elem.name.toLowerCase().includes(this.search.toLowerCase());
      });
    }
  },
  data() {
    return {
      search: '',
      data: []
    };
  },
  created() {
    this.data = persons;
    console.log(this.$store.getters.nickName)
  },
  methods: {
    onInputKeyword: function(event) {
      this.$emit('input-change', event.target.value);
    }
  },
};
</script>

<style scoped>
/* 검색 css */
.search {
  height: 40px;
}
/* 검색 돋보기 위치 */
.mdi:before, .mdi-set {
  position: fixed;
}
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
#v-list-item {
  height: 80px;
}
#subtitle {
  position: fixed;
  bottom: 0px;
}
</style>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Sunflower:wght@300&display=swap" rel="stylesheet');

#subtitle {
  font-family: 'Noto Sans KR';
}
</style>