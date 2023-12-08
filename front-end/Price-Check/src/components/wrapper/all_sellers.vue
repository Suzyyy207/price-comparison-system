<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="seller_list.length === 0">目前还没有任何用户^^</div>
      <div v-else>
        <div v-for="seller in seller_list" :key="seller.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ seller.name }}</h3>
            <p>性别：{{ seller.sex }}</p>
          </div>
          <div class="product-details">
            <h3>Datail</h3>
            <p>地址：{{ seller.address }}</p>
            <p>电话：{{ seller.telephone }}</p>
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
        seller_list: [],
      };
    },
    mounted() {
        // 模拟异步请求后端数据
            setTimeout(() => {
                // 假设这是后端返回的商品数据
                const backendData = [
                { id:1, name: 'TY', address: 'SM', telephone:'188888888'},
                { id:2, name: 'JW', address:'SM',telephone:'188888887'},
                // ... 更多商品数据
                ];
        
                this.seller_list = backendData;
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
    display: flex;
  }
  
  .product-item {
    display: flex;
    border: 1px solid #ccc;
    margin: 10px 30px 20px 80px;
    padding: 16px;
    width: 600px;
  }
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  
  /* 可根据需要添加更多样式 */
</style>
  