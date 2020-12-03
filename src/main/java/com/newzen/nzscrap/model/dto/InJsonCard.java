package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 *	infotech 스크래핑 요청 inJson 파라미터 (카드 <card / ccd>)
 */
public class InJsonCard extends InJson {
	// Fields
	private String loginMethod;	// 로그인 방법 ('ID' / 'CERT')
	private String signCert;	// 인증서 파일 경로(CERT)
	private String signPri;		// 인증서 key 파일 경로(CERT)
	private String signPw;		// 인증서 비밀번호(CERT)
	private String userId;		// 사용자 id (ID)
	private String userPw;		// 사용자 비밀번호 (ID)
	private String cardNo;		// 카드번호
	private String sdate;		// 조회 시작일
	private String edate;		// 조회 종료일
	private String cardCd;		// 카드사 코드
	private String svcOption;	// 가맹점 상세정보

	// Constructors
	public InJsonCard() {
	}
	
	public InJsonCard(String appCd, String orgCd, String svcCd, String reqCd, String proxy) {
		super(appCd, orgCd, svcCd, reqCd, proxy);
	}

	// Getters & Setters
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public String getCardCd() {
		return cardCd;
	}

	public void setCardCd(String cardCd) {
		this.cardCd = cardCd;
	}

	public String getSvcOption() {
		return svcOption;
	}

	public void setSvcOption(String svcOption) {
		this.svcOption = svcOption;
	}

	@Override
	public String toString() {
		return "InJsonCard [loginMethod=" + loginMethod + ", signCert=" + signCert + ", signPri=" + signPri
				+ ", signPw=" + signPw + ", userId=" + userId + ", userPw=" + userPw + ", cardNo=" + cardNo + ", sdate="
				+ sdate + ", edate=" + edate + ", cardCd=" + cardCd + ", svcOption=" + svcOption + "]";
	}
}
