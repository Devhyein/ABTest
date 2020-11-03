<template>
  <div>
    <EncarHeader />
    <div class="col-8 col-m-10 m-auto">
      생각보다어렵네?
      <b-tabs content-class="mt-3">
        <b-tab title="전체" active>
          <p>저언체</p>
          <!-- <b-dropdown
            id="dropdown-1"
            v-model="selected"
            :options="options"
            class="m-md-2"
          >
            <b-dropdown-item>전환율</b-dropdown-item>
            <b-dropdown-item>이탈율</b-dropdown-item>
            <b-dropdown-item>회원가입율</b-dropdown-item>
            <b-dropdown-item>구매율</b-dropdown-item>
          </b-dropdown> -->
          <b-form-select v-model="selected" :options="options1">
          </b-form-select>
          <div>Selected: {{ selected }}</div>
          <div>
            <b-table hover :items="items"></b-table>
          </div>
        </b-tab>
        <b-tab title="성별">
          <p>성별탭</p>
          <b-form-select v-model="selected" :options="options2">
          </b-form-select>
          <div>Selected: {{ selected }}</div>
        </b-tab>
        <b-tab title="나이"
          ><p>나이탭</p>
          <b-form-select v-model="selected" :options="options2">
          </b-form-select>
          <div>Selected: {{ selected }}</div>
        </b-tab>
        <b-tab title="사용자지정"
          ><p>상자지정</p>
          <b-form-select v-model="selected" :options="options1">
          </b-form-select>
          <div>Selected: {{ selected }}</div>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<script>
import EncarHeader from "@/components/Header";

export default {
  components: {
    EncarHeader,
  },
  data() {
    return {
      selected: "null",
      options1: [
        { value: null, text: "구분" },
        { value: "전환율", text: "전환율" },
        { value: "이탈율", text: "이탈율" },
        { value: "회원가입율", text: "회원가입율" },
        { value: "구매율", text: "구매율" },
      ],
      options2: [
        { value: null, text: "구분" },
        { value: "전환율", text: "전환율" },
        { value: "이탈율", text: "이탈율" },
        { value: "구매율", text: "구매율" },
      ],
      items: [{ 구분: "전환율", A안: "40%", B안: "30%", 증가율: "+33%" }],
    };
  },
};
</script>
