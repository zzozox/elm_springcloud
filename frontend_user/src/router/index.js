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
            path: '/logout',
            name: 'Logout',
            component: () => import('../components/userViews/IndexView.vue'),
        },{
            path:'/addUserAddress',
            name:"add-user-address",
            component: () => import('../components/userViews/AddUserAddress.vue'),
        },{
            path:'/businessInfo',
            name:"businessInfo",
            component: () => import('../components/userViews/BusinessInfo.vue'),
        },{
            path:'/businessList/',
            name:"businessList",
            component: () => import('../components/userViews/BusinessList.vue'),
        },{
            path:'/editUserAddress',
            name:"edit-user-address",
            component: () => import('../components/userViews/EditUserAddress.vue'),
        },{
            path:'/index',
            name:"index",
            component: () => import('../components/userViews/index.vue'),
        },{
            path:'/orderList',
            name:"orderList",
            component: () => import('../components/userViews/OrderList.vue'),
        },{
            path:'/orders',
            name:"orders",
            component: () => import('../components/userViews/Orders.vue'),
        },{
            path:'/payment',
            name:"payment",
            component: () => import('../components/userViews/Payment.vue'),
        },{
            path:'/userAddress',
            name:"userAddress",
            component: () => import('../components/userViews/UserAddress.vue'),
        },{
            path:'/editUser',
            name:"editUser",
            component:()=>import('../components/userViews/EditUser.vue')
        },{
            path:'/businessIndex',
            name:"businessIndex",
            component:()=>import('../components/businessViews/businessIndex.vue')
        },{
            path:'/businessLogout',
            name:"businessLogout",
            component:()=>import('../components/businessViews/businessLogout.vue')
        },{
            path:'/businessInfoLook',
            name:"businessInfoLook",
            component:()=>import('../components/businessViews/businessInfoLook.vue')
        },{
            path:'/businessInfoManage',
            name:"businessInfoManage",
            component:()=>import('../components/businessViews/businessInfoManage.vue')
        },{
            path:'/myBusiness',
            name:"myBusiness",
            component:()=>import('../components/businessViews/myBusiness.vue')
        },{
            path:'/editBusiness',
            name:"editBusiness",
            component:()=>import('../components/businessViews/editBusiness.vue')
        },{
            path:'/addBusiness',
            name:"addBusiness",
            component:()=>import('../components/businessViews/addBusiness.vue')
        },{
            path:'/addFood',
            name:"addFood",
            component:()=>import('../components/businessViews/addFood.vue')
        },{
            path:'/editFood',
            name:"editFood",
            component:()=>import('../components/businessViews/editFood.vue')
        },{
            path:'/editBusinessAccount',
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
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/login')
    } else {
        next()
    }
})

export default index
