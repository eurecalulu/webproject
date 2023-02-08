<template>
  <div class="help-container">
    <div class="help-body">
      <h1>用户手册</h1>
      <div>本节将介绍银保监会政策库系统的主要功能和基本使用方法。</div>
      <h1>主要功能</h1>
      <h2>政策解读列表展示</h2>
      <div>本系统可以通过列表形式展示已有的政策解读。政策解读数据主要来自对银保监会官方网站的爬取，此外也可以通过手动添加的方式新增政策解读。系统每日执行定时任务来刷新数据。</div>
      <h2>政策解读管理</h2>
      <div>用户可以通过手动方式新增政策解读，或暂存为草稿。政策解读允许的状态流转包括：草稿=>发布；发布=>废除；草稿=>删除。此外也可以对草稿进行编辑。</div>
      <h2>可视化数据分析</h2>
      <div>系统的首页通过图表的形式展示后台对数据的分析结果。目前主要的分析维度为对词频的统计。</div>
      <h1>基本使用方法</h1>
      <h2>页面切换</h2>
      <div>用户可以通过左侧的导航栏来进行页面跳转；点击顶部导航的书本图标以跳转至首页；鼠标移至头像上可以看到“退出登录”按钮来切换账户。</div>
      <h2>浏览政策解读</h2>
      <div>系统在政策解读列表页的上方提供过滤器，可以设置法规标题、法规文号、发文机构、起止时间，点击“查询”按钮来对政策解读进行筛选。点击过滤器下方的“...”按钮可以显示/隐藏过滤器。</div>
      <div>在列表的底部可以切换表格页，也可以调整页的大小来控制加载数据的条数。</div>
      <h2>查看政策解读详情</h2>
      <div>点击政策解读列表页的某一条数据后“查看”按钮来完整浏览该政策解读，包括解读正文。在细节页面可以按状态执行相应的状态流转操作。如果是草稿状态，可以点击右上角的“编辑”按钮，对需要更改的条目点击标题后的铅笔图标以作出更改。</div>
      <h2>查看政策解读对应法规</h2>
      <div>在政策解读详情页的右侧可以查看该政策解读对应的法规全文。在页面左侧的“解读正文”栏目中选取一段文本，即可由系统计算匹配结果并展示。单击“解读正文”任意位置可取消匹配。</div>
   </div></div>
</template>

<script>
export default {
  name: 'Help',
  data () {
    return {
    }
  }
}
</script>

<style lang="less">
.help-container {
  width: 100%;
  text-align: left;
  padding: 30px;
  background-color: #E9EEF3;
  overflow: auto;
  .help-body {
    padding: 10px;
    background-color: #fff;
    overflow: auto;
    h1 {
      &:nth-child(1) {
        margin-top: 0;
      }
      margin: 30px 0 10px 0;
      font-size: 30px;
      font-weight: 600;
    }
    h2 {
      margin: 10px 0;
      font-size: 20px;
      font-weight: 500;
    }
    div {
      font-size: 14px;
    }
  }
}
</style>
