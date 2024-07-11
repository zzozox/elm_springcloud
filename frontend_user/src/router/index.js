import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

const index = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('../components/welcome/WelcomeView.vue'),
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
            path: '/user/logout',
            name: 'Logout',
            component: () => import('../components/userViews/IndexView.vue'),
        },{
            path:'/user/addUserAddress',
            name:"add-user-address",
            component: () => import('../components/userViews/AddUserAddress.vue'),
        },{
            path:'/user/businessInfo',
            name:"businessInfo",
            component: () => import('../components/userViews/BusinessInfo.vue'),
        },{
            path:'/user/businessList/',
            name:"businessList",
            component: () => import('../components/userViews/BusinessList.vue'),
        },{
            path:'/user/editUserAddress',
            name:"edit-user-address",
            component: () => import('../components/userViews/EditUserAddress.vue'),
        },{
            path:'/user/index',
            name:"index",
            component: () => import('../components/userViews/index.vue'),
        },{
            path:'/user/orderList',
            name:"orderList",
            component: () => import('../components/userViews/OrderList.vue'),
        },{
            path:'/user/orders',
            name:"orders",
            component: () => import('../components/userViews/Orders.vue'),
        },{
            path:'/user/payment',
            name:"payment",
            component: () => import('../components/userViews/Payment.vue'),
        },{
            path:'/user/userAddress',
            name:"userAddress",
            component: () => import('../components/userViews/UserAddress.vue'),
        },{
            path:'/user/editUser',
            name:"editUser",
            component:()=>import('../components/userViews/EditUser.vue')
        },{
            path:'/business/businessIndex',
            name:"businessIndex",
            component:()=>import('../components/businessViews/businessIndex.vue')
        },{
            path:'/business/businessLogout',
            name:"businessLogout",
            component:()=>import('../components/businessViews/businessLogout.vue')
        },{
            path:'/business/businessInfoLook',
            name:"businessInfoLook",
            component:()=>import('../components/businessViews/businessInfoLook.vue')
        },{
            path:'/business/businessInfoManage',
            name:"businessInfoManage",
            component:()=>import('../components/businessViews/businessInfoManage.vue')
        },{
            path:'/business/myBusiness',
            name:"myBusiness",
            component:()=>import('../components/businessViews/myBusiness.vue')
        },{
            path:'/business/editBusiness',
            name:"editBusiness",
            component:()=>import('../components/businessViews/editBusiness.vue')
        },{
            path:'/business/addBusiness',
            name:"addBusiness",
            component:()=>import('../components/businessViews/addBusiness.vue')
        },{
            path:'/business/addFood',
            name:"addFood",
            component:()=>import('../components/businessViews/addFood.vue')
        },{
            path:'/business/editFood',
            name:"editFood",
            component:()=>import('../components/businessViews/editFood.vue')
        },{
            path:'/business/editBusinessAccount',
            name:"editBusinessAccount",
            component:()=>import('../components/businessViews/editBusinessAccount.vue')
        }
    ]
})

/**
 * 路由守卫
 */
index.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name && to.name.startsWith('welcome') && !isUnauthorized) {
        next('/user/index')
    } else if(to.fullPath.startsWith('/user') && isUnauthorized) {
        next('/login')
    } else if(to.fullPath.startsWith('/business') && isUnauthorized) {
        next('/login')
    } else {
        next()
    }
})

export default index
