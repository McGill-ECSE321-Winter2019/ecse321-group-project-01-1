import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import InternshipListing from "@/components/InternshipListing";
import InternshipItem from "@/components/InternshipItem";
<<<<<<< HEAD
import ProfileViewing from "@/components/ProfileViewing";
=======
import Profile from '@/components/Profile'
>>>>>>> 619547a40beffb85b1cd1cf2bd97dae3e9c614c2



<<<<<<< HEAD
export default new Router({
    routes: [
        {
            path: '/hello',
            name: 'Hello',
            component: Hello
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/list',
            name: 'InternshipListing',
            component: InternshipListing
        },
        {
            path: '/test',
            name: 'test',
            component: InternshipItem
        },
    	{
      	    path: '/view',
      	    name: 'View',
      	    component: ProfileViewing
    	}
=======
Vue.use(Router)
>>>>>>> 619547a40beffb85b1cd1cf2bd97dae3e9c614c2

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
