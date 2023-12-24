<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="loading">Loading...</div>
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
            <p>我设定的期望价格 {{ product.target }}</p>
            <div>
              <input v-model="new_price" placeholder="输入两位小数">
              <button @click="update_target_price(product.id)">设定期望价格</button>
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
        new_price:12,
      };
    },
    created (){
        this.get_collects()
    },
    methods: {
        get_collects() {
            const user_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_user_products',{
              get_type: 1,
              user_id: user_id
            })
            .then(res => {
              console.log(res.data.data);
                this.products_list = [];
                this.products_list = this.products_list.concat(res.data.data);
            })
        },
        to_product(product){
          var localStorage = window.localStorage;
          localStorage.setItem("p_id",product.id);
          this.$router.push({name:'product_detail'});
        },
        update_target_price(goods_id){
          this.$axios.post('http://localhost:8000/set_target_price',{
              expectPrice: this.new_price,
              goodsId: goods_id,
              userId: window.localStorage.getItem("user_id")
            })
            .then(res => {
              if(res.data.data == true){
                console.log(res.data.data);
                this.get_collects();
              }
            }
            )
        }
    },
    
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
  