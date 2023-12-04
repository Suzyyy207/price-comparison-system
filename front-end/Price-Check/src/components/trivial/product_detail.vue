<template>
    <div>
      <!-- 商品信息的展示 -->
      <div class="product-info">
        <div class="collect">
          <h2>{{ product.name }}</h2>
          <button @click="toggleFavorite" class="favorite-button">
            {{ isFavorite ? '取消收藏' : '收藏' }}
         </button>
        </div>
        <div class="info">
          <p>产地：{{ product.origin }}</p>
          <p>种类：{{ product.category }}</p>
          <p>价格：{{ product.currentPrice }}</p>
        </div>
        
        <p>简介：{{ product.description }}</p>
  
        <!-- 收藏按钮 -->
        
      </div>
  
      <!-- 商品历史价格对比 -->
      <div class="price-comparison">
        <div class="price-info">
          <h3>历史价格对比</h3>
          <!-- 选择时间的下拉框 -->
          <select v-model="selectedTimePeriod">
            <option value="7">最近7天</option>
            <option value="30">最近30天</option>
            <option value="90">最近90天</option>
          </select>
          <button>
              查询价格变化
          </button>
        </div>
      
        <!-- 显示历史价格对比的图表 -->
        <div >
          <table class="chart-container">
            <thead>
              <tr>
                <th>时间</th>
                <th>价格</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="priceRecord in historicalPrices" :key="priceRecord.id">
                <td>{{ priceRecord.time }}</td>
                <td>{{ priceRecord.price }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      product: {
        type: Object,
        
        required: true
      }
    },
    data() {
      return {
        isFavorite: false,
        product: {
            name: "盼盼面包",
            origin: "这里",
            category: "面包",
            currentPrice: 12,
            description: "nct127",
        },
        selectedTimePeriod: '7',// 默认选择最近7天
        historicalPrices: [
      { id: 1, time: '2023-01-01', price: 18.99 },
      { id: 2, time: '2023-01-02', price: 17.99 },
      { id: 3, time: '2023-01-03', price: 16.99 },
      // ... 更多历史价格数据
    ]
      };
    },
    methods: {
      toggleFavorite() {
        this.isFavorite = !this.isFavorite;
        // 在这里可以将收藏状态发送给后端保存，或者进行其他逻辑处理
        // 暂时只是在控制台输出
        console.log(`商品 ${this.product.name} 已${this.isFavorite ? '收藏' : '取消收藏'}`);
      }
    }
  };
  </script>
  
<style>
/* 商品信息区域样式 */
.product-info {
  margin-bottom: 20px;
}

.collect{
  display: flex;
  margin-top: 25px;
}
.collect h2{
  flex: 1;
  text-align: center;
}
.collect button{
  flex:0.3;
}

.info{
  display: flex;
}
.info p{
  flex: 1;
}

.product-info h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.product-info p {
  margin-top: 20px;
  margin-bottom: 10px;
}

/* 收藏按钮样式 */
button {
  background-color: #3498db;
  color: #fff;
  padding: 8px 12px;
  font-size: 14px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
}

/* 商品历史价格对比区域样式 */
.price-comparison {
  margin-top: 20px;
}

.price-comparison h3 {
  font-size: 18px;
  margin-bottom: 10px;
  margin-right: 30px;
}

.price-info{
  display: flex;
}

.price-info h3{
  flex: 0.8;
  text-align: center;
}
/* 下拉框样式 */
.price-info select {
  flex: 0.3;
  text-align: center;
  font-size: 14px;
  margin-right: 40px;
  margin-left: -20px;
  margin-top:2px;
}

.price-info button{
  flex: 0.3;
  font-size: 12px;
  padding: 3px;
}

/* 图表区域样式 */
.chart-container {
  margin: auto;
  margin-top: 20px;
  width: 70%;
  height: auto;
  border: 1px solid #ccc;
}

tbody{
  text-align: center;
}


</style>
  