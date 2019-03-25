import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Reminder from '@/components/Reminder'
import Profile from '@/components/Profile'


Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/reminders',
      name: 'reminders',
      component: Reminder
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
    }

  ]
})

export default router
