package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_현금영수증 매입 내역 DTO
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ4002 {
	private String compCd;		// 회사코드
	private String trDt;		// 매입일자
	private String trTm;		// 매입시간
	private String apprNo;		// 승인번호
	private String useStore;	// 가맹점
	private String storeNo;		// 가맹점사업자번호
	private String supAmt;		// 공급가액
	private String taxAmt;		// 부가세
	private String tip;			// 봉사료
	private String totAmt;		// 총금액
	private String frPartNo;	// 발급수단
	private String trGb;		// 거래구분
	private String ddcYn;		// 공제구분
	private String bmanClNm;	// 사업자구분
	private String tfbCd;		// 업종코드
	private String tfbNm;		// 업종명
	private String bigo;		// 비고_내역
	private String rmk;			// 비고
	private String markPen;		// 형광펜
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시
	
	private String issueGb;		// _param에 포함 (발행구분)

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

	public String getApprNo() {
		return apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public String getUseStore() {
		return useStore;
	}

	public void setUseStore(String useStore) {
		this.useStore = useStore;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}

	public String getFrPartNo() {
		return frPartNo;
	}

	public void setFrPartNo(String frPartNo) {
		this.frPartNo = frPartNo;
	}

	public String getTrGb() {
		return trGb;
	}

	public void setTrGb(String trGb) {
		this.trGb = trGb;
	}

	public String getDdcYn() {
		return ddcYn;
	}

	public void setDdcYn(String ddcYn) {
		this.ddcYn = ddcYn;
	}

	public String getBmanClNm() {
		return bmanClNm;
	}

	public void setBmanClNm(String bmanClNm) {
		this.bmanClNm = bmanClNm;
	}

	public String getTfbCd() {
		return tfbCd;
	}

	public void setTfbCd(String tfbCd) {
		this.tfbCd = tfbCd;
	}

	public String getTfbNm() {
		return tfbNm;
	}

	public void setTfbNm(String tfbNm) {
		this.tfbNm = tfbNm;
	}

	public String getBigo() {
		return bigo;
	}

	public void setBigo(String bigo) {
		this.bigo = bigo;
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

	public String getIssueGb() {
		return issueGb;
	}

	public void setIssueGb(String issueGb) {
		this.issueGb = issueGb;
	}

	@Override
	public String toString() {
		return "SHometaxZ4002 [compCd=" + compCd + ", trDt=" + trDt + ", trTm=" + trTm + ", apprNo=" + apprNo
				+ ", useStore=" + useStore + ", storeNo=" + storeNo + ", supAmt=" + supAmt + ", taxAmt=" + taxAmt
				+ ", tip=" + tip + ", totAmt=" + totAmt + ", frPartNo=" + frPartNo + ", trGb=" + trGb + ", ddcYn="
				+ ddcYn + ", bmanClNm=" + bmanClNm + ", tfbCd=" + tfbCd + ", tfbNm=" + tfbNm + ", bigo=" + bigo
				+ ", rmk=" + rmk + ", markPen=" + markPen + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt
				+ ", uptId=" + uptId + ", uptDt=" + uptDt + ", issueGb=" + issueGb + "]";
	}
}
