package com.har.unmanned.mfront.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * 
 * @author tanzeng
 */
public class DateUtil {

	/** DateFormat:yyyyMMddHHmmss */
	public static final String FORMATE_YYYYMMDDHHMMSSZMINUS = "yyyyMMddHHmmssSSS";

	/** DateFormat:yyyy-MM-dd */
	public static final String FORMATE_YYYY_MM_DD_MINUS = "yyyy-MM-dd";

	/** DateFormat:yyyy-MM-dd HH:mm:ss */
	public static final String FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS = "yyyy-MM-dd HH:mm:ss";
	
	/** DateFormat:yyyyMMdd */
	public static final String FORMATE_YYYY_MM_DD = "yyyyMMdd";
	
	/** DateFormat:yyMMdd */
	public static final String FORMATE_YY_MM_DD = "yyMMdd";

	/** 年月日格式 */
	public static final String CHN_FORMATE_YYYY_MM_DD = "yyyy年MM月dd日";

	/** 年月日时分秒 */
	public static final String CHN_FORMATE_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
	
	/** DateFormat:HH:mm:ss */
	public static final String FORMATE_HHMMSS = "HHmmss";
	
	/** DateFormat:yyyyMMddHH */
	public static final String FORMATE_YYYYMMDDHH = "yyyyMMddHH";
	
	/** DateFormat:yyyyMMddHHmmss*/
	public static final String FORMATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 将字符串装换成日期类型
	 * @param str 需要转换的字符串
	 * @return Date 转换后的日期
	 */
	public static Date convertToDate(String format, String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException pse) {
			return null;
		}
	}
	
	/**
	 * 将字符串装换成日期类型
	 * @param str 需要转换的字符串
	 * @return Date 转换后的日期
	 */
	public static Date convertToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS);
		try {
			return sdf.parse(str);
		} catch (ParseException pse) {
			return null;
		}
	}
	
	/**
	 * 将日期转换成指定格式的字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String convertDateToStr(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获得当前时间yyyyMMddHHmmssSSS
	 * @return String 系统当前时间戳X
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYYMMDDHHMMSSZMINUS);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 得到系统当前时间戳。(精确到毫秒)
	 * @return String 系统当前时间戳X
	 */
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYYMMDDHHMMSSZMINUS);
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 获取当前日期,格式：YYYYMMDD
	 * @return String 系统当前日期
	 */
	public static String getCurrentDateYMD() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取当前日期,格式：YYMMDD
	 * @return String 系统当前日期
	 */
	public static String getCurrentDateYmd() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YY_MM_DD);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取当前日期。格式：YYYY-MM-DD
	 * @return String 系统当前日期
	 */
	public static String getCurrentDateY_M_D() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取指定日期。格式：YYYY-MM-DD
	 * @return String 系统当前日期
	 */
	public static String getCurrentDateY_M_D(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
		return sdf.format(date);
	}

	/**
	 * 获取当前日期。
	 * @return Date 系统当前日期
	 */
	public static Date getCurrentDateAsDate() {
		Date date = new Date();
		return date;
	}
	
	/**
	 * 获取系统当前日期（YYYY年MM月DD日）
	 * @return
	 */
	public static String getCurrentDateYMDChn() {
		SimpleDateFormat sdf = new SimpleDateFormat(CHN_FORMATE_YYYY_MM_DD);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取指定日期（YYYY年MM月DD日）
	 * @return
	 */
	public static String getCurrentDateYMDChn(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CHN_FORMATE_YYYY_MM_DD);
		return sdf.format(date);
	}
	
	/**
	 * 获取系统当前日期（yyyyMMddHHmmss）
	 * @return
	 */
	public static String getCurrentDateYYYYMMDDHHMMSS() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYYMMDDHHMMSS);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取系统当前日时（YYYY年MM月DD日 HH：MMrSS）
	 * @return
	 */
	public static String getCurrentDateYMDHMSChn() {
		SimpleDateFormat sdf = new SimpleDateFormat(CHN_FORMATE_YYYY_MM_DD_HH_MM_SS);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 将当前日期转化为字符串
	 * @param date FORMATE_YYYYMMDDHHMMSS
	 * @return
	 */
	public static String getStrDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYYMMDDHHMMSS);
		return sdf.format(date);
	}
	
	/**
	 * 增加分钟，计算时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addTimeByMinute(Date date, int minutes){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	/**
	 * 增加天，计算时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addTimeByDay(Date date, int days){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	/**
	 * 增加月数，计算时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addTimeByMonth(Date date, int month){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	/**
	 * 增加小时，计算时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addTimeByHour(Date date, int hours){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}
	
	/**
	 * 增加分钟，计算时间
	 * @param date
	 * @param minute
	 * @param calendarField
	 * @return
	 */
	public static Date calcTime(Date date, int addAmount, int calendarField){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField, addAmount);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前系统时间和星期几
	 * @return YYYY年MM月DD日 星期几
	 */
	public static String getCurrentDateAndWeekDay() {
		String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String week_day = dayNames[day - 1];
		return getCurrentDateYMDChn() + " "+ week_day;
	}
	
	/**
	 * 获取当前系统时间和星期几
	 * @return YYYY年MM月DD日 星期几
	 */
	public static String getCurrentDateAndWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_WEEK)-1;
		if (day==0){
			day = 7;
		} 		
		return String.valueOf(day);
	}
	
	
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss 格式)
	 * @return
	 */
	public static Date getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS);
		try {
			return sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss 格式)
	 * @return
	 */
	public static Date getCurrentDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	/***
	 * 获取一个时间与当前时间相差多少秒
	 *
	 * @param date
	 * @return
	 */
	public static long getCompareSecond(Date date) {
		long dateOld=date.getTime();
		long dateNow=new Date().getTime();
		return (dateNow-dateOld) / 1000;
	}
	
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss 格式)
	 * @return String
	 */
	public static String getCurrentDateTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss 格式)
	 * @return String
	 */
	public static String getCurrentDateHourTimeStr(String parrten) {
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		return sdf.format(new Date());
	}
	
	public static boolean compareCurrentTime(String start,String end){
		Date now = getCurrentDateTime();
		String day =  convertDateToStr(now,FORMATE_YYYY_MM_DD_MINUS);
		Date startTime =  convertToDate(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS,day+" "+ start);
		Date endTime =  convertToDate(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS,day+" "+ end);
		return startTime.before(now)&&now.before(endTime);
	}
	
	public static boolean compareCurrentTime(String stime){
		Date now = getCurrentDateTime();
		String day =  convertDateToStr(now,FORMATE_YYYY_MM_DD_MINUS);
		Date startTime =  convertToDate(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS,day+" "+ stime);
		return now.before(startTime);
	}
	
	public static Date getInterValDate(Integer interValHours){
		 Calendar cal = Calendar.getInstance();         
		 cal.setTime(new Date());          
		 cal.add(Calendar.HOUR, -interValHours); 
		 return cal.getTime();
	}
	
	public static String[] getCurrentDateTimeArray(){
		Date now = new Date();
		String day = convertDateToStr(now, FORMATE_YYYY_MM_DD);
		String time = convertDateToStr(now, FORMATE_HHMMSS);
		String[] nowArray = {day, time};
		return nowArray;
	}
	
	/**
	 * 计算日期（dt1）是否在日期（dt2）- 期间（period）的之后
	 * @param dt1 日期1
	 * @param dt2 日期2
	 * @param period 时间间隔
	 * @return dt1 ≥ dt2 - period：返回true，否则返回false
	 */
	public static boolean compareTimeInDay(Date dt1, Date dt2, int period, boolean ... notCotainsTargetDayFlg) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
		try {
			Date tmpDate1 = sdf.parse(sdf.format(dt1));
			Calendar c = Calendar.getInstance();
			c.setTime(dt2);
			c.add(Calendar.HOUR, period * 24);
			Date tmpDate2 = sdf.parse(sdf.format(c.getTime()));
			if (notCotainsTargetDayFlg != null &&notCotainsTargetDayFlg.length > 0 && notCotainsTargetDayFlg[0]) {
				if (tmpDate1.compareTo(tmpDate2) > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				if (tmpDate1.compareTo(tmpDate2) >= 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static long calcDaysPeriod(Date dt1, Date dt2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
			Date dt01 = sdf.parse(sdf.format(dt1));
			Date dt02 = sdf.parse(sdf.format(dt2));
			return (dt02.getTime() - dt01.getTime())/(3600*24*1000);
		} catch (Exception e) {}
		return 0;
	}
	
	/**
	 * 获取当前时间。
	 * @return String 系统当前时间
	 */
	public static String getCurrentDateHMS() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_HHMMSS);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取当前日期号
	 * 
	 * @author maf
	 * @return
	 */
	public static int getCurrentDay(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取上个月最后一天
	 * 
	 * @author maf
	 * @return
	 */
	public static Date getLastDayOfLastMonth(){
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH,0);
		return calendar.getTime();
	}
	
	/**
	 * 获取上个月指定一天
	 * @param day
	 * @return
	 */
	public static Date getAppointDateOfLastMonth(int day){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.MONTH, -1); 
		calendar.set(Calendar.DAY_OF_MONTH,day);
		return calendar.getTime();
	}
	
	/**
	 * 获取当月第一天
	 * 
	 * @author maf
	 * @return
	 */
	public static Date getFirstDayOfMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	/**
	 * 获取当月指定一天
	 * 
	 * @author maf
	 * @return
	 */
	public static Date getAppointDayOfMonth(int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,day);		
		return calendar.getTime();
	}
	
	
	/****
	 * 获得昨天的年月日时间  YYYY-MM-dd
	 *
	 * @return  昨天时间字符串
	 */
	public static String getYesterdayDateYMD(){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
		String dateString = formatter.format(date);
		return dateString;
		
	}
	
	/**
	 * 获得昨天的时间
	 * @param format 日期格式
	 * @return
	 */
	public static String getYesterdayDate(String format){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	
	/***
	 * 把日期格式转化为 YYYY-MM-dd HH:mm:ss格式字符串
	 *
	 * @param date
	 * @return
	 */
	public static String convertDateToStr(Date date) {
		return convertDateToStr(date,FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS);
	}
	
	/**
	 * 字符串格式 yyyyMMddHHmmss转化为日期格式
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date convertStrToDate(String date) throws ParseException {
		 SimpleDateFormat sdf1 = new SimpleDateFormat(FORMATE_YYYYMMDDHHMMSS);
	     SimpleDateFormat sdf2 =new SimpleDateFormat(FORMATE_YYYY_MM_DD_HH_MM_SS_MINUS);
		 return convertToDate(sdf2.format(sdf1.parse(date)));
	}
	
	/***
	 * 获取几个月前的当天
	 * @param num 几个月前
	 * @return
	 */
	public static String getOldDateYMD(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -num);    //得到前3个月
		return DateUtil.getCurrentDateY_M_D(calendar.getTime());
	}
	
	/**
     * 获取指定时间的那天 23:59:59 的时间
     * @param date
     * @author yb
     * @return
     */
    public static Date getDayEnd(Date date) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(Calendar.HOUR_OF_DAY, 23);
         calendar.set(Calendar.MINUTE, 59);
         calendar.set(Calendar.SECOND, 59);
         calendar.set(Calendar.MILLISECOND, 999);
         return calendar.getTime();
    }
    
    /**
     * 获取指定时间的那天 00:00:00 的时间
     * @param date
     * @author yb
     * @return
     */
    public static Date getDayBegin(Date date) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);
         calendar.set(Calendar.MILLISECOND, 0);
         return calendar.getTime();
    }
    
    /**
	 * 获取当天的结束时间
	 * @return
	 */
	public static Date endOfTodDay(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR_OF_DAY, 23);
		 calendar.set(Calendar.MINUTE, 59);
		 calendar.set(Calendar.SECOND, 59);
		 calendar.set(Calendar.MILLISECOND, 999);
		 return calendar.getTime();
	}
	
	/**
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) { 
        Calendar calst = Calendar.getInstance();   
        Calendar caled = Calendar.getInstance();   
        calst.setTime(early);   
        caled.setTime(late);   
        //设置时间为0时   
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数   
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24 + 1;   
        return days;   
   }  
	
	public static Date getMonthBegin(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
 	public static Date getNextMonthBegin(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
 	
 	public static Date getMonthBegin(Date date,int month) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
 	
 	/**
	 * 
	 * @Title: isLatestWeek
	 * @Description: TODO(获得n天之前的日期)
	 * @author jiangjj
	 * @param day
	 * @return
	 * @throws
	 */
	public static String latestWeek(int day) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(new Date());// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -day); // 设置为n天前
		Date before = calendar.getTime(); // 得到n天前的时间
		SimpleDateFormat sf=new SimpleDateFormat(FORMATE_YYYY_MM_DD_MINUS);
		return sf.format(before);
	}
	
	/**
	 * 获取前一天的 0时0分0秒
	 * @return
	 */
	public static Date getFrontDayDateBegin(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,-1);
		 calendar.set(Calendar.HOUR_OF_DAY, 00);
		 calendar.set(Calendar.MINUTE, 00);
		 calendar.set(Calendar.SECOND, 00);
		 calendar.set(Calendar.MILLISECOND, 000);
		 return calendar.getTime();
	}
	
	/**
	 * 获取前一天的 23时59分59秒
	 * @return
	 */
	public static Date getFrontDayDateEnd(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,-1);
		 calendar.set(Calendar.HOUR_OF_DAY, 23);
		 calendar.set(Calendar.MINUTE, 59);
		 calendar.set(Calendar.SECOND, 59);
		 calendar.set(Calendar.MILLISECOND, 999);
		 return calendar.getTime();
	}
	/**
	 * 获取当前时间的前一天
	 * @return
	 */
	public static Date getFrontDayCurrDate(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,-1);
		 return calendar.getTime();
	}
	
	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 判断两个日期是否同一天
	 * @param date1
	 * @param date2
	 * @return  true为同一天， false为不同一天
	 */
	public static boolean isSameDate(Date date1, Date date2) {
       Calendar cal1 = Calendar.getInstance();
       cal1.setTime(date1);
       Calendar cal2 = Calendar.getInstance();
       cal2.setTime(date2);
       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
               .get(Calendar.YEAR);
       boolean isSameMonth = isSameYear
               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
       boolean isSameDate = isSameMonth
               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                       .get(Calendar.DAY_OF_MONTH);
       return isSameDate;
   }
	
	/** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：yyyy-MM-dd HH:mm:ss 
     * @param str2 时间参数 2 格式：yyyy-MM-dd HH:mm:ss 
     * @return long[] 返回值为：{天, 时, 分, 秒} 
     */  
    public static long[] getDistanceTimes(Date one, Date two) {  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        long time1 = one.getTime();  
        long time2 = two.getTime();  
        long diff ;  
        if(time1<time2) {  
            diff = time2 - time1;  
        } else {  
            diff = time1 - time2;  
        }  
        day = diff / (24 * 60 * 60 * 1000);  
        hour = (diff / (60 * 60 * 1000) - day * 24);  
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        long[] times = {day, hour, min, sec};  
        return times;  
    }  
	
	public static Date[] handleDate(String date){
		String[] dateArr={};
		if(!CheckUtil.isNull(date)){
			dateArr = date.trim().split("-");
			Date[] dateArry = new Date[2];
			try {
				for (int i=0;i<dateArr.length;i++) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					dateArry[i] = sdf.parse(dateArr[i]);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return dateArry;
		}
		
		return null;
	}
	public static void main(String[] args) throws ParseException{
		Date date1 = new Date();
//		Date date2 =  convertToDate("2017-07-11 08:29:00");
		Date date2 = convertStrToDate("20170711082900");
		Date date3 = convertStrToDate("20170711082901");
//		System.out.println(date1);
//		System.out.println(date2);
//		System.out.println(isSameDate(date1,date2));
//		System.out.println(DateUtil.getCurrentTimeStamp());
//		System.out.println(DateUtil.getCurrentDateYmd());
		long[] time = getDistanceTimes(date1, date2);
		System.out.println(time[0]+"天"+time[1]+"时"+time[2]+"分"+time[3]+"秒");
		System.out.println(time[3]+time[2]*60+time[1]*60*60+time[0]*60*60*24);
		System.out.println(date2.getTime()-date3.getTime());
		
		
	}
}
