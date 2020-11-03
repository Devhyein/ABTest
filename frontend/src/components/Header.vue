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
          <b-form-input v-model="pw" type="password"/>
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

    <b-modal title="로그아웃" v-model="logoutShow">
      로그아웃 하시겠습니까?
      <template #modal-footer>
        <b-button @click="close()" variant="danger">
          취소
        </b-button>
        <b-button @click="logout()" variant="success">
          로그아웃
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
      logoutShow: false,
      id: "",
      pw: "",
    };
  },
  methods: {
    loginModal() {
      if ( this.$store.state.email != "")  this.logoutShow = !this.logoutShow;
      else this.modalShow = !this.modalShow;
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
          this.$store.commit("addUserInfo", res);

          this.modalShow = !this.modalShow;
          this.$router.push("/main");
          location.reload();
        },
        (err) => {
          console.log(err);
          swal("로그인 실패", "로그인에 실패하였습니다.", "error");
          this.reset();
        }
      );
    },
    logout(){
       this.$store.commit("deleteUserInfo");
       this.id = "";
       this.pw = "";
       this.logoutShow = !this.logoutShow;
       location.reload();
    },
    reset() {
      (this.id = ""), (this.pw = "");
      this.modalShow = !this.modalShow;
    },
    close(){
      this.logoutShow = !this.logoutShow;
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
