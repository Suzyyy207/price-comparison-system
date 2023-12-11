<script setup>
import top_nav from '../../components/trivial/seller_nav.vue'
</script>

<template>
    <div class = "home-page">
      <!-- 顶部导航栏 -->
      <div class="top-nav">
        <top_nav></top_nav>
      </div>
  
      <!-- 个人中心内容 -->
      <div class="profile-container">
        <h2>商家中心</h2>
        <div v-if="loading">加载中...</div>
        <div v-else>
          <div class="info">
            <label>商家名:</label> {{ user.name }}
          </div>
          <div class="info">
            <label>地址:</label> {{ user.address }}
          </div>
          <div class="info">
            <label>电话:</label> {{ user.telephone }}
          </div>
          <button @click="edit_profile">编辑商家信息</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        loading: true,
        name: "",
        address: "",
        telephone: "",
      };
    },
    created() {
        this.get_seller_profile();
    },
    methods: {
      get_seller_profile(){
        var localStorage = window.localStorage;
        this.$axios.post('http://localhost:8000/get_seller_profile', {
            id: localStorage.getItem("user_id")
        })
        .then(res => {
            const user = res.data.data;
            this.name = user.username;
            this.address = user.address;
            this.telephone = user.telephone;
            this.loading = false;
        })
      },
      edit_profile() {
        this.$router.push('/edit_profile');
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

  
  .profile-container {
    max-width: 600px;
    margin: auto;
    text-align: center;
  }
  
  h2 {
    text-align: center;
    color: #333333;
    margin-top: 20px;
  }

  .info{
    margin:15px;
  }
  
  label {
    font-weight: bold;
    margin-right: 5px;
  }
  
  button {
    margin-top: 5px;
    padding: 5px 10px;
    font-size: 18pxß;
    background-color: #4caf50;
    color: #ffffff;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #45a049;
  }
  </style>
  