package com.newzen.nzscrap.model.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 응답 히스토리 DTO
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryRes {
	// Fields
	private String compCd;			// 회사코드
	private String scrapDt;			// 스크랩일자
	private String appCd;			// 어플리케이션명
	private String orgCd;			// 기관코드
	private String svcCd;			// 서비스코드
	private int keyCd;				// 뱅킹, 카드 연번
	private String reqCd;			// 요청코드
	private String resCd;			// 응답코드
	private String resMsg;			// 응답메시지
	private String connCnt;			// 접속횟수
	private String errYn;			// 에러코드
	private String errMsg;			// 에러메시지
	private String outTime;			// 결과 반환 시간
	private String bridgeAppVer;	// Bridge 서버 버전
	private String bridgeHostNm;	// Bridge 서버 호스트명
	private String bridgeOsNm;		// Bridge 서버 OS 구분
	private String bridgeReqDt;		// Bridge 서버 요청시간
	private String bridgeResDt;		// Bridge 서버 응답시간
	private String worker;			// 사용 Worker
	private String workerAppVer;	// Worker 서버 버전
	private String workerHostNm;	// Worker 서버 호스트명
	private String workerOsNm;		// Worker 서버 OS 구분
	private String workerReqDt;		// Worker 서버 요청시간
	private String workerResDt;		// Worker 서버 응답시간

	// Constructors
	public HistoryRes() {
		super();
	}
	
	// - Copy constructor
	public HistoryRes(HistoryRes historyRes) throws IllegalAccessException, InvocationTargetException {
		String reqCd = historyRes.getReqCd();
		
		if(reqCd != null && reqCd.length() > 14) {
			historyRes.setScrapDt(reqCd.substring(0, 8));	// 스크랩일자 (yyyyMMdd)
		}

		// Deep copy
		BeanUtils.copyProperties(this, historyRes);
	}
	// - 스크래핑 결과내용으로 생성시
	public HistoryRes(ServerScrapReqParam reqParam, HashMap<String, Object> resParam) {
		// 공통필드 설정
		this.setCompCd(reqParam.getCompCd());
		this.setAppCd(reqParam.getAppCd());
		this.setOrgCd(reqParam.getOrgCd());
		this.setSvcCd(reqParam.getSvcCd());
		this.setKeyCd(reqParam.getKeyCd());
		this.setResCd(resParam.get("resCd").toString());
		this.setConnCnt("0");
		this.setErrYn(resParam.get("errYn").toString());
		this.setErrMsg(resParam.get("errMsg").toString());
		this.setOutTime("");
		this.setBridgeAppVer(resParam.get("bridgeAppVer").toString());
		this.setBridgeHostNm(resParam.get("bridgeHostNm").toString());
		this.setBridgeOsNm(resParam.get("bridgeOsNm").toString());
		this.setBridgeReqDt(resParam.get("bridgeReqDt").toString());
		this.setBridgeResDt(resParam.get("bridgeResDt").toString());
		this.setWorker(resParam.get("worker").toString());
		this.setWorkerAppVer(resParam.get("workerAppVer").toString());
		this.setWorkerHostNm(resParam.get("workerHostNm").toString());
		this.setWorkerOsNm(resParam.get("workerOsNm").toString());
		this.setWorkerReqDt(resParam.get("workerReqDt").toString());
		this.setWorkerResDt(resParam.get("workerResDt").toString());
		
		// - 요청코드 관련
		String reqCd = reqParam.getReqCd();
		this.setReqCd(reqCd);
		if(reqCd != null && reqCd.length() > 14) {
			this.setScrapDt(reqCd.substring(0, 8));	// 스크랩일자 (yyyyMMdd)
		}
		
		// 추가필드 설정
		switch(reqParam.getOrgCd()) {
		// - 홈택스
		case "hometax":
			this.setConnCnt(resParam.get("connCnt").toString());
			this.setOutTime(resParam.get("outTime").toString());
			break;
		// - 여신
		case "cardsales":
			this.setConnCnt(resParam.get("connCnt").toString());
			break;
		}
	}
	
	// Getters & Setters
	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getScrapDt() {
		return scrapDt;
	}
	public void setScrapDt(String scrapDt) {
		this.scrapDt = scrapDt;
	}
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public int getKeyCd() {
		return keyCd;
	}
	public void setKeyCd(int keyCd) {
		this.keyCd = keyCd;
	}
	public String getReqCd() {
		return reqCd;
	}
	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}
	public String getResCd() {
		return resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getConnCnt() {
		return connCnt;
	}
	public void setConnCnt(String connCnt) {
		this.connCnt = connCnt;
	}
	public String getErrYn() {
		return errYn;
	}
	public void setErrYn(String errYn) {
		this.errYn = errYn;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getBridgeAppVer() {
		return bridgeAppVer;
	}
	public void setBridgeAppVer(String bridgeAppVer) {
		this.bridgeAppVer = bridgeAppVer;
	}
	public String getBridgeHostNm() {
		return bridgeHostNm;
	}
	public void setBridgeHostNm(String bridgeHostNm) {
		this.bridgeHostNm = bridgeHostNm;
	}
	public String getBridgeOsNm() {
		return bridgeOsNm;
	}
	public void setBridgeOsNm(String bridgeOsNm) {
		this.bridgeOsNm = bridgeOsNm;
	}
	public String getBridgeReqDt() {
		return bridgeReqDt;
	}
	public void setBridgeReqDt(String bridgeReqDt) {
		this.bridgeReqDt = bridgeReqDt;
	}
	public String getBridgeResDt() {
		return bridgeResDt;
	}
	public void setBridgeResDt(String bridgeResDt) {
		this.bridgeResDt = bridgeResDt;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getWorkerAppVer() {
		return workerAppVer;
	}
	public void setWorkerAppVer(String workerAppVer) {
		this.workerAppVer = workerAppVer;
	}
	public String getWorkerHostNm() {
		return workerHostNm;
	}
	public void setWorkerHostNm(String workerHostNm) {
		this.workerHostNm = workerHostNm;
	}
	public String getWorkerOsNm() {
		return workerOsNm;
	}
	public void setWorkerOsNm(String workerOsNm) {
		this.workerOsNm = workerOsNm;
	}
	public String getWorkerReqDt() {
		return workerReqDt;
	}
	public void setWorkerReqDt(String workerReqDt) {
		this.workerReqDt = workerReqDt;
	}
	public String getWorkerResDt() {
		return workerResDt;
	}
	public void setWorkerResDt(String workerResDt) {
		this.workerResDt = workerResDt;
	}
	@Override
	public String toString() {
		return "HistoryRes [compCd=" + compCd + ", scrapDt=" + scrapDt + ", appCd=" + appCd + ", orgCd=" + orgCd
				+ ", svcCd=" + svcCd + ", keyCd=" + keyCd + ", reqCd=" + reqCd + ", resCd=" + resCd + ", resMsg="
				+ resMsg + ", connCnt=" + connCnt + ", errYn=" + errYn + ", errMsg=" + errMsg + ", outTime=" + outTime
				+ ", bridgeAppVer=" + bridgeAppVer + ", bridgeHostNm=" + bridgeHostNm + ", bridgeOsNm=" + bridgeOsNm
				+ ", bridgeReqDt=" + bridgeReqDt + ", bridgeResDt=" + bridgeResDt + ", worker=" + worker
				+ ", workerAppVer=" + workerAppVer + ", workerHostNm=" + workerHostNm + ", workerOsNm=" + workerOsNm
				+ ", workerReqDt=" + workerReqDt + ", workerResDt=" + workerResDt + "]";
	}
}
