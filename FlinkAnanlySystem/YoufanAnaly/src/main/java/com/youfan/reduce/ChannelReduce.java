package com.youfan.reduce;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class ChannelReduce implements ReduceFunction<ChannelInfo> {
    @Override
    public ChannelInfo reduce(ChannelInfo channelInfo, ChannelInfo t1) throws Exception {
        String groupByField = channelInfo.getGroupByField();
        String deviceType =channelInfo.getDeviceType();
        String timeInfo = channelInfo.getTimeinfo();
        String chanenelInfoString = channelInfo.getChannelinfo();

        long times1 = channelInfo.getTimes();
        long times2 = t1.getTimes();

        //新增用户
        long newusers1 = channelInfo.getNewusers();
        long newusers2 = t1.getNewusers();

        //小时活跃
        long hourActivenums1  = channelInfo.getHourActivenums();
        long hourActivenums2  = t1.getHourActivenums();
        //天活跃
        long dayActivenums1  = channelInfo.getDayActivenums();
        long dayActivenums2  = t1.getDayActivenums();
        //月活跃
        long monthActivenums1  = channelInfo.getMonthActivenums();
        long monthActivenums2  = t1.getMonthActivenums();
        //周活跃
        long weekActivenums1  = channelInfo.getWeekActivenums();
        long weekActivenums2  = t1.getWeekActivenums();

        //用户访问人数
        long userNums1  = channelInfo.getUserNums();
        long userNums2  = t1.getUserNums();

        ChannelInfo channelInfofinal = new ChannelInfo();
        channelInfofinal.setGroupByField(groupByField);
        channelInfofinal.setDeviceType(deviceType);
        channelInfofinal.setTimeinfo(timeInfo);
        channelInfofinal.setChannelinfo(chanenelInfoString);
        channelInfofinal.setTimes(times1+times2);
        channelInfofinal.setNewusers(newusers1+newusers2);
        channelInfofinal.setHourActivenums(hourActivenums1+hourActivenums2);
        channelInfofinal.setDayActivenums(dayActivenums1+dayActivenums2);
        channelInfofinal.setMonthActivenums(monthActivenums1+monthActivenums2);
        channelInfofinal.setWeekActivenums(weekActivenums1+weekActivenums2);
        channelInfofinal.setUserNums(userNums1+userNums2);
        return channelInfofinal;
    }
}
