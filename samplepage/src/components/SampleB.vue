<template>
  <div id="sample">
    <!-- Header -->
    <b-navbar class="mynavbar d-flex justify-content-between">
      <b-navbar-brand href="#">
        <img :src="encar" class="mylogo" alt="encar logo" />
      </b-navbar-brand>
      <b-icon-list v-b-toggle.sidebar class="myicon mt-3"></b-icon-list>
    </b-navbar>
    <b-sidebar id="sidebar" bg-variant="light" right backdrop shadow>
      <template #footer>
        <div class="mysidefooter d-flex justify-content-around py-3">
          <small class="d-block">이벤트</small>
          <small class="d-block">공지사항</small>
          <small class="d-block" v-if="!isLogin" @click="login"
            >로그인</small
          >
          <small class="d-block" v-else @click="logout">로그아웃</small>
        </div>
      </template>
      <div class="d-flex justify-content-around px-2 pt-3 bg-white">
        <div>
          <b-icon-eye class="myicon mb-1"></b-icon-eye>
          <p>최근본차</p>
        </div>
        <div>
          <b-icon-search class="myicon mb-1"></b-icon-search>
          <p @click="search()">최근검색</p>
        </div>
        <div>
          <b-icon-heart class="myicon mb-1"></b-icon-heart>
          <p>찜한차량</p>
        </div>
      </div>
      <div class="text-left font-weight-bold ml-3 py-3">
        <h5 class="m-0">차량 구매</h5>
      </div>
      <div class="text-left pl-3 py-3 bg-white">
        <p @click="search()">차량 검색</p>
        <p @click="buy()">홈 서비스</p>
        <p @click="buy()">이 차 어때?</p>
        <p @click="buy()">신차 시세</p>
        <p @click="buy()">중고차 시세</p>
        <p>보험료 계산</p>
        <p class="m-0">금융</p>
      </div>
      <div class="text-left font-weight-bold ml-3 py-3">
        <h5 class="m-0">차량 판매</h5>
      </div>
      <div class="text-left pl-3 py-3 bg-white">
        <p @click="sell()">진단 등록 예약</p>
      </div>
      <div class="text-left font-weight-bold ml-3 py-3">
        <h5 class="m-0">정보</h5>
      </div>
      <div class="text-left pl-3 py-3 bg-white">
        <p>전기<b-icon-dot></b-icon-dot>친환경차</p>
        <p class="m-0">미디어</p>
      </div>
    </b-sidebar>

    <div class="mybody">
      <div class="text-left ml-3 mt-4">
        <strong>내 차를 마련하고 싶으신가요?</strong>
      </div>
      <b-input-group class="myinputgroup py-2 mx-3 my-3 sticky-top bg-white">
        <b-input-group-prepend>
          <b-button class="mysearch bg-white"
            ><b-icon-search @click="search()"></b-icon-search
          ></b-button>
        </b-input-group-prepend>
        <b-form-input
          type="text"
          class="myinputtext pl-0"
          placeholder="129,431개의 매물이 기다리고 있어요."
        ></b-form-input>
      </b-input-group>
      <div class="d-flex justify-content-between mx-3">
        <div class="d-flex align-items-center">
          <small class="mr-2" @click="buy()">엔카진단</small>
          <small class="mr-2" @click="buy()">엔카보증</small>
          <small @click="buy()">엔카홈서비스</small>
        </div>
        <div class="d-flex align-items-center">
          <small @click="search()">최근검색 <b-icon-chevron-right></b-icon-chevron-right></small>
        </div>
      </div>
      <div class="text-left ml-3 mt-5">
        <strong>내 차를 팔고 싶으신가요?</strong>
      </div>
      <div class="d-flex justify-content-between mx-3 my-3">
        <div class="d-flex align-items-center">
          <small class="mr-2" @click="sell()">비교견적</small>
          <small @click="sell()">셀프등록</small>
        </div>
        <div class="d-flex align-items-center">
          <small class="myarrow"
            ><b-icon-arrow-right></b-icon-arrow-right
          ></small>
        </div>
      </div>
      <div class="mt-5 py-5 bg-secondary text-light">광고 이미지</div>
      <div class="py-3 bg-light">
        <strong>안심하고 구매하는 중고차</strong>
        <div @click="buy()"
          class="d-flex justify-content-between mx-3 my-3 px-3 py-3 bg-white mydiv"
        >
          <div class="d-flex">
            <b-avatar icon="house" variant="light" class="mr-3"></b-avatar>
            <div class="text-left">
              <small class="text-secondary">7일간 타보고 결정!</small>
              <small class="d-block"><strong>엔카홈서비스 차량</strong></small>
            </div>
          </div>
          <div class="d-flex align-items-center text-secondary">
            <b-icon-chevron-right></b-icon-chevron-right>
          </div>
        </div>
        <div @click="buy()"
          class="d-flex justify-content-between mx-3 my-3 px-3 py-3 bg-white mydiv"
        >
          <div class="d-flex">
            <b-avatar icon="award" variant="light" class="mr-3"></b-avatar>
            <div class="text-left">
              <small class="text-secondary"
                >보증수리까지 제공되는 안심차량</small
              >
              <small class="d-block"><strong>엔카보증 차량</strong></small>
            </div>
          </div>
          <div class="d-flex align-items-center text-secondary">
            <b-icon-chevron-right></b-icon-chevron-right>
          </div>
        </div>
        <div @click="buy()"
          class="d-flex justify-content-between mx-3 my-3 px-3 py-3 bg-white mydiv"
        >
          <div class="d-flex">
            <b-avatar
              icon="check2-circle"
              variant="light"
              class="mr-3"
            ></b-avatar>
            <div class="text-left">
              <small class="text-secondary"
                >진단마스터가 직접 진단한 무사고</small
              >
              <small class="d-block"><strong>엔카진단 차량</strong></small>
            </div>
          </div>
          <div class="d-flex align-items-center text-secondary">
            <b-icon-chevron-right></b-icon-chevron-right>
          </div>
        </div>
        <div @click="buy()"
          class="d-flex justify-content-between mx-3 mt-3 px-3 py-3 bg-white mydiv"
        >
          <div class="d-flex">
            <b-avatar icon="gift" variant="light" class="mr-3"></b-avatar>
            <div class="text-left">
              <small class="text-secondary">믿고 방문할 수 있는</small>
              <small class="d-block"><strong>헛걸음보상 차량</strong></small>
            </div>
          </div>
          <div class="d-flex align-items-center text-secondary">
            <b-icon-chevron-right></b-icon-chevron-right>
          </div>
        </div>
      </div>
      <div class="py-3">
        <strong>엔카의 자동차 리뷰, 이 차 어때?</strong>
        <div class="my-3 py-5 bg-secondary text-light">광고 캐러셀</div>
      </div>
      <div class="py-3 bg-light">
        <strong>전기<b-icon-dot></b-icon-dot>친환경차도 엔카에서. </strong>
        <strong class="text-primary">EV포스트</strong>
        <div class="my-3 py-4 bg-secondary text-light">포스트</div>
      </div>
    </div>

    <!-- Footer -->
    <b-nav justified class="myfooter pt-2 bg-white">
      <b-nav-item>
        <b-icon-house-door class="text-danger"></b-icon-house-door>
        <p class="mytab text-danger mt-1 mb-2">홈</p>
      </b-nav-item>
      <b-nav-item>
        <b-icon-tag class="text-dark"></b-icon-tag>
        <p class="mytab text-dark mt-1 mb-2" @click="sell()">내차팔기</p>
      </b-nav-item>
      <b-nav-item>
        <b-icon-graph-up class="text-dark"></b-icon-graph-up>
        <p class="mytab text-dark mt-1 mb-2">시세</p>
      </b-nav-item>
      <b-nav-item>
        <b-icon-calculator class="text-dark"></b-icon-calculator>
        <p class="mytab text-dark mt-1 mb-2">금융</p>
      </b-nav-item>
      <b-nav-item>
        <b-icon-person class="text-dark"></b-icon-person>
        <p class="mytab text-dark mt-1 mb-2">마이페이지</p>
      </b-nav-item>
    </b-nav>
  </div>
</template>

<script>
import encar from "@/assets/encar.png";
import clickEvent from "@/click/click.js";

export default {
  name: "SampleB",
  data() {
    return {
      encar,
    };
  },
  methods: {
    login() {
      console.log("로그인");
      this.$router.push("/sample/login");
    },
    sell() {
      console.log("팔기");
      clickEvent(this, "/sell");
    },
    buy() {
      console.log("사기");
      clickEvent(this, "/buy");
    },
    search(){
      console.log("검색");
      clickEvent(this, "/search");
    },
    logout() {
      console.log("로그아웃");
      this.$store.commit("deleteUserInfo");
      sessionStorage.removeItem("userInfo");
    },
  },
  computed: {
    isLogin() {
      return this.$store.state.isLogin;
    },
  },
};
</script>

<style scoped>
#sample {
  font-family: "IBMPlexSansKR-SemiBold";
}
.mynavbar {
  height: 50px;
}
.myicon {
  font-size: x-large;
}
.mylogo {
  height: 40px;
}
.mysidebar {
  height: 620px;
}
.mysidefooter {
  border-top: 1px solid lightgray;
}
.mybody {
  padding-bottom: 70px;
}
.myinputgroup {
  border: 1px solid red;
  border-radius: 0.25rem;
  width: auto;
}
.mysearch {
  border: none;
  color: red;
}
.myinputtext {
  border: none;
}
.myarrow {
  color: red;
}
.mydiv {
  border-radius: 0.5rem;
}
.myfooter {
  position: fixed;
  bottom: 0;
  width: 100%;
}
.mytab {
  font-size: x-small;
}
a.nav-link {
  padding: 0;
}
</style>
