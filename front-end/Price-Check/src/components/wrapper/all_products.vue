<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="product_list.length === 0">目前还没有任何商品^ ^</div>
      <div v-else>
        <div v-for="product in product_list" :key="product.id" class="product-item" @click = "to_product(product)">
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
            <button>修改信息</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        product_list: [],
      };
    },
    created (){
        this.get_all_products()
    },
    methods: {
        get_all_products() {
            const manager_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_all_goods',{
              id: manager_id
            })
            .then(res => {
                this.product_list = this.product_list.concat(res.data.data);
            })
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
  