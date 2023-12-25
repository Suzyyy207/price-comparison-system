<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="user_list.length === 0">目前还没有任何用户^^</div>
      <div v-else>
        <div v-for="user in user_list" :key="user.id" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>{{ user.name }}</h3>
            <p>性别：{{ user.sex }}</p>
          </div>
          <div class="product-details">
            <h3>Datail</h3>
            <p>年龄：{{ user.age }}</p>
            <p>电话：{{ user.phone }}</p>
          </div>
          <div class="product-details">
            <button @click="goto_user_info(user.id)">修改信息</button>
          </div>
          <div class="product-details">
            <button @click="delete_user(user.id)">删除这个用户</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        user_list: [],
      };
    },
    created (){
        this.get_all_users();
        window.localStorage.setItem("user_id",1);
        window.localStorage.setItem("user_type",0);
        window.localStorage.setItem("new",0);
    },
    methods: {
        get_all_users() {
            const manager_id = window.localStorage.getItem('user_id');
            this.$axios.post('http://localhost:8000/get_all_users',{
              id: manager_id
            })
            .then(res => {
                this.user_list = [];
                this.user_list = this.user_list.concat(res.data.data);
                for (let index = 0; index < this.user_list.length; index++) {
                  if (this.user_list[index].sex==1) {
                    this.user_list[index].sex = "男";
                  }
                  else{
                    this.user_list[index].sex = "女";
                  }
                  
                }
            })
        },
        goto_user_info(id){
          window.localStorage.setItem("user_id",id);
          window.localStorage.setItem("new",1);
          this.$router.push({name:'change_user'});
        },
        delete_user(id){
          this.$axios.post('http://localhost:8000/delete_user',{
              user_id: id
            })
            .then(res => {
                if (res.data.data == false) {
                  alert("失败");
                }
                this.get_all_users();
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
  