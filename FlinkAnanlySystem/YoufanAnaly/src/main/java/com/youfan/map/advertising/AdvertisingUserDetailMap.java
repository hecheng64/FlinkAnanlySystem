package com.youfan.map.advertising;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class AdvertisingUserDetailMap implements MapFunction<String, AdvertisingInfo> {


    @Override
    public AdvertisingInfo map(String s) throws Exception {
        ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
        String deviceType = scanPageLog.getDeviceType();
        String visitTime = scanPageLog.getVisitTime();
        String hourTime = DateUtils.getByinterHour(visitTime);
        DeviceComomInfo deviceComomInfo = scanPageLog.getDeviceComomInfo();
        String adId = scanPageLog.getAdId();
        String productId = scanPageLog.getProductId();
        String userId = deviceComomInfo.getUserId();
        //次数  ，新增用户，活跃用户，
        AdvertisingInfo advertisingInfo = new AdvertisingInfo();
        advertisingInfo.setDeviceType(deviceType);
        advertisingInfo.setUserId(userId);
        advertisingInfo.setTimes(1l);
        advertisingInfo.setTimeinfo(hourTime);
        advertisingInfo.setAdId(adId);
        advertisingInfo.setProductId(productId);
        advertisingInfo.setGroupByField(userId+"=="+deviceType+"=="+hourTime);
        return advertisingInfo;
    }
}
