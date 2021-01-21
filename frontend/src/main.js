import Vue from 'vue';
import Vuex from 'vuex';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import store from './store/index';
import VueGlide from 'vue-glide-js';
import 'vue-glide-js/dist/vue-glide.css'

Vue.config.productionTip = false;

// Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(VueGlide);

// const router = new VueRouter({
//   routes,
// });

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
