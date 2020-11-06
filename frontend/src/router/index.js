import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import SampleA from '@/views/SampleA.vue'
import SampleB from '@/views/SampleB.vue'
import Insert from '../views/Insert.vue'
import Detail from '../views/Detail.vue'
import Join from '../views/Join.vue'

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    redirect: 'main',
  },
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
    path: '/insert',
    name: 'Insert',
    component: Insert
  },  
  {
    path: '/join',
    name: 'Join',
    component: Join
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
