package com.youfan.reduce;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class ChannelUserDetailReduce implements ReduceFunction<ChannelInfo> {
    @Override
    public ChannelInfo reduce(ChannelInfo channelInfo, ChannelInfo t1) throws Exception {
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



        ChannelInfo channelInfofinal = new ChannelInfo();
        channelInfofinal.setGroupByField(channelInfo.getGroupByField());
        channelInfofinal.setTimes(times1+times2);
        channelInfofinal.setNewusers(newusers1+newusers2);
        channelInfofinal.setHourActivenums(hourActivenums1+hourActivenums2);
        channelInfofinal.setDayActivenums(dayActivenums1+dayActivenums2);
        channelInfofinal.setMonthActivenums(monthActivenums1+monthActivenums2);
        channelInfofinal.setWeekActivenums(weekActivenums1+weekActivenums2);
        channelInfofinal.setDeviceType(channelInfo.getDeviceType());
        channelInfofinal.setTimeinfo(channelInfo.getTimeinfo());
        channelInfofinal.setUserId(channelInfo.getUserId());
        channelInfofinal.setChannelinfo(channelInfo.getChannelinfo());
        return  channelInfofinal;
    }
}
