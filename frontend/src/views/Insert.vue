<template>
  <div>
    <div class="home">
      <h1>Home!</h1>
    </div>
    <div class="title">
      실험 이름
      <input type="text" />
    </div>
    <div class="title">
      A 안
      <input type="text" />
    </div>
    <div class="title">
      A 안 별칭
      <input type="text" />
    </div>
    <div class="title">
      B 안
      <input type="text" />
    </div>
    <div class="title">
      B 안 별칭
      <input type="text" />
    </div>
    <div>시작일 : <input type="date" /> 종료일 : <input type="date" /></div>

    <div>
      <div class="mt-2">A안: {{ value }}%</div>
      <div class="mt-2">B안:{{ 100 - value }}%</div>
      <b-form-input id="range-1" v-model="value" type="range" min="0" max="100" step="10">
      </b-form-input>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      value:'50'
    };
  },
};
</script>
