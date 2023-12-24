<!-- ProductList.vue -->

<template>
    <div class="product-list">
      <div v-if="tag_list.length === 0">没有匹配种类</div>
      <div v-else>
        <div v-for="tag in tag_list" :key="tag" class="product-item">
          <!--img :src="product.image" alt="Product Image" class="product-image"-->
          <div class="product-details">
            <h3>种类名：{{ tag }}</h3>
          </div>
          <div class="product-details">
            <button @click = "to_tag(tag)">点击进入这个种类的比价</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        tag_list: [],
      };
    },
    created (){
    },
    methods: {
        to_tag(tag){
          var localStorage = window.localStorage;
          localStorage.setItem("tag",tag);
          this.$router.push({name:'compare_detail'});
        },
        change(new_tag_list){
          this.tag_list = [];
          this.tag_list = new_tag_list;
        }
    }
  };
  </script>
  
  <style scoped>
  /* 在这里添加组件的样式 */
  .product-list {
    max-width: 800px;
    margin: auto;
  }
  
  .product-item {
    display: flex;
    border: 1px solid #ccc;
    margin-bottom: 16px;
    padding: 16px;
  }
  
  .product-image {
    max-width: 100px;
    margin-right: 16px;
  }
  
  .product-details {
    flex-grow: 1;
  }
  
  h2 {
    text-align: center;
  }
  
  /* 可根据需要添加更多样式 */
  </style>
  