<script setup>
import Footer from './businessFooter.vue';
import {ref,onMounted} from "vue";
import { logout,post ,getUser} from '@/net'
import router from "@/router";
import {Edit, House, Setting} from "@element-plus/icons-vue";
const userData=ref({});
const user=ref({});
onMounted(()=>{
  user.value=getUser();
  console.log('user:',user.value);
  post(`/api/auth/index/${user.value.id}`,{userId:user.value.id},(data)=>{userData.value=data;})
})
function userLogout() {
  logout(() => router.push("/"))
}
function getUserSexLabel(userSex) {
  return userSex === 1 ? "男" : "女";
}
</script>


<template>
  <div class="profile-container">
    <div class="profile-header">
      <div class="profile-username">{{ userData.username }}</div>
      <div class="header-buttons">
        <el-icon @click="router.push('/business/editBusinessAccount')" class="header-icon"><Edit /></el-icon>
      </div>
    </div>
    <div class="profile-content">
      <div class="profile-image-container">
        <img :src="userData.userImg" alt="用户头像" class="profile-image">
      </div>
      <div class="profile-details">
        <div class="info-box">
          <span class="info-label">Email:</span>
          <span class="info-value">{{ userData.email }}</span>
        </div>
        <div class="info-box">
          <span class="info-label">性别:</span>
          <span class="info-value">{{ getUserSexLabel(userData.userSex) }}</span>
        </div>
      </div>
    </div>
    <el-button @click="userLogout" class="logout-button">登出</el-button>
  </div>
  <Footer></Footer>
</template>

<style scoped>
.profile-container {
  text-align: center;
  padding: 20px;
}

.profile-header {
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

.profile-username {
  font-weight: bold;
}

.profile-content {
  margin-top: 60px;
}

.profile-image-container {
  margin-bottom: 20px;
}

.profile-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  border: 3px solid #3498db;
}

.profile-details {
  text-align: left;
}

.info-box {
  background-color: rgba(52, 152, 219, 0.1);
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
}

.info-label {
  font-weight: bold;
  margin-right: 5px;
}

.logout-button {
  background-color: #e74c3c;
  color: #fff;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  margin-top: 20px;
}

.address-link {
  text-decoration: none;
  color: #3498db;
  font-size: 16px;
  display: block;
  margin-top: 10px;
}

.header-buttons {
  display: flex;
  position: absolute;
  top: 10px;
  right: 10px;
}

.header-buttons {
  display: flex;
  position: absolute;
  top: 10px;
  right: 10px;
}

.header-icon {
  color: #fff;
  font-size: 20px;
  cursor: pointer;
  flex: 0 0 auto; /* 这里是关键，使得图标不会缩小，而保持原始大小 */
  margin-right: 10px; /* 你可以调整这里的数值来增加两个图标之间的距离 */
}
/****************** footer部分 ******************/
footer {
  width: 100%;
  height: 14vw;
  position: fixed;
  left: 0;
  bottom: 0;
  z-index: 1000;
}
</style>
