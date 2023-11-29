<!-- MessageList.vue -->
<script setup>
import top_nav from '../../components/trivial/top_nav.vue'
</script>

<template>
  <div class="home-page">
    <!-- 头部 -->
    <div class="top-nav">
        <top_nav></top_nav>
    </div>

    <div class="search-bar">
        <h2>消息列表</h2>
      </div>

    <!-- 消息列表内容 -->
    <MessageList></MessageList>
  </div>
</template>

<script>
import MessageList from '../../components/wrapper/user_m.vue';


export default {
  components: {
    MessageList
  },
  data() {
    return {
      searchQuery: ''
    };
  },
  methods: {
    search() {
      // 在这里执行搜索逻辑，可以根据 searchQuery 向后端请求匹配的商品数据
      console.log('执行搜索:', this.searchQuery);
      // 清空搜索框
      this.searchQuery = '';
    }
  }
};



</script>

<style scoped>
/* 在这里添加组件样式 */
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
</style>
