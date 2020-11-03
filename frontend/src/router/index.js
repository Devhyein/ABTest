import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import SampleA from '@/views/SampleA.vue'
import SampleB from '@/views/SampleB.vue'
import Login from '../views/Login.vue'
import Insert from '../views/Insert.vue'
import Detail from '../views/Detail.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/main',
    name: 'Main',
    component: Main
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
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/insert',
    name: 'Insert',
    component: Insert
  },
  {
    path: '/detail',
    name: 'Detail',
    component: Detail
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
