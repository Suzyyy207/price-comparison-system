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
              <label>姓名:</label>
              <input v-model="user.name" required />
            </div>
            <div>
              <label>性别:</label>
              <select v-model="user.sex" required>
                <option value="1">男</option>
                <option value="0">女</option>
              </select>
            </div>
            <div>
              <label>电话号</label>
              <input v-model="user.phone" required />
            </div>
            <div>
              <label>年龄:</label>
              <input v-model="user.age" type="number" required />
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
        if (this.user.sex == "0") {
          this.user.sex = 0
        }
        else{
          this.user.sex = 1
        }
        console.log(this.user);
        
        this.$axios.post('http://localhost:8000/insert_user', {
          address: "",
          age: this.user.age,
          name: this.user.name,
          phone: this.user.phone,
          sex: this.user.sex
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("修改失败");
            }
            else{
              this.$router.push({name:'all_users'});
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
  