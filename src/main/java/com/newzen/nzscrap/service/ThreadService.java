package com.newzen.nzscrap.service;

import java.util.concurrent.Future;

public interface ThreadService {
	Future<String> sendScrap(String inJsonList, String reqCd);
}
