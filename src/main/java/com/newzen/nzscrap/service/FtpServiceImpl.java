package com.newzen.nzscrap.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class FtpServiceImpl implements FtpService {
	private final String FTP_IP ="183.111.102.219";
	private final int FTP_PORT = 22200;
	private final String FTP_ID = "newzen";
	private final String FTP_PWD = "!@#sbwps09";
	private final String CERT_DIR = "/Cert/";

	@Override
	public String upload(String filePath, String dirNm) {
		// Connect
		ChannelSftp chSftp = connect();
		
		if(chSftp != null) {
			try {
				// TODO: dir 있는 경우 해당 dir내용 삭제루틴 추가
				//rmDir(chSftp, path);
				
				// MkDir
				String dirPath = mkDir(chSftp, dirNm);
				System.out.println("path: " + dirPath);
				
				// Upload
				File file = new File(filePath);
				
				FileInputStream fi;
				fi = new FileInputStream(file);
				chSftp.cd(dirPath);
				chSftp.put(fi, file.getName());
				
				System.out.println("############## upload finish!");
			} catch (SftpException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				// Disconnect
				disconnect(chSftp);
			}
			
		}
		
		return null;
	}

	
	
	private ChannelSftp connect() {
		// FTP 관련 객체 선언
		Session ses = null;
		Channel ch = null;
		JSch jsch = new JSch();
		
		try {
			// 세션 객체 생성 (id, host, port)
			ses =jsch.getSession(FTP_ID, FTP_IP, FTP_PORT);
			// 비밀번호 설정
			ses.setPassword(FTP_PWD);
			
			// 세션정보 설정
			Properties props = new Properties();
			
			// - 호스트 정보 검사X
			props.put("StrictHostKeyChecking", "no");
			ses.setConfig(props);
			
			System.out.println("############## 연결중");
			
			// 접속
			ses.connect();
			
			// sftp 채널 open
			ch = ses.openChannel("sftp");
			
			// sftp 채널 연결
			ch.connect();
			
			System.out.println("############# SFTP Connected!");

			// 채널을 FTP용 채널 객체로 캐스팅
			return (ChannelSftp)ch;
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("############# SFTP Connect Failed!");
		}
		
		return null;
	}

	private void disconnect(ChannelSftp chSftp) {
		chSftp.quit();
	}
	
	private String mkDir(ChannelSftp chSftp, String dirNm) throws SftpException {
		chSftp.cd(CERT_DIR);
		chSftp.mkdir(dirNm);
		System.out.println("############# mkDir");
		return CERT_DIR + dirNm;
	}
	
	private void rmDir(ChannelSftp chSftp, String path) throws SftpException {
		// 디렉토리 내 삭제할 파일, 디렉토리 리스트
		Collection<ChannelSftp.LsEntry> rmList = chSftp.ls(path);
		
		for(ChannelSftp.LsEntry item: rmList) {
			if(!item.getAttrs().isDir()) {
				// 파일 삭제
				chSftp.rm(path + "/" + item.getFilename());
			} else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
				try {
					// 하위 디렉토리 삭제
					chSftp.rmdir(path + "/" + item.getFilename());
				} catch(Exception e) {
					// 재귀호출로 하위디렉토리 내 파일 삭제
					rmDir(chSftp, path + "/" + item.getFilename());
				}
			}
		}
		// 전체 파일, 하위 디렉토리 삭제 후 현재 dir 삭제
		chSftp.rm(path);
	}
}
