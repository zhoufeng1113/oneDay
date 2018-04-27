package com.homevip.util.game;

import com.homevip.util.date.DateTimeUtil;
import com.homevip.util.em.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 抽奖相关公共代码.
 */
public class GameUtil {
    public static List<String> getdateRange(String createdate){
        List<String> dates = new ArrayList<String>();
        String createdatestr =null;
        String enddatestr =null;
        Date gamestartdate = DateTimeUtil.parseDate(createdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date gamepdate1 = DateTimeUtil.addDay(DateTimeUtil.parseDate(createdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"),7);
        Date gamepdate2 = DateTimeUtil.addDay(DateTimeUtil.parseDate(createdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"),14);
        Date gamepdate3 = DateTimeUtil.addDay(DateTimeUtil.parseDate(createdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"),21);
        Date nowate = DateTimeUtil.getDate(DateTimeUtil.getCurDateTime());
        //测试用到
//        nowate = DateTimeUtil.getDate("2016-02-20 00:00:01");
        // if(DateTimeUtil.parseDate(marketting.getCreatedate()+" 00:00:00").equals())

        if(nowate.after(gamestartdate)&&nowate.before(gamepdate1)){
            createdatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(gamestartdate);
            enddatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(gamepdate1);
        }else if(nowate.after(gamepdate1)&&nowate.before(gamepdate2)){
            createdatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(DateTimeUtil.addDay(gamepdate1,0));
            enddatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(gamepdate2);
        }else if(nowate.after(gamepdate2)&&nowate.before(gamepdate3)){
            createdatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(DateTimeUtil.addDay(gamepdate2,0));
            enddatestr = DateUtil.dateToStr_yyyy_MM_dd_HH_mm_ss(gamepdate3);
        }
        if(createdate!=null){
            dates.add(createdatestr);

        }
        if(enddatestr!=null){
            dates.add(enddatestr);
        }
        return dates;

    }
}
