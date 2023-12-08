import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import sign_in from '../views/sign_in/sign_in.vue'
import user_home from '../views/user_web/user_home.vue'
import user_favorites from '../views/user_web/user_favorites.vue'
import user_profile from '../views/user_web/user_profile.vue'
import user_messages from '../views/user_web/user_messages.vue'
import edit_profile from '../views/change_info/person_info.vue'
import seller_home from '../views/seller_web/seller_home.vue'
import seller_profile from '../views/seller_web/seller_profile.vue'
import edit_product from '../views/change_info/product_info.vue'
import product_detail from '../views/user_web/product_show.vue'
import manager_home from '../views/manager_web/manager_home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: manager_home
    },
    {
      path: '/user_web',
      name: 'user_web',
      component: user_home
    },
    {
      path: '/favorites',
      name: 'favorites',
      component: user_favorites
    },
    {
      path: '/profile',
      name: 'profile',
      component: user_profile
    },
    {
      path: '/messages',
      name: 'messages',
      component: user_messages
    },
    {
      path: '/edit_profile',
      name: 'edit_profile',
      component: edit_profile
    },

    {
      path:'/seller_web',
      name: 'seller_web',
      component: seller_home
    },
    {
      path:'/seller_profile',
      name: 'seller_profile',
      component: seller_profile
    },
    {
      path: '/edit_product',
      name: 'edit_product',
      component: edit_product
    },
    {
      path: '/product_detail',
      name: 'product_detail',
      component: product_detail
    },
    {
      //管理员主页
      path: '/manager_web',
      name: 'manager_web',
      component: manager_home
    },
    {
      //显示全部商品，管理员用
      path: '/all_products',
      name: 'all_products',
      component: product_detail //改
    },
    {
      //显示全部用户，管理员用
      path: '/all_users',
      name: 'all_users',
      component: product_detail //改
    },
    {
      //显示全部商家，管理员用
      path: '/all_sellers',
      name: 'all_sellers',
      component: product_detail //改
    },
    {
      //显示全部平台，管理员用
      path: '/all_platforms',
      name: 'all_platforms',
      component: product_detail //改
    }

    /*{
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }*/
  ]
})

export default router
