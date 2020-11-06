package com.newzen.nzscrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newzen.nzscrap.service.ScrapService;

@Controller
@RequestMapping(value = "/scrap")
public class ScrapController {
	@Autowired
	ScrapService scrapService;
	
	@RequestMapping(value="insertHistory", method=RequestMethod.POST)
	@ResponseBody
	public String insertHistory(
			@RequestParam(value="jsonReqList") String jsonReqList,
			@RequestParam(value="jsonResList") String jsonResList) {
		return scrapService.insertHistory(jsonReqList, jsonResList);
	}
	
	@RequestMapping(value="insertData", method=RequestMethod.POST)
	@ResponseBody
	public String insertData(
			@RequestParam(value="dataType") String dataType,
			@RequestParam(value="jsonDataList") String jsonDataList) {
		return scrapService.insertData(dataType, jsonDataList);
	}
	
	@RequestMapping(value="deleteData", method=RequestMethod.POST)
	@ResponseBody
	public String deleteData(
			@RequestParam(value="dataType") String dataType,
			@RequestParam(value="jsonDataList") String jsonDataList) {
		return scrapService.deleteData(dataType, jsonDataList);
	}
	
	@RequestMapping(value="sendData", method=RequestMethod.POST)
	@ResponseBody
	public String sendData(
			@RequestParam(value="compCd") String compCd,
			@RequestParam(value="scrapDt") String scrapDt) {
		return scrapService.sendDataToBizbooks(compCd, scrapDt);
	}
	
	
	@RequestMapping(value="scrap", method=RequestMethod.POST)
	@ResponseBody
	public String scrap() {
		return scrapService.scrap();
	}
}
