<template>
  <div>
    <Header />
    <div class="col-8 col-m-10 m-auto">
      <div class="infoBox my-2">
        <div class="d-flex justify-content-end">
          <small class="d-flex align-items-center"
            ><b-icon icon="circle-fill" class="ml-2 mr-1 text-success" /> 진행
            전</small
          >
          <small class="d-flex align-items-center"
            ><b-icon icon="circle-fill" class="ml-2 mr-1 text-warning" /> 진행
            중</small
          >
          <small class="d-flex align-items-center"
            ><b-icon icon="circle-fill" class="ml-2 mr-1 text-danger" /> 진행
            완료</small
          >
        </div>
      </div>
      <div class="tabs">
        <b-tabs v-model="tabIndex">
          <b-tab
            title="전체 실험"
            :title-link-class="linkClass(0)"
            @click="getAllTest()"
          >
            <b-table :items="tests" :fields="fields" class="text-light">
              <template #cell(test_title)="data">
                <span class="status" @click="detail(data.value)">
                  {{ data.value.test_title }}
                  <b-icon
                    icon="circle-fill"
                    class="ml-2"
                    :class="data.value.color"
                  />
                </span>
              </template>
              <template #cell(icon)="data">
                <span
                  v-show="!data.value.hide"
                  class="edit"
                  @click="edit(data.value.test_no)"
                >
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value.test_no)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>

          <b-tab
            title="진행 전"
            :title-link-class="linkClass(1)"
            @click="getBeforeTest()"
          >
            <b-table :items="tests" :fields="fields" class="text-light">
              <template #cell(test_title)="data">
                <span class="status" @click="detail(data.value)">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span
                  v-show="!data.value.hide"
                  class="edit"
                  @click="edit(data.value.test_no)"
                >
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value.test_no)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>

          <b-tab
            title="진행 중"
            :title-link-class="linkClass(2)"
            @click="getProgressTest()"
          >
            <b-table :items="tests" :fields="fields" class="text-light">
              <template #cell(test_title)="data">
                <span class="status" @click="detail(data.value)">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span
                  v-show="!data.value.hide"
                  class="edit"
                  @click="edit(data.value.test_no)"
                >
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value.test_no)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>

          <b-tab
            title="진행 완료"
            :title-link-class="linkClass(3)"
            @click="getCompleteTest()"
          >
            <b-table :items="tests" :fields="fields" class="text-light">
              <template #cell(test_title)="data">
                <span class="status" @click="detail(data.value)">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span
                  v-show="!data.value.hide"
                  class="edit"
                  @click="edit(data.value.test_no)"
                >
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value.test_no)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>
        </b-tabs>
        <b-modal title="실험 수정" v-model="modalShow">
          <b-row class="my-1">
            <b-col sm="3">
              <label>실험명 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.test_title" />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>A안 별칭 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.test_a" />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>B안 별칭 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.test_b" />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>URL :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.url_a" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>전환율 분석 페이지 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.urls" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>시작일 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.start" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>종료일 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.end" type="date" :min="today" />
            </b-col>
          </b-row>
          <template #modal-footer>
            <b-button @click="modalShow = false" variant="danger">
              취소
            </b-button>
            <b-button @click="dataCheck()" variant="success">
              수정
            </b-button>
          </template>
        </b-modal>
      </div>
      <div>
        <b-icon-plus
          @click="goInsert"
          class="rounded-circle bg-danger text-white createBtn"
        ></b-icon-plus>
        <!-- <b-button pill class="createBtn" v-bind:to="'insert'"
          >실험생성</b-button
        > -->
      </div>
    </div>
  </div>
</template>

<script>
import API from "@/api/API";
import swal from "sweetalert";

import Header from "@/components/Header";

export default {
  components: {
    Header,
  },
  data() {
    return {
      tabIndex: 0,
      modalShow: false,
      today: new Date(),
      inputs: {
        test_no: "",
        test_title: "",
        url_a: "",
        test_a: "",
        test_b: "",
        start: "",
        end: "",
        urls: "",
        temp: "",
      },
      fields: [
        { key: "test_title", label: "실험명" },
        { key: "testA", label: "A 안" },
        { key: "testB", label: "B 안" },
        { key: "date", label: "기간" },
        { key: "icon", label: "" },
      ],
      tests: [
        {
          test_no: "",
          test_title: "",
          url_a: "",
          test_a: "",
          test_b: "",
          start: "",
          end: "",
          per_a: "",
          per_b: "",
        },
      ],
    };
  },
  computed: {
    email() {
      return this.$store.state.email;
    },
  },
  created() {
    this.getAllTest();

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
  methods: {
    dataCheck() {
      let err = false;
      let msg = "";
      !this.inputs.test_title &&
        ((msg = "실험명을 입력해주세요"), (err = true));
      !err &&
        !this.inputs.test_a &&
        ((msg = "A안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.inputs.test_b &&
        ((msg = "B안의 별칭을 입력해주세요"), (err = true));
      !err &&
        !this.inputs.end &&
        ((msg = "종료일을 설정해주세요"), (err = true));
      if (err) swal(msg);
      else this.editTest();
    },
    makeTableData() {
      for (let test of this.tests) {
        test.icon = { test_no: test.test_no, hide: false };
        test.test_title = {
          test_title: test.test_title,
          test_no: test.test_no,
        };
        if (test.status == "진행전") {
          test.test_title.color = "text-success";
        } else if (test.status == "진행중") {
          test.test_title.color = "text-warning";
        } else {
          test.test_title.color = "text-danger";
          test.icon.hide = true;
        }

        test.date = test.start + " - " + test.end;

        test.testA = test.test_a + "(" + test.per_a + "%)";
        test.testB = test.test_b + "(" + test.per_b + "%)";
      }
    },

    linkClass(idx) {
      if (this.tabIndex === idx) {
        if (idx === 0) {
          return ["bg-info", "text-light"];
        } else if (idx === 1) {
          return ["bg-success", "text-light"];
        } else if (idx === 2) {
          return ["bg-warning", "text-light"];
        } else {
          return ["bg-danger", "text-light"];
        }
      } else {
        return ["bg-light", "text-dark"];
      }
    },
    edit(id) {
      var thisTest;
      for (let test of this.tests) {
        if (test.test_no == id) {
          console.log(test);
          thisTest = test;
        }
      }

      this.inputs.test_no = thisTest.test_no;
      this.inputs.test_title = thisTest.test_title.test_title;
      this.inputs.url_a = thisTest.url_a;
      this.inputs.test_a = thisTest.test_a;
      this.inputs.test_b = thisTest.test_b;
      this.inputs.start = thisTest.start;
      this.inputs.end = thisTest.end;
      this.inputs.urls = thisTest.urls;

      for (var i = 0; i < this.inputs.urls.length; i++) {
        this.inputs.temp += this.inputs.urls[i].urlName;
        if (i != this.inputs.urls.length - 1) this.inputs.temp += ", ";
      }
      this.inputs.urls = this.inputs.temp;
      this.inputs.temp = "";
      console.log(thisTest);
      this.modalShow = !this.modalShow;
    },
    detail(id) {
      console.log(id);
      API.getDetailTest(
        "test_no=" + id.test_no,
        (res) => {
          console.log(res);
          this.$store.commit("addDetail", res);
          this.$router.push("/detail");
        },
        (err) => {
          console.log(err);
          swal(
            "페이지 조회 실패",
            "분석페이지 조회에 실패하였습니다.",
            "error"
          );
        }
      );
    },
    deleteTest(id) {
      console.log(id);
      API.deleteTest(
        "test_no=" + id,
        (res) => {
          console.log(res);
          swal("삭제 완료", "실험이 정상적으로 삭제되었습니다.", "success");
          this.$router.push("/main");
        },
        (err) => {
          console.log(err);
          swal("삭제 실패", "실험삭제에 실패하였습니다.", "error");
        }
      );

      var remainTests = [];
      for (var index in this.tests) {
        if (this.tests[index].test_no != id) {
          remainTests.push(this.tests[index]);
        }
      }
      this.tests = remainTests;
    },
    editTest() {
      let data = {};
      data.test_no = this.inputs.test_no;
      data.test_title = this.inputs.test_title;
      data.test_a = this.inputs.test_a;
      data.test_b = this.inputs.test_b;
      data.url_a = this.inputs.url_a;
      data.url_b = this.inputs.url_b;
      data.end = this.inputs.end;

      API.modifyTest(
        data,
        (res) => {
          console.log(res);
          swal("수정 완료", "실험이 정상적으로 수정되었습니다.", "success");
          this.modalShow = !this.modalShow;
          var temp = [];
          for (var index in this.tests) {
            if (this.tests[index].test_no == data.test_no) {
              this.tests[index].test_a = data.test_a;
              this.tests[index].test_b = data.test_b;
              this.tests[index].end = data.end;
              this.tests[index].test_title = {
                test_title: data.test_title,
                test_no: data.test_no,
              };
              if (this.tests[index].status == "진행전")
                this.tests[index].test_title.color = "text-success";
              else if (this.tests[index].status == "진행중")
                this.tests[index].test_title.color = "text-warning";
              else this.tests[index].test_title.color = "text-danger";
            }
            temp.push(this.tests[index]);
          }
          this.tests = temp;
          console.log(temp);
        },
        (err) => {
          console.log(err);
          swal("수정 실패", "실험수정에 실패하였습니다.", "error");
        }
      );
    },
    getAllTest() {
      (this.tests = []),
        API.getTestList(
          "email=" + this.email,
          (res) => {
            this.tests = res;
            this.makeTableData();
          },
          (err) => {
            console.log(err);
          }
        );
    },
    getBeforeTest() {
      (this.tests = []),
        API.getTestListBefore(
          "email=" + this.email,
          (res) => {
            this.tests = res;
            this.makeTableData();
          },
          (err) => {
            console.log(err);
          }
        );
    },
    getProgressTest() {
      (this.tests = []),
        API.getTestListProgress(
          "email=" + this.email,
          (res) => {
            this.tests = res;
            this.makeTableData();
          },
          (err) => {
            console.log(err);
          }
        );
    },
    getCompleteTest() {
      (this.tests = []),
        API.getTestListComplete(
          "email=" + this.email,
          (res) => {
            this.tests = res;
            this.makeTableData();
          },
          (err) => {
            console.log(err);
          }
        );
    },
    goInsert() {
      this.$router.push({ name: "Insert" });
    },
  },
};
</script>

<style scoped>
.createBtn {
  float: right;
  font-size: 30px;
  cursor: pointer;
}
.tabs {
  clear: both;
  /* height: ; */
}
.infoBox {
  overflow: hidden;
  width: 100%;
}
.status {
  cursor: pointer;
}
</style>