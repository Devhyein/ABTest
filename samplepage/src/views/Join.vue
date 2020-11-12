<template>
  <div id="sample">
    <img :src="encar" class="mylogo my-5" alt="encar logo" />
    <div class="mx-4 mb-5">
      <h2 class="mb-5">회원가입</h2>
      <b-row class="align-items-center">
        <b-col cols="4" class="text-left my-3">
          <label for="input-id" class="m-0">아이디</label>
        </b-col>
        <b-col cols="8" class="pl-0">
          <b-form-input
            id="input-id"
            class="mytext"
            v-model="form.id"
            placeholder="아이디를 입력하세요."
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row class="justify-content-end mr-0">
        <b-button size="sm" v-if="!isChecked" @click="checkId()" variant="dark"
          >중복 체크</b-button
        >
        <b-button size="sm" v-else disabled variant="secondary"
          >체크 완료</b-button
        >
      </b-row>
      <b-row class="align-items-center">
        <b-col cols="4" class="text-left my-3">
          <label for="input-pw" class="m-0">비밀번호</label>
        </b-col>
        <b-col cols="8" class="pl-0">
          <b-form-input
            class="mytext"
            id="input-pw"
            v-model="form.pw"
            placeholder="비밀번호를 입력하세요."
            type="password"
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row class="align-items-center">
        <b-col cols="4" class="text-left my-3">
          <label for="input-chpw" class="m-0">비밀번호 확인</label>
        </b-col>
        <b-col cols="8" class="pl-0">
          <b-form-input
            class="mytext"
            id="input-chpw"
            :state="checkPw"
            v-model="chpw"
            placeholder="비밀번호를 다시 입력하세요."
            type="password"
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row class="align-items-center">
        <b-col cols="4" class="text-left my-3">
          <label for="input-gender" class="m-0">성별</label>
        </b-col>
        <b-col cols="8" class="pl-0">
          <b-form-radio-group
            v-model="form.gender"
            :options="gender_options"
          ></b-form-radio-group>
        </b-col>
      </b-row>
      <b-row class="align-items-center">
        <b-col cols="4" class="text-left my-3">
          <label for="input-gender" class="m-0">생년월일</label>
        </b-col>
        <b-col cols="8" class="pl-0">
          <b-form-input class="mytext" v-model="birth" type="date" />
        </b-col>
      </b-row>

      <b-button class="mytext mr-3 my-4" @click="join()" variant="dark"
        >회원가입</b-button
      >
      <b-button class="mytext" @click="reset()" variant="secondary"
        >초기화</b-button
      >
    </div>
  </div>
</template>

<script>
import encar from "@/assets/encar.png";
import API from "@/api/API";
import swal from "sweetalert";

export default {
  data() {
    return {
      encar,
      test_no: 2,
      page_type: "A",
      birth: "",
      form: {
        id: "",
        pw: "",
        gender: "남성",
        age: "20",
      },
      chpw: "",
      gender_options: [
        { text: "남성", value: "남성" },
        { text: "여성", value: "여성" },
      ],
      isChecked: false,
    };
  },
  computed: {
    checkPw() {
      if (this.form.pw.length == 0 || this.chpw.length == 0) return null;
      return this.form.pw == this.chpw ? true : false;
    },
  },
  methods: {
    checkId() {
      if (this.form.id.length < 1) {
        swal("ERROR", "아이디를 입력해주세요.", "error");
        return;
      }
      API.checkId(
        "id=" + this.form.id,
        (res) => {
          if (!res) {
            swal("ERROR", "이미 존재하는 아이디입니다.", "error");
            this.form.id = "";
          } else {
            swal("SUCCESS", "사용 가능한 아이디입니다.", "success");
            this.isChecked = true;
            document.getElementById("input-id").setAttribute("readonly", true);
          }
        },
        (err) => {
          console.log(err);
          swal("ERROR", "중복 체크에 실패했습니다.", "error");
        }
      );
    },
    join() {
      if (!this.isChecked) {
        swal("ERROR", "중복 체크를 해주세요.", "error");
        return;
      }
      if (
        this.chpw.length == 0 ||
        this.form.pw.length == 0 ||
        this.chpw != this.form.pw
      ) {
        swal("ERROR", "비밀번호를 확인해주세요.", "error");
        return;
      }
      var date = new Date();
      var join_date;
      if (date.getDate() < 10) {
        join_date =
          date.getFullYear() +
          "-" +
          String(date.getMonth() + 1) +
          "-0" +
          String(date.getDate());
      } else
        join_date =
          date.getFullYear() +
          "-" +
          String(date.getMonth() + 1) +
          "-" +
          String(date.getDate());
      let data = {};
      data.test_no = this.$store.state.test.test_no;
      data.page_type =this.$store.state.test.page_type;
      data.join_date = join_date;
      data.id = this.form.id;
      data.pw = this.form.pw;
      data.birth = this.birth;
      data.gender = this.form.gender;
      data.session_id = this.$store.state.test.session_id;
      console.log(data);
      API.join(
        data,
        (res) => {
          console.log(res);
          swal("SUCCESS", "회원가입이 완료되었습니다.", "success");
          this.$router.push({ name: "Login" });
        },
        (err) => {
          console.log(err);
          swal("ERROR", "회원가입에 실패했습니다.", "error");
        }
      );
    },
    reset() {
      (this.form.id = ""),
        (this.form.pw = ""),
        (this.chpw = ""),
        (this.form.age = "20"),
        (this.form.gender = "남성");
      this.birth = "";
    },
  },
};
</script>

<style scoped>
#sample {
  font-family: "IBMPlexSansKR-SemiBold";
  font-size: 14px;
}
.form_border {
  border: 1px black solid;
}
.mylogo {
  height: 60px;
}
.mytext {
  font-size: 14px;
}
</style>