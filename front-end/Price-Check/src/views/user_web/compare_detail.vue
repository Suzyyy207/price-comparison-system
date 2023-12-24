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
      <h2>比价种类：{{ this.tag }}</h2>
    </div>
    <div class="choose">
        <div>
            <button @click="change_type(1)">比较不同商家现在的价格，请选择平台</button>
            <select v-model="search_type_now">
                <option value="0">全平台</option>
                <option value="1">淘宝</option>
                <option value="2">京东</option>
                <option value="3">闲鱼</option>
                <option value="4">NCITY</option>
            </select>
        </div>
        <div>
            <button @click="change_type(2)">比较不同商家的历史价格差异，请选择平台与时间</button>
            <input v-model="time_len" placeholder="时间填写">
            <select v-model="search_type_diff">
                <option value="0">全平台</option>
                <option value="1">淘宝</option>
                <option value="2">京东</option>
                <option value="3">闲鱼</option>
                <option value="4">NCITY</option>
            </select>
            
        </div>
    </div>
    <div>
        <div v-if="choose_type === 1">
          <table class="chart-container">
            <thead>
              <tr>
                <th>商品名</th>
                <th>商家名</th>
                <th>平台名</th>
                <th>现在价格</th>
                <th>历史最低价格</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in search_result" :key="record.id">
                <td>{{ record.goodsName }}</td>
                <td>{{ record.sellerName }}</td>
                <td>{{ record.platformName }}</td>
                <td>{{ record.price }}</td>
                <td>{{ record.minPrice }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div v-else-if="choose_type === 2">
          <table class="chart-container">
            <thead>
              <tr>
                <th>商品名</th>
                <th>商家名</th>
                <th>平台名</th>
                <th>历史最高价格</th>
                <th>历史最低价格</th>
                <th>历史最高最低价格的差</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in search_result" :key="record.id">
                <td>{{ record.goodsName }}</td>
                <td>{{ record.sellerName }}</td>
                <td>{{ record.platformName }}</td>
                <td>{{ record.maxPrice }}</td>
                <td>{{ record.minPrice }}</td>
                <td>{{ record.priceDiffNum }}</td>
              </tr>
            </tbody>
          </table>
        </div>
    </div>
    
  </div>
</template>


<script>

export default {
  data() {
    return {
      search_keyword: '',
      search_result:[],
      tag:window.localStorage.getItem("tag"),
      choose_type: 0,
      search_type_now: 0,
      search_type_diff:0,
      time_len: 0
    };
  },
  methods: {
    get_price_now() {
      this.$axios.post('http://localhost:8000/get_price_for_tag',{
            keyword: this.tag,
            userId: this.search_type_now
      })
      .then(res => {
          console.log(res.data.data);
          this.search_result = [];
          this.search_result = this.search_result.concat(res.data.data);  
      })
    },
    get_price_diff() {
      this.$axios.post('http://localhost:8000/get_price_difference_for_tag',{
            tag: this.tag,
            platform_id: this.search_type_diff,
            time_len: this.time_len
      })
      .then(res => {
          console.log(res.data.data);
          this.search_result = [];
          this.search_result = this.search_result.concat(res.data.data);  
      })
    },
    change_type(type){
        this.choose_type =type;
        if (type==1) {
            this.get_price_now()
        }
        else if (type==2) {
            this.get_price_diff()
        }
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
.choose{
    display: flex;
}

.choose select {
  flex: 0.3;
  text-align: center;
  font-size: 14px;
  margin-right: 40px;
  margin-bottom: 40px;
  margin-top:2px;
}

.choose button{
  flex: 0.3;
  font-size: 12px;
  padding: 5px;
}


.product-list {
  justify-content: space-between;
  width: 100%;
}
</style>
