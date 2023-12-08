<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="platform_list.length === 0">目前还没有任何平台入驻^^</div>
      <div v-else>
        <div v-for="platform in platform_list" :key="platform.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ platform.name }}</h3>
            <p>运营时长：{{ platform.age }}</p>
          </div>
          <div class="product-details">
            <h3>Datail</h3>
            <p>地址：{{ platform.address }}</p>
            <p>电话：{{ platform.telephone }}</p>
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
        platform_list: [],
      };
    },
    mounted() {
        // 模拟异步请求后端数据
            setTimeout(() => {
                // 假设这是后端返回的商品数据
                const backendData = [
                { id:1, name: 'TY', age: 18, telephone:'188888888',address:'China'},
                { id:2, name: 'JW', age: 18,telephone:'188888887', address:'HH'},
                // ... 更多商品数据
                ];
        
                this.platform_list = backendData;
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
  