package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별승인내역(일별)_상세 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0011Dtl {
	private int keyNo;			// 키 번호(A.I)
	private String compCd;		// 회사코드
	private String trDt;		// 거래일자
	private String trTm;		// 거래시간
	private String cardRealNo;	// 암호화된 카드번호
	private String apprNo;		// 승인번호
	private String trDiv;		// 구분
	private String cardCorp1;	// 카드사명
	private String cardCorp2;	// 제휴카드사명
	private String merNo;		// 가맹점번호
	private String cardKndCd;	// 카드구분코드
	private String cardKnd;		// 카드구분
	private String cardNum;		// 카드번호
	private String apprAmt;		// 승인금액
	private String instDiv;		// 할부기간
	private String insTrm;		// 할부개월
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
	public String getTrDt() {
		return trDt;
	}
	public void setTrDt(String trDt) {
		this.trDt = trDt;
	}
	public String getTrTm() {
		return trTm;
	}
	public void setTrTm(String trTm) {
		this.trTm = trTm;
	}
	public String getCardRealNo() {
		return cardRealNo;
	}
	public void setCardRealNo(String cardRealNo) {
		this.cardRealNo = cardRealNo;
	}
	public String getApprNo() {
		return apprNo;
	}
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}
	public String getTrDiv() {
		return trDiv;
	}
	public void setTrDiv(String trDiv) {
		this.trDiv = trDiv;
	}
	public String getCardCorp1() {
		return cardCorp1;
	}
	public void setCardCorp1(String cardCorp1) {
		this.cardCorp1 = cardCorp1;
	}
	public String getCardCorp2() {
		return cardCorp2;
	}
	public void setCardCorp2(String cardCorp2) {
		this.cardCorp2 = cardCorp2;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getCardKndCd() {
		return cardKndCd;
	}
	public void setCardKndCd(String cardKndCd) {
		this.cardKndCd = cardKndCd;
	}
	public String getCardKnd() {
		return cardKnd;
	}
	public void setCardKnd(String cardKnd) {
		this.cardKnd = cardKnd;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getApprAmt() {
		return apprAmt;
	}
	public void setApprAmt(String apprAmt) {
		this.apprAmt = apprAmt;
	}
	public String getInstDiv() {
		return instDiv;
	}
	public void setInstDiv(String instDiv) {
		this.instDiv = instDiv;
	}
	public String getInsTrm() {
		return insTrm;
	}
	public void setInsTrm(String insTrm) {
		this.insTrm = insTrm;
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
		return "SCardsalesB0011Dtl [keyNo=" + keyNo + ", compCd=" + compCd + ", trDt=" + trDt + ", trTm=" + trTm
				+ ", cardRealNo=" + cardRealNo + ", apprNo=" + apprNo + ", trDiv=" + trDiv + ", cardCorp1=" + cardCorp1
				+ ", cardCorp2=" + cardCorp2 + ", merNo=" + merNo + ", cardKndCd=" + cardKndCd + ", cardKnd=" + cardKnd
				+ ", cardNum=" + cardNum + ", apprAmt=" + apprAmt + ", instDiv=" + instDiv + ", insTrm=" + insTrm
				+ ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId
				+ ", uptDt=" + uptDt + ", fromDt=" + fromDt + ", toDt=" + toDt + "]";
	}
}
