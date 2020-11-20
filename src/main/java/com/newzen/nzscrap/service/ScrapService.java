package com.newzen.nzscrap.service;

public interface ScrapService {
	String insertHistory(String jsonReqList, String jsonResList);
	String insertData(String dataType, String jsonData);
	String deleteData(String dataType, String jsonData);
	String sendDataToBizbooks(String compCd, String scrapDt);
	String scrap();
	
	
	//######################## 201117_kmh 건별 스크래핑 처리 완료 후 Refactoring
}
