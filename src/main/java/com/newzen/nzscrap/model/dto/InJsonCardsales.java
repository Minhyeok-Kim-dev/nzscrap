package com.newzen.nzscrap.model.dto;

/**
 * @author Minhyeok Kim
 *	infotech 스크래핑 요청 inJson 파라미터 (여신금융협회)
 */
public class InJsonCardsales extends InJson {
	// Fields
	private String userId;		// 사용자 id
	private String userPw;		// 사용자 비밀번호
	private String fromDate;	// 조회 시작일
	private String toDate;		// 조회 종료일

	// Constructors
	public InJsonCardsales() {
	}
	
	public InJsonCardsales(String appCd, String orgCd, String svcCd, String reqCd, String proxy) {
		super(appCd, orgCd, svcCd, reqCd, proxy);
	}

	// Getters & Setters
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "InJsonCardsales [userId=" + userId + ", userPw=" + userPw + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}
}
