<template>
	<view class="self-container">
		<view class="self-input-box" v-if="!userName">
			<view class="self-input-box-item">
				<u-input
					placeholder="请输入用户名"
					border="surround"
					v-model="inputUserName" />
			</view>
			<view class="self-input-box-item">
				<u-input
					placeholder="请输入密码"
					border="surround"
					v-model="inputPassword"
					type="password" />
			</view>
			<view class="self-input-box-item btn-box">
				<u-button type="primary" text="登录" @click="login"></u-button>
			</view>
		</view>
	
		<view class="self-info-box" v-else>
			{{userName}}
		</view>
	</view>
</template>

<script>
	export default {
		data () {
			return {
				userName: '',
				inputUserName: '',
				inputPassword: '',
				redirectUrl: ''
			}
		},
		onShow () {
			let redirectUrl = uni.getStorageSync('redirect')
			if (uni.getStorageSync('redirect')) this.redirectUrl = uni.getStorageSync('redirect')
			uni.removeStorageSync('redirect')
		},
		methods: {
			login () {
				console.log(this.inputUserName)
				console.log(this.inputPassword)
				if (!this.inputUserName) {
					uni.showToast({
						title: '请输入用户名！',
						icon: 'error'
					})
					return
				}
				if (!this.inputPassword) {
					uni.showToast({
						title: '请输入密码！',
						icon: 'error'
					})
					return
				}
				this.$store.dispatch('login', {
					name: this.inputUserName,
					password: this.inputPassword
				}).then(res => {
					console.log(res)
					if (res.success) {
						this.$store.commit('setUserName', this.inputUserName)
						this.userName = this.inputUserName
						if (this.redirectUrl) {
							uni.switchTab({
								url: '/pages/PIList/PIList'
							})
						} else {
							uni.showToast({
								title: '登录成功！'
							})
							return							
						}
					} else {
						uni.showToast({
							title: res.message,
							icon: 'error'
						})
						return
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.self-container {
		height: 100%;
		.self-input-box {
			display: flex;
			flex-direction: column;
			align-items: center;
			margin: 20px;
			.self-input-box-item {
				margin-top: 20px;
				width: 80%;
			}
			.btn-box {
				width: 40%;
			}
		}
	}
</style>
