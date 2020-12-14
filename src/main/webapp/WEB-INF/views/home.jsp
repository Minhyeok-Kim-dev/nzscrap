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
	           /*	           
	           let jsonData = {
                   appCd: "bizbooks",
                   orgCd: "hometax",
                   svcCd: "Z0001",
                   bizNo: "6528800075"
               };
	           */
	           let jsonData = {
	               "exeTimeout":"300",
                   "htmlLogMode":"Y",
                   "agentId": "",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
                   "signPw": "8495volo",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
                   "userPw": "",
                   "bizNo": "1538500145",
                   "itrfCd": null,
                   "wrtArr": null,
                   "appCd": "bizbooks",
                   "reqCd": "",
                   "svcCd": "Z3001",
                   "supByr": null,
                   "userId": "",
                   "agentPw": "",
                   "stlYr": null,
                   "wrtYr": null,
                   "proxy": "115.144.31.6:6716",
                   "orgCd": "hometax",
                   "fromQ": null,
                   "taxGb": null,
                   "toQ": null,
                   "inqrDtStrt": "20201210",
                   "inqrDtEnd": "20201210",
                   "fromY": null,
                   "dtCd": null,
                   "wrtQt": null,
                   "toY": null
               };
	           /*
	           let jsonData = {
                   "appCd": "bizbooks",
                   "orgCd": "hometax",
                   "svcCd": "Z5002",
                   "reqCd": "202011110944580000000654",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIFyTCCBLGgAwIBAgIEJlGyLjANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0xOTEyMTUxNTAwMDBaFw0yMDEyMTkx\nNDU5NTlaMIGUMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjETMBEGA1UE\nCwwKeFVzZTRFc2VybzEMMAoGA1UECwwDSE5CMREwDwYDVQQLDAhGSU5FIFRBWDE9\nMDsGA1UEAww07KGw7J2A7IS466y067KV7J24KEZJTkUgVEFYKTAwODE2ODMyMDA5\nMTIwOTE4MTAwMTIwNjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALmN\nRiE4VJSiaLuhPaaiWwpkHg5bS2FRbejq5eXe4YCzMqYwK2kRgocH2inCr44F8BYl\n1bMlZIfOg4N8P08ht2+VXOs6TQ2mokFG2ch65lZA22oD07qgTwiQiquKVuYsL0ef\nw6EJFivth8EFHiKW43yZHv2oYF0vdLKWX5wWoEnnca+8DwKhCcuWf7NSZW6uubHC\nd3wTH5Q9+exVzgSqq4FK+gSYAxaLBPmw7BlEtbsXsAWQ7H1ShVuwkSvZzJU0sJEx\nDbumXKY+R5L4qjTVxNHvQVAuyaJRrOVcdPUqv4QkCDirKLLlUBdEKti29uOJooxi\nkU7jkWQRoSANxKc6URcCAwEAAaOCAmIwggJeMIGPBgNVHSMEgYcwgYSAFO/cRNLG\njcAOozjAfJPGw0G/So/woWikZjBkMQswCQYDVQQGEwJLUjENMAsGA1UECgwES0lT\nQTEuMCwGA1UECwwlS29yZWEgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkgQ2VudHJh\nbDEWMBQGA1UEAwwNS0lTQSBSb290Q0EgNIICEBwwHQYDVR0OBBYEFFVgrdqBI9bL\n1kvkzWOTTZJOYkffMA4GA1UdDwEB/wQEAwIGwDB6BgNVHSABAf8EcDBuMGwGCiqD\nGoyaRQEBBggwXjAuBggrBgEFBQcCAjAiHiDHdAAgx3jJncEcspQAIKz1x3jHeMmd\nwRwAIMeFssiy5DAsBggrBgEFBQcCARYgaHR0cDovL3d3dy55ZXNzaWduLm9yLmty\nL2Nwcy5odG0wcQYDVR0RBGowaKBmBgkqgxqMmkQKAQGgWTBXDBLsobDsnYDshLjr\nrLTrspXsnbgwQTA/BgoqgxqMmkQKAQEBMDEwCwYJYIZIAWUDBAIBoCIEIN4qgVLk\npXcttjgayV7vwtOaVVjteBB3ilEoMpqcHE7tMHIGA1UdHwRrMGkwZ6BloGOGYWxk\nYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1kcDVwNTMyOTUsb3U9QWNjcmVk\naXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Qw\nOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxodHRwOi8vb2NzcC55ZXNzaWdu\nLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQA8IodCcv/8atDTys3AbCPF9JWR\np0Wtb6S/aT+g8EAtloyxFVVSGwGMFpDj7H+xQhMh7cjUd1+lMbt2pyXYRC3ufj7R\nA/7vmwCHyA8PD7lTEEggzCW/jrYJSfPaw/vr3TPv3yX4dM+dGnwOwXU9tkhVoUkF\nfz0BHgvTYLRT9ZaETFtWGXhDCVXsN627DmoIb3jTeAE+iiCeniYMcOu0KAM2mID1\nbzsG/ag3XaESIRdifEeFsv8jfvs7ZJdHIoZ9REoGLq806WnlCi1AtqYHw1pEEpE1\nI5amvMd0J7ZLCwyWlbFIQNZqD6bit4IG+t+e2WeteD7w76hJD50rakNPDKf1\n-----END CERTIFICATE-----",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQI6iTKEdeoKJsCAggA\nMBwGCCqDGoyaRAEEBBCa0ikEisIzlhQup0S5mXUjBIIE8H7qcNczZGYkYuMptakb\ngCHv+nlMPkUmJ7PO3h1GeZRgUoqqsRHWbgRKx6LbXelodeHAHOuFMJZl5hmszbrz\njA1zbs3Jf3HVYAhZh8p/Eh2sGzABCSWRwSLhN+EvqCXv4ts8RmtlHzd/zx/jkc9c\nyPV5Cs/934pgf88ns4rAUDDy4Tl56RaqCCzUU+aqZ7v1I1IAbOI0zcS3pBdhDQcn\nEo/dmR65i+J/x8lZCl2MzC7+VUx9MSeiZ3i3qFMO5beHOGL+moSozGXkS0dHRGlP\n1oe/hJFFHYBw3ZuTsZa/ul+AGd0Yw5ni/MKLh9tq2VU5XI4pcU9Ova0UJAL5B3oh\nWKedK+EdYxZj5tlW6hiJQZRDIh8du1lIUvvqtQ1qjvDNZ6UUtqfOl40WCEQvRobA\naNSVHjFhtdcjaYXudEJ3xs9bo+7lvkjB/2lF9xQRpC7sYUYJ8SEQAfSgZZSZfsfl\nSyFMobGArOA23gEyu+9msJGjqkthz4CNZsTP2uTFkMKOD/w7FIkYUdBshrkvqrwK\nsN2PURiTcyMpSj0ckCyb9HdF6Tm+jFGZz8o4r8Uz/6eGl2/bMt8sCsjlqSIdhhUc\n1yQ5iDXzr2pEg/Szme4AM8x9jje9gLWyoJIkOrkNDNFw350gfMOPDlbVy0PHbcTQ\nuJCLNl3Q0INrGmPYs3VzE7dNYZ9pRrWkXGNQeswdRMmcD4pbyLtGjWQbsvcq16xP\n0yfFtxpd4nbXxiI+WDAp4MIpLejcjVXQbQuc2wyUrbxoItN1JG60G81bNBUak2Eh\nI0utWFG6rJ0BSMWibAc+g2IzjwdJZcoy9WszZOY7D3dgyYKTnvMSF+FOoxwyrRx9\nzXQWfwNnKt3XlReX0SnYzm8neCU2rcvvKIhplKYDsP9qNAji9UuMev+l/CwaCfq4\n5wByUluOU/3KiYy7mFzsRZUIPeREMs13rc1c5gl3AHHG9PYJIpLTFgsJ8nVjcFqy\n2Okjo8UWqCUIpZa+GSL+B8AroJASP19pJ6KmjYSliCVlMU3uizgpzKcTHXW6F5Mv\nfIRmlu6eGq4lnR5T9FDSXve6+psiXCc9k3FjobFyR0knr5ZyFAf1YKZXXAOJJQrC\nx9c0J0RdAkeAnkyPJXhj5oVj+o6K4v5ImfpC8gvp1l7BBgICClA1dJY0ODps+L/0\nLYVOMXB6rK1TFcv/FU7V7BQQdwRM0TlhdFllNMOVZYLXEfGkicZW2ekRu9+HsD26\nsUHgXfDKhjkcs6pRUL2K0Tn/4dGHXMbTgr1u3nx0wr0bBADnDHmylUZgCQlcNCNu\no8KWl3KY8Ap/12SwxR3NXeOowOpQB/ztE2cKHsVvt+G5CT7heaplK7YFOpovNiWx\nlMGZ3L1d3IX8GDuJ/x3zs/Kb8RoLKwZHkE67LE3Sqz2dR66LPxfCbuiGIaNsxvOO\nQIPJLJINSclDWkFFS7/yAa/LSbzkZo0XfdJ9LRbFvFjoyJ/WaCiHNUSHzugzu1uj\nhUmDMZSX5U/WEgUi7cbIVpXsQtpH57d++sBkYllCe1oJg+90god7WXaYvqQv5WN6\nlybRC6x9iAFtgoPIoaFGpwKX0bwDO1jIeQEj7erHjbatbZFj3FONp+bcif/I4Tmg\n7OwKlKoGet/pvv3mFI1vuKWKbO76epTdtezD0F1Ld34R4wgINVWRWsmW+odDVYFT\nAUE=\n-----END ENCRYPTED PRIVATE KEY-----",
                   "signPw": "won139900*",
                   "userId": "",
                   "userPw": "",
                   "agentId": "P07085",
                   "agentPw": "won139900*",
                   "bizNo": "2180363456",
                   "inqrDtStrt": "20200101",
                   "inqrDtEnd": "20201110",
                   "itrfCd": "41",
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
	           _loadDoc("execute", JSON.stringify(jsonData)).then(function(result) {
	               console.log(result);
	           });
               */
               
	           
               let jsonList = [];
               jsonList.push(jsonData);
               
               let url = "https://183.111.102.219:9402/rest/ext";
               
               $.ajax(url, {
                   type : "post",
                   //dataType : "json",
                   data: {
                       inJsonList: JSON.stringify(jsonList)
                   }
               })
               .done(function(data) { // success 시
                   console.log(data.outJsonList[0]);
                   //console.log(data);
                   //console.log(JSON.stringify(data.outJsonList[0].list));
               })
               .fail(function(data) {
                   alert("loadLogs failed");
               });
	        });
	        
	        $("#btnSingleTest2").on("click", function() {
	            let jsonList = [{
                   "agentId": "",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
                   "signPw": "8495volo",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
                   "thread_id": "8d02208e-c8c2-46bf-a8ff-8049c6ef186e",
                   "userPw": "",
                   "bizNo": "1538500145",
                   "itrfCd": null,
                   "wrtArr": null,
                   "appCd": "bizbooks",
                   "reqCd": "",
                   "svcCd": "Z3001",
                   "supByr": null,
                   "userId": "",
                   "agentPw": "",
                   "stlYr": null,
                   "wrtYr": null,
                   "orgCd": "hometax",
                   "fromQ": null,
                   "taxGb": null,
                   "toQ": null,
                   "inqrDtStrt": "20201210",
                   "inqrDtEnd": "20201210",
                   "fromY": null,
                   "dtCd": null,
                   "wrtQt": null,
                   "toY": null
               }, {
                   "agentId": "",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
                   "signPw": "8495volo",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
                   "thread_id": "8d02208e-c8c2-46bf-a8ff-8049c6ef186e",
                   "userPw": "",
                   "bizNo": "1538500145",
                   "itrfCd": null,
                   "wrtArr": null,
                   "appCd": "bizbooks",
                   "reqCd": "",
                   "svcCd": "Z3002",
                   "supByr": null,
                   "userId": "",
                   "agentPw": "",
                   "stlYr": null,
                   "wrtYr": null,
                   "orgCd": "hometax",
                   "fromQ": null,
                   "taxGb": null,
                   "toQ": null,
                   "inqrDtStrt": "20201210",
                   "inqrDtEnd": "20201210",
                   "fromY": null,
                   "dtCd": null,
                   "wrtQt": null,
                   "toY": null
               }, {
                   "agentId": "",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
                   "signPw": "8495volo",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
                   "thread_id": "8d02208e-c8c2-46bf-a8ff-8049c6ef186e",
                   "userPw": "",
                   "bizNo": "1538500145",
                   "itrfCd": null,
                   "wrtArr": null,
                   "appCd": "bizbooks",
                   "reqCd": "",
                   "svcCd": "Z3003",
                   "supByr": null,
                   "userId": "",
                   "agentPw": "",
                   "stlYr": null,
                   "wrtYr": null,
                   "orgCd": "hometax",
                   "fromQ": null,
                   "taxGb": null,
                   "toQ": null,
                   "inqrDtStrt": "20201210",
                   "inqrDtEnd": "20201210",
                   "fromY": null,
                   "dtCd": null,
                   "wrtQt": null,
                   "toY": null
               }, {
                   "agentId": "",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF2zCCBMOgAwIBAgIEJoUO0TANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDAxMDUxNTAwMDBaFw0yMTAxMjYx\nNDU5NTlaMIGcMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDjAMBgNVBAsMBVdPT1JJMQ8wDQYDVQQLDAZBTFZPTE8x\nRDBCBgNVBAMMOyjso7wp7JWM67O866Gc7JeQ7ZSE7JWk7JSo7ZS8KEFMVk9MTykw\nMDIwMDI0MjAxNjAxMjIzMTI3MjYyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAjaYSn+eOKji5baonc0Vn7aWN5jHSCsHifKUp4hFx8H5QAKTmNC4y1Yfu\n+XkdmnVbWE9VXIdFMw0ZDa2zIz5ukq2EyJo4h953qHtILJGgbqy5E6pG1S9RQC18\nyvDkd5inbMYsP6NLAVgLrvOXZ+fKKKqSSfwiV7MvKxwe8oIjJLeo8kfpjQC9L0Ot\n5KHxpYLRrKBLfHT+orH5YyWEBpL1nTG65YtDH7tVwg4W020l7Mvw+mZ8wI68wuBJ\nTriLWQMgnxb2rMvp4AoZjShboUbTm7cQ0tTTbQU29Hm3fmhRUH6Y34CQzUMYx5HO\nvvORMzRED22vQt6cGKJposLpBzvQ9wIDAQABo4ICbDCCAmgwgY8GA1UdIwSBhzCB\nhIAU79xE0saNwA6jOMB8k8bDQb9Kj/ChaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYD\nVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\neSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQHDAdBgNVHQ4EFgQU\nhE1ga7YEaYOnOGypFsEo8oMLf30wDgYDVR0PAQH/BAQDAgbAMHkGA1UdIAEB/wRv\nMG0wawYJKoMajJpFAQECMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs\n9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93d3cueWVzc2ln\nbi5vci5rci9jcHMuaHRtMHwGA1UdEQR1MHOgcQYJKoMajJpECgEBoGQwYgwdKOyj\nvCnslYzrs7zroZzsl5DtlITslaTslKjtlLwwQTA/BgoqgxqMmkQKAQEBMDEwCwYJ\nYIZIAWUDBAIBoCIEIIMjIOQKlT6cf+efngCIht2/ojDrIQMtIKCJ3b9R4YCaMHIG\nA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmtyOjM4OS9vdT1k\ncDVwNTQ0MTcsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWtyP2NlcnRpZmlj\nYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsGAQUFBzABhhxo\ndHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEBCwUAA4IBAQCJ\no9VQ1W6L425cvczosMbErMt98IMhnqgnIAa4eoB1icNU8b5CEPDXz9/s8nyX6Wof\njDTN+/7TkpM7C3JvgWzC2lTl9s782p0E9QnMhxAiWPHovOC6mxoKTbz1GhI5yR6c\nDSCp6mhcaqiGSqqdWWJONxwVTNp2+3T2Kwjfa4YQXxb+N7PuuI3WY14Fhmerftdg\nmi7vMVSeg/xvQfjxOkZdISyjDtZpS0CFxND2yVJA9gjIYEChhBtwEAZUfIY/Z/DU\n8KxsvaD6NSO5l5wcDxzXqeVAlCttsOpdA2DZFGoiohSfE+W9vETEQLenfX7RNivI\nXlPgiUVt9EAE8k1zJS4A\n-----END CERTIFICATE-----",
                   "signPw": "8495volo",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFEDAaBggqgxqMmkQBDzAOBAgZS7mcpVMI+AICCAAEggTwbjBfKFhBidvyBJvq\no6Zjha9lbdUa43nmsWgU9iqEsKQ/Hjy2vcnvPcELoSbbbObrgQWn8OyZqFvnBSVv\nEoxqgogxkrnT1WQjnQFsoTXpVhsjTzjPJv+GX9Ro8qb08Lird2o6Yurj5mcxpyXY\nDnAIXVy2DVs0JyBp6oDHe96sxw8bI1zMMONMChV9TEydQEWuk1mPz7c8xQIUNMSj\niFt2W2bm/KxTh0EB4d9UyXJ9RXuTZ6zyK6+wfTk0iGlNgaIpPfkQV5smyjrmu8PD\nCu9PRb6wMY5tLsM7NNB31+qv6dldVPYgc6rny3J6MVkO04IB80+czM6NbQIBvKVb\ntaoXG8XVylMz9pRMtpAWmSpGao79F9NSM7OuBqsNu4HskJgjtLKMMDPucDr4++L2\nb6pCCeXpgvsiHng7tiItbM2XnnhOY/tWvDRdUguVjDFL1VAu9M+YEvCzDReCm1jZ\nTNkenru8B6EttODWje2NpQjkd2qxqMYRN43ptPnaUFVqp+7vMK6ykBux8IyquMbt\nKs+euLBAFUuslmiqOq5Vrd4N/MeIXIpYivwKD7qqslyv/nSCnirdw/CNGwsaSvBA\nMsS6Ik3M3dCmQsF1y6ij2vn3zwTiqQFTn2/1RtVJ+CUlSp9TiD5I0kNfJpKjdWxJ\nrYOLiWP4AQPQxYByCtJB6OZ3h2yt4UKSdpaU15oCZGMDTHEyRgWUmw4KuybgwkCW\nGoMBSNFwZpwcifcbPBep9UVP+1MTBvWHG/GdQE9mnE0lvwzEn6EHB2fEg23kCAF1\nxHWwFqugtYCngywnfRZwjYsXf01XZWSzk03NSJZlJ22DvJlKWNuSdQXv/mnDkFK1\nYpRq96M1ugman3qmmoaI2C+4pO+oBEVr4CVDkH5iqlS8OsQDTgT/NuqAboUQWo5u\n31p5mEA04vhjUBCH4wMcK7EoMsDqnkRQYiB8KX6ibFD98+i9AAg50jPXYR96d2u+\nyE+DiGhlq+huNoswaCAWmZqf4/3ryKFD77sRQOYLp0mrJ/mcx1ZEiU5oXHyxch3s\nxqp3nUD7UTon/wG1CO+1R6SK1ZFOw1UwU8k8EeBQMRXJQIxRsNsvBW3/9owhmmc9\nYg56BVzDaWsa/CkBazw7N6B6qFBDntNPw+i0C5Td0lpeyVHHttPyujiE9kiopXUh\nbHcESQ079tKnScVPK55g+12hcyM+ZpLl/ejXQVRvCmaa/YUL7SciOzsMa4Ekwu03\nhSEthIidFtoFB2gxXsFd8G/mMTL8K9uzljq5cfMO1Q17UAzU0zYoOWLLwpSP/FGc\nsRtIx+Il6+usREBdTvUpt/QCPcyMWKk0nj0rVpqSbLUq1HeLd2/HwpDFuMQqna/7\nMFYXvlcYvVAkuTEjAJTSVAYUYEXtn2R5xEW42CIZP1irPLBsYRXathxZcglZHYGr\nzV4YFvB2avahLaL0fSxOwdsd3IkOLShLiG4hXkmV2z43aGE30mtf7nuD2chum3dt\n/Lp1jQLctRiLmpjRB5B6X+6JggjEbLPgn0Qm+ar5Fdhicm2P2yyugJ52tVvNsHpa\n/9DCNxM3BB+ZcT+/OYM+rOzrspMGUk1NxzhIFuWXwj6r+N6IiETb5gazutOrBEsB\n2llDl24KfBaZ2fCwyyCKWeUqeIs73Z+d3KWvegz2yCtG7SCSbTXcpFPLxsJinTFE\n+YUjzQ==\n-----END ENCRYPTED PRIVATE KEY-----",
                   "thread_id": "8d02208e-c8c2-46bf-a8ff-8049c6ef186e",
                   "userPw": "",
                   "bizNo": "1538500145",
                   "itrfCd": null,
                   "wrtArr": null,
                   "appCd": "bizbooks",
                   "reqCd": "",
                   "svcCd": "Z3004",
                   "supByr": null,
                   "userId": "",
                   "agentPw": "",
                   "stlYr": null,
                   "wrtYr": null,
                   "orgCd": "hometax",
                   "fromQ": null,
                   "taxGb": null,
                   "toQ": null,
                   "inqrDtStrt": "20201210",
                   "inqrDtEnd": "20201210",
                   "fromY": null,
                   "dtCd": null,
                   "wrtQt": null,
                   "toY": null
               }];
          
	           let url = "https://183.111.102.219:9402/rest/ext";
	               
               $.ajax(url, {
                   type : "post",
                   //dataType : "json",
                   data: {
                       inJsonList: JSON.stringify(jsonList)
                   }
               })
               .done(function(data) { // success 시
                   console.log(data.outJsonList);
                   //console.log(data);
                   //console.log(JSON.stringify(data.outJsonList[0].list));
               })
               .fail(function(data) {
                   alert("loadLogs failed");
               });
	            
            });
	        
	        
	        function test(jsonList, idx) {
	            if(idx >= jsonList.length) {
	                return;
	            }
	            
	            _loadDoc("execute", JSON.stringify(jsonList[idx])).then(function(result) {
                    test(jsonList, ++idx);
                });
	        }
	        
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
	    
	    /*
	     * _loadDoc <PROMISE>
	     * 
	     * @description: 
	     *   NAX 모듈을 호출합니다  
	     *    - 사용법: http://www.infotech3.co.kr/guide/nx2.html 참고
	     * 
	     * @param: 
	     *   {String} s_op : 수행 내용
	     *   {String} s_inJson : 파라미터
	     * 
	     * @return: 
	     *   {Object} : 결과 Object
	     * 
	     */
	    function _loadDoc(s_op, s_inJson) {
	        return new Promise(function(resolve) {
	            $.ajax({
	                type: "POST",
	                url: "https://127.0.0.1:16566/?op=" + s_op,
	                data: s_inJson || "{}",
	                crossDomain: true,
	                crossOrigin: true,
	                dataType: "json",
	                contentType: "application/json; charset=UTF-8",
	                success: function(data) { // data : outJson Object
	                    /* 결과 확인 및 data(outJson) 처리 로직이 들어가면 됨 */
	                    resolve(data);
	                },
	                //async: false,
	                error: function(xhr, status, error) {
	                    /* s_op가 'setup'일 경우에 설치파일 다운로드 로직 구현 */
	                    if ('setup' == s_op) {
	                        alert('프로그램 설치가 필요합니다.');
	                        let downLoadUrl = util.getContextPath() + "/common/scrap/nax/downloadSetupFile";
	                        if(!$('#ifrFile').length) {
	                            $("body").append($("<iframe/>",{id:"ifrFile",style:"display:none;"}));
	                        }
	                        $('#ifrFile').attr('src', downLoadUrl);
	                    } else {
	                        /* 에러 처리 로직이 들어가면 됨 */
	                    }
	                }
	            });
	        });
	    }
    </script>
</head>
<body>
    <input type="button" id="btnSingleTest" value="단일테스트" />
    <input type="button" id="btnSingleTest2" value="단일테스트2" />
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
