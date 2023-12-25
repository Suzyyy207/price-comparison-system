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
        <h2>编辑商品信息</h2>
        <div v-if="loading">加载中...</div>
        <div v-else>
          <form @submit.prevent="saveProfile">
            <div>
              <label>商品名字: </label>
              <input v-model="product.name" required />
            </div>
            <div>
              <label>平台名: </label>
              <select v-model="product.platformName" required>
                <option v-for="platform in platform_list" :key="platform.id" :value="platform.id">{{ platform.name }}</option>
              </select>
            </div>
            <div>
              <label>价格: </label>
              <input v-model="product.price" type="number" required />
            </div>
            <div>
              <label>具体种类(同一种类将用来比价): </label>
              <input v-model="product.tag"  required />
            </div>
            <div>
              <label>模糊种类: </label>
              <input v-model="product.category" required />
            </div>
            <div>
              <label>产地: </label>
              <input v-model="product.location" required />
            </div>
            <div>
              <label>生产日期: </label>
              <input v-model="product.productionDate" type="date" required />
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
      product: {},
      platform_list:[]
    };
  },
  created(){
    this.get_product_profile();
    this.get_all_platforms();
  },
    methods: {
      get_product_profile() {
        var localStorage = window.localStorage;
          //console.log(localStorage.getItem("user_id"));
          this.$axios.post('http://localhost:8000/get_good', {
            userId: localStorage.getItem("user_id"),
            goodsId:localStorage.getItem("goods_id")
          })
          .then(res => {
            console.log(res.data.data)
              this.product = res.data.data;
              this.loading =false;
          })
        
      },
      get_all_platforms() {
            this.$axios.post('http://localhost:8000/get_all_platforms',{
              id: 1
            })
            .then(res => {
              console.log(res.data.data);
                this.platform_list = this.platform_list.concat(res.data.data);
            })
        },
      save(){
        console.log("hi")
        this.$axios.post('http://localhost:8000/update_goods', {
          category: this.product.category,
          location: this.product.location,
          name: this.product.name,
          platformName: this.product.platformName,
          price: this.product.price,
          productionDate: this.product.productionDate,
          sellerName: this.product.sellerName,
          tag: this.product.tag,
          userId: 1,
          userType: 0,
          goodsId: window.localStorage.getItem("goods_id")
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("这个商家在同平台已经发布过这类商品");
            }
            else{
              this.$router.push({name:'all_products'});
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
  