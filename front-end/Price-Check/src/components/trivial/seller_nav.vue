<template>
    <div class="user-info">用户信息</div>
    <div class="nav-links">
        <router-link to="/seller_web">我的商品</router-link>
        <router-link to="/edit_product">发布商品</router-link>
        <router-link to="/seller_profile">个人中心</router-link>
    </div>
</template>

<style scoped>
  .user-info {
    font-weight: bold;
  }
  
  .nav-links a {
    margin-left: 16px;
    text-decoration: none;
    color: #fff;
  }
</style>
