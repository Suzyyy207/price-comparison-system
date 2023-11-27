<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="loading">Loading...</div>
      <div v-else>
        <div v-for="product in products" :key="product.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ product.name }}</h3>
            <p>价格：${{ product.price }}</p>
            <p>历史最低：${{ product.min_price }}</p>
          </div>
          <div class="product-details">
            <p>商品属性：{{ product.goods }}</p>
            <p>卖家：{{ product.seller }}</p>
            <p>平台：{{ product.platform }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        loading: true,
        products: []
      };
    },
    mounted() {
      // 模拟异步请求后端数据
      setTimeout(() => {
        // 假设这是后端返回的商品数据
        const backendData = [
          { id:1, name: 'PanPan', min_price: 10, price: 19.99, goods:'Bread', seller:'P',platform:'TB'},
          { id: 2, name: 'DingDing', min_price: 12.7, price: 29.99, goods:'Bread', seller:'X',platform:'JD'},
          // ... 更多商品数据
        ];
  
        this.products = backendData;
        this.loading = false;
      }, 1000); // 模拟1秒后获取到数据
    }
  };
  </script>
  
  <style scoped>
  /* 在这里添加组件的样式 */
  .product-list {
    max-width: 800px;
    margin: auto;
  }
  
  .product-item {
    display: flex;
    border: 1px solid #ccc;
    margin-bottom: 16px;
    padding: 16px;
  }
  
  .product-image {
    max-width: 100px;
    margin-right: 16px;
  }
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  
  /* 可根据需要添加更多样式 */
  </style>
  