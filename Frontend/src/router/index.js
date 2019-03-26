import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import ApplicationForm from '@/components/ApplicationForm'
import InternshipListing from "@/components/InternshipListing";
import InternshipItem from "@/components/InternshipItem";

import Reminder from '@/components/Reminder'

import ProfileViewing from '@/components/ProfileViewing'



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
      path: '/form',
      name: 'form',
      component: ApplicationForm
    },
    {
        path: '/profile',
        name: 'ProfileViewing',
        component: ProfileViewing
    },
    {
        path: '/list',
        name: 'InternshipListing',
        component: InternshipListing
    },
  ]
})

export default router