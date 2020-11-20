package com.newzen.nzscrap.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.newzen.nzscrap.model.dto.ProxyAddr;

@Mapper
public interface ProxyMapper {
	ArrayList<ProxyAddr> selectProxyAddrList();
}
