<template>
    <div>
      <!-- 商品信息的展示 -->
      <div class="product-info">
        <div class="collect">
          <h2>{{ product.name }} 具体种类：{{ product.tag }}</h2>
          <button @click="collect_change(product)" class="favorite-button">
            {{ product.is_collect ? '取消收藏' : '收藏' }}
         </button>
        </div>
        <div class="info">
          <p>产地：{{ product.location }}</p>
          <p>种类：{{ product.category }}</p>
          <p>生产日期：{{ product.production_date }}</p>
        </div>
        <div class="info">
          <p>价格：{{ product.price }}</p>
          <p>商家：{{ product.seller_name }}</p>
          <p>平台：{{ product.platform_name }}</p>
        </div>
        
  
        
      </div>
  
      <!-- 商品历史价格对比 -->
      <div class="price-comparison">
        <div class="price-info">
          <h3>历史价格对比</h3>
          <!-- 选择时间的下拉框 -->
          <select v-model="time_len">
            <option value="-1">全部</option>
            <option value="7">最近7天</option>
            <option value="30">最近30天</option>
            <option value="365">最近365天</option>
          </select>
          <button @click="get_history()">
              查询价格变化
          </button>
        </div>
      
        <!-- 显示历史价格对比的图表 -->
        <div>
          <div class="price-info">
            <h3>最低价格: {{ this.min_price }}</h3>
            <h3>日期: {{ this.min_time }}</h3>
          </div>
          
          <table class="chart-container">
            <thead>
              <tr>
                <th>时间</th>
                <th>价格</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in histories" :key="record.id">
                <td>{{ record.pDate }}</td>
                <td>{{ record.price }}</td>
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
        product: {
            name: "",
            location: "",
            category: "",
            min_price: 0,
            price: 0,
            platform_name: "",
            seller_name: "",
            production_date: "",
            is_collect: false,
            tag: ""
        },
        time_len: "-1",// 默认选择全部
        histories: [],
        min_price: 0,
        min_time: ""
      };
    },
    created (){
      this.get_good();
      this.get_history();
    },
    methods: {
      get_good(){
        const goods_id = window.localStorage.getItem('goods_id');
        const user_id = window.localStorage.getItem('user_id');
        this.$axios.post('http://localhost:8000/get_good',{
            goodsId: goods_id,
            userId: user_id
        })
        .then(res => {
          console.log(res.data.data)
            const product_info = res.data.data;
            this.product.name = product_info.name;
            this.product.location = product_info.location;
            this.product.category = product_info.category;
            this.product.price = product_info.price;
            this.product.min_price = product_info.minPrice;
            this.product.platform_name = product_info.platformName;
            this.product.seller_name = product_info.sellerName;
            this.product.production_date = product_info.productionDate;
            this.product.tag = product_info.tag;
            if(product_info.isCollect == 0){
              this.product.is_collect = false;
            }
            else{
              this.product.is_collect = true;
            }
        })
      },
      get_history(){
        const goods_id = window.localStorage.getItem('goods_id');
        this.$axios.post('http://localhost:8000/get_history_price',{
            goods_id: goods_id,
            time_len: this.time_len
        })
        .then(res => {
            this.histories  = [];
            this.histories = this.histories.concat(res.data.data);
            this.get_min();
        })
      },
      get_min(){
        if (this.histories.length > 0 ) {
              this.min_price = this.histories[0].price;
              this.min_time = this.histories[0].pDate;
              console.log(this.histories.length);
              var i = 0;
              while (i < this.histories.length) {
                if (this.min_price > this.histories[i].price) {
                  this.min_price = this.histories[i].price;
                  this.min_time = this.histories[i].pDate;
                }
                i++;
              }
            }
          else{
            this.min_price = "";
              this.min_time = "";
          }
      },
      collect_change(product){
        if (product.is_collect) {
          this.$axios.post('http://localhost:8000/cancel_collect',{
            goodsId: window.localStorage.getItem("goods_id"),
            userId: window.localStorage.getItem("user_id"),
            expectPrice: -1,
          })
          .then(res => {
              console.log(res.data.data);
              product.is_collect = false;
          })
        }
        else{
            this.$axios.post('http://localhost:8000/set_collect',{
            goodsId: window.localStorage.getItem("goods_id"),
            userId: window.localStorage.getItem("user_id"),
            expectPrice: -1,
          })
          .then(res => {
              console.log(res.data.data);
              product.is_collect = true;
          })
        }
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
  margin-top: 15px;
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
  