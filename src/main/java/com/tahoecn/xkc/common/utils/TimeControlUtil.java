package com.tahoecn.xkc.common.utils;

import com.tahoecn.core.date.DateTime;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class TimeControlUtil {

    public static boolean judgmentDate( Date startTime, Date endTime, Double hour) {
        //1当前时间 2活动开始时间  hour开始几小时前 不可用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");

        Date now = null;
        Date start = null;
        Date end = null;
        try {
            now = sdf.parse(DateTime.now().toString());
//            System.out.println("now = " + now);
            start = startTime;

            end = endTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //活动开始前小时
        double temp = hour * 1000 * 60 * 60;
//        System.out.println("temp = " + temp);

        long time = start.getTime() - now.getTime();

//        System.out.println("time = " + time);
        long huodongshijian = start.getTime() - end.getTime();

        //当前时间为活动结束 返回真
        if (now.getTime() > end.getTime()) {
            return true;
        }
        //开始时间减去当前时间 如果小于规定的hour  返回假 不可修改
        return time >= temp;
    }



}
