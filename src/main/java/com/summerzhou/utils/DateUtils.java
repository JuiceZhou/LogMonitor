package com.summerzhou.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对日期进行操作
 */
public class DateUtils {
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getDate());
    }
}
