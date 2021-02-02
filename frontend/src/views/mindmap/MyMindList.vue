<template>
  <v-container fluid>
    <v-btn @click="readmymindmap">임시</v-btn>
    <v-row dense id="list">
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <v-img
            :src="card.src"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="200px"
          >
            <v-card-title v-text="card.title"></v-card-title>
          </v-img>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn icon>
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
  name: 'FavoriteMind',
  data: () => ({
    cards: ''
  }),
  methods: {
    readmymindmap () {
      const user = this.$store.getters.getJWT

      this.$store
        .dispatch('readMyMindMap', user)
        .then(() => {
          this.card = this.$store.getters.getMindList
          console.log(this.card)
        })
    }
  }
};
</script>

<style>
  #list {
    padding: 5px;
    margin-bottom: 50px;
  }
</style>
