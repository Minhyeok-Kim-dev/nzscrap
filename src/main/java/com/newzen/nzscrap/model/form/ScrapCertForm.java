package com.newzen.nzscrap.model.form;

import com.newzen.nzscrap.model.dto.HistoryRes;
import com.newzen.nzscrap.model.dto.ServerScrapReqParam;

/**
 * @author Minhyeok Kim
 * 스크래핑에 사용되는 인증 정보 (중복 인증서는 제외) 
 */
public class ScrapCertForm {
	private String certId;								// 인증 식별내용
	private ServerScrapReqParam serverScrapReqParam;	// 스크래핑 요청 파라미터
	private HistoryRes historyRes;						// 스크래핑 결과

	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public ServerScrapReqParam getServerScrapReqParam() {
		return serverScrapReqParam;
	}
	public void setServerScrapReqParam(ServerScrapReqParam serverScrapReqParam) {
		this.serverScrapReqParam = serverScrapReqParam;
	}
	public HistoryRes getHistoryRes() {
		return historyRes;
	}
	public void setHistoryRes(HistoryRes historyRes) {
		this.historyRes = historyRes;
	}
	@Override
	public String toString() {
		return "ScrapCertForm [certId=" + certId + ", serverScrapReqParam=" + serverScrapReqParam + ", historyRes="
				+ historyRes + "]";
	}
}
