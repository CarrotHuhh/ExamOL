import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import qs from 'qs'
import moment from 'moment'
import {
	getCurDate,
	setSessionStorage,
	getSessionStorage,
	removeSessionStorage,
	setLocalStorage,
	getLocalStorage,
	removeLocalStorage
} from './common.js'

Vue.config.productionTip = false

//set base url
axios.defaults.baseURL = 'http://localhost:8080/exam/';
//attach axios to vue, using by this.$axios
Vue.prototype.$axios = axios;
//attach qs to vue, using by this.$qs
Vue.prototype.$qs = qs;

Vue.prototype.$moment = moment;
//attach getCurDate to vue, using by this.$getCurDate
Vue.prototype.$getCurDate = getCurDate;
//attach setSessionStorage to vue, using by this.$setSessionStorage
Vue.prototype.$setSessionStorage = setSessionStorage;
//attach getSessionStorage to vue, using by this.$getSessionStorage
Vue.prototype.$getSessionStorage = getSessionStorage;
//attach removeSessionStorage to vue, using by this.$removeSessionStorage
Vue.prototype.$removeSessionStorage = removeSessionStorage;
//attach setLocalStorage to vue, using by this.$setLocalStorage
Vue.prototype.$setLocalStorage = setLocalStorage;
//attach getLocalStorage to vue, using by this.$getLocalStorage
Vue.prototype.$getLocalStorage = getLocalStorage;
//attach removeLocalStorage to vue, using by this.$removeLocalStorage
Vue.prototype.$removeLocalStorage = removeLocalStorage;

router.beforeEach(function(to, from, next) {
	let user = sessionStorage.getItem('user');
	if (!to.path == '/login'){
		if (user == null) {
			router.push(('login'));
			location.reload();
		}
	}
	next();
});

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

