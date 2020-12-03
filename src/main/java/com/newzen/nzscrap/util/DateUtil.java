package com.newzen.nzscrap.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	/**
	 * getToday
	 * 
	 * 오늘 날짜를 반환합니다.
	 * 
	 * @return 오늘날짜 문자열 (yyyyMMdd)
	 */
	public static String getToday() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * getYear
	 * 
	 * 해당일자의 년도를 반환합니다.
	 * 
	 * @param ymd 	- yyyyMMdd 형식 문자열
	 * @return 해당년도
	 */
	public static String getYear(String ymd) {
		if(ymd.length() != 8) {
			return "";
		}
		
		return LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"))
				.format(DateTimeFormatter.ofPattern("yyyy"));
	}
	
	/**
	 * getLastYear
	 * 
	 * 전 년도를 반환합니다.
	 * 
	 * @return 전년도 문자열 (yyyy)
	 */
	public static String getLastYear() {
		return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy"));
	}
	
	/**
	 * getMonth
	 * 
	 * 해당 일자의 월을 반환합니다. (MM)
	 * 
	 * @param ymd	- yyyyMMdd 형식 무자열
	 * @return 해당 월 문자열 (MM)
	 */
	public static String getMonth(String ymd) {
		if(ymd.length() != 8) {
			return "";
		}
		
		return LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"))
				.format(DateTimeFormatter.ofPattern("MM"));
	}
	
	/**
	 * getMinusMonths
	 * 
	 * 해당 일자에 입력받은 월을 뺀 날짜를 반환합니다.
	 * 
	 * @param ymd	- yyyyMMdd 형식 문자열
	 * @param minus - 빼고자 하는 월 수
	 * @return 연산 결과 날짜 문자열 (yyyyMMdd)
	 */
	public static String getMinusMonths(String ymd, int minus) {
		if(ymd.length() != 8) {
			return "";
		}
		
		LocalDate date = LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		return date.minusMonths(minus).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * getDay
	 * 
	 * 해당 일자의 일을 반환합니다. (dd)
	 * @param ymd	- yyyyMMdd 형식 문자열
	 * @return 해당 일 문자열 (dd)
	 */
	public static String getDay(String ymd) {
		if(ymd.length() != 8) {
			return "";
		}
		
		return LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"))
				.format(DateTimeFormatter.ofPattern("dd"));
	}
	
	/**
	 * getMinusDays
	 * 
	 * 해당 일자에 입력받은 일을 뺀 날짜를 반환합니다.
	 * 
	 * @param ymd	- yyyyMMdd 형식 문자열
	 * @param minus - 빼고자 하는 일 수
	 * @return 연산 결과 날짜 문자열 (yyyyMMdd)
	 */
	public static String getMinusDays(String ymd, int minus) {
		if(ymd.length() != 8) {
			return "";
		}
		
		LocalDate date = LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		return date.minusDays(minus).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * getFirstDayOfMonth
	 * 
	 * 해당 월의 처음 일자를 반환합니다.
	 *  
	 * @param ymd - yyyyMMdd 형식 문자열
	 * @return 해당년월01일(yyyyMMdd)
	 */
	public static String getFirstDayOfMonth(String ymd) {
		LocalDate date = LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return date.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * getLastDayOfMonth
	 * 
	 * 해당 월의 마지막 일자를 반환합니다.
	 * 
	 * @param ymd - yyyyMMdd 형식 문자열
	 * @return 해당년월마지막일(yyyyMMdd)
	 */
	public static String getLastDayOfMonth(String ymd) {
		if(ymd.length() != 8) {
			return "";
		}
		
		LocalDate date = LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return date.withDayOfMonth(date.lengthOfMonth()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * isInRange
	 * 
	 * 해당 일자가 범위일자 내에 포함되어있는지 반환합니다.
	 * 
	 * @param targetYmd - yyyyMMdd 형식 문자열
	 * @param fromYmd	- 비교 시작일 문자열 (yyyyMMdd)
	 * @param toYmd		- 비교 종료일 문자열 (yyyyMMdd)
	 * @return 해당일자가 포함된 경우 true
	 */
	public static boolean isInRange(String targetYmd, String fromYmd, String toYmd) {
		if(targetYmd.length() != 8 && fromYmd.length() != 8 && toYmd.length() != 8) {
			return false;
		}
		LocalDate targetDt = LocalDate.parse(targetYmd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		LocalDate fromDt = LocalDate.parse(fromYmd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		LocalDate toDt = LocalDate.parse(toYmd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		return targetDt.isAfter(fromDt) && targetDt.isBefore(toDt);
	}
	
	/**
	 * getQuarter
	 * 
	 * 해당 일자가 포함된 분기를 반환합니다.
	 * @param ymd	- yyyyMMdd 형식 문자열 
	 * @return 해당 분기
	 */
	public static int getQuarter(String ymd) {
		if(ymd.length() != 8) {
			return 0;
		}
		
		LocalDate date = LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return date.get(IsoFields.QUARTER_OF_YEAR);
	}
	
	
	
	
	/**
	 * getLastDt
	 * 
	 * @return yyyyMMddHHmmssSSS 형식 반환
	 */
	public static String getLastDt() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		
		return df.format(date);
	}
	
	/**
	 * getUniqueKey
	 * 
	 * @return yyyyMMddHHmmssSSS 형식 반환 (Locale설정)
	 */
	public static String getUniqueKey() {
        Date date = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        //기본적으로 국내에서 또는 타임존을 한국으로 세팅한경우 Locale 세팅이 필요치는 않다.
        String pattern = "yyyyMMddHHmmssSSS";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        
        return formatter.format(date);
    }
	
	/**
	 * getDate
	 * 
	 * 날짜 길이별로 파싱처리 
	 * 
	 * @return 현재날짜 yyyy-MM-dd
	 * @throws ParseException
	 */
	public static String getDate() {
		return getDate(getLastDt().substring(0, 8));
	}
	
	/**
	 * getDate
	 * 
	 * 날짜 길이별로 파싱처리 
	 * @param date - 날짜('-'제외)
	 * @return 길이 6 -> yyyy-MM 길이 8 -> yyyy-MM-dd, 길이 12 -> yyyy-MM-dd HH:mm 
	 * @throws ParseException
	 */
	public static String getDate(String date) {
		if(date == null || date.length() == 0) {
			return "";
		}
		
		DateFormat dateFormatToCal;
		DateFormat dateFormatFromCal;

		int len = date.length();
		
		switch (len) {
		case 6:
			// 임시로 1일 추가 후 삭제 
			date += "01";
			dateFormatToCal = new SimpleDateFormat("yyyyMMdd");
			dateFormatFromCal = new SimpleDateFormat("yyyy-MM");
			break;
		case 8:
			dateFormatToCal = new SimpleDateFormat("yyyyMMdd");
			dateFormatFromCal = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 12 :
			dateFormatToCal = new SimpleDateFormat("yyyyMMddHHmm");
			dateFormatFromCal = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		default :
			dateFormatToCal = new SimpleDateFormat("");
			dateFormatFromCal = new SimpleDateFormat("");
			break;
		}
		
		try {
			return dateFormatFromCal.format(dateFormatToCal.parse(date));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
	
	/**
	 * getCurrentFull
	 * 
	 * 현재년도월일시분초밀리세컨드 형태로 리턴
	 * @return
	 */
	public static String getCurrentFull() {
        Date date = new Date();
        String pattern = "yyyyMMddHHmmssSSS";
        SimpleDateFormat simpleformat = new SimpleDateFormat(pattern, java.util.Locale.KOREA);
        return simpleformat.format(date);
    }
	
	
	/**
	 * getBetweenMonth
	 * 
	 * fromYm ~ toYm 사이의 년월을 반환
	 * @param fromYm 기준 시작년월
	 * @param toYm 기준 종료년월
	 * @return ArrayList<String> (ex: '202001', '202002'...)
	 */
	public static ArrayList<String> getBetweenMonth(String fromYm, String toYm) {
		if((fromYm != null && fromYm.length() != 6) &&
			(toYm != null && toYm.length() != 6)) {
			return null;
		}
		
		ArrayList<String> arrMonth = new ArrayList<>();
		
		DateFormat fmt = new SimpleDateFormat("yyyyMM");
		
		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		
		try {
			fromCal.setTime(fmt.parse(fromYm));
			toCal.setTime(fmt.parse(toYm));
		} catch(ParseException e) {
			e.printStackTrace();
		} 
		
		// 기준 시작년월 추가
		arrMonth.add(fmt.format(fromCal.getTime()));

		// 기준 종료년월까지 내용 추가
		while(fromCal.before(toCal)) {
			fromCal.add(Calendar.MONTH, 1);
			arrMonth.add(fmt.format(fromCal.getTime()));
		}
		
		return arrMonth;
	}
}