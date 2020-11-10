package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_사업용신용카드 매입세액 공제 확인/변경 DTO
 * 
 * 201109_kmh 
 * - 날짜 and 공급가액(or 합계금액) and 사업자번호 동일할 경우 중복으로 체크
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ4010 {
	private String compCd;					// 회사코드	(P)
	private String aprvDt;					// 승인일자	(P)
	private String vatDdcClCd;				// 카드사코드
	private String crcmClNm;				// 카드사
	private String busnCrdCardNoEncCntn;	// 카드번호
	private String mrntTxprDscmNoEncCntn;	// 가맹점사업자번호	(P)
	private String mrntTxprNm;				// 가맹점명
	private String splCft;					// 공급가액	(P)
	private String vaTxamt;					// 세액
	private String tip;						// 봉사료
	private String totaTrsAmt;				// 합계		(P)
	private String bmanClNm;				// 가맹점유형
	private String bcNm;					// 업태
	private String tfbNm;					// 업종
	private String ddcYnNm;					// 공제여부결정
	private String vatDdcClNm;				// 비고_내역
	private String rmk;						// 비고
	private String markPen;					// 형광펜
	private String reqCd;					// 요청코드
	private String regId;					// 등록자ID
	private String regDt;					// 등록일시
	private String uptId;					// 수정자ID
	private String uptDt;					// 수정일자

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getAprvDt() {
		return aprvDt;
	}
	public void setAprvDt(String aprvDt) {
		this.aprvDt = aprvDt;
	}
	public String getVatDdcClCd() {
		return vatDdcClCd;
	}
	public void setVatDdcClCd(String vatDdcClCd) {
		this.vatDdcClCd = vatDdcClCd;
	}
	public String getCrcmClNm() {
		return crcmClNm;
	}
	public void setCrcmClNm(String crcmClNm) {
		this.crcmClNm = crcmClNm;
	}
	public String getBusnCrdCardNoEncCntn() {
		return busnCrdCardNoEncCntn;
	}
	public void setBusnCrdCardNoEncCntn(String busnCrdCardNoEncCntn) {
		this.busnCrdCardNoEncCntn = busnCrdCardNoEncCntn;
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
	public String getBcNm() {
		return bcNm;
	}
	public void setBcNm(String bcNm) {
		this.bcNm = bcNm;
	}
	public String getTfbNm() {
		return tfbNm;
	}
	public void setTfbNm(String tfbNm) {
		this.tfbNm = tfbNm;
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
	@Override
	public String toString() {
		return "SHometaxZ4010 [compCd=" + compCd + ", aprvDt=" + aprvDt + ", vatDdcClCd=" + vatDdcClCd + ", crcmClNm="
				+ crcmClNm + ", busnCrdCardNoEncCntn=" + busnCrdCardNoEncCntn + ", mrntTxprDscmNoEncCntn="
				+ mrntTxprDscmNoEncCntn + ", mrntTxprNm=" + mrntTxprNm + ", splCft=" + splCft + ", vaTxamt=" + vaTxamt
				+ ", tip=" + tip + ", totaTrsAmt=" + totaTrsAmt + ", bmanClNm=" + bmanClNm + ", bcNm=" + bcNm
				+ ", tfbNm=" + tfbNm + ", ddcYnNm=" + ddcYnNm + ", vatDdcClNm=" + vatDdcClNm + ", rmk=" + rmk
				+ ", markPen=" + markPen + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId="
				+ uptId + ", uptDt=" + uptDt + "]";
	}
}
