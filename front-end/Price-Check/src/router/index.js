import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import sign_in from '../views/sign_in/sign_in.vue'
import user_home from '../views/user_web/user_home.vue'
import user_favorites from '../views/user_web/user_favorites.vue'
import user_profile from '../views/user_web/user_profile.vue'
import user_messages from '../views/user_web/user_messages.vue'
import edit_profile from '../views/change_info/person_info.vue'
import seller_home from '../views/seller_web/seller_home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: user_home
    },
    {
      path: '/user_web',
      name: 'user_web',
      component: user_home
    },
    {
      path:'/seller_web',
      name: 'seller_web',
      component: seller_home
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
      path: '/edit-profile',
      name: 'edit-profile',
      component: edit_profile
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
