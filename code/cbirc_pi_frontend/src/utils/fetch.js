import axios from 'axios'
import { getToken } from './auth'

const devBaseUrl = 'http://localhost:8002'
const prodBaseUrl = ''

let baseUrl = process.env.NODE_ENV === 'development' ? devBaseUrl : prodBaseUrl

const instance = axios.create({
  baseURL: baseUrl,
  timeout: 10000
})
// 添加请求拦截器
instance.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  const hasToken = getToken()
  if (hasToken) {
    config.headers['cbirc-token'] = hasToken.token
  }
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  let responseData = {
    data: response.data,
    status: response.status,
    statusText: response.statusText
  }
  if (response.config.url === '/user/login' && response.data.success) {
    responseData.data.token = response.headers['cbirc-token']
  }
  return responseData
}, function (error) {
  // 对响应错误做点什么
  let responseData = {
    data: error.response.data,
    status: error.response.status,
    statusText: error.response.statusText
  }
  return Promise.reject(responseData)
})
export default instance
