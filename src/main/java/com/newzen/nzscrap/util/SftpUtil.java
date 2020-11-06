package com.newzen.nzscrap.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author Minhyeok Kim
 *
 * 중계서버와의 SFTP(Secure File Transfer Protocal) 처리에 사용되는 util class입니다.
 * 
 */
/**
 * @author Minhyeok Kim
 *
 */
public class SftpUtil {
	// FTP 접속정보
	private final String FTP_IP ="183.111.102.219";
	private final int FTP_PORT = 22200;
	private final String FTP_ID = "newzen";
	private final String FTP_PWD = "!@#sbwps09";
	
	// 인증서 경로정보
	private final String CERT_DIR = "/Cert/";

	
	/* Public Methods */
	/**
	 * uploadCertFile
	 *  - 인증서 파일 업로드를 수행합니다.
	 *  - pem 기반 임시파일 생성 -> 인증서 폴더 생성 -> 인증서 저장 순서로 진행됩니다. 
	 * 
	 * @param pem
	 *  - upload할 인증서 pem
	 *  
	 * @param type
	 *  - 파일 구분 (1: der파일, 2: key파일)
	 *  
	 * @param dirNm
	 *  - 인증서 폴더명
	 *  
	 * @return
	 *  - 정상 처리시 true
	 */
	public boolean uploadCertFile(String pem, int type, String dirNm) {
		// Connect
		ChannelSftp chSftp = connect();
		BufferedWriter bw = null;
		
		if(chSftp != null) {
			try {
				// temp file 생성
				File tmpFile = File.createTempFile("tmpFile", ".tmp");
				tmpFile.deleteOnExit();
				
				bw = new BufferedWriter(new FileWriter(tmpFile));
				bw.write(pem);
				bw.close();
				
				String fileNm = "";
				
				switch(type) {
				case 1:	// der
					fileNm = "signCert.der";
					break;
				case 2: // key 
					fileNm = "signPri.key";
				}
				
				// MkDir
				String dirPath = mkDir(chSftp, dirNm);
				
				// Upload
				FileInputStream fi;
				fi = new FileInputStream(tmpFile);
				chSftp.cd(dirPath);
				// - 파일명 변경 -> 전송
				chSftp.put(fi, fileNm);
				
				return true;
				
			} catch (SftpException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// Disconnect
				disconnect(chSftp);
				
				if(bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		return false;
	}

	
	/**
	 * deleteFile
	 * - 파일을 삭제합니다.
	 * 
	 * @param dirNm
	 * - 삭제 할 디렉토리 명을 작성합니다.
	 * 
	 * @return
	 * - 정상 삭제시 true 반환
	 */
	public boolean deleteFile(String dirNm) {
		// Connect
		ChannelSftp chSftp = connect();
		
		try {
			if(isExistDir(chSftp, dirNm)) {
				rmDir(chSftp, dirNm);
				return true;
			}
		} catch (SftpException e) {
			System.out.println(e.getMessage());
		} finally {
			// Disconnect
			disconnect(chSftp);
		}
		
		return false;
	}

	
	/**
	 * getNewDirNm
	 * - 중계서버 인증서 경로에 없는 임의 디렉토리 명을 반환합니다. 
	 * 
	 * @return 
	 * - 새로운 디렉토리명 
	 */
	public String getNewDirNm() {
		// Connect
		ChannelSftp chSftp = connect();
		
		String dirNm = getRandomStr();
		
		try {
			while(isExistDir(chSftp, dirNm)) {
				dirNm = getRandomStr();
			}
		} catch (SftpException e) {
			System.out.println(e.getMessage());
		} finally {
			// Disconnect
			disconnect(chSftp);
		}
		
		return dirNm;
	}
	

	public String getFileData(String dirPath, String fileNm) {
		if(dirPath == null || dirPath.length() == 0) {
			return "";
		}
		
		// Connect
		ChannelSftp chSftp = connect();
		
		if(chSftp != null) {
			try {
				dirPath = CERT_DIR + dirPath;
				
				chSftp.cd(dirPath);
				
				String data = new BufferedReader(
			      new InputStreamReader(chSftp.get(fileNm), StandardCharsets.UTF_8))
			        .lines()
			        .collect(Collectors.joining("\n"));
			        
		        return data;
					 
				
			} catch (SftpException e) {
				e.printStackTrace();
			} finally {
				// Disconnect
				disconnect(chSftp);
			}
		}
		
		return "";
	}
	
	/* Private Methods */
	/**
	 * connect
	 * - SFTP에 연결합니다.
	 * - 연결 성공시 sftp object를 반환하며 이 후 기능들은 해당 객체를 통해 처리합니다.
	 * 
	 * @return 
	 * - ChannelSftp Object: 연결 성공
	 * - null : 연결 실패 
	 */
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
			
			//System.out.println("############## 연결중");
			
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
			System.out.println("############# SFTP Connect Failed!");
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	
	/**
	 * disconnect
	 * - SFTP 연결을 해제합니다.
	 * 
	 * @param chSftp 
	 * - 연결 해제할 ChannelSftp Object
	 */
	private void disconnect(ChannelSftp chSftp) {
		chSftp.quit();
		System.out.println("############# SFTP Disconnected!");
	}
	
	
	/**
	 * mkDir
	 * - 중계서버 인증서 저장공간에 새로운 디렉토리를 생성합니다.
	 * 
	 * @param chSftp
	 * - SFTP연결에 사용되는 Object
	 * 
	 * @param dirNm
	 * - 새로운 디렉토리 명
	 * 
	 * @return
	 * - 인증서 저장경로 full path
	 * @throws SftpException
	 */
	private String mkDir(ChannelSftp chSftp, String dirNm) throws SftpException {
		chSftp.cd(CERT_DIR);
		chSftp.mkdir(dirNm);
		System.out.println("############# mkDir");
		return CERT_DIR + dirNm;
	}
	
	
	/**
	 * rmDir 
	 * - 해당 디렉토리를 삭제합니다.
	 * - 디렉토리 내 모든 파일, 하위 디렉토리가 삭제됩니다.
	 * 
	 * @param chSftp
	 * - SFTP연결에 사용되는 Object
	 * 
	 * @param path
	 * - 삭제할 디렉토리 명
	 * 
	 * @throws SftpException
	 */
	private void rmDir(ChannelSftp chSftp, String dirNm) throws SftpException {
		chSftp.cd(CERT_DIR);
		
		// 디렉토리 내 삭제할 파일, 디렉토리 리스트
		Collection<ChannelSftp.LsEntry> rmList = chSftp.ls(dirNm);
		
		for(ChannelSftp.LsEntry item: rmList) {
			if(!item.getAttrs().isDir()) {
				// 파일 삭제
				chSftp.rm(dirNm + "/" + item.getFilename());
			} else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
				try {
					// 하위 디렉토리 삭제
					chSftp.rmdir(dirNm + "/" + item.getFilename());
				} catch(Exception e) {
					// 재귀호출로 하위디렉토리 내 파일 삭제
					rmDir(chSftp, dirNm + "/" + item.getFilename());
				}
			}
		}
		// 전체 파일, 하위 디렉토리 삭제 후 현재 dir 삭제
		chSftp.rmdir(dirNm);
	}
	
	
	
	
	
	/**
	 * isExistDir
	 * - 중계서버 인증서 경로에 해당 디렉토리 존재 여부를 반환합니다. 
	 *  
	 * @param chSftp
	 * - SFTP연결에 사용되는 Object
	 * 
	 * @param dirNm
	 * - 디렉토리 명
	 * 
	 * @return
	 * - 해당 디렉토리가 있는 경우 true 반환
	 * 
	 * @throws SftpException 
	 */
	private boolean isExistDir(ChannelSftp chSftp, String dirNm) throws SftpException {
		Collection<ChannelSftp.LsEntry> rmList = chSftp.ls(CERT_DIR);
	
		for(ChannelSftp.LsEntry item: rmList) {
			if(item.getAttrs().isDir() && item.getFilename().equals(dirNm)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * getRandomStr
	 * - 랜덤문자열을 반환합니다 (40~50자)
	 * - 임의 디렉토리 명 제작에 사용됩니다. 
	 * 
	 * @return 
	 * - 랜덤으로 작성된 40 ~ 50자 문자열
	 */
	private String getRandomStr() {
		StringBuffer sb = new StringBuffer();
		
		// 문자열 길이를 40 ~ 50자까지 임의로 설정
		int max = (int)(Math.random() * 10) + 40;
		
		Random rnd = new Random();
		for(int i = 0; i < max; i++) {
			int rIdx = rnd.nextInt(3);
			switch(rIdx) {
			case 0:	// a-z
				sb.append((char)((int)(rnd.nextInt(26)) + 97));
				break;
			case 1:	// A-Z
				sb.append((char)((int)(rnd.nextInt(26)) + 65));
				break;
			case 2:	// 0-9
				sb.append(rnd.nextInt(10));
				break;
			}
		}
		
		return sb.toString();
	}
}
