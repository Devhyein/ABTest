import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Join from '@/views/Join.vue'
import Login from '@/views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/sample',
    name: 'Main',
    component: Main
  },
  {
    path: '/sample/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/sample/login',
    name: 'Login',
    component: Login
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
