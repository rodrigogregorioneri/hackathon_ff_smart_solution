import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vue2Dropzone from 'vue2-dropzone'

import 'vue2-dropzone/dist/vue2Dropzone.min.css'
import '@fortawesome/fontawesome-free/js/all'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"


createApp(App).use(router).use(vue2Dropzone).mount('#app')
