<script setup>
import { ref, onMounted } from 'vue';
import router  from "@/router";
import {post,getUser} from "@/net";

const user = ref();
const userData=ref({});

onMounted(() => {
  user.value = getUser();
  post(`/api/auth/index/${user.value.id}`,{userId:user.value.id},(data)=>{userData.value=data;})
});

const editUser = () => {
  if (userData.value.username === '') {
    alert('用户姓名不能为空！');
    return;
  }
  if (userData.value.password === '') {
    alert('密码不能为空！');
    return;
  }
  post(`/api/auth/updateUser`, {
    userId:user.value.id,
    username:userData.value.username,
    userSex:userData.value.userSex,
    userImg:userData.value.userImg,
    password:userData.value.password,
    },()=>{
    router.push("/logout");
  })
};

const imgInput=ref(null)
const uploadImg = (event) => {
  const img = event.target.files[0];
  if (!img) return;
  userData.value.userImg = URL.createObjectURL(img);
};

const triggerInput = () => {
  imgInput.value.click();
};
</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>编辑用户信息</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div>
          <input type="file" accept="accept=.jpg,.png" id="userData.value.userImg" @change="uploadImg" ref="imgInput" />
          <button class="upload-button" @click="triggerInput"></button>
          <img v-if="userData.value.userImg" :src="userData.value.userImg" alt="User Image" />
        </div>
      </li>
      <li>
        <div class="title">
          用户名：
        </div>
        <div class="content">
          <input type="text" v-model="user.value.username" placeholder="用户姓名">
        </div>
      </li>
      <li>
        <div class="title">
          性别：
        </div>
        <div class="content" style="font-size: 3vw;">
          <input type="radio" v-model="userData.value.userSex" value="1" style="width:6vw;height:3.2vw;" checked>男
          <input type="radio" v-model="userData.value.userSex" value="0" style="width:6vw;height:3.2vw;">女
        </div>
      </li>
      <li>
        <div class="title">
          密码：
        </div>
        <div class="content">
          <input type="tel" v-model="userData.value.password" placeholder="密码">
        </div>
      </li>
    </ul>
    <div class="button-add">
      <button @click="editUser">更新</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>


<style scoped>
/*************** 总容器 ***************/
.wrapper {
  width: 100%;
  height: 100%;
}
/*************** header ***************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  /*保证在最上层*/
  z-index: 1000;
}
/*************** （表单信息） ***************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}
.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0vw 3vw;
  display: flex;
}
.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}
.wrapper .form-box li .content {
  flex: 1;
  display: flex;
  align-items: center;
}
.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}
.wrapper .button-add {
  box-sizing: border-box;
  padding: 4vw 3vw 0vw 3vw;
}
.wrapper .button-add button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  border: none;
  outline: none;
  border-radius: 4px;
  color: #fff;
  background-color: #38CA73;
}
</style>
