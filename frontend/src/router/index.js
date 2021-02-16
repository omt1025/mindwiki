import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
// User
import Login from '@/views/user/Login.vue';
import SignUp from '@/views/user/SignUp.vue';
import PasswordChange from '@/views/user/PasswordChange.vue';
import FindPW from '@/views/user/FindPassword.vue';
// Main
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';

import MainCreateMind from '../views/mindmap/MainCreateMind.vue';

import MyLibrary from '../views/main/MyLibrary.vue';
import MainMenu from '../components/main/menu/MainMenu.vue';
import Withdraw from '../views/main/menu/Withdraw.vue';
// MyLibrary
import Follow from '../views/main/mylibrary/Follow.vue';
// MindMap
import MindMapDetail from '../views/mindmap/MindMapDetail.vue';
import MyMindList from '../views/mindmap/MyMindList.vue';
import MindComment from '../views/mindmap/MindComment.vue';
import MindMapUpdate from '../views/mindmap/MindMapUpdate.vue';
import Test from '../views/mindmap/Test.vue';

// Profile Revisee
import ReviseProfile from '../components/main/profile/ReviseProfile.vue';

import UserProfile from '@/components/main/profile/UserProfile.vue';
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/findpw',
    name: 'FindPW',
    component: FindPW,
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
  },
  {
    path: '/main/createmind',
    name: 'MainCreateMind',
    component: MainCreateMind,
  },
  {
    path: '/main/mylibrary',
    name: 'MyLibrary',
    component: MyLibrary,
  },
  {
    path: '/main/menu',
    name: 'MainMenu',
    component: MainMenu,
  },
  {
    path: '/main/menu/withdraw',
    name: 'Withdraw',
    component: Withdraw,
  },
  {
    path: '/main/mylibrary/follow',
    name: 'Follow',
    component: Follow,
  },
  {
    path: '/main/menu/passwordchange',
    name: 'PasswordChange',
    component: PasswordChange,
  },
  {
    path: '/main/mindmap/mindmapdetail/:no',
    name: 'MindMapDetail',
    component: MindMapDetail,
  },
  {
    path: '/main/mindmap/mymindlist',
    name: 'MyMindList',
    component: MyMindList,
  },
  {
    path: '/main/mindmap/mindmapdetail/:no/mindcomment',
    name: 'MindComment',
    component: MindComment,
  },
  {
    path: '/main/mindmap/mindmapdetail/:no/mindmapupdate',
    name: 'MindMapUpdate',
    component: MindMapUpdate,
  },
  {
    path: '/main/mindmap/test',
    name: 'Test',
    component: Test,
  },
  {
    path: '/main/profile/revise',
    name: 'ReviseProfile',
    component: ReviseProfile,
  },
  {
    path: '/main/userprofile/',
    name: 'UserProfile',
    component: UserProfile,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
