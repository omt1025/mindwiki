/*
 * 작성자 : 서울2반 4팀 오민택
 * 내용 : 캡처 썸네일 이미지 업로드 action추가
 * 생성일자 : 2021-01-20
 * 최종수정일자 : 2021-02-18
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
    liketagData: null, // 관심 태그 리스트
    hashtag: null, // 관심 태그
    likeData: null, // 좋아요 누른 마인드맵
    scrapData: null, // 스크랩한 마인드맵
    commentData: null, // 댓글 리스트
    memberList: null, // 회원가입 한 전체 회원
    mapData: null, // 마인드맵 데이터
    followingData: null, // 팔로잉 목록
    followingMindData: null, // 팔로워의 마인드 목록
    followerData: null, // 나를 팔로우한 사람 목록
    profile: null, // 회원정보
    profileImage: null, // 프로필 사진
    followerProfileImage: null, // 팔로워 이미지
    followingProfileImage: null, // 팔로잉 이미지
    activeList: null, // 내 활동 알림
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
    // 전체 마인드맵 리스트 리턴
    cards(state) {
      return state.cards;
    },
    // 관심태그 마인드맵 리스트 리턴
    liketagData(state) {
      return state.liketagData;
    },
    // 해시태그 리턴
    hashtag(state) {
      return state.hashtag;
    },
    // 좋아요한 마인드맵 리스트 리턴
    likeData(state) {
      return state.likeData;
    },
    // 스크랩한 마인드맵 리스트 리턴
    scrapData(state) {
      return state.scrapData;
    },
    // 댓글 리스트 리턴
    commentData(state) {
      return state.commentData;
    },
    // 마인드맵 데이터 리턴
    getMapData(state) {
      return state.mapData;
    },
    // 회원가입 회원들 리턴
    memberList(state) {
      return state.memberList;
    },
    // 팔로잉 리턴
    followingData(state) {
      return state.followingData;
    },
    // 팔로우한 사람의 마인드 리턴
    followingMindData(state) {
      return state.followingMindData;
    },
    // 나를 팔로우한 사람 목록 리턴
    followerData(state) {
      return state.followerData;
    },
    // 회원 정보 리턴
    profile(state) {
      return state.profile;
    },
    // 회원 프로필 이미지 리턴
    getProfileImage(state) {
      return state.profileImage;
    },
    // 팔로워 프로필 이미지 리턴
    followerProfileImage(state) {
      return state.followerProfileImage;
    },
    // 팔로잉 프로필 이미지 리턴
    followingProfileImage(state) {
      return state.followingProfileImage;
    },
    // 활동 리스트 리턴
    activeList(state) {
      return state.activeList;
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
    // 전체 마인드맵 리스트 변경
    setCards(state, val) {
      state.cards = val;
    },
    // 관심태그 리스트 변경
    setLikeTagData(state, val) {
      state.liketagData = val;
    },
    // 해시태그 변경
    setHashTag(state, val) {
      state.hashtag = val;
    },
    // 좋아요 리스트 변경
    setLikeData(state, val) {
      state.likeData = val;
    },
    // 스크랩 리스트 변경
    setScrapData(state, val) {
      state.scrapData = val;
    },
    // 댓글 데이터 변경
    setCommentData(state, val) {
      state.commentData = val;
    },
    // 마인드맵 변경
    setMapData(state, val) {
      state.mapData = val;
    },
    // 회원가입 회원 변경
    setMemberList(state, val) {
      state.memberList = val;
    },
    // 팔로잉 변경
    setfollowingData(state, val) {
      state.followingData = val;
    },
    // 팔로우 한 사람 마인드맵 리스트 변경
    setfollowingMindData(state, val) {
      state.followingMindData = val;
    },
    // 팔로워 변경
    setfollowerData(state, val) {
      state.followerData = val;
    },
    // 프로필 변경
    setProfile(state, val) {
      state.profile = val;
    },
    // 프로필 사진 변경
    setProfileImage(state, val) {
      state.profileImage = val;
    },
    // 팔로워 프로필 사진 변경
    setFollowerProfileImage(state, val) {
      state.followerProfileImage = val;
    },
    // 팔로잉 프로필 사진 변경
    setFollowingProfileImage(state, val) {
      state.followingProfileImage = val;
    },
    // 활동 리스트 변경
    setActiveList(state, val) {
      state.activeList = val;
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
        context.commit('setJWT', token); // 응답을 mutations으로 전달
        // jwt 디코딩
        var decodedJWT = jwt_decode(token);
        let userId = decodedJWT['email'];
        let nickName = decodedJWT['nickName'];
        // localStorage에 저장
        localStorage.setItem('jwt', token);
        localStorage.setItem('user-id', userId);
        localStorage.setItem('user-nickname', nickName);
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
    // 비밀번호변경[YJS]
    changePW(context, form) {
      return axios.post(`${SERVER_URL}/profile/changePassword`, form).then((response) => {
        context.commit('setMessage', response.data['message']); // 응답을 message에 저장
      });
    },
    // 회원탈퇴[YJS]
    withdraw(context, form) {
      return axios.post(`${SERVER_URL}/profile/withdrawal`, form).then((response) => {
        context.commit('setMessage', response.data['message']); // 응답을 message에 저장
      });
    },
    // 비밀번호 찾기[YJS]
    findpw(context, form) {
      return axios.post(`${SERVER_URL}/profile/sendTempPassword`, form).then((response) => {
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
          context.commit('setMessage', response.data.message); // 응답을 message에 저장
          const form = new FormData();
          form.append('jwt', mind.get('jwt'));
          form.append('hashtag', mind.get('hashtag'));
          form.append('subject', mind.get('subject'));
          // form.append('MindID', mindID)
          axios.post(`${SERVER_URL}/node/getInitNode`, form).then((response) => {
            context.commit('setMapData', response.data);
          });
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
    // 실시간 인기 리스트 불러오기[OMT]
    readLiveRankingMindMap(context, jwt) {
      return axios
        .get(`${SERVER_URL}/liveRanking/list/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setCards', response.data);
        });
    },
    // 관심태그 리스트 불러오기[OMT]
    readLikeTagMindMap(context, jwt) {
      return axios
        .get(`${SERVER_URL}/liketag/home/list/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setLikeTagData', response.data);
        });
    },
    // 해시태그 불러오기[OMT]
    readHashTag(context, jwt) {
      return axios
        .get(`${SERVER_URL}/liketag/list/`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setHashTag', response.data);
        });
    },
    // 해시태그 수정하기[OMT]
    updateHashTag(context, form) {
      return axios.put(`mindwiki/liketag/update/`, form).then((response) => {
        context.commit('setHashTag', response.data);
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
        context.commit('setMindList', response.data); // my_mindList불러오기위해[YJS]
      });
    },
    // 상세 마인드맵 페이지에서 마인드맵 불러오기[OMT]
    readMindDetail(context, user) {
      const jwt = user.get('jwt');
      return axios
        .get(`${SERVER_URL}/mind/read/${user.get('no')}`, {
          params: { jwt: jwt, flag: user.get('flag') },
        })
        .then((response) => {
          context.commit('setMessage', response.data);
        });
    },
    // 마인드맵 작성자 프로필 불러오기[OMT]
    readMindAdminImage(context, user) {
      const jwt = user.get('jwt');
      return axios
        .get(`${SERVER_URL}/mind/read/profilepic/${user.get('no')}`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setProfileImage', response.data);
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
        return axios
          .get(`${SERVER_URL}/mind/like/read/`, {
            params: { jwt: user.get('jwt') },
          })
          .then((response) => {
            context.commit('setLikeData', response.data);
          });
      });
    },
    // 마인드맵 스크랩[OMT]
    scrapMind(context, user) {
      const form = new FormData();
      form.append('jwt', user.get('jwt'));
      form.append('disScrap', user.get('disScrap'));
      return axios.post(`${SERVER_URL}/mind/scrap/${user.get('no')}`, user).then((response) => {
        context.commit('setMessage', response.data);
        return axios
          .get(`${SERVER_URL}/mind/scrap/read/`, {
            params: { jwt: user.get('jwt') },
          })
          .then((response) => {
            context.commit('setScrapData', response.data);
          });
      });
    },
    // 마인드맵 댓글 생성[OMT]
    createComment(context, user) {
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
    // 마인드맵 데이터 불러오기[OMT]
    readMapData(context, mind) {
      return axios.post(`${SERVER_URL}/node/getNode`, mind).then((response) => {
        context.commit('setMapData', JSON.parse(response.data));
      });
    },
    // 마인드맵 데이터 수정하기[OMT]
    updateMapData(context, mind) {
      return axios.post(`${SERVER_URL}/node/setNode`, mind).then(() => {
        return axios.post(`${SERVER_URL}/node/getNode`, mind).then((response) => {
          context.commit('setMapData', JSON.parse(response.data));
        });
      });
    },
    // 전체 회원 불러오기[HYH]
    readMemberList(context, jwt) {
      return axios.post(`${SERVER_URL}/profile/memberList`, jwt).then((response) => {
        context.commit('setMemberList', response.data);
      });
    },
    // 팔로우하기[HYH]
    userFollow(context, user) {
      const form = new FormData();
      form.append('jwt', user.get('jwt'));
      form.append('followeremail', user.get('followeremail'));
      return axios.post(`${SERVER_URL}/follower/save`, form).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
    // 팔로잉 확인[HYH]
    readFollowing(context, jwt) {
      return axios
        .get(`${SERVER_URL}/follower/list`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setfollowingData', response.data);
        });
    },
    // 상대 팔로워 확인[HYH]
    readUserFollower(context, form) {
      return axios
        .get(`${SERVER_URL}/follower/list`, {
          params: { jwt: form.get('jwt'), followeremail: form.get('followeremail') },
        })
        .then((response) => {
          context.commit('setFollowerData', response.data);
        });
    },
    // 팔로우한 유저의 마인드 확인[HYH]
    readfollowingMindData(context, form) {
      return axios
        .get(`${SERVER_URL}/follower/list/detail`, {
          params: { jwt: form.get('jwt'), followeremail: form.get('followeremail') },
        })
        .then((response) => {
          context.commit('setfollowingMindData', response.data);
        });
    },
    // 팔로워 목록에서 제거[HYH]
    deleteFollower(context, user) {
      return axios
        .delete(`${SERVER_URL}/follower/list/delete`, {
          params: {
            jwt: user.get('jwt'),
            followeremail: user.get('followeremail'),
          },
        })
        .then((response) => {
          context.commit('setMessage', response.data);
        });
    },
    // 팔로워 확인[HYH]
    readFollower(context, jwt) {
      return axios
        .get(`${SERVER_URL}/follower/followerlist`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setfollowerData', response.data);
        });
    },
    // 내 프로필 정보 가져오기[YJS]
    myProfile(context, form) {
      return axios.post(`${SERVER_URL}/profile/getMyProfile`, form).then((response) => {
        context.commit('setProfile', response.data.info);
        context.commit('setMessage', response.data.message);
      });
    },
    // 다른 사람 프로필 정보 가져오기[YJS]
    profile(context, form) {
      return axios.post(`${SERVER_URL}/profile/getProfile`, form).then((response) => {
        context.commit('setProfile', response.data.info);
      });
    },
    // 회원정보 수정[YJS]
    changeProfile(context, form) {
      return axios.post(`${SERVER_URL}/profile/changeProfile`, form).then((response) => {
        context.commit('setMessage', response.data.message);
      });
    },
    // 팔로워 사진 확인[HYH]
    readFollowerProfileImage(context, jwt) {
      return axios
        .get(`${SERVER_URL}/follower/followerlist/profilepic`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setFollowerProfileImage', response.data);
        });
    },
    // 팔로잉 사진 확인[HYH]
    readFollowingProfileImage(context, jwt) {
      return axios
        .get(`${SERVER_URL}/follower/list/profilepic`, {
          params: { jwt: jwt },
        })
        .then((response) => {
          context.commit('setFollowingProfileImage', response.data);
        });
    },
    // 내 게시물을 좋아요, 스크랩 한 목록
    readActiveList(context, jwt) {
      return axios.post(`${SERVER_URL}/read/active`, jwt).then((response) => {
        context.commit('setActiveList', response.data);
      });
    },
    // 마인드 썸네일 캡처한 이미지로 수정
    captureImage(context, form) {
      return axios.post(`${SERVER_URL}/capture/tuhmbnail`, form).then((response) => {
        context.commit('setMessage', response.data);
      });
    },
  },
});
