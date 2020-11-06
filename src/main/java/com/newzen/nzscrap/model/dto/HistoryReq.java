package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 요청 히스토리 DTO
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryReq {
	private String compCd;		// 회사코드
	private String appCd;		// 어플리케이션명
	private String orgCd;		// 기관코드
	private String svcCd;		// 서비스코드
	private int keyCd;			// 뱅킹,카드 연번
	private String scrapDt;		// 스크랩 수행일자
	private String reqCd;		// 요청코드
	private String scrapFromDt;	// 스크랩 조회시작일자
	private String scrapToDt;	// 스크랩 조회종료일자
	private String reqDt;		// 요청일시
	private String inJson;		// 요청파라미터

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
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
	public String getScrapDt() {
		return scrapDt;
	}
	public void setScrapDt(String scrapDt) {
		this.scrapDt = scrapDt;
	}
	public String getReqCd() {
		return reqCd;
	}
	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}
	public String getScrapFromDt() {
		return scrapFromDt;
	}
	public void setScrapFromDt(String scrapFromDt) {
		this.scrapFromDt = scrapFromDt;
	}
	public String getScrapToDt() {
		return scrapToDt;
	}
	public void setScrapToDt(String scrapToDt) {
		this.scrapToDt = scrapToDt;
	}
	public String getReqDt() {
		return reqDt;
	}
	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}
	public String getInJson() {
		return inJson;
	}
	public void setInJson(String inJson) {
		this.inJson = inJson;
	}
	@Override
	public String toString() {
		return "HistoryReq [compCd=" + compCd + ", appCd=" + appCd + ", orgCd=" + orgCd + ", svcCd=" + svcCd
				+ ", keyCd=" + keyCd + ", scrapDt=" + scrapDt + ", reqCd=" + reqCd + ", scrapFromDt=" + scrapFromDt
				+ ", scrapToDt=" + scrapToDt + ", reqDt=" + reqDt + ", inJson=" + inJson + "]";
	}
}
