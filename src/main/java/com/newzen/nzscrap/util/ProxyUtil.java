package com.newzen.nzscrap.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newzen.nzscrap.mapper.ProxyMapper;
import com.newzen.nzscrap.model.dto.ProxyAddr;

public class ProxyUtil {
	@Autowired
	ProxyMapper proxyMapper;
	
	private ArrayList<ProxyAddr> proxyAddrList = null;
	private int idx = 0;
	
	public ProxyUtil() {
		proxyAddrList = proxyMapper.selectProxyAddrList();
		
		if(proxyAddrList != null && proxyAddrList.size() > 0) {
			// 난수처리 (0 ~ proxy 주소 갯수)
			idx = (int)(Math.random() * proxyAddrList.size());
		}
	}

	public String getNextAddr() {
		idx = idx > proxyAddrList.size() - 1 ? ++idx : 0;
		
		if(proxyAddrList != null && proxyAddrList.size() > 0) {
			return proxyAddrList.get(idx).getAddr();
		}
		
		return "";
	}
}
