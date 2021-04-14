import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios';
import tocbot from "tocbot";
import * as echarts from 'echarts'
import ElementUI from 'element-ui';
import mavonEditor from 'mavon-editor';

import './axios'
import 'mavon-editor/dist/css/index.css';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/icon/iconfont.css';
import '@/assets/css/notebook_online.css';
import '@/assets/css/marginAndPadding.css';
import '@/assets/css/fontAbout.css';
import '@/assets/css/widthAndHeight.css';
import '@/assets/css/blankDiv.css';

Vue.use(tocbot)
Vue.use(ElementUI)
Vue.use(mavonEditor)
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios

axios.defaults.withCredentials = true
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
