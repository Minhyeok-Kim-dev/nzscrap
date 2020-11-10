package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 뱅킹-개인_거래내역조회(입출금통장) DTO
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SBankB0002 {
	private String compCd;		// 회사코드
	private String acctNo;		// 계좌번호
	private String trDt;		// 거래일자
	private String trTm;		// 거래시간
	private String balance;		// 잔액
	private String bankCd;		// 은행코드
	private String inAmt;		// 입금액
	private String outAmt;		// 출금액
	private String trBr;		// 거래점명
	private String trNm;		// 거래자명
	private String trTp;		// 거래내용(적요)
	private String trDetail;	// 거래내용상세
	private String recvAcctNo;	// 상대방 계좌번호
	private String memo;		// 송금메모
	private String rmk;			// 비고
	private String markPen;		// 형광펜
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시

	private String trRnd;		// _param에 포함
	private String wlbn;		// _param에 포함
	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getInAmt() {
		return inAmt;
	}
	public void setInAmt(String inAmt) {
		this.inAmt = inAmt;
	}
	public String getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(String outAmt) {
		this.outAmt = outAmt;
	}
	public String getTrBr() {
		return trBr;
	}
	public void setTrBr(String trBr) {
		this.trBr = trBr;
	}
	public String getTrNm() {
		return trNm;
	}
	public void setTrNm(String trNm) {
		this.trNm = trNm;
	}
	public String getTrTp() {
		return trTp;
	}
	public void setTrTp(String trTp) {
		this.trTp = trTp;
	}
	public String getTrDetail() {
		return trDetail;
	}
	public void setTrDetail(String trDetail) {
		this.trDetail = trDetail;
	}
	public String getRecvAcctNo() {
		return recvAcctNo;
	}
	public void setRecvAcctNo(String recvAcctNo) {
		this.recvAcctNo = recvAcctNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	public String getMarkPen() {
		return markPen;
	}
	public void setMarkPen(String markPen) {
		this.markPen = markPen;
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
	public String getTrRnd() {
		return trRnd;
	}
	public void setTrRnd(String trRnd) {
		this.trRnd = trRnd;
	}
	public String getWlbn() {
		return wlbn;
	}
	public void setWlbn(String wlbn) {
		this.wlbn = wlbn;
	}
	@Override
	public String toString() {
		return "SBankB0002 [compCd=" + compCd + ", acctNo=" + acctNo + ", trDt=" + trDt + ", trTm=" + trTm
				+ ", balance=" + balance + ", bankCd=" + bankCd + ", inAmt=" + inAmt + ", outAmt=" + outAmt + ", trBr="
				+ trBr + ", trNm=" + trNm + ", trTp=" + trTp + ", trDetail=" + trDetail + ", recvAcctNo=" + recvAcctNo
				+ ", memo=" + memo + ", rmk=" + rmk + ", markPen=" + markPen + ", reqCd=" + reqCd + ", regId=" + regId
				+ ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + ", trRnd=" + trRnd + ", wlbn=" + wlbn
				+ "]";
	}
}
