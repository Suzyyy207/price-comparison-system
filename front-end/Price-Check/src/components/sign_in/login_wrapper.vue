<!-- Login.vue -->

<template>
    <div class="login-box">
      <h2>Login</h2>
      <form @submit.prevent="login">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" v-model="user_id" required>
        <label for="user_type">User Type:</label>
        <input type="text" id="user_type" v-model="user_type" required>
        <button type="submit">Login</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        user_id: "",
        user_type: ""
      };
    },
    created(){
        this.clearLocalStorage();
    },
    methods: {
        login: function(){
            //????
            this.$axios.post('/userlogin', {
                user_id: this.user_id,
                user_type: this.user_type
            })
            .then(res => {
                const user = res.data.data;
                if (res.data.state == window.SUCCESS) {
                    var localStorage = window.localStorage;
                    localStorage.setItem("userid",this.userid);
                    localStorage.setItem("username",this.username);
                    localStorage.setItem("usertype",this.usertype);

                    setTimeout(() => {
                    switch (user.usertype) {
                        case window.USER:
                            this.$router.push({name:'user_web'});
                            break;
                        case window.SELLER:
                            this.$router.push({name:'seller_web'});
                            break;
                        case window.MANAGER:
                            this.$router.push({name:'manager_web'});
                            break;
                        default:
                            this.$message.error("连接错误，请重新登录！");
                            this.$router.push({name:'home'});
                    }
                    }, 1000);
                }
                else {
                    this.$message.error(res.data.message);
                }
            })
    
        },
        clearLocalStorage() {
            localStorage.clear();
        }
    }
  };
  </script>
  
  <style scoped>
  /* 在这里添加组件的样式 */
  .login-box {
    max-width: 300px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  label {
    margin-bottom: 8px;
  }
  
  input {
    padding: 8px;
    margin-bottom: 16px;
  }
  
  button {
    padding: 10px;
    background-color:  hsla(160, 100%, 37%, 1);
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
  }
  </style>
  