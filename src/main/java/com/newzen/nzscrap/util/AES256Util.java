package com.newzen.nzscrap.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Minhyeok Kim
 * AES256 암호화 유틸입니다.
 *
 * @references
 * - local_policy.jar
 * - US_export_policy.jar
 */
public class AES256Util {
	// keys
	// - 회사_대표자 주민등록번호
	public static final String KEY_COMP_REGSNO = "LWVEwag8yz6cQkvF";
		
	// - 사용자 계정
	public static final String KEY_USER_COMP_CD = "C24ZdBF6JafxULBu";
	public static final String KEY_USER_PW = "Ndpr3egM3NNr34aE";
	// - 인증서
	public static final String KEY_CERT_PATH = "SzFKN2bL8QWtXHQc";
	public static final String KEY_CERT_PW = "Tnj3YTYjKtkp4eLR";
	public static final String KEY_CERT_DT = "vpRY9nVb2WyG4WsW";
	public static final String KEY_CERT_LOGIN_ID = "PTmBhasjhfV9XffF";
	public static final String KEY_CERT_LOGIN_PW = "ejuUay4WrMp5VqCp";
	// - 계좌
	public static final String KEY_ACCOUNT_PW = "PvqvRErMRvS9Q5YY";
	// - 카드
	public static final String KEY_CREDIT_PW = "vUkz4y4jjKgrhyBe";
	// - 세무대리인
	public static final String KEY_AGENT_ID = "aK5WLt7bUuJVRs6q";
	public static final String KEY_AGENT_PW = "geMuJr5G36kYmndE";
	public static final String KEY_AGENT_USER_ID = "rZXvXm9BsXX6jShq";
	public static final String KEY_AGENT_USER_PW = "Vj9DRrdqcXcaw4yF";
	
	
	// init vector
	private String iv = "a9d67c844e0b48e7";	// 타 프로그램 연동용 (KcLep, KRP) 
	
	private Key keySpec;
	private IvParameterSpec ivSpec;
	
	/**
	 * Constructor
	 * 
	 * @param key
	 * - 인증 분류에 따른 key내용을 작성해줍니다.
	 * - 상단 상수리터럴 문자열을 키 값으로 사용합니다.
	 *  
	 * @throws UnsupportedEncodingException
	 */
	public AES256Util(String key) throws UnsupportedEncodingException {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(this.iv.getBytes("UTF-8"));
		
		this.keySpec = keySpec;
		this.ivSpec = ivSpec;
	}
	
	
	/** 
	 * encrypt
	 * - 해당 문자열을 암호화합니다.
	 * 
	 * @param str
	 * - 암호화 처리할 문자열 
	 * @return
	 * - 암호화 된 문자열
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String str) throws NoSuchAlgorithmException
			, NoSuchPaddingException
			, InvalidKeyException
			, InvalidAlgorithmParameterException
			, IllegalBlockSizeException
			, BadPaddingException
			, UnsupportedEncodingException {
		
		if(str == null || str.isEmpty()) {
			return "";
		}
		
		// C++ Encrypt 연동관련 추가 (Java에서 '+'기호 그대로 사용하는 문제)
		str = URLEncoder.encode(str, "UTF-8").replace("+", "%20");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, this.keySpec, this.ivSpec);
		
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		
		return enStr;
	}
	
	
	/**
	 * decrypt
	 * - 해당 문자열의 복호화를 수행합니다.
	 * 
	 * @param str
	 * - 복호화 할 문자열
	 * @return
	 * - 복호화 된 문자열
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decrypt(String str) throws NoSuchAlgorithmException
			, NoSuchPaddingException
			, InvalidKeyException
			, InvalidAlgorithmParameterException
			, UnsupportedEncodingException
			, IllegalBlockSizeException
			, BadPaddingException {
		
		if(str == null || str.isEmpty()) {
			return "";
		}
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);

		byte[] byteStr = Base64.decodeBase64(str.getBytes("UTF-8"));
		String decStr = new String(c.doFinal(byteStr), "UTF-8");
		
		return URLDecoder.decode(decStr, "UTF-8"); 
	}
}
