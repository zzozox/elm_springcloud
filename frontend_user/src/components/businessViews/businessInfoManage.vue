<script setup>
import { ref, onMounted, reactive } from 'vue';
import { post, getUser } from '@/net';
import { useRoute } from "vue-router";
import Footer from './businessFooter.vue';
import router from "@/router";
import { Delete, Edit, Plus } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";

const route = useRoute();
const user = ref(null);
const businessId = ref(null);
const business = ref({});
const foodArr = ref([]);
const cartArr = reactive([]);
const cart = ref({});

onMounted(() => {
  user.value = getUser();
  businessId.value = route.query.businessId;
  // 根据 businessId 查询商家信息
  post(`/business/byBusinessId/${businessId.value}`, { businessId: businessId.value }, (data) => {
    business.value = data;
    listFoodArr(businessId.value);
  });
});

const listFoodArr=(businessId)=>{
  // 根据 businessId 查询所属食品信息
  post(`/food/byBusinessId/${businessId}`, { businessId: businessId }, (data) => {
    foodArr.value = data;
  });
}

const editBusiness = () => {
  router.push({ path: '/editBusiness', query: { businessId: businessId.value } });
};

const addFood = () => {
  router.push({ path: '/addFood', query: { businessId: businessId.value } });
};

const editFood = (foodId) => {
  router.push({ path: '/editFood', query: { foodId: foodId } });
};

const deleteFood = (foodId) => {
  if (confirm('确定要删除这个食品吗？')) {
    post(`/food/deleteFood/${foodId}`, { foodId: foodId }, () => {
      ElMessage.success('删除成功');
      // 重新加载食品列表
      listFoodArr(businessId.value);
    });
  }
};
</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>商家信息</p>
      <div class="header-icons">
        <el-icon @click="editBusiness"><Edit /></el-icon>
        <el-icon @click="addFood"><Plus /></el-icon>
      </div>
      </header>
    <div class="content">
      <!-- 商家logo部分 -->
      <div class="business-logo">
        <img :src="business.businessImg" alt="商家logo">
      </div>
      <!-- 商家信息部分 -->
      <div class="business-info">
        <h1>{{ business.businessName }}</h1>
        <p>&#165;{{ business.starPrice }}起送 &#165;{{ business.deliveryPrice }}配送</p>
        <p>{{ business.businessExplain }}</p>
      </div>
      <!-- 食品列表部分 -->
      <ul class="food">
        <li v-for="(item, index) in foodArr" :key="item.foodId">
          <div class="food-left">
            <img :src="item.foodImg" alt="食品图片">
            <div class="food-left-info">
              <h3>{{ item.foodName }}</h3>
              <p>{{ item.foodExplain }}</p>
              <p>&#165;{{ item.foodPrice }}</p>
            </div>
          </div>
          <div class="food-right">
            <el-icon @click="editFood(item.foodId)"><Edit /></el-icon>
            <el-icon @click="deleteFood(item.foodId)"><Delete /></el-icon>
          </div>
        </li>
      </ul>
    </div>
    <Footer></Footer>
  </div>
</template>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
/****************** header部分 ******************/
.wrapper header {
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
.header-icons {
  position: absolute;
  right: 10px;
  display: flex;
  align-items: center;
}
.header-icons .el-icon {
  font-size: 5vw;
  margin-left: 10px;
  cursor: pointer;
}
/****************** content部分 ******************/
.wrapper .content {
  flex: 1;
  overflow-y: auto;
  margin-top: 12vw; /* 留出header的高度 */
  margin-bottom: 14vw; /* 留出footer的高度 */
}
/****************** 商家logo部分 ******************/
.wrapper .business-logo {
  width: 100%;
  height: 35vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wrapper .business-logo img {
  width: 40vw;
  height: 30vw;
  border-radius: 5px;
}
/****************** 商家信息部分 ******************/
.wrapper .business-info {
  width: 100%;
  height: 20vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.wrapper .business-info h1 {
  font-size: 5vw;
}
.wrapper .business-info p {
  font-size: 3vw;
  color: #666;
  margin-top: 1vw;
}
/****************** 食品列表部分 ******************/
.wrapper .food {
  width: 100%;
}
.wrapper .food li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  user-select: none;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .food li .food-left {
  display: flex;
  align-items: center;
}
.wrapper .food li .food-left img {
  width: 20vw;
  height: 20vw;
}
.wrapper .food li .food-left .food-left-info {
  margin-left: 3vw;
}
.wrapper .food li .food-left .food-left-info h3 {
  font-size: 3.8vw;
  color: #555;
}
.wrapper .food li .food-left .food-left-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}
.wrapper .food li .food-right {
  display: flex;
  align-items: center;
}
.wrapper .food li .food-right .el-icon {
  font-size: 5.5vw;
  margin-left: 2vw;
  cursor: pointer;
}
/****************** 购物车部分 ******************/
.wrapper .cart {
  width: 100%;
  height: 14vw;
  position: fixed;
  left: 0;
  bottom: 0;
  display: flex;
}
.wrapper .cart .cart-left {
  flex: 2;
  background-color: #505051;
  display: flex;
}
.wrapper .cart .cart-left .cart-left-icon {
  width: 16vw;
  height: 16vw;
  box-sizing: border-box;
  border: solid 1.6vw #444;
  border-radius: 8vw;
  background-color: #3190E8;
  font-size: 7vw;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: -4vw;
  margin-left: 3vw;
  position: relative;
}
.wrapper .cart .cart-left .cart-left-icon-quantity {
  width: 5vw;
  height: 5vw;
  border-radius: 2.5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}
.wrapper .cart .cart-left .cart-left-info p:first-child {
  font-size: 4.5vw;
  color: #fff;
  margin-top: 1vw;
}
.wrapper .cart .cart-left .cart-left-info p:last-child {
  font-size: 2.8vw;
  color: #AAA;
}
.wrapper .cart .cart-right {
  flex: 1;
}
/*达到起送费时的样式*/
.wrapper .cart .cart-right .cart-right-item {
  width: 100%;
  height: 100%;
  background-color: #38CA73;
  color: #fff;
  font-size: 4.5vw;
  font-weight: 700;
  user-select: none;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** footer ******************/
footer {
  width: 100%;
  height: 14vw;
  position: fixed;
  left: 0;
  bottom: 0;
  z-index: 1000;
}
</style>
