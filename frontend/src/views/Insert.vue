<template>
  <div>
    <EncarHeader />
    <b-row class="my-1">
      <b-col sm="3">
        <label>실험명 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.test_title" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>A안 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.test_aURL" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>A 별칭 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.test_a" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>B안 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.test_bURL" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>B안 별칭 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.test_b" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>시작일 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.start" type="date" />
      </b-col>
    </b-row>
    <b-row class="my-1">
      <b-col sm="3">
        <label>종료일 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.end" type="date" />
      </b-col>
    </b-row>

    <div>
      <div class="mx-3">
        <div class="d-flex justify-content-between">
          <div class="mt-2">A안: {{ input.per_a }}%</div>
          <div class="mt-2">B안:{{ 100 - input.per_a }}%</div>
        </div>
        <b-form-input
          id="range"
          v-model="input.per_a"
          type="range"
          min="0"
          max="100"
          step="10"
        ></b-form-input>
      </div>
    </div>
    <b-button @click="createTest()" variant="success">
      생성
    </b-button>
    <b-button @click="reSet()">
      초기화
    </b-button>
  </div>
</template>

<script>
import API from "@/api/API";

import EncarHeader from "@/components/Header";
import swal from "sweetalert";

export default {
  name: "Home",
  components: {
    EncarHeader,
  },
  data() {
    return {
      input: {
        email: "",
        test_title: "",
        test_a: "",
        test_aURL: "",
        test_b: "",
        test_bURL: "",
        start: "",
        end: "",
        per_a: 50,
        per_b: "",
        status: "",
      },
    };
  },
  methods: {
    createTest() {
      let data = {};
      data.email = this.email;
      data.test_title = this.test_title;
      data.test_a = this.test_a;
      data.test_b = this.test_b;
      data.start = this.start;
      data.end = this.end;
      data.per_a = this.per_a;
      data.per_b = this.per_b;
      data.status = this.status;

      API.createTest(
        data,
        (res) => {
          console.log(res);
          swal("생성 완료", "실험이 생성되었습니다.", "success");
        },
        (err) => {
          console.log(err);
          swal("생성 실패", "실험 생성에 실패하였습니다.", "error");
        }
      );
    },
    reSet() {
      (this.email = ""),
        (this.test_title = ""),
        (this.teat_a = ""),
        (this.test_aURL = ""),
        (this.test_b = ""),
        (this.test_bURL = ""),
        (this.start = ""),
        (this.end = ""),
        (this.per_a = ""),
        (this.per_b = ""),
        (this.status = "");
    },
  },
  created() {
    this.per_b = 100 - this.per_a;
  },
};
</script>
