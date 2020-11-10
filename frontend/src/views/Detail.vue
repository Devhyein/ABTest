<template>
  <div>
    <EncarHeader />
    <div class="col-8 col-m-10 m-auto">
      <!-- 이 부분 예쁘게 해주세요(와이어프레임 참고해주세요) -->
      <h3>
        {{ detail.test_title }}
        <b-button pill disabled :variant="detail.color">{{
          detail.status
        }}</b-button>
      </h3>

      {{ detail.start }} ~ {{ detail.end }}
      <b-tabs v-model="tabIndex">
        <b-tab title="전체" :title-link-class="linkClass(0)">
          <b-form-select v-model="selected1" :options="options1" />
          <div>
            <b-button variant="info" @click="urlModal()">
              페이지별 전환율 상세보기
            </b-button>

            <canvas id="chartID" width="300" height="300"></canvas>

            <b-table hover :items="tableData" :fields="fields"> </b-table>
          </div>
        </b-tab>
        <b-tab title="성별" :title-link-class="linkClass(1)">
          <b-form-select v-model="selected2" :options="options2" />
          <div>
            <b-button variant="info" @click="urlModal()">
              페이지별 전환율 상세보기
            </b-button>
          </div>
        </b-tab>
        <b-tab title="나이" :title-link-class="linkClass(2)">
          <b-form-select v-model="selected3" :options="options2" />
          <div>
            <b-button variant="info" @click="urlModal()">
              페이지별 전환율 상세보기
            </b-button>
          </div>
        </b-tab>
        <b-tab title="사용자 지정" :title-link-class="linkClass(3)">
          <b-form-select v-model="selected4" :options="options1" />
          <div>
            <b-button variant="info" @click="urlModal()">
              페이지별 전환율 상세보기
            </b-button>
          </div>
        </b-tab>
      </b-tabs>
      <b-modal title="URL별 페이지 전환율" v-model="modalShow">
        <!-- 모달에 들어 갈 테이블 추가 -->
        <b-table hover :items="detailTableData" :fields="detailFields">
        </b-table>
        <template #modal-footer>
          <div></div>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<script>
import API from "@/api/API";
import Chart from "chart.js";
import totalData from "../chart/chart-data.js";

import EncarHeader from "@/components/Header";

/* var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, {
  type: "bar",
  data: {
    labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
    datasets: [
      {
        label: "# of Votes",
        data: [12, 19, 3, 5, 2, 3],
      },
    ],
  },
  options: {
    scales: {
      yAxes: [
        {
          ticks: {
            beginAtZero: true,
          },
        },
      ],
    },
  },
}); */

export default {
  components: {
    EncarHeader,
  },
  data() {
    return {
      totalData: totalData,
      modalShow: false,
      tabIndex: 0,
      tableData: [],
      detailTableData: [],
      // chart어떤식으로 받아야할지 공부
      //chart:[],
      fields: [
        { key: "assortment", label: "구분" },
        { key: "testA", label: "A 안" },
        { key: "testB", label: "B 안" },
        { key: "rate", label: "증감률" },
      ],
      detailFields: [],

      selected1: null,
      selected2: null,
      selected3: null,
      selected4: null,

      options1: [
        { value: null, text: "구분" },
        { value: "전환율", text: "전환율" },
        { value: "이탈률", text: "이탈률" },
        { value: "회원가입률", text: "회원가입률" },
        { value: "구매율", text: "구매율" },
      ],
      options2: [
        { value: null, text: "구분" },
        { value: "전환율", text: "전환율" },
        { value: "이탈률", text: "이탈률" },
        { value: "구매율", text: "구매율" },
      ],
    };
  },
  created() {
    console.log(totalData);
    // this.createChart("myChart", totalData);
    this.tableData = [
      {
        assortment: "전환율",
        testA: this.detail.conversionA + "%",
        testB: this.detail.conversionB + "%",
        rate: this.detail.con_rate + "%",
      },
      {
        assortment: "이탈률",
        testA: this.detail.bounceA + "%",
        testB: this.detail.bounceA + "%",
        rate: this.detail.bo_rate + "%",
      },
      {
        assortment: "회원가입률",
        testA: this.detail.joinA + "%",
        testB: this.detail.joinB + "%",
        rate: this.detail.jo_rate + "%",
      },
      {
        assortment: "구매율",
        testA: this.detail.purchaseA + "%",
        testB: this.detail.purchaseB + "%",
        rate: this.detail.pur_rate + "%",
      },
    ];

    if (this.detail.status == "진행전") this.detail.color = "success";
    else if (this.detail.status == "진행중") this.detail.color = "warning";
    else this.detail.color = "danger";
  },
  methods: {
    createChart(chartID, chartData) {
      const ctx = document.getElementById(chartID);
      const myChart = new Chart(ctx, {
        type: chartData.type,
        data: chartData.data,
        options: chartData.options,
      });
      console.log("^^" + myChart);
    },
    urlModal() {
      API.getDetailTestConversionWithUrl(
        "test_no=" + this.detail.test_no,
        (res) => {
          console.log(res);
          this.detailFields = res.urls;
          this.detailFields = this.detailFields.map((value) => {
            return {
              key: value,
              label: value,
            };
          });
          this.detailFields.unshift({ key: "name", label: "" });
          var arr = [];
          var obj = {};
          obj.name = "A";
          for (var idx in res.urls) {
            obj[res.urls[idx]] = res.conversionA[idx];
          }

          arr.push(obj);
          obj = {};
          obj.name = "B";
          for (var idx2 in res.urls) {
            obj[res.urls[idx2]] = res.conversionB[idx2];
          }
          arr.push(obj);
          this.detailTableData = arr;
          console.log(this.detailFields);
          console.log(this.detailTableData);
        },
        (err) => {
          console.log(err);
        }
      );

      this.modalShow = !this.modalShow;
    },
    linkClass(idx) {
      if (this.tabIndex === idx) {
        return ["bg-danger", "text-light"];
      } else {
        return ["bg-light", "text-dark"];
      }
    },
  },
  mounted() {
    this.createChart("chartID", totalData);
  },
  watch: {
    // 드롭다운 보고있다가 selected에 변화가 생겼을때 API부르기
    // chart에 넣기(어떻게 가져와서 각각 라인,바 차트에 넣을지 고민쓰)
    selected1(val) {
      if (val == "전환율") {
        console.log("전체-전환율");
        API.getChartConversion(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "이탈률") {
        console.log("전체-이탈률");
        API.getChartBounce(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "회원가입률") {
        console.log("전체-회원가입률");
        API.getChartJoin(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "구매율") {
        console.log("전체-구매율");
        API.getChartPurchase(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    selected2(val) {
      if (val == "전환율") {
        console.log("gender-전환율");
        API.getChartGenderConversion(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "이탈률") {
        console.log("gender-이탈률");
        API.getChartGenderBounce(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "구매율") {
        console.log("gender-구매율");
        API.getChartGenderPurchase(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    selected3(val) {
      if (val == "전환율") {
        console.log("age-전환율");
        API.getChartAgeConversion(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "이탈률") {
        console.log("age-이탈율");
        API.getChartAgeBounce(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "구매율") {
        console.log("age-구매율");
        API.getChartAgePurchase(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    selected4(val) {
      if (val == "전환율") {
        console.log("custom-전환율");
        API.getChartCustomConversion(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "이탈률") {
        console.log("custom-이탈률");
        API.getChartCustomBounce(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "회원가입률") {
        console.log("custom-회원가입률");
        API.getChartCustomJoin(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      } else if (val == "구매율") {
        console.log("custom-구매율");
        API.getChartCustomPurchase(
          "test_no=" + this.detail.test_no,
          (res) => {
            console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
  },
  computed: {
    detail() {
      return this.$store.state.detail;
    },
  },
};
</script>
