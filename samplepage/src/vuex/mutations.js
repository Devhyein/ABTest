export default {
    addUserInfo(state, user) {
        state.user = user;
    },
    deleteUserInfo(state) {
        state.user = "";
    },
    addSampleInfo(state, sample){
        state.sample = sample;
    }
}