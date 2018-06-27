package com.ycjcjy.gene.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/23
 **/
public class CDateUtil {
    private static final SimpleDateFormat sdfSimple = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static Map<String,Date> turnMonthStart(Date dateStart, Date dateEnd){
        Map<String,Date> result = new HashMap<String, Date>();
        Calendar calendar = Calendar.getInstance();
        if(dateStart!=null){
            calendar.setTime(dateStart);
            int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),minDay,00,00,00);
            dateStart = calendar.getTime();
            result.put("dateStart",dateStart);
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),maxDay,23,59,59);
            dateEnd = calendar.getTime();
            result.put("dateEnd",dateEnd);
        }
        return result;
    }


    public static Map<String,Date> turnWeekStart(Date dateStar,Date dateEnd){
        Map<String,Date> dateMap = new HashMap<String, Date>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendar.setTime(dateStar);
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayofweek==1){
            dayofweek+=7;
        }
        calendar.add(Calendar.DATE,2-dayofweek);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        dateStar = calendar.getTime();//选中日期本周开始时间
        dateMap.put("dateStart",dateStar);
        calendarEnd.setTime(dateStar);
        calendarEnd.add(Calendar.DAY_OF_WEEK,6);
        calendarEnd.set(Calendar.HOUR_OF_DAY,23);
        calendarEnd.set(Calendar.MINUTE,59);
        calendarEnd.set(Calendar.SECOND,59);
        dateEnd = calendarEnd.getTime();//本周结束时间
        dateMap.put("dateEnd",dateEnd);
        return dateMap;
    }


    public static Date getDayBegin(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date getDayEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    public static String dayForWeek(Date pTime){
        String[] weeks = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        Calendar c = Calendar.getInstance();
        c.setTime(pTime);
        int dayForWeek;
        if (c.get(7) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(7) - 1;
        }
        return weeks[dayForWeek - 1];
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }


    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    //获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }



    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d){ calendar.setTime(d);}
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) {calendar.setTime(d);}
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * 计算本周周一和周日日期
     *
     * @return MAP
     * @author jiangfengcheng
     */
    public static HashMap<String, String> getWeekByDate() {
        HashMap<String, String> dayMap = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdfSimple.format(cal.getTime())); // 输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdfSimple.format(cal.getTime());
        System.out.println("所在周星期一的日期：" + imptimeBegin);
        dayMap.put("Monday", imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdfSimple.format(cal.getTime());
        System.out.println("所在周星期日的日期：" + imptimeEnd);
        dayMap.put("SunDay", imptimeEnd);
        return dayMap;
    }

    /**
     * 获得本月第一天0点时间本月最后一天24点时间
     * @return
     * @author jiangfengcheng
     */
    public static HashMap<String, String> getTimesMonth() {
        HashMap<String, String> dayMap = new HashMap<String, String>();
        Calendar cals = Calendar.getInstance();
        cals.set(cals.get(Calendar.YEAR), cals.get(Calendar.MONDAY), cals.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cals.set(Calendar.DAY_OF_MONTH, cals.getActualMinimum(Calendar.DAY_OF_MONTH));
        Calendar cale = Calendar.getInstance();
        cale.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONDAY), cale.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        cale.set(Calendar.HOUR_OF_DAY, 24);
// 本月第一天
        String lastBeginDate = sdfSimple.format(cals.getTime());
        System.out.println(lastBeginDate);
        dayMap.put("Months", lastBeginDate);
// 本月第二天
        String lastEndDate = sdfSimple.format(cale.getTime());
        System.out.println(lastEndDate);
        dayMap.put("Monthe", lastEndDate);

        return dayMap;
    }

}
