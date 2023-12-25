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
                <option value="淘宝">淘宝</option>
                <option value="京东">京东</option>
                <option value="闲鱼">闲鱼</option>
                <option value="NCITY">NCITY</option>
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
    };
  },
  created(){
    this.get_product_profile();
  },
    methods: {
      get_product_profile() {
        var localStorage = window.localStorage;
        if (localStorage.getItem("new") == 1) {
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
        }
        this.loading =false;
        
      },
      save(){
        var localStorage = window.localStorage;
        if (localStorage.getItem("new") != 1){
          this.$axios.post('http://localhost:8000/insert_goods', {
          category: this.product.category,
          location: this.product.location,
          name: this.product.name,
          platformName: this.product.platformName,
          price: this.product.price,
          productionDate: this.product.productionDate,
          sellerName:  window.localStorage.getItem("user_name"),
          tag: this.product.tag
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("您已在这个平台上发布过同种类商品");
            }
            else{
              this.$router.push({name:'seller_web'});
            }
        })
      }
      else{
        console.log("hi")
        this.$axios.post('http://localhost:8000/update_goods', {
          category: this.product.category,
          location: this.product.location,
          name: this.product.name,
          platformName: this.product.platformName,
          price: this.product.price,
          productionDate: this.product.productionDate,
          sellerName: window.localStorage.getItem("user_name"),
          tag: this.product.tag,
          userId: window.localStorage.getItem("user_id"),
          userType: window.localStorage.getItem("user_type"),
          goodsId: window.localStorage.getItem("goods_id")
        })
        .then(res => {
          console.log(res.data.data)
            if (res.data.data == false) {
              alert("更新失败");
            }
            else{
              this.$router.push({name:'seller_web'});
            }
        })
      }
        
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
  