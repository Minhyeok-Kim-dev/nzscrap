package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_현금영수증 매출총액 조회
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ4060 {
	private String compCd;				//회사코드
	private String sttsYm;				//거래년월
	private String cshSlsSplCftAmt;		//공급가액
	private String cshSlsVatAmt;		//부가세
	private String cshSlsTipAmt;		//봉사료
	private String cshSlsAmt;			//총금액
	private String cshSlsScnt;			//거래건수
	private String rmk;					//비고
	private String reqCd;				//요청코드
	private String regId;				//등록자 아이디
	private String regDt;				//등록일시
	private String uptId;				//수정자 아이디
	private String uptDt;				//수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getSttsYm() {
		return sttsYm;
	}
	public void setSttsYm(String sttsYm) {
		this.sttsYm = sttsYm;
	}
	public String getCshSlsSplCftAmt() {
		return cshSlsSplCftAmt;
	}
	public void setCshSlsSplCftAmt(String cshSlsSplCftAmt) {
		this.cshSlsSplCftAmt = cshSlsSplCftAmt;
	}
	public String getCshSlsVatAmt() {
		return cshSlsVatAmt;
	}
	public void setCshSlsVatAmt(String cshSlsVatAmt) {
		this.cshSlsVatAmt = cshSlsVatAmt;
	}
	public String getCshSlsTipAmt() {
		return cshSlsTipAmt;
	}
	public void setCshSlsTipAmt(String cshSlsTipAmt) {
		this.cshSlsTipAmt = cshSlsTipAmt;
	}
	public String getCshSlsAmt() {
		return cshSlsAmt;
	}
	public void setCshSlsAmt(String cshSlsAmt) {
		this.cshSlsAmt = cshSlsAmt;
	}
	public String getCshSlsScnt() {
		return cshSlsScnt;
	}
	public void setCshSlsScnt(String cshSlsScnt) {
		this.cshSlsScnt = cshSlsScnt;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	public String getReqCd() {
		return reqCd;
	}
	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUptId() {
		return uptId;
	}
	public void setUptId(String uptId) {
		this.uptId = uptId;
	}
	public String getUptDt() {
		return uptDt;
	}
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	@Override
	public String toString() {
		return "SHometaxZ4060 [compCd=" + compCd + ", sttsYm=" + sttsYm + ", cshSlsSplCftAmt=" + cshSlsSplCftAmt
				+ ", cshSlsVatAmt=" + cshSlsVatAmt + ", cshSlsTipAmt=" + cshSlsTipAmt + ", cshSlsAmt=" + cshSlsAmt
				+ ", cshSlsScnt=" + cshSlsScnt + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt="
				+ regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
