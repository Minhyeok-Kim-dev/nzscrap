package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별매입내역(일별)_합계 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0021Sum {
	private String compCd;		// 회사코드
	private String buyDt;		// 매입일자
	private String buySumAmt;	// 매입합계금액
	private String buySumCnt;	// 매입합계건수
	private String paySumAmt;	// 지급예정금액
	private String rmk;			// 비고
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시

	private String commMem;		// _param에 포함 (수수료_가맹점)
	private String commPoint;	// _param에 포함 (수수료_포인트)
	private String commEtc;		// _param에 포함 (수수료_기타)
	private String commSum;		// _param에 포함 (수수료_계)
	private String commVat;		// _param에 포함 (부가세대리납부금액)
	
	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public String getBuySumAmt() {
		return buySumAmt;
	}
	public void setBuySumAmt(String buySumAmt) {
		this.buySumAmt = buySumAmt;
	}
	public String getBuySumCnt() {
		return buySumCnt;
	}
	public void setBuySumCnt(String buySumCnt) {
		this.buySumCnt = buySumCnt;
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
	public String getCommMem() {
		return commMem;
	}
	public void setCommMem(String commMem) {
		this.commMem = commMem;
	}
	public String getCommPoint() {
		return commPoint;
	}
	public void setCommPoint(String commPoint) {
		this.commPoint = commPoint;
	}
	public String getCommEtc() {
		return commEtc;
	}
	public void setCommEtc(String commEtc) {
		this.commEtc = commEtc;
	}
	public String getCommSum() {
		return commSum;
	}
	public void setCommSum(String commSum) {
		this.commSum = commSum;
	}
	public String getCommVat() {
		return commVat;
	}
	public void setCommVat(String commVat) {
		this.commVat = commVat;
	}
	@Override
	public String toString() {
		return "SCardsalesB0021Sum [compCd=" + compCd + ", buyDt=" + buyDt + ", buySumAmt=" + buySumAmt + ", buySumCnt="
				+ buySumCnt + ", paySumAmt=" + paySumAmt + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId
				+ ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + ", commMem=" + commMem + ", commPoint="
				+ commPoint + ", commEtc=" + commEtc + ", commSum=" + commSum + ", commVat=" + commVat + "]";
	}
}
