package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 * 
 * 홈택스_부가세신고용 합계표 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ0006 {
	private String compCd;					// 회사코드
	private String supByr;					// 매출/매입 구분 (01: 매출, 02: 매입)
	private String taxGb;					// 세금계산서 구분 (01: 전자세금계산서, 03: 전자계산서)
	private String wrtYr;					// 조회년도
	private String wrtQt;					// 조회분기 (1: 1기예정, 2: 1기확정, 3: 1기예정 + 확정, 4: 2기예정, 5: 2기확정, 6: 2기예정 + 확정)
	private String wrtDtFrom;				// 조회시작일
	private String wrtDtTo;					// 조회종료일
											// * 과세기간 종료일 다음달 11일까지 전송된 매입 전자세금계산서 합계
	private String withIn_bsnoIsnClplcCnt;	// 11일까지_사업자 발행 매출처 수
	private String withIn_bsnoIsnScnt;		// 11일까지_사업자 발행 매수
	private String withIn_bsnoIsnSplCft;	// 11일까지_사업자 발행 공급가액
	private String withIn_bsnoIsnTxamt;		// 11일까지_사업자 발행 세액
	private String withIn_resnoIsnClplcCnt;	// 11일까지_주민등록 발행 매출처 수
	private String withIn_resnoIsnScnt;		// 11일까지_주민등록 발행 매수
	private String withIn_resnoIsnSplCft;	// 11일까지_주민등록 발행 공급가액
	private String withIn_resnoIsnTxamt;	// 11일까지_주민등록 발행 세액
	private String withIn_totCnt;			// 11일까지_총 매수
	private String withIn_totPurchplcCnt;	// 11일까지_총 매입처 수
	private String withIn_totSellplcCnt;	// 11일까지_총 매출처 수
	private String withIn_totSplCft;		// 11일까지_총 공급가액
	private String withIn_totTxamt;			// 11일까지_총 세액
											// * 과세기간 종료일 다음달 12일부터 전송된 매입 전자세금계산서 합계
	private String et_bsnoIsnClplcCnt;		// 12일부터_사업자 발행 매출처 수
	private String et_bsnoIsnScnt;			// 12일부터_사업자 발행 매수
	private String et_bsnoIsnSplCft;		// 12일부터_사업자 발행 공급가액
	private String et_bsnoIsnTxamt;			// 12일부터_사업자 발행 세액
	private String et_resnoIsnClplcCnt;		// 12일부터_주민등록 발행 매출처 수
	private String et_resnoIsnScnt;			// 12일부터_주민등록 발행 매수
	private String et_resnoIsnSplCft;		// 12일부터_주민등록 발행 공급가액
	private String et_resnoIsnTxamt;		// 12일부터_주민등록 발행 세액
	private String et_totCnt;				// 12일부터_총 매수
	private String et_totPurchplcCnt;		// 12일부터_총 매입처 수
	private String et_totSellplcCnt;		// 12일부터_총 매출처 수
	private String et_totSplCft;			// 12일부터_총 공급가액
	private String et_totTxamt;				// 12일부터_총 세액
	
	private String rmk;						// 비고
	private String reqCd;					// 요청코드
	private String regId;					// 등록자ID
	private String regDt;					// 등록일시
	private String uptId;					// 수정자ID
	private String uptDt;					// 수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getSupByr() {
		return supByr;
	}
	public void setSupByr(String supByr) {
		this.supByr = supByr;
	}
	public String getTaxGb() {
		return taxGb;
	}
	public void setTaxGb(String taxGb) {
		this.taxGb = taxGb;
	}
	public String getWrtYr() {
		return wrtYr;
	}
	public void setWrtYr(String wrtYr) {
		this.wrtYr = wrtYr;
	}
	public String getWrtQt() {
		return wrtQt;
	}
	public void setWrtQt(String wrtQt) {
		this.wrtQt = wrtQt;
	}
	public String getWrtDtFrom() {
		return wrtDtFrom;
	}
	public void setWrtDtFrom(String wrtDtFrom) {
		this.wrtDtFrom = wrtDtFrom;
	}
	public String getWrtDtTo() {
		return wrtDtTo;
	}
	public void setWrtDtTo(String wrtDtTo) {
		this.wrtDtTo = wrtDtTo;
	}
	public String getWithIn_bsnoIsnClplcCnt() {
		return withIn_bsnoIsnClplcCnt;
	}
	public void setWithIn_bsnoIsnClplcCnt(String withIn_bsnoIsnClplcCnt) {
		this.withIn_bsnoIsnClplcCnt = withIn_bsnoIsnClplcCnt;
	}
	public String getWithIn_bsnoIsnScnt() {
		return withIn_bsnoIsnScnt;
	}
	public void setWithIn_bsnoIsnScnt(String withIn_bsnoIsnScnt) {
		this.withIn_bsnoIsnScnt = withIn_bsnoIsnScnt;
	}
	public String getWithIn_bsnoIsnSplCft() {
		return withIn_bsnoIsnSplCft;
	}
	public void setWithIn_bsnoIsnSplCft(String withIn_bsnoIsnSplCft) {
		this.withIn_bsnoIsnSplCft = withIn_bsnoIsnSplCft;
	}
	public String getWithIn_bsnoIsnTxamt() {
		return withIn_bsnoIsnTxamt;
	}
	public void setWithIn_bsnoIsnTxamt(String withIn_bsnoIsnTxamt) {
		this.withIn_bsnoIsnTxamt = withIn_bsnoIsnTxamt;
	}
	public String getWithIn_resnoIsnClplcCnt() {
		return withIn_resnoIsnClplcCnt;
	}
	public void setWithIn_resnoIsnClplcCnt(String withIn_resnoIsnClplcCnt) {
		this.withIn_resnoIsnClplcCnt = withIn_resnoIsnClplcCnt;
	}
	public String getWithIn_resnoIsnScnt() {
		return withIn_resnoIsnScnt;
	}
	public void setWithIn_resnoIsnScnt(String withIn_resnoIsnScnt) {
		this.withIn_resnoIsnScnt = withIn_resnoIsnScnt;
	}
	public String getWithIn_resnoIsnSplCft() {
		return withIn_resnoIsnSplCft;
	}
	public void setWithIn_resnoIsnSplCft(String withIn_resnoIsnSplCft) {
		this.withIn_resnoIsnSplCft = withIn_resnoIsnSplCft;
	}
	public String getWithIn_resnoIsnTxamt() {
		return withIn_resnoIsnTxamt;
	}
	public void setWithIn_resnoIsnTxamt(String withIn_resnoIsnTxamt) {
		this.withIn_resnoIsnTxamt = withIn_resnoIsnTxamt;
	}
	public String getWithIn_totCnt() {
		return withIn_totCnt;
	}
	public void setWithIn_totCnt(String withIn_totCnt) {
		this.withIn_totCnt = withIn_totCnt;
	}
	public String getWithIn_totPurchplcCnt() {
		return withIn_totPurchplcCnt;
	}
	public void setWithIn_totPurchplcCnt(String withIn_totPurchplcCnt) {
		this.withIn_totPurchplcCnt = withIn_totPurchplcCnt;
	}
	public String getWithIn_totSellplcCnt() {
		return withIn_totSellplcCnt;
	}
	public void setWithIn_totSellplcCnt(String withIn_totSellplcCnt) {
		this.withIn_totSellplcCnt = withIn_totSellplcCnt;
	}
	public String getWithIn_totSplCft() {
		return withIn_totSplCft;
	}
	public void setWithIn_totSplCft(String withIn_totSplCft) {
		this.withIn_totSplCft = withIn_totSplCft;
	}
	public String getWithIn_totTxamt() {
		return withIn_totTxamt;
	}
	public void setWithIn_totTxamt(String withIn_totTxamt) {
		this.withIn_totTxamt = withIn_totTxamt;
	}
	public String getEt_bsnoIsnClplcCnt() {
		return et_bsnoIsnClplcCnt;
	}
	public void setEt_bsnoIsnClplcCnt(String et_bsnoIsnClplcCnt) {
		this.et_bsnoIsnClplcCnt = et_bsnoIsnClplcCnt;
	}
	public String getEt_bsnoIsnScnt() {
		return et_bsnoIsnScnt;
	}
	public void setEt_bsnoIsnScnt(String et_bsnoIsnScnt) {
		this.et_bsnoIsnScnt = et_bsnoIsnScnt;
	}
	public String getEt_bsnoIsnSplCft() {
		return et_bsnoIsnSplCft;
	}
	public void setEt_bsnoIsnSplCft(String et_bsnoIsnSplCft) {
		this.et_bsnoIsnSplCft = et_bsnoIsnSplCft;
	}
	public String getEt_bsnoIsnTxamt() {
		return et_bsnoIsnTxamt;
	}
	public void setEt_bsnoIsnTxamt(String et_bsnoIsnTxamt) {
		this.et_bsnoIsnTxamt = et_bsnoIsnTxamt;
	}
	public String getEt_resnoIsnClplcCnt() {
		return et_resnoIsnClplcCnt;
	}
	public void setEt_resnoIsnClplcCnt(String et_resnoIsnClplcCnt) {
		this.et_resnoIsnClplcCnt = et_resnoIsnClplcCnt;
	}
	public String getEt_resnoIsnScnt() {
		return et_resnoIsnScnt;
	}
	public void setEt_resnoIsnScnt(String et_resnoIsnScnt) {
		this.et_resnoIsnScnt = et_resnoIsnScnt;
	}
	public String getEt_resnoIsnSplCft() {
		return et_resnoIsnSplCft;
	}
	public void setEt_resnoIsnSplCft(String et_resnoIsnSplCft) {
		this.et_resnoIsnSplCft = et_resnoIsnSplCft;
	}
	public String getEt_resnoIsnTxamt() {
		return et_resnoIsnTxamt;
	}
	public void setEt_resnoIsnTxamt(String et_resnoIsnTxamt) {
		this.et_resnoIsnTxamt = et_resnoIsnTxamt;
	}
	public String getEt_totCnt() {
		return et_totCnt;
	}
	public void setEt_totCnt(String et_totCnt) {
		this.et_totCnt = et_totCnt;
	}
	public String getEt_totPurchplcCnt() {
		return et_totPurchplcCnt;
	}
	public void setEt_totPurchplcCnt(String et_totPurchplcCnt) {
		this.et_totPurchplcCnt = et_totPurchplcCnt;
	}
	public String getEt_totSellplcCnt() {
		return et_totSellplcCnt;
	}
	public void setEt_totSellplcCnt(String et_totSellplcCnt) {
		this.et_totSellplcCnt = et_totSellplcCnt;
	}
	public String getEt_totSplCft() {
		return et_totSplCft;
	}
	public void setEt_totSplCft(String et_totSplCft) {
		this.et_totSplCft = et_totSplCft;
	}
	public String getEt_totTxamt() {
		return et_totTxamt;
	}
	public void setEt_totTxamt(String et_totTxamt) {
		this.et_totTxamt = et_totTxamt;
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
		return "SHometaxZ0006 [compCd=" + compCd + ", supByr=" + supByr + ", taxGb=" + taxGb + ", wrtYr=" + wrtYr
				+ ", wrtQt=" + wrtQt + ", wrtDtFrom=" + wrtDtFrom + ", wrtDtTo=" + wrtDtTo + ", withIn_bsnoIsnClplcCnt="
				+ withIn_bsnoIsnClplcCnt + ", withIn_bsnoIsnScnt=" + withIn_bsnoIsnScnt + ", withIn_bsnoIsnSplCft="
				+ withIn_bsnoIsnSplCft + ", withIn_bsnoIsnTxamt=" + withIn_bsnoIsnTxamt + ", withIn_resnoIsnClplcCnt="
				+ withIn_resnoIsnClplcCnt + ", withIn_resnoIsnScnt=" + withIn_resnoIsnScnt + ", withIn_resnoIsnSplCft="
				+ withIn_resnoIsnSplCft + ", withIn_resnoIsnTxamt=" + withIn_resnoIsnTxamt + ", withIn_totCnt="
				+ withIn_totCnt + ", withIn_totPurchplcCnt=" + withIn_totPurchplcCnt + ", withIn_totSellplcCnt="
				+ withIn_totSellplcCnt + ", withIn_totSplCft=" + withIn_totSplCft + ", withIn_totTxamt="
				+ withIn_totTxamt + ", et_bsnoIsnClplcCnt=" + et_bsnoIsnClplcCnt + ", et_bsnoIsnScnt=" + et_bsnoIsnScnt
				+ ", et_bsnoIsnSplCft=" + et_bsnoIsnSplCft + ", et_bsnoIsnTxamt=" + et_bsnoIsnTxamt
				+ ", et_resnoIsnClplcCnt=" + et_resnoIsnClplcCnt + ", et_resnoIsnScnt=" + et_resnoIsnScnt
				+ ", et_resnoIsnSplCft=" + et_resnoIsnSplCft + ", et_resnoIsnTxamt=" + et_resnoIsnTxamt + ", et_totCnt="
				+ et_totCnt + ", et_totPurchplcCnt=" + et_totPurchplcCnt + ", et_totSellplcCnt=" + et_totSellplcCnt
				+ ", et_totSplCft=" + et_totSplCft + ", et_totTxamt=" + et_totTxamt + ", rmk=" + rmk + ", reqCd="
				+ reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
