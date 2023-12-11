<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="products_list.length === 0">目前你还没有发布任何商品^^</div>
      <div v-else>
        <div v-for="product in products" :key="product.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ product.name }}</h3>
            <p>目前价格：${{ product.price }}</p>
            <p>历史最低：${{ product.min_price }}</p>
          </div>
          <div class="product-details">
            <p>商品类别：{{ product.category }}</p>
            <p>卖家：{{ product.seller_name }}</p>
            <p>平台：{{ product.platform_name }}</p>
          </div>
          <div class="product-details">
            <button @click="edit_product">编辑商品信息</button>
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
            const seller_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_seller_products',{
              id: seller_id
            })
            .then(res => {
                this.products_list = this.products_list.concat(res.data.data);
            })
        },
        edit_product() {
          this.$router.push('/edit_product');
      },
      
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
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  </style>
  