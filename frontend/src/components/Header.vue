<template>
  <div class="headerdiv">
    <img src="/img/logo.png" class="ml-5" @click="main()" />
    <b-icon icon="person-circle" scale="2" @click="loginModal()" />

    <b-modal title="로그인" v-model="modalShow">
      <b-row class="my-1">
        <b-col sm="3">
          <label>ID : </label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="id" />
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label>PW : </label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="pw" />
        </b-col>
      </b-row>
      <template #modal-footer>
        <b-button @click="reset()" variant="danger">
          취소
        </b-button>
        <b-button @click="login()" variant="success">
          로그인
        </b-button>
      </template>
    </b-modal>

    <div class="a"></div>
  </div>
</template>

<script>
import API from "@/api/API";
import swal from "sweetalert";

export default {
  name: "Header",
  data() {
    return {
      modalShow: false,
      id: "",
      pw: "",
    };
  },
  methods: {
    loginModal() {
      this.modalShow = !this.modalShow;
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
          this.modalShow = !this.modalShow;
          this.$router.push("/main");
        },
        (err) => {
          console.log(err);
          swal("로그인 실패", "로그인에 실패하였습니다.", "error");
          this.reset();
        }
      );
    },
    reset() {
      (this.id = ""), (this.pw = "");
       this.modalShow = !this.modalShow;
    },
    main() {
      this.$router.push("/main");
    },
  },
};
</script>
<style scoped>
img {
  width: 150px;
  height: 80px;
  float: left;
}
b-icon {
  float: right;
}
.a {
  clear: both;
}
.headerdiv {
  background-color: lightgray;
  margin-bottom: 20px;
}
</style>
