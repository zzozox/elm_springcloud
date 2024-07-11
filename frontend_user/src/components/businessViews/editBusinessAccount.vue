<script setup>
import { ref, onMounted } from 'vue';
import Footer from './businessFooter.vue';
import router from "@/router";
import { useRoute } from "vue-router";
import { post, getUser } from "@/net";
import { ElMessage } from "element-plus";

const route = useRoute();
const user = ref({});
const account = ref({
  userId: null,
  username: '',
  password: '',
  email: '',
  role: 'user',
  registerTime: null,
  userSex: 1,
  userImg: '',
  delTag: 1
});

onMounted(() => {
  user.value = getUser();
  account.value.userId=user.value.id;
  post(`/api/auth/index/${account.value.userId}`, { userId: account.value.userId }, (data) => {
    account.value = data;
  });
});

const editAccount = () => {
  if (account.value.username === '') {
    alert('用户名不能为空！');
    return;
  }
  //不修改邮箱和密码
  // if (account.value.password === '') {
  //   alert('密码不能为空！');
  //   return;
  // }
  // if (account.value.email === '') {
  //   alert('邮箱不能为空！');
  //   return;
  // }
  post(`/api/auth/updateUser`, {
    userId: account.value.userId,
    username: account.value.username,
    password: account.value.password,
    email: account.value.email,
    role: account.value.role,
    registerTime: account.value.registerTime,
    userSex: account.value.userSex,
    userImg: account.value.userImg,
    delTag: account.value.delTag
  }, () => {
    ElMessage.success('修改用户信息成功');
    router.push({ path: '/business/businessLogout'});
  });
};

const handleImageChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      account.value.userImg = e.target.result;
    };
    reader.readAsDataURL(file);
  }
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
      <li class="user-logo" @click="() => $refs.fileInput.click()">
        <img :src="account.userImg" alt="用户头像">
        <input type="file" ref="fileInput" @change="handleImageChange" style="display: none;">
      </li>
      <li>
        <div class="title">用户名：</div>
        <div class="content">
          <input type="text" v-model="account.username" placeholder="用户名">
        </div>
      </li>
      <!--      <li>-->
      <!--        <div class="title">密码：</div>-->
      <!--        <div class="content">-->
      <!--          <input type="password" v-model="account.password" placeholder="密码">-->
      <!--        </div>-->
      <!--      </li>-->
      <!--      <li>-->
      <!--        <div class="title">邮箱：</div>-->
      <!--        <div class="content">-->
      <!--          <input type="email" v-model="account.email" placeholder="邮箱">-->
      <!--        </div>-->
      <!--      </li>-->
      <li>
        <div class="title">角色：</div>
        <div class="content" style="font-size: 3vw;">
          <input type="radio" v-model="account.role" value="user" style="width:6vw;height:3.2vw;" checked>用户
          <input type="radio" v-model="account.role" value="business" style="width:6vw;height:3.2vw;">商家
        </div>
      </li>
      <li>
        <div class="title">性别：</div>
        <div class="content" style="font-size: 3vw;">
          <input type="radio" v-model="account.userSex" value="1" style="width:6vw;height:3.2vw;" checked>男
          <input type="radio" v-model="account.userSex" value="0" style="width:6vw;height:3.2vw;">女
        </div>
      </li>
    </ul>
    <div class="button-add">
      <button @click="editAccount">更新</button>
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
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
}
/****************** 用户头像部分 ******************/
.wrapper .user-logo {
  width: 100%;
  height: 35vw;
  margin-top: 12vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wrapper .user-logo img {
  width: 40vw;
  height: 30vw;
  border-radius: 5px;
}
/*************** 表单信息 ***************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}
.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
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
  padding: 4vw 3vw 0 3vw;
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
