<script setup>
import top_nav from '../../components/trivial/top_nav.vue'
</script>

<template>
  <div class="home-page">
    <!-- 顶部导航栏 -->
    <div class="top-nav">
        <top_nav></top_nav>
    </div>

    <!-- 搜索框 -->
    <div class="search-bar">
      <select v-model="search_type">
            <option value="1">商品名</option>
            <option value="2">具体种类</option>
            <option value="3">模糊种类</option>
      </select>
      <input v-model="search_keyword" placeholder="搜索商品">
      <button @click="search">搜索</button>
    </div>

    <!-- 商品列表 -->
    <product-list ref="productList"></product-list>
  </div>
</template>


<script>
import ProductList from '../../components/wrapper/user_h.vue';

export default {
  components: {
    ProductList
  },
  data() {
    return {
      search_keyword: '',
      search_type: "1",
      search_result:[]

    };
  },
  methods: {
    search() {
      this.$axios.post('http://localhost:8000/search_goods',{
            keyword: this.search_keyword,
            type: this.search_type
      })
      .then(res => {
          console.log(res.data.data);
          this.search_result = [];
          this.search_result = this.search_result.concat(res.data.data);  
          this.$refs.productList.change(this.search_result);
      })
    }
  }
};



</script>

<style scoped>
/* 在这里添加组件的样式 */
.home-page {
  max-width: 800px;
  margin: auto;
  padding: 16px;
  background-color: #f8f8f8; /* 背景色 */
}

.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #007bff; /* 顶部导航栏背景色 */
  color: #fff; /* 文字颜色 */
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 16px;
}


.search-bar {
  display: flex;
  justify-content: center; 
  align-items: center;
  margin-bottom: 16px;
}

input {
  padding: 8px;
}

button {
  padding: 8px 12px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.product-list {
  justify-content: space-between;
  width: 100%;
}
</style>
