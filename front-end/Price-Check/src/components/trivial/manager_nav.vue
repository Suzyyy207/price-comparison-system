<template>
    <div class="user-info">Hello!</div>
    <div class="nav-links">
        <router-link to="/all_products">全部商品</router-link>
        <router-link to="/all_sellers">全部商家</router-link>
        <router-link to="/all_users">全部用户</router-link>
        <router-link to="/all_platforms">全部平台</router-link>
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
