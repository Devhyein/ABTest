import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Join from '@/views/Join.vue'
import Login from '@/views/Login.vue'
import Test from '@/views/Test.vue'
import TestLogin from '@/views/TestLogin.vue'
import TestJoin from '@/views/TestJoin.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: 'sample',
  },
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
  {
    path: '/sample/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/sample/test/join',
    name: 'TestJoin',
    component: TestJoin
  },
  {
    path: '/sample/test/login',
    name: 'TestLogin',
    component: TestLogin
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
