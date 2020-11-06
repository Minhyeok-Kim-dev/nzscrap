/**
 * 200724_kmh
 * 스크래핑 관련 js입니다.
 */
define(["jquery", "Promise"], function(jquery, Promise) {
    const SCRAP_URL = "https://183.111.102.219:9402/rest/ext";
    const DATA_DIVISION_LEN = 300;   // 데이터 분할전송량
    
    let _reqParamList = [];
    let _resParamList = [];
    
    function init() {
        _initEvents();
    }
    
    function _initEvents() {
        $("#btnTest").on("click", function() {
            _scrap();
        });
        
        $("#btnSend").on("click", function() {
            _sendData();  
        });
        
        $("#btnScrap").on("click", function() {
            $.ajax(_getContextPath() + "/scrap/scrap", {
                type : "post"
            })
            .done(function(data) { // success 시
                console.log("scrap~!!");
            })
            .fail(function(data) {
                alert("loadLogs failed");
            });
        });
    }

    function _scrap() {
        let reqParamList;
        let resParamList;
        let outJsonList;
        
        let fromDt = "20200101";
        let toDt = "20200805";
        
        _getReqParamList(fromDt, toDt).then(function(data) {
            if(typeof data === "object") {
                reqParamList = data;
                return data;
            }
        }).then(function(data) {
            console.log("level1 : bizbooks reqParamList");
            //console.log(data);
            return _getInJsonList(data);
        }).then(function(data) {
            console.log("level2 : inJsonParamList");
            console.log(data);
            
            let reqCd = data.length > 0 ? data[0].reqCd : "0000";
            return _sendScrap(data, reqCd);
            
        }).then(function(data) {
            console.log("level3 : sendScrap");
            console.log(data);
            
            outJsonList = data.outJsonList;
            return _getResParamList(reqParamList, data);
            
        }).then(function(data) {
            console.log("level4 : resParamList");
            console.log(data);
            
            resParamList = data;
            
            if( typeof reqParamList === "object" &&
                typeof resParamList === "object") {
                console.log("level4 : result");
                console.log(reqParamList);
                console.log(resParamList);
                
                return _insertHistory(reqParamList, resParamList);
            }
        }).then(function(data) {
            if(data === true) {
                return _getInsertDataList(outJsonList, resParamList);
            }
        }).then(function(data) {
            console.log("level5 : getInsertDataList");
            
            if(typeof data === "object") {
                return _insertDataList(data);
            }
        }).then(function(data) {
            
        });
    }

    
    function _sendData() {
        $.ajax(_getContextPath() + "/scrap/sendData", {
            type : "post",
            data: {
                scrapDt: "20200806"
            }
        })
        .done(function(data) { // success 시
            console.log("send~");
        })
        .fail(function(data) {
            alert("loadLogs failed");
        });
    }
    
    
    function _getContextPath() { 
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }
    
    function _getReqParamList(fromDt, toDt) {
        return new Promise(function(resolve) {
            $.ajax(_getContextPath() + "/scrap/getReqParamList", {
                type : "post",
                data: {
                    fromDt: fromDt,
                    toDt: toDt
                }
            })
            .done(function(data) { // success 시
                resolve(JSON.parse(data));
            })
            .fail(function(data) {
                alert("loadLogs failed");
            });
        });
    }
    
    function _getResParamList(reqList, resParam) {
        return new Promise(function(resolve) {
            let resList = [];
            let outJsonList = resParam.outJsonList;
            
            for(var i = 0; i < reqList.length; i++) {
                let appCd = outJsonList[i].appCd;
                let orgCd = outJsonList[i].orgCd;
                let svcCd = outJsonList[i].svcCd;
                
                // 공통필드 설정
                let res = {
                    compCd: reqList[i].compCd,
                    appCd: appCd,
                    orgCd: orgCd,
                    svcCd: svcCd,
                    keyCd: 0,
                    reqCd: reqList[i].reqCd,
                    resCd: resParam.resCd,
                    connCnt: 0,
                    errYn: outJsonList[i].errYn,
                    errMsg: outJsonList[i].errMsg,
                    outTime: "",
                    bridgeAppVer: resParam.bridgeAppVer,
                    bridgeHostNm: resParam.bridgeHostNm,
                    bridgeOsNm: resParam.bridgeOsNm,
                    bridgeReqDt: resParam.bridgeReqDt,
                    bridgeResDt: resParam.bridgeResDt,
                    worker: resParam.worker,
                    workerAppVer: resParam.workerAppVer,
                    workerHostNm: resParam.workerHostNm,
                    workerOsNm: resParam.workerOsNm,
                    workerReqDt: resParam.workerReqDt,
                    workerResDt: resParam.workerResDt
                }
                
                // 추가 필드 설정
                switch(orgCd) {
                // - 홈택스
                case "hometax":
                    res.connCnt = outJsonList[i].connCnt;
                    res.outTime = outJsonList[i].outTime;
                    break;
                // - 여신
                case "cardsales":
                    res.connCnt = outJsonList[i].connCnt;
                    break;
                // - 뱅킹
                // - 신용카드
                case "bank":
                case "cbk":
                case "card":
                case "ccd":
                    res.keyCd = reqList[i].keyCd;
                    break;
                }
                
                resList.push(res);
            }
            
            resolve(resList);
        });
    }
    
    function _getInJsonList(reqParamList) {
        return new Promise(function(resolve) {
            console.log(reqParamList);
            
            let inJsonList = [];
            
            for(var i = 0; i < reqParamList.length; i++) {
                // 공통 요청정보
                let appCd = reqParamList[i].appCd;
                let orgCd = reqParamList[i].orgCd;
                let svcCd = reqParamList[i].svcCd;
                
                // 인증서 정보
                let signCert = "\\\\183.111.102.219\\Cert\\" + reqParamList[i].signCert + "\\signCert.der";
                let signPri = "\\\\183.111.102.219\\Cert\\" + reqParamList[i].signPri + "\\signPri.key";
                let signPw = reqParamList[i].signPw;
                let reqCd = reqParamList[i].reqCd;
                
                let jsonData = {
                    appCd: appCd,
                    orgCd: orgCd,
                    svcCd: svcCd,
                    reqCd: reqCd
                };
                
                switch(orgCd) {
             
                case "hometax":     // 홈택스 정보
                    jsonData.signCert = signCert;
                    jsonData.signPri = signPri;
                    jsonData.signPw = signPw;
                    jsonData.bizNo = reqParamList[i].bizNo;
                    jsonData.inqrDtStrt = reqParamList[i].fromDt;
                    jsonData.inqrDtEnd = reqParamList[i].toDt;
                    break;
                case "bank":        // 뱅킹 정보
                case "cbk":
                    jsonData.loginMethod = "CERT";
                    jsonData.signCert = signCert;
                    jsonData.signPri = signPri;
                    jsonData.signPw = signPw;
                    jsonData.acctNo = reqParamList[i].acctNo;
                    jsonData.sdate = reqParamList[i].fromDt;
                    jsonData.edate = reqParamList[i].toDt;
                    jsonData.curCd = "KRW";
                    
                    // 은행코드 '0' left padding
                    let bankCd = reqParamList[i].bankCd * 1;
                    jsonData.bankCd = bankCd.length >= 3 ? 
                            bankCd : new Array(3 - (bankCd + "").length + 1).join("0") + bankCd;
                    break;
                case "card":        // 신용카드 정보
                case "ccd":
                    jsonData.loginMethod = "CERT";
                    jsonData.signCert = signCert;
                    jsonData.signPri = signPri;
                    jsonData.signPw = signPw;
                    jsonData.cardNo = reqParamList[i].cardNo;
                    jsonData.sdate = reqParamList[i].fromDt;
                    jsonData.edate = reqParamList[i].toDt;
                    
                    // 카드사코드 '0' left padding
                    let cardCd = reqParamList[i].cardCd * 1;
                    jsonData.cardCd = cardCd.length >= 3 ? 
                            cardCd : new Array(3 - (cardCd + "").length + 1).join("0") + cardCd;
                    break;
                case "cardsales":   // 여신 정보
                    jsonData.userId = reqParamList[i].userId;
                    jsonData.userPw = reqParamList[i].userPw;
                    jsonData.fromDate = reqParamList[i].fromDt;
                    jsonData.toDate = reqParamList[i].toDt;
                    break;
                }
                
                inJsonList.push(jsonData);
            }
            
            resolve(inJsonList);
        });
    }
    
    function _sendScrap(jsonList, reqCd) {
        // TODO: Scrap 모드 설정 (현재 전체 묶음으로 처리)
        return new Promise(function(resolve) {
            $.ajax(SCRAP_URL, {
                type : "post",
                dataType : "json",
                data: {
                    inJsonList: JSON.stringify(jsonList),
                    reqCd: reqCd
                }
            })
            .done(function(resParamList) { // success 시
                resolve(resParamList);
            })
            .fail(function(data) {
                alert("loadLogs failed");
            });
        });
    }
    
    function _insertHistory(reqList, resList) {
        return new Promise(function(resolve) {
            $.ajax(getContextPath() + "/scrap/insertHistory", {
                type : "post",
                dataType : "json",
                data: {
                    jsonReqList: JSON.stringify(reqList),
                    jsonResList: JSON.stringify(resList)
                }
            })
            .done(function(data) { // success 시
                resolve(true);
            })
            .fail(function(data) {
                alert("loadLogs failed");
            });
        });
    }
    
    function _getInsertDataList(outJsonList, resParamList) {
        
        return new Promise(function(resolve) {
            if(typeof outJsonList !== "object" || outJsonList.length === 0) {
                resolve(false);
            }
 
            let hometaxZ300XList = [];
            let hometaxZ300XItemList = [];
            let hometaxZ4001List = [];
            let hometaxZ4002List = [];
            let hometaxZ4010List = [];
            let hometaxZ4020List = [];
            let bankB0002List = [];
            let cbkB0002List = [];
            let cardC0005List = [];
            let ccdC0005List = [];
            let cardsalesB0002List = [];
            let cardsalesB0011DtlList = [];
            let cardsalesB0011SumList = [];
            let cardsalesB0021DtlList = [];
            let cardsalesB0021SumList = [];
            let cardsalesB0031DtlList = [];
            let cardsalesB0031SumList = [];
            
            for(var i = 0; i < outJsonList.length; i++) {
                let outJson = outJsonList[i];
                let compCd = resParamList[i].compCd;    // 사용자 회사코드
                let reqCd = resParamList[i].reqCd;
                
                // 스크랩 기업구분
                switch(outJson.orgCd) {
                // 홈택스
                case "hometax":     
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 전자세금계산서
                    case "Z3001":
                    case "Z3002":
                    case "Z3003":
                    case "Z3004":
                        // -- 내역
                        if(typeof outJson.list === "object" && outJson.list.length > 0) {
                            for(var j = 0; j < outJson.list.length; j++) {
                                let hometaxZ300X = outJson.list[j];
                                hometaxZ300X.compCd = compCd;
                                hometaxZ300X.svcCd = outJson.svcCd;
                                hometaxZ300X.reqCd = reqCd;
                                hometaxZ300XList.push(hometaxZ300X);
                            }
                        }
                        
                        // -- 품목
                        if(typeof outJson.itemList === "object" && outJson.itemList.length > 0) {
                            for(var j = 0; j < outJson.itemList.length; j++) {
                                let hometaxZ300XItem = outJson.itemList[j];
                                hometaxZ300XItem.compCd = compCd;
                                hometaxZ300XItem.svcCd = outJson.svcCd;
                                hometaxZ300XItem.reqCd = reqCd;
                                hometaxZ300XItemList.push(hometaxZ300XItem);
                            }
                            
                        }
                        break;
                    // - 현금영수증
                    case "Z4001":
                        if(typeof outJson.list === "object" && outJson.list.length > 0) {
                            for(var j = 0; j < outJson.list.length; j++) {
                                let hometaxZ4001 = outJson.list[j];
                                hometaxZ4001.compCd = compCd;
                                hometaxZ4001.reqCd = reqCd;
                                hometaxZ4001List.push(hometaxZ4001);
                            }
                        }
                        break;
                    case "Z4002":
                        if(typeof outJson.list === "object" && outJson.list.length > 0) {
                            for(var j = 0; j < outJson.list.length; j++) {
                                let hometaxZ4002 = outJson.list[j];
                                hometaxZ4002.compCd = compCd;
                                hometaxZ4002.reqCd = reqCd;
                                hometaxZ4002List.push(hometaxZ4002);
                            }
                        }
                        break;
                    // - 사업용 화물복지 신용카드
                    case "Z4010":
                        if(typeof outJson.list === "object" && outJson.list.length > 0) {
                            for(var j = 0; j < outJson.list.length; j++) {
                                let hometaxZ4010 = outJson.list[j];
                                hometaxZ4010.compCd = compCd;
                                hometaxZ4010.reqCd = reqCd;
                                hometaxZ4010List.push(hometaxZ4010);
                            }
                        }
                        break;
                    case "Z4020":
                        if(typeof outJson.list === "object" && outJson.list.length > 0) {
                            for(var j = 0; j < outJson.list.length; j++) {
                                let hometaxZ4020 = outJson.list[j];
                                hometaxZ4020.compCd = compCd;
                                hometaxZ4020.reqCd = reqCd;
                                hometaxZ4020List.push(hometaxZ4020);
                            }
                        }
                        break;
                    }
                    break;
                // 여신
                case "cardsales":
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 가맹점 수수료율/대금지급주기
                    case "B0002":
                        if(typeof outJson.outB0002.list === "object" 
                                && outJson.outB0002.list.length > 0) {
                            for(var j = 0; j < outJson.outB0002.list.length; j++) {
                                let cardsalesB0002 = outJson.outB0002.list[j];
                                cardsalesB0002.compCd = compCd;
                                cardsalesB0002.reqCd = reqCd;
                                cardsalesB0002List.push(cardsalesB0002);
                            }
                        }
                        break;
                    // - 기간별승인내역(일별)
                    case "B0011":
                        // -- 상세
                        if(typeof outJson.outB0011.listDtl === "object" 
                                && outJson.outB0011.listDtl.length > 0) {
                            for(var j = 0; j < outJson.outB0011.listDtl.length; j++) {
                                let cardsalesB0011Dtl = outJson.outB0011.listDtl[j];
                                cardsalesB0011Dtl.compCd = compCd;
                                cardsalesB0011Dtl.reqCd = reqCd;
                                cardsalesB0011DtlList.push(cardsalesB0011Dtl);
                            }
                        }
                        // -- 합계
                        if(typeof outJson.outB0011.listSum === "object" 
                                && outJson.outB0011.listSum.length > 0) {
                            for(var j = 0 ; j < outJson.outB0011.listSum.length; j++) {
                                let cardsalesB0011Sum = outJson.outB0011.listSum[j];
                                cardsalesB0011Sum.compCd = compCd;
                                cardsalesB0011Sum.reqCd = reqCd;
                                cardsalesB0011SumList.push(cardsalesB0011Sum);
                            }
                        }
                        break;
                    // - 기간별매입내역(일별)
                    case "B0021":
                        // -- 상세
                        if(typeof outJson.outB0021.listDtl === "object" 
                                && outJson.outB0021.listDtl.length > 0) {
                            for(var j = 0; j < outJson.outB0021.listDtl.length; j++) {
                                let cardsalesB0021Dtl = outJson.outB0021.listDtl[j];
                                cardsalesB0021Dtl.compCd = compCd;
                                cardsalesB0021Dtl.reqCd = reqCd;
                                cardsalesB0021DtlList.push(cardsalesB0021Dtl);
                            }
                        }
                        // -- 합계
                        if(typeof outJson.outB0021.listSum === "object" 
                                && outJson.outB0021.listSum.length > 0) {
                            for(var j = 0; j < outJson.outB0021.listSum.length; j++) {
                                let cardsalesB0021Sum =  outJson.outB0021.listSum[j];
                                cardsalesB0021Sum.compCd = compCd;
                                cardsalesB0021Sum.reqCd = reqCd;
                                cardsalesB0021SumList.push(cardsalesB0021Sum);
                            }
                        }
                        break;
                    // - 기간별입금내역
                    case "B0031":
                        // -- 상세
                        if(typeof outJson.outB0031.listDtl === "object" 
                                && outJson.outB0031.listDtl.length > 0) {
                            for(var j = 0; j < outJson.outB0031.listDtl.length; j++) {
                                let cardsalesB0031Dtl = outJson.outB0031.listDtl[j];
                                cardsalesB0031Dtl.compCd = compCd;
                                cardsalesB0031Dtl.reqCd = reqCd;
                                cardsalesB0031DtlList.push(cardsalesB0031Dtl);
                            }
                        }
                        // -- 합계
                        if(typeof outJson.outB0031.listSum === "object" 
                                && outJson.outB0031.listSum.length > 0) {
                            for(var j = 0; j < outJson.outB0031.listSum.length; j++) {
                                let cardsalesB0031Sum = outJson.outB0031.listSum[j];
                                cardsalesB0031Sum.compCd = compCd;
                                cardsalesB0031Sum.reqCd = reqCd;
                                cardsalesB0031SumList.push(cardsalesB0031Sum);
                            }
                        }
                        break;
                    }
                    break;
                // 뱅킹 - 개인
                case "bank":        
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 거래내역조회 (입출금통장)
                    case "B0002":
                        if(typeof outJson.outB0002[0].list === "object" 
                                && outJson.outB0002[0].list.length > 0) {
                            for(var j = 0; j < outJson.outB0002[0].list.length; j++) {
                                let bankB0002 = outJson.outB0002[0].list[j];
                                bankB0002.compCd = compCd;
                                bankB0002.reqCd = reqCd;
                                bankB0002.acctNo = outJson.outB0002[0].in.acctNo;
                                bankB0002.bankCd = outJson.outB0002[0].in.bankCd;
                                bankB0002List.push(bankB0002);
                            }
                        }
                        break;
                    }
                    break;
                // 뱅킹 - 기업
                case "cbk": 
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 거래내역조회 (입출금통장)
                    case "B0002":
                        if(typeof outJson.outB0002[0].list === "object" 
                                && outJson.outB0002[0].list.length > 0) {
                            for(var j = 0; j < outJson.outB0002[0].list.length; j++) {
                                let cbkB0002 = outJson.outB0002[0].list[j];
                                cbkB0002.compCd = compCd;
                                cbkB0002.reqCd = reqCd;
                                cbkB0002.acctNo = outJson.outB0002[0].in.acctNo;
                                cbkB0002.bankCd = outJson.outB0002[0].in.bankCd;
                                cbkB0002List.push(cbkB0002);
                            }
                        }
                        break;
                    }
                    break;
                // 신용카드 - 개인
                case "card":        
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 승인내역조회
                    case "C0005":
                        if(typeof outJson.outC0005 === "object" 
                                && typeof outJson.outC0005.list === "object"
                                && outJson.outC0005.list.length > 0) {
                            for(var j = 0; j < outJson.outC0005.list.length; j++) {
                                let cardC0005 = outJson.outC0005.list[j];
                                cardC0005.compCd = compCd;
                                cardC0005.reqCd = reqCd;
                                cardC0005List.push(cardC0005);
                            }
                        }
                        break;
                    }
                    break;
                // 신용카드 - 기업
                case "ccd":         
                    // - 서비스 구분
                    switch(outJson.svcCd) {
                    // - 승인내역조회
                    case "C0005":
                        if(typeof outJson.outC0005 === "object" 
                                && typeof outJson.outC0005.list === "object"
                                && outJson.outC0005.list.length > 0) {
                            for(var j = 0; j < outJson.outC0005.list.length; j++) {
                                let ccdC0005 = outJson.outC0005.list[j];
                                ccdC0005.compCd = compCd;
                                ccdC0005.reqCd = reqCd;
                                ccdC0005List.push(ccdC0005);
                            }
                        }
                        break;
                    }
                    break;
                }
            }
            
            let insertDataList = {
                hometaxZ300XList,
                hometaxZ300XItemList,
                hometaxZ4001List,
                hometaxZ4002List,
                hometaxZ4010List,
                hometaxZ4020List,
                bankB0002List,
                cbkB0002List,
                cardC0005List,
                ccdC0005List,
                cardsalesB0002List,
                cardsalesB0011DtlList,
                cardsalesB0011SumList,
                cardsalesB0021DtlList,
                cardsalesB0021SumList,
                cardsalesB0031DtlList,
                cardsalesB0031SumList,
            };
            
            resolve(insertDataList);
        });
    }
   
    
    function _insertDataList(data) {
        // 홈택스
        // - 전자세금계산서
        if(data.hometaxZ300XList.length > 0) {
            _insertDivData("hometaxZ300X", data.hometaxZ300XList);
        }
        // - 전자세금계산서 내역
        if(data.hometaxZ300XItemList.length > 0) {
            _insertDivData("hometaxZ300XItem", data.hometaxZ300XItemList);
        } 
        // - 현금영수증
        if(data.hometaxZ4001List.length > 0) {
            _insertDivData("hometaxZ4001", data.hometaxZ4001List);
        }
        if(data.hometaxZ4002List.length > 0) {
            _insertDivData("hometaxZ4002", data.hometaxZ4002List);
        } 
        // - 사업용 화물복지 신용카드
        if(data.hometaxZ4010List.length > 0) {
            // - PK 가 keyNo만 존재하는 경우 -> 데이터 삭제 후 insert 
            _deleteData("hometaxZ4010", data.hometaxZ4010List).then(function(result) {
                if(result === true) {
                    _insertDivData("hometaxZ4010", data.hometaxZ4010List);
                }
            });
        }
        if(data.hometaxZ4020List.length > 0) {
            // - PK 가 keyNo만 존재하는 경우 -> 데이터 삭제 후 insert
            _deleteData("hometaxZ4020", data.hometaxZ4020List).then(function(result) {
                if(result === true) {
                    _insertDivData("hometaxZ4020", data.hometaxZ4020List);
                }
            });
        } 
        
        // 여신
        // - 가맹점 수수료율/대금지급주기
        if(data.cardsalesB0002List.length > 0) {
            _insertDivData("cardsalesB0002", data.cardsalesB0002List);
        } 
        // - 기간별승인내역(일별)
        if(data.cardsalesB0011DtlList.length > 0) {
            _deleteData("cardsalesB0011Dtl", data.cardsalesB0011DtlList).then(function(result) {
                if(result === true) {
                    _insertDivData("cardsalesB0011Dtl", data.cardsalesB0011DtlList);
                }
            });
        } 
        if(data.cardsalesB0011SumList.length > 0) {
            _insertDivData("cardsalesB0011Sum", data.cardsalesB0011SumList);
        } 
        // - 기간별매입내역(일별)
        if(data.cardsalesB0021DtlList.length > 0) {
            _deleteData("cardsalesB0021Dtl", data.cardsalesB0021DtlList).then(function(result) {
                if(result === true) {
                    _insertDivData("cardsalesB0021Dtl", data.cardsalesB0021DtlList);
                }
            });
        }
        if(data.cardsalesB0021SumList.length > 0) {
            _insertDivData("cardsalesB0021Sum", data.cardsalesB0021SumList);
        } 
        // - 기간별입금내역
        if(data.cardsalesB0031DtlList.length > 0) {
            _deleteData("cardsalesB0031Dtl", data.cardsalesB0031DtlList).then(function(result) {
                if(result === true) {
                    _insertDivData("cardsalesB0031Dtl", data.cardsalesB0031DtlList);
                }
            });
        } 
        if(data.cardsalesB0031SumList.length > 0) {
            _insertDivData("cardsalesB0031Sum", data.cardsalesB0031SumList);
        }
        
        // 뱅킹
        // - 개인
        if(data.bankB0002List.length > 0) {
            _insertDivData("bankB0002", data.bankB0002List);
        } 
        // - 기업
        if(data.cbkB0002List.length > 0) {
            _insertDivData("cbkB0002", data.cbkB0002List);
        } 
        
        // 신용카드
        // - 개인
        if(data.cardC0005List.length > 0) {
            _insertDivData("cardC0005", data.cardC0005List);
        } 
        // - 기업
        if(data.ccdC0005List.length > 0) {
            _insertDivData("ccdC0005", data.ccdC0005List);
        }
        
    }
    
    function _insertDivData(dataType, dataList) {
        console.log(dataType);
        console.log(dataList);
        
        // 데이터 전송
        if(typeof dataType !== "undefined" && dataList.length !== 0) {
            // - 데이터 양이 분할전송량보다 큰 경우 나눠서 여러번 전송
            if(dataList.length > DATA_DIVISION_LEN) {
                let len = dataList.length;
                let cnt = Math.floor(len / DATA_DIVISION_LEN);
                let dataDivList = [];
                
                for(var i = 0; i <= cnt; i++) {
                    dataDivList.push(dataList.splice(0, DATA_DIVISION_LEN));
                }   
                
                for(var i = 0 ; i < dataDivList.length; i++) {
                    _insertData(dataType, dataDivList[i]);
                }
            } else {
                _insertData(dataType, dataList);
            }
        }
    }
    
    function _insertData(dataType, dataList) {
        $.ajax(getContextPath() + "/scrap/insertData", {
            type : "post",
            dataType : "json",
            data: {
                dataType: dataType,
                jsonDataList: JSON.stringify(dataList)
            }
        })
        .done(function(data) { // success 시
            console.log(data);
        })
        .fail(function(data) {
            alert("loadLogs failed");
        });
    }
    
    function _deleteData(dataType, dataList) {
        return new Promise(function(resolve) {
            if(typeof dataType !== "undefined" && dataList.length !== 0) {
                // 조회 데이터의 from ~ to 추출위해 처음 ~ 마지막 index 내용만 전송
                let deleteDataList = [
                    dataList[0], dataList[dataList.length - 1]
                ];
                
                $.ajax(getContextPath() + "/scrap/deleteData", {
                    type : "post",
                    dataType : "json",
                    data: {
                        dataType: dataType,
                        jsonDataList: JSON.stringify(deleteDataList)
                    }
                })
                .done(function(data) { // success 시
                    console.log(data);
                    resolve(true);
                })
                .fail(function(data) {
                    alert("loadLogs failed");
                });
            }
        });
    }
    
    return {
        init: init
    }
});