package com.newzen.nzscrap.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newzen.nzscrap.service.ProxyService;
import com.newzen.nzscrap.util.DateUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	ProxyService proxyService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		// 시작일 , 종료일
		// 이전 7개월 ~ 전월
		String today = "20200701";
		ArrayList<String> arrMonth = DateUtil.getBetweenMonth(
				DateUtil.getMinusMonths("20200101", 1).substring(0, 6)
				, DateUtil.getMinusMonths("20200101", 1).substring(0, 6));
		
		// ',' 포함 문자열로 변환 (ex: '202001, 201002, ...')
		String wrtArr = String.join(",", arrMonth);
		System.out.println(wrtArr);
		
		return "home";
	}
	
}
