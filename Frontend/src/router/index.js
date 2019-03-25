import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import InternshipListing from "@/components/InternshipListing";
import InternshipItem from "@/components/InternshipItem";
import ProfileViewing from "@/components/ProfileViewing";


Vue.use(Router)

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

    ]
})
