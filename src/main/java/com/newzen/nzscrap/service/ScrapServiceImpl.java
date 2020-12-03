package com.newzen.nzscrap.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newzen.nzscrap.mapper.ScrapMapper;
import com.newzen.nzscrap.model.dto.HistoryReq;
import com.newzen.nzscrap.model.dto.HistoryRes;
import com.newzen.nzscrap.model.dto.InJsonBank;
import com.newzen.nzscrap.model.dto.InJsonCard;
import com.newzen.nzscrap.model.dto.InJsonCardsales;
import com.newzen.nzscrap.model.dto.InJsonHometax;
import com.newzen.nzscrap.model.dto.SBankB0002;
import com.newzen.nzscrap.model.dto.SCardC0005;
import com.newzen.nzscrap.model.dto.SCardsalesB0002;
import com.newzen.nzscrap.model.dto.SCardsalesB0011Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0011Sum;
import com.newzen.nzscrap.model.dto.SCardsalesB0021Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0021Sum;
import com.newzen.nzscrap.model.dto.SCardsalesB0031Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0031Sum;
import com.newzen.nzscrap.model.dto.SCbkB0002;
import com.newzen.nzscrap.model.dto.SCcdC0005;
import com.newzen.nzscrap.model.dto.SHometaxZ0006;
import com.newzen.nzscrap.model.dto.SHometaxZ0006Dtl;
import com.newzen.nzscrap.model.dto.SHometaxZ300X;
import com.newzen.nzscrap.model.dto.SHometaxZ300XItem;
import com.newzen.nzscrap.model.dto.SHometaxZ4001;
import com.newzen.nzscrap.model.dto.SHometaxZ4002;
import com.newzen.nzscrap.model.dto.SHometaxZ4010;
import com.newzen.nzscrap.model.dto.SHometaxZ4020;
import com.newzen.nzscrap.model.dto.SHometaxZ4050;
import com.newzen.nzscrap.model.dto.SHometaxZ4060;
import com.newzen.nzscrap.model.dto.SHometaxZ4070;
import com.newzen.nzscrap.model.dto.SHometaxZ5002;
import com.newzen.nzscrap.model.dto.SSbkB0002;
import com.newzen.nzscrap.model.dto.ServerScrapReqParam;
import com.newzen.nzscrap.model.form.ScrapCertForm;
import com.newzen.nzscrap.util.AES256Util;
import com.newzen.nzscrap.util.DateUtil;
import com.newzen.nzscrap.util.SftpUtil;

/**
 * @author Minhyeok Kim
 *
 */
@Service
public class ScrapServiceImpl implements ScrapService {
	@Autowired 
	ProxyService proxyService;
	
	//private static final String BIZBOOKS_URL = "https://bizbooks.newzensolution.co.kr/bizbooks";
	private static final String BIZBOOKS_URL = "http://192.168.11.49:8090/bizbooks/";
	
	//private static final String BIZBOOKS_URL = "https://bizbooks.newzensolution.co.kr/bizbooks_test";
	
	private static final int DATA_DIVISION_LEN = 50;   // 데이터 분할전송량
	private static final int DELAY_MS = 100;			// 파일 전송 시차
	
	private static String FIRST_REQ_CD = "0000";	// 처음 요청 코드 값 (일괄 스크래핑시 사용)
	
	@Autowired
	ScrapMapper scrapMapper;

	@Override
	public String scrap() {
		try {
			// 프록시 내용 초기화
			proxyService.init();
			
			HashMap<String, String> scrapListMap = getBizbooksScrapSchedule("20201101", "20201130");
			
			long sTime = System.currentTimeMillis();
			ArrayList<String> timeList = new ArrayList<>(); 

			for(Map.Entry<String, String> entry : scrapListMap.entrySet()) {
				String compCd = entry.getKey();
				String jsonReqParam = entry.getValue();
				
				/*
				if(compCd.equals("D000000273") || compCd.equals("D00102") || compCd.equals("D00105") || compCd.equals("D00113") 
					|| compCd.equals("D00114") || compCd.equals("D00117") || compCd.equals("D00131") || compCd.equals("D00156") 
					|| compCd.equals("D00161") || compCd.equals("D00162") || compCd.equals("D00166") || compCd.equals("D00174") 
					|| compCd.equals("D00180") || compCd.equals("D00183") || compCd.equals("D00185") || compCd.equals("D00195")) {
			    */
				//if(compCd.equals("D00113") || compCd.equals("D00117") || compCd.equals("D00156") || compCd.equals("D00183") || compCd.equals("D00195")) {
				
				//if(compCd.equals("D00156") || compCd.equals("D00026")) {
				if(compCd.equals("D00102")) {
					System.out.println(compCd);
					
					long ssTime = System.currentTimeMillis();
					
					scrapByCompCd(compCd, jsonReqParam);
					//scrapByCompCd_old(compCd, jsonReqParam);
					
					long eeTime = System.currentTimeMillis();
					timeList.add("# comp time : " + compCd + " // " + (eeTime - ssTime));
				}
			}
			
			long eTime = System.currentTimeMillis();
			System.out.println("## total time : " + (eTime - sTime));
			
			for(String s : timeList) {
				System.out.println(s);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	/**
	 * scrapByCompCd_old (TODO: Deprecated)
	 * - 회사코드 별로 스크래핑업무를 수행합니다. (요청 일괄처리)
	 * 
	 * @param compCd
	 * @param jsonReqParamList
	 * 
	 * @throws IOException
	 */
	private void scrapByCompCd_old(String compCd, String jsonReqParamList) throws IOException { 
		// #1. inJson List 생성
		String inJsonList = makeInJsonList_old(jsonReqParamList);
		
		// #2. 스크랩 전송
		String jsonScrapData = sendScrap(inJsonList, FIRST_REQ_CD);
		
		// #3. 결과값 + ReqParamList로 ResParamList 생성
		HashMap<String, Object> hmScrapData = makeScrapData(jsonScrapData, jsonReqParamList);
		String resParamList = hmScrapData.get("resParamList").toString();
		
		// #4. OutJsonList 가져옴
		String outJsonList = hmScrapData.get("outJsonList").toString();
		
		// #5. outJsonList에서 insert할 data 추출
		HashMap<String, Object> insertDataMap = makeInsertData(outJsonList, resParamList);
		
		// #6. History insert
		insertHistory(jsonReqParamList, resParamList);
		
		// #7. data insert
		insertScrapData(insertDataMap);
		
		// #8. 비즈북스 데이터 전송
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		sendDataToBizbooks(compCd, today);
	}
	
	
	/**
	 * scrapByCompCd
	 * 
	 * 수임업체 별 스크래핑을 수행합니다.
	 * 처리 순서는 인증정보 검증 -> 요청별 스크래핑 처리 -> DB저장 -> 비즈북스 전송 순서로 진행합니다.
	 * 
	 * - 인증정보 리스트 생성
	 * - 인증정보 포함한 요청정보 리스트 생성
	 * - 인증 실패한 내역 히스토리 생성 후 전송
	 * - 요청별 스크래핑 처리
	 *   > 요청 inJson 생성
	 *   > 요청내용 스크래핑 전송
	 *   > 결과 내용(metadata)으로 스크래핑 데이터 생성
	 * - 스크래핑 히스토리 작업
	 *   > 히스토리 요청내용 생성
	 *   > 히스토리 응답내용 생성
	 *   > 히스토리 DB처리
	 *   > 히스토리 비즈북스 전송
	 * - 스크래핑 데이터 작업
	 *   > 데이터 생성
	 *   > 데이터 DB처리
	 *   > 데이터 비즈북스 전송
	 * @param compCd
	 * @param jsonReqParamList
	 */
	@SuppressWarnings("unchecked")
	private void scrapByCompCd(String compCd, String jsonReqParamList) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			// #1. 인증정보 리스트 생성
			ArrayList<ScrapCertForm> scrapCertFormList = makeScrapCertFormList(jsonReqParamList);
			
			// #2. 인증정보 포함한 요청정보 리스트 생성
			ArrayList<ServerScrapReqParam> reqParamList = makeReqParamList(scrapCertFormList, jsonReqParamList);
			
			// #3. 인증 실패한 내역 히스토리 생성 후 전송
			sendCertFailedHistoryList(scrapCertFormList, reqParamList);
			
			// #4. 요청별 스크래핑 처리
			for(ServerScrapReqParam reqParam : reqParamList) {
				// - 요청 inJson 생성
				String inJson = makeInJson(reqParam);
				
				if(inJson.isEmpty() == false) {
					if(reqParam.getSvcCd().equals("Z0006")) {
						int tmp = 0;
					}
					// - 요청내용 스크래핑 전송
					String jsonScrapData = sendScrap(inJson, FIRST_REQ_CD);
					
					// - 결과 내용(meta data)으로 스크래핑 데이터 생성
					HashMap<String, Object> resParam = objectMapper.readValue(jsonScrapData, HashMap.class);
					ArrayList<HashMap<String, Object>> outJsonList = 
							(ArrayList<HashMap<String, Object>>) resParam.get("outJsonList");
					
					HashMap<String, Object> outJson = outJsonList.get(0);
					
					// #5. 스크래핑 히스토리 작업
					// - 히스토리 요청내용 생성
					HistoryReq historyReq = new HistoryReq(reqParam);
					
					// - 히스토리 응답내용 생성
					resParam.put("errYn", outJson.get("errYn"));
					resParam.put("errMsg", outJson.get("errMsg"));
					resParam.put("connCnt", outJson.get("connCnt"));
					resParam.put("outTime", outJson.get("outTime"));
					HistoryRes historyRes = new HistoryRes(reqParam, resParam);
					
					// - 히스토리 DB처리
					if(historyReq != null) {
						scrapMapper.insertHistoryReq(historyReq);
					}
					
					if(historyRes != null) {
						scrapMapper.insertHistoryRes(historyRes);
					}
					
					// - 히스토리 비즈북스 전송
					HashMap<String, Object> historyListMap = new HashMap<>();
					historyListMap.put("historyReq", historyReq);
					historyListMap.put("historyRes", historyRes);
					
					sendDataToBizbooks(historyListMap);
					
					// #6. 스크래핑 데이터 작업
					// - 데이터 생성
					HashMap<String, Object> insertDataMap = makeInsertData_test(outJson, reqParam);
			
					// - 데이터 DB처리
					insertScrapData(insertDataMap);
					
					// - 데이터 비즈북스 전송
					sendDataToBizbooks(insertDataMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * makeScrapCertFormList
	 * 
	 * 전체 스크래핑 요청내역으로  이 후 스크래핑에 사용할 인증정보 리스트를 생성합니다.
	 * - 인증정보 중복제거
	 * - 유효성검증용 테스트 스크래핑 inJson 생성
	 * - 테스트 스크래핑 -> 인증 결과 historyRes 적용
	 * - 인증 오류내용 비즈북스 전송 -> 해당 항목 스크랩 사용여부 비활성화 처리
	 * 
	 * @param jsonReqParamList - 전체 스크래핑 요청내역 json
	 * @return
	 * @throws IOException: TODO: 테스트 완료 후 Throw
	 */
	private ArrayList<ScrapCertForm> makeScrapCertFormList(String jsonReqParamList) throws IOException {
		ArrayList<ScrapCertForm> scrapCertFormList = new ArrayList<>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		SftpUtil sftpUtil = new SftpUtil();
		
		ArrayList<ServerScrapReqParam> reqParamList = 
				objectMapper.reader()
				.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
				.readValue(jsonReqParamList);
		
		// #1. 인증정보 중복제거
		// - 홈택스 (certMap -> certList 순으로 적재)
		//  > hometaxZ300X - 세무대리 인증서, 본인 인증서
		//  > hometaxZ40XX - 본인 인증서, 본인 id
		//  > hometaxZXXXX - 세무대리 인증서
		
		// - 여신  (certMap -> certList 순으로 적재)
		//  > cardsalesBXXXX - 본인 id
		
		// - 카드 (certList 적재)
		//  > creditCcd		- LoginType 비교 (1: 본인 id, 2: 본인 인증서) 
		//  > creditCard 	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		
		// - 통장 (certList 적재)
		//  > accountCbk	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		//  > accountBank  	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		//  > accountSbk   	- LoginType 비교 (3: 빠른조회)
		ArrayList<ServerScrapReqParam> certList = new ArrayList<>();
		HashMap<String, ServerScrapReqParam> certMap = new HashMap<>();
		HashMap<String, ServerScrapReqParam> creditMap = new HashMap<>();
		HashMap<String, ServerScrapReqParam> accountMap = new HashMap<>();
		
		ArrayList<ServerScrapReqParam> hometaxReqParamList = new ArrayList<>();
		ArrayList<ServerScrapReqParam> cardsalesReqParamList = new ArrayList<>();
		ArrayList<ServerScrapReqParam> creditReqParamList = new ArrayList<>();
		ArrayList<ServerScrapReqParam> accountReqParamList = new ArrayList<>();
		String signCert = "";
		String signPri = "";
		String loginId = "";
		String loginPw = "";
		
		for(ServerScrapReqParam reqParam : reqParamList) {
			switch (reqParam.getOrgCd()) {
			case "hometax":
				hometaxReqParamList.add(reqParam);
				break;
			case "cardsales":
				cardsalesReqParamList.add(reqParam);
				break;
			case "ccd":
			case "card":
				creditReqParamList.add(reqParam);
				break;
			case "cbk":
			case "bank":
			case "sbk":
				accountReqParamList.add(reqParam);
				break;
			}
		}
		
		// - 홈택스
		//  > Z300X - 세무대리 인증서, 본인 인증서
		//  > Z40XX - 본인 인증서, 본인 id
		//  > ZXXXX - 세무대리 인증서
		ServerScrapReqParam reqParamTmp;
		
		String svcCd = "";
		int loginType = 0;	// 1:id, 2:인증서
		
		for(ServerScrapReqParam reqParam : hometaxReqParamList) {
			signCert = reqParam.getSignCert();
			signPri = reqParam.getSignPri();
			loginId = reqParam.getLoginId();
			loginPw = reqParam.getLoginPw();
			
			switch(reqParam.getSvcCd()) {
			case "Z3001":
			case "Z3002":
			case "Z3003":
			case "Z3004":
				svcCd = "hometaxZ300X";
				loginType = 2;
				break;
			case "Z4001":
			case "Z4002":	
			case "Z4010":
			case "Z4020":
				svcCd = "hometaxZ40XX";
				// 본인 id 있는 경우
				if(loginId != null && loginId.length() > 0 && loginPw != null && loginPw.length() > 0) {
					loginType = 1;
				}
				// 본인 인증서 있는 경우
				if(signCert != null && signCert.length() > 0 && signPri != null && signPri.length() > 0) {
					loginType = 2;
				} 
				break;
			default:
				svcCd = "hometaxZXXXX";
				loginType = 2;
				break;
			}
			
			reqParam.setSvcCd(svcCd); // 현재 서비스 코드 추가 -> 각 서비스별 1회만 스크래핑에 사용 (홈택스, 여신 only)
			
			
			reqParamTmp = certMap.get(svcCd);
			
			switch(loginType) {
			case 1:	// id
				if(loginId != null && loginId.length() > 0 && loginPw != null && loginPw.length() > 0) { 
					certMap.put(svcCd, reqParam);
				}
				break;
			case 2: // 인증서 
				if(reqParamTmp == null && signCert != null && signCert.length() > 0 && signPri != null && signPri.length() > 0) {
					reqParam.setSignCert(sftpUtil.getFileData(signCert, "signCert.der"));
					reqParam.setSignPri(sftpUtil.getFileData(signPri, "signPri.key"));
					
					certMap.put(svcCd, reqParam);
				}
			}
		}
		
		// - 여신 
		//  > cardsalesBXXXX - 본인 id
		for(ServerScrapReqParam reqParam : cardsalesReqParamList) {
			loginId = reqParam.getLoginId();
			loginPw = reqParam.getLoginPw();
			svcCd = "cardsalesBXXXX";
			
			reqParam.setSvcCd(svcCd); // 서비스 코드 추가 -> 각 서비스별 1회만 스크래핑에 사용 (홈택스, 여신 only)
			
			reqParamTmp = certMap.get(svcCd);
			
			if(reqParamTmp == null && loginId != null && loginId.length() > 0 && loginPw != null && loginPw.length() > 0) {
				certMap.put(svcCd, reqParam);
			}
		}
		
		// 홈택스, 여신스크랩 내용 적용
		for(Entry<String, ServerScrapReqParam> e : certMap.entrySet()) {
			certList.add(e.getValue());
		}
		
		
		// - 카드 
		//  개인, 법인만 구분하여 스크래핑 리스트에 포함합니다.
		//  동일 인증서 file 사용하는 경우 -> 한번만 복호화 처리 -> overwrite 처리순으로 진행됩니다.
		//  > creditCcd		- LoginType 비교 (1: 본인 id, 2: 본인 인증서) 
		//  > creditCard 	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		for(ServerScrapReqParam reqParam : creditReqParamList) {
			signCert = reqParam.getSignCert();
			signPri = reqParam.getSignPri();
			loginId = reqParam.getLoginId();
			loginPw = reqParam.getLoginPw();
			
			switch(reqParam.getOrgCd()) {
			case "ccd":
				svcCd = "creditCcd";
				break;
			case "card":
				svcCd = "creditCard";
				break;
			}
			
			reqParam.setSvcCd(svcCd);	// 개인, 법인 구분 서비스 코드 추가 
			
			switch(reqParam.getLogInType()) {
			case 1:	// id
				// 인증 종류별 필요한 인증내역 제외하고 공란처리 (인증서 정보 제거)				
				reqParam.setSignCert("");
				reqParam.setSignPri("");
				reqParam.setSignPw("");
				break;
			case 2: // 인증서
				// 인증 종류별 필요한 인증내역 제외하고 공란처리 (id 정보 제거)				
				reqParam.setLoginId("");
				reqParam.setLoginPw("");
				
				// 동일 인증서 file 사용하는 경우 -> 한번만 복호화 처리 -> overwrite 처리
				reqParamTmp = creditMap.get(signCert);
				
				if(reqParamTmp == null) {
					if(signCert != null && signCert.length() > 0 && signPri != null && signPri.length() > 0) {
						reqParam.setSignCert(sftpUtil.getFileData(signCert, "signCert.der"));
						reqParam.setSignPri(sftpUtil.getFileData(signPri, "signPri.key"));
						
						creditMap.put(signCert, reqParam);
					}
				} else {
					reqParam.setSignCert(reqParamTmp.getSignCert());
					reqParam.setSignPri(reqParamTmp.getSignPri());
				}
				break;
			}
			
			certList.add(reqParam);
		}
		
		// - 통장 (certList 적재)
		//  개인, 법인만 구분하여 스크래핑 리스트에 포함합니다.
		//  동일 인증서 file 사용하는 경우 -> 한번만 복호화 처리 -> overwrite 처리순으로 진행됩니다.
		//  > accountCbk	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		//  > accountBank  	- LoginType 비교 (1: 본인 id, 2: 본인 인증서)
		//  > accountSbk   	- LoginType 비교 (3: 빠른조회)
		for(ServerScrapReqParam reqParam : accountReqParamList) {
			signCert = reqParam.getSignCert();
			signPri = reqParam.getSignPri();
			loginId = reqParam.getLoginId();
			loginPw = reqParam.getLoginPw();
			
			switch(reqParam.getOrgCd()) {
			case "bank":
				svcCd = "accountBank";
				break;
			case "cbk":
				svcCd = "accountCbk";
				break;
			case "sbk":
				svcCd = "accountSbk";
			}
			
			reqParam.setSvcCd(svcCd);	// 개인, 법인 구분 서비스 코드 추가 
			
			switch(reqParam.getLogInType()) {
			case 1:	// id
				// 인증 종류별 필요한 인증내역 제외하고 공란처리 (인증서 / 빠른조회 정보 제거)				
				reqParam.setSignCert("");
				reqParam.setSignPri("");
				reqParam.setSignPw("");
				reqParam.setAcctPw("");
				break;
			case 2:	// 인증서
				// 인증 종류별 필요한 인증내역 제외하고 공란처리 (id / 빠른조회 정보 제거)				
				reqParam.setLoginId("");
				reqParam.setLoginPw("");
				reqParam.setAcctPw("");
				
				// 동일 인증서 file 사용하는 경우 -> 한번만 복호화 처리 -> overwrite 처리
				reqParamTmp = accountMap.get(signCert);
				
				if(reqParamTmp == null) {
					if(signCert != null && signCert.length() > 0 && signPri != null && signPri.length() > 0) {
						reqParam.setSignCert(sftpUtil.getFileData(signCert, "signCert.der"));
						reqParam.setSignPri(sftpUtil.getFileData(signPri, "signPri.key"));
						
						accountMap.put(signCert, reqParam);
					}
				} else {
					reqParam.setSignCert(reqParamTmp.getSignCert());
					reqParam.setSignPri(reqParamTmp.getSignPri());
				}
				
				break;
			case 3:	// 빠른조회
				// 인증 종류별 필요한 인증내역 제외하고 공란처리 (인증서 / id 정보 제거)				
				reqParam.setSignCert("");
				reqParam.setSignPri("");
				reqParam.setSignPw("");
				reqParam.setLoginId("");
				reqParam.setLoginPw("");
				break;
			}
			
			certList.add(reqParam);
		}

		for(ServerScrapReqParam reqParam : certList) {
			ScrapCertForm scrapCertForm = new ScrapCertForm();
			scrapCertForm.setSvcCd(reqParam.getSvcCd());
			scrapCertForm.setServerScrapReqParam(reqParam);
			scrapCertFormList.add(scrapCertForm);
		}

		//#2. 유효성검증용 테스트 스크랩 inJson 생성
		setScrapCertFormInJson(scrapCertFormList);
		
		//#3. 테스트 스크래핑 -> 인증 결과 historyRes 적용
		setScrapCertFormHistoryRes(scrapCertFormList);
		
		//#4. 인증 오류내용 비즈북스 전송 -> 해당 사항 스크랩 사용여부 비활성화 처리
		sendScrapErrListToBizbooks(scrapCertFormList);
		
		return scrapCertFormList;
	}
	
	
	/**
	 * setScrapCertFormInJson
	 * 
	 * 인증정보 리스트에 인증정보 유효성검증용 테스트스크랩을 수행할 inJson 내용을 추가합니다.
	 *  - 테스트 스크래핑은 1일치만 수행하도록 저장 (inJson내 조회기간)
	 *  - 이전 스크래핑 일자는 historyReq field에 저장  
	 *  
	 * @param scrapCertFormList - 인증정보 리스트
	 */
	private void setScrapCertFormInJson(ArrayList<ScrapCertForm> scrapCertFormList) {
		ObjectMapper objectMapper = new ObjectMapper();

		String today = DateUtil.getDate().replaceAll("-", ""); 
		
		for(ScrapCertForm scrapCertForm : scrapCertFormList) {
			String svcCd = scrapCertForm.getSvcCd();
			ServerScrapReqParam reqParam = scrapCertForm.getServerScrapReqParam();

			// 스크래핑 요청 정보 초기화
			// - 조회기간 -> 서비스 별 스크래핑 조회기간으로 설정
			HistoryReq historyReq = new HistoryReq();
			historyReq.setScrapFromDt(reqParam.getFromDt());
			historyReq.setScrapToDt(reqParam.getToDt());
			scrapCertForm.setHistoryReq(historyReq);
			
			String appCd = reqParam.getAppCd();
			String orgCd = reqParam.getOrgCd();
			
			String signCert = reqParam.getSignCert();
			String signPri = reqParam.getSignPri();
			String signPw = reqParam.getSignPw();
			String reqCd = reqParam.getReqCd();
			
			// 인증 조회기간 -> 금일로 설정
			reqParam.setFromDt(today);
			reqParam.setToDt(today);
			
			String proxy = proxyService.getNextAddr();	// 각 서비스 호출시마다 다른 proxy 주소로 전송
			
			ArrayList<String> inJsonList = new ArrayList<>();
			
			try {
				switch(orgCd) {
				case "hometax":	// 홈택스
					InJsonHometax inJsonHometax = new InJsonHometax(appCd, orgCd, svcCd, reqCd, proxy);
					inJsonHometax.setSignCert(signCert);
					inJsonHometax.setSignPri(signPri);
					inJsonHometax.setSignPw(signPw);
					inJsonHometax.setUserId(reqParam.getLoginId());
					inJsonHometax.setUserPw(reqParam.getLoginPw());
					inJsonHometax.setBizNo(reqParam.getBizNo());
					inJsonHometax.setInqrDtStrt(reqParam.getFromDt());
					inJsonHometax.setInqrDtEnd(reqParam.getToDt());
					inJsonHometax.setAgentId(reqParam.getAgentId());
					inJsonHometax.setAgentPw(reqParam.getAgentPw());				
					
					switch(svcCd) {
					case "hometaxZ300X": // 전자세금계산서
						inJsonHometax.setSvcCd("Z3001"); // 세금계산서발행내역(매출) 
						
						// 인증서 제외 기타 인증내용 제거
						inJsonHometax.setAgentId("");
						inJsonHometax.setAgentPw("");
						inJsonHometax.setUserId("");
						inJsonHometax.setUserPw("");
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonHometax));
						break;
					case "hometaxZ40XX": // 현금영수증, 사업용(화물복지)신용카드 
						inJsonHometax.setSvcCd("Z4001"); // 현금영수증(매출)
						
						// 등록된 홈택스 id가 있는 경우
						if(inJsonHometax.getUserId() != null 
							&& inJsonHometax.getUserId().isEmpty() == false) {
							// id 제외 기타 인증내용 제거
							inJsonHometax.setSignCert("");
							inJsonHometax.setSignPri("");
							inJsonHometax.setSignPw("");
						} else {
							// 인증서 제외 기타 인증내용 제거
							inJsonHometax.setUserId("");
							inJsonHometax.setUserPw("");
							
							// 인증서 없는 경우 인증내용 공란으로 설정
							if(inJsonHometax.getSignCert() == null || inJsonHometax.getSignCert().isEmpty()) {
								inJsonHometax.setSignCert("");
								inJsonHometax.setSignPri("");
								inJsonHometax.setSignPw("");
							}
						}
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonHometax));
						break;
					case "hometaxZXXXX": // 기타 홈택스 스크래핑
						inJsonHometax.setSvcCd("Z0000"); // 회원정보
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonHometax));
						break;
					}
					break;
					
				// 여신
				case "cardsales":
					switch(svcCd) {
					case "cardsalesBXXXX":	// 여신_스크래핑
						svcCd = "B0001"; // 가맹점 정보조회
						
						InJsonCardsales inJsonCardsales = new InJsonCardsales(appCd, orgCd, svcCd, reqCd, proxy);
						
						inJsonCardsales.setUserId(reqParam.getLoginId());
						inJsonCardsales.setUserPw(reqParam.getLoginPw());
						inJsonCardsales.setFromDate(reqParam.getFromDt());
						inJsonCardsales.setToDate(reqParam.getToDt());
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonCardsales));
						break;
					}
					break;
				
				// 카드
				case "card":
				case "ccd":
					switch(svcCd) {
					case "creditCcd":		// 카드_법인카드
					case "creditCard":		// 카드_개인카드
						svcCd = "C0005"; // 승인내역조회
						
						InJsonCard inJsonCard = new InJsonCard(appCd, orgCd, svcCd, reqCd, proxy);
						
						switch(reqParam.getLogInType()) {
						case 1:	// ID 로그인
							inJsonCard.setLoginMethod("ID");
							inJsonCard.setUserId(reqParam.getLoginId());
							inJsonCard.setUserPw(reqParam.getLoginPw());
							// - 기타 인증내용 제거
							inJsonCard.setSignCert("");
							inJsonCard.setSignPri("");
							inJsonCard.setSignPw("");
							break;
						case 2:	// 인증서 로그인
							inJsonCard.setLoginMethod("CERT");
							inJsonCard.setSignCert(signCert);
							inJsonCard.setSignPri(signPri);
							inJsonCard.setSignPw(signPw);
							// - 기타 인증내용 제거
							inJsonCard.setUserId("");
							inJsonCard.setUserPw("");
							break;
						}
						
						inJsonCard.setCardNo(reqParam.getCardNo());
						inJsonCard.setSdate(reqParam.getFromDt());
						inJsonCard.setEdate(reqParam.getToDt());
						
						// 카드사코드 '0' padding
						String cardCd = String.format("%03d", Integer.parseInt(reqParam.getCardCd()));
						inJsonCard.setCardCd(cardCd);
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonCard));
						break;
					}
					break;
					
				case "bank":
				case "cbk":
				case "sbk":
					switch(svcCd) {
					case "accountCbk":		// 통장_법인통장
					case "accountBank":		// 통장_개인통장
					case "accountSbk":		// 통장_빠른조회
						svcCd = "B0002"; // 거래내역조회
						
						InJsonBank inJsonBank = new InJsonBank(appCd, orgCd, svcCd, reqCd, proxy);
						
						switch(reqParam.getLogInType()) {
						case 1:	// ID 로그인
							inJsonBank.setLoginMethod("ID");
							inJsonBank.setUserId(reqParam.getLoginId());
							inJsonBank.setUserPw(reqParam.getLoginPw());
							// - 기타 인증내용 제거
							inJsonBank.setSignCert("");
							inJsonBank.setSignPri("");
							inJsonBank.setSignPw("");
							break;
						case 2:	// 인증서 로그인
							inJsonBank.setLoginMethod("CERT");
							inJsonBank.setSignCert(signCert);
							inJsonBank.setSignPri(signPri);
							inJsonBank.setSignPw(signPw);
							// - 기타 인증내용 제거
							inJsonBank.setUserId("");
							inJsonBank.setUserPw("");
							break;
						case 3: // 빠른조회
							inJsonBank.setAcctPw(reqParam.getAcctPw());
							inJsonBank.setBizNo(reqParam.getBizNo());
							// - 기타 인증내용 제거
							inJsonBank.setLoginMethod("");
							inJsonBank.setSignCert("");
							inJsonBank.setSignPri("");
							inJsonBank.setUserId("");
							inJsonBank.setUserPw("");
						}
						
						inJsonBank.setAcctNo(reqParam.getAcctNo());
						inJsonBank.setSdate(reqParam.getFromDt());
						inJsonBank.setEdate(reqParam.getToDt());
						inJsonBank.setCurCd("KRW");	// 원화
						
						// 은행코드 '0' padding
						String bankCd = String.format("%03d", Integer.parseInt(reqParam.getBankCd()));
						inJsonBank.setBankCd(bankCd);
						
						inJsonList.add(objectMapper.writeValueAsString(inJsonBank));
						break;
					}
					break;
				}
				
				scrapCertForm.setInJson(objectMapper.writeValueAsString(inJsonList));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * setScrapCertFormHistoryRes
	 * 
	 * 인증정보 리스트에 테스트 스크래핑 결과내용을 추가합니다.
	 * 
	 * @param scrapCertFormList - 인증정보 리스트
	 */
	@SuppressWarnings("unchecked")
	private void setScrapCertFormHistoryRes(ArrayList<ScrapCertForm> scrapCertFormList) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			for(ScrapCertForm scrapCertForm : scrapCertFormList) {
				String jsonScrapData = sendScrap(scrapCertForm.getInJson(), FIRST_REQ_CD);
				
				HashMap<String, Object> resParam = objectMapper.readValue(jsonScrapData, HashMap.class);
				
				ArrayList<HashMap<String, Object>> outJsonList = 
						(ArrayList<HashMap<String, Object>>) resParam.get("outJsonList");
				
				if(outJsonList.size() > 0) {
					HashMap<String, Object> outJson = outJsonList.get(0);
					
					String appCd = outJson.get("appCd").toString();
					String orgCd = outJson.get("orgCd").toString();
					String svcCd = outJson.get("svcCd").toString();
					
					// 공통 필드 설정
					HistoryRes historyRes = new HistoryRes();
					historyRes.setCompCd(scrapCertForm.getServerScrapReqParam().getCompCd());
					historyRes.setReqCd(scrapCertForm.getServerScrapReqParam().getReqCd());
					historyRes.setAppCd(appCd);
					historyRes.setOrgCd(orgCd);
					historyRes.setSvcCd(svcCd);
					historyRes.setKeyCd(0);
					historyRes.setResCd(resParam.get("resCd").toString());
					historyRes.setConnCnt("0");
					historyRes.setErrYn(outJson.get("errYn").toString());
					historyRes.setErrMsg(outJson.get("errMsg").toString());
					historyRes.setOutTime("");
					historyRes.setBridgeAppVer(resParam.get("bridgeAppVer").toString());
					historyRes.setBridgeHostNm(resParam.get("bridgeHostNm").toString());
					historyRes.setBridgeOsNm(resParam.get("bridgeOsNm").toString());
					historyRes.setBridgeReqDt(resParam.get("bridgeReqDt").toString());
					historyRes.setBridgeResDt(resParam.get("bridgeResDt").toString());
					historyRes.setWorker(resParam.get("worker").toString());
					historyRes.setWorkerAppVer(resParam.get("workerAppVer").toString());
					historyRes.setWorkerHostNm(resParam.get("workerHostNm").toString());
					historyRes.setWorkerOsNm(resParam.get("workerOsNm").toString());
					historyRes.setWorkerReqDt(resParam.get("workerReqDt").toString());
					historyRes.setWorkerResDt(resParam.get("workerResDt").toString());
					
					// 추가 필드 설정
					switch(orgCd) {
					// - 홈택스
					case "hometax":
						historyRes.setConnCnt(outJson.get("connCnt").toString());
						historyRes.setOutTime(outJson.get("outTime").toString());
						break;
					// - 여신
					case "cardsales":
						historyRes.setConnCnt(outJson.get("connCnt").toString());
						break;
					// - 뱅킹
					// - 신용카드
					case "bank":
					case "cbk":
					case "sbk":
					case "card":
					case "ccd":
						historyRes.setKeyCd(scrapCertForm.getServerScrapReqParam().getKeyCd());
						break;
					}
					
					scrapCertForm.setHistoryRes(historyRes);
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * sendScrapErrListToBizbooks
	 * 
	 * 비즈북스에 인증 오류내용을 전송합니다.
	 * 비즈북스에서는 이 후 해당 항목이 재 스크랩 되지 않도록 스크랩 사용여부를 비활성화 처리합니다.
	 * 
	 * @param jsonData - 인증 오류내용 json
	 * @return TODO: 응답 메시지 코드 정의
	 */
	private String sendScrapErrListToBizbooks(String jsonData) {
		String url = BIZBOOKS_URL + "/api/serverScrap/sendScrapErrList";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";
		HashMap<String, Object> params = new HashMap<>();
		params.put("dataType", "scrapErrList");
		params.put("jsonData", jsonData);
		
		sendMessage(url, method, contentType, params);
		
		return "true";
	}	
	
	
	/**
	 * makeReqParamList
	 * 
	 * 스크래핑에 사용가능한 인증정보를 포함한 요청정보 리스트를 생성합니다.
	 * 
	 * @param scrapCertFormList - 인증 스크래핑 결과리스트
	 * @param jsonReqParamList - 스크래핑 요청내용 json
	 * @return 인증 정보가 포함된 스크래핑 요청내용 리스트
	 */
	private ArrayList<ServerScrapReqParam> makeReqParamList(ArrayList<ScrapCertForm> scrapCertFormList, String jsonReqParamList) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<ServerScrapReqParam> reqParamList = null;
		
		try {
			reqParamList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
					.readValue(jsonReqParamList);
			
			for(ScrapCertForm scrapCertForm : scrapCertFormList) {
				String certSvcCd = scrapCertForm.getSvcCd();
				ServerScrapReqParam serverScrapReqParam = scrapCertForm.getServerScrapReqParam();
				HistoryRes historyRes = scrapCertForm.getHistoryRes();
				
				boolean isErr = false; // 스크래핑 인증 에러인 경우 스크래핑에서 제외
				
				// 인증정보 바인딩
				for(ServerScrapReqParam reqParam : reqParamList) {
					String orgCd = reqParam.getOrgCd();
					String svcCd = reqParam.getSvcCd();
					
					switch(orgCd) {
					case "hometax":
						isErr = historyRes.getErrYn().equals("Y");
						
						switch(svcCd) {
						case "Z3001":
						case "Z3002":
						case "Z3003":
						case "Z3004":
							if(certSvcCd.equals("hometaxZ300X")) {
								reqParam.setErrYn(isErr ? "Y" : "N");

								if(isErr) {
									continue; 
								}
								
								reqParam.setSignCert(serverScrapReqParam.getSignCert());
								reqParam.setSignPri(serverScrapReqParam.getSignPri());
							}
							break;
						case "Z4001":
						case "Z4002":
						case "Z4010":
						case "Z4020":
							if(certSvcCd.equals("hometaxZ40XX")) {
								reqParam.setErrYn(isErr ? "Y" : "N");
								
								if(isErr) {
									continue; 
								}
								
								reqParam.setSignCert(serverScrapReqParam.getSignCert());
								reqParam.setSignPri(serverScrapReqParam.getSignPri());
								reqParam.setLoginId(serverScrapReqParam.getLoginId());
								reqParam.setLoginPw(serverScrapReqParam.getLoginPw());
							}
							break;
						default:
							if(certSvcCd.equals("hometaxZXXXX")) {
								reqParam.setErrYn(isErr ? "Y" : "N");
								
								if(isErr) {
									continue; 
								}
								
								reqParam.setSignCert(serverScrapReqParam.getSignCert());
								reqParam.setSignPri(serverScrapReqParam.getSignPri());
								reqParam.setLoginId(serverScrapReqParam.getLoginId());
								reqParam.setLoginPw(serverScrapReqParam.getLoginPw());
								reqParam.setAgentId(serverScrapReqParam.getAgentId());
								reqParam.setAgentPw(serverScrapReqParam.getAgentPw());
							}
							break;
						}
						break;
					case "cardsales":
						isErr = historyRes.getErrYn().equals("Y");
						
						if(certSvcCd.equals("cardsalesBXXXX")) {
							reqParam.setErrYn(isErr ? "Y" : "N");

							if(isErr) {
								continue; 
							}
							
							reqParam.setLoginId(serverScrapReqParam.getLoginId());
							reqParam.setLoginPw(serverScrapReqParam.getLoginPw());
						}
						break;
					case "ccd":
					case "card":
						if(certSvcCd.equals("creditCcd") || certSvcCd.equals("creditCard")) {
							if(reqParam.getKeyCd() == historyRes.getKeyCd()) {
								isErr = historyRes.getErrYn().equals("Y");
								reqParam.setErrYn(isErr ? "Y" : "N");
								
								if(isErr) {
									continue; 
								}
								
								reqParam.setSignCert(serverScrapReqParam.getSignCert());
								reqParam.setSignPri(serverScrapReqParam.getSignPri());
								reqParam.setLoginId(serverScrapReqParam.getLoginId());
								reqParam.setLoginPw(serverScrapReqParam.getLoginPw());
							}
						}
						break;
					case "cbk":
					case "bank":
					case "sbk":
						if(certSvcCd.equals("accountCbk") || certSvcCd.equals("accountBank") || certSvcCd.equals("accountSbk")) {
							if(reqParam.getKeyCd() == historyRes.getKeyCd()) {
								isErr = historyRes.getErrYn().equals("Y");
								reqParam.setErrYn(isErr ? "Y" : "N");
								
								if(isErr) {
									continue;
								}
								
								reqParam.setSignCert(serverScrapReqParam.getSignCert());
								reqParam.setSignPri(serverScrapReqParam.getSignPri());
								reqParam.setLoginId(serverScrapReqParam.getLoginId());
								reqParam.setLoginPw(serverScrapReqParam.getLoginPw());
							}
						}
						break;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return reqParamList;
	}
	
	
	/**
	 * sendCertFailedHistoryList
	 * 
	 * 인증 실패한 내역 히스토리를 생성 -> DB 저장 -> 비즈북스에 전송합니다.
	 * - 인증실패 History 생성
	 * - 스크래핑 DB에 저장
	 * - 비즈북스 전송
	 * 
	 * @param scrapCertFormList 인증 스크래핑 결과리스트
	 * @param reqParamList 스크래핑 요청내용 리스트
	 */
	private void sendCertFailedHistoryList(ArrayList<ScrapCertForm> scrapCertFormList, ArrayList<ServerScrapReqParam> reqParamList)
	{
		ArrayList<HistoryReq> historyReqList = new ArrayList<>();
		ArrayList<HistoryRes> historyResList = new ArrayList<>();
		
		try {
			// #1.인증실패 History 생성
			for(ServerScrapReqParam reqParam : reqParamList) {
				HistoryRes historyResParam = getErrorHistoryRes(scrapCertFormList, reqParam);
            	
            	if(historyResParam != null) {
            		//- HistoryReq 생성
	    			historyReqList.add(new HistoryReq(reqParam));
	    			
		    		//- HistoryRes 생성
	    			historyResList.add(new HistoryRes(historyResParam));
            	}
			}
			
			// #2. 스크래핑 DB에 저장
			if(historyReqList.size() > 0) {
				scrapMapper.insertHistoryReqList(historyReqList);
			}

			if(historyResList.size() > 0) {
				scrapMapper.insertHistoryResList(historyResList);
			}
			
			// #3. 비즈북스 전송
			HashMap<String, Object> historyListMap = new HashMap<>();
			historyListMap.put("historyReqList", historyReqList);
			historyListMap.put("historyResList", historyResList);
			
			sendDataToBizbooks(historyListMap);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * makeInJson
	 * 
	 * 스크래핑 요청정보를 기반으로 스크래핑 요청 전문을 생성합니다.
	 * 
	 * @param reqParam - 스크래핑 요청정보
	 * 
	 * @return 스크래핑 요청 전문
	 */
	private String makeInJson(ServerScrapReqParam reqParam) {
		ObjectMapper objectMapper = new ObjectMapper();
		String inJson = "";
		
		try {
			// 인증정보 에러인 경우 공란 반환
			if(reqParam.getErrYn() != null && reqParam.getErrYn().equals("Y")) {
				return "";
			}
			
			// 공통 요청정보 바인딩 
			String reqCd = reqParam.getReqCd();

			String appCd = reqParam.getAppCd();
			String orgCd = reqParam.getOrgCd();
			String svcCd = reqParam.getSvcCd();
			
			String proxy = proxyService.getNextAddr();	// 각 서비스 호출시마다 다른 proxy 주소로 전송
			
			// 데이터 전송시 ArrayList로 한번 더 감싸서 전송해야함 (inJson -> [inJson])
			ArrayList<String> inJsonTmpList = new ArrayList<>();

			switch(orgCd) {
			case "hometax":
				InJsonHometax inJsonHometax = new InJsonHometax(appCd, orgCd, svcCd, reqCd, proxy);
				inJsonHometax.setSignCert(reqParam.getSignCert());
				inJsonHometax.setSignPri(reqParam.getSignPri());
				inJsonHometax.setSignPw(reqParam.getSignPw());
				inJsonHometax.setUserId(reqParam.getLoginId());
				inJsonHometax.setUserPw(reqParam.getLoginPw());
				inJsonHometax.setBizNo(reqParam.getBizNo());
				inJsonHometax.setInqrDtStrt(reqParam.getFromDt());
				inJsonHometax.setInqrDtEnd(reqParam.getToDt());
				
				int scrapMode = reqParam.getScrapMode();
				
				// 선택된 인증서별로 서비스 호출 (본인인증서 / 세무대리인 인증서)  
				switch(scrapMode) {
				// - 본인인증서 only
				case 0:
					switch(svcCd) {
					// 전자세금계산서 스크래핑 조건
					// - 인증서로만 조회
					case "Z3001":
					case "Z3002":
					case "Z3003":
					case "Z3004":
						// 인증서 제외 기타 인증내용 제거
						inJsonHometax.setAgentId("");
						inJsonHometax.setAgentPw("");
						inJsonHometax.setUserId("");
						inJsonHometax.setUserPw("");
						break;
						
					// 현금영수증 / 사업용(화물복지)신용카드 스크래핑 조건 
					// - id가 있는 경우 id로 스크래핑
					// - id가 없는 경우 인증서로 스크래핑
					case "Z4001": // 현금영수증 매출 내역
					case "Z4002": // 현금영수증 매입 내역
					case "Z4010": // 사업용신용카드 매입세액 공제 확인/변경
					case "Z4020": // 화물운전자복지카드 매입세액 공제금액 조회
						// 등록된 홈택스 id가 있는 경우
						if(inJsonHometax.getUserId() != null 
							&& inJsonHometax.getUserId().isEmpty() == false) {
							// id 제외 기타 인증내용 제거
							inJsonHometax.setSignCert("");
							inJsonHometax.setSignPri("");
							inJsonHometax.setSignPw("");
						} else {
							// 인증서 제외 기타 인증내용 제거
							inJsonHometax.setUserId("");
							inJsonHometax.setUserPw("");
							
							// 인증서 없는 경우 인증내용 공란으로 설정
							if(inJsonHometax.getSignCert() == null || inJsonHometax.getSignCert().isEmpty()) {
								inJsonHometax.setSignCert("");
								inJsonHometax.setSignPri("");
								inJsonHometax.setSignPw("");
							}
						}
						break;
					}
					break;
				// - 세무대리인 인증서 only
				case 1:
					inJsonHometax.setAgentId(reqParam.getAgentId());
					inJsonHometax.setAgentPw(reqParam.getAgentPw());
					
					switch(svcCd) {
					case "Z5002": // 세금신고 접수증 조회
						inJsonHometax.setItrfCd(reqParam.getItrfCd());
						break;
					case "Z4050": // 신용카드 매출자료 조회
						inJsonHometax.setFromY(reqParam.getFromY());
						inJsonHometax.setToY(reqParam.getToY());
						inJsonHometax.setFromQ(reqParam.getFromQ());
						inJsonHometax.setToQ(reqParam.getToQ());
						break;
					case "Z4060": // 현금영수증 매출총액 조회
						inJsonHometax.setStlYr(reqParam.getStlYr());
						break;
					case "Z4070": // 수출실적명세서 조회
						inJsonHometax.setDtCd("01");	// 조회구분 - 01:월별 (default)
						inJsonHometax.setWrtArr(reqParam.getWrtArr());
						break;
					case "Z0006": // 부가세 합계표 조회
						inJsonHometax.setSupByr(reqParam.getSupByr());
						inJsonHometax.setTaxGb(reqParam.getTaxGb());
						inJsonHometax.setWrtYr(reqParam.getWrtYr());
						inJsonHometax.setWrtQt(reqParam.getWrtQt());
						break;
					default:
						// 인증서 제외 기타 인증내용 제거
						inJsonHometax.setUserId("");
						inJsonHometax.setUserPw("");
					}
					break;
				}
				
				inJsonTmpList.add(objectMapper.writeValueAsString(inJsonHometax));
				inJson = objectMapper.writeValueAsString(inJsonTmpList);
				break;
			case "bank":
			case "cbk": 
			case "sbk": {
				InJsonBank inJsonBank = new InJsonBank(appCd, orgCd, svcCd, reqCd, proxy);
				
				switch(reqParam.getLogInType()) {
				case 1:	// ID 로그인
					inJsonBank.setLoginMethod("ID");
					inJsonBank.setUserId(reqParam.getLoginId());
					inJsonBank.setUserPw(reqParam.getLoginPw());
					// - 기타 인증내용 제거
					inJsonBank.setSignCert("");
					inJsonBank.setSignPri("");
					inJsonBank.setSignPw("");
					break;
				case 2:	// 인증서 로그인
					inJsonBank.setLoginMethod("CERT");
					inJsonBank.setSignCert(reqParam.getSignCert());
					inJsonBank.setSignPri(reqParam.getSignPri());
					inJsonBank.setSignPw(reqParam.getSignPw());
					// - 기타 인증내용 제거
					inJsonBank.setUserId("");
					inJsonBank.setUserPw("");
					break;
				case 3: // 빠른조회
					inJsonBank.setAcctPw(reqParam.getAcctPw());
					inJsonBank.setBizNo(reqParam.getBizNo());
					// - 기타 인증내용 제거
					inJsonBank.setLoginMethod("");
					inJsonBank.setSignCert("");
					inJsonBank.setSignPri("");
					inJsonBank.setUserId("");
					inJsonBank.setUserPw("");
				}
				
				inJsonBank.setAcctNo(reqParam.getAcctNo());
				inJsonBank.setSdate(reqParam.getFromDt());
				inJsonBank.setEdate(reqParam.getToDt());
				inJsonBank.setCurCd("KRW");
				
				// 은행코드 '0' padding
				String bankCd = String.format("%03d", Integer.parseInt(reqParam.getBankCd()));
				inJsonBank.setBankCd(bankCd);
				
				inJsonTmpList.add(objectMapper.writeValueAsString(inJsonBank));
				inJson = objectMapper.writeValueAsString(inJsonTmpList);
				break;
			}
			case "card":
			case "ccd": {
				InJsonCard inJsonCard = new InJsonCard(appCd, orgCd, svcCd, reqCd, proxy);
				
				switch(reqParam.getLogInType()) {
				case 1:	// ID 로그인
					inJsonCard.setLoginMethod("ID");
					inJsonCard.setUserId(reqParam.getLoginId());
					inJsonCard.setUserPw(reqParam.getLoginPw());
					// - 기타 인증내용 제거
					inJsonCard.setSignCert("");
					inJsonCard.setSignPri("");
					inJsonCard.setSignPw("");
					break;
				case 2:	// 인증서 로그인
					inJsonCard.setLoginMethod("CERT");
					inJsonCard.setSignCert(reqParam.getSignCert());
					inJsonCard.setSignPri(reqParam.getSignPri());
					inJsonCard.setSignPw(reqParam.getSignPw());
					// - 기타 인증내용 제거
					inJsonCard.setUserId("");
					inJsonCard.setUserPw("");
					break;
				}
				
				inJsonCard.setCardNo(reqParam.getCardNo());
				inJsonCard.setSdate(reqParam.getFromDt());
				inJsonCard.setEdate(reqParam.getToDt());
				inJsonCard.setSvcOption("storeDetail");	// 가맹점 상세정보
				
				// 카드사코드 '0' padding
				String cardCd = String.format("%03d", Integer.parseInt(reqParam.getCardCd()));
				inJsonCard.setCardCd(cardCd);
				
				inJsonTmpList.add(objectMapper.writeValueAsString(inJsonCard));
				inJson = objectMapper.writeValueAsString(inJsonTmpList);
				break;
			}
			case "cardsales":
				InJsonCardsales inJsonCardsales = new InJsonCardsales(appCd, orgCd, svcCd, reqCd, proxy);
				inJsonCardsales.setUserId(reqParam.getLoginId());
				inJsonCardsales.setUserPw(reqParam.getLoginPw());
				inJsonCardsales.setFromDate(reqParam.getFromDt());
				inJsonCardsales.setToDate(reqParam.getToDt());
				
				inJsonTmpList.add(objectMapper.writeValueAsString(inJsonCardsales));
				inJson = objectMapper.writeValueAsString(inJsonTmpList);
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return inJson;
	}
	
	
	/**
	 * sendScrap
	 * 
	 * 요청 전문을 기반으로 서버 스크래핑을 수행합니다.
	 * 
	 * @param inJsonList - 요청 전문
	 * @param reqCd - 요청코드 (default 0000으로 표기)
	 * @return 스크래핑 결과 json
	 */
	private String sendScrap(String inJsonList, String reqCd) {
		String url = "https://183.111.102.219:9402/rest/ext";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("inJsonList", inJsonList);
		params.put("reqCd", reqCd);
		
		return sendMessage(url, method, contentType, params);
	}
	
	
	private String sendMessage(String strUrl, String method
			, String contentType, HashMap<String, Object> params) {
		
		String result = "";
		
		URL url;
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		
		try {
			disableSslVerification();
			
			url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			
	        conn.setRequestMethod(method);
	        conn.setRequestProperty("Content-type", contentType);
	        
	        if(params != null) {
	        	StringBuilder sb = new StringBuilder();
	        	for(Map.Entry<String, Object> param : params.entrySet()) {
	        		if(sb.length() != 0) {
	        			sb.append('&');
	        		}
	        		sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        		sb.append("=");
	        		sb.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        	}
	        	
	        	byte[] postDataBytes = sb.toString().getBytes("UTF-8");
	        	
	        	conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        	conn.setDoOutput(true);
	        	conn.getOutputStream().write(postDataBytes);
	        } 
	        
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }

	        rd.close();
	        
	        result = sb.toString();
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.disconnect();
		}
		
		return result;
	}
	
	
	/**
	 * sendDataToBizbooks
	 * 
	 * 비즈북스에 데이터를 전송합니다.
	 * 데이터는 스크래유형에 따라 단일, 복수건에 따라 다르게 처리합니다.
	 * - 단일 : 바로 전송
	 * - 복수 : 
	 *   > (데이터 / DATA_DIVISION_LEN) 횟수로 DELAY_MS 시간마다 분할 전송
	 *   > 스크래핑 유형에 따라 delete -> insert 처리하는 항목있음 (A.I field 사용하는 항목들)
	 * 
	 * @param dataMap - 전송할 데이터
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sendDataToBizbooks(HashMap<String, Object> dataMap) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			for(Map.Entry<String, Object> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				
				// 복수건, 단일건에 따른 전송방법 분리
				if(entry.getValue() instanceof ArrayList<?>) {
					// 복수건 전송
					ArrayList<? extends Object> dataList = (ArrayList<? extends Object>) entry.getValue();
					
					// 삭제할 데이터 있는 경우 삭제
					String jsonDelData = "";
					
					switch(key) {
						// 여신
						// - 기간별승인내역(일별)_상세
						case "cardsalesB0011Dtl": {
							SCardsalesB0011Dtl deleteParam = (SCardsalesB0011Dtl) dataList.get(0);
							deleteParam.setFromDt(deleteParam.getTrDt());
							deleteParam.setToDt(((SCardsalesB0011Dtl) dataList.get(dataList.size() - 1)).getTrDt());
							
							jsonDelData = objectMapper.writeValueAsString(deleteParam);
							break;
						}
						
						// - 기간별입금내역_상세
						case "cardsalesB0031Dtl": {
							SCardsalesB0031Dtl deleteParam = (SCardsalesB0031Dtl) dataList.get(0);
							deleteParam.setFromDt(deleteParam.getPayDt());
							deleteParam.setToDt(((SCardsalesB0031Dtl) dataList.get(dataList.size() - 1)).getPayDt());
							
							jsonDelData = objectMapper.writeValueAsString(deleteParam);
							break;
						}
					}
					
					if(jsonDelData.length() > 0) {
						deleteFromBizbooks(key, jsonDelData);
					}
					
					// 데이터 분할 전송 
					// - (데이터 / DATA_DIVISION_LEN) 횟수로 DELAY_MS 시간마다 전송
					int size = dataList.size();
					
					if(size > 0) {
						int cnt = Math.floorDiv(size, DATA_DIVISION_LEN);

						// 비즈북스 전송시 데이터 전송 시차를 두어 프로그램 다운 방지
						Timer timer = new Timer(false);
						
						for(int i = 0; i <= cnt; i++) {
							int startIdx = i * DATA_DIVISION_LEN;
							int endIdx =  size <= ((i * DATA_DIVISION_LEN) + DATA_DIVISION_LEN) ?
									size : ((i * DATA_DIVISION_LEN) + DATA_DIVISION_LEN);
							
							ArrayList<? extends Object> tmpList = new ArrayList(dataList.subList(startIdx, endIdx));
							
							String jsonData;
							jsonData = objectMapper.writeValueAsString(tmpList);
							
							timer.schedule(new TimerTask() {
								@Override
								public void run() {
									insertToBizbooks(key, jsonData);
								}
							}, ((i + 1) * DELAY_MS));
						}
					}
				} else {
					// 단일건 전송
					Object data = (Object) entry.getValue();
					
					switch(key) {
					case "historyReq":
					case "historyRes":
						String jsonData;
						jsonData = objectMapper.writeValueAsString(data);
						insertToBizbooks(key, jsonData);
						break;
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertHistoryReqList(String jsonReqList) {
		// HistoryReq DTO는 BizbooksReqParam DTO를 가공해서 생성
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<ServerScrapReqParam> reqParamList;
		try {
			reqParamList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
					.readValue(jsonReqList);
			
			ArrayList<HistoryReq> historyReqList = new ArrayList<HistoryReq>(); 
			 
			for(ServerScrapReqParam reqParam : reqParamList) {
				// 요청코드 (yyyyMMddhhmmss + 10자리 코드)
				String reqCd = reqParam.getReqCd();
				
				HistoryReq historyReq = new HistoryReq();
				historyReq.setCompCd(reqParam.getCompCd());
				historyReq.setAppCd(reqParam.getAppCd());
				historyReq.setOrgCd(reqParam.getOrgCd());
				historyReq.setSvcCd(reqParam.getSvcCd());
				historyReq.setKeyCd(reqParam.getKeyCd());
				historyReq.setReqCd(reqCd);
				
				// 스크랩일자 (yyyyMMdd)
				historyReq.setScrapDt(reqCd.substring(0, 8));
				// 요청일시 (yyyyMMddhhmmss)
				historyReq.setReqDt(reqCd.substring(0, 14));
				
				historyReq.setScrapFromDt(reqParam.getFromDt());
				historyReq.setScrapToDt(reqParam.getToDt());
				
				historyReqList.add(historyReq);
			}
			
			scrapMapper.insertHistoryReqList(historyReqList);
 			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void insertHistoryResList(String jsonResList) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<HistoryRes> historyResList;
		try {
			historyResList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<HistoryRes>>() {})
					.readValue(jsonResList);
			
			for(HistoryRes res : historyResList) {
				// 스크랩일자 (yyyyMMdd)
				res.setScrapDt(res.getReqCd().substring(0, 8));
			}
			
			scrapMapper.insertHistoryResList(historyResList);
 			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String insertHistory(String jsonReqList, String jsonResList) {
		insertHistoryReqList(jsonReqList);
		insertHistoryResList(jsonResList);		
		
		return "true";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String insertData(String dataType, String jsonData) {
		System.out.println("############# insertData");
		System.out.println(dataType);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<? extends Object> dataList = null;
		
		try {
			switch(dataType) {
			// 홈택스
			// - 전자세금계산서
			case "hometaxZ300X":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ300X>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ300XList((ArrayList<SHometaxZ300X>) dataList);
				}
				break;
			// - 전자세금계산서 품목
			case "hometaxZ300XItem":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ300XItem>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ300XItemList((ArrayList<SHometaxZ300XItem>) dataList);
				}
				break;
			// - 현금영수증
			case "hometaxZ4001":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4001>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4001List((ArrayList<SHometaxZ4001>) dataList);
				}
				break;
			case "hometaxZ4002":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4002>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4002List((ArrayList<SHometaxZ4002>) dataList);
				}
				break;
			// - 사업용 화물복지 신용카드
			case "hometaxZ4010":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4010>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4010List((ArrayList<SHometaxZ4010>) dataList);
				}
				break;
			case "hometaxZ4020":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4020>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4020List((ArrayList<SHometaxZ4020>) dataList);
				}
				break;
			// - 신용카드 매출자료 조회
			case "hometaxZ4050":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4050>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4050List((ArrayList<SHometaxZ4050>) dataList);
				}
				break;
			// - 현금영수증 매출총액 조회
			case "hometaxZ4060":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4060>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4060List((ArrayList<SHometaxZ4060>) dataList);
				}
				break;
			// - 수출실적명세서 조회
			case "hometaxZ4070":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ4070>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ4070List((ArrayList<SHometaxZ4070>) dataList);
				}
				break;
			// - 세금신고 접수증조회
			case "hometaxZ5002":
				dataList = objectMapper.reader()
				.forType(new TypeReference<ArrayList<SHometaxZ5002>>() {})
				.readValue(jsonData);
			
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ5002List((ArrayList<SHometaxZ5002>) dataList);
				}
				break;
			// - 부가세신고용 합계표
			case "hometaxZ0006":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ0006>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ0006List((ArrayList<SHometaxZ0006>) dataList); 
				}
				break;
			// - 부가세신고용 합계표 명세
			case "hometaxZ0006Dtl":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SHometaxZ0006Dtl>>() {})
					.readValue(jsonData);
			
				if(dataList.size() > 0) {
					scrapMapper.insertSHometaxZ0006DtlList((ArrayList<SHometaxZ0006Dtl>) dataList); 
				}
				break;
			
			// 여신
			// - 가맹점 수수료율/대금지급주기
			case "cardsalesB0002":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0002>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0002List((ArrayList<SCardsalesB0002>) dataList);
				}
				break;
			// - 기간별승인내역(일별)
			case "cardsalesB0011Dtl":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0011Dtl>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0011DtlList((ArrayList<SCardsalesB0011Dtl>) dataList);
				}
				break;
			case "cardsalesB0011Sum":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0011Sum>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0011SumList((ArrayList<SCardsalesB0011Sum>) dataList);
				}
				break;
			// - 기간별매입내역(일별)
			case "cardsalesB0021Dtl":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0021Dtl>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0021DtlList((ArrayList<SCardsalesB0021Dtl>) dataList);
				}
				break;
			case "cardsalesB0021Sum":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0021Sum>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0021SumList((ArrayList<SCardsalesB0021Sum>) dataList);
				}
				break;
			// - 기간별입금내역
			case "cardsalesB0031Dtl":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0031Dtl>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0031DtlList((ArrayList<SCardsalesB0031Dtl>) dataList);
				}
				break;
			case "cardsalesB0031Sum":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardsalesB0031Sum>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardsalesB0031SumList((ArrayList<SCardsalesB0031Sum>) dataList);
				}
				break;
				
			// 뱅킹
			// - 개인
			case "bankB0002":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SBankB0002>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSBankB0002List((ArrayList<SBankB0002>) dataList);
				}
				break;
			// - 법인
			case "cbkB0002":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCbkB0002>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCbkB0002List((ArrayList<SCbkB0002>) dataList);
				}
				break;
			// - 빠른조회
			case "sbkB0002":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SSbkB0002>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSSbkB0002List((ArrayList<SSbkB0002>) dataList);
				}
				break;
			
			// 신용카드
			// - 개인
			case "cardC0005":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCardC0005>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCardC0005List((ArrayList<SCardC0005>) dataList);
				}
				break;
			// - 법인
			case "ccdC0005":
				dataList = objectMapper.reader()
					.forType(new TypeReference<ArrayList<SCcdC0005>>() {})
					.readValue(jsonData);
				
				if(dataList.size() > 0) {
					scrapMapper.insertSCcdC0005List((ArrayList<SCcdC0005>) dataList);
				}
				break;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "true";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteData(String dataType, String jsonData) {
		System.out.println("############# insertData");
		System.out.println(dataType);

		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<? extends Object> dataList = null;
		
		try {
			switch(dataType) {
			// 여신
			// - 기간별승인내역(일별)_상세
			case "cardsalesB0011Dtl":
				{
					dataList = objectMapper.reader()
							.forType(new TypeReference<ArrayList<SCardsalesB0011Dtl>>() {})
							.readValue(jsonData);
					
					SCardsalesB0011Dtl deleteParam = (SCardsalesB0011Dtl) dataList.get(0);
					deleteParam.setFromDt(deleteParam.getTrDt());
					deleteParam.setToDt(((SCardsalesB0011Dtl) dataList.get(dataList.size() - 1)).getTrDt());
					
					scrapMapper.deleteSCardsalesB0011DtlList(deleteParam);
					break;			
				}
			// - 기간별입금내역_상세
			case "cardsalesB0031Dtl":
				{
					dataList = objectMapper.reader()
							.forType(new TypeReference<ArrayList<SCardsalesB0031Dtl>>() {})
							.readValue(jsonData);
					
					SCardsalesB0031Dtl deleteParam = (SCardsalesB0031Dtl) dataList.get(0);
					deleteParam.setFromDt(deleteParam.getPayDt());
					deleteParam.setToDt(((SCardsalesB0031Dtl) dataList.get(dataList.size() - 1)).getPayDt());
					
					scrapMapper.deleteSCardsalesB0031DtlList(deleteParam);
					break;
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "true";
	}

	@Override
	public String sendDataToBizbooks(String compCd, String scrapDt) {
		// 1. 히스토리 내역 조회
		HistoryReq historyReqParam = new HistoryReq();
		historyReqParam.setScrapDt(scrapDt);
		historyReqParam.setCompCd(compCd);
		
		HistoryRes historyResParam = new HistoryRes();
		historyResParam.setScrapDt(scrapDt);
		historyResParam.setCompCd(compCd);
		
		ArrayList<HistoryReq> historyReqList = scrapMapper.selectHistoryReqList(historyReqParam);
		ArrayList<HistoryRes> historyResList = scrapMapper.selectHistoryResList(historyResParam);
		
		// 히스토리 내역 전송
		HashMap<String, Object> historyListMap = new HashMap<>();
		historyListMap.put("historyReqList", historyReqList);
		historyListMap.put("historyResList", historyResList);
		
		sendDataToBizbooks(historyListMap);
		
		try {
			
			// 스크랩 데이터 전송
			for(HistoryRes historyRes : historyResList) {
				HashMap<String, Object> dataListMap = new HashMap<>();

				// - 정상 스크래핑 된 내역만 전송
				if(historyRes.getErrYn().equals("N")) {
					String orgCd = historyRes.getOrgCd();
					String svcCd = historyRes.getSvcCd();
					String reqCd = historyRes.getReqCd();
					
					switch(orgCd) {
					// 홈택스
					case "hometax":
						switch(svcCd) {
						// - 전자세금계산서
						case "Z3001":
						case "Z3002":
						case "Z3003":
						case "Z3004":
							// 전자세금계산서_내역
							SHometaxZ300X sHometaxZ300XParam = new SHometaxZ300X();
							sHometaxZ300XParam.setCompCd(compCd);
							sHometaxZ300XParam.setSvcCd(svcCd);
							sHometaxZ300XParam.setReqCd(reqCd);
							dataListMap.put("hometaxZ300X", scrapMapper.selectSHometaxZ300XList(sHometaxZ300XParam));
							
							// 전자세금계산서_품목
							SHometaxZ300XItem sHometaxZ300XItemParam = new SHometaxZ300XItem();
							sHometaxZ300XItemParam.setCompCd(compCd);
							sHometaxZ300XItemParam.setSvcCd(svcCd);
							sHometaxZ300XItemParam.setReqCd(reqCd);
							dataListMap.put("hometaxZ300XItem", scrapMapper.selectSHometaxZ300XItemList(sHometaxZ300XItemParam));
							break;
						// - 현금영수증
						case "Z4001":
							SHometaxZ4001 sHometaxZ4001Param = new SHometaxZ4001();
							sHometaxZ4001Param.setCompCd(compCd);
							sHometaxZ4001Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4001", scrapMapper.selectSHometaxZ4001List(sHometaxZ4001Param));
							break;
						case "Z4002":
							SHometaxZ4002 sHometaxZ4002Param = new SHometaxZ4002();
							sHometaxZ4002Param.setCompCd(compCd);
							sHometaxZ4002Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4002", scrapMapper.selectSHometaxZ4002List(sHometaxZ4002Param));
							break;
						// - 사업용신용카드
						case "Z4010":
							SHometaxZ4010 sHometaxZ4010Param = new SHometaxZ4010();
							sHometaxZ4010Param.setCompCd(compCd);
							sHometaxZ4010Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4010", scrapMapper.selectSHometaxZ4010List(sHometaxZ4010Param));
							break;
						case "Z4020":
							SHometaxZ4020 sHometaxZ4020Param = new SHometaxZ4020();
							sHometaxZ4020Param.setCompCd(compCd);
							sHometaxZ4020Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4020", scrapMapper.selectSHometaxZ4020List(sHometaxZ4020Param));
							break;
						// - 신용카드 매출자료 조회
						case "Z4050":
							SHometaxZ4050 sHometaxZ4050Param = new SHometaxZ4050();
							sHometaxZ4050Param.setCompCd(compCd);
							sHometaxZ4050Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4050", scrapMapper.selectSHometaxZ4050List(sHometaxZ4050Param));
							break;
						// - 현금영수증 매출총액 조회
						case "Z4060":
							SHometaxZ4060 sHometaxZ4060Param = new SHometaxZ4060();
							sHometaxZ4060Param.setCompCd(compCd);
							sHometaxZ4060Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4060", scrapMapper.selectSHometaxZ4060List(sHometaxZ4060Param));
							break;
						// - 수출실적명세서 조회
						case "Z4070":
							SHometaxZ4070 sHometaxZ4070Param = new SHometaxZ4070();
							sHometaxZ4070Param.setCompCd(compCd);
							sHometaxZ4070Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ4070", scrapMapper.selectSHometaxZ4070List(sHometaxZ4070Param));
							break;
						// - 세금신고 접수증조회
						case "Z5002":
							SHometaxZ5002 sHometaxZ5002Param = new SHometaxZ5002();
							sHometaxZ5002Param.setCompCd(compCd);
							sHometaxZ5002Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ5002", scrapMapper.selectSHometaxZ5002List(sHometaxZ5002Param));
							break;
						// - 부가세신고용 합계표 조회
						case "Z0006":
							// - 부가세신고용 합계표
							SHometaxZ0006 sHometaxZ0006Param = new SHometaxZ0006();
							sHometaxZ0006Param.setCompCd(compCd);
							sHometaxZ0006Param.setReqCd(reqCd);
							dataListMap.put("hometaxZ0006", scrapMapper.selectSHometaxZ0006List(sHometaxZ0006Param));
							
							// - 부가세신고용 합계표 명세
							SHometaxZ0006Dtl sHometaxZ0006DtlParam = new SHometaxZ0006Dtl();
							sHometaxZ0006DtlParam.setCompCd(compCd);
							sHometaxZ0006DtlParam.setReqCd(reqCd);
							dataListMap.put("hometaxZ0006Dtl", scrapMapper.selectSHometaxZ0006DtlList(sHometaxZ0006DtlParam));
							break;
						}
						break;
					// 여신
					case "cardsales":
						switch(svcCd) {
						// - 가맹점 수수료율/대금지급주기 조회
						case "B0002":
							SCardsalesB0002 sCardsalesB0002Param = new SCardsalesB0002();
							sCardsalesB0002Param.setCompCd(compCd);
							sCardsalesB0002Param.setReqCd(reqCd);
							dataListMap.put("cardsalesB0002", scrapMapper.selectSCardsalesB0002List(sCardsalesB0002Param));
							break;
						// - 기간별승인내역(일별)
						case "B0011":
							SCardsalesB0011Dtl sCardsalesB0011DtlParam = new SCardsalesB0011Dtl();
							sCardsalesB0011DtlParam.setCompCd(compCd);
							sCardsalesB0011DtlParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0011Dtl", scrapMapper.selectSCardsalesB0011DtlList(sCardsalesB0011DtlParam));

							SCardsalesB0011Sum sCardsalesB0011SumParam = new SCardsalesB0011Sum();
							sCardsalesB0011SumParam.setCompCd(compCd);
							sCardsalesB0011SumParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0011Sum", scrapMapper.selectSCardsalesB0011SumList(sCardsalesB0011SumParam));
							break;
						// - 기간별매입내역(일별)
						case "B0021":
							SCardsalesB0021Dtl sCardsalesB0021DtlParam = new SCardsalesB0021Dtl();
							sCardsalesB0021DtlParam.setCompCd(compCd);
							sCardsalesB0021DtlParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0021Dtl", scrapMapper.selectSCardsalesB0021DtlList(sCardsalesB0021DtlParam));
							
							SCardsalesB0021Sum sCardsalesB0021SumParam = new SCardsalesB0021Sum();
							sCardsalesB0021SumParam.setCompCd(compCd);
							sCardsalesB0021SumParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0021Sum", scrapMapper.selectSCardsalesB0021SumList(sCardsalesB0021SumParam));
							break;
						// - 기간별입금내역
						case "B0031":
							SCardsalesB0031Dtl sCardsalesB0031DtlParam = new SCardsalesB0031Dtl();
							sCardsalesB0031DtlParam.setCompCd(compCd);
							sCardsalesB0031DtlParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0031Dtl", scrapMapper.selectSCardsalesB0031DtlList(sCardsalesB0031DtlParam));
							
							SCardsalesB0031Sum sCardsalesB0031SumParam = new SCardsalesB0031Sum();
							sCardsalesB0031SumParam.setCompCd(compCd);
							sCardsalesB0031SumParam.setReqCd(reqCd);
							dataListMap.put("cardsalesB0031Sum", scrapMapper.selectSCardsalesB0031SumList(sCardsalesB0031SumParam));
							break;
						}
						break;
					// 뱅킹_개인
					case "bank":
						SBankB0002 sBankB0002Param = new SBankB0002();
						sBankB0002Param.setCompCd(compCd);
						sBankB0002Param.setReqCd(reqCd);
						dataListMap.put("bankB0002", scrapMapper.selectSBankB0002List(sBankB0002Param));
						break;
					// 뱅킹_기업
					case "cbk":
						SCbkB0002 sCbkB0002Param = new SCbkB0002();
						sCbkB0002Param.setCompCd(compCd);
						sCbkB0002Param.setReqCd(reqCd);
						dataListMap.put("cbkB0002", scrapMapper.selectSCbkB0002List(sCbkB0002Param));
						break;
					// 뱅킹_빠른조회
					case "sbk":
						SSbkB0002 sSbkB0002Param = new SSbkB0002();
						sSbkB0002Param.setCompCd(compCd);
						sSbkB0002Param.setReqCd(reqCd);
						dataListMap.put("sbkB0002", scrapMapper.selectSSbkB0002List(sSbkB0002Param));
						break;
					// 신용카드_개인
					case "card":
						SCardC0005 sCardC0005Param = new SCardC0005();
						sCardC0005Param.setCompCd(compCd);
						sCardC0005Param.setReqCd(reqCd);
						dataListMap.put("cardC0005", scrapMapper.selectSCardC0005List(sCardC0005Param));
						break;
					// 신용카드_기업
					case "ccd":
						SCcdC0005 sCcdC0005Param = new SCcdC0005();
						sCcdC0005Param.setCompCd(compCd);
						sCcdC0005Param.setReqCd(reqCd);
						dataListMap.put("ccdC0005", scrapMapper.selectSCcdC0005List(sCcdC0005Param));
						break;
					}
				}
				
				sendDataToBizbooks(dataListMap);
			}

			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "true";
	}
	
	
	
	private String insertToBizbooks(String dataType, String jsonData) {
		String url = BIZBOOKS_URL + "/api/serverScrap/insertScrapDataList";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";
		HashMap<String, Object> params = new HashMap<>();
		params.put("dataType", dataType);
		params.put("jsonData", jsonData);
		
		sendMessage(url, method, contentType, params);
		
		return "true";
	}
	
	private String deleteFromBizbooks(String dataType, String jsonData) {
		String url = BIZBOOKS_URL + "/api/serverScrap/deleteScrapDataList";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";
		HashMap<String, Object> params = new HashMap<>();
		params.put("dataType", dataType);
		params.put("jsonData", jsonData);
		
		sendMessage(url, method, contentType, params);
		
		return "true";
	}
	
	
	private String makeInJsonList_old(String jsonReqParamList) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<ServerScrapReqParam> reqParamList = 
				objectMapper.reader()
				.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
				.readValue(jsonReqParamList);
		
		ArrayList<String> inJsonList = new ArrayList<>();
		
		// 201110_kmh 중복 인증서 제거 
		// - 인증서 명이 같은 경우 certMap에 포함하고, 스크래핑시 해당 인증서로 처리합니다.
		HashMap<String, ServerScrapReqParam> certMap = new HashMap<>();
		
		for(ServerScrapReqParam reqParam : reqParamList) {
			ServerScrapReqParam reqParamTmp = certMap.get(reqParam.getCertNm());
			
			if(reqParamTmp == null && reqParam.getCertNm() != null) {
				SftpUtil sftpUtil = new SftpUtil();
				
				String signCert = sftpUtil.getFileData(reqParam.getSignCert(), "signCert.der");
				String signPri = sftpUtil.getFileData(reqParam.getSignPri(), "signPri.key");
				
				reqParam.setSignCert(signCert);
				reqParam.setSignPri(signPri);
				
				certMap.put(reqParam.getCertNm(), reqParam);
			}
		}
		
		
		for(ServerScrapReqParam reqParam : reqParamList) {
			// 공통 요청정보
			String appCd = reqParam.getAppCd();
			String orgCd = reqParam.getOrgCd();
			String svcCd = reqParam.getSvcCd();
			
			String signCert = null;
			String signPri = null;
			String proxy = proxyService.getNextAddr();	// 각 서비스 호출시마다 다른 proxy 주소로 전송
			
			ServerScrapReqParam reqParamTmp = certMap.get(reqParam.getCertNm());
			if(reqParamTmp != null) {
				signCert = reqParamTmp.getSignCert();
				signPri = reqParamTmp.getSignPri();
			}
			
			String signPw = reqParam.getSignPw();
			String reqCd = reqParam.getReqCd();
			
			switch(orgCd) {
			case "hometax":
				InJsonHometax inJsonHometax = new InJsonHometax(appCd, orgCd, svcCd, reqCd, proxy);
				inJsonHometax.setSignCert(signCert);
				inJsonHometax.setSignPri(signPri);
				inJsonHometax.setSignPw(signPw);
				inJsonHometax.setUserId(reqParam.getLoginId());
				inJsonHometax.setUserPw(reqParam.getLoginPw());
				inJsonHometax.setBizNo(reqParam.getBizNo());
				inJsonHometax.setInqrDtStrt(reqParam.getFromDt());
				inJsonHometax.setInqrDtEnd(reqParam.getToDt());
				
				int scrapMode = reqParam.getScrapMode();
				
				// 선택된 인증서별로 서비스 호출 (본인인증서 / 세무대리인 인증서)  
				switch(scrapMode) {
				// - 본인인증서 only
				case 0:
					switch(svcCd) {
					// 전자세금계산서 스크래핑 조건
					// - 인증서로만 조회
					case "Z3001":
					case "Z3002":
					case "Z3003":
					case "Z3004":
						// 인증서 제외 기타 인증내용 제거
						inJsonHometax.setAgentId("");
						inJsonHometax.setAgentPw("");
						inJsonHometax.setUserId("");
						inJsonHometax.setUserPw("");
						break;
						
					// 현금영수증 / 사업용(화물복지)신용카드 스크래핑 조건 
					// - id가 있는 경우 id로 스크래핑
					// - id가 없는 경우 인증서로 스크래핑
					case "Z4001": // 현금영수증 매출 내역
					case "Z4002": // 현금영수증 매입 내역
					case "Z4010": // 사업용신용카드 매입세액 공제 확인/변경
					case "Z4020": // 화물운전자복지카드 매입세액 공제금액 조회
						// 등록된 홈택스 id가 있는 경우
						if(inJsonHometax.getUserId() != null 
							&& inJsonHometax.getUserId().isEmpty() == false) {
							// id 제외 기타 인증내용 제거
							inJsonHometax.setSignCert("");
							inJsonHometax.setSignPri("");
							inJsonHometax.setSignPw("");
						} else {
							// 인증서 제외 기타 인증내용 제거
							inJsonHometax.setUserId("");
							inJsonHometax.setUserPw("");
							
							// 인증서 없는 경우 인증내용 공란으로 설정
							if(inJsonHometax.getSignCert() == null || inJsonHometax.getSignCert().isEmpty()) {
								inJsonHometax.setSignCert("");
								inJsonHometax.setSignPri("");
								inJsonHometax.setSignPw("");
							}
						}
						break;
					}
					break;
				// - 세무대리인 인증서 only
				case 1:
					inJsonHometax.setAgentId(reqParam.getAgentId());
					inJsonHometax.setAgentPw(reqParam.getAgentPw());
					
					switch(svcCd) {
					case "Z5002": // 세금신고 접수증 조회
						inJsonHometax.setItrfCd(reqParam.getItrfCd());
						break;
					case "Z4050": // 신용카드 매출자료 조회
						inJsonHometax.setFromY(reqParam.getFromY());
						inJsonHometax.setToY(reqParam.getToY());
						inJsonHometax.setFromQ(reqParam.getFromQ());
						inJsonHometax.setToQ(reqParam.getToQ());
						break;
					case "Z4060": // 현금영수증 매출총액 조회
						inJsonHometax.setStlYr(reqParam.getStlYr());
						break;
					case "Z4070": // 수출실적명세서 조회
						inJsonHometax.setDtCd("01");	// 조회구분 - 01:월별 (default)
						inJsonHometax.setWrtArr(reqParam.getWrtArr());
						break;
					case "Z0006": // 부가세 합계표 조회
						inJsonHometax.setSupByr(reqParam.getSupByr());
						inJsonHometax.setTaxGb(reqParam.getTaxGb());
						inJsonHometax.setWrtYr(reqParam.getWrtYr());
						inJsonHometax.setWrtQt(reqParam.getWrtQt());
						break;
					default:
						// 인증서 제외 기타 인증내용 제거
						inJsonHometax.setUserId("");
						inJsonHometax.setUserPw("");
					}
					break;
				}
				
				inJsonList.add(objectMapper.writeValueAsString(inJsonHometax));
				break;
			case "bank":
			case "cbk": 
			case "sbk": {
				InJsonBank inJsonBank = new InJsonBank(appCd, orgCd, svcCd, reqCd, proxy);
				
				switch(reqParam.getLogInType()) {
				case 1:	// ID 로그인
					inJsonBank.setLoginMethod("ID");
					inJsonBank.setUserId(reqParam.getLoginId());
					inJsonBank.setUserPw(reqParam.getLoginPw());
					// - 기타 인증내용 제거
					inJsonBank.setSignCert("");
					inJsonBank.setSignPri("");
					inJsonBank.setSignPw("");
					break;
				case 2:	// 인증서 로그인
					inJsonBank.setLoginMethod("CERT");
					inJsonBank.setSignCert(signCert);
					inJsonBank.setSignPri(signPri);
					inJsonBank.setSignPw(signPw);
					// - 기타 인증내용 제거
					inJsonBank.setUserId("");
					inJsonBank.setUserPw("");
					break;
				case 3: // 빠른조회
					inJsonBank.setAcctPw(reqParam.getAcctPw());
					inJsonBank.setBizNo(reqParam.getBizNo());
					// - 기타 인증내용 제거
					inJsonBank.setLoginMethod("");
					inJsonBank.setSignCert("");
					inJsonBank.setSignPri("");
					inJsonBank.setUserId("");
					inJsonBank.setUserPw("");
				}
				
				inJsonBank.setAcctNo(reqParam.getAcctNo());
				inJsonBank.setSdate(reqParam.getFromDt());
				inJsonBank.setEdate(reqParam.getToDt());
				inJsonBank.setCurCd("KRW");
				
				// 은행코드 '0' padding
				String bankCd = String.format("%03d", Integer.parseInt(reqParam.getBankCd()));
				inJsonBank.setBankCd(bankCd);
				
				inJsonList.add(objectMapper.writeValueAsString(inJsonBank));
				break;
			}
			case "card":
			case "ccd": {
				InJsonCard inJsonCard = new InJsonCard(appCd, orgCd, svcCd, reqCd, proxy);
				
				switch(reqParam.getLogInType()) {
				case 1:	// ID 로그인
					inJsonCard.setLoginMethod("ID");
					inJsonCard.setUserId(reqParam.getLoginId());
					inJsonCard.setUserPw(reqParam.getLoginPw());
					// - 기타 인증내용 제거
					inJsonCard.setSignCert("");
					inJsonCard.setSignPri("");
					inJsonCard.setSignPw("");
					break;
				case 2:	// 인증서 로그인
					inJsonCard.setLoginMethod("CERT");
					inJsonCard.setSignCert(signCert);
					inJsonCard.setSignPri(signPri);
					inJsonCard.setSignPw(signPw);
					// - 기타 인증내용 제거
					inJsonCard.setUserId("");
					inJsonCard.setUserPw("");
					break;
				}
				
				inJsonCard.setCardNo(reqParam.getCardNo());
				inJsonCard.setSdate(reqParam.getFromDt());
				inJsonCard.setEdate(reqParam.getToDt());
				
				// 카드사코드 '0' padding
				String cardCd = String.format("%03d", Integer.parseInt(reqParam.getCardCd()));
				inJsonCard.setCardCd(cardCd);
				
				inJsonList.add(objectMapper.writeValueAsString(inJsonCard));
				break;
			}
			case "cardsales":
				InJsonCardsales inJsonCardsales = new InJsonCardsales(appCd, orgCd, svcCd, reqCd, proxy);
				inJsonCardsales.setUserId(reqParam.getLoginId());
				inJsonCardsales.setUserPw(reqParam.getLoginPw());
				inJsonCardsales.setFromDate(reqParam.getFromDt());
				inJsonCardsales.setToDate(reqParam.getToDt());
				
				inJsonList.add(objectMapper.writeValueAsString(inJsonCardsales));
				break;
			}
		}
			
		
		return objectMapper.writeValueAsString(inJsonList);
	}
	
	
	// ResParamList 구함
	// OutJsonList 구함
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> makeScrapData(String jsonScrapData, String jsonReqParamList) {
		ObjectMapper objectMapper = new ObjectMapper();

		HashMap<String, Object> hm = new HashMap<>();
		
		try {
			HashMap<String, Object> resParam = objectMapper.readValue(jsonScrapData, HashMap.class);
			
			ArrayList<HashMap<String, Object>> outJsonList = 
					(ArrayList<HashMap<String, Object>>) resParam.get("outJsonList");
			
			ArrayList<ServerScrapReqParam> reqParamList = 
					objectMapper.reader()
					.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
					.readValue(jsonReqParamList);
			
			if(reqParamList.size() != outJsonList.size()) {
				return null;
			}
			
			ArrayList<HistoryRes> resParamList = new ArrayList<>();
			
			for(int i = 0; i < reqParamList.size(); i++) {
				ServerScrapReqParam reqParam = reqParamList.get(i);
				HashMap<String, Object> outJson = outJsonList.get(i);
				
				String appCd = outJson.get("appCd").toString();
				String orgCd = outJson.get("orgCd").toString();
				String svcCd = outJson.get("svcCd").toString();
				
				// 공통 필드 설정
				HistoryRes res = new HistoryRes();
				res.setCompCd(reqParam.getCompCd());
				res.setAppCd(appCd);
				res.setOrgCd(orgCd);
				res.setSvcCd(svcCd);
				res.setKeyCd(0);
				res.setReqCd(reqParam.getReqCd());
				res.setResCd(resParam.get("resCd").toString());
				res.setConnCnt("0");
				res.setErrYn(outJson.get("errYn").toString());
				res.setErrMsg(outJson.get("errMsg").toString());
				res.setOutTime("");
				res.setBridgeAppVer(resParam.get("bridgeAppVer").toString());
				res.setBridgeHostNm(resParam.get("bridgeHostNm").toString());
				res.setBridgeOsNm(resParam.get("bridgeOsNm").toString());
				res.setBridgeReqDt(resParam.get("bridgeReqDt").toString());
				res.setBridgeResDt(resParam.get("bridgeResDt").toString());
				res.setWorker(resParam.get("worker").toString());
				res.setWorkerAppVer(resParam.get("workerAppVer").toString());
				res.setWorkerHostNm(resParam.get("workerHostNm").toString());
				res.setWorkerOsNm(resParam.get("workerOsNm").toString());
				res.setWorkerReqDt(resParam.get("workerReqDt").toString());
				res.setWorkerResDt(resParam.get("workerResDt").toString());
				
				// 추가 필드 설정
				switch(orgCd) {
				// - 홈택스
				case "hometax":
					res.setConnCnt(outJson.get("connCnt").toString());
					res.setOutTime(outJson.get("outTime").toString());
					break;
				// - 여신
				case "cardsales":
					res.setConnCnt(outJson.get("connCnt").toString());
					break;
				// - 뱅킹
				// - 신용카드
				case "bank":
				case "cbk":
				case "sbk":
				case "card":
				case "ccd":
					res.setKeyCd(reqParam.getKeyCd());
					break;
				}
				
				// System.out.println(res);
				
				resParamList.add(res);
			}
			
			hm.put("resParamList", objectMapper.writeValueAsString(resParamList));
			hm.put("outJsonList", objectMapper.writeValueAsString(outJsonList));
	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
				
		return hm;
	}
	

	@SuppressWarnings("unchecked")
	private HashMap<String, Object> makeInsertData(String jsonOutJsonList, String jsonResParamList) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<HashMap<String, Object>> outJsonList = 
				objectMapper.reader()
				.forType(new TypeReference<ArrayList<HashMap<String, Object>>>() {})
				.readValue(jsonOutJsonList);
		
		ArrayList<HistoryRes> resParamList = 
				objectMapper.reader()
				.forType(new TypeReference<ArrayList<HistoryRes>>() {})
				.readValue(jsonResParamList);
		
		if( (outJsonList == null || resParamList == null) ||
			(outJsonList.size() != resParamList.size())) {
			return null;
		}

		ArrayList<SHometaxZ300X> sHometaxZ300XList = new ArrayList<>();
		ArrayList<SHometaxZ300XItem> sHometaxZ300XItemList = new ArrayList<>();
		ArrayList<SHometaxZ4001> sHometaxZ4001List = new ArrayList<>();
		ArrayList<SHometaxZ4002> sHometaxZ4002List = new ArrayList<>();
		ArrayList<SHometaxZ4010> sHometaxZ4010List = new ArrayList<>();
		ArrayList<SHometaxZ4020> sHometaxZ4020List = new ArrayList<>();
		ArrayList<SHometaxZ4050> sHometaxZ4050List = new ArrayList<>();
		ArrayList<SHometaxZ4060> sHometaxZ4060List = new ArrayList<>();
		ArrayList<SHometaxZ4070> sHometaxZ4070List = new ArrayList<>();
		ArrayList<SHometaxZ5002> sHometaxZ5002List = new ArrayList<>(); 
		ArrayList<SCardsalesB0002> sCardsalesB0002List = new ArrayList<>();
		ArrayList<SCardsalesB0011Dtl> sCardsalesB0011DtlList = new ArrayList<>();
		ArrayList<SCardsalesB0011Sum> sCardsalesB0011SumList = new ArrayList<>();
		ArrayList<SCardsalesB0021Dtl> sCardsalesB0021DtlList = new ArrayList<>();
		ArrayList<SCardsalesB0021Sum> sCardsalesB0021SumList = new ArrayList<>();
		ArrayList<SCardsalesB0031Dtl> sCardsalesB0031DtlList = new ArrayList<>();
		ArrayList<SCardsalesB0031Sum> sCardsalesB0031SumList = new ArrayList<>();
		ArrayList<SBankB0002> sBankB0002List = new ArrayList<>();
		ArrayList<SCbkB0002> sCbkB0002List = new ArrayList<>();
		ArrayList<SSbkB0002> sSbkB0002List = new ArrayList<>();
		ArrayList<SCardC0005> sCardC0005List = new ArrayList<>();
		ArrayList<SCcdC0005> sCcdC0005List = new ArrayList<>();
		ArrayList<SHometaxZ0006> sHometaxZ0006List = new ArrayList<>();
		ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlList = new ArrayList<>();
		
		
		for(int i = 0 ; i < outJsonList.size(); i++) {
			try {
				HashMap<String, Object> outJson = outJsonList.get(i);
				String compCd = resParamList.get(i).getCompCd();
				String reqCd = resParamList.get(i).getReqCd();
				
				String orgCd = outJson.get("orgCd").toString();
				String svcCd = outJson.get("svcCd").toString();
				
				// 스크랩 기업 구분
				switch(orgCd) {
				// 홈택스
				case "hometax":
					// 서비스 구분
					switch(svcCd) {
						// - 전자세금계산서
						case "Z3001":
						case "Z3002":
						case "Z3003":
						case "Z3004": {
							// -- 내역
							ArrayList<SHometaxZ300X> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ300X>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ300X sHometaxZ300X : list) {
									sHometaxZ300X.setCompCd(compCd);
									sHometaxZ300X.setSvcCd(svcCd);
									sHometaxZ300X.setReqCd(reqCd);
									sHometaxZ300XList.add(sHometaxZ300X);
								}
							}
							
							// -- 품목
							ArrayList<SHometaxZ300XItem> itemList = 
									objectMapper.convertValue(outJson.get("itemList"), new TypeReference<ArrayList<SHometaxZ300XItem>>() {});
							if(itemList != null && itemList.size() > 0) {
								for(SHometaxZ300XItem sHometaxZ300XItem : itemList) {
									sHometaxZ300XItem.setCompCd(compCd);
									sHometaxZ300XItem.setSvcCd(svcCd);
									sHometaxZ300XItem.setReqCd(reqCd);
									sHometaxZ300XItemList.add(sHometaxZ300XItem);
								}
							}
							break;
						}
						// - 현금영수증
						case "Z4001": {
							ArrayList<SHometaxZ4001> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4001>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4001 sHometaxZ4001 : list) {
									sHometaxZ4001.setCompCd(compCd);
									sHometaxZ4001.setReqCd(reqCd);
									sHometaxZ4001List.add(sHometaxZ4001);
								}
							}
							break;
						}
						case "Z4002": {
							ArrayList<SHometaxZ4002> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4002>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4002 sHometaxZ4002 : list) {
									sHometaxZ4002.setCompCd(compCd);
									sHometaxZ4002.setReqCd(reqCd);
									sHometaxZ4002List.add(sHometaxZ4002);
								}
							}
							break;
						}
						// - 사업용 화물복지 신용카드
						case "Z4010": {
							ArrayList<SHometaxZ4010> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4010>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4010 sHometaxZ4010 : list) {
									sHometaxZ4010.setCompCd(compCd);
									sHometaxZ4010.setReqCd(reqCd);
									sHometaxZ4010List.add(sHometaxZ4010);
								}
							}
							break;
						}
						case "Z4020": {
							ArrayList<SHometaxZ4020> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4010>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4020 sHometaxZ4020 : list) {
									sHometaxZ4020.setCompCd(compCd);
									sHometaxZ4020.setReqCd(reqCd);
									sHometaxZ4020List.add(sHometaxZ4020);
								}
							}
							break;
						}
						// - 신용카드 매출자료 조회
						case "Z4050": {
							ArrayList<SHometaxZ4050> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4050>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4050 sHometaxZ4050 : list) {
									sHometaxZ4050.setCompCd(compCd);
									sHometaxZ4050.setReqCd(reqCd);
									sHometaxZ4050List.add(sHometaxZ4050);
								}
							}
							break;
						}
						// - 현금영수증 매출총액 조회
						case "Z4060": {
							ArrayList<SHometaxZ4060> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4060>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4060 sHometaxZ4060 : list) {
									sHometaxZ4060.setCompCd(compCd);
									sHometaxZ4060.setReqCd(reqCd);
									sHometaxZ4060List.add(sHometaxZ4060);
								}
							}
							break;
						}
						// - 수출실적명세서 조회
						case "Z4070": {
							ArrayList<SHometaxZ4070> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4070>>() {});
							if(list != null && list.size() > 0) {
								for(SHometaxZ4070 sHometaxZ4070 : list) {
									sHometaxZ4070.setCompCd(compCd);
									sHometaxZ4070.setReqCd(reqCd);
									sHometaxZ4070List.add(sHometaxZ4070);
								}
							}
							break;
						}
						// - 세금신고 접수증조회
						case "Z5002": {
							ArrayList<SHometaxZ5002> list = 
									objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ5002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SHometaxZ5002 sHometaxZ5002 : list) {
									sHometaxZ5002.setCompCd(compCd);
									sHometaxZ5002.setReqCd(reqCd);
									sHometaxZ5002List.add(sHometaxZ5002);
								}
							}
							break;
						}
						// - 부가세신고용 합계표
						// > 부가세신고용 합계표 + 부가세신고용 합계표 명세로 구성 (SHometaxZ0006 + SHometaxZ0006Dtl)
						case "Z0006": {
							ArrayList<HashMap<String, Object>> outZ0006List = 
									objectMapper.convertValue(outJson.get("outZ0006"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
							
							if(outZ0006List != null) {
								for(HashMap<String, Object> outZ0006 : outZ0006List) {
									HashMap<String, Object> cnd = (HashMap<String, Object>) outZ0006.get("cnd");
											
									// 공통 fields
									String wrtYr = cnd.get("wrtYr").toString();
									String wrtQt = cnd.get("wrtQt").toString();
									String wrtDtFrom = cnd.get("wrtDtFrom").toString();
									String wrtDtTo = cnd.get("wrtDtTo").toString();
									String supByr = cnd.get("supByr").toString();
									String taxGb = cnd.get("taxGb").toString();
									
									// TODO: setter -> convertValue to POJO (현재 null bind됨..)
									//SHometaxZ0006 sHometaxZ0006 = objectMapper.convertValue(outZ0006, SHometaxZ0006.class);
									
									// 부가세신고용 합계표 조회
									SHometaxZ0006 sHometaxZ0006 = new SHometaxZ0006();
									sHometaxZ0006.setCompCd(compCd);
									sHometaxZ0006.setSupByr(supByr);
									sHometaxZ0006.setTaxGb(taxGb);
									sHometaxZ0006.setWrtYr(wrtYr);
									sHometaxZ0006.setWrtQt(wrtQt);
									sHometaxZ0006.setWrtDtFrom(wrtDtFrom);
									sHometaxZ0006.setWrtDtTo(wrtDtTo);
									sHometaxZ0006.setWithIn_bsnoIsnClplcCnt(outZ0006.get("WithIn_bsnoIsnClplcCnt").toString());
									sHometaxZ0006.setWithIn_bsnoIsnScnt(outZ0006.get("WithIn_bsnoIsnScnt").toString());
									sHometaxZ0006.setWithIn_bsnoIsnSplCft(outZ0006.get("WithIn_bsnoIsnSplCft").toString());
									sHometaxZ0006.setWithIn_bsnoIsnTxamt(outZ0006.get("WithIn_bsnoIsnTxamt").toString());
									sHometaxZ0006.setWithIn_resnoIsnClplcCnt(outZ0006.get("WithIn_resnoIsnClplcCnt").toString());
									sHometaxZ0006.setWithIn_resnoIsnScnt(outZ0006.get("WithIn_resnoIsnScnt").toString());
									sHometaxZ0006.setWithIn_resnoIsnSplCft(outZ0006.get("WithIn_resnoIsnSplCft").toString());
									sHometaxZ0006.setWithIn_resnoIsnTxamt(outZ0006.get("WithIn_resnoIsnTxamt").toString());
									sHometaxZ0006.setWithIn_totCnt(outZ0006.get("WithIn_totCnt").toString());
									sHometaxZ0006.setWithIn_totPurchplcCnt(outZ0006.get("WithIn_totPurchplcCnt").toString());
									sHometaxZ0006.setWithIn_totSellplcCnt(outZ0006.get("WithIn_totSellplcCnt").toString());
									sHometaxZ0006.setWithIn_totSplCft(outZ0006.get("WithIn_totSplCft").toString());
									sHometaxZ0006.setWithIn_totTxamt(outZ0006.get("WithIn_totTxamt").toString());
									sHometaxZ0006.setEt_bsnoIsnClplcCnt(outZ0006.get("Et_bsnoIsnClplcCnt").toString());
									sHometaxZ0006.setEt_bsnoIsnScnt(outZ0006.get("Et_bsnoIsnScnt").toString());
									sHometaxZ0006.setEt_bsnoIsnSplCft(outZ0006.get("Et_bsnoIsnSplCft").toString());
									sHometaxZ0006.setEt_bsnoIsnTxamt(outZ0006.get("Et_bsnoIsnTxamt").toString());
									sHometaxZ0006.setEt_resnoIsnClplcCnt(outZ0006.get("Et_resnoIsnClplcCnt").toString());
									sHometaxZ0006.setEt_resnoIsnScnt(outZ0006.get("Et_resnoIsnScnt").toString());
									sHometaxZ0006.setEt_resnoIsnSplCft(outZ0006.get("Et_resnoIsnSplCft").toString());
									sHometaxZ0006.setEt_resnoIsnTxamt(outZ0006.get("Et_resnoIsnTxamt").toString());
									sHometaxZ0006.setEt_totCnt(outZ0006.get("Et_totCnt").toString());
									sHometaxZ0006.setEt_totPurchplcCnt(outZ0006.get("Et_totPurchplcCnt").toString());
									sHometaxZ0006.setEt_totSellplcCnt(outZ0006.get("Et_totSellplcCnt").toString());
									sHometaxZ0006.setEt_totSplCft(outZ0006.get("Et_totSplCft").toString());
									sHometaxZ0006.setEt_totTxamt(outZ0006.get("Et_totTxamt").toString());
									sHometaxZ0006.setReqCd(reqCd);
									sHometaxZ0006List.add(sHometaxZ0006);
									
									// 부가세신고용 합계표 조회 명세
									// - 11일 이전, 11일 이후 2가지 list로 값 넘어옴
									ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlEtList = 
											objectMapper.convertValue(outZ0006.get("listEt"), new TypeReference<ArrayList<SHometaxZ0006Dtl>>() {});
									ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlWithInList = 
											objectMapper.convertValue(outZ0006.get("listWithIn"), new TypeReference<ArrayList<SHometaxZ0006Dtl>>() {});
									
									if(sHometaxZ0006DtlEtList.size() > 0) {
										for(SHometaxZ0006Dtl sHometaxZ0006Dtl : sHometaxZ0006DtlEtList) {
											sHometaxZ0006Dtl.setCompCd(compCd);
											sHometaxZ0006Dtl.setSupByr(supByr);
											sHometaxZ0006Dtl.setTaxGb(taxGb);
											sHometaxZ0006Dtl.setWrtYr(wrtYr);
											sHometaxZ0006Dtl.setWrtQt(wrtQt);
											sHometaxZ0006Dtl.setWrtDtFrom(wrtDtFrom);
											sHometaxZ0006Dtl.setWrtDtTo(wrtDtTo);
											sHometaxZ0006Dtl.setReqCd(reqCd);
											sHometaxZ0006DtlList.add(sHometaxZ0006Dtl);
										}
									}
									
									if(sHometaxZ0006DtlWithInList.size() > 0) {
										for(SHometaxZ0006Dtl sHometaxZ0006Dtl : sHometaxZ0006DtlWithInList) {
											sHometaxZ0006Dtl.setCompCd(compCd);
											sHometaxZ0006Dtl.setSupByr(supByr);
											sHometaxZ0006Dtl.setTaxGb(taxGb);
											sHometaxZ0006Dtl.setWrtYr(wrtYr);
											sHometaxZ0006Dtl.setWrtQt(wrtQt);
											sHometaxZ0006Dtl.setWrtDtFrom(wrtDtFrom);
											sHometaxZ0006Dtl.setWrtDtTo(wrtDtTo);
											sHometaxZ0006Dtl.setReqCd(reqCd);
											sHometaxZ0006DtlList.add(sHometaxZ0006Dtl);
										}
									}
								}	
							}
							break;
						}
					}
					break;
				// 여신
				case "cardsales":
					// 서비스 구분
					switch(svcCd) {
						// - 가맹점 수수료율/대금지급주기
						case "B0002": {
							HashMap<String, Object> outB0002 =  
									objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<HashMap<String, Object>>() {});
							
							ArrayList<SCardsalesB0002> list = 
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SCardsalesB0002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCardsalesB0002 sCardsalesB0002 : list) {
									sCardsalesB0002.setCompCd(compCd);
									sCardsalesB0002.setReqCd(reqCd);
									sCardsalesB0002List.add(sCardsalesB0002);
								}
							}
							break;
						}
						// - 기간별승인내역(일별)
						case "B0011": {
							// -- 상세
							HashMap<String, Object> outB0011 = 
									objectMapper.convertValue(outJson.get("outB0011"), new TypeReference<HashMap<String, Object>>() {});
							
							ArrayList<SCardsalesB0011Dtl> listDtl = 
									objectMapper.convertValue(outB0011.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0011Dtl>>() {});
															
							if(listDtl != null && listDtl.size() > 0) {
								for(SCardsalesB0011Dtl sCardsalesB0011Dtl : listDtl) {
									sCardsalesB0011Dtl.setCompCd(compCd);
									sCardsalesB0011Dtl.setReqCd(reqCd);
									sCardsalesB0011DtlList.add(sCardsalesB0011Dtl);
								}
							}
							
							// -- 합계
							ArrayList<SCardsalesB0011Sum> listSum = 
									objectMapper.convertValue(outB0011.get("listSum"), new TypeReference<ArrayList<SCardsalesB0011Sum>>() {});
							
							if(listSum != null && listSum.size() > 0) {
								for(SCardsalesB0011Sum sCardsalesB0011Sum : listSum) {
									sCardsalesB0011Sum.setCompCd(compCd);
									sCardsalesB0011Sum.setReqCd(reqCd);
									sCardsalesB0011SumList.add(sCardsalesB0011Sum);
								}
							}
							break;
						}
						// - 기간별매입내역(일별)
						case "B0021": {
							// -- 상세
							HashMap<String, Object> outB0021 = 
									objectMapper.convertValue(outJson.get("outB0021"), new TypeReference<HashMap<String, Object>>() {});
							
							ArrayList<SCardsalesB0021Dtl> listDtl =
									objectMapper.convertValue(outB0021.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0021Dtl>>() {});

							if(listDtl != null && listDtl.size() > 0) {
								for(SCardsalesB0021Dtl sCardsalesB0021Dtl : listDtl) {
									sCardsalesB0021Dtl.setCompCd(compCd);
									sCardsalesB0021Dtl.setReqCd(reqCd);
									sCardsalesB0021DtlList.add(sCardsalesB0021Dtl);
								}
							}
							
							// -- 합계
							ArrayList<SCardsalesB0021Sum> listSum = 
									objectMapper.convertValue(outB0021.get("listSum"), new TypeReference<ArrayList<SCardsalesB0021Sum>>() {});
							
							if(listSum != null && listSum.size() > 0) {
								for(SCardsalesB0021Sum sCardsalesB0021Sum : listSum) {
									sCardsalesB0021Sum.setCompCd(compCd);
									sCardsalesB0021Sum.setReqCd(reqCd);
									sCardsalesB0021SumList.add(sCardsalesB0021Sum);
								}
							}
							break;
						}
						// - 기간별입금내역
						case "B0031": {
							HashMap<String, Object> outB0031 = 
									objectMapper.convertValue(outJson.get("outB0031"), new TypeReference<HashMap<String, Object>>() {});
							
							// -- 상세
							ArrayList<SCardsalesB0031Dtl> listDtl = 
									objectMapper.convertValue(outB0031.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0031Dtl>>() {});

							if(listDtl != null && listDtl.size() > 0) {
								for(SCardsalesB0031Dtl sCardsalesB0031Dtl : listDtl) {
									sCardsalesB0031Dtl.setCompCd(compCd);
									sCardsalesB0031Dtl.setReqCd(reqCd);
									sCardsalesB0031DtlList.add(sCardsalesB0031Dtl);
								}
							}
							
							// -- 합계
							ArrayList<SCardsalesB0031Sum> listSum = 
									objectMapper.convertValue(outB0031.get("listSum"), new TypeReference<ArrayList<SCardsalesB0031Sum>>() {});
							
							if(listSum != null && listSum.size() > 0) {
								for(SCardsalesB0031Sum sCardsalesB0031Sum : listSum) {
									sCardsalesB0031Sum.setCompCd(compCd);
									sCardsalesB0031Sum.setReqCd(reqCd);
									sCardsalesB0031SumList.add(sCardsalesB0031Sum);
								}
							}
							break;
						}
					}
					break;
				// 뱅킹 - 개인
				case "bank":
					// 서비스 구분
					switch(svcCd) {
						// - 거래내역조회 (입출금통장)
						case "B0002": {
							ArrayList<HashMap<String, Object>> outB0002List = 
									objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
							
							if(outB0002List == null || outB0002List.size() == 0) {
								continue;
							}
							
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SBankB0002> list = 
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SBankB0002>>() {});

							if(list != null && list.size() > 0) {
								for(SBankB0002 sBankB0002 : list) {
									sBankB0002.setCompCd(compCd);
									sBankB0002.setReqCd(reqCd);
									sBankB0002.setAcctNo(in.get("acctNo").toString());
									sBankB0002.setBankCd(in.get("bankCd").toString());
									sBankB0002List.add(sBankB0002);
								}
							}
							break;
						}
					}
					break;
				// 뱅킹 - 기업
				case "cbk":
					// 서비스 구분
					switch(svcCd) {
						// - 거래내역조회 (입출금통장)
						case "B0002": {
							ArrayList<HashMap<String, Object>> outB0002List = 
									objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
							
							if(outB0002List == null || outB0002List.size() == 0) {
								continue;
							}
							
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SCbkB0002> list =
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SCbkB0002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCbkB0002 sCbkB0002 : list) {
									sCbkB0002.setCompCd(compCd);
									sCbkB0002.setReqCd(reqCd);
									sCbkB0002.setAcctNo(in.get("acctNo").toString());
									sCbkB0002.setBankCd(in.get("bankCd").toString());
									sCbkB0002List.add(sCbkB0002);
								}
							}
							break;
						}
					}
					break;
				// 뱅킹 - 빠른조회
				case "sbk":
					// 서비스 구분
					switch(svcCd) {
						// - 거래내역조회 (입출금통장)
						case "B0002": {
							ArrayList<HashMap<String, Object>> outB0002List = 
									objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
							
							if(outB0002List == null || outB0002List.size() == 0) {
								continue;
							}
							
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SSbkB0002> list = 
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SSbkB0002>>() {});

							if(list != null && list.size() > 0) {
								for(SSbkB0002 sSbkB0002 : list) {
									sSbkB0002.setCompCd(compCd);
									sSbkB0002.setReqCd(reqCd);
									sSbkB0002.setAcctNo(in.get("acctNo").toString());
									sSbkB0002.setBankCd(in.get("bankCd").toString());
									sSbkB0002List.add(sSbkB0002);
								}
							}
							break;
						}
					}
					break;
				// 신용카드 - 개인
				case "card":
					// 서비스 구분
					switch(svcCd) {
						// - 승인내역조회
						case "C0005": {
							HashMap<String, Object> outC0005 = 
									objectMapper.convertValue(outJson.get("outC0005"), new TypeReference<HashMap<String, Object>>() {});
							
							ArrayList<SCardC0005> list =
									objectMapper.convertValue(outC0005.get("list"), new TypeReference<ArrayList<SCardC0005>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCardC0005 sCardC0005 : list) {
									sCardC0005.setCompCd(compCd);
									sCardC0005.setReqCd(reqCd);
									sCardC0005List.add(sCardC0005);
								}
							}
							break;
						}
					}
					break;
				// 신용카드 - 기업
				case "ccd":
					// 서비스 구분
					switch(svcCd) {
						// - 승인내역조회
						case "C0005": {
							HashMap<String, Object> outC0005 = 
									objectMapper.convertValue(outJson.get("outC0005"), new TypeReference<HashMap<String, Object>>() {});
							
							ArrayList<SCcdC0005> list =
									objectMapper.convertValue(outC0005.get("list"), new TypeReference<ArrayList<SCcdC0005>>() {});
									
							if(list != null && list.size() > 0) {
								for(SCcdC0005 sCcdC0005 : list) {
									sCcdC0005.setCompCd(compCd);
									sCcdC0005.setReqCd(reqCd);
									sCcdC0005List.add(sCcdC0005);
								}
							}
							break;
						}
					}
					break;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		HashMap<String, Object> insertDataMap = new HashMap<>();
		insertDataMap.put("hometaxZ300X",  sHometaxZ300XList);
		insertDataMap.put("hometaxZ300XItem", sHometaxZ300XItemList);
		insertDataMap.put("hometaxZ4001", sHometaxZ4001List);
		insertDataMap.put("hometaxZ4002", sHometaxZ4002List);
		insertDataMap.put("hometaxZ4010", sHometaxZ4010List);
		insertDataMap.put("hometaxZ4020", sHometaxZ4020List);
		insertDataMap.put("hometaxZ4050", sHometaxZ4050List);
		insertDataMap.put("hometaxZ4060", sHometaxZ4060List);
		insertDataMap.put("hometaxZ4070", sHometaxZ4070List);
		insertDataMap.put("hometaxZ5002", sHometaxZ5002List);
		insertDataMap.put("hometaxZ0006", sHometaxZ0006List);
		insertDataMap.put("hometaxZ0006Dtl", sHometaxZ0006DtlList);
		insertDataMap.put("bankB0002", sBankB0002List);
		insertDataMap.put("cbkB0002", sCbkB0002List);
		insertDataMap.put("sbkB0002", sSbkB0002List);
		insertDataMap.put("cardC0005", sCardC0005List);
		insertDataMap.put("ccdC0005", sCcdC0005List);
		insertDataMap.put("cardsalesB0002", sCardsalesB0002List);
		insertDataMap.put("cardsalesB0011Dtl", sCardsalesB0011DtlList);
		insertDataMap.put("cardsalesB0011Sum", sCardsalesB0011SumList);
		insertDataMap.put("cardsalesB0021Dtl", sCardsalesB0021DtlList);
		insertDataMap.put("cardsalesB0021Sum", sCardsalesB0021SumList);
		insertDataMap.put("cardsalesB0031Dtl", sCardsalesB0031DtlList);
		insertDataMap.put("cardsalesB0031Sum", sCardsalesB0031SumList);
		
		return insertDataMap;
	}

	@SuppressWarnings("unchecked")
	private void insertScrapData(HashMap<String, Object> insertDataMap) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		for(Map.Entry<String, Object> entry : insertDataMap.entrySet()) {
			String key = entry.getKey();
			ArrayList<Object> dataList = (ArrayList<Object>) entry.getValue();
			
			if(dataList.size() > 0) {
				String jsonData = objectMapper.writeValueAsString(dataList);

				// delete -> insert 필요한 항목들 처리
				switch(key) {
					case "cardsalesB0011Dtl":
					case "cardsalesB0031Dtl":
						deleteData(key, jsonData);
						break;
				}
				
				insertData(key, jsonData);
			}
		}
	}
	
	
	//################ SSL Security Exception 방지 코드
	// TODO: util로 분리
	private static void disableSslVerification() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
	
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
	
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
	
			} 
		};

		try {
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * getBizbooksScrapSchedule
	 *  - 비즈북스에서 스크래핑을 수행할 내역들을 가져옵니다.
	 *    이 후 스크래핑은 회사코드(CompCd)별로 수행됩니다. 
	 * 
	 * @param fromDt	: 스크래핑 시작 일시 (yyyyMMdd)
	 * @param toDt		: 스크래핑 종료 일시 (yyyyMMdd)
	 * @return
	 * 	HashMap<String, String>
	 *	- compCd : 회사코드
	 *	- jsonScrapList : 스크랩 항목
	 */
	private HashMap<String, String> getBizbooksScrapSchedule(String fromDt, String toDt) {
		// 입력날짜 없는 경우 기본날짜 설정
		if(fromDt.isEmpty() || toDt.isEmpty()) {
			HashMap<String, String> scrapDt = getDefaultScrapDt();
			fromDt = scrapDt.get("fromDt");
			toDt = scrapDt.get("toDt");
		}
		
		// 비즈북스에서 스크랩 리스트 가져옴
		return getBizbooksScrapListMap(fromDt, toDt);
	}

	private HashMap<String, String> getBizbooksScrapListMap(String fromDt, String toDt) {
		// 비즈북스에서 스크래핑 리스트 가져옴
		String url = BIZBOOKS_URL + "/api/serverScrap/getScrapList";
		url += "?fromDt=" + fromDt;
		url += "&toDt=" + toDt;
		
		String method = "POST";
		String contentType = "application/json";
		String jsonScrapList = sendMessage(url, method, contentType, null);
		

		HashMap<String, String> scrapListMap = new HashMap<>();
		try {
			// 스크래핑 리스트 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<ServerScrapReqParam> reqParamList = objectMapper.reader()
				.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
				.readValue(jsonScrapList);
			
			AES256Util aes256CertPw = new AES256Util(AES256Util.KEY_CERT_PW);
			AES256Util aes256AgentId = new AES256Util(AES256Util.KEY_AGENT_ID);
			AES256Util aes256AgentPw = new AES256Util(AES256Util.KEY_AGENT_PW);
			AES256Util aes256CertLoginId = new AES256Util(AES256Util.KEY_CERT_LOGIN_ID);
			AES256Util aes256CertLoginPw = new AES256Util(AES256Util.KEY_CERT_LOGIN_PW);
			AES256Util aes256AccountPw = new AES256Util(AES256Util.KEY_ACCOUNT_PW);
			AES256Util aes256CompRegsNo = new AES256Util(AES256Util.KEY_COMP_REGSNO);

			String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			
			// 데이터 복호화
			for(int i = 0; i < reqParamList.size(); i++) {
				ServerScrapReqParam reqParam = reqParamList.get(i);
				
				// 요청번호 부여 (yyyyMMddHHmmss)
				String reqCd = String.format("%s%010d", dateTime, i);
				reqParam.setReqCd(reqCd);
				
				// 데이터 복호화
				// - 인증서 비밀번호
				if(reqParam.getSignPw() != null) {
					reqParam.setSignPw(aes256CertPw.decrypt(reqParam.getSignPw()));
				}
				// - 세무대리인 관리번호
				if(reqParam.getAgentId() != null) {
					reqParam.setAgentId(aes256AgentId.decrypt(reqParam.getAgentId()));
				}
				// - 세무대리인 비밀번호
				if(reqParam.getAgentPw() != null) {
					reqParam.setAgentPw(aes256AgentPw.decrypt(reqParam.getAgentPw()));
				}
				// - 로그인 id (여신 / 뱅킹 / 카드)
				if(reqParam.getLoginId() != null) {
					reqParam.setLoginId(aes256CertLoginId.decrypt(reqParam.getLoginId()));
				}
				// - 로그인 pw (여신 / 뱅킹 / 카드)
				if(reqParam.getLoginPw() != null) {
					reqParam.setLoginPw(aes256CertLoginPw.decrypt(reqParam.getLoginPw()));
				}
				// - 계좌비밀번호 (뱅킹-빠른조회)
				if(reqParam.getAcctPw() != null) {
					reqParam.setAcctPw(aes256AccountPw.decrypt(reqParam.getAcctPw()));
				}
				
				// - 주민등록번호
				// > 홈택스_세금신고접수증조회_종합소득세
				if((reqParam.getOrgCd() != null && reqParam.getOrgCd().equals("hometax")) 
						&& (reqParam.getSvcCd() != null && reqParam.getSvcCd().equals("Z5002")) 
						&& (reqParam.getItrfCd() != null && reqParam.getItrfCd().equals("10"))) {
					if(reqParam.getBizNo() != null) {
						reqParam.setBizNo(aes256CompRegsNo.decrypt(reqParam.getBizNo()));
					}
				}
			}
			

			// 회사코드별 분류
			String cmpCompCd = "";
			ArrayList<ServerScrapReqParam> scrapList = new ArrayList<>();
			
			for(int i = 0; i < reqParamList.size(); i++) {
				ServerScrapReqParam reqParam = reqParamList.get(i);
				String compCd = reqParam.getCompCd();
				
				if(i == 0) {
					cmpCompCd = compCd;
				}
				
				if(compCd.equals(cmpCompCd)) {
					scrapList.add(reqParam);
				} else {
					String jsonData = objectMapper.writeValueAsString(scrapList);
					scrapListMap.put(cmpCompCd, jsonData);

					scrapList.clear();
					scrapList.add(reqParam);
					
					cmpCompCd = compCd;
				} 
			}
			
			if(scrapList.size() > 0) {
				String jsonData = objectMapper.writeValueAsString(scrapList);
				scrapListMap.put(cmpCompCd, jsonData);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return scrapListMap;
	}

	/**
	 * getDefaultScrapDt
	 *  - 스크래핑을 수행할 기본 날짜를 설정합니다
	 * 
	 * @return
	 *  HashMap<String, String>
	 * 	- fromDt: 스크래핑 조회 시작일자
	 *  - toDt: 스크래핑 조회 종료일자
	 */
	private HashMap<String, String> getDefaultScrapDt() {
		HashMap<String, String> scrapDt = new HashMap<>();
		
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String year = today.substring(0, 4);
		String month = today.substring(4, 6);
		String day = today.substring(6, 8);
		
		// 결과날짜
		String fromDt = year + "0101";
		String toDt = today;
		
		int nMonthDay = Integer.parseInt(month + day);
		
		// 3.15 ~ 4.25 -> 1월 1일 ~ 당월 현재
		if(nMonthDay >= 315 && nMonthDay <= 425) {
			fromDt = year + "0101";
			
		// 6.15 ~ 7.25 -> 1월 1일 ~ 당월 현재
		} else if(nMonthDay >= 615 && nMonthDay <= 725) {
			fromDt = year + "0101";
			
		// 9.15 ~ 10.25 -> 7월 1일 ~ 당월 현재
		} else if(nMonthDay >= 915 && nMonthDay <= 1025) {
			fromDt = year + "0701";
			
		// 12.15 ~ 1.25 -> 7월 1일 ~ 당월 현재
		} else if(nMonthDay >= 1215) {
			fromDt = year + "0701";
			
		// 그 외 1일 ~ 11일 -> 전월 1일 ~ 당월 현재
		} else if(nMonthDay >= Integer.parseInt(month + "01") && nMonthDay <= Integer.parseInt(month + "11")) {
			// 전월처리
			int nLastMonth = Integer.parseInt(month) - 1;
			String lastMonth = nLastMonth == 0 ? "12" : String.format("%02d", nLastMonth);

			fromDt = year + lastMonth + "01";
			
		// 그외 12일 ~ 말일 -> 당월1일 ~ 당월 현재
		} else if(nMonthDay >= Integer.parseInt(month + "12")) {
			fromDt = year + month + "01";
		}
		
		scrapDt.put("fromDt", fromDt);
		scrapDt.put("toDt", toDt);
		
		return scrapDt;
	}
	
	

	
	
	
	
	
	/**
	 * 스크래핑 인증 오류 발생내용 비즈북스 전송
	 * @param scrapCertFormList
	 */
	private void sendScrapErrListToBizbooks(ArrayList<ScrapCertForm> scrapCertFormList) {
		
		ArrayList<HistoryRes> scrapErrList = new ArrayList<>();
		
		for(ScrapCertForm scrapCertForm : scrapCertFormList) {
			HistoryRes scrapErr = scrapCertForm.getHistoryRes();
			
			if(scrapErr != null && scrapErr.getErrYn() != null && scrapErr.getErrYn().equals("Y")) {
				scrapErr.setSvcCd(scrapCertForm.getSvcCd()); // 서비스코드 변환 (ex: Z4001 -> hometaxZ40XX)
				scrapErrList.add(scrapErr);
			}
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			sendScrapErrListToBizbooks(objectMapper.writeValueAsString(scrapErrList));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private ArrayList<String> makeInJsonList_test(ArrayList<ServerScrapReqParam> reqParamList) {
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<String> inJsonList = new ArrayList<>();
		
		try {
			// 공통 요청정보 바인딩 
			for(ServerScrapReqParam reqParam : reqParamList) {
				// 인증정보 에러인 경우 pass
				if(reqParam.getErrYn() != null && reqParam.getErrYn().equals("Y")) {
					continue;
				}
				
				String reqCd = reqParam.getReqCd();

				String appCd = reqParam.getAppCd();
				String orgCd = reqParam.getOrgCd();
				String svcCd = reqParam.getSvcCd();
				
				String proxy = proxyService.getNextAddr();	// 각 서비스 호출시마다 다른 proxy 주소로 전송
				
				// ArrayList로 한번 더 감싸서 인포텍 전송해야함
				ArrayList<String> inJsonTmpList = new ArrayList<>();

				switch(orgCd) {
				case "hometax":
					InJsonHometax inJsonHometax = new InJsonHometax(appCd, orgCd, svcCd, reqCd, proxy);
					inJsonHometax.setSignCert(reqParam.getSignCert());
					inJsonHometax.setSignPri(reqParam.getSignPri());
					inJsonHometax.setSignPw(reqParam.getSignPw());
					inJsonHometax.setUserId(reqParam.getLoginId());
					inJsonHometax.setUserPw(reqParam.getLoginPw());
					inJsonHometax.setBizNo(reqParam.getBizNo());
					inJsonHometax.setInqrDtStrt(reqParam.getFromDt());
					inJsonHometax.setInqrDtEnd(reqParam.getToDt());
					
					int scrapMode = reqParam.getScrapMode();
					
					// 선택된 인증서별로 서비스 호출 (본인인증서 / 세무대리인 인증서)  
					switch(scrapMode) {
					// - 본인인증서 only
					case 0:
						switch(svcCd) {
						// 전자세금계산서 스크래핑 조건
						// - 인증서로만 조회
						case "Z3001":
						case "Z3002":
						case "Z3003":
						case "Z3004":
							// 인증서 제외 기타 인증내용 제거
							inJsonHometax.setAgentId("");
							inJsonHometax.setAgentPw("");
							inJsonHometax.setUserId("");
							inJsonHometax.setUserPw("");
							break;
							
						// 현금영수증 / 사업용(화물복지)신용카드 스크래핑 조건 
						// - id가 있는 경우 id로 스크래핑
						// - id가 없는 경우 인증서로 스크래핑
						case "Z4001": // 현금영수증 매출 내역
						case "Z4002": // 현금영수증 매입 내역
						case "Z4010": // 사업용신용카드 매입세액 공제 확인/변경
						case "Z4020": // 화물운전자복지카드 매입세액 공제금액 조회
							// 등록된 홈택스 id가 있는 경우
							if(inJsonHometax.getUserId() != null 
								&& inJsonHometax.getUserId().isEmpty() == false) {
								// id 제외 기타 인증내용 제거
								inJsonHometax.setSignCert("");
								inJsonHometax.setSignPri("");
								inJsonHometax.setSignPw("");
							} else {
								// 인증서 제외 기타 인증내용 제거
								inJsonHometax.setUserId("");
								inJsonHometax.setUserPw("");
								
								// 인증서 없는 경우 인증내용 공란으로 설정
								if(inJsonHometax.getSignCert() == null || inJsonHometax.getSignCert().isEmpty()) {
									inJsonHometax.setSignCert("");
									inJsonHometax.setSignPri("");
									inJsonHometax.setSignPw("");
								}
							}
							break;
						}
						break;
					// - 세무대리인 인증서 only
					case 1:
						inJsonHometax.setAgentId(reqParam.getAgentId());
						inJsonHometax.setAgentPw(reqParam.getAgentPw());
						
						switch(svcCd) {
						case "Z5002": // 세금신고 접수증 조회
							inJsonHometax.setItrfCd(reqParam.getItrfCd());
							break;
						case "Z4050": // 신용카드 매출자료 조회
							inJsonHometax.setFromY(reqParam.getFromY());
							inJsonHometax.setToY(reqParam.getToY());
							inJsonHometax.setFromQ(reqParam.getFromQ());
							inJsonHometax.setToQ(reqParam.getToQ());
							break;
						case "Z4060": // 현금영수증 매출총액 조회
							inJsonHometax.setStlYr(reqParam.getStlYr());
							break;
						case "Z4070": // 수출실적명세서 조회
							inJsonHometax.setDtCd("01");	// 조회구분 - 01:월별 (default)
							inJsonHometax.setWrtArr(reqParam.getWrtArr());
							break;
						case "Z0006": // 부가세 합계표 조회
							inJsonHometax.setSupByr(reqParam.getSupByr());
							inJsonHometax.setTaxGb(reqParam.getTaxGb());
							inJsonHometax.setWrtYr(reqParam.getWrtYr());
							inJsonHometax.setWrtQt(reqParam.getWrtQt());
							break;
						default:
							// 인증서 제외 기타 인증내용 제거
							inJsonHometax.setUserId("");
							inJsonHometax.setUserPw("");
						}
						break;
					}
					
					inJsonTmpList.add(objectMapper.writeValueAsString(inJsonHometax));
					inJsonList.add(objectMapper.writeValueAsString(inJsonTmpList));
					break;
				case "bank":
				case "cbk": 
				case "sbk": {
					InJsonBank inJsonBank = new InJsonBank(appCd, orgCd, svcCd, reqCd, proxy);
					
					switch(reqParam.getLogInType()) {
					case 1:	// ID 로그인
						inJsonBank.setLoginMethod("ID");
						inJsonBank.setUserId(reqParam.getLoginId());
						inJsonBank.setUserPw(reqParam.getLoginPw());
						// - 기타 인증내용 제거
						inJsonBank.setSignCert("");
						inJsonBank.setSignPri("");
						inJsonBank.setSignPw("");
						break;
					case 2:	// 인증서 로그인
						inJsonBank.setLoginMethod("CERT");
						inJsonBank.setSignCert(reqParam.getSignCert());
						inJsonBank.setSignPri(reqParam.getSignPri());
						inJsonBank.setSignPw(reqParam.getSignPw());
						// - 기타 인증내용 제거
						inJsonBank.setUserId("");
						inJsonBank.setUserPw("");
						break;
					case 3: // 빠른조회
						inJsonBank.setAcctPw(reqParam.getAcctPw());
						inJsonBank.setBizNo(reqParam.getBizNo());
						// - 기타 인증내용 제거
						inJsonBank.setLoginMethod("");
						inJsonBank.setSignCert("");
						inJsonBank.setSignPri("");
						inJsonBank.setUserId("");
						inJsonBank.setUserPw("");
					}
					
					inJsonBank.setAcctNo(reqParam.getAcctNo());
					inJsonBank.setSdate(reqParam.getFromDt());
					inJsonBank.setEdate(reqParam.getToDt());
					inJsonBank.setCurCd("KRW");
					
					// 은행코드 '0' padding
					String bankCd = String.format("%03d", Integer.parseInt(reqParam.getBankCd()));
					inJsonBank.setBankCd(bankCd);
					
					inJsonTmpList.add(objectMapper.writeValueAsString(inJsonBank));
					inJsonList.add(objectMapper.writeValueAsString(inJsonTmpList));
					break;
				}
				case "card":
				case "ccd": {
					InJsonCard inJsonCard = new InJsonCard(appCd, orgCd, svcCd, reqCd, proxy);
					
					switch(reqParam.getLogInType()) {
					case 1:	// ID 로그인
						inJsonCard.setLoginMethod("ID");
						inJsonCard.setUserId(reqParam.getLoginId());
						inJsonCard.setUserPw(reqParam.getLoginPw());
						// - 기타 인증내용 제거
						inJsonCard.setSignCert("");
						inJsonCard.setSignPri("");
						inJsonCard.setSignPw("");
						break;
					case 2:	// 인증서 로그인
						inJsonCard.setLoginMethod("CERT");
						inJsonCard.setSignCert(reqParam.getSignCert());
						inJsonCard.setSignPri(reqParam.getSignPri());
						inJsonCard.setSignPw(reqParam.getSignPw());
						// - 기타 인증내용 제거
						inJsonCard.setUserId("");
						inJsonCard.setUserPw("");
						break;
					}
					
					inJsonCard.setCardNo(reqParam.getCardNo());
					inJsonCard.setSdate(reqParam.getFromDt());
					inJsonCard.setEdate(reqParam.getToDt());
					
					// 카드사코드 '0' padding
					String cardCd = String.format("%03d", Integer.parseInt(reqParam.getCardCd()));
					inJsonCard.setCardCd(cardCd);
					
					inJsonTmpList.add(objectMapper.writeValueAsString(inJsonCard));
					inJsonList.add(objectMapper.writeValueAsString(inJsonTmpList));
					break;
				}
				case "cardsales":
					InJsonCardsales inJsonCardsales = new InJsonCardsales(appCd, orgCd, svcCd, reqCd, proxy);
					inJsonCardsales.setUserId(reqParam.getLoginId());
					inJsonCardsales.setUserPw(reqParam.getLoginPw());
					inJsonCardsales.setFromDate(reqParam.getFromDt());
					inJsonCardsales.setToDate(reqParam.getToDt());
					
					inJsonTmpList.add(objectMapper.writeValueAsString(inJsonCardsales));
					inJsonList.add(objectMapper.writeValueAsString(inJsonTmpList));
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return inJsonList;
	}
	

	
	
	private HashMap<String, Object> makeInsertData_test(HashMap<String, Object> outJson, ServerScrapReqParam reqParam) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		HashMap<String, Object> insertDataMap = new HashMap<>();
		
		try {
			String compCd = reqParam.getCompCd();
			String reqCd = reqParam.getReqCd();
			
			String orgCd = outJson.get("orgCd").toString();
			String svcCd = outJson.get("svcCd").toString();
			
			// 스크랩 기업 구분
			switch(orgCd) {
			// 홈택스
			case "hometax":
				// 서비스 구분
				switch(svcCd) {
					// - 전자세금계산서
					case "Z3001":
					case "Z3002":
					case "Z3003":
					case "Z3004": {
						ArrayList<SHometaxZ300X> sHometaxZ300XList = new ArrayList<>();
						ArrayList<SHometaxZ300XItem> sHometaxZ300XItemList = new ArrayList<>();
						
						// -- 내역
						ArrayList<SHometaxZ300X> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ300X>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ300X sHometaxZ300X : list) {
								sHometaxZ300X.setCompCd(compCd);
								sHometaxZ300X.setSvcCd(svcCd);
								sHometaxZ300X.setReqCd(reqCd);
								sHometaxZ300XList.add(sHometaxZ300X);
							}
						}
						
						// -- 품목
						ArrayList<SHometaxZ300XItem> itemList = 
								objectMapper.convertValue(outJson.get("itemList"), new TypeReference<ArrayList<SHometaxZ300XItem>>() {});
						if(itemList != null && itemList.size() > 0) {
							for(SHometaxZ300XItem sHometaxZ300XItem : itemList) {
								sHometaxZ300XItem.setCompCd(compCd);
								sHometaxZ300XItem.setSvcCd(svcCd);
								sHometaxZ300XItem.setReqCd(reqCd);
								sHometaxZ300XItemList.add(sHometaxZ300XItem);
							}
						}
						
						insertDataMap.put("hometaxZ300X",  sHometaxZ300XList);
						insertDataMap.put("hometaxZ300XItem", sHometaxZ300XItemList);
						break;
					}
					// - 현금영수증
					case "Z4001": {
						ArrayList<SHometaxZ4001> sHometaxZ4001List = new ArrayList<>();
						
						ArrayList<SHometaxZ4001> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4001>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4001 sHometaxZ4001 : list) {
								sHometaxZ4001.setCompCd(compCd);
								sHometaxZ4001.setReqCd(reqCd);
								sHometaxZ4001List.add(sHometaxZ4001);
							}
						}
						
						insertDataMap.put("hometaxZ4001", sHometaxZ4001List);
						break;
					}
					case "Z4002": {
						ArrayList<SHometaxZ4002> sHometaxZ4002List = new ArrayList<>();
						
						ArrayList<SHometaxZ4002> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4002>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4002 sHometaxZ4002 : list) {
								sHometaxZ4002.setCompCd(compCd);
								sHometaxZ4002.setReqCd(reqCd);
								sHometaxZ4002List.add(sHometaxZ4002);
							}
						}
						
						insertDataMap.put("hometaxZ4002", sHometaxZ4002List);
						break;
					}
					// - 사업용 화물복지 신용카드
					case "Z4010": {
						ArrayList<SHometaxZ4010> sHometaxZ4010List = new ArrayList<>();
						
						ArrayList<SHometaxZ4010> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4010>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4010 sHometaxZ4010 : list) {
								sHometaxZ4010.setCompCd(compCd);
								sHometaxZ4010.setReqCd(reqCd);
								sHometaxZ4010List.add(sHometaxZ4010);
							}
						}
						
						insertDataMap.put("hometaxZ4010", sHometaxZ4010List);
						break;
					}
					case "Z4020": {
						ArrayList<SHometaxZ4020> sHometaxZ4020List = new ArrayList<>();
						
						ArrayList<SHometaxZ4020> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4010>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4020 sHometaxZ4020 : list) {
								sHometaxZ4020.setCompCd(compCd);
								sHometaxZ4020.setReqCd(reqCd);
								sHometaxZ4020List.add(sHometaxZ4020);
							}
						}
						
						insertDataMap.put("hometaxZ4020", sHometaxZ4020List);
						break;
					}
					// - 신용카드 매출자료 조회
					case "Z4050": {
						ArrayList<SHometaxZ4050> sHometaxZ4050List = new ArrayList<>();
						
						ArrayList<SHometaxZ4050> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4050>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4050 sHometaxZ4050 : list) {
								sHometaxZ4050.setCompCd(compCd);
								sHometaxZ4050.setReqCd(reqCd);
								sHometaxZ4050List.add(sHometaxZ4050);
							}
						}
						
						insertDataMap.put("hometaxZ4050", sHometaxZ4050List);
						break;
					}
					// - 현금영수증 매출총액 조회
					case "Z4060": {
						ArrayList<SHometaxZ4060> sHometaxZ4060List = new ArrayList<>();
						
						ArrayList<SHometaxZ4060> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4060>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4060 sHometaxZ4060 : list) {
								sHometaxZ4060.setCompCd(compCd);
								sHometaxZ4060.setReqCd(reqCd);
								sHometaxZ4060List.add(sHometaxZ4060);
							}
						}
						
						insertDataMap.put("hometaxZ4060", sHometaxZ4060List);
						break;
					}
					// - 수출실적명세서 조회
					case "Z4070": {
						ArrayList<SHometaxZ4070> sHometaxZ4070List = new ArrayList<>();
						
						ArrayList<SHometaxZ4070> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ4070>>() {});
						if(list != null && list.size() > 0) {
							for(SHometaxZ4070 sHometaxZ4070 : list) {
								sHometaxZ4070.setCompCd(compCd);
								sHometaxZ4070.setReqCd(reqCd);
								sHometaxZ4070List.add(sHometaxZ4070);
							}
						}
						
						insertDataMap.put("hometaxZ4070", sHometaxZ4070List);
						break;
					}
					// - 세금신고 접수증조회
					case "Z5002": {
						ArrayList<SHometaxZ5002> sHometaxZ5002List = new ArrayList<>();
						
						ArrayList<SHometaxZ5002> list = 
								objectMapper.convertValue(outJson.get("list"), new TypeReference<ArrayList<SHometaxZ5002>>() {});
						
						if(list != null && list.size() > 0) {
							for(SHometaxZ5002 sHometaxZ5002 : list) {
								sHometaxZ5002.setCompCd(compCd);
								sHometaxZ5002.setReqCd(reqCd);
								sHometaxZ5002List.add(sHometaxZ5002);
							}
						}
						
						insertDataMap.put("hometaxZ5002", sHometaxZ5002List);
						break;
					}
					// - 부가세신고용 합계표
					// > 부가세신고용 합계표 + 부가세신고용 합계표 명세로 구성 (SHometaxZ0006 + SHometaxZ0006Dtl)
					case "Z0006": {
						ArrayList<SHometaxZ0006> sHometaxZ0006List = new ArrayList<>();
						ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlList = new ArrayList<>();
						
						ArrayList<HashMap<String, Object>> outZ0006List = 
								objectMapper.convertValue(outJson.get("outZ0006"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
						
						if(outZ0006List != null) {
							for(HashMap<String, Object> outZ0006 : outZ0006List) {
								HashMap<String, Object> cnd = (HashMap<String, Object>) outZ0006.get("cnd");
										
								// 공통 fields
								String wrtYr = cnd.get("wrtYr").toString();
								String wrtQt = cnd.get("wrtQt").toString();
								String wrtDtFrom = cnd.get("wrtDtFrom").toString();
								String wrtDtTo = cnd.get("wrtDtTo").toString();
								String supByr = cnd.get("supByr").toString();
								String taxGb = cnd.get("taxGb").toString();
								
								// TODO: setter -> convertValue to POJO (현재 null bind됨..)
								//SHometaxZ0006 sHometaxZ0006 = objectMapper.convertValue(outZ0006, SHometaxZ0006.class);
								
								// 부가세신고용 합계표 조회
								SHometaxZ0006 sHometaxZ0006 = new SHometaxZ0006();
								sHometaxZ0006.setCompCd(compCd);
								sHometaxZ0006.setSupByr(supByr);
								sHometaxZ0006.setTaxGb(taxGb);
								sHometaxZ0006.setWrtYr(wrtYr);
								sHometaxZ0006.setWrtQt(wrtQt);
								sHometaxZ0006.setWrtDtFrom(wrtDtFrom);
								sHometaxZ0006.setWrtDtTo(wrtDtTo);
								sHometaxZ0006.setWithIn_bsnoIsnClplcCnt(outZ0006.get("WithIn_bsnoIsnClplcCnt").toString());
								sHometaxZ0006.setWithIn_bsnoIsnScnt(outZ0006.get("WithIn_bsnoIsnScnt").toString());
								sHometaxZ0006.setWithIn_bsnoIsnSplCft(outZ0006.get("WithIn_bsnoIsnSplCft").toString());
								sHometaxZ0006.setWithIn_bsnoIsnTxamt(outZ0006.get("WithIn_bsnoIsnTxamt").toString());
								sHometaxZ0006.setWithIn_resnoIsnClplcCnt(outZ0006.get("WithIn_resnoIsnClplcCnt").toString());
								sHometaxZ0006.setWithIn_resnoIsnScnt(outZ0006.get("WithIn_resnoIsnScnt").toString());
								sHometaxZ0006.setWithIn_resnoIsnSplCft(outZ0006.get("WithIn_resnoIsnSplCft").toString());
								sHometaxZ0006.setWithIn_resnoIsnTxamt(outZ0006.get("WithIn_resnoIsnTxamt").toString());
								sHometaxZ0006.setWithIn_totCnt(outZ0006.get("WithIn_totCnt").toString());
								sHometaxZ0006.setWithIn_totPurchplcCnt(outZ0006.get("WithIn_totPurchplcCnt").toString());
								sHometaxZ0006.setWithIn_totSellplcCnt(outZ0006.get("WithIn_totSellplcCnt").toString());
								sHometaxZ0006.setWithIn_totSplCft(outZ0006.get("WithIn_totSplCft").toString());
								sHometaxZ0006.setWithIn_totTxamt(outZ0006.get("WithIn_totTxamt").toString());
								sHometaxZ0006.setEt_bsnoIsnClplcCnt(outZ0006.get("Et_bsnoIsnClplcCnt").toString());
								sHometaxZ0006.setEt_bsnoIsnScnt(outZ0006.get("Et_bsnoIsnScnt").toString());
								sHometaxZ0006.setEt_bsnoIsnSplCft(outZ0006.get("Et_bsnoIsnSplCft").toString());
								sHometaxZ0006.setEt_bsnoIsnTxamt(outZ0006.get("Et_bsnoIsnTxamt").toString());
								sHometaxZ0006.setEt_resnoIsnClplcCnt(outZ0006.get("Et_resnoIsnClplcCnt").toString());
								sHometaxZ0006.setEt_resnoIsnScnt(outZ0006.get("Et_resnoIsnScnt").toString());
								sHometaxZ0006.setEt_resnoIsnSplCft(outZ0006.get("Et_resnoIsnSplCft").toString());
								sHometaxZ0006.setEt_resnoIsnTxamt(outZ0006.get("Et_resnoIsnTxamt").toString());
								sHometaxZ0006.setEt_totCnt(outZ0006.get("Et_totCnt").toString());
								sHometaxZ0006.setEt_totPurchplcCnt(outZ0006.get("Et_totPurchplcCnt").toString());
								sHometaxZ0006.setEt_totSellplcCnt(outZ0006.get("Et_totSellplcCnt").toString());
								sHometaxZ0006.setEt_totSplCft(outZ0006.get("Et_totSplCft").toString());
								sHometaxZ0006.setEt_totTxamt(outZ0006.get("Et_totTxamt").toString());
								sHometaxZ0006.setReqCd(reqCd);
								sHometaxZ0006List.add(sHometaxZ0006);
								
								// 부가세신고용 합계표 조회 명세
								// - 11일 이전, 11일 이후 2가지 list로 값 넘어옴
								ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlEtList = 
										objectMapper.convertValue(outZ0006.get("listEt"), new TypeReference<ArrayList<SHometaxZ0006Dtl>>() {});
								ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlWithInList = 
										objectMapper.convertValue(outZ0006.get("listWithIn"), new TypeReference<ArrayList<SHometaxZ0006Dtl>>() {});
								
								if(sHometaxZ0006DtlEtList.size() > 0) {
									for(SHometaxZ0006Dtl sHometaxZ0006Dtl : sHometaxZ0006DtlEtList) {
										sHometaxZ0006Dtl.setCompCd(compCd);
										sHometaxZ0006Dtl.setSupByr(supByr);
										sHometaxZ0006Dtl.setTaxGb(taxGb);
										sHometaxZ0006Dtl.setWrtYr(wrtYr);
										sHometaxZ0006Dtl.setWrtQt(wrtQt);
										sHometaxZ0006Dtl.setWrtDtFrom(wrtDtFrom);
										sHometaxZ0006Dtl.setWrtDtTo(wrtDtTo);
										sHometaxZ0006Dtl.setReqCd(reqCd);
										sHometaxZ0006DtlList.add(sHometaxZ0006Dtl);
									}
								}
								
								if(sHometaxZ0006DtlWithInList.size() > 0) {
									for(SHometaxZ0006Dtl sHometaxZ0006Dtl : sHometaxZ0006DtlWithInList) {
										sHometaxZ0006Dtl.setCompCd(compCd);
										sHometaxZ0006Dtl.setSupByr(supByr);
										sHometaxZ0006Dtl.setTaxGb(taxGb);
										sHometaxZ0006Dtl.setWrtYr(wrtYr);
										sHometaxZ0006Dtl.setWrtQt(wrtQt);
										sHometaxZ0006Dtl.setWrtDtFrom(wrtDtFrom);
										sHometaxZ0006Dtl.setWrtDtTo(wrtDtTo);
										sHometaxZ0006Dtl.setReqCd(reqCd);
										sHometaxZ0006DtlList.add(sHometaxZ0006Dtl);
									}
								}
							}	
						}

						insertDataMap.put("hometaxZ0006", sHometaxZ0006List);
						insertDataMap.put("hometaxZ0006Dtl", sHometaxZ0006DtlList);
						break;
					}
				}
				break;
			// 여신
			case "cardsales":
				// 서비스 구분
				switch(svcCd) {
					// - 가맹점 수수료율/대금지급주기
					case "B0002": {
						ArrayList<SCardsalesB0002> sCardsalesB0002List = new ArrayList<>();
						
						HashMap<String, Object> outB0002 =  
								objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<HashMap<String, Object>>() {});
						
						ArrayList<SCardsalesB0002> list = 
								objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SCardsalesB0002>>() {});
						
						if(list != null && list.size() > 0) {
							for(SCardsalesB0002 sCardsalesB0002 : list) {
								sCardsalesB0002.setCompCd(compCd);
								sCardsalesB0002.setReqCd(reqCd);
								sCardsalesB0002List.add(sCardsalesB0002);
							}
						}
						
						insertDataMap.put("cardsalesB0002", sCardsalesB0002List);
						break;
					}
					// - 기간별승인내역(일별)
					case "B0011": {
						ArrayList<SCardsalesB0011Dtl> sCardsalesB0011DtlList = new ArrayList<>();
						ArrayList<SCardsalesB0011Sum> sCardsalesB0011SumList = new ArrayList<>();
						
						// -- 상세
						HashMap<String, Object> outB0011 = 
								objectMapper.convertValue(outJson.get("outB0011"), new TypeReference<HashMap<String, Object>>() {});
						
						ArrayList<SCardsalesB0011Dtl> listDtl = 
								objectMapper.convertValue(outB0011.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0011Dtl>>() {});
														
						if(listDtl != null && listDtl.size() > 0) {
							for(SCardsalesB0011Dtl sCardsalesB0011Dtl : listDtl) {
								sCardsalesB0011Dtl.setCompCd(compCd);
								sCardsalesB0011Dtl.setReqCd(reqCd);
								sCardsalesB0011DtlList.add(sCardsalesB0011Dtl);
							}
						}
						
						// -- 합계
						ArrayList<SCardsalesB0011Sum> listSum = 
								objectMapper.convertValue(outB0011.get("listSum"), new TypeReference<ArrayList<SCardsalesB0011Sum>>() {});
						
						if(listSum != null && listSum.size() > 0) {
							for(SCardsalesB0011Sum sCardsalesB0011Sum : listSum) {
								sCardsalesB0011Sum.setCompCd(compCd);
								sCardsalesB0011Sum.setReqCd(reqCd);
								sCardsalesB0011SumList.add(sCardsalesB0011Sum);
							}
						}
						
						insertDataMap.put("cardsalesB0011Dtl", sCardsalesB0011DtlList);
						insertDataMap.put("cardsalesB0011Sum", sCardsalesB0011SumList);
						break;
					}
					// - 기간별매입내역(일별)
					case "B0021": {
						ArrayList<SCardsalesB0021Dtl> sCardsalesB0021DtlList = new ArrayList<>();
						ArrayList<SCardsalesB0021Sum> sCardsalesB0021SumList = new ArrayList<>();
						
						// -- 상세
						HashMap<String, Object> outB0021 = 
								objectMapper.convertValue(outJson.get("outB0021"), new TypeReference<HashMap<String, Object>>() {});
						
						ArrayList<SCardsalesB0021Dtl> listDtl =
								objectMapper.convertValue(outB0021.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0021Dtl>>() {});

						if(listDtl != null && listDtl.size() > 0) {
							for(SCardsalesB0021Dtl sCardsalesB0021Dtl : listDtl) {
								sCardsalesB0021Dtl.setCompCd(compCd);
								sCardsalesB0021Dtl.setReqCd(reqCd);
								sCardsalesB0021DtlList.add(sCardsalesB0021Dtl);
							}
						}
						
						// -- 합계
						ArrayList<SCardsalesB0021Sum> listSum = 
								objectMapper.convertValue(outB0021.get("listSum"), new TypeReference<ArrayList<SCardsalesB0021Sum>>() {});
						
						if(listSum != null && listSum.size() > 0) {
							for(SCardsalesB0021Sum sCardsalesB0021Sum : listSum) {
								sCardsalesB0021Sum.setCompCd(compCd);
								sCardsalesB0021Sum.setReqCd(reqCd);
								sCardsalesB0021SumList.add(sCardsalesB0021Sum);
							}
						}
						
						insertDataMap.put("cardsalesB0021Dtl", sCardsalesB0021DtlList);
						insertDataMap.put("cardsalesB0021Sum", sCardsalesB0021SumList);
						break;
					}
					// - 기간별입금내역
					case "B0031": {
						ArrayList<SCardsalesB0031Dtl> sCardsalesB0031DtlList = new ArrayList<>();
						ArrayList<SCardsalesB0031Sum> sCardsalesB0031SumList = new ArrayList<>();
						
						HashMap<String, Object> outB0031 = 
								objectMapper.convertValue(outJson.get("outB0031"), new TypeReference<HashMap<String, Object>>() {});
						
						// -- 상세
						ArrayList<SCardsalesB0031Dtl> listDtl = 
								objectMapper.convertValue(outB0031.get("listDtl"), new TypeReference<ArrayList<SCardsalesB0031Dtl>>() {});

						if(listDtl != null && listDtl.size() > 0) {
							for(SCardsalesB0031Dtl sCardsalesB0031Dtl : listDtl) {
								sCardsalesB0031Dtl.setCompCd(compCd);
								sCardsalesB0031Dtl.setReqCd(reqCd);
								sCardsalesB0031DtlList.add(sCardsalesB0031Dtl);
							}
						}
						
						// -- 합계
						ArrayList<SCardsalesB0031Sum> listSum = 
								objectMapper.convertValue(outB0031.get("listSum"), new TypeReference<ArrayList<SCardsalesB0031Sum>>() {});
						
						if(listSum != null && listSum.size() > 0) {
							for(SCardsalesB0031Sum sCardsalesB0031Sum : listSum) {
								sCardsalesB0031Sum.setCompCd(compCd);
								sCardsalesB0031Sum.setReqCd(reqCd);
								sCardsalesB0031SumList.add(sCardsalesB0031Sum);
							}
						}
						
						insertDataMap.put("cardsalesB0031Dtl", sCardsalesB0031DtlList);
						insertDataMap.put("cardsalesB0031Sum", sCardsalesB0031SumList);
						break;
					}
				}
				break;
			// 뱅킹 - 개인
			case "bank":
				// 서비스 구분
				switch(svcCd) {
					// - 거래내역조회 (입출금통장)
					case "B0002": {
						ArrayList<SBankB0002> sBankB0002List = new ArrayList<>();
						
						ArrayList<HashMap<String, Object>> outB0002List = 
								objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
						
						if(outB0002List != null && outB0002List.size() > 0) {
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SBankB0002> list = 
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SBankB0002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SBankB0002 sBankB0002 : list) {
									sBankB0002.setCompCd(compCd);
									sBankB0002.setReqCd(reqCd);
									sBankB0002.setAcctNo(in.get("acctNo").toString());
									sBankB0002.setBankCd(in.get("bankCd").toString());
									sBankB0002List.add(sBankB0002);
								}
							}
							
							insertDataMap.put("bankB0002", sBankB0002List);
						}
						break;
					}
				}
				break;
			// 뱅킹 - 기업
			case "cbk":
				// 서비스 구분
				switch(svcCd) {					
					// - 거래내역조회 (입출금통장)
					case "B0002": {
						ArrayList<SCbkB0002> sCbkB0002List = new ArrayList<>();
						
						ArrayList<HashMap<String, Object>> outB0002List = 
								objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
						
						if(outB0002List != null && outB0002List.size() > 0) {
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SCbkB0002> list =
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SCbkB0002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCbkB0002 sCbkB0002 : list) {
									sCbkB0002.setCompCd(compCd);
									sCbkB0002.setReqCd(reqCd);
									sCbkB0002.setAcctNo(in.get("acctNo").toString());
									sCbkB0002.setBankCd(in.get("bankCd").toString());
									sCbkB0002List.add(sCbkB0002);
								}
							}
							
							insertDataMap.put("cbkB0002", sCbkB0002List);
						}
						break;
					}
				}
				break;
			// 뱅킹 - 빠른조회
			case "sbk":
				// 서비스 구분
				switch(svcCd) {
					// - 거래내역조회 (입출금통장)
					case "B0002": {
						ArrayList<SSbkB0002> sSbkB0002List = new ArrayList<>();
						
						ArrayList<HashMap<String, Object>> outB0002List = 
								objectMapper.convertValue(outJson.get("outB0002"), new TypeReference<ArrayList<HashMap<String, Object>>>() {});
						
						if(outB0002List != null && outB0002List.size() > 0) {
							HashMap<String, Object> outB0002 = outB0002List.get(0);
							
							HashMap<String, Object> in = (HashMap<String, Object>) outB0002.get("in");
							
							ArrayList<SSbkB0002> list = 
									objectMapper.convertValue(outB0002.get("list"), new TypeReference<ArrayList<SSbkB0002>>() {});
							
							if(list != null && list.size() > 0) {
								for(SSbkB0002 sSbkB0002 : list) {
									sSbkB0002.setCompCd(compCd);
									sSbkB0002.setReqCd(reqCd);
									sSbkB0002.setAcctNo(in.get("acctNo").toString());
									sSbkB0002.setBankCd(in.get("bankCd").toString());
									sSbkB0002List.add(sSbkB0002);
								}
							}
							
							insertDataMap.put("sbkB0002", sSbkB0002List);
						}
						break;
					}
				}
				break;
			// 신용카드 - 개인
			case "card":
				// 서비스 구분
				switch(svcCd) {
					// - 승인내역조회
					case "C0005": {
						ArrayList<SCardC0005> sCardC0005List = new ArrayList<>();
						
						HashMap<String, Object> outC0005 = 
								objectMapper.convertValue(outJson.get("outC0005"), new TypeReference<HashMap<String, Object>>() {});
						
						if(outC0005 != null) {
							ArrayList<SCardC0005> list =
									objectMapper.convertValue(outC0005.get("list"), new TypeReference<ArrayList<SCardC0005>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCardC0005 sCardC0005 : list) {
									sCardC0005.setCompCd(compCd);
									sCardC0005.setReqCd(reqCd);
									sCardC0005List.add(sCardC0005);
								}
								
								insertDataMap.put("cardC0005", sCardC0005List);
							}
						}
						break;
					}
				}
				break;
			// 신용카드 - 기업
			case "ccd":
				// 서비스 구분
				switch(svcCd) {
					// - 승인내역조회
					case "C0005": {
						ArrayList<SCcdC0005> sCcdC0005List = new ArrayList<>();
						
						HashMap<String, Object> outC0005 = 
								objectMapper.convertValue(outJson.get("outC0005"), new TypeReference<HashMap<String, Object>>() {});
						
						if(outC0005 != null) {
							ArrayList<SCcdC0005> list =
									objectMapper.convertValue(outC0005.get("list"), new TypeReference<ArrayList<SCcdC0005>>() {});
							
							if(list != null && list.size() > 0) {
								for(SCcdC0005 sCcdC0005 : list) {
									sCcdC0005.setCompCd(compCd);
									sCcdC0005.setReqCd(reqCd);
									sCcdC0005List.add(sCcdC0005);
								}
								
								insertDataMap.put("ccdC0005", sCcdC0005List);
							}
						}
						break;
					}
				}
				break;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		
		return insertDataMap;
	}
	
	/**
	 * 인증 스크래핑 결과와 스크래핑 요청을 비교해서 오류가 발생한 HistoryRes를 반환
	 * @param scrapCertForm - 인증 스크래핑 결과리스트
	 * @param serverScrapReqParam - 스크래핑 요청내용
	 * @return 오류내역 HistoryRes DTO, 오류 없는 경우 null
	 */
	private HistoryRes getErrorHistoryRes(ArrayList<ScrapCertForm> scrapCertFormList, ServerScrapReqParam serverScrapReqParam) {
		String orgCd = serverScrapReqParam.getOrgCd();
		String svcCd = serverScrapReqParam.getSvcCd();
		String reqCd = serverScrapReqParam.getReqCd();
		
		for(ScrapCertForm scrapCertForm : scrapCertFormList) {
			String certSvcCd = scrapCertForm.getSvcCd() == null ? "" : scrapCertForm.getSvcCd();

			HistoryRes historyRes = scrapCertForm.getHistoryRes();
			String errYn = historyRes.getErrYn() == null ? "" : historyRes.getErrYn();

			int keyCd = serverScrapReqParam.getKeyCd(); // 뱅킹, 카드사 비교조건에 사용 
			
			if(orgCd.equals(historyRes.getOrgCd())) {
				switch(orgCd) {
				case "hometax":
					switch(svcCd) {
					case "Z3001":
					case "Z3002":
					case "Z3003":
					case "Z3004":
						if(certSvcCd.equals("hometaxZ300X") && errYn.equals("Y")) {
							historyRes.setSvcCd(svcCd);
							historyRes.setReqCd(reqCd);
							return historyRes;
						}
						break;
					case "Z4001":
					case "Z4002":
					case "Z4010":
					case "Z4020":
						if(certSvcCd.equals("hometaxZ40XX") && errYn.equals("Y")) {
							historyRes.setSvcCd(svcCd);
							historyRes.setReqCd(reqCd);
							return historyRes;
						}
						break;
					default:
						if(certSvcCd.equals("hometaxZXXXX") && errYn.equals("Y")) {
							historyRes.setSvcCd(svcCd);
							historyRes.setReqCd(reqCd);
							return historyRes;
						}
						break;
					}
					break;
				case "cardsales":
					if(certSvcCd.equals("cardsalesBXXXX") && errYn.equals("Y")) {
						historyRes.setSvcCd(svcCd);
						historyRes.setReqCd(reqCd);
						return historyRes;
					}
					break;
				case "card":
				case "ccd":
					if((certSvcCd.equals("creditCcd") || certSvcCd.equals("creditCard")) 
							&& errYn.equals("Y") && keyCd == historyRes.getKeyCd()) {
						historyRes.setSvcCd(svcCd);
						historyRes.setReqCd(reqCd);
						return historyRes;
					}
					break;
				case "bank":
				case "cbk":
				case "sbk":
					if((certSvcCd.equals("accountBank") || certSvcCd.equals("accountCbk") || certSvcCd.equals("accountSbk")) 
							&& errYn.equals("Y") && keyCd == historyRes.getKeyCd()) {
						historyRes.setSvcCd(svcCd);
						historyRes.setReqCd(reqCd);
						return historyRes;
					}
					break;
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * 스크래핑 결과내용 생성
	 * @param jsonScrapData
	 * @param jsonReqParamList
	 * @return
	 */
	private HashMap<String, Object> makeScrapResultData_test(String jsonScrapData, String jsonReqParamList) {
		ObjectMapper objectMapper = new ObjectMapper();
	
		HashMap<String, Object> hm = new HashMap<>();
		
		try {
			HashMap<String, Object> resParam = objectMapper.readValue(jsonScrapData, HashMap.class);
			
			ArrayList<HashMap<String, Object>> outJsonList = 
					(ArrayList<HashMap<String, Object>>) resParam.get("outJsonList");
			
			ArrayList<ServerScrapReqParam> reqParamList = 
					objectMapper.reader()
					.forType(new TypeReference<ArrayList<ServerScrapReqParam>>() {})
					.readValue(jsonReqParamList);
			
			if(reqParamList.size() != outJsonList.size()) {
				return null;
			}
			
			ArrayList<HistoryRes> resParamList = new ArrayList<>();
			
			for(int i = 0; i < reqParamList.size(); i++) {
				ServerScrapReqParam reqParam = reqParamList.get(i);
				HashMap<String, Object> outJson = outJsonList.get(i);
				
				String appCd = outJson.get("appCd").toString();
				String orgCd = outJson.get("orgCd").toString();
				String svcCd = outJson.get("svcCd").toString();
				
				// 공통 필드 설정
				HistoryRes res = new HistoryRes();
				res.setCompCd(reqParam.getCompCd());
				res.setAppCd(appCd);
				res.setOrgCd(orgCd);
				res.setSvcCd(svcCd);
				res.setKeyCd(0);
				res.setReqCd(reqParam.getReqCd());
				res.setResCd(resParam.get("resCd").toString());
				res.setConnCnt("0");
				res.setErrYn(outJson.get("errYn").toString());
				res.setErrMsg(outJson.get("errMsg").toString());
				res.setOutTime("");
				res.setBridgeAppVer(resParam.get("bridgeAppVer").toString());
				res.setBridgeHostNm(resParam.get("bridgeHostNm").toString());
				res.setBridgeOsNm(resParam.get("bridgeOsNm").toString());
				res.setBridgeReqDt(resParam.get("bridgeReqDt").toString());
				res.setBridgeResDt(resParam.get("bridgeResDt").toString());
				res.setWorker(resParam.get("worker").toString());
				res.setWorkerAppVer(resParam.get("workerAppVer").toString());
				res.setWorkerHostNm(resParam.get("workerHostNm").toString());
				res.setWorkerOsNm(resParam.get("workerOsNm").toString());
				res.setWorkerReqDt(resParam.get("workerReqDt").toString());
				res.setWorkerResDt(resParam.get("workerResDt").toString());
				
				// 추가 필드 설정
				switch(orgCd) {
				// - 홈택스
				case "hometax":
					res.setConnCnt(outJson.get("connCnt").toString());
					res.setOutTime(outJson.get("outTime").toString());
					break;
				// - 여신
				case "cardsales":
					res.setConnCnt(outJson.get("connCnt").toString());
					break;
				// - 뱅킹
				// - 신용카드
				case "bank":
				case "cbk":
				case "sbk":
				case "card":
				case "ccd":
					res.setKeyCd(reqParam.getKeyCd());
					break;
				}
				
				// System.out.println(res);
				
				resParamList.add(res);
			}
			
			hm.put("resParamList", objectMapper.writeValueAsString(resParamList));
			hm.put("outJsonList", objectMapper.writeValueAsString(outJsonList));
	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
				
		return hm;
	}
}
 