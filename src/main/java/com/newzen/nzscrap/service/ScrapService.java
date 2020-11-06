package com.newzen.nzscrap.service;

public interface ScrapService {
	String insertHistory(String jsonReqList, String jsonResList);
	String insertData(String dataType, String jsonData);
	String deleteData(String dataType, String jsonData);
	
	String sendDataToBizbooks(String compCd, String scrapDt);
	
	
	String scrap();
}
