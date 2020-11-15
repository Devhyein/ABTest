<template>
  <div id="test">
    <!-- Header -->
    <b-nav justified class="mynavbar align-items-center">
      <b-nav-item class="d-flex justify-content-start">
        <b-icon-chevron-left
          @click="out"
          class="text-white mx-3"
        ></b-icon-chevron-left>
      </b-nav-item>
      <b-nav-item>
        <h5 class="m-0 text-white font-weight-bolder">Solutions</h5>
      </b-nav-item>
      <b-nav-item>
        <b-icon-blank></b-icon-blank>
      </b-nav-item>
    </b-nav>

    <div class="mybody">
      <div>
        <img :src="bora" class="mylogo" alt="bora logo" />
      </div>
      <h5 class="my-4">A/B 테스팅 솔루션 플랫폼</h5>

      <b-button
        block
        variant="success"
        class="my-3"
        v-if="!isLogin"
        @click="login"
        >로그인</b-button
      >
      <b-button block variant="success" class="my-3" v-else @click="logout"
        >로그아웃</b-button
      >

      <!-- <b-button block variant="success" class="my-3" @click="join"
        >회원가입</b-button
      > -->
      <b-button block variant="danger" class="text-light my-3" @click="buy"
        >구매하기</b-button
      >
      <b-button block variant="info" class="text-light" @click="detail"
        >상세보기</b-button
      >
    </div>
  </div>
</template>

<script>
import bora from "@/assets/bora.png";
import clickEvent from "@/click/sampleclick.js";

export default {
  name: "TestB",
  data() {
    return {
      bora,
    };
  },
  methods: {
    logout() {
      this.$store.commit("deleteSampleUserInfo");
      location.reload();
    },
    // join() {
    //   console.log("회원가입");
    //   this.$router.push({ name: "TestJoin" });
    // },
    login() {
      console.log("로그인");
      this.$router.push({ name: "TestLogin" });
    },
    buy() {
      console.log("사기");
      clickEvent(this, "/buy");
    },
    detail() {
      console.log("상세");
      clickEvent(this, "/detail");
    },
    out() {
      console.log("이탈");
      clickEvent(this, "/out");
    },
  },
  computed: {
    isLogin() {
      return this.$store.state.isTestLogin;
    },
  },
};
</script>

<style scoped>
#test {
  font-family: "IBMPlexSansKR-SemiBold";
}
.mynavbar {
  height: 50px;
  background-color: rgb(122, 56, 209);
}
.mylogo {
  background-color: #313131;
  height: 200px;
}
.mybody {
  padding: 100px 80px;
}
a.nav-link {
  padding: 0;
}
</style>
