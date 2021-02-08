<template>
  <v-container fluid>
    <v-row dense id="list">
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <v-img
            :src="card.thumbnail"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="200px"
            @click="clickParams(card.mindID)"
          >
            <v-card-title v-text="card.title"></v-card-title>
          </v-img>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn icon color="purple">
              <v-icon>mdi-heart</v-icon>
            </v-btn>

            <v-btn icon>
              <v-icon>mdi-bookmark</v-icon>
            </v-btn>

            <v-btn icon>
              <v-icon>mdi-share-variant</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'MyWiki',
  data: () => ({
    cardsData:[],
    cards: [],
  }),
  methods: {
    // 좋아요 한 마인드맵 출력
    readlikemindmap () {
      this.$store.dispatch("readMindMap", this.$store.getters.getJWT).then(() => {
        this.cardsData = this.$store.getters.getMessage;
      })
      this.$store.dispatch("readLikeMindMap", this.$store.getters.getJWT).then(() => {
        const likeMinds = this.$store.getters.getMessage
        for (var i=0; i<likeMinds.length; i++) {
          for (var j=0; j<this.cardsData.length; j++) {
            if (likeMinds[i]["likedMindID"] == this.cardsData[j]["mindID"]) {
              this.cards.push(this.cardsData[j])
            }
          }
        }
      })
    },
    clickParams(no) {
      this.$router.push({name: 'MindMapDetail', params: {no: Number(no)}});
    }
  },
  mounted: function () {
    this.readlikemindmap ()
  }
};
</script>

<style>
  #list {
    padding: 5px;
    margin-bottom: 50px;
  }
</style>
