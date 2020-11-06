<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
	           
	           // 부가세합계표 
	           let jsonData = 
	           /*
	           {
	               "appCd": "bizbooks",
	               "orgCd": "hometax",
	               "svcCd": "Z0006",
	               "reqCd": "202010261734570000000145",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIF5TCCBM2gAwIBAgIEJgkm2DANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0xOTExMDUxNTAwMDBaFw0yMDEyMDcx\nNDU5NTlaMIGkMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjETMBEGA1UE\nCwwKeFVzZTRFc2VybzEQMA4GA1UECwwHS05CQkFOSzERMA8GA1UECwwIc2VvIHNh\nbmcxSTBHBgNVBAMMQOyEnOyDge2ZmOyEuOustO2ajOqzhOyCrOustOyGjChzZW8g\nc2FuZykwMDM5Njg2MjAxMDExMjMxMzkwMDAwMjEwggEiMA0GCSqGSIb3DQEBAQUA\nA4IBDwAwggEKAoIBAQCt2zl5H5RKEqks1My2XrjjDbbOUAODleTC07MuCPtKnrkX\nams02H27wIMjtOMIvsQ6QQqpHYspGXG1Eq2rmjKBwfD+eYGiZ6WNVuh4oWA0RIw4\nV8gbXk2QYUjgQ1gpKKhEoizkfyXhGvZry/0RXnqbOsl86AUosMnLikd1uk9Hgm5t\nkamnxbHMnj964M9Rs/65bR7/91WVREcSexkuKjqfaiRrLHv068gSJkOkmd03eIHd\nbddkV856is44uGLXpZOh5c6/NYZNEZThlq+KEkK3RuuDmE45blZNbUB1JICXBr/h\nFVEMMX124ISvoJHwLUq3/uTUIiiACVgK0asKRcBHAgMBAAGjggJuMIICajCBjwYD\nVR0jBIGHMIGEgBTv3ETSxo3ADqM4wHyTxsNBv0qP8KFopGYwZDELMAkGA1UEBhMC\nS1IxDTALBgNVBAoMBEtJU0ExLjAsBgNVBAsMJUtvcmVhIENlcnRpZmljYXRpb24g\nQXV0aG9yaXR5IENlbnRyYWwxFjAUBgNVBAMMDUtJU0EgUm9vdENBIDSCAhAcMB0G\nA1UdDgQWBBT6Si6wXQeOJi+wvnlWYCnt6w4k8DAOBgNVHQ8BAf8EBAMCBsAwegYD\nVR0gAQH/BHAwbjBsBgoqgxqMmkUBAQYIMF4wLgYIKwYBBQUHAgIwIh4gx3QAIMd4\nyZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLAYIKwYBBQUHAgEWIGh0dHA6Ly93\nd3cueWVzc2lnbi5vci5rci9jcHMuaHRtMH0GA1UdEQR2MHSgcgYJKoMajJpECgEB\noGUwYwwe7ISc7IOB7ZmY7IS466y07ZqM6rOE7IKs66y07IaMMEEwPwYKKoMajJpE\nCgEBATAxMAsGCWCGSAFlAwQCAaAiBCBhsFlu5mflAPp7QIOEUsLh4YPbI4sYolDa\n6XL+USbFIDByBgNVHR8EazBpMGegZaBjhmFsZGFwOi8vZHMueWVzc2lnbi5vci5r\ncjozODkvb3U9ZHA1cDUxNzEwLG91PUFjY3JlZGl0ZWRDQSxvPXllc3NpZ24sYz1r\ncj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0MDgGCCsGAQUFBwEBBCwwKjAoBggr\nBgEFBQcwAYYcaHR0cDovL29jc3AueWVzc2lnbi5vcmc6NDYxMjANBgkqhkiG9w0B\nAQsFAAOCAQEAVPDcxd+8bis9PeT8tZkPJsLgiXqwVa/MeD/DepXqYf9kG8gnB5jP\nTxYqz9Kene4A0nStryNkSQCUvTG74shrI/yKfnNW5zPg/wFGf43X5TFvuZuk6c0G\n7nzcJ8GC2YoAvv7v5wgUAWtq/4VDc6OARMFyVJo33HChTUoadihXR3x5+9ihh4wr\niqKJTQsbWjqGVXwHZu0wb1nxPtF2myNkAr6ZznRjNgHPUdJWs6ieo0zkjwDyR2Jy\nfh3jlIXoTboJp6eHR9YaYqx4wq5y8xgn//qK45b07Z97qCynO5xs3beLB89624T6\np71QGgYBS1k/0MtHMWRQCQmcEhv6SnnVUQ==\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIaK1K4Lyuz00CAggA\nMBwGCCqDGoyaRAEEBBBTwQJZHGZ4cNsZVQNr0IBGBIIE8J/Hjw4wL7h/JCygL0Rp\nshvsasbqMLyqp4Oeqqkf6I8+H39DALP4v+4If59BgcMfEu2dmfT3fqaCT75RxTuJ\nP9rdfNRdCS/0dpKT5jvdoKUr9d9cUuUdMJZ6XAdFoK+qQZrHZAlE7jfSWWVrnST8\nNPzjEC0tV/PGCicksNdUHRnhUqUpQbd6PI01ZYAQVRlGEVPnUGui/BhXtXvEP3lW\nP1VEvKfAYIcnhsegTq6xdL23CDJYglrP5wO897AgX5uUDl/QjRzG62jk/D5eF1dq\nygKxnaKgEOoPvcKbIZ/j238hO5mJYFdhId5yRuWaa7Gx3/uc+7yXh3vGNb8SO3Lc\nHaz1sddCh1wFNJKfDMFfrjSLDCaeZhYf9RYiusaLUlJwUarwcDn+vWFAc2WgXTHX\n+abgPXIaAKy6FCSEvr/I+kZIDfKNJcVEDAwQaQSA958BpnPU7N1ZuyoISNRA6OEB\nj326oSj+AToMBXCjA85/euwISu9pmVXWbCcyD97Bh9uRXRfZMQyFPSsc5CiC68Ln\nOw6nvlF/anSdB8pLo9c5QZHrJfvnYuFUTN/bpBaoLxzKDuhsrTZP6OGNNYnMCrNV\nmborydJ/0IUPdxE5p2UffVsDfqMZMVydisPBQ8XBLTIJEnPwcWsf+67FZSYMZioV\n8C4Y+QnlMD8xNzGaxELVDHu9pSbCc+cWLhvdX3K+bpkRP5lrnQlbYY7hIF0U6V/h\n2XtTdLjZcWvsUvQG2Te4t0AhSTygKw9HiVQLPLzy59HA+NtIQU3nTYdooj5gvy1D\n01RuGDUxVziOk7I/MPMFrIriTDXsaiHCRBVFWSnhw0dWw1QewdM/OmIvtluk8zHE\ncYl4jDUmopT0iKqQELSdV7cckIh1qW/xXdfazmgACXP2tzgebja3dFu35To1OmWl\nanwFtYiMimiBfE/ISQie+gx6hyCwwMywXzSwx2mtf2YhWNvwDUr8OD4MlQvR82Ga\njGEWN5YJZhcEJpYt0GK9u4ISUWYjr7rjTTzWbVgCBOlbUrlgG0Ji0kTpAKJbP8eq\nO9Jv62+J57ZFFLxnu4+CcvSu7OK/xLipPysqI6snoOKYhKO3WIVy5jbFonEUzDYK\n72O6lNLnQXZ0Ds5/vXn67S1zHfzcrANeHmkYsJNbKa48SblBL2U8iNCs8Zpvw1up\ny9TQc3FJnrQkVI5p6Z+26csqOsbIy+LW9FWAXhYAa4C8wfu7PzFzntoCsBiom2cY\nPRm6H8xgfGHb00tnf6lRfhZ+Vx81vKTq16ToWjDDrtA6xZgwVjQMIhxAzU4outer\n8SR8eyGQLgQhlMMLzBaNC+xFf6a8dkIhAXjmR45WP5j6e+UyPu+uUv2lI/uWd3Lr\nnEi2t9YZFev71VstwUO+Ecy27QWBeLjJNLBDIrSozZPIKYDopttQBELgTi3A6Hxp\nmlhw0h3fcBplZ0kXZ/uKEUXxWAAIYhJOMJjkwcl8EKbdV2hA4AZf/HWIEvuhdtnL\n7YLlA1dZUo5phnaekyb7+HPj5zAi1+ufwXHSi9nLfIH3TTuYXYJVlsivkeDEkmfk\ngyYyY0cqc0QgDeOwaGTxQrHt6BhzYRdbKQJP2U8Jsm7Z8+MGz8jQq7l7hKskJtLR\nI5iu8mwEE8ANjNcBp73Zmegm6F0wmCRNObkSzbvSxowz704zsA9ZH4ML4wIB469Y\n7EE=\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "ctassh0540*",
	               "userId": null,
	               "userPw": null,
	               "agentId": "",
	               "agentPw": "",
	               "bizNo": "6083260706",
	               "inqrDtStrt": "20200501",
	               "inqrDtEnd": "20200601",
	               "itrfCd": null,
	               "fromY": null,
	               "toY": null,
	               "fromQ": null,
	               "toQ": null,
	               "stlYr": null,
	               "supByr": "AL",
	               "taxGb": "",
	               "wrtYr": "2020",
	               "wrtQt": "3"
	           }
	           */
	           {
	               "appCd": "bizbooks",
	               "orgCd": "hometax",
	               "svcCd": "Z4010",
	               "reqCd": "202011061107170000000192",
	               "signCert": "-----BEGIN CERTIFICATE-----\nMIIF5DCCBMygAwIBAgIEKJ22bzANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJr\ncjEQMA4GA1UECgwHeWVzc2lnbjEVMBMGA1UECwwMQWNjcmVkaXRlZENBMRowGAYD\nVQQDDBF5ZXNzaWduQ0EgQ2xhc3MgMjAeFw0yMDA5MDgxNTAwMDBaFw0yMTA5MTMx\nNDU5NTlaMIGnMQswCQYDVQQGEwJrcjEQMA4GA1UECgwHeWVzc2lnbjEUMBIGA1UE\nCwwLY29ycG9yYXRpb24xDDAKBgNVBAsMA0tNQjEZMBcGA1UECwwQVGF4ZmlybSBX\nSVRIUExVUzFHMEUGA1UEAww+7IS466y067KV7J247JyE65Oc7ZSM65+s7IqkKFRh\neGZpcm0gV0lUSFBMVVMpMDAwNDAyM0IwMDQ2OTM1NDQwggEiMA0GCSqGSIb3DQEB\nAQUAA4IBDwAwggEKAoIBAQDHKiqgkuiDBVeriNGmllAjemdIlIVbuhZN4xAeWsTx\nVh6SZJFPcT0gaIjkQaHdxxsDI/CwYtCxmJpxfNkSm3gqfCUHSIMtWScfArQzT0m8\nc+GtTtACxlZmetOCK/2EcB7toPf/qWNuRP85VdbXO/yoeXxIguOL13MSHKbUDbDh\nxfgOc1td6KNBUpturHidf3WzR5kcQW2JPm11h2SA/EjZHsAD5AGQkmSj/f93gXVc\ncPVlm1BFq9pZbjz5IPO7xlPKEUj/K+k3K7TXQU6YSxYjmQ5bnFF/05W6uWy8yjoN\n5ughuNHSzeqmPRKwdmNTwFSdwhSbh0xjujZpdaJmNFzPAgMBAAGjggJqMIICZjCB\njwYDVR0jBIGHMIGEgBTv3ETSxo3ADqM4wHyTxsNBv0qP8KFopGYwZDELMAkGA1UE\nBhMCS1IxDTALBgNVBAoMBEtJU0ExLjAsBgNVBAsMJUtvcmVhIENlcnRpZmljYXRp\nb24gQXV0aG9yaXR5IENlbnRyYWwxFjAUBgNVBAMMDUtJU0EgUm9vdENBIDSCAhAc\nMB0GA1UdDgQWBBS/nEg1GZWou8CUdbTg9fXA+r1o2DAOBgNVHQ8BAf8EBAMCBsAw\neQYDVR0gAQH/BG8wbTBrBgkqgxqMmkUBAQIwXjAuBggrBgEFBQcCAjAiHiDHdAAg\nx3jJncEcspQAIKz1x3jHeMmdwRwAIMeFssiy5DAsBggrBgEFBQcCARYgaHR0cDov\nL3d3dy55ZXNzaWduLm9yLmtyL2Nwcy5odG0wegYDVR0RBHMwcaBvBgkqgxqMmkQK\nAQGgYjBgDBvshLjrrLTrspXsnbjsnITrk5ztlIzrn6zsiqQwQTA/BgoqgxqMmkQK\nAQEBMDEwCwYJYIZIAWUDBAIBoCIEIBx+O75LSsIJxn3aPe6oXOulLq8n198vZH8/\nF7cws612MHIGA1UdHwRrMGkwZ6BloGOGYWxkYXA6Ly9kcy55ZXNzaWduLm9yLmty\nOjM4OS9vdT1kcDVwNjYxNDAsb3U9QWNjcmVkaXRlZENBLG89eWVzc2lnbixjPWty\nP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3QwOAYIKwYBBQUHAQEELDAqMCgGCCsG\nAQUFBzABhhxodHRwOi8vb2NzcC55ZXNzaWduLm9yZzo0NjEyMA0GCSqGSIb3DQEB\nCwUAA4IBAQBECnVYm+6i9wqFUkvlisONDcJ5AjmoJEnLsvwhmCYrvVhJfPvVu5A1\nP1tu8vSKn031yV9VJzfIOMqsMyE+K0oupZxg0K5MeKROtMuz+2TfMFGoKt0zzeHl\nNhxX78BZMWtpoBDSpDdkJvFJ41By1ay0URcvnI4zVjWuGTaf8acc1FabhMYc8ENN\n1H/6b33K7YVTrLPejJNCTMzOR55oJMlkYCFu75JL+K+EERoA9tnaTRDztbF9eq41\nQYA98Liij8jTQPmCDhm2zq+XSkCNAr2khGobcHj4k1QuDUv6hEalBh+WxBQZptLO\n/eaQ6FRgqkLa6paDG3p/pqYg+jPY4SEU\n-----END CERTIFICATE-----",
	               "signPri": "-----BEGIN ENCRYPTED PRIVATE KEY-----\nMIIFPjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQItr1fwdZko14CAggA\nMBwGCCqDGoyaRAEEBBDmV7fTQ8FwVJwYZWyE4fQMBIIE8IuZlQx2IGFPcJWSjrna\nVYMm3i859jJi9/DoiNk+/31NeSa/WmR4h+6SUTT7AJDH3Q12LBh7fNC/PvujPT2R\n3nOvAcSefyeqnujp11qppJs78312abBSmcb8Wb0Ui1Bn0hq7UEIlVe4o7d/y6Sen\nvMOprXAbJ6CWQSYhmevXZx7M6SNW7liNV9aCALNGaPc2qILr+kIwwV67nu6ekf/T\nt51J6wBg+pp4phUZmXBxkjMuvTi1ck4NnJ9t2+Lrd3LwMiuNX7h6E/NncliefTlB\nq5TdjPA8qfoFFQUdha09XRGy3IbCWPik0YufKtUeMbSbnEAAsqTTeZWb2Gb6bcoR\nlTwUipzqecbVAP0EOw4c811BE9Ddmb+OYcri9ShI9dH0lPEPHMIE2KeCvYgrXzDI\nhs7zPkI8cTuiIbnNRTTseLe3g7h1gbSzPHQBkqbsi4PykZ5cWIPAA/jVNqyugBKz\ncr/j5SE4vbkYCG8Eu//6zjyRwtmLmZBPG4Q1rhTNYETEpF49pvvDl9vein4gjlzD\n8KbQYvg9J1ghRU5MCMZQyv5rRoZPXkyIXQsGoHBpdEv+MFvfW/Hbq2Ww7499UVao\nWiTMIEn/NaC4rYHCxffoIqu6x7xopWfZ+fQ06vC/hWcSOuNhwvwJ5ZhrxE25eRP9\nUIAeEQsqFd8Yu5ftpzxvWxGml1xka+spvku3aoLKcniyc4Rt6o8ZK2+IPBJwPbEe\ndITCUoXYWEY5UElnSV4zfEkysT8Twlull8/PsX79s3JqMTEEyZHScDqKBOyvitaU\n5uFuHBcyiv+S1cc8MpDTmi+vgquF1nW1wgycfvt/mQNtCoFZffjkkmHqYDekhmwR\nmVgzdCgP7ilrCYiLR0D56ZQT6TAdGjK4l8Jk5ycvBEoeMTe9uaBQUS6YGc8+J57L\nKY9Xi9CpEgN2QlYDeHPQg7Kf40p5y9gAvmVaE8qqDAQu5ZN6+qCTcyTMSbrt0Tcf\nyH+EKHKEeGpy6jziJ570oqRJLgBtnYJQ16h770Ge7etMp56MDQaLOGt+/71H1ej2\nAaZA+VkP+tnByUs/Z+p7rpqIN7z3N+EJXdAKvvijyYxpuWBS2coW362TZuO09Btt\nVAqo8pmGx6VGsiUxyrp9NkVQFpGfccA45qjHoiRA+qgwv+uNa4zRPuKlr5N6ciYQ\nFOt70doeWZCnXLOfcAarbxEl707QRG/VxDVlL5h7+ntOd7lesCJ4uxcsraKqLZae\niZbSgNFmZNIDRHmP7KQqYKYAG9lDbhw16ZriY5/voy9AP61IVFnkqsaq9kQC7ti5\noYh9nsB29i7IpRaPocBwfRvh92sUHLblvyF+Una4iU7UxwNlykVDWdF+OJpzEy7w\nDivDyqDlqtnoI+16QCmXzyVWJR+QN1XSaS0P25R5KL6bCh5XunZtWNbWTT9OL5rh\n5RcCi7utnw7BGkD2ogHDRbvVL7XjI+GvO1xTyG4yyxokOcsXtljTrlCS4TTf/ZUt\nQYitpeJrCTj6eK/C2gnnW0PzPHVaKUwwjpiFiVXmCVGV+NNUQao8lSJd9dD9ypD7\nBkiXzFfSDoEsEW5Gg2lj75vt1R1tTVbFfGWwfvr9U5XNJj2pZm38Yl/gHvgE8jcA\nuCR176atPBI0n4SpsrFNZxxzFSK4ZvNx6lCWz5yh2moNJhDD7/fF+3xJQs2ieoo0\ns30=\n-----END ENCRYPTED PRIVATE KEY-----",
	               "signPw": "withplus16@@",
	               "userId": "",
	               "userPw": "",
	               "agentId": "w24167",
	               "agentPw": "itax3300",
	               "bizNo": "7738800144",
	               "inqrDtStrt": "20200101",
	               "inqrDtEnd": "20201105",
	               "itrfCd": null,
	               "fromY": null,
	               "toY": null,
	               "fromQ": null,
	               "toQ": null,
	               "stlYr": null,
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
</body>
</html>
