package com.newzen.nzscrap.service;

/**
 * @author Minhyeok Kim
 * 
 * 스크래핑 Proxy 연동관련 Service
 */
public interface ProxyService {
	/**
	 * ProxyService를 초기화합니다.
	 * - 서비스 사용시에 한번만 초기화 해줍니다.
	 * - 현재 등록된 Proxy:ip 주소 리스트 내용을 가져온 후 배열 인덱스를 난수처리합니다.
	 */
	void init();
	
	/**
	 * 다음 Proxy주소 내용을 반환합니다. 
	 * @return 다음 Proxy:ip 주소
	 */
	String getNextAddr();
	
	/**
	 * 현재 배열 인덱스를 반환합니다.
	 * @return 현재 배열 인덱스
	 */
	int getCurIdx();	 
}
