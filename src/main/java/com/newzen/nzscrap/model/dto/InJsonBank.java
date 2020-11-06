package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim infotech 스크래핑 요청 inJson 파라미터 (뱅킹 <bank / cbk>)
 */
public class InJsonBank extends InJson {
	private String loginMethod;	// 로그인 방법 ('ID' / 'CERT')
	private String signCert; 	// 인증서 파일 경로 (CERT)
	private String signPri; 	// 인증서 key 파일 경로 (CERT)
	private String signPw; 		// 인증서 비밀번호 (CERT)
	private String userId;		// 사용자 id (ID)
	private String userPw;		// 사용자 비밀번호 (ID)
	private String acctNo; 		// 계좌번호
	private String acctPw;		// 계좌비밀번호 (빠른조회)
	private String bizNo;		// 사업자등록번호 (빠른조회)
	private String sdate; 		// 조회 시작일
	private String edate; 		// 조회 종료일
	private String curCd; 		// 화폐 단위 코드
	private String bankCd; 		// 은행 코드

	public InJsonBank() {
	}

	public InJsonBank(String appCd, String orgCd, String svcCd, String reqCd) {
		super(appCd, orgCd, svcCd, reqCd);
	}

	public String getLoginMethod() {
		return loginMethod;
	}

	public void setLoginMethod(String loginMethod) {
		this.loginMethod = loginMethod;
	}

	public String getSignCert() {
		return signCert;
	}

	public void setSignCert(String signCert) {
		this.signCert = signCert;
	}

	public String getSignPri() {
		return signPri;
	}

	public void setSignPri(String signPri) {
		this.signPri = signPri;
	}

	public String getSignPw() {
		return signPw;
	}

	public void setSignPw(String signPw) {
		this.signPw = signPw;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctPw() {
		return acctPw;
	}

	public void setAcctPw(String acctPw) {
		this.acctPw = acctPw;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getCurCd() {
		return curCd;
	}

	public void setCurCd(String curCd) {
		this.curCd = curCd;
	}

	public String getBankCd() {
		return bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	@Override
	public String toString() {
		return "InJsonBank [loginMethod=" + loginMethod + ", signCert=" + signCert + ", signPri=" + signPri
				+ ", signPw=" + signPw + ", userId=" + userId + ", userPw=" + userPw + ", acctNo=" + acctNo
				+ ", acctPw=" + acctPw + ", bizNo=" + bizNo + ", sdate=" + sdate + ", edate=" + edate + ", curCd="
				+ curCd + ", bankCd=" + bankCd + "]";
	}
}
