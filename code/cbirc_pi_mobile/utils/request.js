import store from '../store'

const devBaseUrl = 'http://localhost:8002'
const prodBaseUrl = ''

let baseUrl = process.env.NODE_ENV === 'development' ? devBaseUrl : prodBaseUrl

function request(url, method, data) {
	uni.showLoading({
		title: '加载中' //  数据请求前loading
	})
	const header = {
		'content-type': 'application/json',
	}
	if (store.getters.getToken.length) header['cbirc-token'] = store.getters.getToken
	return new Promise((resolve, reject) => {
		uni.request({
			url: baseUrl + url,
			method,
			data,
			header,
			success: function(res) {
				uni.hideLoading()
				if (url.indexOf('/user/login' !== -1)) {
					if (res.header && res.header['cbirc-token']) {
						store.state.user.token = res.header['cbirc-token']
					}
				}
				resolve(res.data)
			},
			error: function(error) {
				uni.hideLoading()
				reject(error)
			},
			complete: function() {
				uni.hideLoading()
			}
		})
	})
}


function get(url, data) {
	return request(url, 'GET', data)
}

function post(url, data) {
	return request(url, 'POST', data)
}

export default {
	request,
	get,
	post
}
