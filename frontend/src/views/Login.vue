<template>
  <div>
      <div>
        <h2>LogIn</h2>
      </div>
    <div>ID <input type="text" v-model = "id" placeholder="아이디" /></div>
    <div>PW <input type="password" v-model = "pw" placeholder="비밀번호" /></div>
    <div>
      <button @click="join()">회원가입</button>
      <button @click="login()">로그인</button>
    </div>
  </div>
</template>
<script>
import API from "@/api/API";
import swal from "sweetalert";

export default {
  data: () => {
    return{
      id:"",
      pw:""
    }
  },
  methods: {
    join() {
      this.$router.push("/join");
    },
    login() {
      let data = {};
      data.id = this.id;
      data.pw = this.pw;
      API.manageLogin(
        data,
        (res) => {
          console.log(res);
          swal("로그인 완료", "정상적으로 로그인 되었습니다.", "success");
          this.$router.push("/main");
        },
        (err) => {
          console.log(err);
          swal("로그인 실패", "로그인에 실패하였습니다.", "error");
        }
      );
    },
  },
};
</script>
