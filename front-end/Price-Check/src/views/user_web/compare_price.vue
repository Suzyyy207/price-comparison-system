<script setup>
import top_nav from '../../components/trivial/top_nav.vue'
</script>

<template>
  <div class="home-page">
    <!-- 顶部导航栏 -->
    <div class="top-nav">
        <top_nav></top_nav>
    </div>

    <div class="search-bar">
      <h2>比价平台</h2>
    </div>
    <!-- 搜索框 -->
    <div class="search-bar">
      <input v-model="search_keyword" placeholder="搜索种类">
      <button @click="search">搜索</button>
    </div>

    <!-- 商品列表 -->
    <tag-list ref="tagList"></tag-list>
  </div>
</template>


<script>
import TagList from '../../components/wrapper/compare_tag.vue';

export default {
  components: {
    TagList
  },
  data() {
    return {
      search_keyword: '',
      search_result:[]
    };
  },
  methods: {
    search() {
      this.$axios.post('http://localhost:8000/search_for_tag',{
            keyword: this.search_keyword,
            userId: window.localStorage.getItem("user_id")
      })
      .then(res => {
          console.log(res.data.data);
          this.search_result = [];
          this.search_result = this.search_result.concat(res.data.data);  
          this.$refs.tagList.change(this.search_result);
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
