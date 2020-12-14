package com.newzen.nzscrap.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.newzen.nzscrap.service.ScrapService;

public class ScrapExcutor extends QuartzJobBean{
	private ScrapService scrapService;

	public void setScrapService(ScrapService scrapService) {
		this.scrapService = scrapService;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("#########" + DateUtil.getCurrentFull());
		scrapService.scrap();
	}

}
