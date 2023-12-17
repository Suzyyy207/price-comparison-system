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
    created (){
        this.get_all_platforms()
    },
    methods: {
        get_all_platforms() {
            const manager_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_all_platforms',{
              id: manager_id
            })
            .then(res => {
              console.log(res.data.data);
                this.platform_list = this.platform_list.concat(res.data.data);
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
  