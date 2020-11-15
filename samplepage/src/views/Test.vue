<template>
  <div>
    <TestA v-if="page_type === 'A'" />
    <TestB v-if="page_type === 'B'" />
  </div>
</template>

<script>
import API from '@/api/API'
import TestA from '@/components/TestA.vue'
import TestB from '@/components/TestB.vue'
import clickEvent from '@/click/click.js'

export default {
  name: 'Test',
  components: {
    TestA,
    TestB,
  },
  data() {
    return {
      page_type: "",
    }
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
}
</script>

<style>

</style>