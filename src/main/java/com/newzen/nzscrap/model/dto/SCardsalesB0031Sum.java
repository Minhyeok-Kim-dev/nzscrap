package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별입금내역_합계 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0031Sum {
	private String compCd;		// 회사코드 
	private String payDt;		// 입금일자
	private String salesSumAmt;	// 매출합계금액
	private String salesSumCnt;	// 매출합계건수
	private String paySumAmt;	// 입금합계금액
	private String rmk;			// 비고
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getPayDt() {
		return payDt;
	}
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}
	public String getSalesSumAmt() {
		return salesSumAmt;
	}
	public void setSalesSumAmt(String salesSumAmt) {
		this.salesSumAmt = salesSumAmt;
	}
	public String getSalesSumCnt() {
		return salesSumCnt;
	}
	public void setSalesSumCnt(String salesSumCnt) {
		this.salesSumCnt = salesSumCnt;
	}
	public String getPaySumAmt() {
		return paySumAmt;
	}
	public void setPaySumAmt(String paySumAmt) {
		this.paySumAmt = paySumAmt;
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
		return "sCardsalesB0031Sum [compCd=" + compCd + ", payDt=" + payDt + ", salesSumAmt=" + salesSumAmt
				+ ", salesSumCnt=" + salesSumCnt + ", paySumAmt=" + paySumAmt + ", rmk=" + rmk + ", reqCd=" + reqCd
				+ ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
