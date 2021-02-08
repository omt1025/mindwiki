/*
 * 작성자 : 서울2반 4팀 윤지선
 * 내용 : 회원가입 메소드 추가, 로그인 메소드 수정
 * 생성일자 : 2021-01-20
 * 최종수정일자 : 2021-02-08
 */

import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios'; // 서버 통신
import jwt_decode from 'jwt-decode'; // jwt 디코드
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = 'http://localhost:8000/mindwiki';

export default new Vuex.Store({
  // 여러 컴포넌트에 공유되는 데이터
  state: {
    jwt: null, // 로그인 인증 토큰
    message: null, // action응답을 vue로 건내줄때 사용
    userId: null, // JWT 속 email
    nickName: null, // JWT 속 nickname
    bottomNav: 'home', // 하단 바 현재 위치
    mainTab: '0', // 상단 탭 현재 위치
    mindList: [], // 프로필 내 마인드리스트목록
  },

  // 연산된 state값을 접근
  getters: {
    // 로그인 인증 토큰 리턴
    getJWT(state) {
      state.jwt = localStorage.getItem('jwt');
      return state.jwt;
    },
    // action에서 서버와 통신하고 받은 message 리턴
    getMessage(state) {
      return state.message;
    },
    // 접속중인 email 리턴
    userId() {
      return localStorage.getItem('user-id');
    },
    // 접속중인 nickname 리턴
    nickName() {
      return localStorage.getItem('user-nickname');
    },
    // 선택되어있는 하단 바 리턴
    bottomNav(state) {
      return state.bottomNav;
    },
    // 선택되어있는 상단 탭 리턴
    mainTab(state) {
      return state.mainTab;
    },
    // 프로필 : 내 마인드리스트 리턴
    mindList(state) {
      return state.mindList;
    },
  },

  plugins: [createPersistedState()],

  // state값을 변경하는 이벤트 로직/메서드
  mutations: {
    // 로그인 결과를 state에 반영[YJS]
    LOGIN(state, payload) {
      state.jwt = payload['jwt'];
      state.message = payload['message'];

      var decodedJWT = jwt_decode(state.jwt);
      state.userId = decodedJWT['email'];
      state.nickName = decodedJWT['nickName'];
    },
    // 로그아웃 결과를 state에 반영[YJS]
    LOGOUT(state) {
      state.jwt = null;
      state.userId = null;
      state.nickName = null;
    },
    // SNS로그인 후 받은 jwt로 state, localStorage에 반영[YJS]
    setJWT(state, jwt) {
      var decodedJWT = jwt_decode(jwt); // JWT 디코딩

      // store 변수 갱신
      state.jwt = jwt;
      state.userId = decodedJWT['email'];
      state.nickName = decodedJWT['nickName'];

      // localStorage 변수 갱신
      localStorage.setItem('jwt', jwt);
      localStorage.setItem('user-id', state.userId);
      localStorage.setItem('user-nickname', state.nickName);
    },
    // message값을 value값으로 변경[YJS]
    setMessage(state, value) {
      state.message = value;
    },
    // userId를 value값으로 변경[YJS]
    setUserId(state, value) {
      state.userId = value;
    },
    // nickName을 value값으로 변경[YJS]
    setNickName(state, value) {
      state.nickName = value;
    },
    // 선택된 하단 바 변경[YJS]
    setBottomNav(state, nav) {
      state.bottomNav = nav;
    },
    // 상단 탭 변경[YJS]
    setMainTab(state, tab) {
      state.mainTab = tab;
    },
    // 프로필 마인드리스트[YJS]
    setMindList(state, mindList) {
      state.mindList = mindList;
    },
  },

  // 비동기처리 로직을 선언하는 메서드
  // vue에서 actions에 있는 메소드 호출 -> 서버와 통신 -> mutations메소드 호출하여 state값 변경
  actions: {
    // 로그인 서버와 통신[YJS]
    LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/login`, user).then((response) => {
        // 성공시에만 mutation의 LOGIN으로 이동
        if (response.data['message'] === 'SUCCESS') {
          context.commit('LOGIN', response.data); // 응답을 mutations으로 전달
          let token = `${response.data['jwt']}`;
          // jwt 디코딩
          var decodedJWT = jwt_decode(token);
          let userId = decodedJWT['email'];
          let nickName = decodedJWT['nickName'];

          // localStorage에 저장
          localStorage.setItem('jwt', token);
          localStorage.setItem('user-id', userId);
          localStorage.setItem('user-nickname', nickName);
        } else {
          context.commit('setMessage', response.data['message']);
        }
      });
    },
    // 로그아웃[YJS]
    LOGOUT(context) {
      axios.defaults.headers.common['jwt'] = undefined;

      // localStorage에 저장된 값 지우기
      localStorage.removeItem('jwt');
      localStorage.removeItem('user-id');
      localStorage.removeItem('user-nickname');

      // muatation에 있는 state값 날리기
      context.commit('LOGOUT');
    },
    // 회원가입[YJS]
    signUp(context, user) {
      return axios.post(`${SERVER_URL}/profile/register`, user).then((response) => {
        context.commit('setMessage', response.data['message']); // 응답을 message에 저장
      });
    },
    // vue에서 sns로그인 하고 받은 jwt를 mutation에서 state값 변경[YJS]
    setJWT(context, jwt) {
      context.commit('setJWT', jwt);
    },
    // vue에서 message 사용 후, 재사용을 위해 초기화 시킬 때 사용[YJS]
    setMessage(context, value) {
      context.commit('setMessage', value);
    },
    // 선택된 하단 바 변경[YJS]
    setBottomNav(context, nav) {
      context.commit('setBottomNav', nav);
    },
    // 상단 탭 변경[YJS]
    setMainTab(context, tab) {
      context.commit('setMainTab', tab);
    },
    // 마인드맵 생성[OMT]
    makeMindMap(context, mind) {
      return axios
        .post(`${SERVER_URL}/mind`, mind, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((response) => {
          context.commit('setMessage', response.data['message']); // 응답을 message에 저장
        });
    },
    // 마인드맵 리스트 불러오기[OMT]
    readMyMindMap(context, jwt) {
      return axios.post(`${SERVER_URL}/mind/list`, jwt).then((response) => {
        context.commit('setMessage', response.data); // 응답을 message에 저장

        // 메소드는 겹치는데, 혹시 몰라서, 따로 만들었음.. 후에 조정 필요
        context.commit('setMindList', response.data); // my_mindList불러오기위해[YJS]
      });
    },
    // 상세 마인드맵 페이지에서 마인드맵 불러오기[OMT]
    readMindDetail(context, user) {
      const jwt = user.get('jwt');

      return axios
        .get(`${SERVER_URL}/mind/read/${user.get('no')}`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setMessage', response.data);
        });
    },
    // 마인드맵 수정[OMT]
    updateMind(context, mind) {
      console.log(mind.get('jwt'));
      console.log(mind.get('MindID'));
      let form = new FormData();
      form = mind;
      return axios.put(`/mindwiki/mind/update`, form).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
    // 마인드맵 제거[OMT]
    deleteMind(context, user) {
      const jwt = user.get('jwt');

      return axios
        .delete(`${SERVER_URL}/mind/delete/${user.get('no')}`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setMessage', response.data);
        });
    },
  },
});
