<script setup>
import top_nav from '../../components/trivial/seller_nav.vue'
</script>

<template>
  <div class="home-page">
    <!-- 顶部导航栏 -->
    <div class="top-nav">
        <top_nav></top_nav>
    </div>

    <div class="search-bar">
        <h2>我的商品分析</h2>
      </div>
    <!-- 搜索框 -->
    <div class="search-bar">
      
      <table class="chart-container">
            <thead>
              <tr>
                <th>商品名</th>
                <th>收藏量</th>
                <th>收藏用户期望价格平均值</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in analysis_list">
                <td>{{ record.goodsName }}</td>
                <td>{{ record.count}}</td>
                <td>{{ record.avgPrice}}</td>
              </tr>
            </tbody>
          </table>
    </div>

  </div>
</template>


<script>
export default {

  data() {
    return {
      analysis_list:[],
    };
  },
  created(){
    this.get_analysis();
  },
  methods: {
    get_analysis() {
      this.$axios.post('http://localhost:8000/get_avg_targetprice',{
            user_id: window.localStorage.getItem("user_id"),
      })
      .then(res => {
          this.analysis_list = this.analysis_list.concat(res.data.data);
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
  margin-bottom: 5px;
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
</style>
