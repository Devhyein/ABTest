import Vue from "vue";
import VueRouter from "vue-router";
import SampleA from "@/views/SampleA.vue";
import SampleB from "@/views/SampleB.vue";
import Join from "@/views/Join.vue";
import Login from "@/views/Login.vue";

Vue.use(VueRouter);

export default new VueRouter({
  linkExactActiveClass: "active",
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/sample",
      redirect: "main",
      children: [
        {
          path: "/main",
          name: "main",
          component: SampleA,
        },
        {
          path: "/sample/a",
          name: "SampleA",
          component: SampleA,
        },
        {
          path: "/sample/b",
          name: "SampleB",
          component: SampleB,
        },
        {
          path: "/sample/join",
          name: "Join",
          component: Join,
        },
        {
          path: "/sample/login",
          name: "Login",
          component: Login,
        },
      ],
    },
  ],
});
