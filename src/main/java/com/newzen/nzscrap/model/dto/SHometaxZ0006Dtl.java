package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 * 
 * 홈택스_부가세신고용 합계표 명세 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ0006Dtl {
	private String compCd;				// 회사코드 
	private String supByr;				// 매출/매입 구분 (01: 매출, 02: 매입)
	private String taxGb;				// 세금계산서 구분 (01: 전자세금계산서, 03: 전자계산서)
	private String wrtYr;				// 조회년도
	private String wrtQt;				// 조회분기 (1: 1기예정, 2: 1기확정, 3: 1기예정 + 확정, 4: 2기예정, 5: 2기확정, 6: 2기예정 + 확정)
	private String wrtDt;				// 작성일자
	private String etan;				// 승인번호 
	private String wrtDtFrom;			// 조회시작일
	private String wrtDtTo;				// 조회종료일
	private String isnDtm;				// 발급일자
	private String tmsnDt;				// 전송일자
	private String dmnrTxprDscmNo;		// 공급받는자
	private String dmnrMpbNoStr;		// 종사업장번호
	private String tnmNm;				// 상호
	private String rprsFnm;				// 대표자명
	private String totaAmt;				// 합계금액
	private String supSplCft;			// 공급가액
	private String sumTxamt;			// 세액
	private String etxivKndCd;			// 전자세금계산서종류
	private String isnTypeCd;			// 발급유형
	private String etxivSq1RmrkCntn;	// 비고
	private String recApeClCd;			// 영수/청구
	private String mchrgEmlAdrSls;		// 공급자이메일
	private String mchrgEmlAdrPrh;		// 공급받는자이메일1
	private String rmk;					// 비고
	private String reqCd;				// 요청코드
	private String regId;				// 등록자ID
	private String regDt;				// 등록일시
	private String uptId;				// 수정자ID
	private String uptDt;				// 수정일시

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
	public String getWrtDt() {
		return wrtDt;
	}
	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}
	public String getEtan() {
		return etan;
	}
	public void setEtan(String etan) {
		this.etan = etan;
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
	public String getIsnDtm() {
		return isnDtm;
	}
	public void setIsnDtm(String isnDtm) {
		this.isnDtm = isnDtm;
	}
	public String getTmsnDt() {
		return tmsnDt;
	}
	public void setTmsnDt(String tmsnDt) {
		this.tmsnDt = tmsnDt;
	}
	public String getDmnrTxprDscmNo() {
		return dmnrTxprDscmNo;
	}
	public void setDmnrTxprDscmNo(String dmnrTxprDscmNo) {
		this.dmnrTxprDscmNo = dmnrTxprDscmNo;
	}
	public String getDmnrMpbNoStr() {
		return dmnrMpbNoStr;
	}
	public void setDmnrMpbNoStr(String dmnrMpbNoStr) {
		this.dmnrMpbNoStr = dmnrMpbNoStr;
	}
	public String getTnmNm() {
		return tnmNm;
	}
	public void setTnmNm(String tnmNm) {
		this.tnmNm = tnmNm;
	}
	public String getRprsFnm() {
		return rprsFnm;
	}
	public void setRprsFnm(String rprsFnm) {
		this.rprsFnm = rprsFnm;
	}
	public String getTotaAmt() {
		return totaAmt;
	}
	public void setTotaAmt(String totaAmt) {
		this.totaAmt = totaAmt;
	}
	public String getSupSplCft() {
		return supSplCft;
	}
	public void setSupSplCft(String supSplCft) {
		this.supSplCft = supSplCft;
	}
	public String getSumTxamt() {
		return sumTxamt;
	}
	public void setSumTxamt(String sumTxamt) {
		this.sumTxamt = sumTxamt;
	}
	public String getEtxivKndCd() {
		return etxivKndCd;
	}
	public void setEtxivKndCd(String etxivKndCd) {
		this.etxivKndCd = etxivKndCd;
	}
	public String getIsnTypeCd() {
		return isnTypeCd;
	}
	public void setIsnTypeCd(String isnTypeCd) {
		this.isnTypeCd = isnTypeCd;
	}
	public String getEtxivSq1RmrkCntn() {
		return etxivSq1RmrkCntn;
	}
	public void setEtxivSq1RmrkCntn(String etxivSq1RmrkCntn) {
		this.etxivSq1RmrkCntn = etxivSq1RmrkCntn;
	}
	public String getRecApeClCd() {
		return recApeClCd;
	}
	public void setRecApeClCd(String recApeClCd) {
		this.recApeClCd = recApeClCd;
	}
	public String getMchrgEmlAdrSls() {
		return mchrgEmlAdrSls;
	}
	public void setMchrgEmlAdrSls(String mchrgEmlAdrSls) {
		this.mchrgEmlAdrSls = mchrgEmlAdrSls;
	}
	public String getMchrgEmlAdrPrh() {
		return mchrgEmlAdrPrh;
	}
	public void setMchrgEmlAdrPrh(String mchrgEmlAdrPrh) {
		this.mchrgEmlAdrPrh = mchrgEmlAdrPrh;
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
		return "SHometaxZ0006Dtl [compCd=" + compCd + ", supByr=" + supByr + ", taxGb=" + taxGb + ", wrtYr=" + wrtYr
				+ ", wrtQt=" + wrtQt + ", wrtDt=" + wrtDt + ", etan=" + etan + ", wrtDtFrom=" + wrtDtFrom + ", wrtDtTo="
				+ wrtDtTo + ", isnDtm=" + isnDtm + ", tmsnDt=" + tmsnDt + ", dmnrTxprDscmNo=" + dmnrTxprDscmNo
				+ ", dmnrMpbNoStr=" + dmnrMpbNoStr + ", tnmNm=" + tnmNm + ", rprsFnm=" + rprsFnm + ", totaAmt="
				+ totaAmt + ", supSplCft=" + supSplCft + ", sumTxamt=" + sumTxamt + ", etxivKndCd=" + etxivKndCd
				+ ", isnTypeCd=" + isnTypeCd + ", etxivSq1RmrkCntn=" + etxivSq1RmrkCntn + ", recApeClCd=" + recApeClCd
				+ ", mchrgEmlAdrSls=" + mchrgEmlAdrSls + ", mchrgEmlAdrPrh=" + mchrgEmlAdrPrh + ", rmk=" + rmk
				+ ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt
				+ "]";
	}
}
