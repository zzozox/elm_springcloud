<script setup>
import Footer from "../userViews/Footer.vue";
import {User, Lock} from '@element-plus/icons-vue'
import router from "@/router";
import {reactive, ref} from "vue";
import {login} from '@/net'

const formRef = ref()
const form = reactive({
  username: '',
  password: '',
  remember: false
})

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur'}
  ]
}

function userLogin() {
  formRef.value.validate((isValid) => {
    if(isValid) {
      login(form.username, form.password, form.remember, (data) => {
        if(data && data.role) {
          if(data.role === 'user'){
            router.push("/index")
          } else if(data.role === 'business'){
            router.push("/businessIndex")
          } else {
            console.error("Unknown role:", data.role);
          }
        } else {
          console.error("Invalid login response:", data);
        }
      }, (error) => {
        console.error("Login failed:", error);
      });
    }
  });
}
</script>

<template>
  <div class="login-container">
    <header class="header">
      <p>用户登录</p>
    </header>
    <div class="welcome-message">
      <div class="sub-text">欢迎登录饿了么小程序</div>
    </div>
    <div class="form-container">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" maxlength="10" type="text" placeholder="用户名/邮箱">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" maxlength="20" placeholder="密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12" style="text-align: left">
            <el-form-item prop="remember">
              <el-checkbox v-model="form.remember" label="记住我"/>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-link @click="router.push('/forget')">忘记密码？</el-link>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="login-button">
      <el-button @click="userLogin()" type="success" plain>立即登录</el-button>
    </div>
    <el-divider>
      <span class="no-account-text">没有账号</span>
    </el-divider>
    <div class="register-button">
      <el-button @click="router.push('/register')" type="warning" plain>注册账号</el-button>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  text-align: center;
  margin: 0 20px;
}
.header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}
.welcome-message {
  margin-top: 150px;
  font-size: 14px;
  color: grey;
}
.form-container {
  margin-top: 50px;
}
.login-button {
  margin-top: 40px;
}
.no-account-text {
  color: grey;
  font-size: 13px;
}
.register-button {
  margin-top: 10px;
}
</style>
