package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_신용카드 매출 자료 조회
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SHometaxZ4050 {
	private String compCd;			//회사코드
	private String stlYm;			//승인년월
	private String stlScnt;			//건수
	private String totaStlAmt;		//매출액계
	private String etcSls;			//신용카드/기타매출
	private String purcEuCardSls;	//구매전용카드매출
	private String tip;				//봉사료
	private String rmk;				//비고
	private String reqCd;			//요청코드
	private String regId;			//등록자 아이디
	private String regDt;			//등록일시
	private String uptId;			//수정자 아이디
	private String uptDt;			//수정일시

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getStlYm() {
		return stlYm;
	}
	public void setStlYm(String stlYm) {
		this.stlYm = stlYm;
	}
	public String getStlScnt() {
		return stlScnt;
	}
	public void setStlScnt(String stlScnt) {
		this.stlScnt = stlScnt;
	}
	public String getTotaStlAmt() {
		return totaStlAmt;
	}
	public void setTotaStlAmt(String totaStlAmt) {
		this.totaStlAmt = totaStlAmt;
	}
	public String getEtcSls() {
		return etcSls;
	}
	public void setEtcSls(String etcSls) {
		this.etcSls = etcSls;
	}
	public String getPurcEuCardSls() {
		return purcEuCardSls;
	}
	public void setPurcEuCardSls(String purcEuCardSls) {
		this.purcEuCardSls = purcEuCardSls;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
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
		return "SHometaxZ4050 [compCd=" + compCd + ", stlYm=" + stlYm + ", stlScnt=" + stlScnt + ", totaStlAmt="
				+ totaStlAmt + ", etcSls=" + etcSls + ", purcEuCardSls=" + purcEuCardSls + ", tip=" + tip + ", rmk="
				+ rmk + ", reqCd=" + reqCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt="
				+ uptDt + "]";
	}
}
