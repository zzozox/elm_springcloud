<script setup>
import Footer from './businessFooter.vue';
import { ref, onMounted } from 'vue';
import router from "@/router";
import { useRoute } from "vue-router";
import { post, getUser } from "@/net";
import { ElMessage } from "element-plus";

const route = useRoute();
const user = ref({});
const food = ref({
  foodId:null,
  foodName:null,
  foodExplain:null,
  foodImg:null,
  foodPrice:null,
  businessId:null,
  createTime:null,
  remarks:null,
  delTag:null
});

onMounted(() => {
  user.value = getUser();
  food.value.businessId = route.query.businessId;
});

const addFood = () => {
  if (food.value.foodName === '') {
    alert('食品名不能为空！');
    return;
  }
  if (food.value.foodExplain === '') {
    alert('食品描述不能为空！');
    return;
  }
  if (food.value.foodImg === '') {
    alert('食品图片不能为空！');
    return;
  }
  if (food.value.foodPrice === '') {
    alert('食品价格不能为空！');
    return;
  }
  post(`/food/createFood`, {
    foodName:food.value.foodName,
    foodExplain:food.value.foodExplain,
    foodImg:food.value.foodImg,
    foodPrice:food.value.foodPrice,
    businessId:food.value.businessId,
    remarks:food.value.remarks,
  }, () => {
    ElMessage.success('新增食品成功')
    router.push({ path: '/business/businessInfoManage', query: { businessId: food.value.businessId } });
  })
};

const handleImageChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      food.value.foodImg = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>编辑食品信息</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li class="business-logo" @click="() => $refs.fileInput.click()">
        <img :src="food.foodImg" alt="食品logo">
        <input type="file" ref="fileInput" @change="handleImageChange" style="display: none;">
      </li>
      <li>
        <div class="title">
          食品名称：
        </div>
        <div class="content">
          <input type="text" v-model="food.foodName" placeholder="食品名称">
        </div>
      </li>
      <li>
        <div class="title">
          食品描述：
        </div>
        <div class="content">
          <input type="text" v-model="food.foodExplain" placeholder="食品描述">
        </div>
      </li>
      <li>
        <div class="title">
          食品价格：
        </div>
        <div class="content">
          <input type="text" v-model="food.foodPrice" placeholder="食品价格">
        </div>
      </li>
    </ul>
    <div class="button-add">
      <button @click="addFood">新增</button>
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
