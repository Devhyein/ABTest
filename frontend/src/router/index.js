import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
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
