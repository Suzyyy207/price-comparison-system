<!-- EditProfile.vue -->
<script setup>
import top_nav from '../../components/trivial/manager_nav.vue'
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
              <label>平台名:</label>
              <input v-model="user.name" required />
            </div>
            <button @click="save()">保存</button>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
    return {
      loading: false,
      user: {},
    };
  },
  created(){
  },
    methods: {
      save(){
        var localStorage = window.localStorage;
        console.log(this.user);
        //console.log(localStorage.getItem("user_id"));
        this.$axios.post('http://localhost:8000/update_platform', {
          id: localStorage.getItem("platform_id"),
          name: this.user.name,
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("修改失败");
            }
            else{
              this.$router.push({name:'all_platforms'});
            }
        })
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
  