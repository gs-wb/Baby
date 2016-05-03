package com.yikang.health.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

public class TimeUtils {

	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String toDateAndTimeString(Date date) {
		return formatter.format(date);
	}

	private static SimpleDateFormat formatSimple = new SimpleDateFormat(
			"yyyyMMdd");
	
	public static SimpleDateFormat formatSimple2 = new SimpleDateFormat(
			"MM月dd日");
	
	public static SimpleDateFormat formatSimple3 = new SimpleDateFormat(
			"yyyy");

	/**
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String toSimpleDateAndTimeString(Date date) {
		return formatSimple.format(date);
	}

	/**
	 * @param dateAndTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDateAndTime(String dateAndTime) {
		try {
			return formatter.parse(dateAndTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	private static SimpleDateFormat formatShort = new SimpleDateFormat(
			"MM/dd HH:mm");

	private static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

	/**
	 * 
	 * @param date
	 * @return
	 */

	public static String toDateAndShortTimeString(Date date) {
		return formatTime.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */

	public static String toDateAndTimeShortString(Date date) {
		return formatShort.format(date);
	}

	private static SimpleDateFormat fullShort = new SimpleDateFormat(
			"yyyy年MM月dd日");

	public static SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"MM-dd HH:mm");
	public static SimpleDateFormat  mDate = new SimpleDateFormat("yyyy-MM-dd");
	public static String formatDateTime(long time) {
		if (0 == time) {
			return "";
		}
		return mDateFormat.format(new Date(time));
	}
	public static String formatDateday(String time){
		if (TextUtils.isEmpty(time)) {
			return "";
		}
		return mDate.format(getDateByStr2(time));
	}
	/**
	 * 
	 * @param date
	 * @return
	 */

	public static String toDateAndTimeFullString(Date date) {
		return fullShort.format(date);
	}



	/**
	 * @param calendar
	 * @param field
	 * @param value
	 * @param begin
	 */

	public static void setCalendar(Calendar calendar, int field, int value,
			boolean begin, boolean append) {
		if (append) {
			calendar.set(field, calendar.get(field) + value);
		} else {
			calendar.set(field, value);
		}
		if (begin) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
	}

	/**
	 * 
	 * @param today
	 * @param date
	 * @return
	 */

	public static int dateDiff(Calendar today, Calendar date) {

		Calendar d1 = (Calendar) today.clone();
		Calendar d2 = (Calendar) date.clone();
		d1.set(d1.HOUR, 0);
		d1.set(d1.MINUTE, 0);
		d1.set(d1.SECOND, 0);
		d2.set(d2.HOUR, 0);
		d2.set(d2.MINUTE, 0);
		d2.set(d2.SECOND, 0);
		int diff = (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 60 * 60));
		return (diff);
	}

	public static int getDayofweek(String date) {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date(System.currentTimeMillis()));
		if (TextUtils.isEmpty(date)) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(getDateByStr2(date));
		}
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static Date getDateByStr2(String dd) {

		Date date;
		try {
			date = mDate.parse(dd);
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		return date;
	}

	public static String getChinaWeekStr(int dateOfWeek) {
		switch (dateOfWeek) {
		case 1:
			return "周日";
		case 2:
			return "周一";
		case 3:
			return "周二";
		case 4:
			return "周三";
		case 5:
			return "周四";
		case 6:
			return "周五";
		case 7:
			return "周六";
		default:
			break;
		}
		return null;
	}
	

	/** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static String getCurrYearFirst(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    }  
      
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static String getCurrYearLast(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearLast(currentYear);  
    }  
      
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static String getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return formatSimple.format(currYearFirst);  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static String getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return formatSimple.format(currYearLast);  
    } 
    public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    }
    public static Date getCurrDay(Date date) {  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, 0);  
        date = calendar.getTime();  
        return date;  
    }  
}
