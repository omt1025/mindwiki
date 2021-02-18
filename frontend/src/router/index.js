import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
// Start Page
import Home from '../views/Home.vue';
// User
import Login from '@/views/user/Login.vue';
import SignUp from '@/views/user/SignUp.vue';
import PasswordChange from '@/views/user/PasswordChange.vue';
import FindPW from '@/views/user/FindPassword.vue';
// Main
import Main from '../views/Main.vue';
// Main Menu
import MainMenu from '../components/main/menu/MainMenu.vue';
import Withdraw from '../views/main/menu/Withdraw.vue';
// MindMap
import MainCreateMind from '../views/mindmap/MainCreateMind.vue';
import MindMapDetail from '../views/mindmap/MindMapDetail.vue';
import MindComment from '../views/mindmap/MindComment.vue';
import MindMapUpdate from '../views/mindmap/MindMapUpdate.vue';
// Profile Revise
import ReviseProfile from '../components/main/profile/ReviseProfile.vue';
import UserProfile from '@/components/main/profile/UserProfile.vue';
// Follow
import Follow from '../components/main/mylibrary/Follow.vue';

import Test from '../views/mindmap/Test.vue';

Vue.use(VueRouter);

const routes = [
  // Start Page
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  // User
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
    path: '/main/menu/passwordchange',
    name: 'PasswordChange',
    component: PasswordChange,
  },
  {
    path: '/findpw',
    name: 'FindPW',
    component: FindPW,
  },
  // Main
  {
    path: '/main',
    name: 'Main',
    component: Main,
  },
  // Main Menu
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
  // MindMap
  {
    path: '/main/createmind',
    name: 'MainCreateMind',
    component: MainCreateMind,
  },
  {
    path: '/main/mindmap/mindmapdetail/:no',
    name: 'MindMapDetail',
    component: MindMapDetail,
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
  // Profile Revise
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
  {
    path: '/main/mindmap/test',
    name: 'Test',
    component: Test,
  },
  // Follow
  {
    path: '/main/mylibrary/follow',
    name: 'Follow',
    component: Follow,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
