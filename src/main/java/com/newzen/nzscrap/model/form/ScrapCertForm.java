package com.newzen.nzscrap.model.form;

import com.newzen.nzscrap.model.dto.HistoryReq;
import com.newzen.nzscrap.model.dto.HistoryRes;
import com.newzen.nzscrap.model.dto.ServerScrapReqParam;

/**
 * @author Minhyeok Kim
 * 스크래핑에 사용되는 인증 정보 (중복 인증서는 제외) 
 */
public class ScrapCertForm {
	/* svcCd - 서비스코드
	 * - 홈택스
	 *  > hometaxZ300X 		- 전자세금계산서
	 *  > hometaxZ400X 		- 현금영수증
	 *  > hometaxZ40X0 		- 사업용(화물복지)신용카드
	 *  > hometaxZXXXX 		- 기타 홈택스 스크래핑
	 *   
	 * - 여신
	 *  > cardsalesBXXXX 	- 여신 스크래핑
	 *   
	 * - 카드
	 *  > creditCcd 		- 법인카드
	 *  > creditCard 		- 개인카드
	 *  
	 * - 통장
	 *  > accountCbk		- 법인통장
	 *  > accountBank		- 개인통장
	 *  > accountSbk		- 빠른조회
	 */
	private String svcCd;								
	private ServerScrapReqParam serverScrapReqParam;	// 스크래핑 요청 파라미터
	private String inJson;								// 요청 json 전문			
	private HistoryReq historyReq;						// 스크래핑 요청 내용
	private HistoryRes historyRes;						// 스크래핑 응답 내용

	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public ServerScrapReqParam getServerScrapReqParam() {
		return serverScrapReqParam;
	}
	public void setServerScrapReqParam(ServerScrapReqParam serverScrapReqParam) {
		this.serverScrapReqParam = serverScrapReqParam;
	}
	public String getInJson() {
		return inJson;
	}
	public void setInJson(String inJson) {
		this.inJson = inJson;
	}
	public HistoryReq getHistoryReq() {
		return historyReq;
	}
	public void setHistoryReq(HistoryReq historyReq) {
		this.historyReq = historyReq;
	}
	public HistoryRes getHistoryRes() {
		return historyRes;
	}
	public void setHistoryRes(HistoryRes historyRes) {
		this.historyRes = historyRes;
	}
	@Override
	public String toString() {
		return "ScrapCertForm [svcCd=" + svcCd + ", serverScrapReqParam=" + serverScrapReqParam + ", inJson=" + inJson
				+ ", historyReq=" + historyReq + ", historyRes=" + historyRes + "]";
	}
}
