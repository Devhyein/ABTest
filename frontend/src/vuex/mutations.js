export default {
    addUserInfo(state, email) {
        state.email = email;
    },
    deleteUserInfo(state) {
        state.email = "";
    },
    addDetail(state, detail){
        state.detail = detail;
    }
}