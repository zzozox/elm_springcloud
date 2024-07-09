<script setup>
import { ref, onMounted, reactive, computed } from 'vue';
import { post, getUser } from '@/net';
import router from "@/router";
import { useRoute } from "vue-router";
import { Minus, Plus, ShoppingCart } from "@element-plus/icons-vue";

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
    // 根据 businessId 查询所属食品信息
    post(`/food/byBusinessId/${businessId.value}`, { businessId: businessId.value }, (data) => {
      foodArr.value = data;
      listCart();
    });
  });
});

// 查询购物车中所有食物
const listCart = () => {
  post(`/cart/listCartByUserIdAndBusinessId`, { businessId: businessId.value, userId: user.value.id }, (data) => {
    cartArr.splice(0, cartArr.length, ...data);
    updateFoodQuantities();
  });
};

const updateFoodQuantities = () => {
  for (const foodItem of foodArr.value) {
    foodItem.quantity = 0;
    for (const cartItem of cartArr) {
      if (cartItem.foodId === foodItem.foodId) {
        foodItem.quantity = cartItem.quantity;
        break;
      }
    }
  }
};

// 食物加号
const add = (index) => {
  const foodItem = foodArr.value[index];
  if (foodItem.quantity === 0) {
    saveCart(index);
  } else {
    updateCart(index, 1);
  }
};

// 食物减号
const minus = (index) => {
  const foodItem = foodArr.value[index];
  if (foodItem.quantity > 1) {
    updateCart(index, -1);
  } else {
    removeCart(index);
  }
};

const saveCart = (index) => {
  post(`/cart/createCart`, {
    foodId: foodArr.value[index].foodId,
    businessId: businessId.value,
    userId: user.value.id,
    quantity: 1
  }, () => {
    foodArr.value[index].quantity = 1;
    listCart();
  });
};

const updateCart = (index, num) => {
  const cartItem = cartArr.find(item => item.foodId === foodArr.value[index].foodId);
  post(`/cart/updateCart`, {
    cartId: cartItem.cartId,
    foodId: foodArr.value[index].foodId,
    businessId: businessId.value,
    userId: user.value.id,
    quantity: foodArr.value[index].quantity + num
  }, () => {
    foodArr.value[index].quantity += num;
    listCart();
  });
};

const removeCart = (index) => {
  const cartItem = cartArr.find(item => item.foodId === foodArr.value[index].foodId);
  post(`/cart/deleteCart/${cartItem.cartId}`, { cartId: cartItem.cartId }, () => {
    foodArr.value[index].quantity = 0;
    listCart();
  });
};

const toOrder = () => {
  router.push({ path: '/orders', query: { businessId: businessId.value } });
};

// 计算属性
const totalPrice = computed(() => {
  return foodArr.value.reduce((total, item) => total + item.foodPrice * item.quantity, 0);
});

const totalQuantity = computed(() => {
  return foodArr.value.reduce((quantity, item) => quantity + item.quantity, 0);
});

const totalSettle = computed(() => {
  return totalPrice.value + business.value.deliveryPrice;
});
</script>
<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>商家信息</p>
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
        <li v-for="(item,index) in foodArr" :key="item.foodId">
          <div class="food-left">
            <img :src="item.foodImg" alt="食品图片">
            <div class="food-left-info">
              <h3>{{ item.foodName }}</h3>
              <p>{{ item.foodExplain }}</p>
              <p>&#165;{{ item.foodPrice }}</p>
            </div>
          </div>
          <div class="food-right">
            <div>
              <el-icon @click="minus(index)" v-show="item.quantity !== 0"><Minus /></el-icon>
            </div>
            <p><span v-show="item.quantity !== 0">{{ item.quantity }}</span></p>
            <div>
              <el-icon @click="add(index)"><Plus /></el-icon>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <!--购物车部分 -->
    <div class="cart">
      <div class="cart-left">
        <div class="cart-left-icon" :style="{ backgroundColor: totalQuantity === 0 ? '#505051' : '#3190E8' }">
          <el-icon><ShoppingCart /></el-icon>
          <div class="cart-left-icon-quantity" v-show="totalQuantity !== 0">
            {{ totalQuantity }}
          </div>
        </div>
        <div class="cart-left-info">
          <p>&#165;{{ totalPrice }}</p>
          <p>另需配送费{{ business.deliveryPrice }}元</p>
        </div>
      </div>
      <div class="cart-right">
        <!-- 不够起送费 -->
        <div class="cart-right-item" v-show="totalSettle < business.starPrice" style="background-color: #535356;cursor: default;">
          &#165;{{ business.starPrice }}起送
        </div>
        <!-- 达到起送费 -->
        <div class="cart-right-item" @click="toOrder" v-show="totalSettle >= business.starPrice">
          去结算
        </div>
      </div>
    </div>
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
  width: 16vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .food li .food-right .el-icon {
  font-size: 5.5vw;
  cursor: pointer;
}
.wrapper .food li .food-right p {
  font-size: 3.6vw;
  color: #333;
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
/*不够起送费时的样式*/
.wrapper .cart .cart-right .cart-right-item[style*="background-color: #535356;"] {
  cursor: default;
}
</style>
