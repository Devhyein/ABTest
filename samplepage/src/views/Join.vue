<template>
  <b-container fluid class="container">
    <b-card bg-variant="light" @submit.stop.prevent="join">
      <img :src="encar" class="mylogo" alt="encar logo" />
      <b-row class="my-1">
        <b-col sm="2">
          <label for="input-default">아이디</label>
        </b-col>
        <b-col sm="8">
          <b-form-input
            id="input-default"
            v-model="form.id"
            placeholder="Enter Id"
          ></b-form-input>
        </b-col>
        <b-button v-if="!isChecked" @click="checkId()" variant="success"
          >중복 체크</b-button
        >
        <b-button v-else disabled variant="success">체크 완료</b-button>
      </b-row>
      <b-row class="my-2">
        <b-col sm="2">
          <label for="input-pw">비밀번호</label>
        </b-col>
        <b-col sm="9">
          <b-form-input
            id="input-pw"
            v-model="form.pw"
            placeholder="Enter Password"
            type="password"
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-3">
        <b-col sm="2">
          <label for="input-chpw">비밀번호 확인</label>
        </b-col>
        <b-col sm="9">
          <b-form-input
            id="input-chpw"
            :state="checkPw"
            v-model="chpw"
            placeholder="Enter Password"
            type="password"
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-4">
        <b-col sm="2">
          <label for="input-gender">성별</label>
        </b-col>
        <b-form-radio-group
          v-model="form.gender"
          :options="gender_options"
        ></b-form-radio-group>
      </b-row>
      <b-row class="my-5">
        <b-col sm="2">
          <label for="input-gender">연령대</label>
        </b-col>
        <b-col sm="9">
          <b-form-select
            v-model="form.age"
            :options="age_options"
          ></b-form-select>
        </b-col>
      </b-row>

      <b-button class="joinBtn" @click="join()" variant="primary"
        >회원가입</b-button
      >
      <b-button class="resetBtn" @click="reset()" variang="secondary"
        >초기화</b-button
      >
    </b-card>
  </b-container>
</template>


<script>
import encar from "@/assets/encar.png";
import API from "@/api/API";
import swal from "sweetalert";

export default {
  data() {
    return {
      encar,
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
      age_options: [
        { text: "20대", value: 20 },
        { text: "30대", value: 30 },
        { text: "40대", value: 40 },
        { text: "50대", value: 50 },
        { text: "60대", value: 60 },
        { text: "70대 이상", value: 70 },
      ],
      isChecked: false,
    };
  },
  computed: {
    checkPw() {
      return this.form.pw == this.chpw ? true : false;
    },
  },
  methods: {
    checkId() {
      API.checkId(
        "id=" + this.form.id,
        (res) => {
          if (!res) {
            swal("ERROR", "이미 존재하는 아이디입니다.", "error");
            this.form.id = "";
          } else {
            swal("SUCCESS", "사용 가능한 아이디입니다.", "success");
            this.isChecked = true;
            document
              .getElementById("input-default")
              .setAttribute("readonly", true);
          }
        },
        (err) => {
          console.log(err);
          swal("ERROR", "중복 체크에 실패하였습니다.", "error");
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
      let data = {};
      data.id = this.form.id;
      data.pw = this.form.pw;
      data.age = this.form.age;
      data.gender = this.form.gender;

      API.join(
        data,
        (res) => {
          console.log(res);
          swal("SUCCESS", "회원가입이 완료되었습니다.", "success");
        },
        (err) => {
          console.log(err);
          swal("ERROR", "회원가입에 실패하였습니다.", "error");
        }
      );
    },
    reset() {
      (this.form.id = ""),
        (this.form.pw = ""),
        (this.chpw = ""),
        (this.form.age = "20"),
        (this.form.gender = "남성");
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 100px;
}
.form_border {
  border: 1px black solid;
}
.mylogo {
  height: 40px;
  margin: 40px;
}
.joinBtn {
  margin-right: 10px;
}
.resetBtn {
  margin-left: 10px;
}
</style>