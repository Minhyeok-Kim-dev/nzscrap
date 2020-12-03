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
                   "orgCd": "hometax",
                   "svcCd": "Z4001",
                   "reqCd": "202011251320440000002096",
                   "proxy": "121.126.104.159:6766",
                   "signCert": "",
                   "signPri": "",
                   "signPw": "",
                   "userId": "barofood0614",
                   "userPw": "baro!14002",
                   "agentId": null,
                   "agentPw": null,
                   "bizNo": "1274227037",
                   "inqrDtStrt": "20201101",
                   "inqrDtEnd": "20201124",
                   "itrfCd": null,
                   "fromY": null,
                   "toY": null,
                   "fromQ": null,
                   "toQ": null,
                   "stlYr": null,
                   "dtCd": null,
                   "wrtArr": null,
                   "supByr": null,
                   "taxGb": null,
                   "wrtYr": null,
                   "wrtQt": null
               }
               */
               /*
	           {
	               "appCd": "bizbooks",
	               "orgCd": "hometax",
	               "svcCd": "Z0000",
	               "reqCd": "202011251153040000002508",
	               "proxy": "115.144.98.198:6733",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "8495volo",
	               "userId": "",
	               "userPw": "",
	               "agentId": "",
	               "agentPw": "",
	               "bizNo": "1538500145",
	               "inqrDtStrt": "20201101",
	               "inqrDtEnd": "20201124",
	               "itrfCd": null,
	               "fromY": null,
	               "toY": null,
	               "fromQ": null,
	               "toQ": null,
	               "stlYr": null,
	               "dtCd": null,
	               "wrtArr": null,
	               "supByr": null,
	               "taxGb": null,
	               "wrtYr": null,
	               "wrtQt": null
	           }
	           */
	           {
	               "appCd": "bizbooks",
	               "orgCd": "hometax",
	               "svcCd": "Z3001",
	               "reqCd": "202011252011590000002500",
	               "proxy": "49.254.124.40:8344",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "8495volo",
	               "userId": "",
	               "userPw": "",
	               "agentId": "",
	               "agentPw": "",
	               "bizNo": "1538500145",
	               "inqrDtStrt": "20201125",
	               "inqrDtEnd": "20201125",
	               "itrfCd": null,
	               "fromY": null,
	               "toY": null,
	               "fromQ": null,
	               "toQ": null,
	               "stlYr": null,
	               "dtCd": null,
	               "wrtArr": null,
	               "supByr": null,
	               "taxGb": null,
	               "wrtYr": null,
	               "wrtQt": null
	           }

	           
               let jsonList = [];
               jsonList.push(jsonData);
               
               let url = "https://183.111.102.219:9402/rest/ext";
               
               $.ajax(url, {
                   type : "post",
                   //dataType : "json",
                   data: {
                       inJsonList: JSON.stringify(["{\"svcOption\":\"storeDetail\",\"appCd\":\"bizbooks\",\"orgCd\":\"ccd\",\"svcCd\":\"C0005\",\"reqCd\":\"202012021544310000000022\",\"proxy\":\"203.109.3.51:5753\",\"loginMethod\":\"CERT\",\"signCert\":\"-----BEGIN CERTIFICATE-----\\nMIIF0jCCBLqgAwIBAgIEBT/qUjANBgkqhkiG9w0BAQsFADBKMQswCQYDVQQGEwJL\\nUjENMAsGA1UECgwES0lDQTEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRUwEwYDVQQD\\nDAxzaWduR0FURSBDQTUwHhcNMjAwMzMxMDcxODM3WhcNMjEwNDExMTQ1OTU5WjCB\\nkTELMAkGA1UEBhMCS1IxDTALBgNVBAoMBEtJQ0ExEzARBgNVBAsMCmxpY2Vuc2Vk\\nQ0ExFTATBgNVBAsMDOuTseuhneq4sOq0gDEVMBMGA1UECwwM6riw7JeF7J2A7ZaJ\\nMREwDwYDVQQLDAhSQeyEvO2EsDEdMBsGA1UEAwwUKOyjvCnslaDrk5zsupDsiqTt\\njIUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC8zP6nwDdDG1GikLeG\\n7/a1dEYqZ2aVghVNffiTF5Eghi+aGhY6qeoQO0j6qsutpYU1pmB4aOJpGC2Dptz6\\nxjCTCmLqIrgQ7x2vlmAErnV5HfnmWPCBTufueuYUDsxiiPbEVZfXgItM3jZpC9ZS\\n6K/MkeRObXW10hTCIj3nECfYTk63Xflrg2fMZYRFFANjZk9jHhi0jUeQbziLVthv\\n5JE6bo+RGiiRalGEAL3gOh4W4mjpHZ0451z54Fe52pNSAe0Jl3aLVajZ/5yPRy4l\\nFa+1E0wkPkT+FeYZ4DNVs71Gveq6ZsYQBHEDahBFjFsBJtmqmr/poVypd93PIJht\\nIfktAgMBAAGjggJ2MIICcjCBjwYDVR0jBIGHMIGEgBTYvjrsRZnFnuOc6oEf0h0S\\nsDY+iKFopGYwZDELMAkGA1UEBhMCS1IxDTALBgNVBAoMBEtJU0ExLjAsBgNVBAsM\\nJUtvcmVhIENlcnRpZmljYXRpb24gQXV0aG9yaXR5IENlbnRyYWwxFjAUBgNVBAMM\\nDUtJU0EgUm9vdENBIDSCAhAdMB0GA1UdDgQWBBSB6XcXBwqHujB8wwzbXg2t9fGt\\n2DAOBgNVHQ8BAf8EBAMCBsAwdQYDVR0gBG4wbDBqBgoqgxqMmkQFAgEBMFwwLAYI\\nKwYBBQUHAgEWIGh0dHA6Ly93d3cuc2lnbmdhdGUuY29tL2Nwcy5odG1sMCwGCCsG\\nAQUFBwICMCAeHsd0ACDHeMmdwRyylAAgrPXHeMd4yZ3BHMeFssiy5DCBkAYDVR0R\\nBIGIMIGFgRlhZGNhc3RpbmdAYWRjYXN0aW5nLmNvLmtyoGgGCSqDGoyaRAoBAaBb\\nMFkMFCjso7wp7JWg65Oc7LqQ7Iqk7YyFMEEwPwYKKoMajJpECgEBATAxMAsGCWCG\\nSAFlAwQCAaAiBCA/CasZFXNwdpZ5+8kzt+Vm1T/ySkL64IBvXeQ0NMndGDBfBgNV\\nHR8EWDBWMFSgUqBQhk5sZGFwOi8vbGRhcC5zaWduZ2F0ZS5jb206Mzg5L291PWRw\\nN3AyMTA3NSxvdT1jcmxkcCxvdT1BY2NyZWRpdGVkQ0Esbz1LSUNBLGM9S1IwRAYI\\nKwYBBQUHAQEEODA2MDQGCCsGAQUFBzABhihodHRwOi8vb2NzcC5zaWduZ2F0ZS5j\\nb206OTAyMC9PQ1NQU2VydmVyMA0GCSqGSIb3DQEBCwUAA4IBAQBHtcxywUP4C0gs\\nVY2ErFKQgGZuyQad3cetqLzvZOMlRBLWJUxeEqWIh/gPMotMIuL5TVc5aFIFODdo\\nrZeJpOzEz8NWJ1TmHeblOncjObBiUiW9Il6JgnAG4cTrmBwWsIPpulMmfnsYSk+0\\nPQeWVHwlbN+SRrXy5BcmyU0Vaa5QuaSu7JbRL+kbX8aBN47Z443ZYqs94zZRTpFM\\neY8io2DcX1th98g71jMuN0JcdQjL7wU6S9cpGNFDtb8jGqIspxXYIBf/ZC0d4U/w\\n1t8NAGqnKXHlVN2XT87MwwgSyB8ooRucaiIoyMVfMQ/SeRJzR/HcR+QhKEwo29q4\\nFRltuxEK\\n-----END CERTIFICATE-----\",\"signPri\":\"-----BEGIN ENCRYPTED PRIVATE KEY-----\\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQI9V6vXj0XEX8CAggA\\nMBwGCCqDGoyaRAEEBBAHj41lICWs+xJh8S1nER9bBIIE8Abg2oALuohEJaFWiPhv\\ntlJKowwEFALVy5uWnuRse5jdamAQWnAJMhMYBO/FFAKSzextAJBppFhvVaDucuNX\\nwgW+Rsjdwv5UL/y7xywD4Um70gKlGqCgpY9SyG0102my/kJxIbLvOZrqU/jATmik\\n1kINwYWKG4vPr3SOfJKQS5Wkf1QtHfLjUkgW2qj/cSi7fOcueJzdTnyEOaJl2iVP\\nVs0EFNkM7+xXsk48P2Zwyxiq6nFaNYmtTUjRGUDKDsEUfD948TrrZgl+FHdVH9ri\\nq8eyhj5BAeFJM601xDLHofOCFqyXtSGcmj2nVi9dZYzCzA1WVNx5S3nCkKI6cCBf\\nXRUzfONJYakHveIelzT6d+D4gGtFk6PbzT25iwjJ6jcLHWJ687qj39bGqOgPu3yy\\nhbgTsI8PAbve2w95YyE+4JJeFoIJDeyg9wC+m7/hTpyizjkG46M9AiAEwAyos20R\\nwY5BxXLub3mAsPB796g7r3zYW34O6p1QW3KZ3KclKvjIKYKLE1Cmb12edwCV1F66\\nNxlQ45ZPS0iB8N7zgZ1kzv54E6sfeo96wcBz4+dU6MfPitdOaWYg+EmDSA9vedQP\\nFT2jSWGQo5y+tQxFK6HY6VXAZqosfQgRurtSHWQ4rylAGwWqNAC1LwerO1+yMj7Q\\noZV884SWLNkDPuupzARg7Uw7HQgQiobKjjDFqljlHqabIeDt/PwU8kof5SjPTX0N\\nsSZEYlS0P2eJgP4m/S9EJYR3usvAGNtNzZ1FyP+5H4YA1Zb5peGmnBvhilkLySEi\\nwDtYyqcKWV/oiclTU7ol8aaWdbPZkmhGS3oxVfuEfDiTDTv5GHpTbdSu8A63zcjW\\nvheUXjwMRA1+pTH+GdqtyjrsNrAHrfZqVJ44BbL55fZzhBU05SuikOVfWtymTp9Z\\n7J28i1gYx44C9hQ9kKzXL4A5hRl1QmugF/OWvIT7Rw8O2r+1nY/idcoB8i+K01Ak\\nhQk+BV4bdpcnpyhOThBWbLI82/Ud/EN1qvRjOZ7g/jJ5Z7uixjCXsoQ3nNr6hTJ0\\nmTQbOzGiV7PJHzhmGWubK9XQH0N9/l0yaaVnUSUnOsPgQ5zaqDYt1MU5CQ9zgyGI\\nwdU+49GtRE3RHLFW965MxU2M8Lr1WjbW0YZCv3G+kmoELflDytzlHE4ZqHC8YFfQ\\no4glnAl09b8PjWit9a2J52JwSUOhumVZ+EVZSlkYpgVPwMPPscDfSt2nqrOkYbB3\\nE2cAyPNxL5ELOc0yQa1RWuDZRwhZyyN11SSlz7qDHWt1i4AZ6CezXxhNHA6uicRc\\nqPyX4h3OCTUy2Abw1bQjKtS2tovIT+kyE8D5bMtu8Lno6qJfVjEri7SUrVlqSQs+\\nY2tk3WhUfLIOcCnBI64RBQKxAsEHXw4CZthJ4c1E+iNvdQPgovr//4x5IxdHXJ9A\\n/eL8g8ivrKRjg40U+C0ROcMb47wKz1zwG0RUGx1yGfy3j14n/kcvRKLHwzeWXRf7\\nCJhraEcV3ZkvkCsRnMELIxO4oglmjBqADfzKao7ZKPFgYG+xqBBu+pYN46JY29vA\\nDVHMa+G+H6d/B2yFn+2Uig6l9/feMei7pgtZP5uhEEXpIQUIFqmgPMoUTZEXuqvS\\nwmdDh807xfddqrD6mtNH9dD9noujRyU0rKbREknCJdF9090I/SY4VwdGvOe32Ltg\\ngMU=\\n-----END ENCRYPTED PRIVATE KEY-----\",\"signPw\":\"iwsw0912@#\",\"userId\":\"\",\"userPw\":\"\",\"cardNo\":\"6264150587653974\",\"sdate\":\"20201102\",\"edate\":\"20201201\",\"cardCd\":\"010\"}"])
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
