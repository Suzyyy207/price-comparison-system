import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

const app = createApp(App)
app.use(router)
app.mount('#app')

app.config.globalProperties.$axios = axios;
axios.defaults.baseURL = "/api";
axios.defaults.withCredentials = false;
