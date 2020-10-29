<template>
  <div class = "headerdiv">
    <img src="/img/logo.png" class="ml-5"/>
    <div class="a"></div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
    };
  },

};
</script>
<style scoped>
img{
    width: 150px;
    height: 80px;
    float: left;
}
.a{
    clear: both;
}
.headerdiv{
    background-color: lightgray;
    margin-bottom: 20px;
}

</style>