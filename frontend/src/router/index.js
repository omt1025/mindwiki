import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
// User
import Login from '@/views/user/Login2.vue';
// Main
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
import Search from '../views/main/Search.vue'
import MakeMind from '../views/main/MakeMind.vue'
import Activity from '../views/main/Activity.vue'
import MyLibrary from '../views/main/MyLibrary.vue'
import MainMenu from '../components/main/menu/MainMenu.vue'
// MyLibrary
import Followers from '../views/main/mylibrary/Followers.vue'
import Followings from '../views/main/mylibrary/Followings.vue'

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
    path: '/main',
    name: 'Main',
    component: Main,
  },
  {
    path: '/main/search',
    name: 'Search',
    component: Search,
  },
  {
    path: '/main/makemind',
    name: 'MakeMind',
    component: MakeMind,
  },
  {
    path: '/main/activity',
    name: 'Activity',
    component: Activity,
  },
  {
    path: '/main/mylibrary',
    name: 'MyLibrary',
    component: MyLibrary,
  },
  {
    path: '/main/menu',
    name: 'MainMenu',
    component: MainMenu
  },
  {
    path: '/main/mylibrary/followers',
    name: 'Followers',
    component: Followers
  },
  {
    path: '/main/mylibrary/followings',
    name: 'Followings',
    component: Followings
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
