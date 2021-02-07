import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
// User
import Login from '@/views/user/Login.vue';
import SignUp from '@/views/user/SignUp.vue';
import ChangePassword from '@/views/user/ChangePassword.vue';
import FindPW from '@/views/user/FindPassword.vue';
// Main
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';

import MakeMind from '../views/main/MakeMind.vue';

import MyLibrary from '../views/main/MyLibrary.vue';
import MainMenu from '../components/main/menu/MainMenu.vue';
// MyLibrary
import Follow from '../views/main/mylibrary/Follow.vue';
// MindMap
import MindMapDetail from '../views/mindmap/MindMapDetail.vue';
import MyMindList from '../views/mindmap/MyMindList.vue';

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
    path: '/main/makemind',
    name: 'MakeMind',
    component: MakeMind,
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
    path: '/main/mylibrary/follow',
    name: 'Follow',
    component: Follow,
  },
  {
    path: '/main/menu/changepassword',
    name: 'ChangePassword',
    component: ChangePassword,
  },
  {
    path: '/main/mindmap/mindmapdetail/:no',
    name: 'MindMapDetail',
    component: MindMapDetail,
    // props: true
  },
  {
    path: '/main/mindmap/mymindlist',
    name: 'MyMindList',
    component: MyMindList,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
