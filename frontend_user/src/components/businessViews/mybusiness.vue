<script setup>
import { ref, onMounted } from 'vue';
import { post, unauthorized, getUser } from '@/net';
import router from "@/router";
import { useRoute } from "vue-router";

// 引入组件
import Footer from './businessFooter.vue';
import { Delete, Edit, Plus } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";

const route = useRoute();
const businessArr = ref([]);
const user = ref({});
const cartArr = ref([]);

onMounted(() => {
  //获取已登录的user对象
  user.value = getUser();
  listMyBusinesses();
});
const listMyBusinesses = () => {
  post(`/business/getBusinessesByUserId/${user.value.id}`, { userId: user.value.id },
      (data) => {
        businessArr.value = data;
      })
}
const deleteBusiness = (businessId, event) => {
  event.stopPropagation(); // 阻止事件冒泡
  if (confirm('Confirm to delete this shop?')) {
    post(`/business/deleteBusienss/${businessId}`, { businessId: businessId },
        () => {
          ElMessage.success('删除成功');
          listMyBusinesses();
        })
  }
}

const toEditBusiness = (businessId, event) => {
  event.stopPropagation(); // 阻止事件冒泡
  router.push({ path: '/business/editBusiness', query: { businessId: businessId } })
}
const toBusinessInfo = (businessId) => {
  router.push({ path: '/business/businessInfoManage', query: { businessId: businessId } });
};
const toAddBusiness = () => {
  router.push({ path: '/business/addBusiness' });
};
</script>

<template>
  <div class="wrapper">
    <header>
      <p>我的门店</p>
      <el-icon @click="toAddBusiness"  style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%);"><Plus /></el-icon>
    </header>
    <div class="content">
      <ul class="business">
        <li v-for="item in businessArr" :key="item.businessId" @click="toBusinessInfo(item.businessId)">
          <div class="business-img">
            <img :src="item.businessImg" alt="">
            <div class="business-img-quantity" v-show="item.quantity > 0">
              {{ item.quantity }}
            </div>
          </div>
          <div class="business-info">
            <h3>{{ item.businessName }}</h3>
            <p>&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
            <p>{{ item.businessExplain }}</p>
          </div>
          <div class="business-actions">
            <el-icon @click="deleteBusiness(item.businessId, $event)"><Delete /></el-icon>
            <el-icon @click="toEditBusiness(item.businessId, $event)"><Edit /></el-icon>
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
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
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

/****************** 商家列表部分 ******************/
.wrapper .business {
  width: 100%;
}
.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  border-bottom: solid 1px #DDD;
  user-select: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.wrapper .business li .business-img {
  /*这里设置为相对定位，成为business-img-quantity元素的父元素*/
  position: relative;
}
.wrapper .business li .business-img img {
  width: 20vw;
  height: 20vw;
}
.wrapper .business li .business-img .business-img-quantity {
  width: 5vw;
  height: 5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;
  border-radius: 2.5vw;
  display: flex;
  justify-content: center;
  align-items: center;
  /*设置成绝对定位，不占文档流空间*/
  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}
.wrapper .business li .business-info {
  margin-left: 3vw;
  flex-grow: 1;
}
.wrapper .business li .business-info h3 {
  font-size: 3.8vw;
  color: #555;
}
.wrapper .business li .business-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}
.wrapper .business li .business-actions {
  display: flex;
  align-items: center;
}
.wrapper .business li .business-actions .el-icon {
  font-size: 5vw;
  margin-left: 2vw;
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
