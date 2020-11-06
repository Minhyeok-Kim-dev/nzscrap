package com.newzen.nzscrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newzen.nzscrap.util.AES256Util;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		try {
			
			AES256Util aes256Util = new AES256Util(AES256Util.KEY_AGENT_ID);
			AES256Util aes256Util2 = new AES256Util(AES256Util.KEY_USER_PW);
			
			AES256Util tmp = new AES256Util(AES256Util.KEY_COMP_REGSNO);
			
			AES256Util agentIdUtil = new AES256Util(AES256Util.KEY_AGENT_ID);
			AES256Util agentPwUtil = new AES256Util(AES256Util.KEY_AGENT_PW);
			
			String id = agentIdUtil.decrypt("IPRVDWXRDi74UBTCuo4jLA==");
			String pw = agentPwUtil.decrypt("5wzb270ViaFVYamBnTTqWg==");
					
			String ret = tmp.decrypt("+3TMDwfW3/+et6eH0w9yYg==");
			tmp.decrypt("+3TMDwfW3/+et6eH0w9yYg==");
			tmp.decrypt("PlYK3FkxHimXEX4dLQ0JIg==");
			
			
			System.out.println(id);
			System.out.println(pw);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home";
	}
	
}
