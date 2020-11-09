<template>
  <div>
    <EncarHeader />
    <b-row class="my-1">
      <b-col sm="3">
        <label>실험명 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.test_title"
          placeholder="실험명을 입력해주세요"
        />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>A안 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.url_a"
          placeholder="A안의 URL을 입력해주세요"
        />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>A 별칭 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.test_a"
          placeholder="A안의 별칭을 입력해주세요"
        />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>B안 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.url_b"
          placeholder="B안의 URL을 입력해주세요"
        />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>B안 별칭 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.test_b"
          placeholder="B안의 별칭을 입력해주세요"
        />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>시작일 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input
          v-model="input.start"
          type="date"
          :max="input.end"
          :min="today"
        />
      </b-col>
    </b-row>
    <b-row class="my-1">
      <b-col sm="3">
        <label>종료일 :</label>
      </b-col>
      <b-col sm="9">
        <b-form-input v-model="input.end" type="date" :min="input.start" />
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="3">
        <label>전환율 분석페이지 :</label>
      </b-col>
      <b-col sm="9">
        <table>
          <tbody>
            <tr v-for="(row, idx) in rows" :key="idx">
              <td><input type="text" v-model="row.url" /></td>
              <td>
                <b-icon-dash-circle @click="removeRow(idx)" />
              </td>
            </tr>
          </tbody>
        </table>
        <b-icon-plus-circle @click="addRow" />
      </b-col>
    </b-row>

    <div>
      <div class="mx-3">
        <div class="d-flex justify-content-between">
          <div class="mt-2">A안:{{ input.per_a }}%</div>
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
    <b-button @click="dataCheck()" variant="success"> 생성 </b-button>
    <b-button @click="reset()"> 초기화 </b-button>
  </div>
</template>

<script>
import API from "@/api/API";
import EncarHeader from "@/components/Header";
import swal from "sweetalert";

export default {
  components: {
    EncarHeader,
  },
  data() {
    return {
      today: new Date(),
      input: {
        test_title: "",
        url_a: "",
        test_a: "",
        url_b: "",
        test_b: "",
        start: "",
        end: "",
        per_a: 50,
        per_b: 50,
      },
      rows: [{ url: "" }],
    };
  },
  computed: {
    email() {
      return this.$store.state.email;
    },
  },
  methods: {
    addRow() {
      if (this.rows.length >= 5) {
         swal("추가 실패", "전환율 분석페이지는 최대 5개까지 설정 가능합니다.", "error");
        return;
      }
      this.rows.push({ url: "" });
      console.log(this.rows);
    },
    removeRow(idx) {
      this.rows.splice(idx, 1);
      console.log(this.rows);
    },
    dataCheck() {
      let err = false;
      let msg = "";
      !this.input.test_title && ((msg = "실험명을 입력해주세요"), (err = true));
      !err &&
        !this.input.url_a &&
        ((msg = "A안의 URL을 입력해주세요"), (err = true));
      !err &&
        !this.input.test_a &&
        ((msg = "A안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.input.url_b &&
        ((msg = "B안의 URL을 입력해주세요"), (err = true));
      !err &&
        !this.input.test_b &&
        ((msg = "B안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.input.start &&
        ((msg = "시작일을 설정해주세요"), (err = true));
      !err &&
        !this.input.end &&
        ((msg = "종료일을 설정해주세요"), (err = true));
      if (err) swal(msg);
      else this.createTest();
    },
    createTest() {
      var perA = parseInt(this.input.per_a);
      let data = {};
      data.email = this.email;
      data.test_title = this.input.test_title;
      data.test_a = this.input.test_a;
      data.url_a = this.input.url_a;
      data.test_b = this.input.test_b;
      data.url_b = this.input.url_b;
      data.start = this.input.start;
      data.end = this.input.end;
      data.per_a = perA;
      data.per_b = 100 - this.input.per_a;
      data.list = this.list;

      console.log(data);

      API.createTest(
        data,
        (res) => {
          console.log(res);
          swal("생성 완료", "실험이 생성되었습니다.", "success");
          this.$router.push("/main");
        },
        (err) => {
          console.log(err);
          swal("생성 실패", "실험생성에 실패하였습니다.", "error");
        }
      );
    },
    reset() {
      (this.input.test_title = ""),
        (this.input.test_a = ""),
        (this.input.url_a = ""),
        (this.input.test_b = ""),
        (this.input.url_b = ""),
        (this.input.start = ""),
        (this.input.end = ""),
        (this.input.per_a = 50),
        (this.input.per_b = 50),
        (this.list = "");
    },
  },
  created() {
    this.per_b = 100 - this.per_a;

    let month = "";
    if (new Date().getMonth() + 1 < 10) {
      month = "0" + (new Date().getMonth() + 1);
    } else {
      month = new Date().getMonth() + 1;
    }
    let day = "";
    if (new Date().getDate() < 10) {
      day = "0" + new Date().getDate();
    } else {
      day = new Date().getDate();
    }

    this.today = new Date().getFullYear() + "-" + month + "-" + day;
  },
};
</script>
