<!-- ProductList.vue -->

<template>
    <div class="message-list-container">
      <div v-if="loading">加载中...</div>
      <div v-else>
        <div v-for="message in messages" :key="message.id" class="message-item">
          <div class="message-content">
            <p>{{ message.productName }} 已经低于您设定的最低价！</p>
            <p>当前价格：{{ message.currentPrice }}</p>
            <p>设定价格：{{ message.targetPrice }}</p>
          </div>
          <div class="message-details">
            <p>日期：{{ message.date }}</p>
            <p v-if="message.isRead">状态：已读</p>
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
      messages: [],
      userInfo: '' // 从 Local Storage 或后端获取用户信息
    };
  },
  mounted() {
    // 模拟异步获取消息列表
    setTimeout(() => {
      // 模拟的消息数据
      const userId = localStorage.getItem('userId'); // 从 Local Storage 获取用户 ID
      const userType = localStorage.getItem('userType'); // 从 Local Storage 获取用户类型

      this.messages = [
        {
          id: 1,
          productName: '商品A',
          currentPrice: 19.99,
          targetPrice: 15.99,
          date: '2023-12-01',
          isRead: true
        },
        {
          id: 2,
          productName: '商品B',
          currentPrice: 29.99,
          targetPrice: 25.99,
          date: '2023-12-02',
          isRead: false
        },
        // ... 更多消息数据
      ];

      this.userInfo = `${userType} 用户 ${userId}`; // 用户信息字符串
      this.loading = false;
    }, 1000); // 模拟1秒延迟获取数据
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