<template>
  <div>
    <EncarHeader />
    <div class="col-8 col-m-10 m-auto">
      <!-- 이 부분 예쁘게 해주세요(와이어프레임 참고해주세요) -->
      <h3>
        {{ detail.test_title }}
        <b-button pill disabled :variant="detail.color">{{detail.status}}</b-button>
      </h3>

      {{ detail.start }} - {{ detail.end }}
      <b-tabs v-model="tabIndex">
        <b-tab title="전체" :title-link-class="linkClass(0)">
          <b-form-select v-model="selected1" :options="options1" />
          <div>
             <b-button :variant="information">페이지별 전환율 상세보기</b-button>
            <b-table hover :items="tableData" :fields="fields"> </b-table>
          </div>
        </b-tab>
        <b-tab title="성별" :title-link-class="linkClass(1)">
          <b-form-select v-model="selected2" :options="options2" />
          <div>
            <b-table hover :items="tableDataGender" :fields="fields"> </b-table>
          </div>
        </b-tab>
        <b-tab title="나이" :title-link-class="linkClass(2)">
          <b-form-select v-model="selected3" :options="options2" />
          <div>
            <b-table hover :items="tableDataAge" :fields="fields"> </b-table>
          </div>
        </b-tab>
        <b-tab title="사용자 지정" :title-link-class="linkClass(3)">
          <b-form-select v-model="selected4" :options="options1" />
          <div>
            <b-table hover :items="tableDataCustom" :fields="fields"> </b-table>
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<script>
import API from "@/api/API";

import EncarHeader from "@/components/Header";

export default {
  components: {
    EncarHeader,
  },
  data() {
    return {
      tabIndex: 0,
      tableData: [],
      tableDataGender: [],
      tableDataAge: [],
      tableDataCustom: [],
      // chart어떤식으로 받아야할지 공부
      //chart:[],
      fields: [
        { key: "assortment", label: "구분" },
        { key: "testA", label: "A 안" },
        { key: "testB", label: "B 안" },
        { key: "rate", label: "증감률" },
      ],
      //백엔드 연동했을땐 여기있는거 주석 해제해서 사용하고 샘플 지우기
      // detail: {},
      // detailGender: {},
      // detailAge: {},
      // detailCustom: {},

      // 아래는 샘플 값 넣어놓은 거예요 나중에 지우기
      detail: {
        test_no: 2,
        test_title: "버튼테스트",
        start: "2020.10.12",
        end: "2020.11.11",
        status: "진행중",
        conversionA: 30.6,
        conversionB: 40,
        con_rate: 33,
        bounceA: 40,
        bounceB: 30,
        bo_rate: -33,
        joinA: 20,
        joinB: 40,
        jo_rate: 100,
        purchaseA: 40,
        purchaseB: 10,
        pur_rate: -75,
      },
      detailGender: {
        test_no: 2,
        test_title: "버튼테스트",
        start: "2020.10.12",
        end: "2020.11.11",
        status: "진행중",
        conversionA: 10,
        conversionB: 20,
        con_rate: 30,
        bounceA: 40,
        bounceB: 50,
        bo_rate: 60,
        joinA: 70,
        joinB: 80,
        jo_rate: 100,
        purchaseA: 90,
        purchaseB: 70,
        pur_rate: 60,
      },
      detailAge: {
        test_no: 2,
        test_title: "버튼테스트",
        start: "2020.10.12",
        end: "2020.11.11",
        status: "진행중",
        conversionA: 30,
        conversionB: 50,
        con_rate: 35,
        bounceA: 50,
        bounceB: 30,
        bo_rate: -35,
        joinA: 20,
        joinB: 40,
        jo_rate: 100,
        purchaseA: 40,
        purchaseB: 10,
        pur_rate: -75,
      },
      detailCustom: {
        test_no: 2,
        test_title: "버튼테스트",
        start: "2020.10.12",
        end: "2020.11.11",
        status: "진행중",
        conversionA: 50,
        conversionB: 50,
        con_rate: 0,
        bounceA: 50,
        bounceB: 25,
        bo_rate: -53,
        joinA: 25,
        joinB: 50,
        jo_rate: 25,
        purchaseA: 10,
        purchaseB: 20,
        pur_rate: 100,
      },
      /////////////////////////////////////////여기까지 샘플데이터////////////////////////////////////////////

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
    API.getDetailTestGender(
      "test_no=" + this.detail.test_no,
      (res) => {
        console.log(res);
        this.detailGender = res;
      },
      (err) => {
        console.log(err);
      }
    );
    API.getDetailTestAge(
      "test_no=" + this.detail.test_no,
      (res) => {
        console.log(res);
        this.detailAge = res;
      },
      (err) => {
        console.log(err);
      }
    );
    API.getDetailTestCustom(
      "test_no=" + this.detail.test_no,
      (res) => {
        console.log(res);
        this.detailCustom = res;
      },
      (err) => {
        console.log(err);
      }
    );

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

    this.tableDataGender = [
      {
        assortment: "전환율",
        testA: this.detailGender.conversionA + "%",
        testB: this.detailGender.conversionB + "%",
        rate: this.detailGender.con_rate + "%",
      },
      {
        assortment: "이탈률",
        testA: this.detailGender.bounceA + "%",
        testB: this.detailGender.bounceA + "%",
        rate: this.detailGender.bo_rate + "%",
      },
      {
        assortment: "회원가입률",
        testA: this.detailGender.joinA + "%",
        testB: this.detailGender.joinB + "%",
        rate: this.detailGender.jo_rate + "%",
      },
      {
        assortment: "구매율",
        testA: this.detailGender.purchaseA + "%",
        testB: this.detailGender.purchaseB + "%",
        rate: this.detailGender.pur_rate + "%",
      },
    ];

    this.tableDataAge = [
      {
        assortment: "전환율",
        testA: this.detailAge.conversionA + "%",
        testB: this.detailAge.conversionB + "%",
        rate: this.detailAge.con_rate + "%",
      },
      {
        assortment: "이탈률",
        testA: this.detailAge.bounceA + "%",
        testB: this.detailAge.bounceA + "%",
        rate: this.detailAge.bo_rate + "%",
      },
      {
        assortment: "회원가입률",
        testA: this.detailAge.joinA + "%",
        testB: this.detailAge.joinB + "%",
        rate: this.detailAge.jo_rate + "%",
      },
      {
        assortment: "구매율",
        testA: this.detailAge.purchaseA + "%",
        testB: this.detailAge.purchaseB + "%",
        rate: this.detailAge.pur_rate + "%",
      },
    ];

    this.tableDataCustom = [
      {
        assortment: "전환율",
        testA: this.detailCustom.conversionA + "%",
        testB: this.detailCustom.conversionB + "%",
        rate: this.detailCustom.con_rate + "%",
      },
      {
        assortment: "이탈률",
        testA: this.detailCustom.bounceA + "%",
        testB: this.detailCustom.bounceA + "%",
        rate: this.detailCustom.bo_rate + "%",
      },
      {
        assortment: "회원가입률",
        testA: this.detailCustom.joinA + "%",
        testB: this.detailCustom.joinB + "%",
        rate: this.detailCustom.jo_rate + "%",
      },
      {
        assortment: "구매율",
        testA: this.detailCustom.purchaseA + "%",
        testB: this.detailCustom.purchaseB + "%",
        rate: this.detailCustom.pur_rate + "%",
      },
    ];

    if (this.detail.status == "진행전") this.detail.color = "success";
    else if (this.detail.status == "진행중") this.detail.color = "warning";
    else this.detail.color = "danger";
  },
  methods: {
    linkClass(idx) {
      if (this.tabIndex === idx) {
        return ["bg-danger", "text-light"];
      } else {
        return ["bg-light", "text-dark"];
      }
    },
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
    //백엔드에서 가져온 값
    // detail() {
    //   return this.$store.state.detail;
    // },
  },
};
</script>