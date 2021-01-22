import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios'
import qs from 'qs'


import mock from './mock'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Viewer from 'v-viewer'

import 'viewerjs/dist/viewer.css'

import xss from 'xss'
Object.defineProperty(Vue.prototype, '$xss', {
    value: xss,
})
Vue.prototype.$apiUrl = "http://localhost:8080";
Vue.use(Viewer)

Viewer.setDefaults({

    Options: { 'inline': true, 'button': true, 'navbar': true, 'title': true, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true, 'url': 'data-source' }

})

Vue.prototype.$qs = qs

Vue.use(ElementUI)
Vue.prototype.axios = axios
Vue.config.productionTip = false


//刷新保存状态
if (sessionStorage.getItem("store")) {
    store.replaceState(
        Object.assign({},
            store.state,
            JSON.parse(sessionStorage.getItem("store"))
        )
    );
    sessionStorage.removeItem("store")
}

//监听，在页面刷新时将vuex里的信息保存到sessionStorage里
window.addEventListener("beforeunload", () => {
    sessionStorage.setItem("store", JSON.stringify(store.state));
});
new Vue({
    router,
    store,
    mock,
    render: h => h(App)
}).$mount('#app')