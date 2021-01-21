import Vue from 'vue';
import VueRouter from 'vue-router';
// import store from '@/store';
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';
// User
import Login from '@/views/user/Login.vue';
// Main
import LivePopular from '../views/main/home/LivePopular.vue';
import Favorite from '../views/main/home/Favorite.vue';
import MyWiki from '../views/main/home/MyWiki.vue';
import Search from '../views/main/Search.vue';
import MakeMind from '../views/main/MakeMind.vue';
import Activity from '../views/main/Activity.vue';
import MyLibrary from '../views/main/MyLibrary.vue';

Vue.use(VueRouter);

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/login', name: 'Login', component: Login },
  { path: '/main', name: 'Main', component: Main },
  { path: '/main/home', name: 'LivePopular', component: LivePopular },
  { path: '/main/search', name: 'Search', component: Search },
  { path: '/main/makemind', name: 'MakeMind', component: MakeMind },
  { path: '/main/activity', name: 'Activity', component: Activity },
  { path: '/main/mylibrary', name: 'MyLibrary', component: MyLibrary },
  {
    path: '/main/home/favorite',
    name: 'Favorite',
    component: Favorite,
  },
  {
    path: '/main/home/mywiki',
    name: 'MyWiki',
    component: MyWiki,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
