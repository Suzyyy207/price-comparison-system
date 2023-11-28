<template>
    <div class="user-info">用户信息</div>
    <div class="nav-links">
        <router-link to="/user_web">主页</router-link>
        <router-link to="/favorites">收藏夹</router-link>
        <router-link to="/messages">消息列表</router-link>
        <router-link to="/profile">个人中心</router-link>
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
