import Vue from 'vue'
import VueRouter from 'vue-router'
import SampleA from '@/views/SampleA.vue'
import SampleB from '@/views/SampleB.vue'
import Join from '@/views/join.vue'

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
    path: '/sample/b',
    name: 'SampleB',
    component: SampleB
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
