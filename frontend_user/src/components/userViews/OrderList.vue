<script setup>
import Footer from './Footer.vue';
import { ref, onMounted } from 'vue';
import { getUser, post } from "@/net";
import { CaretBottom } from "@element-plus/icons-vue";
import router from "@/router";

const orderArr = ref([]);

const user = ref({});

onMounted(() => {
  user.value = getUser();
  post(`/orders/listOrdersByUserId/${user.value.id}`, { userId: user.value.id }, (data) => {
    orderArr.value = data.map(orderItem => ({
      orderId: orderItem.orderId,
      orderState: orderItem.orderState,
      orderTotal: orderItem.orderTotal,
      businessId: orderItem.businessId,
      business: {}, // 需要查询商家
      showFoodArr: [], // 初始化为空数组，将在后续步骤中填充
      isShowDetailet: false // 初始化订单细节显示状态
    }));

    // 查询每个订单的商家名称和订单细节
    orderArr.value.forEach(order => {
      post(`/business/byBusinessId/${order.businessId}`, { businessId: order.businessId }, (businessData) => {
        order.business = businessData;
      });

      post(`/orderdetailet/getOrderDetailetByOrderId/${order.orderId}`, { orderId: order.orderId }, (orderDetailetData) => {
        order.showFoodArr = orderDetailetData.map(orderDetailetItem => ({
          foodId: orderDetailetItem.foodId,
          foodName: "",
          foodImg: "",
          foodPrice: 0,
          quantity: orderDetailetItem.quantity
        }));

        order.showFoodArr.forEach(item => {
          post(`/food/byFoodId/${item.foodId}`, { foodId: item.foodId }, (foodData) => {
            item.foodImg = foodData.foodImg;
            item.foodName = foodData.foodName;
            item.foodPrice = foodData.foodPrice;
          });
        });
      });
    });

    console.log(orderArr.value);
  });
});

const detailetShow = (item) => {
  item.isShowDetailet = !item.isShowDetailet;
};

const toPay = (id) => {
  router.push({
    path: '/payment',
    query: {
      orderId: id
    }
  });
}
</script>

<template>
  <div class="wrapper">
    <header>
      <p>我的订单</p>
    </header>
    <div class="content">
      <h3>未支付订单信息：</h3>
      <div class="order">
        <ul>
          <li v-for="item in orderArr" v-show="item.orderState === 0" :key="item.orderId">
            <ul class="order-info">
              <p>
                {{ item.business.businessName }}
                <el-icon @click="detailetShow(item)"><CaretBottom /></el-icon>
              </p>
              <div class="order-info-right">
                <p>&#165;{{ item.orderTotal }}</p>
                <div class="order-info-right-icon" @click="toPay(item.orderId)">去支付</div>
              </div>
            </ul>
            <ul class="order-detailet" v-show="item.isShowDetailet">
              <li v-for="odItem in item.showFoodArr" :key="odItem.foodId">
                <p>{{ odItem.foodName }} x {{ odItem.quantity }}</p>
                <p>&#165;{{ odItem.foodPrice * odItem.quantity }}</p>
              </li>
              <li>
                <p>配送费</p>
                <p>&#165;{{ item.business.deliveryPrice }}</p>
              </li>
            </ul>
          </li>
        </ul>
      </div>

      <h3>已支付订单信息：</h3>
      <div class="order">
        <ul>
          <li v-for="item in orderArr" v-show="item.orderState === 1" :key="item.orderId">
            <div class="order-info">
              <p>
                {{ item.business.businessName }}
                <el-icon @click="detailetShow(item)"><CaretBottom /></el-icon>
              </p>
              <div class="order-info-right">
                <p>&#165;{{ item.orderTotal }}</p>
              </div>
            </div>
            <ul class="order-detailet" v-show="item.isShowDetailet">
              <li v-for="odItem in item.showFoodArr" :key="odItem.foodId">
                <p>{{ odItem.foodName }} x {{ odItem.quantity }}</p>
                <p>&#165;{{ odItem.foodPrice * odItem.quantity }}</p>
              </li>
              <li>
                <p>配送费</p>
                <p>&#165;{{ item.business.deliveryPrice }}</p>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <Footer />
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
/****************** 历史订单列表部分 ******************/
.wrapper h3 {
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}
.wrapper .order {
  width: 100%;
}
.wrapper .order li {
  width: 100%;
}
.wrapper .order li .order-info {
  box-sizing: border-box;
  padding: 2vw 4vw;
  font-size: 4vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order li .order-info .order-info-right {
  display: flex;
}
.wrapper .order li .order-info .order-info-right .order-info-right-icon {
  background-color: #f90;
  color: #fff;
  border-radius: 3px;
  margin-left: 2vw;
  user-select: none;
  cursor: pointer;
}
.wrapper .order li .order-detailet {
  width: 100%;
}
.wrapper .order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  color: #666;
  font-size: 3vw;
  display: flex;
  justify-content: space-between;
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
