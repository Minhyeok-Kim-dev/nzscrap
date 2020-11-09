package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 *
 * 홈택스_수출실적명세서 조회
 * 
 */
public class SHometaxZ4070 {
	private String compCd;		// 회사코드
	private String exprRtnNo;	// 수출신고번호
	private String shpnDt;		// 선적일자
	private String isoCrncCd;	// 통화코드
	private String exgrt;		// 환율
	private String fmt;			// 외화금액
	private String wtt;			// 원화환산금액
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
	public String getExprRtnNo() {
		return exprRtnNo;
	}
	public void setExprRtnNo(String exprRtnNo) {
		this.exprRtnNo = exprRtnNo;
	}
	public String getShpnDt() {
		return shpnDt;
	}
	public void setShpnDt(String shpnDt) {
		this.shpnDt = shpnDt;
	}
	public String getIsoCrncCd() {
		return isoCrncCd;
	}
	public void setIsoCrncCd(String isoCrncCd) {
		this.isoCrncCd = isoCrncCd;
	}
	public String getExgrt() {
		return exgrt;
	}
	public void setExgrt(String exgrt) {
		this.exgrt = exgrt;
	}
	public String getFmt() {
		return fmt;
	}
	public void setFmt(String fmt) {
		this.fmt = fmt;
	}
	public String getWtt() {
		return wtt;
	}
	public void setWtt(String wtt) {
		this.wtt = wtt;
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
		return "SHometaxZ4070 [compCd=" + compCd + ", exprRtnNo=" + exprRtnNo + ", shpnDt=" + shpnDt + ", isoCrncCd="
				+ isoCrncCd + ", exgrt=" + exgrt + ", fmt=" + fmt + ", wtt=" + wtt + ", rmk=" + rmk + ", reqCd=" + reqCd
				+ ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + "]";
	}
}
