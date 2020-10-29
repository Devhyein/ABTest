<template>
  <div>
      <div>
        <h2>LogIn</h2>
      </div>
    <div>ID <input type="text" placeholder="아이디" /></div>
    <div>PW <input type="password" placeholder="비밀번호" /></div>
    <div>
      <button @click="join()">회원가입</button>
      <button @click="login()">로그인</button>
    </div>
  </div>
</template>
<script>
export default {
  data: () => {},
  methods: {
    join() {
      this.$router.push("/join");
    },
    login() {},
  },
};
</script>
