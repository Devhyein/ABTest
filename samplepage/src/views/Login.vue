<template>
  <div id="sample">
    <img :src="encar" class="mylogo my-5" alt="encar logo" />
    <div class="mx-4 mb-5">
      <h2 class="mb-5">로그인</h2>
      <b-form-input
        class="mytext my-3"
        placeholder="아이디를 입력하세요."
      ></b-form-input>
      <b-form-input
        class="mytext my-3"
        placeholder="비밀번호를 입력하세요."
        type="password"
      ></b-form-input>
      <b-button block class="mytext my-3" variant="dark">로그인</b-button>
      <h5 class="mytext text-right">회원가입</h5>
    </div>
  </div>
</template>

<script>
import encar from "@/assets/encar.png";
// import API from "@/api/API";
// import swal from "sweetalert";

export default {
  name: "Login",
  data() {
    return {
      encar,
      // form: {
      //   id: '',
      //   pw: '',
      // }
    };
  },
  // methods: {
  //   login() {
  //     API.login(
  //       data,
  //       (res) => {
  //         console.log(res);
  //         this.$router.push(-1);
  //       },
  //       (err) => {
  //         console.log(err);
  //         swal("ERROR", "로그인에 실패하였습니다.", "error");
  //       }
  //     );
  //   },
  //   join() {
  //     this.$router.push({name: 'Join'})
  //   }
  // },
};
</script>

<style scoped>
#sample {
  font-family: "IBMPlexSansKR-SemiBold";
}
.mylogo {
  height: 60px;
}
.mytext {
  font-size: 14px;
}
</style>