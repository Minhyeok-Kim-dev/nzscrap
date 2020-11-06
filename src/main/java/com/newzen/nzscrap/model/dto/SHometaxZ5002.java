package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 * 
 * 홈택스_세금신고 접수증조회 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ5002 {
	private String compCd;				// 회사코드
	private String itrfCd;				// 공통 - 세목코드
	private String txnrmYm;				// 공통 - 과세년월
	private String rtnClCd; 			// 공통 - 신고구분코드
	private String rtnDt; 				// 공통 - 신고일자
	private String mtfbCd; 				// 공통 - 국세청업종코드
	private String frsRgtDtm; 			// 공통 - 최초등록일시
	private String rtnClNm; 			// 공통 - 신고구분명
	private String tin; 				// 공통 - 국세청업체관리번호
	private String rtnCvaId; 			// 공통 - 접수번호
	private String rtnClDetailCd; 		// 공통 - 신고유형코드
	private String rtnClDetailNm; 		// 공통 - 신고유형
	private String pymnYm; 				// 공통 - 납부년월
	private String sbmsYm; 				// 공통 - 제출년월 
	private String stmnKndCd; 			// 공통 - 신고서종류코드
	private String stmnKndNm; 			// 공통 - 신고서종류
	private String txnrmStrtDt; 		// 공통 - 과세기간 시작
	private String txnrmEndDt; 			// 공통 - 과세기간 종료
	private String txprNm; 				// 공통 - 상호및성명
	private String txprNo; 				// 공통 - 사업자주민번호
	private String rcatNo; 				// 공통 - 접수번호
	private String rcatDt; 				// 공통 - 접수일자
	private String cvaAplnDtm; 			// 공통 - 접수일시 
	private String chrgTxhfOgzCd; 		// 공통 - 세무서코드
	private String userId; 				// 공통 - 제출자ID
	private String stasAmt; 			// 공통 - 과세표준
	private String ogntxSbtrScpmTxamt; 	// 법인 - 차감납부할세액 // 종소 - 납부(환급)할 세액
	private String ogntxInpmSchuTxamt; 	// 법인 - 분할납부할세액 // 종소 - 분납할세액
	private String ogntxSbtrPmtTxamt; 	// 법인 - 차감납부세액 // 원천 - 소득세등납부세액 // 부가 - 차가감납부할세액 // 종소 - 신고기한내 납부할세액
	private String fnftxSbtrScpmTxamt; 	// 법인 - 농어촌특별세 차감납부할세액 // 종소 - 농어촌특별세 납부(환급)할세액
	private String fnftxInpmSchuTxamt; 	// 법인 - 농어촌특별세 분할납부할세액 // 종소 - 농어촌특별세 분납할세액
	private String fnftxSbtrPmtTxamt; 	// 법인 - 농어촌특별세 차감납부세액 // 원천 - 농어촌특별세납부세액 // 종소 - 농어촌특별세 신고기한내 납부할세액
	private String aprpAfthPmtTxamt; 	// 법인 - 농어촌특별세 충당후납부세액
	private String surcRtnClCd; 		// 원천 - 매월/반기
	private String tmonCrfwRfndTxamt; 	// 원천 - 차월이월환급세액
	private String rtnRfndClCd; 		// 원천 - 환급신청여부 // 종소 - 환급구분
	private String rfndAplnAmt; 		// 원천 - 환급신청금액
	private String vatRtnYr; 			// 부가 - 신고년기
	private String txtpe; 				// 부가 - 과세유형
	private String vatActlPmtTxamt; 	// 부가 - 실제납부할세액
	private String vlntSbtrPmtTxamt; 	// 종소 - 지방소득세 납부(환급)할세액
	private String rmk;					// 비고
	private String reqCd;				// 요청코드
	private String regId; 				// 등록자ID 
	private String regDt; 				// 등록일시
	private String uptId; 				// 수정자ID
	private String uptDt; 				// 수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getItrfCd() {
		return itrfCd;
	}
	public void setItrfCd(String itrfCd) {
		this.itrfCd = itrfCd;
	}
	public String getTxnrmYm() {
		return txnrmYm;
	}
	public void setTxnrmYm(String txnrmYm) {
		this.txnrmYm = txnrmYm;
	}
	public String getRtnClCd() {
		return rtnClCd;
	}
	public void setRtnClCd(String rtnClCd) {
		this.rtnClCd = rtnClCd;
	}
	public String getRtnDt() {
		return rtnDt;
	}
	public void setRtnDt(String rtnDt) {
		this.rtnDt = rtnDt;
	}
	public String getMtfbCd() {
		return mtfbCd;
	}
	public void setMtfbCd(String mtfbCd) {
		this.mtfbCd = mtfbCd;
	}
	public String getFrsRgtDtm() {
		return frsRgtDtm;
	}
	public void setFrsRgtDtm(String frsRgtDtm) {
		this.frsRgtDtm = frsRgtDtm;
	}
	public String getRtnClNm() {
		return rtnClNm;
	}
	public void setRtnClNm(String rtnClNm) {
		this.rtnClNm = rtnClNm;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getRtnCvaId() {
		return rtnCvaId;
	}
	public void setRtnCvaId(String rtnCvaId) {
		this.rtnCvaId = rtnCvaId;
	}
	public String getRtnClDetailCd() {
		return rtnClDetailCd;
	}
	public void setRtnClDetailCd(String rtnClDetailCd) {
		this.rtnClDetailCd = rtnClDetailCd;
	}
	public String getRtnClDetailNm() {
		return rtnClDetailNm;
	}
	public void setRtnClDetailNm(String rtnClDetailNm) {
		this.rtnClDetailNm = rtnClDetailNm;
	}
	public String getPymnYm() {
		return pymnYm;
	}
	public void setPymnYm(String pymnYm) {
		this.pymnYm = pymnYm;
	}
	public String getSbmsYm() {
		return sbmsYm;
	}
	public void setSbmsYm(String sbmsYm) {
		this.sbmsYm = sbmsYm;
	}
	public String getStmnKndCd() {
		return stmnKndCd;
	}
	public void setStmnKndCd(String stmnKndCd) {
		this.stmnKndCd = stmnKndCd;
	}
	public String getStmnKndNm() {
		return stmnKndNm;
	}
	public void setStmnKndNm(String stmnKndNm) {
		this.stmnKndNm = stmnKndNm;
	}
	public String getTxnrmStrtDt() {
		return txnrmStrtDt;
	}
	public void setTxnrmStrtDt(String txnrmStrtDt) {
		this.txnrmStrtDt = txnrmStrtDt;
	}
	public String getTxnrmEndDt() {
		return txnrmEndDt;
	}
	public void setTxnrmEndDt(String txnrmEndDt) {
		this.txnrmEndDt = txnrmEndDt;
	}
	public String getTxprNm() {
		return txprNm;
	}
	public void setTxprNm(String txprNm) {
		this.txprNm = txprNm;
	}
	public String getTxprNo() {
		return txprNo;
	}
	public void setTxprNo(String txprNo) {
		this.txprNo = txprNo;
	}
	public String getRcatNo() {
		return rcatNo;
	}
	public void setRcatNo(String rcatNo) {
		this.rcatNo = rcatNo;
	}
	public String getRcatDt() {
		return rcatDt;
	}
	public void setRcatDt(String rcatDt) {
		this.rcatDt = rcatDt;
	}
	public String getCvaAplnDtm() {
		return cvaAplnDtm;
	}
	public void setCvaAplnDtm(String cvaAplnDtm) {
		this.cvaAplnDtm = cvaAplnDtm;
	}
	public String getChrgTxhfOgzCd() {
		return chrgTxhfOgzCd;
	}
	public void setChrgTxhfOgzCd(String chrgTxhfOgzCd) {
		this.chrgTxhfOgzCd = chrgTxhfOgzCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStasAmt() {
		return stasAmt;
	}
	public void setStasAmt(String stasAmt) {
		this.stasAmt = stasAmt;
	}
	public String getOgntxSbtrScpmTxamt() {
		return ogntxSbtrScpmTxamt;
	}
	public void setOgntxSbtrScpmTxamt(String ogntxSbtrScpmTxamt) {
		this.ogntxSbtrScpmTxamt = ogntxSbtrScpmTxamt;
	}
	public String getOgntxInpmSchuTxamt() {
		return ogntxInpmSchuTxamt;
	}
	public void setOgntxInpmSchuTxamt(String ogntxInpmSchuTxamt) {
		this.ogntxInpmSchuTxamt = ogntxInpmSchuTxamt;
	}
	public String getOgntxSbtrPmtTxamt() {
		return ogntxSbtrPmtTxamt;
	}
	public void setOgntxSbtrPmtTxamt(String ogntxSbtrPmtTxamt) {
		this.ogntxSbtrPmtTxamt = ogntxSbtrPmtTxamt;
	}
	public String getFnftxSbtrScpmTxamt() {
		return fnftxSbtrScpmTxamt;
	}
	public void setFnftxSbtrScpmTxamt(String fnftxSbtrScpmTxamt) {
		this.fnftxSbtrScpmTxamt = fnftxSbtrScpmTxamt;
	}
	public String getFnftxInpmSchuTxamt() {
		return fnftxInpmSchuTxamt;
	}
	public void setFnftxInpmSchuTxamt(String fnftxInpmSchuTxamt) {
		this.fnftxInpmSchuTxamt = fnftxInpmSchuTxamt;
	}
	public String getFnftxSbtrPmtTxamt() {
		return fnftxSbtrPmtTxamt;
	}
	public void setFnftxSbtrPmtTxamt(String fnftxSbtrPmtTxamt) {
		this.fnftxSbtrPmtTxamt = fnftxSbtrPmtTxamt;
	}
	public String getAprpAfthPmtTxamt() {
		return aprpAfthPmtTxamt;
	}
	public void setAprpAfthPmtTxamt(String aprpAfthPmtTxamt) {
		this.aprpAfthPmtTxamt = aprpAfthPmtTxamt;
	}
	public String getSurcRtnClCd() {
		return surcRtnClCd;
	}
	public void setSurcRtnClCd(String surcRtnClCd) {
		this.surcRtnClCd = surcRtnClCd;
	}
	public String getTmonCrfwRfndTxamt() {
		return tmonCrfwRfndTxamt;
	}
	public void setTmonCrfwRfndTxamt(String tmonCrfwRfndTxamt) {
		this.tmonCrfwRfndTxamt = tmonCrfwRfndTxamt;
	}
	public String getRtnRfndClCd() {
		return rtnRfndClCd;
	}
	public void setRtnRfndClCd(String rtnRfndClCd) {
		this.rtnRfndClCd = rtnRfndClCd;
	}
	public String getRfndAplnAmt() {
		return rfndAplnAmt;
	}
	public void setRfndAplnAmt(String rfndAplnAmt) {
		this.rfndAplnAmt = rfndAplnAmt;
	}
	public String getVatRtnYr() {
		return vatRtnYr;
	}
	public void setVatRtnYr(String vatRtnYr) {
		this.vatRtnYr = vatRtnYr;
	}
	public String getTxtpe() {
		return txtpe;
	}
	public void setTxtpe(String txtpe) {
		this.txtpe = txtpe;
	}
	public String getVatActlPmtTxamt() {
		return vatActlPmtTxamt;
	}
	public void setVatActlPmtTxamt(String vatActlPmtTxamt) {
		this.vatActlPmtTxamt = vatActlPmtTxamt;
	}
	public String getVlntSbtrPmtTxamt() {
		return vlntSbtrPmtTxamt;
	}
	public void setVlntSbtrPmtTxamt(String vlntSbtrPmtTxamt) {
		this.vlntSbtrPmtTxamt = vlntSbtrPmtTxamt;
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
		return "SHometaxZ5002 [compCd=" + compCd + ", itrfCd=" + itrfCd + ", txnrmYm=" + txnrmYm + ", rtnClCd="
				+ rtnClCd + ", rtnDt=" + rtnDt + ", mtfbCd=" + mtfbCd + ", frsRgtDtm=" + frsRgtDtm + ", rtnClNm="
				+ rtnClNm + ", tin=" + tin + ", rtnCvaId=" + rtnCvaId + ", rtnClDetailCd=" + rtnClDetailCd
				+ ", rtnClDetailNm=" + rtnClDetailNm + ", pymnYm=" + pymnYm + ", sbmsYm=" + sbmsYm + ", stmnKndCd="
				+ stmnKndCd + ", stmnKndNm=" + stmnKndNm + ", txnrmStrtDt=" + txnrmStrtDt + ", txnrmEndDt=" + txnrmEndDt
				+ ", txprNm=" + txprNm + ", txprNo=" + txprNo + ", rcatNo=" + rcatNo + ", rcatDt=" + rcatDt
				+ ", cvaAplnDtm=" + cvaAplnDtm + ", chrgTxhfOgzCd=" + chrgTxhfOgzCd + ", userId=" + userId
				+ ", stasAmt=" + stasAmt + ", ogntxSbtrScpmTxamt=" + ogntxSbtrScpmTxamt + ", ogntxInpmSchuTxamt="
				+ ogntxInpmSchuTxamt + ", ogntxSbtrPmtTxamt=" + ogntxSbtrPmtTxamt + ", fnftxSbtrScpmTxamt="
				+ fnftxSbtrScpmTxamt + ", fnftxInpmSchuTxamt=" + fnftxInpmSchuTxamt + ", fnftxSbtrPmtTxamt="
				+ fnftxSbtrPmtTxamt + ", aprpAfthPmtTxamt=" + aprpAfthPmtTxamt + ", surcRtnClCd=" + surcRtnClCd
				+ ", tmonCrfwRfndTxamt=" + tmonCrfwRfndTxamt + ", rtnRfndClCd=" + rtnRfndClCd + ", rfndAplnAmt="
				+ rfndAplnAmt + ", vatRtnYr=" + vatRtnYr + ", txtpe=" + txtpe + ", vatActlPmtTxamt=" + vatActlPmtTxamt
				+ ", vlntSbtrPmtTxamt=" + vlntSbtrPmtTxamt + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId=" + regId
				+ ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
