<script setup>
import {ref, onMounted, toRaw} from 'vue';
import Footer from './Footer.vue';
import {useRoute} from "vue-router";
import {post} from "@/net";
import {ArrowDown, CircleCheckFilled} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import router from "@/router";

const route=useRoute();
const orderId = ref(route.query.orderId);
const order = ref({});
const business=ref({});
const showFoodArr=ref([
  {
    foodId:0,
    foodName: "",
    foodImg: "",
    foodPrice: 0,
    quantity: 0
  }
]);
const isShowDetailet = ref(false);
const detailetShow = () => {
  isShowDetailet.value = !isShowDetailet.value;
};

onMounted(() => {
  //查询订单信息,通过订单获取商家信息
  post(`/orders/getOrdersById/${orderId.value}`, {orderId: orderId.value},
      (data) => {
        order.value = data;
        // 查询当前商家
        post(`/business/byBusinessId/${order.value.businessId}`, {businessId:order.value.businessId},
            (data) => {
              business.value = data;})
  })
  // 查询当前订单细则，以及对应订单细则的食品详情
  post(`/orderdetailet/getOrderDetailetByOrderId/${orderId.value}`,
      {orderId: orderId.value},
      (data) => {
        // 清空 showFoodArr 数组，然后将 data 中的每一项推入数组
        showFoodArr.value = data.map(orderDetailetItem => ({
          foodId: orderDetailetItem.foodId,
          quantity: orderDetailetItem.quantity
        }));

        // 遍历所有订单细则，查询订单细则的食品详细信息
        for (const item of showFoodArr.value) {
          post(`/food/byFoodId/${item.foodId}`, { foodId: item.foodId }, (foodData) => {
            item.foodImg = foodData.foodImg;
            item.foodName = foodData.foodName;
            item.foodPrice = foodData.foodPrice;
          });
        }
      });
});

const payOrder=()=>{
  post(`/orders/payOrder/${orderId.value}`,{orderId: orderId.value},
      (data)=>{
          ElMessage.success('支付成功');
          router.push({
            path: '/index',
          });
      })
}
</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>在线支付</p>
    </header>
    <!-- 订单信息部分 -->
    <h3>订单信息：</h3>
    <div class="order-info">
      <p>
        {{ business.businessName }}
        <el-icon @click="detailetShow"><ArrowDown /></el-icon>
      </p>
      <p>&#165;{{ order.orderTotal }}</p>
    </div>
    <!-- 订单明细部分 -->
    <ul class="order-detailet" v-show="isShowDetailet">
      <li v-for="item in showFoodArr" :key="item.foodId">
        <p>{{ item.foodName }} x {{ item.quantity }}</p>
        <p>&#165;{{ item.foodPrice * item.quantity }}</p>
      </li>
      <li>
        <p>配送费</p>
        <p>&#165;{{ business.deliveryPrice }}</p>
      </li>
    </ul>
    <!-- 支付方式部分 -->
    <ul class="payment-type">
      <li>
        <img src="../../assets/img/alipay.png" alt="支付宝">
        <el-icon><CircleCheckFilled /></el-icon>
      </li>
      <li>
        <img src="../../assets/img/wechat.png" alt="微信">
      </li>
    </ul>
    <div class="payment-button">
      <button @click="payOrder">确认支付</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer />
  </div>
</template>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
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
/****************** 订单信息部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw 4vw 0;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}
.wrapper .order-info {
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order-info p:last-child {
  color: orangered;
}
/****************** 订单明细部分 ******************/
.wrapper .order-detailet {
  width: 100%;
}
.wrapper .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order-detailet li p {
  font-size: 3vw;
  color: #666;
}
/****************** 支付方式部分 ******************/
.wrapper .payment-type {
  width: 100%;
}
.wrapper .payment-type li {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .payment-type li img {
  width: 33vw;
  height: 8.9vw;
}
.wrapper .payment-type li .fa-check-circle {
  font-size: 5vw;
  color: #38CA73;
}
.wrapper .payment-button {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
}
.wrapper .payment-button button {
  width: 100%;
  height: 10vw;
  border: none;
  /*去掉外轮廓线*/
  outline: none;
  border-radius: 4px;
  background-color: #38CA73;
  color: #fff;
}
</style>
