import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = 'http://localhost:8000/mindwiki';

export default new Vuex.Store({
  // 여러 컴포넌트에 공유되는 데이터
  state: {
    jwt: null,
    message: null, // action응답을 vue로 건내줄때 사용
    userId: null,
    nickName: null,
    bottomNav: 'home',
    mainTab: '0',
  },

  // 연산된 state값을 접근
  getters: {
    getJWT(state) {
      state.jwt = localStorage.getItem('jwt');
      // axios.defaults.headers.common['jwt'] = state.jwt;
      return state.jwt;
    },
    getMessage(state) {
      return state.message;
    },
    getUserId() {
      return localStorage.getItem('user-id');
    },
    getNickName() {
      return localStorage.getItem('user-nickname');
    },
    bottomNav(state) {
      return state.bottomNav;
    },
    mainTab(state) {
      return state.mainTab;
    },
  },

  plugins: [createPersistedState()],

  // state값을 변경하는 이벤트 로직/메서드
  mutations: {
    LOGIN(state, payload) {
      state.jwt = payload['jwt'];
      state.message = payload['message'];

      var decodedJWT = jwt_decode(state.jwt);
      state.userId = decodedJWT['email'];
      state.nickName = decodedJWT['nickName'];
    },
    LOGOUT(state) {
      state.jwt = null;
      state.userId = null;
      state.nickName = null;
    },
    setJWT(state, jwt) {
      var decodedJWT = jwt_decode(jwt);
      state.userId = decodedJWT['email'];
      state.nickName = decodedJWT['nickName'];

      localStorage.setItem('jwt', jwt);
      localStorage.setItem('user-id', state.userId);
      localStorage.setItem('user-nickname', state.nickName);
    },
    setMessage(state, value) {
      state.message = value;
    },
    setUserId(state, value) {
      state.userId = value;
    },
    setNickName(state, value) {
      state.nickName = value;
    },
    setBottomNav(state, nav) {
      state.bottomNav = nav;
    },
    setMainTab(state, tab) {
      state.mainTab = tab;
    },
    readMyMindMap(state, list) {
      state.mindlist = list;
    },
  },

  // 비동기처리 로직을 선언하는 메서드
  actions: {
    LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/login`, user).then((response) => {
        context.commit('LOGIN', response.data);
        let token = `${response.data['jwt']}`;

        var decodedJWT = jwt_decode(token);
        let userId = decodedJWT['email'];
        let nickName = decodedJWT['nickName'];
        // console.log(decodedJWT['nickName']);

        localStorage.setItem('jwt', token);
        localStorage.setItem('user-id', userId);
        localStorage.setItem('user-nickname', nickName);

        axios.defaults.headers.common['jwt'] = token;
        this.state.jwt = token;
        this.state.message = `${response.data['message']}`;
      });
    },
    LOGOUT(context) {
      axios.defaults.headers.common['jwt'] = undefined;
      this.state.jwt = null;
      localStorage.removeItem('jwt');
      localStorage.removeItem('user-id');
      localStorage.removeItem('user-nickname');
      context.commit('LOGOUT');
    },
    setJWT(context, jwt) {
      context.commit('setJWT', jwt);
    },
    setMessage(context, value) {
      context.commit('setMessage', value);
    },
    setBottomNav(context, nav) {
      context.commit('setBottomNav', nav);
    },
    setMainTab(context, tab) {
      context.commit('setMainTab', tab);
    },
    makeMindMap(context, mind) {
      return axios.post(`${SERVER_URL}/mind`, mind).then((response) => {
        context.commit('setMessage', response.data['message']);
      });
    },
    readMyMindMap(context, jwt) {
      return axios.post('mind/list', jwt).then((response) => {
        context.commit('setMessage', response.data['message']);
      });
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
