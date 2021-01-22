import axios from 'axios'
import router from '@/router'
axios.defaults.timeout = 3000
axios.defaults.baseURL = ''
axios.interceptors.request.use(
    config => {
        // const token = getCookie('名称')
        // config.data = config.data
        config.headers = {
            'Content-Type': 'application/json; charset=utf-8'
        }
        if (config.url != this.$apiUrl + '/api/login/index') {
            if (localStorage.getItem('Authorization')) {
                config.headers.Authorizatior = localStorage.getItem('Authorization')
            }
        }
        // if (token) {
        // config.params = {'token': token}
        // }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)
axios.interceptors.response.use(
        response => {
            //返回错误跳转到首页
            if (response.data.code === 0) {
                router.push({
                    path: '/home',
                    querry: {
                        redirect: router.currentRoute.fullPath
                    }
                })
            }
            return response
        },
        error => {
            if (error.response) {
                switch (error.response.status) {
                    case 401:
                        localStorage.removeItem('Authorization')
                        router.push('/login')
                }
            }
            return Promise.reject(error)
        }
    )
    /**
     * 封装get方法
     * @param url
     * @param data
     * @returns {Promise}
     */
export function fetch(url, params = {}) {
    return new Promise((resolve, reject) => {
        axios.get(url, {
                params: params
            })
            .then(response => {
                resolve(response.data)
            })
            .catch(err => {
                reject(err)
            })
    })
}
/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.post(url, data)
            .then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
    })
}
/**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function patch(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.patch(url, data)
            .then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
    })
}
/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function put(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.put(url, data)
            .then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
    })
}