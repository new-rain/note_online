import axios from 'axios';

//设置URL前缀
axios.defaults.baseURL="http://localhost:8888"

//设置前置拦截
axios.interceptors.request.use(config=>{
    return config
})

axios.interceptors.response.use(response=>{
    return response
})