export default {
    addUserInfo(state, user) {
        state.user = user;
    },
    deleteUserInfo(state) {
        state.user = "";
    },
    addTestInfo(state, test){
        state.test = test;
    },
}