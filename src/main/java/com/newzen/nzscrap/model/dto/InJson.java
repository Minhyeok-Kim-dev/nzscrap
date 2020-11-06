package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 * 	infotech 스크래핑 요청 inJson 파라미터 (서비스별로 상이하므로 상속을 통한 확장해서 사용)	
 */
public class InJson {
	// Fields
	private String appCd;	// 어플리케이션 코드
	private String orgCd;	// 기관코드
	private String svcCd;	// 서비스 코드
	private String reqCd;	// 요청코드

	// Constructors
	public InJson() {
	}
	
	public InJson(String appCd, String orgCd, String svcCd, String reqCd) {
		super();
		this.appCd = appCd;
		this.orgCd = orgCd;
		this.svcCd = svcCd;
		this.reqCd = reqCd;
	}
	
	// Getters & Setters
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
	public String getReqCd() {
		return reqCd;
	}
	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}
	@Override
	public String toString() {
		return "InJson [appCd=" + appCd + ", orgCd=" + orgCd + ", svcCd=" + svcCd + ", reqCd=" + reqCd + "]";
	}
}
