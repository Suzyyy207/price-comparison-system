<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="seller_list.length === 0">目前还没有任何用户^^</div>
      <div v-else>
        <div v-for="seller in seller_list" :key="seller.id" class="product-item">
          <div class="product-details">
            <h3>{{ seller.name }}</h3>
          </div>
          <div class="product-details">
            <h3>Datail</h3>
            <p>地址：{{ seller.address }}</p>
          </div>
          <div class="product-details">
            <button @click="goto_seller_info(seller.id)">修改信息</button>
          </div>
          <div class="product-details">
            <button @click="delete_seller(seller.id)">删除这个用户</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        seller_list: [],
      };
    },
    created (){
        this.get_all_sellers();
        window.localStorage.setItem("user_id",1);
        window.localStorage.setItem("user_type",0);
        window.localStorage.setItem("new",0);
    },
    methods: {
        get_all_sellers() {
            const manager_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_all_sellers',{
              id: manager_id
            })
            .then(res => {
              console.log(res.data.data);
              this.seller_list = [];
                this.seller_list = this.seller_list.concat(res.data.data);
            })
        },
        goto_seller_info(id){
          window.localStorage.setItem("user_id",id);
          window.localStorage.setItem("new",1);
          this.$router.push({name:'change_seller'});
        },
        delete_seller(id){
          this.$axios.post('http://localhost:8000/delete_seller',{
              user_id: id
            })
            .then(res => {
                if (res.data.data == false) {
                  alert("失败");
                }
                this.get_all_sellers();
            })

        }
    }
    
  };
</script>
  
<style scoped>
  /* 在这里添加组件的样式 */
  .product-list {
    max-width: 800px;
    margin: auto;
    display: flex;
  }
  
  .product-item {
    display: flex;
    border: 1px solid #ccc;
    margin: 10px 30px 20px 80px;
    padding: 16px;
    width: 600px;
  }
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  
  /* 可根据需要添加更多样式 */
</style>
  