<template>
  <div>
    <encar-header></encar-header>
    <div class="col-8 col-m-10 m-auto">
      <div>
        <b-button pill class="createBtn">실험생성</b-button>
      </div>
      <div class="tabs">
        <b-tabs>
          <b-tab title="전체실험">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(name)="data">
                <span class="status">
                  {{ data.value.name }}
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

          <b-tab title="진행전">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(name)="data">
                <span class="status">
                  {{ data.value.name }}
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

          <b-tab title="진행중">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(name)="data">
                <span class="status">
                  {{ data.value.name }}
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

          <b-tab title="진행완료">
            <b-table hover :items="tests" :fields="fields">
              <template #cell(name)="data">
                <span class="status">
                  {{ data.value.name }}
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
          실험명 : <b-form-input v-model="inputs.name"/>
          A URL : <b-form-input v-model="inputs.aURL" disabled/>
          A 별칭 : <b-form-input v-model="inputs.aName"/>
          B URL : <b-form-input v-model="inputs.bURL" disabled/>
          B 별칭 : <b-form-input v-model="inputs.bName"/>
          시작일 : <b-form-input v-model="inputs.startDate" type="date" disabled/>
          종료일 : <b-form-input v-model="inputs.endDate" type="date"/>
        </b-modal>
      </div>
    </div>
  </div>
</template>

<script>
// import API from '@/api/API';
import encarHeader from "@/components/Header";

export default {
  components: {
    encarHeader,
  },
  data() {
    return {
      modalShow:false,
      inputs:{
        name:"", 
        aURL:"",
        aName:"",
        bURL:"",
        bName:"",
        startDate:"",
        endDate:"",
      },
      fields: [
        { key: "no", label: "No" },
        { key: "date", label: "기간" },
        { key: "name", label: "실험명" },
        { key: "a", label: "A 안" },
        { key: "b", label: "B 안" },
        { key: "icon", label: "" },
      ],
      tests: [
        {
          no: 1,
          date: "20.10.14-20.10.25",
          name: "버튼 테스트",
          a: "A안별칭 (비율%)",
          b: "B안별칭 (비율%)",
          status: 2,
        },
        {
          no: 2,
          date: "20.10.31-20.11.13",
          name: "색상 테스트",
          a: "A안별칭 (비율%)",
          b: "B안별칭 (비율%)",
          status: 1,
        },
        {
          no: 3,
          date: "20.10.14-21.10.13",
          name: "폼 테스트",
          a: "A안별칭 (비율%)",
          b: "B안별칭 (비율%)",
          status: 3,
        },
        {
          no: 4,
          date: "20.10.14-20.10.21",
          name: "집계 테스트",
          a: "A안별칭 (비율%)",
          b: "B안별칭 (비율%)",
          status: 3,
        },
      ],
    };
  },
  created() {
    for (let test of this.tests) {
      test.icon = test.no;
      test.name = { name: test.name };
      if (test.status == 1) test.name.color = "text-success";
      else if (test.status == 2) test.name.color = "text-warning";
      else test.name.color = "text-danger";
    }
  },
  methods: {
    edit(id) {
      console.log(id);
      this.modalShow = !this.modalShow;
    },
    deleteTest(id) {
      console.log(id);
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
