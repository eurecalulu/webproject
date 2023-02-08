<template>
	<view class="PIList-container">
		<view class="PI-filter" v-if="showFilter">
			<view class="PI-filter-input">
				<u-input v-model="docTitle" placeholder="请输入法规名称"></u-input>
			</view>
			<view class="PI-filter-input">
				<u-input v-model="docIdentifier" placeholder="请输入法规文号"></u-input>
			</view>
			<view class="PI-filter-input">
				<u-input v-model="docDepart" placeholder="请输入发文机构"></u-input>
			</view>
			<view class="PI-filter-btn-box">
				<view class="PI-filter-btn">
					<u-button type="primary" size="normal" @click="search">搜索</u-button>
				</view>
				<view class="PI-filter-btn">
					<u-button type="error" size="normal" @click="clearInput">清除</u-button>
				</view>
			</view>
		</view>
		<view class="PI-filter-control">
			<view class="PI-filter-control-item" @click="changeShowFilter" v-if="!showFilter">
				<u-icon name="arrow-down" size="20"></u-icon>
				展开
			</view>
			<view class="PI-filter-control-item" @click="changeShowFilter" v-if="showFilter">
				<u-icon name="arrow-up" size="20"></u-icon>
				收起
			</view>
		</view>
		<view class="PI-card" v-for="item in PIList" :key="item.id">
			<view class="PI-card-header">
				<span class="PI-card-user">{{item.user}}</span>
				<span class="PI-card-time">{{item.time}}</span>
			</view>
			<view class="PI-card-name">解读名称：{{item.docTitle}}</view>
			<view class="PI-card-footer">
				<view class="PI-card-footer-btn">
					<u-button type="text" size="normal" @click="jumpToDetail(item.id)">查看详情</u-button>
					<view class="clear-both"></view>
				</view>
			</view>
		</view>
		<u-button type="text" @click="getNextPage" v-if="PIList.length < totalSize">加载数据</u-button>
		<view class="info-box" v-else-if="!totalSize">
			没有找到相关结果
		</view>
		<view class="info-box" v-else-if="PIList.length === totalSize">
			没有更多数据了
		</view>
	<movable-area class="fixed-box">
		<movable-view v-if="1" class="fixed-box-btn" direction="all" :inertia="true" @click="scrollToTop" >
			<slot><view>回到</view><view>顶部</view></slot>
		</movable-view>
	</movable-area>
	</view>
</template>

<script>
	export default {
		components: {
		},
		data() {
			return {
				status: 'PUBLIC',
				pageNo: 1,
				pageSize: 10,
				totalSize: 0,
				PIList: [],
				docTitle: '',
				docIdentifier: '',
				docDepart: '',
				showFilter: true
			}
		},
		methods: {
			search () {
				this.PIList = []
				this.pageNo = 1
				this.getPIList()
			},
			clearInput () {
				this.PIList = []
				this.docTitle = ''
				this.docIdentifier = ''
				this.docDepart = ''
				this.pageNo = 1
				this.getPIList()
			},
			changeShowFilter () {
				this.showFilter = !this.showFilter
			},
			getPIList () {
				this.$store.dispatch('getPIList', {
					status: this.status,
					docTitle: this.docTitle || '',
					docIdentifier: this.docIdentifier || '',
					docDepart: this.docDepart || '',
					startTime: this.startTime || '',
					endTime: this.endTime || '',
					pageSize: this.pageSize,
					pageNo: this.pageNo,
					withBody: false
				}).then(res => {
					this.PIList = this.PIList.concat(res.data)
					this.totalSize = res.total
					console.log(this.PIList)
				})
			},
			getNextPage () {
				if (this.PIList.length === this.totalSize) {
					uni.showToast({
						title: '没有更多数据了',
						icon: 'error'
					})
					return
				}
				this.pageNo++
				this.getPIList()				
			},
			jumpToDetail (PIid) {
				uni.navigateTo({
					url: '/pages/PIDetail/PIDetail?id=' + PIid
				})
			},
			scrollToTop () {
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 200,
				})
			}
		},
		onShow () {
			// if (!this.$store.getters.getToken) {
			// 	uni.setStorageSync('redirect', this.$mp.page.route);
			// 	uni.switchTab({
			// 		url: '/pages/Self/Self'
			// 	})
			// } else {
			// 	if (!this.PIList.length) this.getPIList()
			// }
			if (!this.PIList.length) this.getPIList()
		},
		onReachBottom	() {
			this.getNextPage()
		}
	}
</script>

<style lang="less">
	.PIList-container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
		height: 100%;
		box-sizing: border-box;
		.PI-filter {
			display: flex;
			flex-direction: column;
			align-items: center;
			&-input {
				margin-bottom: 20px;
				width: 80%;
			}
			&-btn-box {
				display: flex;
				width: 80%;
				justify-content: space-around;
				.PI-filter-btn {
					width: 40%;
				}
			}
		}
		.PI-filter-control {
			margin: 10px 0;
			&-item {
				font-size: 14px;
				display: flex;
				justify-content: center;
			}
		}
		.PI-card {
			border-bottom: 1px solid #ddd;
			padding: 5px;
			.PI-card-header {
				display: flex;
				justify-content: space-between;
				.PI-card-user {
					font-size: 18px;
					font-weight: 700;
				}
				.PI-card-time {
					color: #888888;
				}
			}
			.PI-card-name {
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp:2;
				-webkit-box-orient: vertical;
			}
			.PI-card-footer {
				text-align: right;
				overflow: hidden;
				.PI-card-footer-btn {
					width: 30%;
					float: right;
				}
			}
		}
		.info-box {
			margin: 20px 0;
			font-size: 18px;
			text-align: center;
		}
		.fixed-box {
			pointer-events: none; // 这里是重点，盒子可穿透操作
			width: 100vw;
			height: 100vh;
			position: fixed;
			left: 0;
			bottom: 0;
			z-index: 100000;
			.fixed-box-btn {
				pointer-events: auto;
				width: 50px;
				height: 50px;
				border-radius: 25px;
				border: 1px solid #ddd;
				font-size: 12px;
				overflow: hidden;
				z-index: 9999;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				left: 85vw;
				top: 90vh;
				background-color: #ffffff;
			}
		}
	}
</style>
