package com.newzen.nzscrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newzen.nzscrap.service.FtpService;
import com.newzen.nzscrap.util.SftpUtil;

@Controller
@RequestMapping(value="sftp")
public class FtpController {
	@Autowired
	FtpService ftpService;
	
	@RequestMapping(value="upload", method=RequestMethod.GET)
	@ResponseBody
	public String upload() {
		SftpUtil sftpUtil = new SftpUtil();
		
		String dirNm = sftpUtil.getNewDirNm();
		System.out.println(dirNm);
		
		//sftpUtil.uploadCertFile("C:/sftp_test.txt", dirNm);
		
		return "true";
		/*
		String filePath = "C:/sftp_test.txt";
		
		File file = new File(filePath);
		
		try {
			fi = new FileInputStream(file);
			chSftp.cd("/Cert/");
			chSftp.put(fi, file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
		*/
	}
	
	
}
