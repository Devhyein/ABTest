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
    addSampleUserInfo(state,sampleuser){
        state.sampleuser = sampleuser;
        state.isTestLogin = true;
    },
    deleteSampleUserInfo(state) {
        state.sampleuser = {};
        state.isTestLogin = false;
    },
}