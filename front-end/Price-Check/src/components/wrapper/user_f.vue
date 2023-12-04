<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="loading">Loading...</div>
      <div v-else>
        <div v-for="product in products" :key="product.id" class="product-item" @click = "to_product(product)">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ product.name }}</h3>
            <p>价格：${{ product.price }}</p>
            <p>历史最低：${{ product.min_price }}</p>
          </div>
          <div class="product-details">
            <p>商品类别：{{ product.category }}</p>
            <p>卖家：{{ product.seller_name }}</p>
            <p>平台：{{ product.platform_name }}</p>
          </div>
          <div class="product-details">
            <p>我设定的期望价格 {{ product.platform }}</p>
            <div>
              <input v-model="new_target_price" placeholder="输入两位小数">
              <button @click="update_target_price">设定期望价格</button>
            </div>
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
        this.get_collects()
    },
    methods: {
        get_collects() {
            const user_id = window.localStorage.getItem('user_id');
            this.$axios.post('/get_user_products',{
              get_type: 1,
              user_id: user_id
            })
            .then(res => {
                this.products_list = this.products_list.concat(res.data.data);
            })
        },
        to_product(product){
          var localStorage = window.localStorage;
          localStorage.setItem("p_id",product.id);
          this.$router.push({name:'product_detail'});
        }
    },
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
  
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  
  input {
  padding: 4px;
  width: 100px;
  height: 30px;
 }

button {
  padding: 8px 12px;
  margin:30px;
  height: 30px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
  /* 可根据需要添加更多样式 */
  </style>
  