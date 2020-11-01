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
public class ChannelMap implements MapFunction<String, ChannelInfo> {


    @Override
    public ChannelInfo map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        String deviceType = jsonObject.getString("deviceType");//0、app端 1、pc端 2、小程序端
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String visitTime = scanPageLog.getVisitTime();
        String deviceComomInfoString = jsonObject.getString("deviceComomInfo");
        DeviceComomInfo deviceComomInfo = JSONObject.parseObject(deviceComomInfoString,DeviceComomInfo.class);
        String channelInfoString = deviceComomInfo.getChannelinfo();


        String interTime = DateUtils.getByinterMinute(visitTime);

        ChannelInfo channelInfo = new ChannelInfo();

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(visitTime);
            //小时活跃状态
            if(deviceComomInfo.isHourActive()){
                channelInfo.setUserNums(1l);
            }
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(visitTime);
            //5分钟活用状态
            if(deviceComomInfo.isFiveMinuteActive()){
                channelInfo.setUserNums(1l);
            }
        }


        channelInfo.setChannelinfo(channelInfoString);
        channelInfo.setDeviceType(deviceType);
        channelInfo.setTimeinfo(interTime);
        channelInfo.setTimes(1l);
        channelInfo.setGroupByField(deviceType+"=="+channelInfoString+"=="+interTime);

        //新增用户状态
        if(deviceComomInfo.isNew()){
            channelInfo.setNewusers(1l);
        }

        //小时活跃状态
        if(deviceComomInfo.isHourActive()){
            channelInfo.setHourActivenums(1l);
        }

        //天活跃状态
        if(deviceComomInfo.isDayActive()){
            channelInfo.setDayActivenums(1l);
        }

        //月活跃状态
        if(deviceComomInfo.isMonthActive()){
            channelInfo.setMonthActivenums(1l);
        }

        //周活跃状态
        if(deviceComomInfo.isWeekActive()){
            channelInfo.setWeekActivenums(1l);
        }

        return channelInfo;
    }
}
