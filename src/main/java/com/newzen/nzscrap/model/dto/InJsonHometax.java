package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 * 	infotech 스크래핑 요청 inJson 파라미터 (홈택스)
 */
public class InJsonHometax extends InJson {
	// Fields
	// - 공통 Fields
	private String signCert;	// 인증서 파일 경로
	private String signPri;		// 인증서 key 파일 경로
	private String signPw;		// 인증서 비밀번호
	private String userId;		// 사용자 아이디	
	private String userPw;		// 사용자 비밀번호
	private String agentId;		// 세무대리인 관리번호		
	private String agentPw;		// 세무대리인 비밀번호
	private String bizNo;		// 사업자등록번호
	private String inqrDtStrt;	// 조회 시작일
	private String inqrDtEnd;	// 조회 종료일
	
	// - 세금신고 접수증조회 (Z5002)
	private String itrfCd;		// 세목코드 (41:부가세, 14:원천세, 10:종소세, 31:법인세)
	
	// - 신용카드 매출자료 조회 (Z4050)
	private String fromY;		// 조회 시작년도
	private String toY;			// 조회 종료년도
	private String fromQ;		// 조회 시작분기
	private String toQ;			// 조회 종료분기
	
	// - 현금영수증 매출자료 조회 (Z4060)
	private String stlYr;		// 조회년도
	
	// - 부가세합계표 조회 (Z5002)
	private String supByr;		// 매출/매입 (AL:전체, 01:매출, 02:매입)
	private String taxGb;		// 세금계산서구분 (01:전자세금계산서, 03:전자계산서)
	private String wrtYr;		// 조회년도
	private String wrtQt;		// 조회분기 (1:1기예정, 2:1기확정, 3:1기예정+확정, 4:2기예정, 5:2기확정, 6:2기예정+확정)
	
	
	// Constructors
	public InJsonHometax() {
	}

	public InJsonHometax(String appCd, String orgCd, String svcCd, String reqCd) {
		super(appCd, orgCd, svcCd, reqCd);
	}

	// Getters & Setters
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

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getInqrDtStrt() {
		return inqrDtStrt;
	}

	public void setInqrDtStrt(String inqrDtStrt) {
		this.inqrDtStrt = inqrDtStrt;
	}

	public String getInqrDtEnd() {
		return inqrDtEnd;
	}

	public void setInqrDtEnd(String inqrDtEnd) {
		this.inqrDtEnd = inqrDtEnd;
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
		return "InJsonHometax [signCert=" + signCert + ", signPri=" + signPri + ", signPw=" + signPw + ", userId="
				+ userId + ", userPw=" + userPw + ", agentId=" + agentId + ", agentPw=" + agentPw + ", bizNo=" + bizNo
				+ ", inqrDtStrt=" + inqrDtStrt + ", inqrDtEnd=" + inqrDtEnd + ", itrfCd=" + itrfCd + ", fromY=" + fromY
				+ ", toY=" + toY + ", fromQ=" + fromQ + ", toQ=" + toQ + ", stlYr=" + stlYr + ", supByr=" + supByr
				+ ", taxGb=" + taxGb + ", wrtYr=" + wrtYr + ", wrtQt=" + wrtQt + "]";
	}
}
