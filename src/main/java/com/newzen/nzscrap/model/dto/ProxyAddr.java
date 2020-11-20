package com.newzen.nzscrap.model.dto;

public class ProxyAddr {
	private String addr;	// 프록시 주소 (ip:port 형식)
	private String regDt;	// 등록일자

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	@Override
	public String toString() {
		return "ProxyAddr [addr=" + addr + ", regDt=" + regDt + "]";
	}
}
