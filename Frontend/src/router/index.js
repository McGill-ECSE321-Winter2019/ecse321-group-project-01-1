import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import InternshipListing from "@/components/InternshipListing";
import InternshipItem from "@/components/InternshipItem";
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
        path: '/profile',
        name: 'profile',
        component: Profile,
    },
    {
        path: '/list',
        name: 'InternshipListing',
        component: InternshipListing
    },
  ]
})

export default router
