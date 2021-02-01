<template>
  <div id="app">
    <v-app-bar id="navi_shadow" shift fixed color="white" light>
      <v-icon @click="backPage">mdi-keyboard-backspace</v-icon>
<<<<<<< HEAD
    </v-app-bar>
    <mindmap :nodes="nodes" :connections="connections" :editable="true" />
  
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-card-text style="height: 100px; position: relative">
            <v-fab-transition>
              <v-btn
                color="purple"
                dark
                absolute
                top
                right
                fab
                v-bind="attrs"
                v-on="on"
              >
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </v-fab-transition>
          </v-card-text>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">User Profile</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="Legal first name*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="Legal middle name"
                    hint="example of helper text only on focus"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="Legal last name*"
                    hint="example of persistent helper text"
                    persistent-hint
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Email*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Password*"
                    type="password"
                    required
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-select
                    :items="['0-17', '18-29', '30-54', '54+']"
                    label="Age*"
                    required
                  ></v-select>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-autocomplete
                    :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                    label="Interests"
                    multiple
                  ></v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
=======

      <v-spacer></v-spacer>
      <v-toolbar-title class="flex text-center">마인드맵 생성</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>

    <mindmap :nodes="nodes" :connections="connections" :editable="true" />
>>>>>>> 88d38f4b327992dc240831aaf827e8354cef5cbc
  </div>
</template>

<script>
import map from '../map.js';

export default {
  name: 'app',
  components: {},
<<<<<<< HEAD
  data: () => ({
    dialog: false,
    ...map,
    newnode: {
      text: '',
      url: '',
      category: '',
      color: '',
      fx: '',
      fy: '',
    },
    newconnection: {
      source: '',
      target: '',
    }
  }),
=======
  data() {
    return { ...map };
  },
>>>>>>> 88d38f4b327992dc240831aaf827e8354cef5cbc
};
</script>

<style scoped>
#app {
  background-color: #f1f5f8;
}
</style>
