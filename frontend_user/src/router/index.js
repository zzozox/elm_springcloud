import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

const index = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('../components/WelcomeView.vue'),
        },{
            path: '/login',
            name: 'login',
            component: () => import('../components/welcome/LoginPage.vue')
        },  {
            path: '/register',
            name: 'register',
            component: () => import('../components/welcome/RegisterPage.vue')
        }, {
            path: '/forget',
            name: 'forget',
            component: () => import('../components/welcome/ForgetPage.vue')
        },{
            path: '/logout',
            name: 'Logout',
            component: () => import('../components/IndexView.vue'),
        },{
            path:'/addUserAddress',
            name:"add-user-address",
            component: () => import('../components/AddUserAddress.vue'),
        },{
            path:'/businessInfo',
            name:"businessInfo",
            component: () => import('../components/BusinessInfo.vue'),
        },{
            path:'/businessList/',
            name:"businessList",
            component: () => import('../components/BusinessList.vue'),
        },{
            path:'/editUserAddress',
            name:"edit-user-address",
            component: () => import('../components/EditUserAddress.vue'),
        },{
            path:'/index',
            name:"index",
            component: () => import('../components/index.vue'),
        },{
            path:'/orderList',
            name:"orderList",
            component: () => import('../components/OrderList.vue'),
        },{
            path:'/orders',
            name:"orders",
            component: () => import('../components/Orders.vue'),
        },{
            path:'/payment',
            name:"payment",
            component: () => import('../components/Payment.vue'),
        },{
            path:'/userAddress',
            name:"userAddress",
            component: () => import('../components/UserAddress.vue'),
        },{
            path:'/editUser',
            name:"editUser",
            component:()=>import('../components/EditUser.vue')
        }
    ]
})

/**
 * 路由守卫
 */
index.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/login')
    } else {
        next()
    }
})

export default index
