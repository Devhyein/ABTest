<template>
  <div id="sample">
    <img :src="encar" class="mylogo my-5" alt="encar logo" />
    <div class="mx-4 mb-5">
      <h2 class="mb-5">로그인</h2>
      <b-form-input
        class="mytext my-3"
        placeholder="아이디를 입력하세요."
        v-model="id"
      ></b-form-input>
      <b-form-input
        class="mytext my-3"
        placeholder="비밀번호를 입력하세요."
        type="password"
        v-model="pw"
      ></b-form-input>
      <b-button block class="mytext my-3" variant="dark" @click="login()">로그인</b-button>
      <h5 class="mytext text-right" @click="join()">회원가입</h5>
    </div>
  </div>
</template>

<script>
import encar from "@/assets/encar.png";
import API from "@/api/API";
import swal from "sweetalert";

export default {
  name: "Login",
  data() {
    return {
      encar,
      id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      let data = {};
      data.id = this.id;
      data.pw = this.pw;
      API.login(
        data,
        (res) => {
          console.log(res);
          swal("로그인 완료", "정상적으로 로그인 되었습니다.", "success");
          this.$store.commit("addUserInfo", res);
          console.log(this.$store.state.user);
          //여기는 이후에 메인으로 가게 변경
          this.$router.push({name: 'SampleA'})
        },
        (err) => {
          console.log(err);
          swal("로그인 실패", "로그인에 실패하였습니다.", "error");
          this.reset();
        }
      );
    },
    join() {
      this.$router.push({name: 'Join'})
    },
    reset() {
      (this.id = ""), (this.pw = "");
    },
  },
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