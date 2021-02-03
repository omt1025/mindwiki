<template>
  <v-app>
    <v-text-field
      type="search"
      class="search"
      placeholder="type here..."
      v-model="search"
    ></v-text-field>

    <!-- <p>{{ search }}</p> -->
    <!-- <p>{{ searchHandler }}</p> -->

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
    // console.log(this.data);
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
