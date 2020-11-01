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
public class LiuLiangMap implements MapFunction<String, LiuLiangInfo> {


    @Override
    public LiuLiangInfo map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        String deviceType = jsonObject.getString("deviceType");//0、app端 1、pc端 2、小程序端
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String visitTime = scanPageLog.getVisitTime();
        String deviceComomInfoString = jsonObject.getString("deviceComomInfo");
        DeviceComomInfo deviceComomInfo = JSONObject.parseObject(deviceComomInfoString,DeviceComomInfo.class);



        LiuLiangInfo liuLiangInfo = new LiuLiangInfo();
        String interTime = "";
        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(visitTime);
            //小时活跃状态
            if(deviceComomInfo.isHourActive()){
                liuLiangInfo.setUserNums(1l);
            }
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(visitTime);
            //5分钟活用状态
            if(deviceComomInfo.isFiveMinuteActive()){
                liuLiangInfo.setUserNums(1l);
            }
        }

        liuLiangInfo.setTimeinfo(interTime);
        liuLiangInfo.setDeviceType(deviceType);
        liuLiangInfo.setGroupByField(interTime+"=="+deviceType);
        liuLiangInfo.setTimes(1l);

        //新增用户状态
        if(deviceComomInfo.isNew()){
            liuLiangInfo.setNewusers(1l);
        }

        //小时活跃状态
        if(deviceComomInfo.isHourActive()){
            liuLiangInfo.setHourActivenums(1l);
        }

        //天活跃状态
        if(deviceComomInfo.isDayActive()){
            liuLiangInfo.setDayActivenums(1l);
        }

        //月活跃状态
        if(deviceComomInfo.isMonthActive()){
            liuLiangInfo.setMonthActivenums(1l);
        }

        //周活跃状态
        if(deviceComomInfo.isWeekActive()){
            liuLiangInfo.setWeekActivenums(1l);
        }
        return liuLiangInfo;
    }
}
