package com.newzen.nzscrap.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newzen.nzscrap.mapper.ProxyMapper;
import com.newzen.nzscrap.model.dto.ProxyAddr;

@Service
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	ProxyMapper proxyMapper;
	
	private static ArrayList<ProxyAddr> proxyAddrList = null;
	private static int curIdx = 0;
	
	@Override
	public void init() {
		proxyAddrList = proxyMapper.selectProxyAddrList();
		
		if(proxyAddrList != null && proxyAddrList.size() > 0) {
			// 난수처리 (0 ~ proxy 주소 갯수)
			curIdx = (int)(Math.random() * proxyAddrList.size());
		}	
	}

	@Override
	public String getNextAddr() {
		curIdx = curIdx < proxyAddrList.size() - 1 ? ++curIdx : 0;
		
		if(proxyAddrList != null && proxyAddrList.size() > 0) {
			return proxyAddrList.get(curIdx).getAddr();
		}
		
		return "";
	}
	
	@Override
	public int getCurIdx() {
		return curIdx;
	}

}
