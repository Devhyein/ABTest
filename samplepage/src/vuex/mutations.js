export default {
    addUserInfo(state, user) {
        state.user = user;
        state.isLogin = true;
    },
    deleteUserInfo(state) {
        state.user = {};
        state.isLogin = false;
    },
    addTestInfo(state, test){
        state.test = test;
    },
}