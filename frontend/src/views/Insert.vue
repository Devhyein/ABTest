<template>
  <div>
    <EncarHeader />
    <div class="mx-5 my-5">
      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">실험명 :</label>
        </b-col>
        <b-col sm="9">
          <b-form-input
            v-model="input.test_title"
            placeholder="실험명을 입력하세요."
          />
        </b-col>
      </b-row>

      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">A안 별칭 :</label>
        </b-col>
        <b-col sm="9">
          <b-form-input
            v-model="input.test_a"
            placeholder="A안의 별칭을 입력하세요."
          />
        </b-col>
      </b-row>

      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">B안 별칭 :</label>
        </b-col>
        <b-col sm="9">
          <b-form-input
            v-model="input.test_b"
            placeholder="B안의 별칭을 입력하세요."
          />
        </b-col>
      </b-row>

      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">URL :</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="input.url_a" placeholder="실험을 진행할 페이지의 URL을 입력하세요." />
        </b-col>
      </b-row>

      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">실험 시작일 :</label>
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
      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">실험 종료일 :</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="input.end" type="date" :min="input.start" />
        </b-col>
      </b-row>

      <b-row class="mx-5 my-3 align-items-center">
        <b-col sm="3" class="text-left">
          <label class="m-0">전환율 분석 페이지</label>
          <b-icon-plus-circle @click="addRow" class="text-primary ml-2"></b-icon-plus-circle>
        </b-col>
        <b-col sm="9">
          <div v-for="(row, idx) in rows" :key="idx" class="d-flex justify-content-between align-items-center my-2">
            <b-form-input v-model="row.url" class="mr-3" placeholder="전환율을 분석할 페이지의 URL을 입력하세요." />
            <b-icon-dash-circle @click="removeRow(idx)" class="mr-3 text-danger"></b-icon-dash-circle>
          </div>
          <!-- <table>
            <tbody>
              <tr v-for="(row, idx) in rows" :key="idx">
                <td><input type="text" v-model="row.url" /></td>
                <td>
                  <b-icon-dash-circle @click="removeRow(idx)" />
                </td>
              </tr>
            </tbody>
          </table>
          <b-icon-plus-circle @click="addRow" /> -->
        </b-col>
        <!-- <b-col sm="9">
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
        </b-col> -->
      </b-row>

      <div>
        <div class="mx-5 my-3">
          <div class="d-flex justify-content-between mx-3">
            <div class="mt-2">A안: {{ input.per_a }}%</div>
            <div class="mt-2">B안: {{ 100 - input.per_a }}%</div>
          </div>
          <b-form-input
            class="px-3 my-3"
            id="range"
            v-model="input.per_a"
            type="range"
            min="0"
            max="100"
            step="10"
          ></b-form-input>
        </div>
      </div>
      <b-button @click="dataCheck()" variant="success" class="mybutton mr-4">생성</b-button>
      <b-button @click="reset()" class="mybutton">초기화</b-button>
    </div>
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
      urls:[],
      today: new Date(),
      input: {
        test_title: "",
        url_a: "",
        test_a: "",
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
        swal(
          "추가 실패",
          "전환율 분석페이지는 최대 5개까지 설정 가능합니다.",
          "error"
        );
        return;
      }
      this.rows.push({ url: "" });
      console.log(this.rows);
    },
    removeRow(idx) {
       if (this.rows.length == 1) {
        swal(
          "삭제 실패",
          "최소 1개의 전환율 분석페이지가 필요합니다.",
          "error"
        );
        return;
      }

      this.rows.splice(idx, 1);
      console.log(this.rows);
    },
    dataCheck() {
      let err = false;
      let msg = "";
      !this.input.test_title && ((msg = "실험명을 입력해주세요"), (err = true));
      !err && !this.input.url_a && ((msg = "URL을 입력해주세요"), (err = true));
      !err &&
        !this.input.test_a &&
        ((msg = "A안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.input.test_b &&
        ((msg = "B안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.input.start &&
        ((msg = "시작일을 설정해주세요"), (err = true));
      !err &&
        !this.input.end &&
        ((msg = "종료일을 설정해주세요"), (err = true));
      var urls = [];
      this.rows.forEach((element) => {
        urls.push(element.url);
      });
      this.urls = urls;
      !err &&
        urls[0].length == 0 &&
        ((msg = "하나의 분석 URL를 설정해주세요"), (err = true));
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
      data.start = this.input.start;
      data.end = this.input.end;
      data.per_a = perA;
      data.per_b = 100 - this.input.per_a;
      data.urls = this.urls;

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
        (this.rows = {url:""});
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

<style scoped>
.mybutton {
  width: 80px;
}
</style>