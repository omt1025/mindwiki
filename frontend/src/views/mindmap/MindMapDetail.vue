<template>
  <v-app>
    <v-container>
      <!-- <mindmap :nodes="nodes" :connections="connections" :editable="true" /> -->
      <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :nudge-width="200"
        offset-x
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="purple" dark v-bind="attrs" v-on="on" fab small>
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </template>

        <v-card>
          <v-card-title>
            <span class="headline">마인드맵 생성</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="제목"
                    required
                    @keypress.enter="checkHandler"
                    type="text"
                  ></v-text-field>
                </v-col>

                <v-col cols="12">
                  <v-text-field
                    label="주제"
                    required
                    id="title"
                    ref="title"
                    v-model="title"
                    @keypress.enter="checkHandler"
                    type="text"
                  ></v-text-field>
                </v-col>

                <div>
                  <p class="interestTagTitle">해시태그 설정</p>
                </div>

                <v-combobox
                  append-icon
                  flat
                  v-model="chips"
                  chips
                  clearable
                  label="Your favorite hobbies"
                  multiple
                  no-filter
                  solo
                  id="combobox"
                >
                  <template
                    v-slot:selection="{ attrs, item, select, selected }"
                  >
                    <v-chip
                      v-bind="attrs"
                      :input-value="selected"
                      close
                      class="ma-2"
                      color="orange"
                      text-color="white"
                      @click="select"
                      @click:close="remove(item)"
                    >
                      <strong class="test">{{ item }}</strong
                      >&nbsp;
                    </v-chip>
                  </template>
                </v-combobox>

                <v-col cols="12">
                  <v-text-field label="설명" single-line></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>container 크기 줄여야함</small>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text @click="menu = false">
              Cancel
            </v-btn>

            <v-btn text>
              OK
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
    </v-container>
  </v-app>
</template>

<script>
// import axios from 'axios'

// import map from '../map.js';

export default {
  name: "MindMapDetail",
  components: {},
  data: () => ({
    fav: true,
    menu: false,
    chips: ["여행지", "음식", "SNOW"],
    interest: {},
    // ...map,
    title: "",
    url: "",
    category: "",
    color: "",
    fx: "",
    fy: "",
    newconnection: {
      source: "",
      target: "",
    },
  }),
  methods: {
    checkHandler() {
      console(this.title);
    },
  },
  // 관심태그 삭제
  remove(item) {
    this.chips.splice(this.chips.indexOf(item), 1);
    this.chips = [...this.chips];
  },
};
</script>

<style scoped>
#app {
  background-color: #f1f5f8;
}
.interestTagTitle {
  font-family: Poppins-Regular;
  font-size: 14px;
  color: #333333;
  line-height: 1.5;
  padding-left: 7px;
  width: 100%;
  text-align: left;
  margin-bottom: 7px;
}
</style>

<style>
.mindmap-svg {
  position: fixed;
  top: -50px;
  left: 40px;
}
.container {
  max-height: 500px;
}
</style>
