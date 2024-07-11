<template>
  <ul class="footer">
    <li @click="router.push('/user/index')">
      <el-icon><HomeFilled /></el-icon>
      <p>首页</p>
    </li>
    <li>
      <el-icon><View /></el-icon>
      <p>发现</p>
    </li>
    <li @click="router.push('/user/orderList')">
      <el-icon><Fold /></el-icon>
      <p>订单</p>
    </li>
    <li @click="toMe">
      <el-icon><User /></el-icon>
      <p>我的</p>
    </li>
  </ul>
</template>

<script setup>
import router from "@/router";
import {unauthorized} from "@/net";
import {Fold, HomeFilled, User, View} from "@element-plus/icons-vue";
const toMe=()=>{
  if(unauthorized()){
    router.push('/login');
  }else {
    router.push('/user/logout')
  }
}
</script>-->

<style>
.wrapper .footer {
  width: 100%;
  height: 14vw;
  border-top: solid 1px #DDD;
  background-color: #fff;
  position: fixed;
  left: 0;
  bottom: 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 0 10px; /* Adjust the padding as needed */
  z-index: 1000; /* Set z-index to ensure it's above other elements */
}

.wrapper .footer li {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #999;
  user-select: none;
  cursor: pointer;
}

.wrapper .footer li p {
  font-size: 2.8vw;
}

.wrapper .footer li i {
  font-size: 5vw;
}
</style>
