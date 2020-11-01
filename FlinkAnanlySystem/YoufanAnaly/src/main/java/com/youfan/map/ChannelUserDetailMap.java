package com.youfan.map;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class ChannelUserDetailMap implements MapFunction<String, ChannelInfo> {


    @Override
    public ChannelInfo map(String s) throws Exception {
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String deviceType = scanPageLog.getDeviceType();
        String visitTime = scanPageLog.getVisitTime();
        String hourTime = DateUtils.getByinterHour(visitTime);
        DeviceComomInfo deviceComomInfo = scanPageLog.getDeviceComomInfo();
        String channelinfoString = deviceComomInfo.getChannelinfo();
        String userId = deviceComomInfo.getUserId();
        //次数  ，新增用户，活跃用户，
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setDeviceType(deviceType);
        channelInfo.setUserId(userId);
        channelInfo.setChannelinfo(channelinfoString);
        channelInfo.setTimes(1l);
        channelInfo.setTimeinfo(hourTime);
        if(deviceComomInfo.isNew()){
            channelInfo.setNewusers(1l);
        }
        if(deviceComomInfo.isHourActive()){
            channelInfo.setHourActivenums(1l);
        }
        if(deviceComomInfo.isDayActive()){
            channelInfo.setDayActivenums(1l);
        }
        if(deviceComomInfo.isMonthActive()){
            channelInfo.setMonthActivenums(1l);
        }
        if(deviceComomInfo.isWeekActive()){
            channelInfo.setWeekActivenums(1l);
        }
        channelInfo.setGroupByField(userId+"=="+deviceType+"=="+hourTime+"=="+channelinfoString);
        return channelInfo;
    }
}
