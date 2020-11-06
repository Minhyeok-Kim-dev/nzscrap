package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_(세금)계산서발행내역(매출/매입)_내역 DTO 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ300X {
	private String compCd;		// 회사코드
	private String svcCd;		// 서비스코드
	private String supByr;		// 매입매출구분
	private String makeDt;		// 작성일자
	private String issueNo;		// 승인번호
	private String issueNoDisp;	// 출력용 승인번호
	private String issueDt;		// 발급일자
	private String sendDt;		// 전송일자
	private String supBizNo;	// 공급자등록번호
	private String supBizSubNo;	// 종사업자번호
	private String supCorpNm;	// 상호
	private String supRepNm;	// 대표자명
	private String supAddress;	// 주소
	private String byrBizNo;	// 공급받는자등록번호
	private String byrBizSubNo;	// 종사업장번호
	private String byrCorpNm;	// 상호
	private String byrRepNm;	// 대표자명
	private String byrAddress;	// 주소
	private String totAmt;		// 합계금액
	private String supAmt;		// 공급가액
	private String taxAmt;		// 세액
	private String taxClsf;		// 세금계산서분류
	private String taxKnd;		// 세금계산서종류
	private String isnType;		// 발급유형
	private String bigo;		// 비고_내역
	private String demandGb;	// 영수청구구분
	private String supEmail;	// 공급자이메일
	private String byrEmail1;	// 공급받는자이메일
	private String byrEmail2;	// 공급받는자이메일2
	private String itemDt;		// 품목일자
	private String itemNm;		// 품목명
	private String itemStd;		// 품목규격
	private String itemQty;		// 품목수량
	private String itemUnt;		// 품목단가
	private String itemSupAmt;	// 품목공급가액
	private String itemTaxAmt;	// 품목세액
	private String itemBigo;	// 품목비고
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
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public String getSupByr() {
		return supByr;
	}
	public void setSupByr(String supByr) {
		this.supByr = supByr;
	}
	public String getMakeDt() {
		return makeDt;
	}
	public void setMakeDt(String makeDt) {
		this.makeDt = makeDt;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueNoDisp() {
		return issueNoDisp;
	}
	public void setIssueNoDisp(String issueNoDisp) {
		this.issueNoDisp = issueNoDisp;
	}
	public String getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getSupBizNo() {
		return supBizNo;
	}
	public void setSupBizNo(String supBizNo) {
		this.supBizNo = supBizNo;
	}
	public String getSupBizSubNo() {
		return supBizSubNo;
	}
	public void setSupBizSubNo(String supBizSubNo) {
		this.supBizSubNo = supBizSubNo;
	}
	public String getSupCorpNm() {
		return supCorpNm;
	}
	public void setSupCorpNm(String supCorpNm) {
		this.supCorpNm = supCorpNm;
	}
	public String getSupRepNm() {
		return supRepNm;
	}
	public void setSupRepNm(String supRepNm) {
		this.supRepNm = supRepNm;
	}
	public String getSupAddress() {
		return supAddress;
	}
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	public String getByrBizNo() {
		return byrBizNo;
	}
	public void setByrBizNo(String byrBizNo) {
		this.byrBizNo = byrBizNo;
	}
	public String getByrBizSubNo() {
		return byrBizSubNo;
	}
	public void setByrBizSubNo(String byrBizSubNo) {
		this.byrBizSubNo = byrBizSubNo;
	}
	public String getByrCorpNm() {
		return byrCorpNm;
	}
	public void setByrCorpNm(String byrCorpNm) {
		this.byrCorpNm = byrCorpNm;
	}
	public String getByrRepNm() {
		return byrRepNm;
	}
	public void setByrRepNm(String byrRepNm) {
		this.byrRepNm = byrRepNm;
	}
	public String getByrAddress() {
		return byrAddress;
	}
	public void setByrAddress(String byrAddress) {
		this.byrAddress = byrAddress;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getSupAmt() {
		return supAmt;
	}
	public void setSupAmt(String supAmt) {
		this.supAmt = supAmt;
	}
	public String getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
	public String getTaxClsf() {
		return taxClsf;
	}
	public void setTaxClsf(String taxClsf) {
		this.taxClsf = taxClsf;
	}
	public String getTaxKnd() {
		return taxKnd;
	}
	public void setTaxKnd(String taxKnd) {
		this.taxKnd = taxKnd;
	}
	public String getIsnType() {
		return isnType;
	}
	public void setIsnType(String isnType) {
		this.isnType = isnType;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getDemandGb() {
		return demandGb;
	}
	public void setDemandGb(String demandGb) {
		this.demandGb = demandGb;
	}
	public String getSupEmail() {
		return supEmail;
	}
	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}
	public String getByrEmail1() {
		return byrEmail1;
	}
	public void setByrEmail1(String byrEmail1) {
		this.byrEmail1 = byrEmail1;
	}
	public String getByrEmail2() {
		return byrEmail2;
	}
	public void setByrEmail2(String byrEmail2) {
		this.byrEmail2 = byrEmail2;
	}
	public String getItemDt() {
		return itemDt;
	}
	public void setItemDt(String itemDt) {
		this.itemDt = itemDt;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getItemStd() {
		return itemStd;
	}
	public void setItemStd(String itemStd) {
		this.itemStd = itemStd;
	}
	public String getItemQty() {
		return itemQty;
	}
	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}
	public String getItemUnt() {
		return itemUnt;
	}
	public void setItemUnt(String itemUnt) {
		this.itemUnt = itemUnt;
	}
	public String getItemSupAmt() {
		return itemSupAmt;
	}
	public void setItemSupAmt(String itemSupAmt) {
		this.itemSupAmt = itemSupAmt;
	}
	public String getItemTaxAmt() {
		return itemTaxAmt;
	}
	public void setItemTaxAmt(String itemTaxAmt) {
		this.itemTaxAmt = itemTaxAmt;
	}
	public String getItemBigo() {
		return itemBigo;
	}
	public void setItemBigo(String itemBigo) {
		this.itemBigo = itemBigo;
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
		return "sHometaxZ300X [compCd=" + compCd + ", svcCd=" + svcCd + ", supByr=" + supByr + ", makeDt=" + makeDt
				+ ", issueNo=" + issueNo + ", issueNoDisp=" + issueNoDisp + ", issueDt=" + issueDt + ", sendDt="
				+ sendDt + ", supBizNo=" + supBizNo + ", supBizSubNo=" + supBizSubNo + ", supCorpNm=" + supCorpNm
				+ ", supRepNm=" + supRepNm + ", supAddress=" + supAddress + ", byrBizNo=" + byrBizNo + ", byrBizSubNo="
				+ byrBizSubNo + ", byrCorpNm=" + byrCorpNm + ", byrRepNm=" + byrRepNm + ", byrAddress=" + byrAddress
				+ ", totAmt=" + totAmt + ", supAmt=" + supAmt + ", taxAmt=" + taxAmt + ", taxClsf=" + taxClsf
				+ ", taxKnd=" + taxKnd + ", isnType=" + isnType + ", bigo=" + bigo + ", demandGb=" + demandGb
				+ ", supEmail=" + supEmail + ", byrEmail1=" + byrEmail1 + ", byrEmail2=" + byrEmail2 + ", itemDt="
				+ itemDt + ", itemNm=" + itemNm + ", itemStd=" + itemStd + ", itemQty=" + itemQty + ", itemUnt="
				+ itemUnt + ", itemSupAmt=" + itemSupAmt + ", itemTaxAmt=" + itemTaxAmt + ", itemBigo=" + itemBigo
				+ ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId
				+ ", uptDt=" + uptDt + "]";
	}
}
