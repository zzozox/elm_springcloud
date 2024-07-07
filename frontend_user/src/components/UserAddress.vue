<script setup>
import { ref, onMounted } from 'vue';
import Footer from '../components/Footer.vue';
import router  from "@/router";
import {post,getUser} from "../net";
import {CirclePlusFilled, Delete, Edit} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {useRoute} from "vue-router";

const route=useRoute();
const businessId = ref();
const user = ref({});
const deliveryAddressArr = ref([]);

onMounted(() => {
  user.value = getUser();
  businessId.value=route.query.businessId;
  listDeliveryAddressByUserId();
});

const listDeliveryAddressByUserId = () => {
  post(`/deliveryaddress/listDeliveryAddressByUserId/${user.value.id}`,{userId:user.value.id},(data)=>{
    deliveryAddressArr.value=data;
    console.log('deliveryAddressArr.value:',deliveryAddressArr.value);
  })
};

function getUserSexLabel(userSex) {
  return userSex === 1 ? "男" : "女";
}

const toOrder= (daId) => {
  router.push({ path: '/orders', query: { daId: daId,businessId:businessId.value } });
};

const addUserAddress=()=>{
  router.push({path:'/addUserAddress',query:{businessId:businessId.value}})
}
const editUserAddress = (daId) => {
  router.push({ path: '/editUserAddress', query: { daId: daId,businessId:businessId.value } });
};

const removeUserAddress = (daId) => {
  if (confirm('Confirm to delete this delivery address?')) {
    post(`/deliveryaddress/deleteDeliveryAddress/${daId}`,{daId:daId},()=>{
      ElMessage.success('删除成功');
      listDeliveryAddressByUserId();
    });
  }
};
</script>

<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>地址管理</p>
    </header>
    <!-- 地址列表部分 -->
    <ul class="addresslist">
      <li v-for="item in deliveryAddressArr" :key="item.daId">
        <div class="addresslist-left" @click="toOrder(item.daId)">
          <h3>{{ item.contactName }}  {{ getUserSexLabel(item.contactSex)}}  {{ item.contactTel }}
          </h3>
          <p>{{ item.address }}</p>
        </div>
        <div class="addresslist-right">
          <el-icon @click="editUserAddress(item.daId)"><Edit /></el-icon>
          <el-icon @click="removeUserAddress(item.daId)"><Delete /></el-icon>
        </div>
      </li>
    </ul>
    <!-- 新增地址部分 -->
    <div class="addbtn" @click="addUserAddress">
      <el-icon><CirclePlusFilled /></el-icon>
      <p>新增收货地址</p>
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
  background-color: #F5F5F5;
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
  z-index: 1000;
}
@media only screen and (max-width: 600px) {
  .wrapper header {
    font-size: 3.2vw; /* 调整在小屏幕上的字体大小 */
  }
}
/*************** addresslist ***************/
.wrapper .addresslist {
  width: 100%;
  margin-top: 12vw;
  background-color: #fff;
}
.wrapper .addresslist li {
  width: 100%;
  box-sizing: border-box;
  border-bottom: solid 1px #DDD;
  padding: 3vw;
  display: flex;
}
.wrapper .addresslist li .addresslist-left {
  flex: 5;
  user-select: none;
  cursor: pointer;
}
.wrapper .addresslist li .addresslist-left h3 {
  font-size: 4.6vw;
  font-weight: 300;
  color: #666;
}
.wrapper .addresslist li .addresslist-left p {
  font-size: 4vw;
  color: #666;
}
.wrapper .addresslist li .addresslist-right {
  flex: 1;
  font-size: 5.6vw;
  color: #999;
  cursor: pointer;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
/*************** 新增地址部分 ***************/
.wrapper .addbtn {
  width: 100%;
  height: 14vw;
  border-top: solid 1px #DDD;
  border-bottom: solid 1px #DDD;
  background-color: #fff;
  margin-top: 4vw;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 4.5vw;
  color: #0097FF;
  user-select: none;
  cursor: pointer;
}
@media only screen and (max-width: 600px) {
  .wrapper .addbtn {
    height: 10vw; /* 调整在小屏幕上的高度 */
    font-size: 3.5vw; /* 调整在小屏幕上的字体大小 */
  }
}
.wrapper .addbtn p {
  margin-left: 2vw;
}
</style>
