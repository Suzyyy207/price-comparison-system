<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="products_list.length === 0">目前还没有任何商品上架^^</div>
      <div v-else>
        <div v-for="product in products" :key="product.shop_id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ product.shop_name }}</h3>
            <p>价格：${{ product.price }}</p>
            <p>历史最低：${{ product.min_price }}</p>
          </div>
          <div class="product-details">
            <p>商品类别：{{ product.category }}</p>
            <p>卖家：{{ product.seller_name }}</p>
            <p>平台：{{ product.platform_name }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        products_list: [],
      };
    },
    created (){
        this.get_products()
    },
    methods: {
        get_products() {
            const user_id = window.localStorage.getItem('user_id');
            this.$axios.post('/get_user_products',{
              get_type: 0,
              user_id: user_id
            })
            .then(res => {
                this.products_list = this.products_list.concat(res.data.data);
            })
        }
    }
    /*mounted() {
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
    }*/
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
  