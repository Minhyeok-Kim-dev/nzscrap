package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 여신_기간별매입내역(일별)_상세 DTO
 *
 * 201109_kmh 
 * - 거래일 and 매입일 and 승인번호 and 지급금액 같으면 중복으로 체크
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCardsalesB0021Dtl {
	private String compCd;		// 회사코드	(P)
	private String trDt;		// 거래일자	(P)
	private String buyDt;		// 매입일자	(P)
	private String apprNo;		// 승인번호	(P)
	private String cardCorp1;	// 카드사명
	private String cardCorp2;	// 제휴카드사명
	private String merNo;		// 가맹점번호
	private String cardKnd;		// 카드구분
	private String cardNum;		// 카드번호
	private String cardDivCd;	// 카드종류코드
	private String cardDiv;		// 카드종류
	private String buyAmt;		// 매입금액
	private String commMem;		// 수수료_가맹점
	private String commPoint;	// 수수료_포인트
	private String commEtc;		// 수수료_기타
	private String commSum;		// 수수료_계
	private String commVat;		// 부가세대리납부금액
	private String payAmt;		// 지급금액	(P)
	private String payDt;		// 지급예정일
	private String markPen;		// 형광펜
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
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public String getApprNo() {
		return apprNo;
	}
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
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
	public String getCardDivCd() {
		return cardDivCd;
	}
	public void setCardDivCd(String cardDivCd) {
		this.cardDivCd = cardDivCd;
	}
	public String getCardDiv() {
		return cardDiv;
	}
	public void setCardDiv(String cardDiv) {
		this.cardDiv = cardDiv;
	}
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = buyAmt;
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
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getPayDt() {
		return payDt;
	}
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}
	public String getMarkPen() {
		return markPen;
	}
	public void setMarkPen(String markPen) {
		this.markPen = markPen;
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
		return "SCardsalesB0021Dtl [compCd=" + compCd + ", trDt=" + trDt + ", buyDt=" + buyDt + ", apprNo=" + apprNo
				+ ", cardCorp1=" + cardCorp1 + ", cardCorp2=" + cardCorp2 + ", merNo=" + merNo + ", cardKnd=" + cardKnd
				+ ", cardNum=" + cardNum + ", cardDivCd=" + cardDivCd + ", cardDiv=" + cardDiv + ", buyAmt=" + buyAmt
				+ ", commMem=" + commMem + ", commPoint=" + commPoint + ", commEtc=" + commEtc + ", commSum=" + commSum
				+ ", commVat=" + commVat + ", payAmt=" + payAmt + ", payDt=" + payDt + ", markPen=" + markPen + ", rmk="
				+ rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt="
				+ uptDt + "]";
	}
}
