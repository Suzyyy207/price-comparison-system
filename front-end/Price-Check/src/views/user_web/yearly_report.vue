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
      <h2>年度收藏商品</h2>
      <table class="chart-container">
            <thead>
              <tr>
                <th>种类</th>
                <th>收藏冠军商品名</th>
                <th>收藏量</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in top_list">
                <td>{{ record.tag }}</td>
                <td>{{ record.name }}</td>
                <td>{{ record.collectCount}}</td>
              </tr>
            </tbody>
          </table>
    </div>

    <!-- 商品列表 -->
    <div class="search-bar">
      <h2>今年，不同性别的用户喜欢的</h2>
    </div>
  </div>
</template>


<script>
export default {

  data() {
    return {
      top_list:[],
      top_id:0,
    };
  },
  created(){
    this.get_top4year();
  },
  methods: {
    get_top4year() {
      this.$axios.post('http://localhost:8000/top_of_the_year',{
            user_id:0,
      })
      .then(res => {
          console.log(res.data.data);
          this.top_list = this.top_list.concat(res.data.data);
          
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
