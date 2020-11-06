package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_(세금)계산서발행내역(매출/매입)_품목 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ300XItem {
	private String compCd;		// 회사코드
	private String svcCd;		// 서비스코드
	private String issueNo;		// 승인번호
	private String itemNo;		// 품목순번
	private String supBizNo;	// 공급자등록번호
	private String byrBizNo;	// 공급받는자등록번호
	private String itemDt;		// 일자
	private String itemNm;		// 품목명
	private String itemStd;		// 규격
	private String itemQty;		// 수량
	private String itemUnt;		// 단가
	private String itemSupAmt;	// 공급가액
	private String itemTaxAmt;	// 세액
	private String itemBigo;	// 품목비고
	private String rmk;			// 비고
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시

	private String issueNoDisp;	// _param에 포함 (출력용 승인번호)

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

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getSupBizNo() {
		return supBizNo;
	}

	public void setSupBizNo(String supBizNo) {
		this.supBizNo = supBizNo;
	}

	public String getByrBizNo() {
		return byrBizNo;
	}

	public void setByrBizNo(String byrBizNo) {
		this.byrBizNo = byrBizNo;
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

	public String getIssueNoDisp() {
		return issueNoDisp;
	}

	public void setIssueNoDisp(String issueNoDisp) {
		this.issueNoDisp = issueNoDisp;
	}

	@Override
	public String toString() {
		return "sHometaxZ300XItem [compCd=" + compCd + ", svcCd=" + svcCd + ", issueNo=" + issueNo + ", itemNo="
				+ itemNo + ", supBizNo=" + supBizNo + ", byrBizNo=" + byrBizNo + ", itemDt=" + itemDt + ", itemNm="
				+ itemNm + ", itemStd=" + itemStd + ", itemQty=" + itemQty + ", itemUnt=" + itemUnt + ", itemSupAmt="
				+ itemSupAmt + ", itemTaxAmt=" + itemTaxAmt + ", itemBigo=" + itemBigo + ", rmk=" + rmk + ", reqCd="
				+ reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt
				+ ", issueNoDisp=" + issueNoDisp + "]";
	}
}
