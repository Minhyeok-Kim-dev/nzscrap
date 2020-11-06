package com.newzen.nzscrap.service;

public interface FtpService {
	//ChannelSftp connect();
	//void disconnect(ChannelSftp chSftp);
	//String mkDir(ChannelSftp chSftp, String dirNm);
	String upload(String filePath, String dirNm);
}
