package com.rxv5.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期Util类
 * 
 * @author calvin
 */
public class DateUtil {
	private static String defaultDatePattern = "yyyy-MM-dd";

	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";

		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}

		return (returnValue);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	/**
	 * 返回当前的日期时间字符串 格式："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return string
	 */
	public static String getCurrenDateTime() {
		String dt = dateTimeFormatter.format(new Date());
		return dt;
	}

	/**
	 * 返回当前的日期字符串 格式： "yy-MM-dd"
	 * 
	 * @return
	 */
	public static String getCurrenDate() {
		String date = dateFormatter.format(new Date());
		return date;
	}

	/**
	 * 根据所传的格式返回当前的时间字符串 格式 pattern
	 * 
	 * @param pattern
	 *            format pattern
	 * @return current datetime
	 */
	public static String getCurrenDateTimeByPattern(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(new Date());
		return dt;
	}

	/**
	 * 根据所传的格式，格式化想要处理的date 格式： pattern 如果date ＝＝ null，则返回当前的date字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return short format datetime
	 */
	public static String dateFormatterByPattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (date != null) {
			return sdf.format(date);
		} else {
			return sdf.format(new Date());
		}
	}

	/**
	 * 格式化传进去的日期 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String dateTimeFormatter(Date date) {
		if (date != null) {
			return dateTimeFormatter.format(date);
		} else {
			return dateTimeFormatter.format(new Date());
		}
	}

	/**
	 * 格式化传进去的日期 格式 ：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatter(Date date) {
		if (date != null) {
			return dateFormatter.format(date);
		} else {
			return dateFormatter.format(new Date());
		}
	}

	/**
	 * 把传进去的字符串按照相对应的格式转换成日期，时间 格式: "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param param
	 * 
	 * @return java.util.Date
	 */
	public static Date dateTimeParser(String param) {
		Date date = null;
		try {
			date = dateTimeFormatter.parse(param);
		} catch (ParseException ex) {
		}
		return date;
	}

	/**
	 * 把传进去的字符串按照相对应的格式转换成日期 格式：yyyy-MM-dd
	 * 
	 * @param param
	 * @return
	 */
	public static Date dateParser(String param) {
		Date date = null;
		try {
			date = dateFormatter.parse(param);
		} catch (ParseException ex) {
		}
		return date;
	}

	/**
	 * 比较两个日期的前后，前面的日期在后，则为true
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateAfter(Date date1, Date date2) {
		return date1.getTime() >= date2.getTime();
	}

	/**
	 * 比较两个日期字符串的先后，前面的日期在后，则为true
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateAfter(String date1, String date2) {
		Date datea = toCalendar(date1).getTime();
		Date dateb = toCalendar(date2).getTime();
		return DateUtil.isDateAfter(datea, dateb);
	}

	/**
	 * 与当前日期比，比较两个日期字符串的先后
	 * 
	 * @param date
	 *            要比较的日期
	 * @return
	 */
	public static boolean isDateInvalidation(Date date) {
		Date date2 = new Date();
		return DateUtil.isDateAfter(date2, date);
	}

	/**
	 * 从一个日期字符串中取出它的年份
	 * 
	 * @param strDate
	 * @return
	 */
	public static final int getYear(String strDate) {

		Calendar cale = toCalendar(strDate);
		if (cale == null) {
			return -1;
		}
		return cale.get(Calendar.YEAR);
	}

	/**
	 * 从一个日期字符串中取出它的月份
	 * 
	 * @param strDate
	 * @return
	 */
	public static final int getMonth(String strDate) {
		Calendar cale = toCalendar(strDate);
		if (cale == null) {
			return -1;
		}
		return cale.get(Calendar.MONTH) + 1;
	}

	/**
	 * 从一个日期字符串中取出它的日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static final int getDate(String strDate) {
		Calendar cale = toCalendar(strDate);
		if (cale == null) {
			return -1;
		}
		return cale.get(Calendar.DATE);
	}

	/**
	 * 把一个日期字符串转换成Calendar形式
	 * 
	 * @param strDate
	 * @return
	 */
	private static final Calendar toCalendar(String strDate) {
		Calendar cale = null;
		try {
			Date thisDate = dateTimeFormatter.parse(strDate);
			cale = Calendar.getInstance();
			cale.setTime(thisDate);
		} catch (Exception e) {
			return null;
		}
		return cale;
	}

	/**
	 * 返回昨天的日期字符串
	 * 
	 * @param strDate
	 * @return
	 */
	public static final String getYesday() {
		String strDate = getCurrenDateTime();
		Calendar cale = toCalendar(strDate);
		cale.add(Calendar.DAY_OF_YEAR, -1);
		return dateFormatterByPattern(cale.getTime(), "yyyy-MM-dd");

	}

	/**
	 * 计算出strDate之后days天后的日期字符串 days可以为负数
	 * 
	 * @param strDate
	 * @param days
	 * @return
	 */
	public static final String addDayToString(String strDate, int days) {
		Calendar cale = toCalendar(strDate);
		cale.add(Calendar.DAY_OF_YEAR, days);
		return dateFormatterByPattern(cale.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 此函数计算出date之后amount分钟的日期
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinute(Date date, int amount) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MINUTE, amount);
		return cale.getTime();
	}

	/**
	 * 此函数计算出date之后days天后的日期,days可以是负数
	 * 
	 * @param strDate
	 * @param days
	 * @return
	 */
	public static final Date addDay(Date date, int days) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.DAY_OF_YEAR, days);
		return cale.getTime();
	}

	/**
	 * 此函数计算出指定日期之后moths个月的日期,Months可以是负数
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static final Date addMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 此函数计算出指定日期之后seconds秒的日期,seconds可以是负数
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static final Date addSecond(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	/**
	 * 计算指定的两个日期之间的天数
	 * 
	 * @param postDate
	 *            之前的时间
	 * @param afterDate
	 *            之后的时间
	 * @return
	 */
	public static final int diffDay(Date postDate, Date afterDate) {
		Calendar past = Calendar.getInstance();
		past.setTime(postDate);
		Calendar after = new GregorianCalendar();
		after.setTime(afterDate);
		long diffMillis = after.getTimeInMillis() - past.getTimeInMillis();
		return (int) (diffMillis / 1000 / 60 / 60 / 24);
	}

	/**
	 * 计算指定的两个日期之间的小时
	 * 
	 * @param postDate
	 *            之前的时间
	 * @param afterDate
	 *            之后的时间
	 * @return
	 */
	public static final int diffHour(Date postDate, Date afterDate) {
		Calendar past = Calendar.getInstance();
		past.setTime(postDate);
		Calendar after = new GregorianCalendar();
		after.setTime(afterDate);
		long diffMillis = after.getTimeInMillis() - past.getTimeInMillis();
		return (int) (diffMillis / 1000 / 60 / 60);
	}

	/**
	 * 取得指定日期是周几
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static final int getWeekIndex(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		return cal.get(cal.DAY_OF_WEEK) - 1;
	}

	/**
	 * 取得指定日期是几点
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static final int getHourIndex(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		return cal.get(cal.HOUR_OF_DAY);
	}

	/**
	 * 取得指定日期的当月的第一天
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getFirstOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(cal.DAY_OF_MONTH, 1);
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取得指定日期的当月的最后一天
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getEndOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(cal.DAY_OF_MONTH));
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取得指定日期的当天的0点0分0秒
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static final Date getFirstOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取得指定日期的当天的23点59分59秒
	 * 
	 * @param date
	 *            时间。为null则为当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static final Date getEndOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(cal.HOUR_OF_DAY, 23);
		cal.set(cal.MINUTE, 59);
		cal.set(cal.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 返回当前时间的字符串，格式“yyyyMMdd”
	 * @return
	 */
	public static final String getCurrenDateStr() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		return dateFormatter.format(new Date());
	}

	/**
	 * 返回当前时间的字符串，格式“yyyyMMdd”
	 * @return
	 */
	public static final String getCurrenTimeStr() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("hhmmss");
		return dateFormatter.format(new Date());
	}
}