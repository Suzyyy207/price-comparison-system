<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="products_list.length === 0">目前还没有任何商品上架^^</div>
      <div v-else>
        <div v-for="product in products" :key="product.id" class="product-item" @click = "to_product(product)">
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
            this.$axios.post('http://localhost:8000/get_user_products',{
              get_type: 0,
              id: user_id
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
  