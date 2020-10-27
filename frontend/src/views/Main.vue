<template>
  <div>
    <encar-header></encar-header>
    <div class="col-8 col-m-10 m-auto">
      <div>
        <b-button pill class="createBtn" v-bind:to="'insert'">실험생성</b-button>
      </div>
      <div class="tabs">
        <b-tabs>
          <b-tab title="전체실험" @click="getAllTest()">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(test_title)="data">
                <span class="status">
                  {{ data.value.test_title }}
                  <b-icon
                    icon="circle-fill"
                    class="ml-2"
                    :class="data.value.color"
                  />
                </span>
              </template>
              <template #cell(icon)="data">
                <span class="edit" @click="edit(data.value)">
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
            <div class="info">
              <b-icon icon="circle-fill" class="ml-2 text-danger" /> 진행 완료
              <b-icon icon="circle-fill" class="ml-2 text-warning" /> 진행 중
              <b-icon icon="circle-fill" class="ml-2 text-success" /> 진행 전
            </div>
          </b-tab>

          <b-tab title="진행전" @click="getBeforeTest()">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(test_title)="data">
                <span class="status">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span class="edit" @click="edit(data.value)">
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>

          <b-tab title="진행중" @click="getProgressTest()">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(test_title)="data">
                <span class="status">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span class="edit" @click="edit(data.value)">
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>

          <b-tab title="진행완료" @click="getCompleteTest()">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(test_title)="data">
                <span class="status">
                  {{ data.value.test_title }}
                </span>
              </template>
              <template #cell(icon)="data">
                <span class="edit" @click="edit(data.value)">
                  <b-icon icon="pencil-square" />
                </span>
                <span class="delete" @click="deleteTest(data.value)">
                  <b-icon icon="trash" class="ml-2" />
                </span>
              </template>
            </b-table>
          </b-tab>
        </b-tabs>
        <b-modal v-model="modalShow">
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
              <label>A URL :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.aURL" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>A 별칭 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.test_a" />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>B URL :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.bURL" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>B 별칭 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.test_b" />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>시작일 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.startDate" type="date" disabled />
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col sm="3">
              <label>종료일 :</label>
            </b-col>
            <b-col sm="9">
              <b-form-input v-model="inputs.endDate" type="date" />
            </b-col>
          </b-row>
          <template #modal-footer>
            <b-button @click="modalShow=false" variant="danger">
              취소
            </b-button>
            <b-button @click="editTest()" variant="success">
              수정
            </b-button>
          </template>
        </b-modal>
      </div>
    </div>
  </div>
</template>

<script>
import API from '@/api/API';
import swal from 'sweetalert';

import encarHeader from "@/components/Header";

export default {
  components: {
    encarHeader,
  },
  data() {
    return {
      modalShow: false,
      inputs: {
        test_title: "",
        aURL: "",
        test_a: "",
        bURL: "",
        test_b: "",
        start: "",
        end: "",
      },
      fields: [
        { key: "test_no", label: "No" },
        { key: "date", label: "기간" },
        { key: "test_title", label: "실험명" },
        { key: "testA", label: "A 안" },
        { key: "testB", label: "B 안" },
        { key: "icon", label: "" },
      ],
      tests: [
        {
          test_no: 1,
          start: "2020.10.14",
          end:"2020.10.25",
          test_title: "버튼 테스트",
          test_a: "A안별칭",
          per_a:90,
          test_b: "B안별칭",
          per_b:10,
          status: "진행완료",
        },
        {
          test_no: 2,
          start: "2020.10.31",
          end:"2020.11.13",
          test_title: "색상 테스트",
          test_a: "A안별칭",
          per_a:60,
          test_b: "B안별칭",
          per_b:40,
          status: "진행전",
        },
        {
          test_no: 3,
          start: "2020.10.14",
          end:"2021.10.13",
          test_title: "폼 테스트",
          test_a: "A안별칭",
          per_a:70,
          test_b: "B안별칭",
          per_b:30,
          status: "진행중",
        },
        {
          test_no: 4,
          start: "2020.10.14",
          end:"2020.10.21",
          test_title: "집계 테스트",
          test_a: "A안별칭",
          per_a:50,
          test_b: "B안별칭",
          per_b:50,
          status: "진행완료",
        },
      ],
    };
  },
  created() {
    for (let test of this.tests) {
      test.icon = test.test_no;
      test.test_title = { test_title: test.test_title };
      if (test.status == "진행전") test.test_title.color = "text-success";
      else if (test.status == "진행중") test.test_title.color = "text-warning";
      else test.test_title.color = "text-danger";

      test.date = test.start+" - "+test.end;

      test.testA = test.test_a+"("+test.per_a+"%)";
      test.testB = test.test_b+"("+test.per_b+"%)";
    }
  },
  methods: {
    edit(id) {
      console.log(id);
      this.modalShow = !this.modalShow;
    },
    deleteTest(id) {
      console.log(id);

      API.deleteTest(
        id,
        res=>{
          console.log(res);
          swal("삭제 완료", "실험이 정상적으로 삭제되었습니다.", "success");
        },
        err=>{
          console.log(err);
           swal("삭제 실패", "실험삭제에 실패하였습니다.", "error");
        }
      )
    },
    editTest(){
      let data = {};
      data.id = this.id;
      data.test_title = this.test_title;
      data.test_a = this.test_a;
      data.test_b = this.test_b;
      data.end = this.end;

      API.modifyTest(
        data,
        res=>{
          console.log(res);
          swal("수정 완료", "실험이 정상적으로 수정되었습니다.", "success");
        },
        err=>{
          console.log(err);
          swal("수정 실패", "실험수정에 실패하였습니다.", "error");
        }
      )
    },
    getAllTest(){
      API.getTestList(
        this.email,
        res=>{
          console.log(res);
        },
        err=>{
          console.log(err);
        }
      )
    },
    getBeforeTest(){
      API.getTestListBefore(
        this.email,
        res=>{
          console.log(res);
        },
        err=>{
          console.log(err);
        }
      )
    },
    getProgressTest(){
      API.getTestListProgress(
        this.email,
        res=>{
          console.log(res);
        },
        err=>{
          console.log(err);
        }
      )
    },
    getCompleteTest(){
      API.getTestListComplete(
        this.email,
        res=>{
          console.log(res);
        },
        err=>{
          console.log(err);
        }
      )
    },
  },
};
</script>

<style scoped>
.createBtn {
  float: right;
  background-color: tomato;
}

.info {
  float: right;
}

.tabs {
  clear: both;
}
</style>
