<script setup>
import router from "@/router";
</script>

<template>
  <div>
<!--    <video autoplay loop muted>-->
<!--      <source src="../assets/bg.mp4" type="video/mp4">-->
<!--      Your browser does not support the video tag.-->
<!--    </video>-->
    <img src="../../assets/background.jpg" alt="">
    <button @click="router.push('/index')">Welocome to Eleme</button>
  </div>
</template>

<style scoped>
div {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}
img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}

button {
  background-color: rgba(173, 216, 230, 0.7);
  padding: 15px;
  border: 2px solid #ADD8E6;
  border-radius: 10px;
  font-size: 1.2em;
  color: #333;
  cursor: pointer;
}
</style>
