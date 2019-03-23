import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import InternshipListing from "@/components/InternshipListing";
import sidebar from "@/components/sidebar";


Vue.use(Router)

export default new Router({
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
            path: '/test',
            name: 'InternshipListing',
            component: InternshipListing
        },
        {
            path: '/side',
            name: 'sidebar',
            component: sidebar
        }

    ]
})
