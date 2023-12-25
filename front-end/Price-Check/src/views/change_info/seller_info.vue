<!-- EditProfile.vue -->
<script setup>
import top_nav from '../../components/trivial/seller_nav.vue'
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
              <label>商家名:</label>
              <input v-model="user.name" required />
            </div>
            <div>
              <label>地址</label>
              <input v-model="user.address" required />
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
      loading: true,
      user: {},
    };
  },
  created(){
    this.get_user_profile();
  },
    methods: {
      get_user_profile() {
        var localStorage = window.localStorage;
        //console.log(localStorage.getItem("user_id"));
        this.$axios.post('http://localhost:8000/get_seller_profile', {
          user_id: localStorage.getItem("user_id"),
          
        })
        .then(res => {
          console.log(res.data.data)
            this.user = res.data.data;
            this.loading =false;
        })
      },
      save(){
        var localStorage = window.localStorage;
        console.log(this.user);
        //console.log(localStorage.getItem("user_id"));
        this.$axios.post('http://localhost:8000/update_seller', {
          id: localStorage.getItem("user_id"),
          address: this.user.address,
          age: 0,
          name: this.user.name,
          phone: 0,
          sex: 0
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("修改失败");
            }
            else{
              this.$router.push({name:'seller_profile'});
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
  