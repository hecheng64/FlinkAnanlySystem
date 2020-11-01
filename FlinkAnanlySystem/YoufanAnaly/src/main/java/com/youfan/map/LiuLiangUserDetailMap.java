package com.youfan.map;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class LiuLiangUserDetailMap implements MapFunction<String, LiuLiangInfo> {


    @Override
    public LiuLiangInfo map(String s) throws Exception {
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String deviceType = scanPageLog.getDeviceType();
        String visitTime = scanPageLog.getVisitTime();
        String hourTime = DateUtils.getByinterHour(visitTime);
        DeviceComomInfo deviceComomInfo = scanPageLog.getDeviceComomInfo();
        String userId = deviceComomInfo.getUserId();
        //次数  ，新增用户，活跃用户，
        LiuLiangInfo liuLiangInfo = new LiuLiangInfo();
        liuLiangInfo.setDeviceType(deviceType);
        liuLiangInfo.setUserId(userId);
        liuLiangInfo.setTimes(1l);
        liuLiangInfo.setTimeinfo(hourTime);
        if(deviceComomInfo.isNew()){
            liuLiangInfo.setNewusers(1l);
        }
        if(deviceComomInfo.isHourActive()){
            liuLiangInfo.setHourActivenums(1l);
        }
        if(deviceComomInfo.isDayActive()){
            liuLiangInfo.setDayActivenums(1l);
        }
        if(deviceComomInfo.isMonthActive()){
            liuLiangInfo.setMonthActivenums(1l);
        }
        if(deviceComomInfo.isWeekActive()){
            liuLiangInfo.setWeekActivenums(1l);
        }
        liuLiangInfo.setGroupByField(userId+"=="+deviceType+"=="+hourTime);
        return liuLiangInfo;
    }
}
