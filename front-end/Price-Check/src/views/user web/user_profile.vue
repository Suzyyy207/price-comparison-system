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
          <div>
            <label>姓名:</label> {{ user.name }}
          </div>
          <div>
            <label>性别:</label> {{ user.sex }}
          </div>
          <div>
            <label>年龄:</label> {{ user.age }}
          </div>
          <div>
            <label>电话:</label> {{ user.telephone }}
          </div>
          <button @click="goToEditProfile">编辑个人信息</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        loading: true,
        user: {},
        userInfo: '' // 从 Local Storage 或后端获取用户信息
      };
    },
    mounted() {
      // 模拟异步获取用户信息
      setTimeout(() => {
        // 模拟的用户信息数据
        //const userId = localStorage.getItem('username'); // 从 Local Storage 获取用户 ID
        //const userType = localStorage.getItem('userType'); // 从 Local Storage 获取用户类型
  
        // 向后端请求用户详细信息（假设后端有接口 /api/user/:id）
        // 注意：实际情况下，请根据你的后端 API 进行调整
        // axios.get(`/api/user/${userId}?type=${userType}`)
        //   .then(response => {
        //     this.user = response.data;
        //     this.loading = false;
        //   })
        //   .catch(error => {
        //     console.error('Error fetching user information:', error);
        //   });
  
        // 模拟的用户信息
        this.user = {
          name: '张三',
          sex: '男',
          age: 25,
          telephone: '12770921'
        };
  
        this.userInfo = `${this.user.name}（${userType}）`; // 用户信息字符串
        this.loading = false;
      }, 1000); // 模拟1秒延迟获取数据
    },
    methods: {
      goToEditProfile() {
        this.$router.push('/edit-profile');
      }
    }
  };
  </script>
  
  <style scoped>
  /* 在这里添加组件样式 */
  .header {
    background-color: #3498db;
    color: #ffffff;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .header-left {
    display: flex;
    align-items: center;
  }
  
  .header-right {
    display: flex;
  }
  
  .header-right router-link {
    margin-left: 10px;
    color: #ffffff;
    text-decoration: none;
  }
  
  .profile-container {
    max-width: 600px;
    margin: auto;
  }
  
  h2 {
    text-align: center;
    color: #333333;
    margin-top: 20px;
  }
  
  label {
    font-weight: bold;
    margin-right: 5px;
  }
  
  button {
    margin-top: 10px;
    padding: 5px 10px;
    background-color: #4caf50;
    color: #ffffff;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #45a049;
  }
  </style>
  