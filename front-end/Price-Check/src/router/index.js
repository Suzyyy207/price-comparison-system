import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import sign_in from '../views/sign_in/sign_in.vue'
import user_home from '../views/user_web/user_home.vue'
import user_favorites from '../views/user_web/user_favorites.vue'
import user_profile from '../views/user_web/user_profile.vue'
import user_messages from '../views/user_web/user_messages.vue'
import edit_profile from '../views/change_info/person_info.vue'
import edit_seller_profile from '../views/change_info/seller_info.vue'
import seller_home from '../views/seller_web/seller_home.vue'
import seller_profile from '../views/seller_web/seller_profile.vue'
import edit_product from '../views/change_info/product_info.vue'
import product_detail from '../views/user_web/product_show.vue'
import manager_home from '../views/manager_web/manager_home.vue'
import all_users from '../views/manager_web/manager_user.vue'
import all_products from '../views/manager_web/manager_products.vue'
import all_sellers from '../views/manager_web/manager_seller.vue'
import all_platforms from '../views/manager_web/manager_platform.vue'
import change_user from '../views/manager_web/change_user.vue'
import change_seller from '../views/manager_web/change_seller.vue'
import change_goods from '../views/manager_web/change_goods.vue'
import change_platform from '../views/manager_web/change_platform.vue'
import new_user from '../views/manager_web/new_user.vue'
import new_seller from '../views/manager_web/new_seller.vue'
import compare from '../views/user_web/compare_price.vue'
import compare_detail from '../views/user_web/compare_detail.vue'
import yearly_report from '../views/user_web/yearly_report.vue'
import product_analysis from '../views/seller_web/product_analysis.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: sign_in
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
      component: all_products
    },
    {
      //显示全部用户，管理员用
      path: '/all_users',
      name: 'all_users',
      component: all_users
    },
    {
      //显示全部商家，管理员用
      path: '/all_sellers',
      name: 'all_sellers',
      component: all_sellers
    },
    {
      //显示全部平台，管理员用
      path: '/all_platforms',
      name: 'all_platforms',
      component: all_platforms
    },
    {
      path: '/compare',
      name: 'compare',
      component: compare
    },
    {
      path: '/compare_detail',
      name: 'compare_detail',
      component: compare_detail
    },
    {
      path: '/yearly_report',
      name: 'yearly_report',
      component: yearly_report
    },
    {
      path: '/product_analysis',
      name: 'product_analysis',
      component: product_analysis
    },
    {
      path: '/edit_seller',
      name: 'edit_seller',
      component: edit_seller_profile
    },
    {
      path: '/change_user',
      name: 'change_user',
      component: change_user
    },
    {
      path: '/change_seller',
      name: 'change_seller',
      component: change_seller
    },

    {
      path: '/new_user',
      name: 'new_user',
      component: new_user
    },
    {
      path: '/new_seller',
      name: 'new_seller',
      component: new_seller
    },
    {
      path: '/change_goods',
      name: 'change_goods',
      component: change_goods
    },
    {
      path: '/change_platform',
      name: 'change_platform',
      component: change_platform
    },

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
