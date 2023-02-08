<template>
	<view class="PI-detail-container">
		<view class="PI-detail-name">
			{{detail.interpretTitle}}
		</view>
		<view class="PI-detail-info">
			{{detail.user}} &nbsp;&nbsp;{{detail.time}}
		</view>
		<view class="PI-detail-body">
			<view class="PI-detail-body-paragraph" v-for="paragraph in detail.body">{{paragraph}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				detail: {}
			}
		},
		methods: {
			
		},
		onLoad (option) {
			console.log(option.id)
			if (option.id) {
				this.$store.dispatch('getPIByID', option.id).then(res => {
					this.detail = res
					this.detail.body = this.detail.interpretBody.split('\n')
				})
			}
		}
	}
</script>

<style lang="less">
.PI-detail-container {
	padding: 20px;
	.PI-detail-name {
		font-size: 24px;
		font-weight: 700;
		text-align: center;
	}
	.PI-detail-info {
		margin-top: 20px;
		text-align: center;		
	}
	.PI-detail-body {
		margin-top: 20px;
		font-size: 16px;
		line-height: 32px;
		.PI-detail-body-paragraph {
			text-indent: 32px;
		}
	}
}
</style>
