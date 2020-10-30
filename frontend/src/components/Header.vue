<template>
  <div class="headerdiv">
    <img src="/img/logo.png" class="ml-5" />
    <b-icon icon="person-circle" scale="2" @click="login()"/>
    <div class="a"></div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {};
  },
  methods: {
    login() {
      this.$router.push("/login");
    },
  },
};
</script>
<style scoped>
img {
  width: 150px;
  height: 80px;
  float: left;
}
b-icon{
  float: right;
}
.a {
  clear: both;
}
.headerdiv {
  background-color: lightgray;
  margin-bottom: 20px;
}
</style>
