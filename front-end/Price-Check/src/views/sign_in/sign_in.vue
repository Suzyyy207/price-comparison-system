<script setup>
import HelloWorld from '../../components/sign_in/HelloWorld.vue';
import Login from '../../components/sign_in/login_wrapper.vue'
</script>

<template>
  <div class="wrapper">
    <div class="left">
      <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />
  
      <div class="hello">
        <HelloWorld msg="Sign In Now" />
      </div>

    </div>
    
    <div class="right">
        <Login/>
    </div>
  </div>
      
  
  </template>

<style scoped>
.wrapper {
  display: flex;
  margin: 200px 30px 0 30px;
  width: 90%;
}

.left {
  flex: 1;
  margin-left: 100px;
}

.right {
  flex: 1;
}

</style>

