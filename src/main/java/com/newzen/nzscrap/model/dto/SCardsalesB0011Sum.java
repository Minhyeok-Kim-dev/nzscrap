package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별승인내역(일별)_합계 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0011Sum {
	private String compCd;		// 회사코드
	private String trDt;		// 거래일자
	private String trSumAmt;	// 거래합계
	private String trSumCnt;	// 거래건수
	private String apprSumAmt;	// 승인소계
	private String apprSumCnt;	// 승인건수
	private String canSumAmt;	// 취소소계
	private String canSumCnt;	// 취소건수
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
	public String getTrDt() {
		return trDt;
	}
	public void setTrDt(String trDt) {
		this.trDt = trDt;
	}
	public String getTrSumAmt() {
		return trSumAmt;
	}
	public void setTrSumAmt(String trSumAmt) {
		this.trSumAmt = trSumAmt;
	}
	public String getTrSumCnt() {
		return trSumCnt;
	}
	public void setTrSumCnt(String trSumCnt) {
		this.trSumCnt = trSumCnt;
	}
	public String getApprSumAmt() {
		return apprSumAmt;
	}
	public void setApprSumAmt(String apprSumAmt) {
		this.apprSumAmt = apprSumAmt;
	}
	public String getApprSumCnt() {
		return apprSumCnt;
	}
	public void setApprSumCnt(String apprSumCnt) {
		this.apprSumCnt = apprSumCnt;
	}
	public String getCanSumAmt() {
		return canSumAmt;
	}
	public void setCanSumAmt(String canSumAmt) {
		this.canSumAmt = canSumAmt;
	}
	public String getCanSumCnt() {
		return canSumCnt;
	}
	public void setCanSumCnt(String canSumCnt) {
		this.canSumCnt = canSumCnt;
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
		return "sCardsalesB0011Sum [compCd=" + compCd + ", trDt=" + trDt + ", trSumAmt=" + trSumAmt + ", trSumCnt="
				+ trSumCnt + ", apprSumAmt=" + apprSumAmt + ", apprSumCnt=" + apprSumCnt + ", canSumAmt=" + canSumAmt
				+ ", canSumCnt=" + canSumCnt + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt="
				+ regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
