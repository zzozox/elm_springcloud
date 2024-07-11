<script setup>
import Footer from './businessFooter.vue';
import { ref, onMounted } from 'vue';
import router from "@/router";
import { post, getUser } from "@/net";
import { ElMessage } from "element-plus";

const user = ref({});
const business = ref({
  businessName: null,
  businessAddress: null,
  businessExplain: null,
  businessImg: null,
  orderTypeId: null,
  starPrice: null,
  deliveryPrice: null,
  userId: null,
  remarks: null,
});

const orderTypes = ref([
  { id: 1, name: '美食' },
  { id: 2, name: '早餐' },
  { id: 3, name: '跑腿代购' },
  { id: 4, name: '汉堡披萨' },
  { id: 5, name: '甜品饮品' },
  { id: 6, name: '速食简餐' },
  { id: 7, name: '地方小吃' },
  { id: 8, name: '米粉面馆' },
  { id: 9, name: '包子粥铺' },
  { id: 10, name: '炸鸡炸串' }
]);

onMounted(() => {
  user.value = getUser();
  business.value.userId=user.value.id;
});

const addBusiness = () => {
  if (business.value.businessName === '') {
    alert('门店名不能为空！');
    return;
  }
  if (business.value.businessAddress === '') {
    alert('门店地址不能为空！');
    return;
  }
  if (business.value.businessExplain === '') {
    alert('门店描述不能为空！');
    return;
  }
  if (business.value.businessImg === '') {
    alert('门店图片不能为空！');
    return;
  }
  if (business.value.orderTypeId === 0) {
    alert('门店类型不能为空！');
    return;
  }
  if (business.value.starPrice === '') {
    alert('起送费不能为空！');
    return;
  }
  if (business.value.deliveryPrice === '') {
    alert('配送费不能为空！');
    return;
  }
  post(`/business/createBusiness`, {
    businessName: business.value.businessName,
    businessAddress: business.value.businessAddress,
    businessExplain: business.value.businessExplain,
    businessImg: business.value.businessImg,
    orderTypeId: business.value.orderTypeId,
    starPrice: business.value.starPrice,
    deliveryPrice: business.value.deliveryPrice,
    userId: business.value.userId,
    remarks: business.value.remarks,
    delTag: 1
  }, () => {
    ElMessage.success('修改门店信息成功')
    router.push({ path: '/business/myBusiness' });
  })
};

const handleImageChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      business.value.businessImg = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>编辑门店信息</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li class="business-logo" @click="() => $refs.fileInput.click()">
        <img :src="business.businessImg" alt="商家logo">
        <input type="file" ref="fileInput" @change="handleImageChange" style="display: none;">
      </li>
      <li>
        <div class="title">
          门店名称：
        </div>
        <div class="content">
          <input type="text" v-model="business.businessName" placeholder="门店名称">
        </div>
      </li>
      <li>
        <div class="title">
          门店地址：
        </div>
        <div class="content">
          <input type="text" v-model="business.businessAddress" placeholder="门店地址">
        </div>
      </li>
      <li>
        <div class="title">
          门店描述：
        </div>
        <div class="content">
          <input type="text" v-model="business.businessExplain" placeholder="门店描述">
        </div>
      </li>
      <li>
        <div class="title">
          门店类型：
        </div>
        <div class="content">
          <el-select v-model="business.orderTypeId" placeholder="选择门店类型" style="width: 100%;">
            <el-option v-for="type in orderTypes" :key="type.id" :label="type.name" :value="type.id"></el-option>
          </el-select>
        </div>
      </li>
      <li>
        <div class="title">
          起送费：
        </div>
        <div class="content">
          <input type="text" v-model="business.starPrice" placeholder="起送费">
        </div>
      </li>
      <li>
        <div class="title">
          配送费：
        </div>
        <div class="content">
          <input type="text" v-model="business.deliveryPrice" placeholder="配送费">
        </div>
      </li>
    </ul>
    <div class="button-add">
      <button @click="addBusiness">新建</button>
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
/****************** 商家logo部分 ******************/
.wrapper .business-logo {
  width: 100%;
  height: 35vw;
  margin-top: 12vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wrapper .business-logo img {
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
.wrapper .form-box li .content input,
.wrapper .form-box li .content el-select {
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
</style>
