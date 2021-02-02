<template>
  <v-app>
    <v-container>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="purple"
          dark
          v-bind="attrs"
          v-on="on"
          fab
          small
        >
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
                  id="title"
                  ref="title"
                  v-model="newmind.title"
                  @keypress.enter="checkHandler"
                  type="text"
                ></v-text-field>
              </v-col>

              <v-col cols="12">
                <v-text-field
                  label="주제"
                  required
                  id="subject"
                  ref="subject"
                  v-model="newmind.subject"
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
                    <strong class="test">{{ item }}</strong
                    >&nbsp;
                  </v-chip>
                </template>
              </v-combobox>

              <v-col cols="12">
                <v-text-field
                  label="설명"
                  single-line
                  id="explanation"
                  ref="explanation"
                  v-model="newmind.explanation"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            @click="menu = false"
          >
            Cancel
          </v-btn>

          <v-btn
            text
            @click="checkHandler"
          >
            OK
          </v-btn>

        </v-card-actions>
            <small>container 크기 줄여야함</small>
        </v-card-text>
        
        <v-divider></v-divider>

      </v-card>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: 'MakeMind',
  components: {},
  data: () => ({
    fav: true,
    menu: false,
    chips: ['여행지', '음식', 'SNOW'],
    interest: {},
    newmind: {
      title: '',
      subject: '',
      url: '',
      category: '',
      color: '',
      fx: '',
      fy: '',
      explanation: '',
    },
    newconnection: {
      source: '',
      target: '',
    },  
  }),
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.newmind.title &&
        ((msg = '제목을 입력해주세요.'), (err = false), this.$refs.title.focus());
      err &&
        !this.newmind.subject &&
        ((msg = '부모 노드를 입력해주세요.'), (err = false), this.$refs.subject.focus());
      err &&
        !this.newmind.explanation &&
        ((msg = '설명을 적어주세요.'), (err = false), this.$refs.explanation.focus());
      // if (!err) alert(msg);
      if (!err) this.showAlert(msg);
      else this.makemindmap();
    },
    // 마인드맵 생성 요청
    makemindmap() {
      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('title', this.newmind.title);
      form.append('subject', this.newmind.subject);
      form.append('explanation', this.newmind.explanation);
      form.append('hashtag', this.chips);
      // Actions를 호출
      this.$store
        .dispatch('makeMindMap', form)
        .then(() => {
          this.message = this.$store.getters.getMessage;

          if (this.message === 'SUCCESS') {
            this.$store.dispatch('setMessage', null);
            this.$router.push(`/main`);
            this.$router.go(this.$router.currentRoute);
          } else {
            this.showConfirm('로그인이 필요합니다.');
          }
        })
        .catch(({ message }) => (this.msg = message));

      // 탭 초기화(재사용 위해)
      this.message = '';
      this.$store.dispatch('setMainTab', 0);
      this.$store.dispatch('setBottomNav', 'home');
    },
    // 관심태그 삭제
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },

    // 다이얼로그
    showAlert(msg) {
      const options = { title: '알림', size: 'sm' };
      this.$dialogs.alert(msg, options).then((res) => {
        console.log(res); // {ok: true|false|undefined}
      });
    },
    
    showConfirm(msg) {
      const options = {title: '알림', cancelLabel: 'No'}
      this.$dialogs.confirm(msg, options)
      .then(res => {
        console.log(res) // {ok: true|false|undefined}
      })
    },
  }
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
  max-height: 200px;
}
</style>