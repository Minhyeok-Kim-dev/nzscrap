package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_가맹점 수수료율 / 대금지급주기 조회 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0002 {
	private String compCd;			// 회사코드
	private String merNo;			// 가맹점번호
	private String cardNm;			// 카드사
	private String stlBankNm;		// 결제은행
	private String stlAcctNo;		// 결제계좌번호
	private String sinYongFeeRate;	// 신용카드 스스료율
	private String checkFeeRateB;	// 체크카드 수수료율 (은행계)
	private String checkFeeRateC;	// 체크카드 수수료율 (전문계)
	private String pymPeriod;		// 대금지급주기
	private String rmk;				// 비고
	private String reqCd;			// 요청코드
	private String regId;			// 등록자ID
	private String regDt;			// 등록일시
	private String uptId;			// 수정자ID
	private String uptDt;			// 수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getCardNm() {
		return cardNm;
	}
	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}
	public String getStlBankNm() {
		return stlBankNm;
	}
	public void setStlBankNm(String stlBankNm) {
		this.stlBankNm = stlBankNm;
	}
	public String getStlAcctNo() {
		return stlAcctNo;
	}
	public void setStlAcctNo(String stlAcctNo) {
		this.stlAcctNo = stlAcctNo;
	}
	public String getSinYongFeeRate() {
		return sinYongFeeRate;
	}
	public void setSinYongFeeRate(String sinYongFeeRate) {
		this.sinYongFeeRate = sinYongFeeRate;
	}
	public String getCheckFeeRateB() {
		return checkFeeRateB;
	}
	public void setCheckFeeRateB(String checkFeeRateB) {
		this.checkFeeRateB = checkFeeRateB;
	}
	public String getCheckFeeRateC() {
		return checkFeeRateC;
	}
	public void setCheckFeeRateC(String checkFeeRateC) {
		this.checkFeeRateC = checkFeeRateC;
	}
	public String getPymPeriod() {
		return pymPeriod;
	}
	public void setPymPeriod(String pymPeriod) {
		this.pymPeriod = pymPeriod;
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
		return "sCardsalesB0002 [compCd=" + compCd + ", merNo=" + merNo + ", cardNm=" + cardNm + ", stlBankNm="
				+ stlBankNm + ", stlAcctNo=" + stlAcctNo + ", sinYongFeeRate=" + sinYongFeeRate + ", checkFeeRateB="
				+ checkFeeRateB + ", checkFeeRateC=" + checkFeeRateC + ", pymPeriod=" + pymPeriod + ", rmk=" + rmk
				+ ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt
				+ "]";
	}
}
