<!-- EditProfile.vue -->
<script setup>
import top_nav from '../../components/trivial/top_nav.vue'
</script>

<template>
    <div class="home-page">
      <!-- 顶部导航栏 -->
      <div class="top-nav">
        <top_nav></top_nav>
      </div>
  
      <!-- 编辑个人信息内容 -->
      <div class="edit-profile-container">
        <h2>编辑个人信息</h2>
        <div v-if="loading">加载中...</div>
        <div v-else>
          <form @submit.prevent="saveProfile">
            <div>
              <label>姓名:</label>
              <input v-model="user.name" required />
            </div>
            <div>
              <label>邮箱:</label>
              <input v-model="user.email" type="email" required />
            </div>
            <div>
              <label>年龄:</label>
              <input v-model="user.age" type="number" required />
            </div>
            <button type="submit">保存</button>
          </form>
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
      const userId = localStorage.getItem('userId'); // 从 Local Storage 获取用户 ID
      const userType = localStorage.getItem('userType'); // 从 Local Storage 获取用户类型

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
        email: 'zhangsan@example.com',
        age: 25
      };

      this.userInfo = `${this.user.name}（${userType}）`; // 用户信息字符串
      this.loading = false;
    }, 1000); // 模拟1秒延迟获取数据
  },
    methods: {
      saveProfile() {
        // 在这里可以将用户编辑后的信息提交给后端保存
        // 假设有一个保存用户信息的接口，例如 /api/user/:id
        // axios.put(`/api/user/${userId}`, this.user)
        //   .then(response => {
        //     console.log('User information saved successfully:', response.data);
        //     // 这里可以进行一些成功保存后的处理
        //   })
        //   .catch(error => {
        //     console.error('Error saving user information:', error);
        //     // 这里可以进行一些保存失败后的处理
        //   });
  
        console.log('Simulating saving user information:', this.user);
        // 模拟保存成功后的处理
        alert('用户信息保存成功！');
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

.edit-profile-container {
    max-width: 600px;
    margin: auto;
    text-align: center;
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

  input{
    margin:15px;
  }
  
  button {
    margin-top: 10px;
    padding: 5px 10px;
    background-color: #4caf50;
    color: #ffffff;
    border: none;
    cursor: pointer;
  }
</style>
  