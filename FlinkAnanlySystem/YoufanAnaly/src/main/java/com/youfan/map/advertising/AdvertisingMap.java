package com.youfan.map.advertising;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class AdvertisingMap implements MapFunction<String, AdvertisingInfo> {


    @Override
    public AdvertisingInfo map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String visitTime = scanPageLog.getVisitTime();
        String deviceComomInfoString = jsonObject.getString("deviceComomInfo");
        DeviceComomInfo deviceComomInfo = JSONObject.parseObject(deviceComomInfoString,DeviceComomInfo.class);
        String userId = deviceComomInfo.getUserId();
        String adId = scanPageLog.getAdId();
        String productId = scanPageLog.getProductId();
        String interTime = DateUtils.getByinterMinute(visitTime);
        AdvertisingInfo advertisingInfo = new AdvertisingInfo();
        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(visitTime);
            //小时活跃状态
            if(deviceComomInfo.isHourActive()){
                advertisingInfo.setUserNums(1l);
            }
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(visitTime);
            //5分钟活用状态
            if(deviceComomInfo.isFiveMinuteActive()){
                advertisingInfo.setUserNums(1l);
            }
        }

        if(StringUtils.isNotBlank(adId)){
               advertisingInfo.setTimes(1l);
               advertisingInfo.setTimeinfo(interTime);
               advertisingInfo.setUserId(userId);
               advertisingInfo.setGroupByField(interTime+"=="+productId+"=="+adId);
               advertisingInfo.setProductId(productId);
            advertisingInfo.setAdId(adId);
        }
        return advertisingInfo;



    }
}
