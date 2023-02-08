<template>
  <!-- <el-container class="detail-container">
    <el-main>
      <el-row>
        <el-col :span="18">
        </el-col>
        <el-col :span="6">
          <div style="background-color: white; height: 399px;position: fixed;width: 80%;"></div>
        </el-col>
      </el-row>
    </el-main>

  </el-container> -->
  <div class="detail-container">
    <div class="detail-container-left">
      <div class="detail-container-left-box">
        <el-descriptions class="margin-top" :column="2" border direction="vertical">
          <template slot="title">
            <el-button type="text" icon="el-icon-arrow-left" @click="goback">返回</el-button>
          </template>
          <template slot="extra" v-if="state === 'view'">
            <el-button type="primary" v-if="detail.status === 'DRAFT'" @click="edit">编辑</el-button>
            <el-button type="primary" v-if="detail.status === 'DRAFT'" @click="publish">发布</el-button>
            <el-button type="danger" v-if="detail.status === 'DRAFT'" @click="del">删除</el-button>
            <el-button type="danger" v-if="detail.status === 'PUBLIC'" :disabled="detail.user !== $store.getters.getCurrentUserName" @click="abolish">废止</el-button>
          </template>
          <template slot="extra" v-if="state === 'edit'">
            <el-button type="primary" @click="update">保存</el-button>
          </template>
          <el-descriptions-item span="2">
            <template slot="label">
              <i class="el-icon-star-on"></i>
              法规标题
              <i class="el-icon-edit" @click="handleShowInput('docTitle')" v-if="state === 'edit' && !showInput.docTitle"></i>
            </template>
            <div v-if="state === 'view'">
              {{ detail.docTitle }}
              <el-tag v-if="detail.status === 'PUBLIC'" effect='plain'>已发布</el-tag>
              <el-tag v-if="detail.status === 'DRAFT'" effect='plain' type="success">草稿</el-tag>
              <el-tag v-if="detail.status === 'ABOLISH'" effect='plain' type="danger">已废止</el-tag>
            </div>
            <div v-else>
              <div v-if="!showInput.docTitle">
                {{ detail.docTitle || '无' }}
              </div>
              <el-input v-model="detail.docTitle" v-else size="small"></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-promotion"></i>
              法规文号
              <i class="el-icon-edit" @click="handleShowInput('docIdentifier')" v-if="state === 'edit' && !showInput.docIdentifier"></i>
            </template>
            <div v-if="state === 'view'">
              {{ detail.docIdentifier || '无' }}
            </div>
            <div v-else>
              <div v-if="!showInput.docIdentifier">
                {{ detail.docIdentifier }}
              </div>
              <el-input v-model="detail.docIdentifier" v-else size="small"></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location"></i>
              发文部门
              <i class="el-icon-edit" @click="handleShowInput('docDepart')" v-if="state === 'edit' && !showInput.docDepart"></i>
            </template>
            <div v-if="state === 'view'">
              {{ detail.docDepart || '无' }}
            </div>
            <div v-else>
              <div v-if="!showInput.docDepart">
                {{ detail.docDepart }}
              </div>
              <el-input v-model="detail.docDepart" v-else size="small"></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item span="2">
            <template slot="label">
              <i class="el-icon-star-on"></i>
              解读标题
              <i class="el-icon-edit" @click="handleShowInput('interpretTitle')" v-if="state === 'edit' && !showInput.interpretTitle"></i>
            </template>
            <div v-if="state === 'view'">
              {{ detail.interpretTitle || '无' }}
            </div>
            <div v-else>
              <div v-if="!showInput.interpretTitle">
                {{ detail.interpretTitle }}
              </div>
              <el-input v-model="detail.interpretTitle" v-else size="small"></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location"></i>
              解读部门
              <i class="el-icon-edit" @click="handleShowInput('interpretDepart')" v-if="state === 'edit' && !showInput.interpretDepart"></i>
            </template>
            <div v-if="state === 'view'">
              {{ detail.interpretDepart || '无' }}
            </div>
            <div v-else>
              <div v-if="!showInput.interpretDepart">
                {{ detail.interpretDepart }}
              </div>
              <el-input v-model="detail.interpretDepart" v-else size="small"></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              录入人
            </template>
            {{ detail.user }}
          </el-descriptions-item>
          <el-descriptions-item  span="2">
            <template slot="label">
              <i class="el-icon-document"></i>
              解读摘要
              <i class="el-icon-edit" @click="handleShowInput('interpretAbstract')" v-if="state === 'edit' && !showInput.interpretAbstract"></i>
            </template>
            <div v-if="state === 'view'" class="detail-text">
              {{ detail.interpretAbstract || '无' }}
            </div>
            <div v-else>
              <div v-if="!showInput.interpretAbstract" class="detail-text">
                {{ detail.interpretAbstract }}
              </div>
              <el-input v-model="detail.interpretAbstract" v-else size="small" type="textarea" autosize></el-input>
            </div>
          </el-descriptions-item>
          <el-descriptions-item span="2">
            <template slot="label">
              <i class="el-icon-document"></i>
              解读正文
              <i class="el-icon-edit" @click="handleShowInput('interpretBody')" v-if="state === 'edit' && !showInput.interpretBody"></i>
            </template>
            <div v-if="state === 'view'" ref="detailTextBox">
              <div class="detail-text" v-for="paragraph in detail.interpretBody" :key="paragraph" >
                {{paragraph}}
              </div>
            </div>
            <div v-else>
              <div v-if="!showInput.interpretBody" >
                <div class="detail-text" v-for="paragraph in detail.interpretBody" :key="paragraph">
                  {{paragraph}}
                </div>
              </div>
              <el-input v-model="inputInterpretBody" v-else size="small" type="textarea" autosize></el-input>
            </div>
          </el-descriptions-item>
        </el-descriptions>
        <div class="detail-comment-box" v-if="state === 'view'">
          <div class="detail-comment-box-title">
            评论区
          </div>
          <div class="detail-comment-box-empty" v-if="comments.length === 0"> 暂无评论</div>
          <div class="detail-comment-box-card" v-for="comment in comments" :key="comment.id">
            <div class="detail-comment-box-card-header">
              {{comment.userInfo.name}} <span class="detail-comment-box-card-time"> {{comment.time}} </span>
            </div>
            <div class="detail-comment-box-card-ref" v-if="comment.ref">
              {{comment.ref.userInfo.name}}: {{comment.ref.comment}}
            </div>
            <div class="detail-comment-box-card-body">
              {{comment.comment}}
            </div>
            <div class="detail-comment-box-card-footer">
              <span @click="jumpToCreate(comment)"><i class="el-icon-edit"></i> 回复</span>
              <el-popconfirm title="确定删除本条评论吗？" @confirm="deleteComment(comment.id)">
                <span slot="reference" v-if="comment.userInfo.name === $store.getters.getCurrentUserName" >
                <i class="el-icon-delete"></i> 删除评论</span>
              </el-popconfirm>
            </div>
          </div>
          <div class="detail-comment-box-create" ref="myComment">
            <div class="detail-comment-box-create-title">
              发表回复
            </div>
            <div class="detail-comment-box-create-ref" v-if="myCommentRef.comment">
              <div>引用 {{myCommentRef.userInfo.name}} 发表的：</div>
              <div>{{myCommentRef.comment}}</div>
            </div>
            <el-input type="textarea" v-model="myCommentContent" :autosize="{minRows: 2}" placeholder="编辑你的回复"></el-input>
            <div class="detail-comment-box-create-btn">
              <el-button type="primary" @click="addComment">发表</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="detail-container-right" v-if="state === 'view' && detail.status==='PUBLIC'">
      <div class="detail-container-right-title">
        {{ showRelativeText ? '对应法规' : '匹配结果' }}
        <el-button @click="handleShowRelativeText" type="text" style="float: right;" v-show="!showInputRelativeText">切换视图</el-button>
      </div>
      <div class="doc-body-box" v-html="relativeText" ref="docBodyBox" v-show="showRelativeText && !showInputRelativeText"></div>
      <div class="doc-body-box" v-show="showRelativeText && showInputRelativeText">
        <el-input type="textarea" :autosize="{minRows: 15}" v-model="inputRelativeText"></el-input>
      </div>
      <div class="detail-container-right-btn-box" v-show="showRelativeText">
        <el-button class="detail-container-right-btn" type="primary" v-if="selectedText" @click="jumpToHighlight(false)">上一处</el-button>
        <el-button class="detail-container-right-btn" type="primary" v-if="selectedText" @click="jumpToHighlight(true)">下一处</el-button>
        <el-button class="detail-container-right-btn" type="primary" :disabled="detail.user !== $store.getters.getCurrentUserName" v-if="!selectedText && !showInputRelativeText" @click="editRelativeText">编辑</el-button>
        <el-button class="detail-container-right-btn" type="primary" v-if="showInputRelativeText" @click="saveEditRelativeText">保存</el-button>
        <el-button class="detail-container-right-btn" type="danger" v-if="showInputRelativeText" @click="cancelEditRelativeText">取消</el-button>
      </div>
      <div class="doc-body-box" v-show="!showRelativeText">
        <div v-if="!matchItems.length">暂无匹配结果</div>
        <div class="doc-body-box-result-item" v-for="item in matchItems" :key="item">{{item}}<div><br></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      state: '',
      detail: {},
      showInput: {
        docTitle: false,
        docIdentifier: false,
        docDepart: false,
        interpretTitle: false,
        interpretDepart: false,
        interpretAbstract: false,
        interpretBody: false
      },
      inputInterpretBody: '',
      selectedText: '',
      comments: [],
      myCommentContent: '',
      myCommentRef: {},
      relativeText: '',
      inputRelativeText: '',
      showInputRelativeText: false,
      matchItems: [],
      currentNumberOfHighlight: 0,
      showRelativeText: true
    }
  },
  mounted () {
    this.state = this.$route.params.state
    this.$store.dispatch('getPIByID', this.$route.params.id).then(res => {
      this.detail = res.data
      this.inputInterpretBody = this.detail.interpretBody
      this.detail.interpretBody = this.detail.interpretBody.split('\n')
      this.relativeText = '<span class="text-indent"></span>' + this.detail.docBody.replaceAll('<highlight>', '<span class="highlight">').replaceAll('</highlight>', '</span>').replaceAll('\n', '<br><span class="text-indent"></span>')
      this.inputRelativeText = this.detail.docBody
      this.$refs.detailTextBox.addEventListener('mouseup', this.retrieval, true)
    })
    this.getComments()
  },
  beforeDestroy () {
    if (this.detail.status === 'PUBLIC') this.$refs.detailTextBox.removeEventListener('mouseup', this.retrieval, true)
  },
  watch: {
    '$route' (to, from) {
      this.state = this.$route.params.state
      for (let key in this.showInput) {
        this.showInput[key] = false
      }
    }
  },
  methods: {
    getComments () {
      this.$store.dispatch('getCommentsById', this.$route.params.id).then(res => {
        this.comments = res.data
      })
    },
    jumpToCreate (comment) {
      this.myCommentRef = comment
      let container = document.querySelector('.el-main')
      container.scrollTop = this.$refs.myComment.offsetTop
    },
    deleteComment (commentId) {
      if (commentId === this.myCommentRef.id) this.myCommentRef = {}
      this.$store.dispatch('deleteComment', commentId).then(res => {
        if (res.data && res.data.success) {
          this.$message({
            message: '删除成功！',
            type: 'success'
          })
        } else {
          this.$message({
            message: res.data.message,
            type: 'error'
          })
        }
        this.getComments()
      })
    },
    addComment () {
      this.$store.dispatch('addComment', {
        comment: this.myCommentContent,
        piId: this.detail.id,
        refCommentId: this.myCommentRef.id || 0
      }).then(res => {
        if (res.data && res.data.success) {
          this.$message({
            message: '评论成功！',
            type: 'success'
          })
        } else {
          this.$message({
            message: res.data.message,
            type: 'error'
          })
        }
        this.myCommentContent = ''
        this.myCommentRef = {}
        this.getComments()
      })
    },
    goback () {
      if (this.haveChanged()) {
        this.$confirm('您在编辑后还没有保存，是否先进行保存？', '提示', {
          confirmButtonText: '保存并离开',
          cancelButtonText: '直接离开',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('updatePI', {
            ...this.detail,
            interpretBody: this.inputInterpretBody
          }).then(res => {
            if (res) {
              this.$router.push({
                path: `/pi/${this.detail.status.toLowerCase()}`
              })
            }
          }).catch(err => {
            console.log(err)
          })
        }).catch(() => {
          this.$router.push({
            path: `/pi/${this.detail.status.toLowerCase()}`
          })
        })
      } else {
        this.$router.push({
          path: `/pi/${this.detail.status.toLowerCase()}`
        })
      }
    },
    edit () {
      this.$router.push({
        name: 'PIDetail',
        params: {
          id: this.$route.params.id,
          state: 'edit'
        }
      })
    },
    abolish () {
      this.$confirm('此操作将废止该政策解读，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('updatePIStatus', {
          id: this.detail.id,
          status: 'ABOLISH'
        }).then(res => {
          this.$message({
            type: 'success',
            message: '废止成功！'
          })
          this.detail.status = 'ABOLISH'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消废止'
        })
      })
    },
    publish () {
      this.$store.dispatch('updatePIStatus', {
        id: this.detail.id,
        status: 'PUBLIC'
      }).then(res => {
        this.$message({
          type: 'success',
          message: '发布成功'
        })
        this.detail.status = 'PUBLIC'
      })
    },
    update () {
      if (!this.haveChanged()) {
        this.$message({
          message: '保存成功',
          type: 'success'
        })
        this.$router.push({
          name: 'PIDetail',
          params: {
            id: this.$route.params.id,
            state: 'view'
          }
        })
        return
      }
      this.$store.dispatch('updatePI', {
        ...this.detail,
        interpretBody: this.inputInterpretBody
      }).then(res => {
        if (res) {
          this.$message({
            message: '保存成功',
            type: 'success'
          })
          this.$router.push({
            name: 'PIDetail',
            params: {
              id: this.$route.params.id,
              state: 'view'
            }
          })
        }
      })
    },
    del () {
      this.$confirm('此操作将删除该政策解读，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('deletePI', {
          id: this.detail.id
        }).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功！'
          })
          this.$router.push({
            name: 'PIDraft'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消废止'
        })
      })
    },
    handleShowInput (param) {
      this.showInput[param] = true
    },
    haveChanged () {
      for (let key in this.showInput) {
        if (this.showInput[key]) return true
      }
      return false
    },
    handleShowRelativeText () {
      this.showRelativeText = !this.showRelativeText
    },
    editRelativeText () {
      this.showInputRelativeText = true
    },
    saveEditRelativeText () {
      this.$store.dispatch('updatePIDocBody', {
        id: this.detail.id,
        docText: this.inputRelativeText
      }).then(res => {
        if (res.data.success) {
          this.$message({
            message: res.data.message || '更新成功',
            type: 'success'
          })
          this.detail.docBody = this.inputRelativeText
          this.relativeText = '<span class="text-indent"></span>' + this.detail.docBody.replaceAll('<highlight>', '<span class="highlight">').replaceAll('</highlight>', '</span>').replaceAll('\n', '<br><span class="text-indent"></span>')
        }
        this.showInputRelativeText = false
      })
    },
    cancelEditRelativeText () {
      this.$confirm('此操作将放弃所做更改，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.inputRelativeText = this.detail.docBody
        this.showInputRelativeText = false
      })
    },
    retrieval (e) {
      if (window.getSelection().toString() === this.selectedText || this.showInputRelativeText) return
      this.selectedText = window.getSelection().toString()
      if (!this.selectedText) {
        this.relativeText = '<span class="text-indent"></span>' + this.detail.docBody.replaceAll('<highlight>', '<span class="highlight">').replaceAll('</highlight>', '</span>').replaceAll('\n', '<br><span class="text-indent"></span>')
        return
      }
      this.$store.dispatch('retrieval', {
        piId: this.detail.id,
        text: this.selectedText
      }).then(res => {
        if (res.data.found) {
          let relativeText = res.data.policyDoc
          relativeText = '<span class="text-indent"></span>' + relativeText.replaceAll('<highlight>', '<span class="highlight">').replaceAll('</highlight>', '</span>').replaceAll('\n', '<br><span class="text-indent"></span>')
          this.relativeText = relativeText
          this.matchItems = res.data.policyMatchItems
          if (this.showRelativeText) this.scrollTo(this.$refs.docBodyBox, -2)
          this.$message({
            message: res.data.message || '匹配成功',
            type: 'success'
          })
        } else {
          this.relativeText = res.data.message || '暂无匹配结果'
          this.matchItems = []
          this.$message({
            message: res.data.message || '暂无匹配结果',
            type: 'error'
          })
        }
      })
    },
    jumpToHighlight (ifNext) {
      if (ifNext) this.scrollTo(this.$refs.docBodyBox, ++this.currentNumberOfHighlight)
      else this.scrollTo(this.$refs.docBodyBox, --this.currentNumberOfHighlight)
    },
    scrollTo (dom, numberOfHighlight) {
      let fromScrollTop = dom.scrollTop
      let toScrollTop = dom.querySelectorAll('.highlight')
      if (!toScrollTop.length) {
        toScrollTop = 0
      } else if (numberOfHighlight === -2) {
        // 回顶部
        this.currentNumberOfHighlight = 0
        toScrollTop = 0
      } else if (numberOfHighlight <= 0) {
        // 越界，滚动到最下面一个
        this.currentNumberOfHighlight = toScrollTop.length
        numberOfHighlight = toScrollTop.length - 1
        toScrollTop = toScrollTop[numberOfHighlight].offsetTop - 60
      } else if (numberOfHighlight > toScrollTop.length) {
        // 越界，回到顶部，计数置为1
        this.currentNumberOfHighlight = 1
        toScrollTop = toScrollTop[0].offsetTop - 60
      } else toScrollTop = toScrollTop[numberOfHighlight - 1].offsetTop - 60
      // 滑动逻辑
      if (toScrollTop - fromScrollTop === 0) return false
      else if (toScrollTop > fromScrollTop) {
        // 下滑
        let steep = (toScrollTop - fromScrollTop) / 2000
        let timer = setInterval(() => {
          fromScrollTop += steep
          // 步进值不改变的话会匀速缓慢滑动，不断增加步进值滑动的距离增加，视觉效果速度变快
          steep += 5
          if (fromScrollTop >= toScrollTop) {
            clearInterval(timer)
            fromScrollTop = toScrollTop
          }
          // 由于是加在box上的滚动，直接用window.scrollTop无效，查看了getScrollTop方法滚动的元素，所以加在box上
          dom.scrollTop = fromScrollTop
        }, 10)
      } else {
        // 上滑
        let steep = (fromScrollTop - toScrollTop) / 2000
        let timer = setInterval(() => {
          fromScrollTop -= steep
          // 步进值不改变的话会匀速缓慢滑动，不断增加步进值滑动的距离增加，视觉效果速度变快
          steep += 5
          if (fromScrollTop <= toScrollTop) {
            clearInterval(timer)
            fromScrollTop = toScrollTop
          }
          // 由于是加在box上的滚动，直接用window.scrollTop无效，查看了getScrollTop方法滚动的元素，所以加在box上
          dom.scrollTop = fromScrollTop
        }, 10)
      }
    }
  }
}
</script>

<style lang="less">
.detail-container {
  position: relative;
  background-color: #E9EEF3;
  height: 100%;
  flex: 1 0 900px;
  box-sizing: border-box;
  .detail-container-left {
    height: 100%;
    overflow: auto;
    box-sizing: border-box;
    padding: 20px;
  }
  .detail-container-left-box {
    width: 65%;
    margin: 20px;
    box-sizing: border-box;
  }
  .detail-container-right {
    position: absolute;
    top: 40px;
    right: 40px;
    height: 90%;
    width: 30%;
    box-sizing: border-box;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    &-title {
      padding: 10px;
      font-size: 24px;
      font-weight: 700;
      text-align: left;
    }
    .doc-body-box {
      padding: 10px;
      flex: 1 0 500px;
      text-align: left;
      overflow-y: auto;
      overflow-x: hidden;
      .highlight {
        background-color: #f0ff1e;
      }
      .text-indent {
        margin-left: 2em;
      }
      &-result-item {
        &:nth-child(1) {
          color: red;
        }
        &:nth-child(2) {
          color: rgb(255, 72, 0);
        }
        &:nth-child(3) {
          color: orange;
        }
      }
    }
    &-btn-box {
      padding: 10px;
    }
  }
}
.detail-text {
  text-indent: 24px;
}
.el-icon-edit {
  cursor: pointer;
}
.detail-comment-box {
  margin: 50px 0;
  background-color: #fff;
  padding: 10px;
  text-align: left;
  &-title {
    font-size: 24px;
    font-weight: 700;
  }
  &-empty {
    padding-top: 5px;
    color: #aaa;
  }
  &-card {
    border-bottom: 1px solid #ccc;
    text-align: left;
    padding-left: 5px;
    &-header {
      padding: 5px 0;
    }
    &-time {
      font-size: 14px;
      color: #aaa;
    }
    &-ref {
      background-color: #eee;
      color: #888;
      padding: 5px;
    }
    &-body {
      padding: 5px 0;
    }
    &-footer {
      cursor: pointer;
      font-size: 12px;
      color: #888;
      padding: 5px 0;
    }
  }
  &-create {
    text-align: left;
    &-title {
      padding: 5px 0;
      font-weight: 700;
    }
    &-ref {
      background-color: #eee;
      padding: 5px;
      margin-bottom: 10px;
      color: #888;
    }
    &-btn {
      text-align: right;
      padding-top: 10px;
    }
  }
}
</style>
