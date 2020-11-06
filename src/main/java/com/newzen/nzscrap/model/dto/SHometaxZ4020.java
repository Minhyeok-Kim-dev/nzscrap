package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 * 
 * 홈택스_화물운전자복지카드 매입세액 공제금액 조회 DTO
 * 
 * !! 주의 	- 해당 DTO를 유일하게 식별할 키가 없으므로 keyNo를 둠..
 *       	- 내용 insert시 CompCd, trsDt (from ~ to)에 해당하는 내용 삭제 후 insert
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ4020 {
	private int keyNo;						// 키 번호(A.I) - 유일한 식별키가 없음
	private String compCd;					// 회사코드
	private String trsDt;					// 거래일자
	private String crccTxprNm;				// 카드사
	private String wlfCardNoEncCntn;		// 카드번호
	private String mrntTxprDscmNoEncCntn;	// 가맹점사업자번호
	private String mrntTxprNm;				// 가맹점명
	private String splCft;					// 공급가액
	private String vaTxamt;					// 세액
	private String tip;						// 봉사료
	private String totaTrsAmt;				// 합계
	private String bmanClNm;				// 가맹점유형
	private String tfbNm;					// 가맹점업종
	private String trsClNm;					// 거래구분
	private String ddcYnNm;					// 공제여부결정
	private String vatDdcClNm;				// 비고_내역
	private String rmk;						// 비고
	private String reqCd;					// 요청코드
	private String regId;					// 등록자ID
	private String regDt;					// 등록일시
	private String uptId;					// 수정자ID
	private String uptDt;					// 수정일시
	
	private String fromDt;					// 거래일자 시작일 (삭제시 사용)
	private String toDt;					// 거래일자 종료일 (삭제시 사용)

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
	public String getTrsDt() {
		return trsDt;
	}
	public void setTrsDt(String trsDt) {
		this.trsDt = trsDt;
	}
	public String getCrccTxprNm() {
		return crccTxprNm;
	}
	public void setCrccTxprNm(String crccTxprNm) {
		this.crccTxprNm = crccTxprNm;
	}
	public String getWlfCardNoEncCntn() {
		return wlfCardNoEncCntn;
	}
	public void setWlfCardNoEncCntn(String wlfCardNoEncCntn) {
		this.wlfCardNoEncCntn = wlfCardNoEncCntn;
	}
	public String getMrntTxprDscmNoEncCntn() {
		return mrntTxprDscmNoEncCntn;
	}
	public void setMrntTxprDscmNoEncCntn(String mrntTxprDscmNoEncCntn) {
		this.mrntTxprDscmNoEncCntn = mrntTxprDscmNoEncCntn;
	}
	public String getMrntTxprNm() {
		return mrntTxprNm;
	}
	public void setMrntTxprNm(String mrntTxprNm) {
		this.mrntTxprNm = mrntTxprNm;
	}
	public String getSplCft() {
		return splCft;
	}
	public void setSplCft(String splCft) {
		this.splCft = splCft;
	}
	public String getVaTxamt() {
		return vaTxamt;
	}
	public void setVaTxamt(String vaTxamt) {
		this.vaTxamt = vaTxamt;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getTotaTrsAmt() {
		return totaTrsAmt;
	}
	public void setTotaTrsAmt(String totaTrsAmt) {
		this.totaTrsAmt = totaTrsAmt;
	}
	public String getBmanClNm() {
		return bmanClNm;
	}
	public void setBmanClNm(String bmanClNm) {
		this.bmanClNm = bmanClNm;
	}
	public String getTfbNm() {
		return tfbNm;
	}
	public void setTfbNm(String tfbNm) {
		this.tfbNm = tfbNm;
	}
	public String getTrsClNm() {
		return trsClNm;
	}
	public void setTrsClNm(String trsClNm) {
		this.trsClNm = trsClNm;
	}
	public String getDdcYnNm() {
		return ddcYnNm;
	}
	public void setDdcYnNm(String ddcYnNm) {
		this.ddcYnNm = ddcYnNm;
	}
	public String getVatDdcClNm() {
		return vatDdcClNm;
	}
	public void setVatDdcClNm(String vatDdcClNm) {
		this.vatDdcClNm = vatDdcClNm;
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
		return "SHometaxZ4020 [keyNo=" + keyNo + ", compCd=" + compCd + ", trsDt=" + trsDt + ", crccTxprNm="
				+ crccTxprNm + ", wlfCardNoEncCntn=" + wlfCardNoEncCntn + ", mrntTxprDscmNoEncCntn="
				+ mrntTxprDscmNoEncCntn + ", mrntTxprNm=" + mrntTxprNm + ", splCft=" + splCft + ", vaTxamt=" + vaTxamt
				+ ", tip=" + tip + ", totaTrsAmt=" + totaTrsAmt + ", bmanClNm=" + bmanClNm + ", tfbNm=" + tfbNm
				+ ", trsClNm=" + trsClNm + ", ddcYnNm=" + ddcYnNm + ", vatDdcClNm=" + vatDdcClNm + ", rmk=" + rmk
				+ ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt
				+ ", fromDt=" + fromDt + ", toDt=" + toDt + "]";
	}
}
