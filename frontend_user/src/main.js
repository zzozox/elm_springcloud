import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import qs from "qs";
import 'element-plus/theme-chalk/dark/css-vars.css'

axios.defaults.baseURL = '/ele';

const app = createApp(App)

app.use(router)
app.provide("$qs", qs);

app.mount('#app')

