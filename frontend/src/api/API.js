import http from "./http-common.js";
import header from "./header.js"

const getApi = (url, data, callback, errorCallback) => {
    http.get(url + '?' + data, header())
        .then(res => {
            // RestAPI 서버가 null 을 응답하는 경우
            if(res == null) {
                let error = {msg : 'Server response is NULL'};
                errorCallback(error);
            }
            // RestAPI 에서 null 이 아닌 응답이 왔을 때
            else {
                // res.data 가 Rest 서버에서 반환한 객체
                let responseData = res.data

                // 서버의 수행이 성공적이었다면 callback 수행
                if(responseData.status) {
                    // 서버의 수행이 성공적이었다면 토큰이 넘어옴
                    // sessionStorage.setItem('jwt-token', responseData.data.token);

                    callback(responseData.data);
                }
                // 서버에서 오류가 발생했다면
                else {
                    // 그 오류메시지로 errorCallback 부름
                    let error = {msg : responseData.msg};
                    errorCallback(error);
                }
            }
        })
        .catch(err => {
            err.msg = url + ': Exception Occurred';
            errorCallback(err);
        });
}

const postApi = (url, data, callback, errorCallback) => {
    http.post(url, data, header())
        .then(res => {
            // RestAPI 서버가 null 을 응답하는 경우
            if(res == null) {
                let error = {msg : 'Server response is NULL'};
                errorCallback(error);
            }
            // RestAPI 에서 null 이 아닌 응답이 왔을 때
            else {
                // res.data 가 Rest 서버에서 반환한 객체
                let responseData = res.data

                // 서버의 수행이 성공적이었다면 callback 수행
                if(responseData.status) {
                    // 서버의 수행이 성공적이었다면 토큰이 넘어옴
                    // sessionStorage.setItem('jwt-token', responseData.data.token);

                    callback(responseData.data);
                }
                // 서버에서 오류가 발생했다면
                else {
                    // 그 오류메시지로 errorCallback 부름
                    let error = {msg : responseData.msg};
                    errorCallback(error);
                }
            }
        })
        .catch(err => {
            err.msg = url + ': Exception Occurred';
            errorCallback(err);
        });
}

const putApi = (url, data, callback, errorCallback) => {
    http.put(url, data, header())
        .then(res => {
            // RestAPI 서버가 null 을 응답하는 경우
            if(res == null) {
                let error = {msg : 'Server response is NULL'};
                errorCallback(error);
            }
            // RestAPI 에서 null 이 아닌 응답이 왔을 때
            else {
                // res.data 가 Rest 서버에서 반환한 객체
                let responseData = res.data

                // 서버의 수행이 성공적이었다면 callback 수행
                if(responseData.status) {
                    // 서버의 수행이 성공적이었다면 토큰이 넘어옴
                    // sessionStorage.setItem('jwt-token', responseData.data.token);

                    callback(responseData.data);
                }
                // 서버에서 오류가 발생했다면
                else {
                    // 그 오류메시지로 errorCallback 부름
                    let error = {msg : responseData.msg};
                    errorCallback(error);
                }
            }
        })
        .catch(err => {
            err.msg = url + ': Exception Occurred';
            errorCallback(err);
        });
}

const deleteApi = (url, data, callback, errorCallback) => {
    http.delete(url + '?' + data, header())
        .then(res => {
            // RestAPI 서버가 null 을 응답하는 경우
            if(res == null) {
                let error = {msg : 'Server response is NULL'};
                errorCallback(error);
            }
            // RestAPI 에서 null 이 아닌 응답이 왔을 때
            else {
                // res.data 가 Rest 서버에서 반환한 객체
                let responseData = res.data

                // 서버의 수행이 성공적이었다면 callback 수행
                if(responseData.status) {
                    // 서버의 수행이 성공적이었다면 토큰이 넘어옴
                    // sessionStorage.setItem('jwt-token', responseData.data.token);
                    
                    callback(responseData.data);
                }
                // 서버에서 오류가 발생했다면
                else {
                    // 그 오류메시지로 errorCallback 부름
                    let error = {msg : responseData.msg};
                    errorCallback(error);
                }
            }
        })
        .catch(err => {
            err.msg = url + ': Exception Occurred';
            errorCallback(err);
        });
}

const API = {
    createTest:(data,callback,errorCallback)=>postApi('/spring/createtest',data,callback,errorCallback),
    getTestList:(data,callback,errorCallback)=>getApi('/spring/testlist',data,callback,errorCallback),
    getTestListBefore:(data,callback,errorCallback)=>getApi('/spring/testlist/before',data,callback,errorCallback),
    getTestListProgress:(data,callback,errorCallback)=>getApi('/spring/testlist/progress',data,callback,errorCallback),
    getTestListComplete:(data,callback,errorCallback)=>getApi('/spring/testlist/complete',data,callback,errorCallback),
    modifyTest:(data,callback,errorCallback)=>putApi('/spring/modify/test',data,callback,errorCallback),
    deleteTest:(data,callback,errorCallback)=>deleteApi('/spring/delete/test',data,callback,errorCallback),
   
    getDetailTest:(data,callback,errorCallback)=>getApi('/spring/detail/test',data,callback,errorCallback),
    getDetailTestGender:(data,callback,errorCallback)=>getApi('/spring/detail/gender/test',data,callback,errorCallback),
    getDetailTestAge:(data,callback,errorCallback)=>getApi('/spring/detail/age/test',data,callback,errorCallback),
    getDetailTestCustom: (data, callback, errorCallback) => getApi('/spring/detail/custom/test', data, callback, errorCallback),
    
    getTableDataGender:(data,callback,errorCallback)=>getApi('/spring/detail/table/gender/test',data,callback,errorCallback),
    getTableDataAge:(data,callback,errorCallback)=>getApi('/spring/detail/table/age/test',data,callback,errorCallback),

    getDetailTestConversionWithUrl:(data,callback,errorCallback)=>getApi('/spring/detail/conversion/url',data,callback,errorCallback),


    getChartConversion:(data,callback,errorCallback)=>getApi('/spring/chart/test/conversion',data,callback,errorCallback),
    getChartBounce:(data,callback,errorCallback)=>getApi('/spring/chart/test/bounce',data,callback,errorCallback),
    getChartJoin:(data,callback,errorCallback)=>getApi('/spring/chart/test/join',data,callback,errorCallback),
    getChartPurchase:(data,callback,errorCallback)=>getApi('/spring/chart/test/purchase',data,callback,errorCallback),

    getChartGenderConversion:(data,callback,errorCallback)=>getApi('/spring/chart/gender/conversion',data,callback,errorCallback),
    getChartGenderBounce:(data,callback,errorCallback)=>getApi('/spring/chart/gender/bounce',data,callback,errorCallback),
    getChartGenderPurchase:(data,callback,errorCallback)=>getApi('/spring/chart/gender/purchase',data,callback,errorCallback),
    
    getChartAgeConversion:(data,callback,errorCallback)=>getApi('/spring/chart/age/conversion',data,callback,errorCallback),
    getChartAgeBounce:(data,callback,errorCallback)=>getApi('/spring/chart/age/bounce',data,callback,errorCallback),
    getChartAgePurchase:(data,callback,errorCallback)=>getApi('/spring/chart/age/purchase',data,callback,errorCallback),

    
    getChartCustomConversion:(data,callback,errorCallback)=>getApi('/spring/chart/custom/conversion',data,callback,errorCallback),
    getChartCustomBounce:(data,callback,errorCallback)=>getApi('/spring/chart/custom/bounce',data,callback,errorCallback),
    getChartCustomJoin:(data,callback,errorCallback)=>getApi('/spring/chart/custom/join',data,callback,errorCallback),
    getChartCustomPurchase:(data,callback,errorCallback)=>getApi('/spring/chart/custom/purchase',data,callback,errorCallback),
   
    manageLogin:(data,callback,errorCallback)=>postApi('/spring/login',data,callback,errorCallback),

    login:(data,callback,errorCallback)=>postApi('/account/login',data,callback,errorCallback),
    join:(data,callback,errorCallback)=>postApi('/account/join',data,callback,errorCallback),
    checkId:(data,callback,errorCallback)=>getApi('/account/checkId',data,callback,errorCallback),
}

export default API