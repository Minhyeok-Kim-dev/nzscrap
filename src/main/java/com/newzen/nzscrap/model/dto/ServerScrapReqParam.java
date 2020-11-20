package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 * 비즈북스 - 스크래핑 요청 파라미터
 */
public class ServerScrapReqParam {
	// 공통 fields
	private String compCd;		// 회사코드	
	private String appCd;		// 어플리케이션명
	private String svcCd;		// 서비스코드
	private String orgCd;		// 기관코드
	private String signCert;	// 인증서 der 
	private String signPri;		// 인증서 key
	private String certNm;		// 인증서명
	private String signPw;		// 인증서 비밀번호
	private String agentId;		// 세무대리인 관리번호
	private String agentPw;		// 세무대리인 비밀번호
	
	private String loginId;		// 로그인용 아이디 (여신 / 뱅킹 / 카드)
	private String loginPw;		// 로그인용 비밀번호 (여신 / 뱅킹 / 카드)
	
	// 홈택스
	private String bizNo;		// 사업자 등록번호
	private String fromDt;		// 조회기간 from
	private String toDt;		// 조회기간 to
	
	// 뱅킹
	private String bankCd;		// 은행코드
	private String acctNo;		// 계좌번호
	private String acctPw;		// 계좌비밀번호
	
	// 카드
	private String cardCd;		// 카드사코드
	private String cardNo;		// 카드번호
	
	// 기타
	private int keyCd;			// 뱅킹, 카드 연번 (스크랩시 여러개 조회가능)
	private String reqCd;		// 스크래핑 요청 - 요청코드
	private int scrapMode;		// 회사정보-스크래핑설정 (0:사용자 인증서, 1:세무대리인 인증서)
	private int logInType;		// 등록방법 (1:아이디, 2:인증서, 3:빠른조회(뱅킹))

	// 세금신고 접수증조회 (Z5002)
	private String itrfCd;		// 세목코드 (41:부가세, 14:원천세, 10:종소세, 31:법인세)
	
	// 신용카드 매출자료 조회 (Z4050)
	private String fromY;		// 조회 시작년도
	private String toY;			// 조회 종료년도
	private String fromQ;		// 조회 시작분기
	private String toQ;			// 조회 종료분기
	
	// 현금영수증 매출자료 조회 (Z4060)
	private String stlYr;		// 조회년도
	
	// 수출실적명세서 조회 (Z4070)
	private String wrtArr;		// 멀티조회월 (ex: '202001,202002,...') 
	
	// 부가세 신고용합계표 (Z0006)
	private String supByr;		// 매출/매입 (AL:전체, 01:매출, 02:매입)
	private String taxGb;		// 세금계산서구분 (01:전자세금계산서, 03:전자계산서)
	private String wrtYr;		// 조회년도
	private String wrtQt;		// 조회분기 (1:1기예정, 2:1기확정, 3:1기예정+확정, 4:2기예정, 5:2기확정, 6:2기예정+확정)

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
	public String getCertNm() {
		return certNm;
	}
	public void setCertNm(String certNm) {
		this.certNm = certNm;
	}
	public String getSignPw() {
		return signPw;
	}
	public void setSignPw(String signPw) {
		this.signPw = signPw;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentPw() {
		return agentPw;
	}
	public void setAgentPw(String agentPw) {
		this.agentPw = agentPw;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
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
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
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
	public String getCardCd() {
		return cardCd;
	}
	public void setCardCd(String cardCd) {
		this.cardCd = cardCd;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getKeyCd() {
		return keyCd;
	}
	public void setKeyCd(int keyCd) {
		this.keyCd = keyCd;
	}
	public String getReqCd() {
		return reqCd;
	}
	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}
	public int getScrapMode() {
		return scrapMode;
	}
	public void setScrapMode(int scrapMode) {
		this.scrapMode = scrapMode;
	}
	public int getLogInType() {
		return logInType;
	}
	public void setLogInType(int logInType) {
		this.logInType = logInType;
	}
	public String getItrfCd() {
		return itrfCd;
	}
	public void setItrfCd(String itrfCd) {
		this.itrfCd = itrfCd;
	}
	public String getFromY() {
		return fromY;
	}
	public void setFromY(String fromY) {
		this.fromY = fromY;
	}
	public String getToY() {
		return toY;
	}
	public void setToY(String toY) {
		this.toY = toY;
	}
	public String getFromQ() {
		return fromQ;
	}
	public void setFromQ(String fromQ) {
		this.fromQ = fromQ;
	}
	public String getToQ() {
		return toQ;
	}
	public void setToQ(String toQ) {
		this.toQ = toQ;
	}
	public String getStlYr() {
		return stlYr;
	}
	public void setStlYr(String stlYr) {
		this.stlYr = stlYr;
	}
	public String getWrtArr() {
		return wrtArr;
	}
	public void setWrtArr(String wrtArr) {
		this.wrtArr = wrtArr;
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
	@Override
	public String toString() {
		return "ServerScrapReqParam [compCd=" + compCd + ", appCd=" + appCd + ", svcCd=" + svcCd + ", orgCd=" + orgCd
				+ ", signCert=" + signCert + ", signPri=" + signPri + ", certNm=" + certNm + ", signPw=" + signPw
				+ ", agentId=" + agentId + ", agentPw=" + agentPw + ", loginId=" + loginId + ", loginPw=" + loginPw
				+ ", bizNo=" + bizNo + ", fromDt=" + fromDt + ", toDt=" + toDt + ", bankCd=" + bankCd + ", acctNo="
				+ acctNo + ", acctPw=" + acctPw + ", cardCd=" + cardCd + ", cardNo=" + cardNo + ", keyCd=" + keyCd
				+ ", reqCd=" + reqCd + ", scrapMode=" + scrapMode + ", logInType=" + logInType + ", itrfCd=" + itrfCd
				+ ", fromY=" + fromY + ", toY=" + toY + ", fromQ=" + fromQ + ", toQ=" + toQ + ", stlYr=" + stlYr
				+ ", wrtArr=" + wrtArr + ", supByr=" + supByr + ", taxGb=" + taxGb + ", wrtYr=" + wrtYr + ", wrtQt="
				+ wrtQt + "]";
	}
}
