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
	           /*
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
                   //"reqCd": "",
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
	           */
	           /*
	           let jsonData = {
                   "appCd": "bizbooks",
                   "orgCd": "hometax",
                   "svcCd": "Z5002",
                   "reqCd": "202012181141120000000437",
                   "proxy": "121.126.134.144:7136",
                   "signCert": "-----BEGIN CERTIFICATE-----\nMIIF5TCCBM2gAwIBAgIEKRtnsjANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDExMzAxNTAwMDBaFw0yMTEyMDgx\nNDU5NTlaMIGkMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjETMBEGA1UE\nCwwKeFVzZTRFc2VybzEQMA4GA1UECwwHS05CQkFOSzERMA8GA1UECwwIc2VvIHNh\nbmcxSTBHBgNVBAMMQOyEnOyDge2ZmOyEuOustO2ajOqzhOyCrOustOyGjChzZW8g\nc2FuZykwMDM5Njg2MjAxMDExMjMxMzkwMDAwMjEwggEiMA0GCSqGSIb3DQEBAQUA\nA4IBDwAwggEKAoIBAQCzaiLKG5tlwsK0YuR7xHG3thsoLV8Ox3qtuZh5mTvaDVqV\nmuiMaJc3B7fDRdUsMRCgQwM1+eqL6eLebZtwLBn0ZMYl3Ks9/zfCGwFlu/oY/9VZ\nQ1F708ONfKNow/bLgrPL5ArY4Xy4oOj0evVqvq7X4CdeRMG6IGOAios+8/KkOxJ1\nFFpZVDWshMNq+/u5oWnKJho+nTa95FZHIE9TdGtjNpe4FvhJiBAMHOBhwuf+b6YC\n2S4t6tuTsZcEpAS9lI1zxEOFmX1hy4nPDUqIHk4OYu2v+IYbkJ5W/TXKxfnwMhCf\nsXIZrBjpNE3Vf5dXsm8BNuNC1X/9BHk+yFrto/PLAgMBAAGjggJuMIICajCBjwYD\nVR0jBIGHMIGEgBTv3ETSxo3ADqM4wHyTxsNBv0qP8KFopGYwZDELMAkGA1UEBhMC\nS1IxDTALBgNVBAoMBEtJU0ExLjAsBgNVBAsMJUtvcmVhIENlcnRpZmljYXRpb24g\nQXV0aG9yaXR5IENlbnRyYWwxFjAUBgNVBAMMDUtJU0EgUm9vdENBIDSCAhAcMB0G\nA1UdDgQWBBQsbc5p0GXmkrFlSW5RndE/nhqWNzAOBgNVHQ8BAf8EBAMCBsAwegYD\nVR0gAQH/BHAwbjBsBgoqgxqMmkUBAQYIMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4\nyZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93\nd3cueWVzc2lnbi5vci5rci9jcHMuaHRtMH0GA1UdEQR2MHSgcgYJKoMajJpECgEB\noGUwYwwe7ISc7IOB7ZmY7IS466y07ZqM6rOE7IKs66y07IaMMEEwPwYKKoMajJpE\nCgEBATAxMAsGCWCGSAFlAwQCAaAiBCCeag+SGCKMd8gAlWqNXyjhWzE9EQMp9V/v\n7im6Ht8akjByBgNVHR8EazBpMGegZaBjhmFsZGFwOi8vZHMueWVzc2lnbi5vci5r\ncjozODkvb3U9ZHA1cDY4ODg2LG91PUFjY3JlZGl0ZWRDQSxvPXllc3NpZ24sYz1r\ncj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0MDgGCCsGAQUFBwEBBCwwKjAoBggr\nBgEFBQcwAYYcaHR0cDovL29jc3AueWVzc2lnbi5vcmc6NDYxMjANBgkqhkiG9w0B\nAQsFAAOCAQEAaiWP4DW3lA258iAhTJQhY0XFr8vix55XDgEqCkboBPyjjD2gQzvd\nmfSpy5Cy7HWlBWfq3qTIDDAQn7BgXPxVpoEY2M1TUrLM83IOWtOc4zNc8UeLY2j/\nLLM10IndqCEwQOEQpd+i7i+gwT9yDo1kvvIdp9Auw58CMO7qoXTIZeA7K6umHjWp\ntl/ANQG9uN4+mibdS61zCUxBtr6mdr+atVX7oXLCoo33VOaY8aOJ+djSnt2eliPF\nDadywyUsW8JV4kp7RobU0HlZzQ63AiQtP9N1SBXw0djLvoIDoTvukCHZyBw9GWT6\nFymSgNW2QfChWmViPEC1FV4pHadRhZXM5w==\n-----END CERTIFICATE-----",
                   "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIYuu4T0e3+sgCAgQA\nMBwGCCqDGoyaRAEEBBBvGFI7ahZFNL4P0rcZMP4ABIIE8HFGDDZnTFIAdTcTyMZ2\n2q58/C1oX2awqn7kv29vSf/0XsccPiv3oh3UcKCrSkesGDkH1uSADpTlFrdeDQRA\nkMuNEUD9wm8OwvvnONB882Ds7LAXE8q7YIngrwB8gGWFWEjM4oHWdeTXn7rbedTd\nKU107Yv58zA3fV2YJdjitnNPWunk3cjmNpgl4n3xLHHzjyj8PihRzG690sQGAVC+\na7doz2Wzptl5eBMsWInG1BcvcVPklDIvM25o/rkBp1/97OzBAciVwy8IFsXMYR2f\nv5SLAcNQWpUhjxGnm+CenDLIKUwzVyd2h6CDTezn97SpNdWHtzxAmO/ujnQPhZ8g\n3nvkWcny58NahLChTaSqqywRCrKzx9KhweHpwOQ7SHvxOgDNcos8+dTg5KcpgEBR\ndwMAjAZwBhCaJdx3aRL9jjgpJdB43x7BQJgF57rYHQ4SICz9qoAgenCLjVEqxtHW\nJgV/TP0ZvVXmdhyYtuuTBVG/+HWJ/LjEAaV6j/jaYg8xn74b/2ErCh7Y+D6JqUjY\nmj5OmBWEk4/nKo3tkRfFDgj9DyYQn61s/Fp7iSyhRRiL/3ew/5m4KPu+B9A3Oid/\nyf/3MFXN1/qewudt1N0a0BuSrDm9qFOLWNTxlbNzZcgKhA8pBz2YinKrSBdz7+lo\nbZjD90Z0TNFuYjflnsnL6sfEvof06bxK5VGABDWiEKbUp5/811CIpKunlSRUU5Z+\nb240Rv61sLZ1BVtKbfEeH4eZjjsGsGj/R9VXfmXTt2AE2K6CxLA+SdBcEhz0CUMy\njJST1X/HXEO0eqYd9FiCTIHu9OAeBAdD90CeAiJlfHdwLYEYV6VFHZPJg8iWOVAU\nknW9RqYlIUBsTIoFbU7xbLUB+tmhUZauWyVZQzcwEiuyI9gVo6tlOhXe1y4gTfYK\nQS/vyiZwf9ZbCXGlErVjMmp44MFdgae5cnuS3u7CB7/ugojCVUFuUJHXARltnpZi\nJIaAX6TZuSwG9iAz788hTRg9UIs0/d/sfuFuuFt07tgAuKS1fn35IZYr3pImwCPl\nz7FKoxqyqkszAoUxYAnasDX9I8U826ih0C5wvwHMwH0jneOo0Ma8ecVlOX9rRMKK\nCQcrsB0pIc4TNBO++rY1fxLmPM8pAue/mlTodBI9EyvXF3d7Vajw4rvvKuUi7KnB\nwPsENti+4gey56DlZmLqLOP3+KMJtc14YEhkxj7cQ4j/ZGarrkAu5P4Wz5SLrF+p\nwjbAWyXPtDxz9uDOppz7gObMsGqKsHjV/rHevXJGfIeBCoD4Pth7QOjw3LG5k8cO\ni7ahgEzhwI6tLRLAbnkYFRFSChwbgiNe/eJwhYi4XQ2Y9dTZWuZqQVmrwkwY+A6m\nIcMbBIXXzqZPQo+pJHnU67yRLjT0LXkVoMmVJvmMByLy5CTVWWvi17AqDbVTsZZv\n8ooSBq9lRkBTx0xCyUPfIjuLUYNJMRdaoJZBwI259zun7IfMk19+ovjy7OcLOUhY\nZOKVWmbuIXk19B3TYtIMhwHzbUvOWCmxilPzl8dK4fQRdxc12vy0EjfVImK6W90V\n+Tyf/efZ/r267ue8LoBCw8D76gaaJotWZOuolMOqxrkjubu8tNxln2LWCGl0D+S7\nPMuMvIm5adMhtHa5oQtyqeCcAVQBTYknS7ZXkKwK9z3WTTfHJ3yNX7ERPPyfl73f\n6dU=\n-----END ENCRYPTED PRIVATE KEY-----",
                   "signPw": "ctassh0540*",
                   "userId": "",
                   "userPw": "",
                   "agentId": "",
                   "agentPw": "",
                   //"bizNo": "6082376002",
                   "bizNo": "2245200449",   // 유원테크
                   //"bizNo": "6080554998",
                   //"bizNo": "2148771987", //
                   //"inqrDtStrt": "20200101",
                   "inqrDtStrt": "20200101",
                   "inqrDtEnd": "20201217",
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
                   "wrtQt": null,
                   "pdfYn": "Y",
                   "payPdfYn": "Y",
                   "sgPdfYn": "Y",
                   //"savePath1": "C:\\Users\\Minhyeok Kim\\Desktop\\201218",
                   //"savePath2": "C:\\Users\\Minhyeok Kim\\Desktop\\201218"
               }
               */
               let jsonData = {
                   appCd: "bizbooks",
                   svcCd: "B0001",
                   orgCd: "bank",
                   bankCd: "088",
                   loginMethod: "CERT",
                   signCert: "C:\Users\Minhyeok Kim\AppData\LocalLow\NPKI\yessign\User\cn=김민혁()008804220100308188000091,ou=SHB,ou=personal4IB,o=yessign,c=kr\signCert.der",
                   signPri: "C:\Users\Minhyeok Kim\AppData\LocalLow\NPKI\yessign\User\cn=김민혁()008804220100308188000091,ou=SHB,ou=personal4IB,o=yessign,c=kr\signPri.key",
                   signPw: "rlaalsgu1!", 
               }
	           
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
	            let jsonData = 
	            /*
	            {
                    "appCd": "bizbooks",
                    "orgCd": "hometax",
                    "svcCd": "P5001",
                    "reqCd": "202012181141120000000437",
                    "proxy": "121.126.134.144:7136",
                    "signCert": "-----BEGIN CERTIFICATE-----\nMIIF5TCCBM2gAwIBAgIEKRtnsjANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDExMzAxNTAwMDBaFw0yMTEyMDgx\nNDU5NTlaMIGkMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjETMBEGA1UE\nCwwKeFVzZTRFc2VybzEQMA4GA1UECwwHS05CQkFOSzERMA8GA1UECwwIc2VvIHNh\nbmcxSTBHBgNVBAMMQOyEnOyDge2ZmOyEuOustO2ajOqzhOyCrOustOyGjChzZW8g\nc2FuZykwMDM5Njg2MjAxMDExMjMxMzkwMDAwMjEwggEiMA0GCSqGSIb3DQEBAQUA\nA4IBDwAwggEKAoIBAQCzaiLKG5tlwsK0YuR7xHG3thsoLV8Ox3qtuZh5mTvaDVqV\nmuiMaJc3B7fDRdUsMRCgQwM1+eqL6eLebZtwLBn0ZMYl3Ks9/zfCGwFlu/oY/9VZ\nQ1F708ONfKNow/bLgrPL5ArY4Xy4oOj0evVqvq7X4CdeRMG6IGOAios+8/KkOxJ1\nFFpZVDWshMNq+/u5oWnKJho+nTa95FZHIE9TdGtjNpe4FvhJiBAMHOBhwuf+b6YC\n2S4t6tuTsZcEpAS9lI1zxEOFmX1hy4nPDUqIHk4OYu2v+IYbkJ5W/TXKxfnwMhCf\nsXIZrBjpNE3Vf5dXsm8BNuNC1X/9BHk+yFrto/PLAgMBAAGjggJuMIICajCBjwYD\nVR0jBIGHMIGEgBTv3ETSxo3ADqM4wHyTxsNBv0qP8KFopGYwZDELMAkGA1UEBhMC\nS1IxDTALBgNVBAoMBEtJU0ExLjAsBgNVBAsMJUtvcmVhIENlcnRpZmljYXRpb24g\nQXV0aG9yaXR5IENlbnRyYWwxFjAUBgNVBAMMDUtJU0EgUm9vdENBIDSCAhAcMB0G\nA1UdDgQWBBQsbc5p0GXmkrFlSW5RndE/nhqWNzAOBgNVHQ8BAf8EBAMCBsAwegYD\nVR0gAQH/BHAwbjBsBgoqgxqMmkUBAQYIMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4\nyZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93\nd3cueWVzc2lnbi5vci5rci9jcHMuaHRtMH0GA1UdEQR2MHSgcgYJKoMajJpECgEB\noGUwYwwe7ISc7IOB7ZmY7IS466y07ZqM6rOE7IKs66y07IaMMEEwPwYKKoMajJpE\nCgEBATAxMAsGCWCGSAFlAwQCAaAiBCCeag+SGCKMd8gAlWqNXyjhWzE9EQMp9V/v\n7im6Ht8akjByBgNVHR8EazBpMGegZaBjhmFsZGFwOi8vZHMueWVzc2lnbi5vci5r\ncjozODkvb3U9ZHA1cDY4ODg2LG91PUFjY3JlZGl0ZWRDQSxvPXllc3NpZ24sYz1r\ncj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0MDgGCCsGAQUFBwEBBCwwKjAoBggr\nBgEFBQcwAYYcaHR0cDovL29jc3AueWVzc2lnbi5vcmc6NDYxMjANBgkqhkiG9w0B\nAQsFAAOCAQEAaiWP4DW3lA258iAhTJQhY0XFr8vix55XDgEqCkboBPyjjD2gQzvd\nmfSpy5Cy7HWlBWfq3qTIDDAQn7BgXPxVpoEY2M1TUrLM83IOWtOc4zNc8UeLY2j/\nLLM10IndqCEwQOEQpd+i7i+gwT9yDo1kvvIdp9Auw58CMO7qoXTIZeA7K6umHjWp\ntl/ANQG9uN4+mibdS61zCUxBtr6mdr+atVX7oXLCoo33VOaY8aOJ+djSnt2eliPF\nDadywyUsW8JV4kp7RobU0HlZzQ63AiQtP9N1SBXw0djLvoIDoTvukCHZyBw9GWT6\nFymSgNW2QfChWmViPEC1FV4pHadRhZXM5w==\n-----END CERTIFICATE-----",
                    "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIYuu4T0e3+sgCAgQA\nMBwGCCqDGoyaRAEEBBBvGFI7ahZFNL4P0rcZMP4ABIIE8HFGDDZnTFIAdTcTyMZ2\n2q58/C1oX2awqn7kv29vSf/0XsccPiv3oh3UcKCrSkesGDkH1uSADpTlFrdeDQRA\nkMuNEUD9wm8OwvvnONB882Ds7LAXE8q7YIngrwB8gGWFWEjM4oHWdeTXn7rbedTd\nKU107Yv58zA3fV2YJdjitnNPWunk3cjmNpgl4n3xLHHzjyj8PihRzG690sQGAVC+\na7doz2Wzptl5eBMsWInG1BcvcVPklDIvM25o/rkBp1/97OzBAciVwy8IFsXMYR2f\nv5SLAcNQWpUhjxGnm+CenDLIKUwzVyd2h6CDTezn97SpNdWHtzxAmO/ujnQPhZ8g\n3nvkWcny58NahLChTaSqqywRCrKzx9KhweHpwOQ7SHvxOgDNcos8+dTg5KcpgEBR\ndwMAjAZwBhCaJdx3aRL9jjgpJdB43x7BQJgF57rYHQ4SICz9qoAgenCLjVEqxtHW\nJgV/TP0ZvVXmdhyYtuuTBVG/+HWJ/LjEAaV6j/jaYg8xn74b/2ErCh7Y+D6JqUjY\nmj5OmBWEk4/nKo3tkRfFDgj9DyYQn61s/Fp7iSyhRRiL/3ew/5m4KPu+B9A3Oid/\nyf/3MFXN1/qewudt1N0a0BuSrDm9qFOLWNTxlbNzZcgKhA8pBz2YinKrSBdz7+lo\nbZjD90Z0TNFuYjflnsnL6sfEvof06bxK5VGABDWiEKbUp5/811CIpKunlSRUU5Z+\nb240Rv61sLZ1BVtKbfEeH4eZjjsGsGj/R9VXfmXTt2AE2K6CxLA+SdBcEhz0CUMy\njJST1X/HXEO0eqYd9FiCTIHu9OAeBAdD90CeAiJlfHdwLYEYV6VFHZPJg8iWOVAU\nknW9RqYlIUBsTIoFbU7xbLUB+tmhUZauWyVZQzcwEiuyI9gVo6tlOhXe1y4gTfYK\nQS/vyiZwf9ZbCXGlErVjMmp44MFdgae5cnuS3u7CB7/ugojCVUFuUJHXARltnpZi\nJIaAX6TZuSwG9iAz788hTRg9UIs0/d/sfuFuuFt07tgAuKS1fn35IZYr3pImwCPl\nz7FKoxqyqkszAoUxYAnasDX9I8U826ih0C5wvwHMwH0jneOo0Ma8ecVlOX9rRMKK\nCQcrsB0pIc4TNBO++rY1fxLmPM8pAue/mlTodBI9EyvXF3d7Vajw4rvvKuUi7KnB\nwPsENti+4gey56DlZmLqLOP3+KMJtc14YEhkxj7cQ4j/ZGarrkAu5P4Wz5SLrF+p\nwjbAWyXPtDxz9uDOppz7gObMsGqKsHjV/rHevXJGfIeBCoD4Pth7QOjw3LG5k8cO\ni7ahgEzhwI6tLRLAbnkYFRFSChwbgiNe/eJwhYi4XQ2Y9dTZWuZqQVmrwkwY+A6m\nIcMbBIXXzqZPQo+pJHnU67yRLjT0LXkVoMmVJvmMByLy5CTVWWvi17AqDbVTsZZv\n8ooSBq9lRkBTx0xCyUPfIjuLUYNJMRdaoJZBwI259zun7IfMk19+ovjy7OcLOUhY\nZOKVWmbuIXk19B3TYtIMhwHzbUvOWCmxilPzl8dK4fQRdxc12vy0EjfVImK6W90V\n+Tyf/efZ/r267ue8LoBCw8D76gaaJotWZOuolMOqxrkjubu8tNxln2LWCGl0D+S7\nPMuMvIm5adMhtHa5oQtyqeCcAVQBTYknS7ZXkKwK9z3WTTfHJ3yNX7ERPPyfl73f\n6dU=\n-----END ENCRYPTED PRIVATE KEY-----",
                    "signPw": "ctassh0540*",
                    "userId": "",
                    "userPw": "",
                    "agentId": "",
                    "agentPw": "",
                    //"bizNo": "6082376002",
                    //"bizNo": "2245200449",
                    "bizNo": "6080554998",
                    //"inqrDtStrt": "20200101",
                    "inqrDtStrt": "20201201",
                    "inqrDtEnd": "20201217",
                    "itrfCd": "14",
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
                    "wrtQt": null,
                    "pdfYn": "Y",
                    "payPdfYn": "Y",
                    "sgPdfYn": "Y",
                    "savePath1": "C:\\Users\\Minhyeok Kim\\Desktop\\201218",
                    "savePath2": "C:\\Users\\Minhyeok Kim\\Desktop\\201218"
                }
	            */
	            {
	                "appCd": "bizbooks",
	                "orgCd": "card",
	                "svcCd": "C0007",
	                "reqCd": "202012181508100000002167",
	                "proxy": "121.126.134.151:7143",
	                "loginMethod": "ID",
	                "signCert": "",
	                "signPri": "",
	                "signPw": "",
	                "userId": "kbyoungjoon",
	                "userPw": "qudwns1547!",
	                "cardNo": "4658877523374005",
	                "sdate": "20201201",
	                "edate": "20201217",
	                "cardCd": "001",
	                "svcOption": "storeDetail"
	            }
	            
	            
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
