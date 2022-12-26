import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Index from '../views/Index.vue'
import Exam from '../views/Exam.vue'
import MyIndex from '../views/MyIndex.vue'
import Record from '../views/Record.vue'
import Edit from '../views/Edit.vue'

Vue.use(VueRouter)

const routes = [
  {
	path: '/',
    name: 'Home',
    component: Login
  },
  {
	path: '/login',
	name: 'Login',
	component: Login
  },
  {
	path: '/register',
	name: 'Register',
	component: Register
  },
  {
	path: '/index',
	name: 'Index',
	component: Index
  },
  {
	path: '/exam',
	name: 'Exam',
	component: Exam
  },
  {
	path: '/myIndex',
	name: 'MyIndex',
	component: MyIndex
  },
  {
	path: '/record',
	name: 'Record',
	component: Record
  },
  {
	path: '/edit',
	name: 'Edit',
	component: Edit
  }
]

// solve the repeated problem
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
