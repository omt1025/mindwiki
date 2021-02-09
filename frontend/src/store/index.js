/*
(작성자는 최종 수정하는 사람 이름으로 갱신하면될것같아요)
 * 작성자 : 서울2반 4팀 윤지선
 * 내용 : 계정 존재 유무 확인 action메소드 생성
 * 생성일자 : 2021-01-20
 * 최종수정일자 : 2021-02-05
 */

import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios'; // 서버 통신
import jwt_decode from 'jwt-decode'; // jwt 디코드
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = 'http://localhost:8000/mindwiki';
// const SERVER_URL = 'http://i4a204.p.ssafy.io:8000/mindwiki';

export default new Vuex.Store({
  // 여러 컴포넌트에 공유되는 데이터
  state: {
    jwt: null, // 로그인 인증 토큰
    message: null, // action응답을 vue로 건내줄때 사용
    userId: null, // JWT 속 email
    nickName: null, // JWT 속 nickname
    bottomNav: 'home', // 하단 바 현재 위치
    mainTab: '0', // 상단 탭 현재 위치
    mindList: null, // 프로필 내 마인드리스트목록
    followTab: '0', // 상단 탭(팔로워 / 팔로잉) 현재 위치
    cards: null, // 메인 페이지의 마인드맵 리스트
    likeData: null, // 좋아요 누른 마인드맵
    scrapData: null, // 스크랩한 마인드맵
    commentData: null, // 댓글 리스트
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
    // 선택되어있는 상단 탭(팔로우 / 팔로워 리턴)
    followTab(state) {
      return state.followTab;
    },
    cards(state) {
      return state.cards;
    },
    likeData(state) {
      return state.likeData;
    },
    scrapData(state) {
      return state.scrapData;
    },
    commentData(state) {
      return state.commentData;
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
    // 상단 탭(팔로워/팔로잉)변경
    setFollowTab(state, tab) {
      state.followTab = tab;
    },
    setCards(state, val) {
      state.cards = val;
    },
    setLikeData(state, val) {
      state.likeData = val;
    },
    setScrapData(state, val) {
      state.scrapData = val;
    },
    setCommentData(state, val) {
      state.commentData = val;
    },
  },

  // 비동기처리 로직을 선언하는 메서드
  // vue에서 actions에 있는 메소드 호출 -> 서버와 통신 -> mutations메소드 호출하여 state값 변경
  actions: {
    // 로그인 서버와 통신[YJS]
    LOGIN(context, user) {
      return axios.post(`${SERVER_URL}/login`, user).then((response) => {
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

        // axios.defaults.headers.common['jwt'] = token;
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
    // jwt 속 이메일에 해당하는 계정이 있는지 조회
    isExist(context, jwt) {
      return axios.post(`${SERVER_URL}/profile/isExist`, jwt).then((response) => {
        context.commit('setMessage', response.data['message']); // 응답을 message에 저장
      });
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
    // 상단 탭(팔로워/팔로잉)변경
    setFollowTab(context, tab) {
      context.commit('setFollowTab', tab);
    },
    // 마인드맵 생성[OMT]
    makeMindMap(context, mind) {
      return axios
        .post(`${SERVER_URL}/mind`, mind, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((response) => {
          context.commit('setMessage', response.data['message']); // 응답을 message에 저장
        });
    },
    // 전체 마인드맵 리스트 불러오기[OMT]
    readMindMap(context, jwt) {
      return axios
        .get(`${SERVER_URL}/mind/read/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setCards', response.data);
        });
    },
    // 좋아요 누른 마인드맵 리스트 불러오기[OMT]
    readLikeMindMap(context, jwt) {
      return axios
        .get(`${SERVER_URL}/mind/like/read/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setLikeData', response.data);
        });
    },
    // 스크랩 누른 마인드맵 리스트 불러오기[OMT]
    readScrapMindMap(context, jwt) {
      return axios
        .get(`${SERVER_URL}/mind/scrap/read/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setScrapData', response.data);
        });
    },
    // 내 마인드맵 리스트 불러오기[OMT]
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
      return axios.put(`/mindwiki/mind/update`, mind).then((response) => {
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
    // 마인드맵 좋아요[OMT]
    likeMind(context, user) {
      const form = new FormData();
      form.append('jwt', user.get('jwt'));
      form.append('disLike', user.get('disLike'));
      return axios.post(`${SERVER_URL}/mind/like/${user.get('no')}`, form).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
    // 마인드맵 스크랩[OMT]
    scrapMind(context, user) {
      const form = new FormData();
      form.append('jwt', user.get('jwt'));
      form.append('disScrap', user.get('disScrap'));
      return axios.post(`${SERVER_URL}/mind/scrap/${user.get('no')}`, user).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
    // 마인드맵 댓글 생성[OMT]
    createComment(context, user) {
      console.log(user.get('no'));
      const form = new FormData();
      form.append('jwt', user.get('jwt'));
      form.append('data', user.get('data'));
      return axios.post(`/mindwiki/mind/comment/make/${user.get('no')}`, form).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
    // 마인드맵 댓글 불러오기[OMT]
    readComment(context, no) {
      return axios
        .get(`${SERVER_URL}/mind/comment/read/${no}`, {
          params: { no: no },
        })
        .then((response) => {
          context.commit('setCommentData', response.data);
        });
    },
    // 마인드맵 댓글 수정[OMT]
    // updateComment(context, user) {
    //   const form = new FormData();
    //   form.append('jwt', user.get('jwt'));
    //   form.append('data', user.get('data'));
    //   return axios
    //     .put(`${SERVER_URL}/mind/comment/update/${user.get('no')}`, form)
    //     .then((response) => {
    //       context.commit('setMessage', response.data);
    //     });
    // },
    // 마인드맵 댓글 삭제[OMT]
    deleteComment(context, user) {
      return axios
        .delete(`/mindwiki/mind/comment/delete/${user.get('no')}`, {
          params: {
            jwt: user.get('jwt'),
            commentID: user.get('commentID'),
          },
        })
        .then((response) => {
          context.commit('setMessage', response.data);
        });
    },
  },
});
