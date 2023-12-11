<!-- ProductList.vue -->

<template>
    <div class="message-list-container">
      <div v-if="loading">加载中...</div>
      <div v-else>
        <div v-for="message in message_list" :key="message.id" class="message-item">
          <div class="message-content">
            <p>{{ message.name }} 已经低于您设定的最低价！</p>
            <p>降价后价格：{{ message.current_price }}</p>
            <p>你设定价格：{{ message.target_price }}</p>
          </div>
          <div class="message-details">
            <p>日期：{{ message.date }}</p>
            <p v-if="message.is_read">状态：已读</p>
            <p v-else>状态：未读</p>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
      loading: true,
      message_list: [],
    };
  },
  created() {
        this.get_user_message();
    },
  methods: {
    get_user_message(){
        var localStorage = window.localStorage;
        this.$axios.post('http://localhost:8000/get_user_message', {
            id: localStorage.getItem("user_id")
        })
        .then(res => {
            this.message_list = this.products_list.concat(res.data.data);
            this.loading = false;
        })
      },
    }
};
</script>

<style scoped>
.message-list-container {
  max-width: 600px;
  margin: auto;
}

h2 {
  text-align: center;
  color: #333333;
  margin-top: 20px;
}

.message-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}

.message-details {
  text-align: right;
  font-size: 12px;
}

.message-details p {
  margin: 0;
}
</style>