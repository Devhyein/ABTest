<template>
  <div class="mydetail pb-5">
    <EncarHeader />
    <div class="col-8 col-m-10 mx-auto my-5">
      <div class="text-left my-4">
        <h3 class="d-flex align-items-center">
          {{ detail.test_title }}
          <b-button pill :variant="detail.color" class="py-0 ml-2">{{
            detail.status
          }}</b-button>
        </h3>
        <h6>
          {{ detail.start }} ~ {{ detail.end }}
        </h6>
      </div>
      <b-tabs v-model="tabIndex">
        <b-tab title="전체" :title-link-class="linkClass(0)" @click="totalTab()">
            <b-row class="align-items-center mt-3">
              <b-col cols="2">
                <b-form-select v-model="selected1" :options="options1" class="myselect" />
              </b-col>
            </b-row>
            <b-row class="align-items-center mt-3">
              <b-col cols="7">
                <canvas id="myChart"></canvas>
              </b-col>
              <b-col cols="5">
                <div class="mb-4">
                  <b-button variant="info" @click="urlModal()">
                   페이지별 전환율 상세보기
                  </b-button>              
                </div>
                <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
              </b-col>
            </b-row>

        </b-tab>
        <b-tab title="성별" :title-link-class="linkClass(1)" @click="genderTab('male'), resetSelectedTab()">
          <b-row class="mt-3">
            <b-col cols="2">
              <b-form-select v-model="selected2" :options="options2" class="myselect" />
            </b-col>
          </b-row>
          <b-row class="align-items-center mt-3">
              <b-col cols="7">
                <canvas id="genderChart"></canvas>
              </b-col>
              <b-col cols="5">
                <b-tabs v-model="genderIndex">
                  <b-tab title="남성" :title-link-class="genderClass(0)" @click="genderTab('male')">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                  <b-tab title="여성" :title-link-class="genderClass(1)" @click="genderTab('female')">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                </b-tabs>
              </b-col>
            </b-row>
        </b-tab>

        <b-tab title="연령대" :title-link-class="linkClass(2)" @click="ageTab(0), resetSelectedTab()">
          <b-row class="mt-3">
            <b-col cols="2">
              <b-form-select v-model="selected3" :options="options2" class="myselect" />
            </b-col>
          </b-row>
          <b-row class="align-items-center mt-3">
              <b-col cols="7">
                <canvas id="ageChart"></canvas>
              </b-col>
              <b-col cols="5">
                <b-tabs v-model="ageIndex">
                  <b-tab title="20대" :title-link-class="ageClass(0)" @click="ageTab(0)">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                  <b-tab title="30대" :title-link-class="ageClass(1)" @click="ageTab(1)">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                  <b-tab title="40대" :title-link-class="ageClass(2)" @click="ageTab(2)">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                  <b-tab title="50대" :title-link-class="ageClass(3)" @click="ageTab(3)">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                  <b-tab title="60대 이상" :title-link-class="ageClass(4)" @click="ageTab(4)">
                    <b-table :items="tableData" :fields="fields" class="text-light"> </b-table>
                  </b-tab>
                </b-tabs>
              </b-col>
            </b-row>
        </b-tab>

        <!-- <b-tab title="사용자 지정" :title-link-class="linkClass(3)">
          <b-row class="graphChart">
            <b-col cols="3">
            <b-form-select v-model="selected4" :options="options1" />
            </b-col>
            <b-col cols="5" offset="4">
              <div>
                <b-button variant="info" @click="urlModal()">
                  페이지별 전환율 상세보기
                </b-button>
              </div>
            </b-col>
          </b-row>
        </b-tab> -->
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
var genderChart;
var myChart;
var ageChart;
export default {
  components: {
    EncarHeader,
  },
  data() {
    return {
      totalData: totalData,
      modalShow: false,
      tabIndex: 0,
      genderIndex: 0,
      ageIndex: 0,
      tableData: [],
      detailTableData: [],
      // chart어떤식으로 받아야할지 공부
      chart:{
        aChartData : [],
        bChartData : [],
        date : []
      },
      genderChart: {
        amaleChartData : 0,
        afemaleChartData : 0,
        bmaleChartData : 0,
        bfemaleChartData : 0,
      },
      ageChart: {
        aChartData : [],
        bChartData : [],
        label : ["20대","30대","40대","50대","60대 이상",]
      },
      fields: [
        { key: "assortment", label: "구분" },
        { key: "testA", label: "A 안" },
        { key: "testB", label: "B 안" },
        { key: "rate", label: "증감률" },
      ],
      detailFields: [],

      selected1: "전환율",
      selected2: "전환율",
      selected3: "전환율",
      // selected4: null,

      beforeSelectedAgeTab: "전환율",
      beforeSelectedGenderTab: "전환율",


      options1: [
        { value: "전환율", text: "전환율"},
        { value: "이탈률", text: "이탈률" },
        { value: "회원가입률", text: "회원가입률" },
        // { value: "구매율", text: "구매율" },
      ],
      options2: [
        { value: "전환율", text: "전환율" },
        { value: "이탈률", text: "이탈률" },
        // { value: "구매율", text: "구매율" },
      ],
    };
  },
  created() {
    this.totalTab();

    if (this.detail.status == "진행전") this.detail.color = "success";
    else if (this.detail.status == "진행중") this.detail.color = "warning";
    else this.detail.color = "danger";
  },
  methods: {
    resetSelectedTab() {
      this.selected1 = "전환율",
      this.selected2 = "전환율",
      this.selected3 = "전환율"
    },
    totalTab() {
      this.selected1 = "전환율",
      this.getChartDataTotal(this.selected1),
      API.getDetailTest(
      "test_no=" + this.detail.test_no,
      (res) => {
        this.tableData = [
              {
                assortment: "전환율",
                testA: res.conversionA + "%",
                testB: res.conversionB + "%",
                rate: res.con_rate + "%",
              },
              {
                assortment: "이탈률",
                testA: res.bounceA + "%",
                testB: res.bounceB + "%",
                rate: res.bo_rate + "%",
              },
              {
                assortment: "회원가입률",
                testA: res.joinA + "%",
                testB: res.joinB + "%",
                rate: res.jo_rate + "%",
              },
              // {
              //   assortment: "구매율",
              //   testA: this.detail.purchaseA + "%",
              //   testB: this.detail.purchaseB + "%",
              //   rate: this.detail.pur_rate + "%",
              // },
            ];
      },
      (err) => {
        console.log(err);
      }
      );
    },
    genderTab(gender) {
      // this.selected2 = "전환율",
      // beforeSelectedGenderTab = "전환율",
      this.getChartDataGender(this.selected2),
      API.getTableDataGender(
      "test_no=" + this.detail.test_no,
      (res) => {
        if(gender=='male'){
          this.tableData = [
            {
              assortment: "전환율",
              testA: res.conversionAMale + "%",
              testB: res.conversionBMale + "%",
              rate: res.con_rateMale + "%",
            },
            {
              assortment: "이탈률",
              testA: res.bounceAMale + "%",
              testB: res.bounceBMale + "%",
              rate: res.bo_rateMale + "%",
            }
          ];
        } else {
            this.tableData = [
              {
                assortment: "전환율",
                testA: res.conversionAFemale + "%",
                testB: res.conversionBFemale + "%",
                rate: res.con_rateFemale + "%",
              },
              {
                assortment: "이탈률",
                testA: res.bounceAFemale + "%",
                testB: res.bounceBFemale + "%",
                rate: res.bo_rateFemale + "%",
              }
            ];
        }

      },
      (err) => {
        console.log(err);
      }
      );
    },
    ageTab(age) {
      this.getChartDataAge(this.selected3),
      API.getTableDataAge(
      "test_no=" + this.detail.test_no,
      (res) => {
        this.tableData = [
          {
            assortment: "전환율",
            testA: res.conversionA[age] + "%",
            testB: res.conversionB[age] + "%",
            rate: res.con_rate[age] + "%",
          },
          {
            assortment: "이탈률",
            testA: res.bounceA[age] + "%",
            testB: res.bounceB[age] + "%",
            rate: res.bo_rate[age] + "%",
          }
        ];
      },
      (err) => {
        console.log(err);
      }
      );
    },

    createChart() {
      var ctx = document.getElementById('myChart').getContext('2d');
      var opt = {
        type: 'line',
        data: {
          datasets: [{
              label: 'A안',
              data: this.chart.aChartData,
              borderColor : 'rgba(255,99,132,1)',
              backgroundColor : false,
              fill: false
          }, {
              label: 'B안',
              data: this.chart.bChartData,
              // Changes this dataset to become a line
              type: 'line',
              borderColor :'rgba(75, 192, 192, 1)',
              backgroundColor : false,
              fill: false
          }],
          // date
          labels: this.chart.date,
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        fontColor: "rgba(239, 239, 239, 1)"
                    },
                    gridLines: {
                      color: "rgba(239, 239, 239, 0.3)"
                    }
                }],
                xAxes: [{
                  ticks: {
                    fontColor: "rgba(239, 239, 239, 1)"
                  }
                }]
            },
            legend: {
              labels: {
                fontColor: "rgba(239, 239, 239, 1)"
              }
            }
        }
      };

      if(myChart) 
        myChart.destroy();
      myChart = new Chart(ctx, opt);
      myChart.update();

      console.log(myChart);
    },

    createGenderChart() {
      var ctx = document.getElementById('genderChart').getContext('2d');
      var opt = {
        type: 'bar',
        data: {
          labels: ['남성', '여성'],
          datasets: [
            {
              label: 'A안',
              data: [this.genderChart.amaleChartData,this.genderChart.afemaleChartData],
              borderColor : 'rgba(255,99,132,1)',
              backgroundColor:'rgba(255,99,132,1)',
              fill: false,
            },
            {
              type: 'bar',
              label: 'B안',
              data: [this.genderChart.bmaleChartData,this.genderChart.bfemaleChartData],
              borderColor:'rgba(75, 192, 192, 1)',
              backgroundColor: 'rgba(75, 192, 192, 1)',
              fill: false,
            },
            ]
          },
          options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        fontColor: "rgba(239, 239, 239, 1)"
                    },
                    gridLines: {
                      color: "rgba(239, 239, 239, 0.3)"
                    }
                }],
                xAxes: [{
                  ticks: {
                    fontColor: "rgba(239, 239, 239, 1)"
                  }
                }]
            },
            legend: {
              labels: {
                fontColor: "rgba(239, 239, 239, 1)"
              }
            }
        } 
      };
      if(genderChart) 
        genderChart.destroy();
      genderChart = new Chart(ctx, opt);
      genderChart.update();
      console.log(genderChart);
    },

    createAgeChart() {
      var ctx = document.getElementById('ageChart').getContext('2d');
      var opt = {
        type: 'horizontalBar',
        data: {
          labels: this.ageChart.label,
          datasets: [
            {
              label: 'A안',
              data: this.ageChart.aChartData,
              borderColor : 'rgba(255,99,132,1)',
              backgroundColor:'rgba(255,99,132,1)',
              fill: false,
            },
            {
              label: 'B안',
              data: this.ageChart.bChartData,
              borderColor:'rgba(75, 192, 192, 1)',
              backgroundColor:'rgba(75, 192, 192, 1)',
              fill: false,
            },
            ]
          },
        options: {
            scales: {
                xAxes: [{
                    ticks: {
                        suggestedMin: -100,
                        suggestedMax: 100,
                        callback: value => value.toString().replace('-', ''),
                        fontColor: "rgba(239, 239, 239, 1)"
                    },
                    gridLines: {
                      color: "rgba(239, 239, 239, 0.3)"
                    }
                }],
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        fontColor: "rgba(239, 239, 239, 1)"
                    },
                    gridLines: {
                      color: "rgba(239, 239, 239, 0.3)"
                    }
                }]
            },
            legend: {
              display: true,
              labels: {
                fontColor: "rgba(239, 239, 239, 1)"
              }
            }
        }
      };

      if(ageChart) 
        ageChart.destroy();
      ageChart = new Chart(ctx, opt);
      ageChart.update();
      console.log(ageChart);
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
            obj[res.urls[idx]] = res.conversionA[idx] + " %";
          }

          arr.push(obj);
          obj = {};
          obj.name = "B";
          for (var idx2 in res.urls) {
            obj[res.urls[idx2]] = res.conversionB[idx2] + " %";
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
        return ["text-light"];
      }
    },
    getChartDataTotal(val) {
      if (val == "전환율") {
        console.log("전체-전환율");
        API.getChartConversion(
          "test_no=" + this.detail.test_no,
          (res) => {
            // console.log(res);
            this.chart.aChartData = res.achartData;
            this.chart.bChartData = res.bchartData;
            this.chart.date = res.date;
            //res를 chart로 만들기위해 데이터 넣어줘야함
            this.createChart();
          },
          (err) => {
            console.log(err);
          },
        );
      } else if (val == "이탈률") {
        console.log("전체-이탈률");
        API.getChartBounce(
          "test_no=" + this.detail.test_no,
          (res) => {
            // console.log(res);
            //res를 chart로 만들기위해 데이터 넣어줘야함            
            this.chart.aChartData = res.achartData;
            this.chart.bChartData = res.bchartData;
            this.chart.date = res.date;
            //res를 chart로 만들기위해 데이터 넣어줘야함
            this.createChart();
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
            // console.log(res);
            this.chart.aChartData = res.achartData;
            this.chart.bChartData = res.bchartData;
            this.chart.date = res.date;
            this.createChart();
          },
          (err) => {
            console.log(err);
          }
        );
    }
    },
    getChartDataGender(val) {
      if (val == "전환율") {
          console.log("gender-전환율");
          API.getChartGenderConversion(
            "test_no=" + this.detail.test_no,
            (res) => {
              console.log("RES : "+res);
              //res를 chart로 만들기위해 데이터 넣어줘야함
              this.genderChart.amaleChartData = res.amaleChartData;
              this.genderChart.afemaleChartData = res.afemaleChartData;
              this.genderChart.bmaleChartData = res.bmaleChartData;
              this.genderChart.bfemaleChartData = res.bfemaleChartData;
              this.createGenderChart();
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
              this.genderChart.amaleChartData = res.amaleChartData;
              this.genderChart.afemaleChartData = res.afemaleChartData;
              this.genderChart.bmaleChartData = res.bmaleChartData;
              this.genderChart.bfemaleChartData = res.bfemaleChartData;
              this.createGenderChart();
            },
            (err) => {
              console.log(err);
            }
          );
      }
    },
    genderClass(idx) {
      if (this.genderIndex === idx) {
        return ["bg-info", "text-light"];
      } else {
        return ["text-light"];
      }
    },
    getChartDataAge(val){
        if (val == "전환율") {
          console.log("age-전환율");
          API.getChartAgeConversion(
            "test_no=" + this.detail.test_no,
            (res) => {
              console.log(res);
              //res를 chart로 만들기위해 데이터 넣어줘야함
              this.ageChart.aChartData = res.achartData;
              this.ageChart.bChartData = res.bchartData;
              this.createAgeChart();
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
              this.ageChart.aChartData = res.achartData;
              this.ageChart.bChartData = res.bchartData;
              this.createAgeChart();
            },
            (err) => {
              console.log(err);
            }
          );
        }
        // } else if (val == "구매율") {
        //   console.log("age-구매율");
        //   API.getChartAgePurchase(
        //     "test_no=" + this.detail.test_no,
        //     (res) => {
        //       console.log(res);
        //       this.ageChart.aChartData = res.achartData;
        //       this.ageChart.bChartData = res.bchartData;
        //       this.createAgeChart();
        //     },
        //     (err) => {
        //       console.log(err);
        //     }
        //   );
        // }
    },
    ageClass(idx) {
      if (this.ageIndex === idx) {
        return ["bg-info", "text-light"];
      } else {
        return ["text-light"];
      }
    },
  },
  watch: {
    // 드롭다운 보고있다가 selected에 변화가 생겼을때 API부르기
    // chart에 넣기(어떻게 가져와서 각각 라인,바 차트에 넣을지 고민쓰)
    selected1(val) {
      console.log(val);
      this.getChartDataTotal(val)
    },
      // } else if (val == "구매율") {
      //   console.log("전체-구매율");
      //   API.getChartPurchase(
      //     "test_no=" + this.detail.test_no,
      //     (res) => {
      //       // console.log(res);
      //       //res를 chart로 만들기위해 데이터 넣어줘야함           
      //       this.chart.aChartData = res.achartData;
      //       this.chart.bChartData = res.bchartData;
      //       this.chart.date = res.date;
      //       //res를 chart로 만들기위해 데이터 넣어줘야함
      //       this.createChart();
      //     },
      //     (err) => {
      //       console.log(err);
      //     }
      //   );
      // }
    selected2(val) {
      this.getChartDataGender(val)
    },
      // } else if (val == "구매율") {
      //   console.log("gender-구매율");
      //   API.getChartGenderPurchase(
      //     "test_no=" + this.detail.test_no,
      //     (res) => {
      //       console.log(res);
      //       //res를 chart로 만들기위해 데이터 넣어줘야함
      //       this.genderChart.amaleChartData = 0;
      //       this.genderChart.afemaleChartData = 0;
      //       this.genderChart.bmaleChartData = 0;
      //       this.genderChart.bfemaleChartData = 0;
      //       this.createGenderChart();
      //     },
      //     (err) => {
      //       console.log(err);
      //     }
      //   );
      // }
    selected3(val) {
      this.getChartDataAge(val)
    },
    // selected4(val) {
    //   if (val == "전환율") {
    //     console.log("custom-전환율");
    //     API.getChartCustomConversion(
    //       "test_no=" + this.detail.test_no,
    //       (res) => {
    //         console.log(res);
    //         //res를 chart로 만들기위해 데이터 넣어줘야함
    //       },
    //       (err) => {
    //         console.log(err);
    //       }
    //     );
    //   } else if (val == "이탈률") {
    //     console.log("custom-이탈률");
    //     API.getChartCustomBounce(
    //       "test_no=" + this.detail.test_no,
    //       (res) => {
    //         console.log(res);
    //         //res를 chart로 만들기위해 데이터 넣어줘야함
    //       },
    //       (err) => {
    //         console.log(err);
    //       }
    //     );
    //   } else if (val == "회원가입률") {
    //     console.log("custom-회원가입률");
    //     API.getChartCustomJoin(
    //       "test_no=" + this.detail.test_no,
    //       (res) => {
    //         console.log(res);
    //         //res를 chart로 만들기위해 데이터 넣어줘야함
    //       },
    //       (err) => {
    //         console.log(err);
    //       }
    //     );
    //   }
    //   // } else if (val == "구매율") {
    //   //   console.log("custom-구매율");
    //   //   API.getChartCustomPurchase(
    //   //     "test_no=" + this.detail.test_no,
    //   //     (res) => {
    //   //       console.log(res);
    //   //       //res를 chart로 만들기위해 데이터 넣어줘야함
    //   //     },
    //   //     (err) => {
    //   //       console.log(err);
    //   //     }
    //   //   );
    //   // }
    // },
  },
  computed: {
    detail() {
      return this.$store.state.detail;
    },
  },
};
</script>
<style>
.mydetail {
  min-height: 100vh;
  background-color: #313131;
}
.modal-content {
  font-family: 'IBMPlexSansKR-SemiBold';
}
.myselect {
  cursor: pointer;
}
</style>