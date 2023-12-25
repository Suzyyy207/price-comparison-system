<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="products_list.length === 0">没有匹配结果^^</div>
      <div v-else>
        <div v-for="product in products_list" :key="product.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ product.name }}</h3>
            <p>价格：${{ product.price }}</p>
            <p>历史最低：${{ product.minPrice }}</p>
          </div>
          <div class="product-details">
            <p>商品类别：{{ product.category }}</p>
            <p>卖家：{{ product.sellerName }}</p>
            <p>平台：{{ product.platformName }}</p>
          </div>
          <div class="product-details">
            <button @click = "to_product(product)">点击查看详情</button>
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
          localStorage.setItem("goods_id",product.id);
          this.$router.push({name:'product_detail'});
        },
        change(new_product_list){
          this.products_list = [];
          this.products_list = new_product_list;
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
  