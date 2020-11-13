import API from  "../api/API.js";

export default function clickEvent(vue, url) {
    var vueTest = vue.$store.state.test;
    var vueUser = vue.$store.state.user;
    
    console.log("Vue "+vue);
    console.log("vueTest "+vueTest);
    console.log(vueUser);
    let today = new Date();   

    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1;  // 월
    let date = today.getDate();  // 날짜
    var d = year + '-'+month+'-'+date;

    var info = {
        test_no : vueTest.test_no,
        page_type : vueTest.page_type,
        session_id : vueTest.session_id,
        url : url,
        date : d,
        signed : vue.$store.state.user.email != undefined,
        user : vue.$store.state.user.user
    };
    // console.log("user"+ this.$store.state.user.user);
    // console.log(info.user);
    API.clickEvent(
        info,
        (res) => {
            console.log(res);
          },
          (err) => {
            console.log(err);
          }
    );

  }