package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별입금내역_상세 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0031Dtl {
	private int keyNo;			// 키 번호(A.I)
	private String compCd;		// 회사코드
	private String payDt;		// 입금일자
	private String memNo;		// 가맹점번호
	private String acctNo;		// 결제계좌
	private String cardCorp1;	// 카드사명
	private String bank;		// 결제은행
	private String salesCnt;	// 매출건수
	private String salesAmt;	// 매출금액
	private String detAmt;		// 보류금액
	private String commVat;		// 부가세대리납부금액
	private String etcAmt;		// 기타입금
	private String realPayAmt;	// 실입금
	private String rmk;			// 비고
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시
	
	private String fromDt;		// _거래일자 시작일 (삭제시 사용)
	private String toDt;		// _거래일자 종료일 (삭제시 사용)

	public int getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(int keyNo) {
		this.keyNo = keyNo;
	}
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
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getCardCorp1() {
		return cardCorp1;
	}
	public void setCardCorp1(String cardCorp1) {
		this.cardCorp1 = cardCorp1;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getSalesCnt() {
		return salesCnt;
	}
	public void setSalesCnt(String salesCnt) {
		this.salesCnt = salesCnt;
	}
	public String getSalesAmt() {
		return salesAmt;
	}
	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}
	public String getDetAmt() {
		return detAmt;
	}
	public void setDetAmt(String detAmt) {
		this.detAmt = detAmt;
	}
	public String getCommVat() {
		return commVat;
	}
	public void setCommVat(String commVat) {
		this.commVat = commVat;
	}
	public String getEtcAmt() {
		return etcAmt;
	}
	public void setEtcAmt(String etcAmt) {
		this.etcAmt = etcAmt;
	}
	public String getRealPayAmt() {
		return realPayAmt;
	}
	public void setRealPayAmt(String realPayAmt) {
		this.realPayAmt = realPayAmt;
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
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	@Override
	public String toString() {
		return "SCardsalesB0031Dtl [keyNo=" + keyNo + ", compCd=" + compCd + ", payDt=" + payDt + ", memNo=" + memNo
				+ ", acctNo=" + acctNo + ", cardCorp1=" + cardCorp1 + ", bank=" + bank + ", salesCnt=" + salesCnt
				+ ", salesAmt=" + salesAmt + ", detAmt=" + detAmt + ", commVat=" + commVat + ", etcAmt=" + etcAmt
				+ ", realPayAmt=" + realPayAmt + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt="
				+ regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + ", fromDt=" + fromDt + ", toDt=" + toDt + "]";
	}
}
