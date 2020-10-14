import Vue from 'vue'
import Router from 'vue-router'
import DashboardLayout from '@/layout/DashboardLayout'
import AuthLayout from '@/layout/AuthLayout'
Vue.use(Router)

export default new Router({
  linkExactActiveClass: 'active',
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: 'main',
      component: DashboardLayout,
      children: [
        {
          path: '/main',
          name: 'main',
          component: () => import('./views/Main.vue')
        },
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import('./views/Dashboard.vue')
        },
      ]
    },
    {
      path: '/',
      redirect: 'login',
      component: AuthLayout,
      children: [
        {
          path: '/login',
          name: 'login',
          component: () => import( './views/Login.vue')
        },
        {
          path: '/register',
          name: 'register',
          component: () => import( './views/Register.vue')
        }
      ]
    }
  ]
})
