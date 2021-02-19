/*
 * 작성자 : 서울2반 4팀 윤지선
 * 내용 : card 오픈소스 사용위해 VueHtml2Canvasimport
 * 생성일자 : 2021-01-20
 * 최종수정일자 : 2021-02-18
 */

import Vue from 'vue';
import Vuex from 'vuex';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import store from './store';

// glide
import VueGlide from 'vue-glide-js';
import 'vue-glide-js/dist/vue-glide.css';
import * as VeeValidate from 'vee-validate';

// fort-awesome
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

// mindmap[OMT]
import Mindmap from 'vue-mindmap';
import 'vue-mindmap/dist/vue-mindmap.css';

// Wolfi[YJS] : profile에 사용중
// https://wolfi-vue.netlify.app/docs/getting-started/quick-start/
import Wolfi from '@xarples/wolfi-vue';

// tree
import VueTree from '@ssthouse/vue-tree-chart';

// 화면 캡처
import '@babel/polyfill'; // 없으면 에러나요 npm i --save '@babel/polyfill'
import VueHtml2Canvas from 'vue-html2canvas';

library.add(faUserSecret);

Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.config.productionTip = false;

Vue.use(Vuex);
Vue.use(VueGlide);
Vue.use(Mindmap);
Vue.use(Wolfi);
Vue.component('vue-tree', VueTree);
Vue.use(VueHtml2Canvas);

Vue.config.productionTip = false;

new Vue({
  vuetify,
  router,
  store,
  VeeValidate,
  render: (h) => h(App),
}).$mount('#app');

// 카카오 로그인 API
window.Kakao.init('bdcd952db81fedf63f83f013e3bd2e82');
