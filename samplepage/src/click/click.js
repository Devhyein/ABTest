import API from "../api/API.js";

export default function clickEvent(vue, url) {
  var vueTest = vue.$store.state.test;
  var vueUser = vue.$store.state.user;

  let today = new Date();

  let year = today.getFullYear(); // 년도
  let month = today.getMonth() + 1; // 월
  let date = today.getDate(); // 날짜
  var d = year + "-" + month + "-" + date;

  var info = {
    test_no: vueTest.test_no,
    page_type: vueTest.page_type,
    session_id: vueTest.session_id,
    url: url,
    date: d,
    signed: vueUser.email != undefined,
    user: vueUser,
  };

  console.log("click info",info);

  API.clickEvent(
    info,
    (res) => {
      console.log("click console",res);
      if (url != null) {
        // if (vue.$router.currentRoute.name == "Main") {
        //   console.log("reload");
          clickEvent(vue,null);
        // } else {
        //   console.log("main으로 컴백");
        //   clickEvent(vue,null);
        //   vue.$router.push({ name: "Main" });
        // }
      }
    },
    (err) => {
      console.log(err);
    }
  );
}
