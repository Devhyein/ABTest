<template>
  <div class="myheader mb-5">
    <div class="d-flex justify-content-between align-items-center py-2">
      <img :src="BorA" class="mylogo ml-3" @click="main()" />
      <div class="myprofile mr-5 text-light" @click="loginModal()">
        <b-icon icon="person-circle" scale="3" />
      </div>
    </div>

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
import BorA from '@/assets/BorA.png'

export default {
  name: "Header",
  data() {
    return {
      modalShow: false,
      logoutShow: false,
      id: "",
      pw: "",
      BorA,
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
.myheader {
  /* background-color: #000099; */
  border-bottom: 3px solid gray;
}
.a {
  clear: both;
}
.mylogo {
  width: 100px;
  cursor: pointer;
}
.myprofile {
  cursor: pointer;
  width: 50px;
}
</style>