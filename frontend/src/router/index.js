import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
// User
import Login from '@/views/user/Login.vue';
import SignUp from '@/views/user/SignUp.vue';
import ChangePassword from '@/views/user/ChangePassword.vue';
// Main
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';
import Search from '../views/main/Search.vue';
import MakeMind from '../views/main/MakeMind.vue';
import Activity from '../views/main/Activity.vue';
import MyLibrary from '../views/main/MyLibrary.vue';
import MainMenu from '../components/main/menu/MainMenu.vue';
// MyLibrary
import Follow from '../views/main/mylibrary/Follow.vue';

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
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
