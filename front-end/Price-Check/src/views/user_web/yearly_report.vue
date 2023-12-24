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
      <h2>两性用户种类偏好：</h2>
      <table class="chart-container">
            <thead>
              <tr>
                <th>性别</th>
                <th>商品种类</th>
                <th>排名</th>
                <th>收藏量</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in sex_list">
                <td>{{ record.sex }}</td>
                <td>{{ record.tag }}</td>
                <td>{{ record.rnk }}</td>
                <td>{{ record.count}}</td>
              </tr>
            </tbody>
          </table>
    </div>
    <div class="info">
        <h2>今年，您收藏商品种类的排名是</h2>
        <table class="chart-container">
            <thead>
              <tr>
                <th>种类名字</th>
                <th>排名</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="record in collect_list">
                <td>{{ record.tag }}</td>
                <td>{{ record.rnk }}</td>
                <td>{{ record.count }}</td>
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
      top_list:[],
      sex_list:[],
      top_id:0,
    };
  },
  created(){
    this.get_top4year();
    this.get_top4sex();
    //this.get_year_colllect()
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
    },
    get_top4sex() {
      this.$axios.post('http://localhost:8000/top_tag_for_sex',{
            user_id:0,
      })
      .then(res => {
          console.log(res.data.data);
          this.sex_list = this.sex_list.concat(res.data.data);
          for (let index = 0; index < this.sex_list.length; index++) {
            if (this.sex_list[index].sex == 1) {
                this.sex_list[index].sex = '男';
            }
            else{
                this.sex_list[index].sex = '女';
            }
            
          }
          
      })
    },
    get_year_colllect(){
        this.$axios.post('http://localhost:8000/get_collect_tag',{
            user_id:window.localStorage.getItem("user_id"),
      })
      .then(res => {
          console.log(res.data.data);
          this.collect_list = this.collect_list.concat(res.data.data);          
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

.info{
    display: block;
}
.info h2{
    margin-top: 40px;
    margin-left: 200px;
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
