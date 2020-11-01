package com.youfan.reduce;

import com.youfan.entity.LiuLiangInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class LiuLiangReduce implements ReduceFunction<LiuLiangInfo> {
    @Override
    public LiuLiangInfo reduce(LiuLiangInfo liuLiangInfo, LiuLiangInfo t1) throws Exception {
        long times1 = liuLiangInfo.getTimes();
        long times2 = t1.getTimes();

        //新增用户
        long newusers1 = liuLiangInfo.getNewusers();
        long newusers2 = t1.getNewusers();

        //小时活跃
        long hourActivenums1  = liuLiangInfo.getHourActivenums();
        long hourActivenums2  = t1.getHourActivenums();
        //天活跃
        long dayActivenums1  = liuLiangInfo.getDayActivenums();
        long dayActivenums2  = t1.getDayActivenums();
        //月活跃
        long monthActivenums1  = liuLiangInfo.getMonthActivenums();
        long monthActivenums2  = t1.getMonthActivenums();
        //周活跃
        long weekActivenums1  = liuLiangInfo.getWeekActivenums();
        long weekActivenums2  = t1.getWeekActivenums();

        //用户数量
        long userNums1 = liuLiangInfo.getUserNums();
        long userNums2 = t1.getUserNums();

        LiuLiangInfo liuLiangInfofinal = new LiuLiangInfo();
        liuLiangInfofinal.setGroupByField(liuLiangInfo.getGroupByField());
        liuLiangInfofinal.setTimes(times1+times2);
        liuLiangInfofinal.setNewusers(newusers1+newusers2);
        liuLiangInfofinal.setHourActivenums(hourActivenums1+hourActivenums2);
        liuLiangInfofinal.setDayActivenums(dayActivenums1+dayActivenums2);
        liuLiangInfofinal.setMonthActivenums(monthActivenums1+monthActivenums2);
        liuLiangInfofinal.setWeekActivenums(weekActivenums1+weekActivenums2);
        liuLiangInfofinal.setUserNums(userNums1+userNums2);
        liuLiangInfofinal.setDeviceType(liuLiangInfo.getDeviceType());
        liuLiangInfofinal.setTimeinfo(liuLiangInfo.getTimeinfo());
        return liuLiangInfofinal;
    }
}
