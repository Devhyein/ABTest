<template>
  <div>
    <SampleA v-if="page_type === 'A'" />
    <SampleB v-if="page_type === 'B'" />
  </div>
</template>

<script>
import API from "@/api/API";
import SampleA from "@/components/SampleA.vue";
import SampleB from "@/components/SampleB.vue";
import clickEvent from "@/click/click.js";

export default {
  name: "Main",
  components: {
    SampleA,
    SampleB,
  },
  data() {
    return {
      page_type: "",
    };
  },
  created() {
    var url = window.location.href;
    API.abAssign(
      "url=" + url,
      (res) => {
        console.log(res);
        this.$store.commit("addTestInfo", res);
        this.page_type = this.$store.state.test.page_type;
        clickEvent(this, null);
      },
      (err) => {
        console.log(err);
      }
    );
  },
};
</script>

<style></style>
