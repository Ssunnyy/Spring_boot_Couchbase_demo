package com.nearsen.nearsen.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zzf on 2017-07-15.
 */
public class DateUtils {

    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        /**设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
         * 如果最少天数必须是一整个星期，则使用值 7 调用此方法。 **/
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置周一
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.getTime();
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday

        return c.getTime();
    }

    public static long getPreWeekLastDayLastTs() {
        long curts = System.currentTimeMillis();
        Date curdate = new Date(curts);

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(curdate);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - 1);
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // Sunday
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);

        return c2.getTimeInMillis();
    }

    public static long getCurWeekLastDayLastTs() {
        long curts = System.currentTimeMillis();
        Date curdate = new Date(curts);

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(curdate);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR));
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // Sunday
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);

        return c2.getTimeInMillis();
    }

    public static long getNextNWeekLastDayLastTs(int n) {
        long curts = System.currentTimeMillis();
        Date curdate = new Date(curts);

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(curdate);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + n );
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // Sunday
        long millis1 = c2.getTimeInMillis();
        c2.set(Calendar.HOUR, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
//        long millis2 = c2.getTimeInMillis();

        return c2.getTimeInMillis();
    }

}
