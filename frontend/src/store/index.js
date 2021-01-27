import Vue from 'vue';
import Vuex from 'vuex';
// import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

// const state = {
//   isUser: false,
// };

export default new Vuex.Store({
  // 여러 컴포넌트에 공유되는 데이터
  state: {
    bottomNav: 'home',
    homeTab: '0',
  },
  // 연산된 state값을 접근
  getters: {
    bottomNav(state) {
      return state.bottomNav;
    },
    homeTab(state) {
      return state.homeTab;
    },
  },
  plugins: [createPersistedState()],
  // state값을 변경하는 이벤트 로직/메서드
  mutations: {
    setBottomNav(state, nav) {
      state.bottomNav = nav;
    },
    setHomeTab(state, tab) {
      state.homeTab = tab;
    },
  },
  // 비동기처리 로직을 선언하는 메서드
  actions: {
    setBottomNav(context, nav) {
      context.commit('setBottomNav', nav);
    },
    setHomeTab(context, tab) {
      context.commit('setHomeTab', tab);
    },
  },

  // getters: {
  //   getAccessToken(state) {
  //     state.accessToken = localStorage.getItem('access_token');
  //     axios.defaults.headers.common['auth-token'] = state.accessToken;
  //     return state.accessToken;
  //   },
  //   getUserId() {
  //     return localStorage.getItem('user-id');
  //   },
  //   getUserName() {
  //     return localStorage.getItem('user-name');
  //   },
  //   boards(state) {
  //     return state.boards;
  //   },
  //   board(state) {
  //     return state.board;
  //   },
  //   notices(state) {
  //     return state.notices;
  //   },
  //   notice(state) {
  //     return state.notice;
  //   },
  //   schools(state) {
  //     return state.schools;
  //   },
  //   estates(state) {
  //     return state.estates;
  //   },
  //   hospitals(state) {
  //     return state.hospitals;
  //   },
  // },
  // plugins: [createPersistedState()],
  // mutations: {
  //   LOGIN(state, payload) {
  //     state.accessToken = payload['auth-token'];
  //     state.userId = payload['user-id'];
  //     state.userName = payload['user-name'];
  //   },
  //   LOGOUT(state) {
  //     state.accessToken = null;
  //     state.userId = null;
  //     state.userName = null;
  //   },
  //   setBoards(state, boards) {
  //     state.boards = boards;
  //   },
  //   setBoard(state, board) {
  //     state.board = board;
  //   },
  //   setNotices(state, notices) {
  //     state.notices = notices;
  //   },
  //   setNotice(state, notice) {
  //     state.notice = notice;
  //   },
  //   setSchools(state, schools) {
  //     state.schools = schools;
  //   },
  //   setEstates(state, estates) {
  //     state.estates = estates;
  //   },
  //   setHospitals(state, hospitals) {
  //     state.hospitals = hospitals;
  //   },
  // },
  // actions: {
  //   LOGIN(context, user) {
  //     return axios.post(`${SERVER_URL}/user/confirm/login`, user).then((response) => {
  //       context.commit('LOGIN', response.data);
  //       let token = `${response.data['auth-token']}`;
  //       let id = `${response.data['user-id']}`;
  //       let name = `${response.data['user-name']}`;
  //       localStorage.setItem('access_token', token);
  //       localStorage.setItem('user-id', id);
  //       localStorage.setItem('user-name', name);
  //       axios.defaults.headers.common['auth-token'] = token;
  //       this.state.accessToken = token;
  //     });
  //   },
  //   LOGOUT(context) {
  //     axios.defaults.headers.common['auth-token'] = undefined;
  //     this.state.accessToken = null;
  //     localStorage.removeItem('access_token');
  //     localStorage.removeItem('user-id');
  //     localStorage.removeItem('user-name');
  //     context.commit('LOGOUT');
  //   },
  //   getBoards(context) {
  //     axios.get(`${SERVER_URL}/board/listboard`).then(({ data }) => {
  //       context.commit('setBoards', data);
  //     });
  //   },
  //   getBoard(context, url) {
  //     axios.get(url).then(({ data }) => {
  //       context.commit('setBoard', data);
  //     });
  //   },
  //   getNotices(context) {
  //     axios.get(`${SERVER_URL}/notice/listnotice`).then(({ data }) => {
  //       context.commit('setNotices', data);
  //     });
  //   },
  //   getNotice(context, url) {
  //     axios.get(url).then(({ data }) => {
  //       context.commit('setNotice', data);
  //     });
  //   },
  //   getSchools(context) {
  //     axios.get(`${SERVER_URL}/school/listschool`).then(({ data }) => {
  //       context.commit('setSchools', data);
  //     });
  //   },
  //   getEstates(context) {
  //     axios.get(`${SERVER_URL}/estate/listestate`).then(({ data }) => {
  //       context.commit('setEstates', data);
  //     });
  //   },
  //   getHospitals(context) {
  //     axios.get(`${SERVER_URL}/hospital/listhospital`).then(({ data }) => {
  //       context.commit('setHospitals', data);
  //     });
  //   },
  // },
  // modules: {},
});
