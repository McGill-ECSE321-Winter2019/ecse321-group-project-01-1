import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import InternshipListing from "@/components/InternshipListing";
import sidebar from "@/components/sidebar";
import InternshipItem from "@/components/InternshipItem";


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
            path: '/side',
            name: 'sidebar',
            component: sidebar
        },
        {
            path: '/test',
            name: 'test',
            component: InternshipItem
        },

    ]
})
