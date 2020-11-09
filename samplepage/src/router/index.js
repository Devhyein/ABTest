import Vue from 'vue'
import VueRouter from 'vue-router'
import SampleA from '@/views/SampleA.vue'
import SampleB from '@/views/SampleB.vue'
import Join from '@/views/Join.vue'
import Login from '@/views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: 'sample',
  },
  {
    path: '/sample',
    name: 'SampleA',
    component: SampleA
  },
  {
    path: '/sample/a',
    name: 'SampleA',
    component: SampleA
  },
  {
    path: '/sample/b',
    name: 'SampleB',
    component: SampleB
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
