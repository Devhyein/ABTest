import Vue from 'vue'
import VueRouter from 'vue-router'
import SampleA from '@/views/SampleA.vue'
import SampleB from '@/views/SampleB.vue'
import Join from '@/views/Join.vue'
import Login from '@/views/Login.vue'

Vue.use(VueRouter)

const routes = [
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
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/login',
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
