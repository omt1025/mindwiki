<template>
  <v-card
    flat
  >
    <v-toolbar>
      <v-spacer></v-spacer>
      <v-toolbar-title>검색아아아아앙</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-app-bar-nav-icon @click="menu"></v-app-bar-nav-icon>
    </v-toolbar>

    <v-card-text>
      <v-form>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                outlined
                clearable
                placeholder="검색"
                type="text"
                @keypress.enter="onInputKeyword"
                >
                <template v-slot:append>
                  <v-icon @click="onInputKeyword">mdi-magnify</v-icon>
                </template>
              </v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: 'SearchBar',
  methods: {
    onInputKeyword: function (event) {
      this.$emit('input-change', event.target.value)
      console.log('ok')
    },
    menu: function() {
      this.$router.push('/main/search')
    }
  },
}
</script>

<style>

</style>