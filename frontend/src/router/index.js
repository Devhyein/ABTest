import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
<<<<<<< frontend/src/router/index.js
import Main from '../views/Main.vue'
=======
import Main from '../views/Main.vue'
import SampleA from '@/views/SampleA.vue'
>>>>>>> frontend/src/router/index.js

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },{
    path: '/main',
    name: 'Main',
    component: Main
  },
  {
    path: '/sample/a',
    name: 'SampleA',
    component: SampleA
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router