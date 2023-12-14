<script setup>
import top_nav from '../../components/trivial/top_nav.vue'
</script>

<template>
    <div class = "home-page">
      <!-- 顶部导航栏 -->
      <div class="top-nav">
        <top_nav></top_nav>
      </div>
  
      <!-- 个人中心内容 -->
      <div class="profile-container">
        <h2>个人中心</h2>
        <div v-if="loading">加载中...</div>
        <div v-else>
          <div class="info">
            <label>姓名:</label> {{ name }}
          </div>
          <div class="info">
            <label>性别:</label> {{ sex }}
          </div>
          <div class="info">
            <label>年龄:</label> {{ age }}
          </div>
          <div class="info">
            <label>电话:</label> {{ telephone }}
          </div>
          <button @click="edit_profile">编辑个人信息</button>
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
        sex: "",
        age: 0,
        telephone: "",
      };
    },
    created() {
        this.get_user_profile();
    },
    methods: {
      get_user_profile() {
        var localStorage = window.localStorage;
        //console.log(localStorage.getItem("user_id"));
        this.$axios.post('http://localhost:8000/get_user_profile', {
          user_id: localStorage.getItem("user_id"),
          
        })
        .then(res => {
          console.log(res.data.data)
            const user = res.data.data;
            this.name = user.name;
            this.sex = user.sex;
            this.age = user.age;
            this.telephone = user.phone;
            this.loading =false;
        })
      },
      edit_profile() {
        this.$router.push('/edit_profile');
      }
    },
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
  