import Vue from 'vue';
import Vuex from 'vuex';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import store from './store';
import VueGlide from 'vue-glide-js';
import 'vue-glide-js/dist/vue-glide.css';
import * as VeeValidate from 'vee-validate';

//fort-awesome
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

//mindmap
import Mindmap from 'vue-mindmap'
import 'vue-mindmap/dist/vue-mindmap.css'

library.add(faUserSecret);

Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.config.productionTip = false;

// Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(VueGlide);
Vue.use(Mindmap);

Vue.config.productionTip = false
// const router = new VueRouter({
//   routes,
// });

new Vue({
  vuetify,
  router,
  store,
  VeeValidate,
  render: (h) => h(App),
}).$mount('#app');

window.Kakao.init('bdcd952db81fedf63f83f013e3bd2e82');
