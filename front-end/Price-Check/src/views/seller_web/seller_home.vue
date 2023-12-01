<script setup>
import top_nav from '../../components/trivial/seller_nav.vue'
</script>

<template>
  <div class="home-page">
    <!-- 顶部导航栏 -->
    <div class="top-nav">
        <top_nav></top_nav>
    </div>

    <!-- 搜索框 -->
    <div class="search-bar">
      <h2> 我的商品 </h2>
    </div>

    <!-- 商品列表 -->
    <product-list></product-list>
  </div>
</template>


<script>
import ProductList from '../../components/wrapper/seller_product.vue';

export default {
  components: {
    ProductList
  },
  data() {
    return {
      searchQuery: ''
    };
  }
};



</script>

<style scoped>
/* 在这里添加组件的样式 */
.home-page {
  max-width: 800px;
  margin: auto;
  padding: 16px;
  background-color: #f8f8f8; /* 背景色 */
}

.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #007bff; /* 顶部导航栏背景色 */
  color: #fff; /* 文字颜色 */
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 16px;
}


.search-bar {
  display: flex;
  justify-content: center; 
  align-items: center;
  margin-bottom: 16px;
}

input {
  padding: 8px;
}

button {
  padding: 8px 12px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.product-list {
  justify-content: space-between;
  width: 100%;
}
</style>
