<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
	<title>Home</title>
    <script src="<c:url value="/resources/js/jquery/jquery-3.3.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/require/require.js"/>"></script>
    <script src="<c:url value="/resources/js/require/main.js"/>" ></script>
    
	<script>
		require(["scrap"], function(scrap) {
		    scrap.init();
		});		
		
	    $(document).ready(function(){

	        // 1. 비즈북스 서버에서 스케쥴리스트 가져옴 (암호화되어있음)
            // 2. 복호화
            // 3. 메시지큐저장
            // 4. 전송
            /*
	        let jsonData = {
	           appCd: "bizbooks",
	           orgCd: "hometax",
	           svcCd: "Z0001",
	           bizNo: "6528800075"
	        };
	        */
	        
	        /*
	        let jsonData = {
                 appCd: "bizbooks",
                 orgCd: "hometax",
                 svcCd: "Z3002",
                 loginMethod: "CERT",
                 signCert: "\\\\183.111.102.219\\Cert\\3mf988kp5cuhPf093c71r8iQTFXX10ZuQ9u9SVgJ1\\signCert.der",
                 signPri: "\\\\183.111.102.219\\Cert\\Bj8R8Z8vvAc70v33A8IzEAzHp5hw6YN6kKSkL1v8C3u\\signPri.key",
                 //signCert: "C:\\cert\\volo\\signCert.der",
                 //signPri: "C:\\cert\\volo\\signPri.key",
                 signPw: "8495volo",
                 bizNo: "1538500145",
                 inqrDtStrt: "20190101",
                 inqrDtEnd: "20190131"
	        };

            let jsonList = [];
	        jsonList.push(jsonData);

	        $("#btnTest").on("click", function() {
	            $.ajax(url, {
	                type : "post",
	                //dataType : "json",
	                data: {
	                    inJsonList: JSON.stringify(jsonList),
	                    reqCd: "t0100"
	                }
	            })
	            .done(function(data) { // success 시
	                console.log(data);
	            })
	            .fail(function(data) {
	                alert("loadLogs failed");
	            });
	        });
	        */
	        
	        $("#btnSingleTest").on("click", function() {
	           //let jsonData ={"appCd":"bizbooks","orgCd":"hometax","svcCd":"Z3001","reqCd":"202009091118070000000198","signPri":"\\\\183.111.102.219\\Cert\\x96yw39274gS1eWQybjb9P2TcKeKpzoMFFG5d6P2fk\\signPri.key","signPw":"8495volo","agentId":null,"agentPw":null,"bizNo":"1538500145","inqrDtStrt":"20200101","inqrDtEnd":"20200909","itrfCd":null,"fromY":null,"toY":null,"fromQ":null,"toQ":null,"stlYr":null}
	           
	           let jsonData =
	           /*
	           {
	               "appCd": "bizbooks",
	               "orgCd": "cardsales",
	               "svcCd": "B0002",
	               "reqCd": "202011131344280000000244",
	               "userId": "jong6153",
	               "userPw": "jong@#5474",
	               "fromDate": "20201101",
	               "toDate": "20201112",
	               "proxy": "49.254.53.58:7169"
	           }
	           */
	           /*
	           {
	               "appCd": "bizbooks",
	               "orgCd": "cbk",
	               "svcCd": "B0002",
	               "reqCd": "202011201157110000001295",
	               "proxy": "121.126.251.138:7433",
	               "loginMethod": "CERT",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIFjjCCBHagAwIBAgIECC52sDANBgkqhkiG9w0BAQsFADBQMQswCQYDVQQGEwJL\nUjESMBAGA1UECgwJU2lnbktvcmVhMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFjAU\nBgNVBAMMDVNpZ25Lb3JlYSBDQTMwHhcNMjAxMDIxMDY0OTEzWhcNMjExMDI2MTQ1\nOTU5WjBzMQswCQYDVQQGEwJLUjESMBAGA1UECgwJU2lnbktvcmVhMQ8wDQYDVQQL\nDAbspp3qtowxDzANBgNVBAsMBuuMgO2IrDEMMAoGA1UECwwDSFRTMSAwHgYDVQQD\nDBco7KO8KeumrOyhsOydtOyKpO2GteyLoDCCASIwDQYJKoZIhvcNAQEBBQADggEP\nADCCAQoCggEBAJoYiznuVxe6y3x2GgD/hDvTzJPDUCrqUWLmnsJo4caN8lHmvfSU\ngviJzS4R1+tI9sn8dnon2E7vi1NgLz0dWp2WIK7El//TVNB307h6bl4rFV72pjX+\nRXS+z+BwdQyMTGy+SJ/zaa/dzBn61H+Bwwtw5Xn+SmSRNEarHVIYKLVPKoHFhypu\nTx5cr+KTXckTZU428OA7fa3gRyvPzfWUdxP31nxdW29KaqDRWNtJQ2bZHawbveuN\ntU/GUrv784KCX35kKufk1g7Hau4DogjTAtPILV14CU11gUEIFk/f36nihZgvBIMW\nO0pl/gf4WTJfktdgDJB+ksKENiiuhNUAkgECAwEAAaOCAkswggJHMIGPBgNVHSME\ngYcwgYSAFARURbDeEsQnnKBPAmmL1VsUFGMHoWikZjBkMQswCQYDVQQGEwJLUjEN\nMAsGA1UECgwES0lTQTEuMCwGA1UECwwlS29yZWEgQ2VydGlmaWNhdGlvbiBBdXRo\nb3JpdHkgQ2VudHJhbDEWMBQGA1UEAwwNS0lTQSBSb290Q0EgNIICECAwHQYDVR0O\nBBYEFEEsy+xzxmyZ2BUjtZeFuHmMvzHhMA4GA1UdDwEB/wQEAwIGwDB5BgNVHSAB\nAf8EbzBtMGsGCiqDGoyaRAUBAQcwXTAtBggrBgEFBQcCARYhaHR0cDovL3d3dy5z\naWdua29yZWEuY29tL2Nwcy5odG1sMCwGCCsGAQUFBwICMCAeHsd0ACDHeMmdwRyy\nlAAgrPXHeMd4yZ3BHMeFssiy5DB2BgNVHREEbzBtoGsGCSqDGoyaRAoBAaBeMFwM\nFyjso7wp66as7KGw7J207Iqk7Ya17IugMEEwPwYKKoMajJpECgEBATAxMAsGCWCG\nSAFlAwQCAaAiBCBVArlJJTX2FBNAX5Vjx2+LxSb25peOi2Rwibk7T6f6gTBaBgNV\nHR8EUzBRME+gTaBLhklsZGFwOi8vZGlyLnNpZ25rb3JlYS5jb206Mzg5L291PWRw\nNXA4MzYyLG91PUFjY3JlZGl0ZWRDQSxvPVNpZ25Lb3JlYSxjPUtSMDUGCCsGAQUF\nBwEBBCkwJzAlBggrBgEFBQcwAYYZaHR0cDovL29jc3Auc2lnbmtvcmVhLmNvbTAN\nBgkqhkiG9w0BAQsFAAOCAQEAQjY/2w/gYHc+ZgNAbIIM8MMmqJxAeCicf+1BOUTL\nt34IopvCSJNtOQSgfIMZrrZL59Js5ETMj/o/G9Th2JL8/pRQS/+U0bB2b1FQyk0q\nihop3f9Mp0ErSkGM0ekdYMsqXLBXBaF3QGHQip5jJzkUGj9GgMZIo+SqZOMBTEfC\nlmpmYqfWLngu5qQ6VYeZQxsQ455soIdIUfpN1JTSZB86ZrbxLLDCL3HrEz49YgkM\n1QVuVsm7l6OD10CFjFIpl3euBS3MXjpJ+iTWB3HH59JcI9oaYywyXPBrR24j4N1T\n5y23mMY5QlLiET/qR/ShwdX1T/KPCXIvWswCCvJg7cnCug==\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIYfVt9M5hsmgCAggA\nMBwGCCqDGoyaRAEEBBCR7IL9do+o3QnJ9edwT8oaBIIE8ArPWHdDZ5mCVX+pdhbg\nP65ssb81pQSDACMApiclsYJ3ePT3HDM7U0hJl2/bEB48PMRqMOeTiZL9UXVc6YSK\ncZ1jTWXXnKwcVlnMpaxwh6KqEnyYTDW3batbKoRK5HgCTrVlZVaVbo0dFq8pFU/I\nhClQ7QScUNocJmTqUFrPbQkAENi8xJthotsstcc0KxA53lhlHfz0WeSw6UG5bXgv\n3TaGevqdaYwx+TDywzgqjjC+TY9n8Xvh4OsiGba3/MvxTVtcVjj9Ik3Xol9ZHBxA\neiIWustSgPQ5gOxw4vUaVVpB/OXKBkquQ9lqxzW2TaiGLBiOhNJ6OzpnidMn9var\nWhb3yP/PIz3bl60XY0d50ThnsvI3ticaHQp0HfsJWFlMRFc176B4L3sNUhM766GO\nEuKFpzf0gEL7tV8MsQAvFL/zJF30bbJ+ix8kizvFVfSWOFpsobvm+w7eEq7+3dwC\nzwn2LarGQMxfE016YYXowrNmQOisGB/SKRipw6SrpZMIl62oCMnz4xOWCiOQni6j\n1YItWaSBDyz/pE7aJY0ssKLvzHz2NiTmZ2VTZkIzvmA+Sc9OKlktMI5x5CpAtetz\n7RCs5yyloJyS13XWoDcDx6plVzB6IZPmR14jzFWLr740pfBt866pRR09zYXPsf3f\nZos/AJYTly3DeVlYyZv9E3lj7icxAp1LxoLUZzMNs3SwUN/k+U32UjcavdFIw8AH\nG1ZbBpQxGR71zqwlWMf0TU5p/857MbW48Qie04Q/Xet9idVkgv1VVmEi8tlOV2Fw\nZRCxr0E3T1lAk6BPCi68C8lqWMpQSRznr7f3WcGBxwjErSDybosqshCudh1srk5X\n2otpri+Dig9nGtEShLc71njkHXZhEVMutu8RBU2ONUyoSNUQrYn6vIiceoA3aHnx\nLYtovcuY/wrh6Hy28aQXM5vqIoSgUYIANQnStJv7K/8TUJpgT8m65RUs2iIalhfU\nbzWbpkyY0VaA8kkt4JfSWUqt/I/xAy8gdtYP+vIvlH1uhWA8fHGFZAahb2TpJxLq\nsvMPyWq4mYvwKBEMOe/sq7AOraLi6PiwIvsClGWGwwsCkKE1FJ+jlqsbURU4zAVC\n0cjHbEideqoHZzDfXh60u2sZcIzSZk8C3q/7Mg0rxcPIOSEgtnafU5s7Ja6FZXhY\ndlOGhBzMPfouRHXanGu+4NUGC/QHYDFOaoQkWAEOJu3mJ1jz5z6piQij7Bu+i2qW\nlGuPdq3Rvewxhfb9+nBdcJ3WRR5Omvr4X5LZycIahjm9fsRACzioneWEksACtU23\n1jSiJaQvROPfZKSRS7HK05elm7k146xgRrNCSQNSgHeCWi2Wy57fsG9mULBueBs5\n8gqjowKIaB9+dlj/lQ8DR2BejEuJ8IRkmWXy6f2tIjWjNP3dVRP7e1czg4mm1m11\nBode8JkES2+h9PfIicsHYzyiZvtSNczl2/rlOBdXFGV2TpIBZvFFKXHPYbDZDiOH\nKcEWaSJ3fp2XZDmt/Qa3UEOHFRGgToVe6WTJbenRClv4tSGpXYadjurat5yj6qVu\n1shfSFdoYdb9TJNqKju77f/V7XoaMbNnjBibP2aRizUM6S6IuVobDwPYkzYnxYl1\n3fWkmGVZlJlT+Y9RNlLzCNXRRzG4fCECQbmOcx4puSHGSZKB8oRUt8fEqgakfm/Y\n+x4=\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "++doyo1340",
	               "userId": "",
	               "userPw": "",
	               "acctNo": "39591014526113",
	               "acctPw": null,
	               "bizNo": null,
	               "sdate": "20200101",
	               "edate": "20201119",
	               "curCd": "KRW",
	               "bankCd": "081"
	           }
	           */
	           {
	               "appCd": "bizbooks",
	               "orgCd": "cbk",
	               "svcCd": "B0002",
	               "reqCd": "202011201310200000001292",
	               "proxy": "115.144.38.126:6222",
	               "loginMethod": "CERT",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIFjjCCBHagAwIBAgIECC52sDANBgkqhkiG9w0BAQsFADBQMQswCQYDVQQGEwJL\nUjESMBAGA1UECgwJU2lnbktvcmVhMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFjAU\nBgNVBAMMDVNpZ25Lb3JlYSBDQTMwHhcNMjAxMDIxMDY0OTEzWhcNMjExMDI2MTQ1\nOTU5WjBzMQswCQYDVQQGEwJLUjESMBAGA1UECgwJU2lnbktvcmVhMQ8wDQYDVQQL\nDAbspp3qtowxDzANBgNVBAsMBuuMgO2IrDEMMAoGA1UECwwDSFRTMSAwHgYDVQQD\nDBco7KO8KeumrOyhsOydtOyKpO2GteyLoDCCASIwDQYJKoZIhvcNAQEBBQADggEP\nADCCAQoCggEBAJoYiznuVxe6y3x2GgD/hDvTzJPDUCrqUWLmnsJo4caN8lHmvfSU\ngviJzS4R1+tI9sn8dnon2E7vi1NgLz0dWp2WIK7El//TVNB307h6bl4rFV72pjX+\nRXS+z+BwdQyMTGy+SJ/zaa/dzBn61H+Bwwtw5Xn+SmSRNEarHVIYKLVPKoHFhypu\nTx5cr+KTXckTZU428OA7fa3gRyvPzfWUdxP31nxdW29KaqDRWNtJQ2bZHawbveuN\ntU/GUrv784KCX35kKufk1g7Hau4DogjTAtPILV14CU11gUEIFk/f36nihZgvBIMW\nO0pl/gf4WTJfktdgDJB+ksKENiiuhNUAkgECAwEAAaOCAkswggJHMIGPBgNVHSME\ngYcwgYSAFARURbDeEsQnnKBPAmmL1VsUFGMHoWikZjBkMQswCQYDVQQGEwJLUjEN\nMAsGA1UECgwES0lTQTEuMCwGA1UECwwlS29yZWEgQ2VydGlmaWNhdGlvbiBBdXRo\nb3JpdHkgQ2VudHJhbDEWMBQGA1UEAwwNS0lTQSBSb290Q0EgNIICECAwHQYDVR0O\nBBYEFEEsy+xzxmyZ2BUjtZeFuHmMvzHhMA4GA1UdDwEB/wQEAwIGwDB5BgNVHSAB\nAf8EbzBtMGsGCiqDGoyaRAUBAQcwXTAtBggrBgEFBQcCARYhaHR0cDovL3d3dy5z\naWdua29yZWEuY29tL2Nwcy5odG1sMCwGCCsGAQUFBwICMCAeHsd0ACDHeMmdwRyy\nlAAgrPXHeMd4yZ3BHMeFssiy5DB2BgNVHREEbzBtoGsGCSqDGoyaRAoBAaBeMFwM\nFyjso7wp66as7KGw7J207Iqk7Ya17IugMEEwPwYKKoMajJpECgEBATAxMAsGCWCG\nSAFlAwQCAaAiBCBVArlJJTX2FBNAX5Vjx2+LxSb25peOi2Rwibk7T6f6gTBaBgNV\nHR8EUzBRME+gTaBLhklsZGFwOi8vZGlyLnNpZ25rb3JlYS5jb206Mzg5L291PWRw\nNXA4MzYyLG91PUFjY3JlZGl0ZWRDQSxvPVNpZ25Lb3JlYSxjPUtSMDUGCCsGAQUF\nBwEBBCkwJzAlBggrBgEFBQcwAYYZaHR0cDovL29jc3Auc2lnbmtvcmVhLmNvbTAN\nBgkqhkiG9w0BAQsFAAOCAQEAQjY/2w/gYHc+ZgNAbIIM8MMmqJxAeCicf+1BOUTL\nt34IopvCSJNtOQSgfIMZrrZL59Js5ETMj/o/G9Th2JL8/pRQS/+U0bB2b1FQyk0q\nihop3f9Mp0ErSkGM0ekdYMsqXLBXBaF3QGHQip5jJzkUGj9GgMZIo+SqZOMBTEfC\nlmpmYqfWLngu5qQ6VYeZQxsQ455soIdIUfpN1JTSZB86ZrbxLLDCL3HrEz49YgkM\n1QVuVsm7l6OD10CFjFIpl3euBS3MXjpJ+iTWB3HH59JcI9oaYywyXPBrR24j4N1T\n5y23mMY5QlLiET/qR/ShwdX1T/KPCXIvWswCCvJg7cnCug==\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIYfVt9M5hsmgCAggA\nMBwGCCqDGoyaRAEEBBCR7IL9do+o3QnJ9edwT8oaBIIE8ArPWHdDZ5mCVX+pdhbg\nP65ssb81pQSDACMApiclsYJ3ePT3HDM7U0hJl2/bEB48PMRqMOeTiZL9UXVc6YSK\ncZ1jTWXXnKwcVlnMpaxwh6KqEnyYTDW3batbKoRK5HgCTrVlZVaVbo0dFq8pFU/I\nhClQ7QScUNocJmTqUFrPbQkAENi8xJthotsstcc0KxA53lhlHfz0WeSw6UG5bXgv\n3TaGevqdaYwx+TDywzgqjjC+TY9n8Xvh4OsiGba3/MvxTVtcVjj9Ik3Xol9ZHBxA\neiIWustSgPQ5gOxw4vUaVVpB/OXKBkquQ9lqxzW2TaiGLBiOhNJ6OzpnidMn9var\nWhb3yP/PIz3bl60XY0d50ThnsvI3ticaHQp0HfsJWFlMRFc176B4L3sNUhM766GO\nEuKFpzf0gEL7tV8MsQAvFL/zJF30bbJ+ix8kizvFVfSWOFpsobvm+w7eEq7+3dwC\nzwn2LarGQMxfE016YYXowrNmQOisGB/SKRipw6SrpZMIl62oCMnz4xOWCiOQni6j\n1YItWaSBDyz/pE7aJY0ssKLvzHz2NiTmZ2VTZkIzvmA+Sc9OKlktMI5x5CpAtetz\n7RCs5yyloJyS13XWoDcDx6plVzB6IZPmR14jzFWLr740pfBt866pRR09zYXPsf3f\nZos/AJYTly3DeVlYyZv9E3lj7icxAp1LxoLUZzMNs3SwUN/k+U32UjcavdFIw8AH\nG1ZbBpQxGR71zqwlWMf0TU5p/857MbW48Qie04Q/Xet9idVkgv1VVmEi8tlOV2Fw\nZRCxr0E3T1lAk6BPCi68C8lqWMpQSRznr7f3WcGBxwjErSDybosqshCudh1srk5X\n2otpri+Dig9nGtEShLc71njkHXZhEVMutu8RBU2ONUyoSNUQrYn6vIiceoA3aHnx\nLYtovcuY/wrh6Hy28aQXM5vqIoSgUYIANQnStJv7K/8TUJpgT8m65RUs2iIalhfU\nbzWbpkyY0VaA8kkt4JfSWUqt/I/xAy8gdtYP+vIvlH1uhWA8fHGFZAahb2TpJxLq\nsvMPyWq4mYvwKBEMOe/sq7AOraLi6PiwIvsClGWGwwsCkKE1FJ+jlqsbURU4zAVC\n0cjHbEideqoHZzDfXh60u2sZcIzSZk8C3q/7Mg0rxcPIOSEgtnafU5s7Ja6FZXhY\ndlOGhBzMPfouRHXanGu+4NUGC/QHYDFOaoQkWAEOJu3mJ1jz5z6piQij7Bu+i2qW\nlGuPdq3Rvewxhfb9+nBdcJ3WRR5Omvr4X5LZycIahjm9fsRACzioneWEksACtU23\n1jSiJaQvROPfZKSRS7HK05elm7k146xgRrNCSQNSgHeCWi2Wy57fsG9mULBueBs5\n8gqjowKIaB9+dlj/lQ8DR2BejEuJ8IRkmWXy6f2tIjWjNP3dVRP7e1czg4mm1m11\nBode8JkES2+h9PfIicsHYzyiZvtSNczl2/rlOBdXFGV2TpIBZvFFKXHPYbDZDiOH\nKcEWaSJ3fp2XZDmt/Qa3UEOHFRGgToVe6WTJbenRClv4tSGpXYadjurat5yj6qVu\n1shfSFdoYdb9TJNqKju77f/V7XoaMbNnjBibP2aRizUM6S6IuVobDwPYkzYnxYl1\n3fWkmGVZlJlT+Y9RNlLzCNXRRzG4fCECQbmOcx4puSHGSZKB8oRUt8fEqgakfm/Y\n+x4=\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "++doyo1340",
	               "userId": "",
	               "userPw": "",
	               "acctNo": "39591014526113", 
	               "acctPw": null,
	               "bizNo": null,
	               "sdate": "20201101",
	               "edate": "20201119",
	               "curCd": "KRW",
	               "bankCd": "081"
	           }

	           
               let jsonList = [];
               jsonList.push(jsonData);
               
               let url = "https://183.111.102.219:9402/rest/ext";
               
               $.ajax(url, {
                   type : "post",
                   //dataType : "json",
                   data: {
                       inJsonList: JSON.stringify(jsonList),
                   }
               })
               .done(function(data) { // success 시
                   console.log(data);
                   console.log(JSON.stringify(data.outJsonList[0].list));
               })
               .fail(function(data) {
                   alert("loadLogs failed");
               });
	        });
	        
	        $("#btnFtpConnect").on("click", function() {
	            let url = getContextPath() + "/sftp/connect";
	            $.ajax(url, {
                    type : "get"
                })
                .done(function(data) { // success 시
                    console.log(data);
                })
                .fail(function(data) {
                    alert("loadLogs failed");
                });
	        });
	        
	        $("#btnFtpUpload").on("click", function() {
	            let url = getContextPath() + "/sftp/upload";
                $.ajax(url, {
                    type : "get"
                })
                .done(function(data) { // success 시
                    console.log(data);
                })
                .fail(function(data) {
                    alert("loadLogs failed");
                });
            });
            
			$("#btnFtpDisconnect").on("click", function() {
			    let url = getContextPath() + "/sftp/disConnect";
                $.ajax(url, {
                    type : "get"
                })
                .done(function(data) { // success 시
                    console.log(data);
                })
                .fail(function(data) {
                    alert("loadLogs failed");
                });
			});
	    });
	    
	    function getContextPath() { 
	        return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	    }
    </script>
</head>
<body>
    <input type="button" id="btnSingleTest" value="단일테스트" />
    <input type="button" id="btnSend" value="데이터 전송" />
    <input type="button" id="btnScrap" value="스크랩" />
    <hr />
    <h3>SFTP</h3>
    <input type="button" id="btnFtpConnect" value="Connect" />
    <input type="button" id="btnFtpUpload" value="Upload" />
    <input type="button" id="btnFtpDisconnect" value="Disconnect" />
    <hr />
</body>
</html>
