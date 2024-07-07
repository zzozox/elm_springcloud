<script setup>
import { ref, computed, onMounted ,toRaw} from 'vue';
import router from "@/router";
import {getUser,post} from "@/net";
import {useRoute} from "vue-router";
import {ElMessage} from "element-plus";

const route=useRoute();
const businessId = ref();
const daId=ref();
const user = ref({});
const business = ref({});
const deliveryaddress = ref({});
const showFoodArr=ref([
  {
    foodId:0,
    foodName: "",
    foodImg: "",
    foodPrice: 0,
    quantity: 0
  }
]);
const order=ref({});

onMounted(() => {
  user.value = getUser();
  businessId.value=route.query.businessId;
  //设置默认daId为1，选择地址后可重置daId
  if(route.query.daId==null){
    daId.value=1;
  }else {
    daId.value=route.query.daId;
  }
  // 查询当前商家
  post(`/business/byBusinessId/${businessId.value}`, {businessId: businessId.value},
      (data) => {
    business.value = data;})
  // 查询当前用户当前商家购物车中食品的详细信息
  post(`/cart/listCartByUserIdAndBusinessId`,
      { businessId: businessId.value, userId: user.value.id },
      (data) => {
        // 清空 showFoodArr 数组，然后将 data 中的每一项推入数组
        showFoodArr.value = data.map(cartItem => ({
          foodId: cartItem.foodId,
          quantity: cartItem.quantity
        }));

        // 遍历所有购物车，查询购物车内的食品详细信息
        for (const item of showFoodArr.value) {
          post(`/food/byFoodId/${item.foodId}`, { foodId: item.foodId }, (foodData) => {
            item.foodImg = foodData.foodImg;
            item.foodName = foodData.foodName;
            item.foodPrice = foodData.foodPrice;
          });
        }
      });

  //根据userAddres返回的daId查询地址信息
  post(`/deliveryaddress/getDeliveryAddressById/${daId.value}`, {daId: daId.value},
      (data) => {deliveryaddress.value = data;})
})
//计算总的价格
const totalPrice = computed(() => {
  let total = 0;
  for (const item of showFoodArr.value) {
        total += item.foodPrice * item.quantity;
      }
  total += business.value.deliveryPrice;
  return total;
});
const toUserAddress = () => {
  router.push({ path: '/userAddress', query: { businessId: businessId.value } });
};

const toPayment = () => {
  // 创建订单
  post(`/orders/createOrders`, {
    userId: user.value.id,
    businessId: businessId.value,
    daId: daId.value,
    orderTotal: totalPrice.value
  },(data)=>{
    order.value=data;
    if (order.value == null) {
      ElMessage.warning('创建订单失败！');
    } else {
      ElMessage.success('下单成功，请支付订单');
      console.log('showFoodArr.value:', showFoodArr.value);
      router.push({
        path: '/payment',
        query: {
        orderId: order.value.orderId,
        //URL中的查询参数应该是字符串
        //使用JSON.stringify将showFoodArr数组转换为字符串，然后将其作为查询参数传递。
        // 在payment组件中，使用JSON.parse将其转换回数组
        showFoodArr:JSON.stringify(toRaw(showFoodArr.value))}
      });
    }
  })
}
</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>确认订单</p>
    </header>
    <!-- 订单信息部分 -->
    <div class="order-info">
      <h5>订单配送至：</h5>
      <div class="order-info-address" @click="toUserAddress">
        <p>{{ deliveryaddress !== null ? deliveryaddress.address : '请选择送货地址' }}</p>
        <i class="fa fa-angle-right"></i>
      </div>
      <p>{{ deliveryaddress.contactName }}  {{ deliveryaddress.contactSex===1?'男':'女' }}  {{ deliveryaddress.contactTel }}</p>
    </div>
    <h3>{{ business.businessName }}</h3>
    <!-- 订单明细部分 -->
    <ul class="order-detailed">
      <li v-for="showItem in showFoodArr" :key="showItem.foodId">
        <div class="order-detailed-left">
          <img :src="showItem.foodImg" alt="食品图片">
          <p>{{ showItem.foodName }} x {{ showItem.quantity }}</p>
        </div>
        <p>&#165;{{ showItem.foodPrice * showItem.quantity }}</p>
      </li>
    </ul>
    <div class="order-deliveryfee">
      <p>配送费</p>
      <p>&#165;{{ business.deliveryPrice }}</p>
    </div>
    <!-- 合计部分 -->
    <div class="total">
      <div class="total-left">
        &#165;{{ totalPrice }}
      </div>
      <div class="total-right" @click="toPayment">
        去支付
      </div>
    </div>
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
.wrapper .order-info {
  /*注意这里，不设置高，靠内容撑开。因为地址有可能折行*/
  width: 100%;
  margin-top: 12vw;
  background-color: #0097EF;
  box-sizing: border-box;
  padding: 2vw;
  color: #fff;
}
.wrapper .order-info h5 {
  font-size: 3vw;
  font-weight: 300;
}
.wrapper .order-info .order-info-address {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 700;
  user-select: none;
  cursor: pointer;
  margin: 1vw 0;
}
.wrapper .order-info .order-info-address p {
  width: 90%;
  font-size: 5vw;
}
.wrapper .order-info .order-info-address i {
  font-size: 6vw;
}
.wrapper .order-info p {
  font-size: 3vw;
}
.wrapper h3 {
  box-sizing: border-box;
  padding: 3vw;
  font-size: 4vw;
  color: #666;
  border-bottom: solid 1px #DDD;
}
/****************** 订单明细部分 ******************/
.wrapper .order-detailed {
  width: 100%;
}
.wrapper .order-detailed li {
  width: 100%;
  height: 16vw;
  box-sizing: border-box;
  padding: 3vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order-detailed li .order-detailed-left {
  display: flex;
  align-items: center;
}
.wrapper .order-detailed li .order-detailed-left img {
  width: 10vw;
  height: 10vw;
}
.wrapper .order-detailed li .order-detailed-left p {
  font-size: 3.5vw;
  margin-left: 3vw;
}
.wrapper .order-detailed li p {
  font-size: 3.5vw;
}
.wrapper .order-deliveryfee {
  width: 100%;
  height: 16vw;
  box-sizing: border-box;
  padding: 3vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 3.5vw;
}
/****************** 订单合计部分 ******************/
.wrapper .total {
  width: 100%;
  height: 14vw;
  position: fixed;
  left: 0;
  bottom: 0;
  display: flex;
}
.wrapper .total .total-left {
  flex: 2;
  background-color: #505051;
  color: #fff;
  font-size: 4.5vw;
  font-weight: 700;
  user-select: none;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wrapper .total .total-right {
  flex: 1;
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
</style>le>
