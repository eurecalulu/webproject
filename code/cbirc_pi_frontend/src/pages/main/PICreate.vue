<template>
  <el-container>
    <el-header>
      政策解读创建
    </el-header>

    <el-main>
      <el-form :model="formData" :rules="rules" ref="formData" label-width="120px" class="demo-formData">
        <el-form-item label="法规名称" prop="docTitle">
          <el-input v-model="formData.docTitle"></el-input>
        </el-form-item>
        <el-form-item label="法规文号" prop="docIdentifier">
          <el-input v-model="formData.docIdentifier"></el-input>
        </el-form-item>
        <el-form-item label="法规发文部门" prop="docDepart">
          <el-input v-model="formData.docDepart"></el-input>
        </el-form-item>
        <el-form-item label="政策解读部门" prop="interpretDepart">
          <el-input v-model="formData.interpretDepart"></el-input>
        </el-form-item>
        <el-form-item label="政策解读标题" prop="interpretTitle">
          <el-input v-model="formData.interpretTitle"></el-input>
        </el-form-item>
        <el-form-item label="政策解读摘要" prop="interpretAbstract">
          <el-input type="textarea" v-model="formData.interpretAbstract"></el-input>
        </el-form-item>
        <el-form-item label="政策解读内容" prop="interpretBody">
          <el-input type="textarea" v-model="formData.interpretBody"></el-input>
        </el-form-item>
        <el-form-item style="text-align: right">
          <el-button type="primary" @click="submitForm('PUBLIC')">创建</el-button>
          <el-button @click="resetForm()">重置</el-button>
          <el-button type="success" @click="submitForm('DRAFT')">存为草稿</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import moment from 'moment'
export default {
  data () {
    return {
      formData: {
        docTitle: '',
        docIdentifier: '',
        docDepart: '',
        interpretDepart: '',
        interpretTitle: '',
        interpretAbstract: '',
        interpretBody: ''
      },
      rules: {
        docTitle: [{
          required: true,
          message: '请输入法规名称',
          trigger: 'blur'
        }],
        docIdentifier: [{
          required: true,
          message: '请输入法规文号',
          trigger: 'blur'
        }],
        docDepart: [{
          required: true,
          message: '请输入法规发文部门',
          trigger: 'blur'
        }],
        interpretDepart: [{
          required: true,
          message: '请输入政策解读部门',
          trigger: 'blur'
        }],
        interpretTitle: [{
          required: true,
          message: '请输入政策解读标题',
          trigger: 'blur'
        }],
        interpretAbstract: [{
          required: true,
          message: '请输入政策解读摘要',
          trigger: 'blur'
        }],
        interpretBody: [{
          required: true,
          message: '请输入政策解读正文',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    submitForm (status) {
      this.$refs['formData'].validate((valid) => {
        if (valid) {
          this.$store.dispatch('createPI', {
            ...this.formData,
            time: moment().format('yyyy-MM-DD'),
            status
          }).then(res => {
            if (res.data && res.data.success) {
              this.$message({
                message: '创建成功！',
                type: 'success'
              })
              this.$router.push({
                name: 'PIList',
                params: {
                  status
                }
              })
            }
          }).catch(err => {
            console.log(err)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm () {
      this.$refs['formData'].resetFields()
    }
  }
}

</script>

<style lang="less" scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  display: flex;
  justify-content: center;
}

.el-header {
  display: flex;
  justify-content: center;
  align-items: center;
}

.demo-formData {
  width: 70%;
}
</style>
