<template>
  <v-app>
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

    <!-- 프로필 검색  -->
    <v-list-item-content>
      <div class="questrial caption font-weight-bold blue--text">프로필 검색</div>
    </v-list-item-content>


    <template v-for="item in searchHandler">
      <v-list-item
        :key="item.id"
      >
        <v-list-item-avatar>
          <v-img
            :alt="`${item.name} avatar`"
            :src="item.avatar"
          ></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-text="item.name"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </template>

    <v-divider></v-divider>
    <!-- 마인드맵 제목 검색 -->
    <v-list-item-content>
      <div class="questrial caption font-weight-bold blue--text">제목 검색</div>
    </v-list-item-content>

    <v-divider></v-divider>

    <!-- 마인드맵 해시태그 검색 -->
    <v-list-item-content>
      <div class="questrial caption font-weight-bold blue--text">해시태그 검색</div>
    </v-list-item-content>

  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';
import { persons } from '@/data/data';



export default {
  components: {

  },
  computed: {
    ...mapGetters(['mainTab']),
    searchHandler() {
      return this.data.filter(elem => {
        return elem.name.toLowerCase().includes(this.search.toLowerCase());
        // return elem.name

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
  },
  methods: {
    onInputKeyword: function(event) {
      this.$emit('input-change', event.target.value);
    }
  },


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
