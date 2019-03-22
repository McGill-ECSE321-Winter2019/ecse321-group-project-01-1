// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
const config = require('../config')
const backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
const frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port


// axios client
const client = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


Vue.use(BootstrapVue)
Vue.config.productionTip = false
Vue.prototype.$http = client



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },

})


